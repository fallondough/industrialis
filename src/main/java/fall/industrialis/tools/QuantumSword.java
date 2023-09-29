package fall.industrialis.tools;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class QuantumSword extends Item {
    public QuantumSword(FabricItemSettings settings) {
        super(settings.rarity(Rarity.RARE).fireproof());
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.damage(DamageSource.player((PlayerEntity) attacker), 14);

        return super.postHit(stack, target, attacker);
    }
}
