package fall.industrialis.armors;

import fall.industrialis.items.IItems;
import io.github.ladysnake.pal.VanillaAbilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class MiningHelmet extends ArmorItem {
    private static final ArmorMaterial MATERIAL = new ArmorMaterial() {
        @Override
        public int getDurability(EquipmentSlot slot) {
            return 37;
        }

        @Override
        public int getProtectionAmount(EquipmentSlot slot) {
            return 2;
        }

        @Override
        public int getEnchantability() {
            return 34;
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
            return "mining";
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

    public MiningHelmet(Settings settings) {
        super(MATERIAL, EquipmentSlot.HEAD, settings);
    }

//    public static boolean allowLight(PlayerEntity player) {
//        ItemStack head = player.getEquippedStack(EquipmentSlot.HEAD);
//
//        if (head.getItem().equals(IItems.MINING_HELMET.asItem())) {
//            return true;
//        }
//
//        return false;
//    }

//    @Override
//    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
//        if (!world.isClient()) {
//            if (entity instanceof PlayerEntity) {
//                PlayerEntity player = (PlayerEntity) entity;
//
//                if (allowLight(player)) {
//                    world.getLightingProvider().addLightSource(player.getBlockPos(), 6);
//                }
//            }
//        }
//
//        super.inventoryTick(stack, world, entity, slot, selected);
//    }
}
