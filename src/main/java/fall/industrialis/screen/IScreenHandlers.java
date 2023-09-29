package fall.industrialis.screen;

import net.minecraft.screen.FurnaceScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

public class IScreenHandlers {
    public static ScreenHandlerType<FurnaceScreenHandler> ELECTRIC_FURNACE_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        ELECTRIC_FURNACE_SCREEN_HANDLER = new ScreenHandlerType<>(FurnaceScreenHandler::new);
    }
}
