package fall.industrialis.armors.quantum;

import com.google.common.collect.Multimap;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SingularityArmorItem extends ArmorItem {
    public static final List<SingularityArmorItem> ITEMS = new ArrayList<>();

    private static final ArmorMaterial MATERIAL = new ArmorMaterial() {
        @Override
        public int getDurability(EquipmentSlot slot) {
            return 0;
        }

        @Override
        public int getProtectionAmount(EquipmentSlot slot) {
            return 0;
        }

        @Override
        public int getEnchantability() {
            return 0;
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
            return "singularity";
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

    public SingularityArmorItem(EquipmentSlot slot, Settings settings) {
        super(MATERIAL, slot, settings.rarity(Rarity.EPIC).fireproof());
        ITEMS.add(this);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("item.modifiers." + getSlotType().getName()).formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("attribute.modifier.plus.0","¹⁄₄ of ∞", Text.translatable("attribute.name.generic.armor")).formatted(Formatting.BLUE));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return super.getAttributeModifiers(EquipmentSlot.OFFHAND);
    }
 }
