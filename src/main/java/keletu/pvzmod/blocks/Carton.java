package keletu.pvzmod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.LinkedHashSet;
import java.util.Set;

public class Carton extends HorizontalDirectionalBlock {
    public static final IntegerProperty PILE = IntegerProperty.create("pile", 0, 4);
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 2.0D, 16.0D, 12.0D, 14.0D);
    protected static final VoxelShape SHAPE_2 = Block.box(2.0D, 0.0D, 0.0D, 14.0D, 12.0D, 16.0D);

    public Carton(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PILE, 0).setValue(OPEN, false));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        return direction == Direction.NORTH || direction == Direction.SOUTH ? SHAPE : SHAPE_2;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite()).setValue(PILE, 0).setValue(OPEN, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING).add(PILE).add(OPEN);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        if (player.isCrouching() && state.getValue(PILE) == 0) {
            level.setBlock(pos, state.setValue(OPEN, !state.getValue(OPEN)), 3);
            return InteractionResult.SUCCESS;
        }

        if (state.getValue(OPEN)) {
            return InteractionResult.PASS;
        }

        if (state.getValue(PILE) != 0) {
            return InteractionResult.PASS;
        }

        if (!canBuildPileStructure(level, pos)) {
            return InteractionResult.PASS;
        }

        Direction facing = state.getValue(FACING);

        setPileIfCarton(level, pos, 2);

        setPileIfCarton(level, pos.north().west(), 1);
        setPileIfCarton(level, pos.south().west(), 1);
        setPileIfCarton(level, pos.north().east(), 1);
        setPileIfCarton(level, pos.south().east(), 1);

        setPileIfCarton(level, getUpperLeft(level, pos), 3);
        setPileIfCarton(level, getUpperRight(level, pos), 3);

        for (BlockPos airPos : getPile4Positions(pos)) {
            BlockState newState = this.defaultBlockState()
                    .setValue(FACING, facing)
                    .setValue(PILE, 4);

            level.setBlock(airPos, newState, 3);
        }

        return InteractionResult.SUCCESS;
    }

    private BlockPos findPileCenter(Level level, BlockPos brokenPos) {
        for (int x = -3; x <= 3; x++) {
            for (int y = -3; y <= 1; y++) {
                for (int z = -3; z <= 3; z++) {
                    BlockPos possibleCenter = brokenPos.offset(x, y, z);
                    BlockState state = level.getBlockState(possibleCenter);

                    if (state.is(this) && state.getValue(PILE) == 2) {
                        if (isPartOfGroup(level, possibleCenter, brokenPos)) {
                            return possibleCenter;
                        }
                    }
                }
            }
        }

        return null;
    }

    private boolean isPartOfGroup(Level level, BlockPos center, BlockPos pos) {
        if (pos.equals(center)) return true;

        if (pos.equals(center.north().west())) return true;
        if (pos.equals(center.south().west())) return true;
        if (pos.equals(center.north().east())) return true;
        if (pos.equals(center.south().east())) return true;

        if (pos.equals(getUpperLeft(level, center))) return true;
        if (pos.equals(getUpperRight(level, center))) return true;

        return getPile4Positions(center).contains(pos);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!level.isClientSide) {
            if (!state.is(newState.getBlock())) {
                onCartonBroken(level, pos, state);
            }
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }

    private boolean isNormalCarton(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return state.is(this) && state.getValue(PILE) == 0;
    }

    private BlockPos getUpperLeft(Level level, BlockPos center) {
        if (level.getBlockState(center).getValue(FACING) == Direction.NORTH || level.getBlockState(center).getValue(FACING) == Direction.SOUTH)
            return center.above().north();
        return center.above().west();
    }

    private BlockPos getUpperRight(Level level, BlockPos center) {
        if (level.getBlockState(center).getValue(FACING) == Direction.NORTH || level.getBlockState(center).getValue(FACING) == Direction.SOUTH)
            return center.above().south();
        return center.above().east();
    }

    private boolean canBuildPileStructure(Level level, BlockPos center) {
        if (!isNormalCarton(level, center)) {
            return false;
        }

        if (!isNormalCarton(level, center.north().west())) return false;
        if (!isNormalCarton(level, center.south().west())) return false;
        if (!isNormalCarton(level, center.north().east())) return false;
        if (!isNormalCarton(level, center.south().east())) return false;

        if (!isNormalCarton(level, getUpperLeft(level, center))) return false;
        if (!isNormalCarton(level, getUpperRight(level, center))) return false;

        for (BlockPos airPos : getPile4Positions(center)) {
            if (!level.getBlockState(airPos).isAir()) {
                return false;
            }
        }

        return true;
    }

    private void onCartonBroken(Level level, BlockPos brokenPos, BlockState brokenState) {
        int pile = brokenState.getValue(PILE);

        if (pile == 0) {
            return;
        }

        if (pile == 2) {
            resetPileGroup(level, brokenPos);
            return;
        }

        BlockPos center = findPileCenter(level, brokenPos);

        if (center != null) {
            resetPileGroup(level, center);
        }
    }

    private Set<BlockPos> getPile4Positions(BlockPos center) {
        Set<BlockPos> positions = new LinkedHashSet<>();

        addPile4AroundUpperCarton(positions, center);

        return positions;
    }

    private void addPile4AroundUpperCarton(Set<BlockPos> positions, BlockPos upperCartonPos) {
        positions.add(upperCartonPos.north());
        positions.add(upperCartonPos.south());
        positions.add(upperCartonPos.west());
        positions.add(upperCartonPos.east());
        positions.add(upperCartonPos.above());
    }

    private void resetPileGroup(Level level, BlockPos center) {
        setPileIfCarton(level, center, 0);

        setPileIfCarton(level, center.north().west(), 0);
        setPileIfCarton(level, center.south().west(), 0);
        setPileIfCarton(level, center.north().east(), 0);
        setPileIfCarton(level, center.south().east(), 0);

        setPileIfCarton(level, center.above().north(), 0);
        setPileIfCarton(level, center.above().south(), 0);
        setPileIfCarton(level, center.above().west(), 0);
        setPileIfCarton(level, center.above().east(), 0);

        for (BlockPos pile4Pos : getPile4Positions(center)) {
            removePile4Carton(level, pile4Pos);
        }
    }

    private void removePile4Carton(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);

        if (!state.is(this)) {
            return;
        }

        if (state.getValue(PILE) != 4) {
            return;
        }

        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
    }

    private void setPileIfCarton(Level level, BlockPos pos, int pile) {
        BlockState state = level.getBlockState(pos);

        if (!state.is(this)) {
            return;
        }

        if (state.getValue(PILE) == pile) {
            return;
        }

        level.setBlock(pos, state.setValue(PILE, pile), 3);
    }
}
