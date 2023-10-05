package fall.industrialis.screen;

import net.minecraft.screen.ScreenHandlerType;

public class IScreenHandlers {
    public static ScreenHandlerType<ElectricFurnaceScreenHandler> ELECTRIC_FURNACE_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        ELECTRIC_FURNACE_SCREEN_HANDLER = new ScreenHandlerType<>(ElectricFurnaceScreenHandler::new);
    }
}
