package fall.industrialis.blocks;

import fall.industrialis.Industrialis;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;

public class IBlocks {
    public static final Collection<ItemStack> BLOCKS = new ArrayList<>();
    public static final Block BAUXITE_ORE = registerBlock("bauxite_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool()));

    public static final Block ELECTRIC_FURNACE = registerBlock("electric_furnace",
            new Block(FabricBlockSettings.of(Material.STONE).strength(4f).requiresTool()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        BLOCKS.add(block.asItem().getDefaultStack());

        return Registry.register(Registries.BLOCK, new Identifier(Industrialis.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Industrialis.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerBlocks() {
    }
}
