package fall.industrialis.armors;

import fall.industrialis.Industrialis;
import fall.industrialis.armors.quantum.QuantumArmorItem;
import fall.industrialis.armors.quantum.SingularityArmorItem;
import fall.industrialis.items.IItems;
import io.github.ladysnake.pal.AbilitySource;
import io.github.ladysnake.pal.Pal;
import io.github.ladysnake.pal.VanillaAbilities;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.event.GameEvent;

import java.util.concurrent.ThreadLocalRandom;

public class ArmorEffects {
    private ArmorEffects() {}

    public static final AbilitySource FLIGHT = Pal.getAbilitySource(Industrialis.MOD_ID, "quantum_flight");

    public static boolean singularityImmunity(LivingEntity entity) {
        int parts = 0;

        for (SingularityArmorItem item : SingularityArmorItem.ITEMS) {
            if (entity.getEquippedStack(item.getSlotType()).getItem().equals(item)) {
                parts++;
            }
        }

        return ThreadLocalRandom.current().nextDouble() < parts / 4d;
    }

    public static boolean allowFlight(PlayerEntity player) {
        ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);

        if (chest.getItem().equals(IItems.QUANTUM_CHESTPLATE.asItem()) || chest.getItem().equals(IItems.SINGULARITY_CHESTPLATE.asItem())) {
            return true;
        }

        return false;
    }

    public static boolean handleFallDamage(ItemStack boots) {
        return boots.getItem().equals(IItems.QUANTUM_BOOTS.asItem()) || boots.getItem().equals(IItems.SINGULARITY_BOOTS.asItem());
    }

    public static void init() {
        ServerTickEvents.START_WORLD_TICK.register(world -> {
            for (var player : world.getPlayers()) {
                if (allowFlight(player)) {
                    player.heal(.5f);
                    player.getHungerManager().setExhaustion(0f);
                    FLIGHT.grantTo(player, VanillaAbilities.ALLOW_FLYING);
                } else {
                    FLIGHT.revokeFrom(player, VanillaAbilities.ALLOW_FLYING);
                }
            }
        });

        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            if (singularityImmunity(entity)) {
                return false;
            }

            ItemStack handlingStack = null;
            EquipmentSlot slot = null;

            if (source.equals(DamageSource.FALL)) {
                slot = EquipmentSlot.FEET;
                ItemStack boots = entity.getEquippedStack(slot);

                if (handleFallDamage(boots)) {
                    handlingStack = boots;
                }
            }

            if (handlingStack != null) {
                int calculatedAmount = (int) Math.ceil(amount);
                handlingStack.damage(calculatedAmount, entity, p -> entity.emitGameEvent(GameEvent.ENTITY_DAMAGE));

                return false;
            }

            return true;
        });
    }
}
