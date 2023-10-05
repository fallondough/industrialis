package fall.industrialis.screen;

import fall.industrialis.Industrialis;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class IScreenHandlers {
    public static ScreenHandlerType<ElectricFurnaceScreenHandler> ELECTRIC_FURNACE_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        ELECTRIC_FURNACE_SCREEN_HANDLER = new ScreenHandlerType<>(ElectricFurnaceScreenHandler::new);
    }
}
