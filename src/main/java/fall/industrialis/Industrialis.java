package fall.industrialis;

import fall.industrialis.armors.ArmorEffects;
import fall.industrialis.blocks.IBlocks;
import fall.industrialis.blocks.IOres;
import fall.industrialis.blocks.entity.IBlockEntities;
import fall.industrialis.items.IItemGroup;
import fall.industrialis.items.IItems;
import fall.industrialis.recipe.IRecipes;
import fall.industrialis.screen.IScreenHandlers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class Industrialis implements ModInitializer {
    public static final String MOD_ID = "industrialis";

    @Override
    public void onInitialize() {
        IItems.registerItems();
        IBlocks.registerBlocks();
        IBlockEntities.registerBlockEntities();
        IOres.registerOres();
        IItemGroup.registerItemGroup();
        IScreenHandlers.registerAllScreenHandlers();
        IRecipes.registerRecipes();

        ArmorEffects.init();

        StepBoost stepBoost = new StepBoost();
        ClientTickEvents.END_CLIENT_TICK.register(stepBoost);
    }
}