package fall.industrialis.tools;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SingularitySword extends Item {
    public SingularitySword(FabricItemSettings settings) {
        super(settings.rarity(Rarity.EPIC).fireproof().maxCount(1));
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.kill();

        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("item.modifiers.mainhand").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(" ∞ ").append(Text.translatable("attribute.name.generic.attack_damage")).formatted(Formatting.DARK_GREEN));

        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public boolean allowContinuingBlockBreaking(PlayerEntity player, ItemStack oldStack, ItemStack newStack) {
        return false;
    }
}
