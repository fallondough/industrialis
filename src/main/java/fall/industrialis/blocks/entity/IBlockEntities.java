package fall.industrialis.blocks.entity;

import fall.industrialis.Industrialis;
import fall.industrialis.blocks.IBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class IBlockEntities {
    //public static BlockEntityType<ElectricCompressorBlockEntity> ELECTRIC_FURNACE;
    public static BlockEntityType<ElectricCompressorBlockEntity> ELECTRIC_COMPRESSOR;

    public static void registerBlockEntities() {
        ELECTRIC_COMPRESSOR = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Industrialis.MOD_ID, "electric_compressor"),
                FabricBlockEntityTypeBuilder.create(ElectricCompressorBlockEntity::new,
                        IBlocks.ELECTRIC_COMPRESSOR).build(null));
    }
}
