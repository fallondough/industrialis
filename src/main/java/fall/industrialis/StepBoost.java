package fall.industrialis;

import fall.industrialis.items.IItems;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.EndTick;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public final class StepBoost implements EndTick {
    public static boolean allowStepHeightAddition(PlayerEntity player) {
        ItemStack legs = player.getEquippedStack(EquipmentSlot.LEGS);

        if (legs.getItem().equals(IItems.QUANTUM_LEGGINGS.asItem()) || legs.getItem().equals(IItems.SINGULARITY_LEGGINGS)) {
            return true;
        }

        return false;
    }

    @Override
    public void onEndTick(MinecraftClient client) {
        ClientPlayerEntity player;
        player = client.player;

        if (player == null) return;

        if (allowStepHeightAddition(player)) {
            player.stepHeight = 1f;
        } else {
            player.stepHeight = .6f;
        }
    }
}
