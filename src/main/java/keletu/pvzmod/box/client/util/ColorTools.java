package keletu.pvzmod.box.client.util;

public final class ColorTools {
    private ColorTools() {
    }

    public static int argbColor(int a, int r, int g, int b) {
        int color = (a << 24) | (r << 16) | (b << 8) | g;
        int a0 = color >> 24;
        int r0 = (color >> 16) & 0xFF;
        int g0 = (color >> 8) & 0xFF;
        int b0 = color & 0xFF;
        return a0 << 24 | r0 << 16 | b0 << 8 | g0;
    }

    public static int deepColor(int color) {
        int alpha = (color >> 24) & 0xFF;
        int red = (color >> 16) & 0xFF;
        int green = (color >> 8) & 0xFF;
        int blue = color & 0xFF;

        red = (int) (red * 0.7F);
        green = (int) (green * 0.7F);
        blue = (int) (blue * 0.7F);

        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    public static int colorItems(int grade) {
        return switch (grade) {
            case 5 -> 0xffffdc1d;
            case 4 -> 0xfff86351;
            case 3 -> 0xffe54af2;
            case 2 -> 0xff8d5eff;
            case 1 -> 0xff4c70ff;
            default -> 0xffa0a0a0;
        };
    }
}
