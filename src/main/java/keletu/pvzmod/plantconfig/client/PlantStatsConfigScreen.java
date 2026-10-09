package keletu.pvzmod.plantconfig.client;

import keletu.pvzmod.box.client.util.BlurHandler;
import keletu.pvzmod.box.client.util.GuiItemMove;
import keletu.pvzmod.init.PVZItems;
import keletu.pvzmod.network.PVZNetworking;
import keletu.pvzmod.network.SavePlantStatsConfigPacket;
import keletu.pvzmod.plantconfig.PlantStatConfig;
import keletu.pvzmod.plantconfig.PlantStatManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PlantStatsConfigScreen extends Screen {
    private static final int OUTER_MARGIN = 14;
    private static final int GAP = 10;
    private static final int ROW_HEIGHT = 18;
    private static final int FIELD_ROW_HEIGHT = 24;
    private static final int BOTTOM_HEIGHT = 34;

    private final LinkedHashMap<String, PlantStatConfig> configs;
    private final List<String> plantIds;
    private final List<String> visibleFieldKeys = new ArrayList<>();
    private final List<EditBox> fieldBoxes = new ArrayList<>();
    private int selectedIndex;
    private int plantScroll;
    private int fieldScroll;
    private String status = "";
    private float previewRotX = 0.45F;
    private float previewRotY = 0.18F;
    private float previewVelX;
    private float previewVelY;
    private boolean draggingPreview;

    public PlantStatsConfigScreen(LinkedHashMap<String, PlantStatConfig> configs) {
        super(Component.literal("Plant Stats Config"));
        this.configs = copy(configs);
        this.plantIds = new ArrayList<>(this.configs.keySet());
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    protected void init() {
        BlurHandler.updateShader(false);
        refreshWidgets();
    }

    private void refreshWidgets() {
        this.clearWidgets();
        this.fieldBoxes.clear();
        this.visibleFieldKeys.clear();

        int rightX = rightX();
        int rightW = rightW();
        int buttonY = this.height - OUTER_MARGIN - 24;
        int closeW = 52;
        int resetW = 74;
        int saveW = 62;
        this.addRenderableWidget(Button.builder(Component.literal("保存"), button -> save())
                .bounds(rightX + rightW - closeW - resetW - saveW - 24, buttonY, saveW, 20)
                .build());
        this.addRenderableWidget(Button.builder(Component.literal("重置默认"), button -> resetDefaults())
                .bounds(rightX + rightW - closeW - resetW - 14, buttonY, resetW, 20)
                .build());
        this.addRenderableWidget(Button.builder(Component.literal("关闭"), button -> onClose())
                .bounds(rightX + rightW - closeW - 4, buttonY, closeW, 20)
                .build());

        PlantStatConfig config = selectedConfig();
        if (config == null || config.values == null) {
            return;
        }

        List<String> keys = new ArrayList<>(config.values.keySet());
        int fieldTop = fieldTop();
        int visibleRows = Math.max(1, (this.height - fieldTop - BOTTOM_HEIGHT - OUTER_MARGIN) / FIELD_ROW_HEIGHT);
        this.fieldScroll = Mth.clamp(this.fieldScroll, 0, Math.max(0, keys.size() - visibleRows));
        int fieldWidth = inputWidth();
        int fieldX = rightX + rightW - fieldWidth - 18;

        for (int i = 0; i < visibleRows && this.fieldScroll + i < keys.size(); i++) {
            String key = keys.get(this.fieldScroll + i);
            visibleFieldKeys.add(key);
            EditBox box = new EditBox(this.font, fieldX, fieldTop + i * FIELD_ROW_HEIGHT + 3, fieldWidth, 18, Component.literal(key));
            box.setMaxLength(32);
            box.setValue(format(config.get(key, 0.0D)));
            this.fieldBoxes.add(box);
            this.addRenderableWidget(box);
        }
    }

    private void save() {
        captureFieldValues();
        PVZNetworking.sendToServer(new SavePlantStatsConfigPacket(this.configs));
        this.status = "已发送保存请求";
    }

    private void resetDefaults() {
        captureFieldValues();
        this.configs.clear();
        this.configs.putAll(PlantStatManager.defaultConfigs());
        this.plantIds.clear();
        this.plantIds.addAll(this.configs.keySet());
        this.selectedIndex = Mth.clamp(this.selectedIndex, 0, Math.max(0, this.plantIds.size() - 1));
        this.fieldScroll = 0;
        this.status = "已恢复默认值，保存后生效";
        refreshWidgets();
    }

    private void captureFieldValues() {
        PlantStatConfig config = selectedConfig();
        if (config == null || config.values == null || this.visibleFieldKeys.size() != this.fieldBoxes.size()) {
            return;
        }
        for (int i = 0; i < this.fieldBoxes.size(); i++) {
            String raw = this.fieldBoxes.get(i).getValue().trim();
            if (raw.isEmpty()) {
                continue;
            }
            try {
                config.values.put(this.visibleFieldKeys.get(i), Double.parseDouble(raw));
            } catch (NumberFormatException ignored) {
                this.status = "有数值格式不正确，未写入该格";
            }
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(graphics);
        BlurHandler.updateShader(false);
        renderPanels(graphics);

        graphics.drawString(this.font, "植物数值配置", OUTER_MARGIN + 2, 14, 0xFFFFFFFF, false);
        renderPlantList(graphics, mouseX, mouseY);
        renderPreview(graphics, mouseX, mouseY);
        renderFields(graphics);
        if (!this.status.isBlank()) {
            graphics.drawString(this.font, fit(this.status, leftW() - 16), OUTER_MARGIN + 8, this.height - OUTER_MARGIN - 19, 0xFFB8F5BE, false);
        }
        super.render(graphics, mouseX, mouseY, partialTicks);
    }

    private void renderPanels(GuiGraphics graphics) {
        int top = 36;
        int bottom = this.height - OUTER_MARGIN;
        graphics.fill(OUTER_MARGIN, top, OUTER_MARGIN + leftW(), bottom, 0x6612141B);
        graphics.fill(rightX(), top, rightX() + rightW(), bottom, 0x66171A22);
        graphics.fill(OUTER_MARGIN, top, OUTER_MARGIN + leftW(), top + 1, 0xCCD3D3D3);
        graphics.fill(rightX(), top, rightX() + rightW(), top + 1, 0xCCD3D3D3);
        graphics.fill(rightX() + 12, this.height - OUTER_MARGIN - BOTTOM_HEIGHT, rightX() + rightW() - 12, this.height - OUTER_MARGIN - BOTTOM_HEIGHT + 1, 0xAAD3D3D3);
    }

    private void renderPlantList(GuiGraphics graphics, int mouseX, int mouseY) {
        int listTop = listTop();
        int leftX = OUTER_MARGIN;
        int leftW = leftW();
        int visibleRows = Math.max(1, (this.height - listTop - BOTTOM_HEIGHT - OUTER_MARGIN) / ROW_HEIGHT);
        this.plantScroll = Mth.clamp(this.plantScroll, 0, Math.max(0, this.plantIds.size() - visibleRows));
        graphics.drawString(this.font, "植物", leftX + 10, listTop - 14, 0xFFE9EDF5, false);

        for (int i = 0; i < visibleRows && this.plantScroll + i < this.plantIds.size(); i++) {
            int index = this.plantScroll + i;
            int y = listTop + i * ROW_HEIGHT;
            boolean selected = index == this.selectedIndex;
            boolean hovered = mouseX >= leftX + 8 && mouseX <= leftX + leftW - 8 && mouseY >= y && mouseY < y + ROW_HEIGHT - 2;
            int color = selected ? 0xAA2F5DA8 : hovered ? 0x66252C39 : 0x421C2028;
            graphics.fill(leftX + 8, y, leftX + leftW - 8, y + ROW_HEIGHT - 2, color);
            if (selected) {
                graphics.fill(leftX + 8, y, leftX + 11, y + ROW_HEIGHT - 2, 0xFF7FB2FF);
            }
            graphics.drawString(this.font, fit(selectedName(index), leftW - 28), leftX + 16, y + 5, selected ? 0xFFFFFFFF : 0xFFCDD4E0, false);
        }
    }

    private void renderFields(GuiGraphics graphics) {
        PlantStatConfig config = selectedConfig();
        if (config == null) {
            return;
        }

        int rightX = rightX();
        int rightW = rightW();
        int headerX = rightX + 16;
        graphics.drawString(this.font, fit(config.name == null ? plantIds.get(selectedIndex) : config.name, rightW - 32), headerX, 48, 0xFFFFFFFF, false);

        int labelX = rightX + 18;
        int rowW = rightW - 36;
        int fieldTop = fieldTop();
        int inputX = rightX + rightW - inputWidth() - 18;
        for (int i = 0; i < this.visibleFieldKeys.size(); i++) {
            String key = this.visibleFieldKeys.get(i);
            int y = fieldTop + i * FIELD_ROW_HEIGHT;
            int rowColor = i % 2 == 0 ? 0x3A222631 : 0x24222631;
            graphics.fill(labelX - 6, y, labelX - 6 + rowW, y + FIELD_ROW_HEIGHT - 2, rowColor);
            graphics.drawString(this.font, fit(friendlyKey(key), Math.max(44, inputX - labelX - 12)), labelX, y + 8, 0xFFE3E7EE, false);
        }
    }

    private void renderPreview(GuiGraphics graphics, int mouseX, int mouseY) {
        ItemStack stack = selectedPreviewStack();
        LivingEntity player = Minecraft.getInstance().player;
        if (stack.isEmpty() || player == null) {
            return;
        }

        int size = previewSize();
        int x = previewX();
        int y = previewY();
        GuiItemMove.renderItemInInventoryFollowsMouse(graphics, x, y, this.previewRotX, this.previewRotY, stack, player, size / 16.0F);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0 && isInPreview(mouseX, mouseY)) {
            ItemStack stack = selectedPreviewStack();
            if (!stack.isEmpty() && this.minecraft != null) {
                captureFieldValues();
                this.minecraft.setScreen(new PlantStatsPreviewScreen(this, stack, selectedName(this.selectedIndex), this.plantIds.get(this.selectedIndex)));
            }
            return true;
        }

        int listTop = listTop();
        int visibleRows = Math.max(1, (this.height - listTop - BOTTOM_HEIGHT - OUTER_MARGIN) / ROW_HEIGHT);
        if (mouseX >= OUTER_MARGIN + 8 && mouseX <= OUTER_MARGIN + leftW() - 8 && mouseY >= listTop && mouseY < listTop + visibleRows * ROW_HEIGHT) {
            int row = ((int) mouseY - listTop) / ROW_HEIGHT;
            int index = this.plantScroll + row;
            if (index >= 0 && index < this.plantIds.size()) {
                captureFieldValues();
                this.selectedIndex = index;
                this.fieldScroll = 0;
                kickPreview();
                refreshWidgets();
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (button == 0 && this.draggingPreview) {
            this.previewVelX = (float) dragX * 0.018F;
            this.previewVelY = (float) dragY * 0.018F;
            this.previewRotX = Mth.clamp(this.previewRotX + (float) dragX * 0.012F, -1.5F, 1.5F);
            this.previewRotY = Mth.clamp(this.previewRotY + (float) dragY * 0.012F, -1.0F, 1.0F);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0 && this.draggingPreview) {
            this.draggingPreview = false;
            return true;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        captureFieldValues();
        if (mouseX < rightX()) {
            int listTop = listTop();
            int visibleRows = Math.max(1, (this.height - listTop - BOTTOM_HEIGHT - OUTER_MARGIN) / ROW_HEIGHT);
            this.plantScroll = Mth.clamp(this.plantScroll - (int) Math.signum(delta), 0, Math.max(0, this.plantIds.size() - visibleRows));
        } else {
            int totalFields = selectedConfig() == null || selectedConfig().values == null ? 0 : selectedConfig().values.size();
            int visibleRows = Math.max(1, (this.height - fieldTop() - BOTTOM_HEIGHT - OUTER_MARGIN) / FIELD_ROW_HEIGHT);
            this.fieldScroll = Mth.clamp(this.fieldScroll - (int) Math.signum(delta), 0, Math.max(0, totalFields - visibleRows));
        }
        refreshWidgets();
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.draggingPreview) {
            this.previewVelX += (0.35F - this.previewRotX) * 0.035F;
            this.previewVelY += (0.12F - this.previewRotY) * 0.035F;
        }
        this.previewRotX += this.previewVelX;
        this.previewRotY += this.previewVelY;
        this.previewVelX *= 0.84F;
        this.previewVelY *= 0.84F;
        this.previewRotX = Mth.clamp(this.previewRotX, -1.5F, 1.5F);
        this.previewRotY = Mth.clamp(this.previewRotY, -1.2F, 1.2F);
    }

    @Override
    public void renderBackground(GuiGraphics graphics) {
        if (this.minecraft != null && this.minecraft.level != null) {
            graphics.fillGradient(0, 0, this.width, this.height, BlurHandler.getBackgroundColor(), BlurHandler.getBackgroundColor());
            net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.ScreenEvent.BackgroundRendered(this, graphics));
        } else {
            this.renderDirtBackground(graphics);
        }
    }

    @Override
    public void onClose() {
        BlurHandler.updateShader(true);
        super.onClose();
    }

    private PlantStatConfig selectedConfig() {
        if (this.plantIds.isEmpty()) {
            return null;
        }
        this.selectedIndex = Mth.clamp(this.selectedIndex, 0, this.plantIds.size() - 1);
        return this.configs.get(this.plantIds.get(this.selectedIndex));
    }

    private String selectedName(int index) {
        PlantStatConfig config = this.configs.get(this.plantIds.get(index));
        if (config == null || config.name == null || config.name.isBlank()) {
            return this.plantIds.get(index);
        }
        return config.name;
    }

    private static String format(double value) {
        if (Math.abs(value - Math.rint(value)) < 1.0E-6D) {
            return String.valueOf((long) Math.rint(value));
        }
        return String.format(Locale.ROOT, "%.3f", value);
    }

    private static String friendlyKey(String key) {
        return switch (key) {
            case PlantStatManager.HEALTH -> "生命值";
            case PlantStatManager.FOLLOW_RANGE -> "索敌范围（格）";
            case PlantStatManager.ATTACK_RANGE -> "攻击范围（格）";
            case PlantStatManager.PROJECTILE_DAMAGE -> "弹丸伤害";
            case PlantStatManager.PROJECTILE_SPEED -> "弹丸速度";
            case PlantStatManager.COOLDOWN_TICKS -> "攻击间隔（tick）";
            case PlantStatManager.FIRST_COOLDOWN_TICKS -> "首次等待（tick）";
            case PlantStatManager.BURST_COUNT -> "连发数量";
            case PlantStatManager.BURST_DELAY_TICKS -> "连发间隔（tick）";
            case "protect_radius" -> "保护范围（格）";
            case "retarget_interval_ticks" -> "转移仇恨间隔（tick）";
            case "sun_interval_ticks" -> "阳光周期（tick）";
            case "sun_spawn_tick" -> "阳光生成点（tick）";
            case "sun_animation_tick" -> "阳光动画点（tick）";
            case "grow_wait_ticks" -> "成长等待（tick）";
            case "grow_animation_ticks" -> "成长动画（tick）";
            case "trigger_radius" -> "触发半径（格）";
            case "explosion_fuse_ticks" -> "爆炸前摇（tick）";
            case "explosion_radius" -> "爆炸半径（格）";
            case "explosion_damage" -> "爆炸伤害";
            case "uppercut_chance_percent" -> "上勾拳概率%";
            case "uppercut_stun_ticks" -> "上勾拳眩晕（tick）";
            case "uppercut_vertical_velocity" -> "上勾拳击飞力度";
            case "beam_length" -> "烟雾长度（格）";
            case "beam_radius" -> "烟雾宽度（格）";
            case "beam_damage" -> "烟雾伤害";
            case "fear_range" -> "胆怯范围（格）";
            case "hide_ticks" -> "缩回时间（tick）";
            case "special_chance_percent" -> "特殊概率%";
            case "special_damage_multiplier" -> "特殊伤害倍数";
            case "snow_duration_ticks" -> "冰冻时长（tick）";
            case "electric_shock_damage" -> "电弧伤害";
            case "electric_shock_range" -> "电弧范围（格）";
            case "electric_shock_chance_percent" -> "感电概率%";
            case "electric_shocked_duration_ticks" -> "感电时长（tick）";
            case "super_chance_percent" -> "大招概率%";
            case "super_duration_ticks" -> "大招时长（tick）";
            case "super_cooldown_ticks" -> "大招冷却（tick）";
            case "super_min_shots" -> "大招最少弹数";
            case "super_extra_shots" -> "大招额外随机";
            case "super_spread_degrees" -> "大招散布（角度）";
            case "super_spread_offset_degrees" -> "大招偏移（角度）";
            case "super_projectile_speed" -> "大招弹速";
            default -> key;
        };
    }

    private int leftW() {
        return Mth.clamp(this.width / 4, 118, 176);
    }

    private int rightX() {
        return OUTER_MARGIN + leftW() + GAP;
    }

    private int rightW() {
        return Math.max(180, this.width - rightX() - OUTER_MARGIN);
    }

    private int listTop() {
        return 56;
    }

    private int fieldTop() {
        return Mth.clamp(previewY() + previewSize() + 16, 104, Math.max(104, this.height - OUTER_MARGIN - BOTTOM_HEIGHT - FIELD_ROW_HEIGHT));
    }

    private int inputWidth() {
        return Mth.clamp(this.rightW() / 5, 58, 88);
    }

    private int previewSize() {
        return Mth.clamp(this.rightW() / 6, 46, 72);
    }

    private int previewX() {
        return rightX() + rightW() - previewSize() - 26;
    }

    private int previewY() {
        return 52;
    }

    private boolean isInPreview(double mouseX, double mouseY) {
        int x = previewX();
        int y = previewY();
        int size = previewSize();
        return mouseX >= x - 8 && mouseX <= x + size + 8 && mouseY >= y - 8 && mouseY <= y + size + 8;
    }

    private void kickPreview() {
        this.previewRotX = 0.45F;
        this.previewRotY = -0.18F;
        this.previewVelX = 0.08F;
        this.previewVelY = 0.05F;
    }

    private ItemStack selectedPreviewStack() {
        if (this.plantIds.isEmpty()) {
            return ItemStack.EMPTY;
        }
        return switch (this.plantIds.get(this.selectedIndex)) {
            case "sun_flower" -> new ItemStack(PVZItems.SUN_FLOWER_CARD.get());
            case "pea_shooter" -> new ItemStack(PVZItems.PEASHOOTER_CARD.get());
            case "snow_pea" -> new ItemStack(PVZItems.SNOWPEA_CARD.get());
            case "repeater" -> new ItemStack(PVZItems.REPEATER_CARD.get());
            case "gatling_pea" -> new ItemStack(PVZItems.GATLING_PEA_CARD.get());
            case "primal_peashooter" -> new ItemStack(PVZItems.PRIMAL_PEASHOOTER_CARD.get());
            case "electric_peashooter" -> new ItemStack(PVZItems.ELECTRIC_PEASHOOTER_CARD.get());
            case "walnut" -> new ItemStack(PVZItems.WALNUT_CARD.get());
            case "tallnut" -> new ItemStack(PVZItems.TALL_NUT_CARD.get());
            case "potato_mine" -> new ItemStack(PVZItems.POTATO_MINE_CARD.get());
            case "bonkchoy" -> new ItemStack(PVZItems.BONKCHOY_CARD.get());
            case "puff_shroom" -> new ItemStack(PVZItems.PUFF_SHROOM_CARD.get());
            case "fume_shroom" -> new ItemStack(PVZItems.FUME_SHROOM_CARD.get());
            case "scaredy_shroom" -> new ItemStack(PVZItems.SCAREDY_SHROOM_CARD.get());
            case "pumpkin" -> new ItemStack(PVZItems.PUMPKIN_CARD.get());
            case "super_gatling_pea" -> new ItemStack(PVZItems.SUPER_GATLING_PEA_CARD.get());
            case "super_snow_gatling_pea" -> new ItemStack(PVZItems.SNOWPEA_CARD.get());
            case "super_primal_gatling_pea" -> new ItemStack(PVZItems.PRIMAL_PEASHOOTER_CARD.get());
            case "super_electric_gatling_pea" -> new ItemStack(PVZItems.ELECTRIC_PEASHOOTER_CARD.get());
            default -> ItemStack.EMPTY;
        };
    }

    private String fit(String text, int maxWidth) {
        return this.font.plainSubstrByWidth(text, Math.max(12, maxWidth));
    }

    private static LinkedHashMap<String, PlantStatConfig> copy(Map<String, PlantStatConfig> source) {
        LinkedHashMap<String, PlantStatConfig> copy = new LinkedHashMap<>();
        for (Map.Entry<String, PlantStatConfig> entry : source.entrySet()) {
            copy.put(entry.getKey(), entry.getValue().copy());
        }
        return copy;
    }
}
