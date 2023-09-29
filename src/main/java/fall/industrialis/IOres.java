package fall.industrialis;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class IOres {

    public static final RegistryKey<PlacedFeature> BAUXITE_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE,
            new Identifier(Industrialis.MOD_ID, "bauxite_ore"));

    public static void registerOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, BAUXITE_ORE_PLACED_KEY);
    }
}
