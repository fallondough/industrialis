package fall.industrialis.blocks.entity;

import fall.industrialis.Industrialis;
import fall.industrialis.blocks.IBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class IBlockEntities {
    public static BlockEntityType<ElectricFurnaceBlockEntity> ELECTRIC_FURNACE;

    public static void registerBlockEntities() {
        ELECTRIC_FURNACE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Industrialis.MOD_ID, "electric_furnace"),
                FabricBlockEntityTypeBuilder.create(ElectricFurnaceBlockEntity::new,
                        IBlocks.ELECTRIC_FURNACE).build(null));
    }
}
