package fall.industrialis.items;

import fall.industrialis.Industrialis;
import fall.industrialis.blocks.IBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class IItemGroup {
    public static final ItemGroup INDUSTRIALIS = FabricItemGroup.builder(new Identifier(Industrialis.MOD_ID, "item_group"))
            .icon(() -> new ItemStack(IItems.QUANTUM_CHESTPLATE))
            .displayName(Text.translatable("itemGroup.industrialis.item_group"))
            .build();

    public static void registerItemGroup() {
        ItemGroupEvents.modifyEntriesEvent(IItemGroup.INDUSTRIALIS).register(entries -> {
            entries.addAll(IItems.ITEMS);
            entries.addAll(IBlocks.BLOCKS);
        });
    }
}
