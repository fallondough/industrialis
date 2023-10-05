package fall.industrialis.recipe;

import fall.industrialis.Industrialis;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class IRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Industrialis.MOD_ID, CompressorRecipes.Serializer.ID), CompressorRecipes.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(Industrialis.MOD_ID, CompressorRecipes.Type.ID), CompressorRecipes.Type.INSTANCE);
    }
}
