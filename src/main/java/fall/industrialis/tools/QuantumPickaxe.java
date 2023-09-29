package fall.industrialis.tools;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Rarity;

public class QuantumPickaxe extends PickaxeItem {
    private static final ToolMaterial MATERIAL = new ToolMaterial() {
        @Override
        public int getDurability() {
            return 0;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 3;
        }

        @Override
        public float getAttackDamage() {
            return 0;
        }

        @Override
        public int getMiningLevel() {
            return 25565;
        }

        @Override
        public int getEnchantability() {
            return 25565;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }
    };

    public QuantumPickaxe(FabricItemSettings settings) {
        super(MATERIAL, 9, 0f, settings.rarity(Rarity.RARE).fireproof());
        getDefaultStack().addEnchantment(Enchantments.FORTUNE, 4);
    }
}
