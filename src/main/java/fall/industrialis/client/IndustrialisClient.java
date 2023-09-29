package fall.industrialis.client;

import fall.industrialis.screen.IScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.FurnaceScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class IndustrialisClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(IScreenHandlers.ELECTRIC_FURNACE_SCREEN_HANDLER, FurnaceScreen::new);
    }
}
