package fall.industrialis;

import fall.industrialis.armors.ArmorEffects;
import fall.industrialis.blocks.IBlocks;
import fall.industrialis.items.IItemGroup;
import fall.industrialis.items.IItems;
import fall.industrialis.screen.IScreenHandlers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class Industrialis implements ModInitializer {
    public static final String MOD_ID = "industrialis";

    @Override
    public void onInitialize() {
        IItems.registerItems();
        IBlocks.registerBlocks();
        IOres.registerOres();
        IItemGroup.registerItemGroup();
        IScreenHandlers.registerAllScreenHandlers();

        ArmorEffects.init();

        StepBoost stepBoost = new StepBoost();
        ClientTickEvents.END_CLIENT_TICK.register(stepBoost);
    }
}