package fall.industrialis.armors.quantum;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Wearable;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.List;

public class QuantumArmorItem extends ArmorItem {
    public static final List<QuantumArmorItem> ITEMS = new ArrayList<>();

    private static final ArmorMaterial MATERIAL = new ArmorMaterial() {
        @Override
        public int getDurability(EquipmentSlot slot) {
            return 3671;
        }

        @Override
        public int getProtectionAmount(EquipmentSlot slot) {
            return 10;
        }

        @Override
        public int getEnchantability() {
            return 64;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }

        @Override
        public String getName() {
            return "quantum";
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }

        @Override
        public String toString() {
            return getName().replace("/", ":");
        }
    };

    public QuantumArmorItem(EquipmentSlot slot, Settings settings) {
        super(MATERIAL, slot, settings.rarity(Rarity.RARE).fireproof());
        ITEMS.add(this);
    }
}
