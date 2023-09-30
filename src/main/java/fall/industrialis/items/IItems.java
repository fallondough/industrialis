package fall.industrialis.items;

import fall.industrialis.Industrialis;
import fall.industrialis.armors.quantum.QuantumArmorItem;
import fall.industrialis.armors.quantum.SingularityArmorItem;
import fall.industrialis.tools.SingularityPickaxe;
import fall.industrialis.tools.SingularitySword;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collection;

public class IItems {
    public static final Collection<ItemStack> ITEMS = new ArrayList<>();

    //items
    public static final Item RESIN = registerItem("resin",
            new Item(new FabricItemSettings()));
    public static final Item TIN_INGOT = registerItem("tin_ingot",
            new Item(new FabricItemSettings()));
    public static final Item TIN_DUST = registerItem("tin_dust",
            new Item(new FabricItemSettings()));
    public static final Item TIN_PLATE = registerItem("tin_plate",
            new Item(new FabricItemSettings()));
    public static final Item ALUMINIUM_INGOT = registerItem("aluminium_ingot",
            new Item(new FabricItemSettings()));
    public static final Item ALUMINIUM_DUST = registerItem("aluminium_dust",
            new Item(new FabricItemSettings()));
    public static final Item ALUMINIUM_PLATE = registerItem("aluminium_plate",
            new Item(new FabricItemSettings()));
    public static final Item RAW_BAUXITE = registerItem("raw_bauxite",
            new Item(new FabricItemSettings()));
    public static final Item BAUXITE_DUST = registerItem("bauxite_dust",
            new Item(new FabricItemSettings()));
    public static final Item ELECTRUM_INGOT = registerItem("electrum_ingot",
            new Item(new FabricItemSettings()));
    public static final Item ELECTRUM_DUST = registerItem("electrum_dust",
            new Item(new FabricItemSettings()));
    public static final Item ELECTRUM_PLATE = registerItem("electrum_plate",
            new Item(new FabricItemSettings()));

    //armours
    public static final Item QUANTUM_HELMET = registerItem("quantum_helmet",
            new QuantumArmorItem(EquipmentSlot.HEAD, new FabricItemSettings()));
    public static final Item QUANTUM_CHESTPLATE = registerItem("quantum_chestplate",
            new QuantumArmorItem(EquipmentSlot.CHEST, new FabricItemSettings()));
    public static final Item QUANTUM_LEGGINGS = registerItem("quantum_leggings",
            new QuantumArmorItem(EquipmentSlot.LEGS, new FabricItemSettings()));
    public static final Item QUANTUM_BOOTS = registerItem("quantum_boots",
            new QuantumArmorItem(EquipmentSlot.FEET, new FabricItemSettings()));

    public static final Item SINGULARITY_HELMET = registerItem("singularity_helmet",
            new SingularityArmorItem(EquipmentSlot.HEAD, new FabricItemSettings()));
    public static final Item SINGULARITY_CHESTPLATE = registerItem("singularity_chestplate",
            new SingularityArmorItem(EquipmentSlot.CHEST, new FabricItemSettings()));
    public static final Item SINGULARITY_LEGGINGS = registerItem("singularity_leggings",
            new SingularityArmorItem(EquipmentSlot.LEGS, new FabricItemSettings()));
    public static final Item SINGULARITY_BOOTS = registerItem("singularity_boots",
            new SingularityArmorItem(EquipmentSlot.FEET, new FabricItemSettings()));
    public static final Item SINGULARITY_SWORD = registerItem("singularity_sword",
            new SingularitySword(new FabricItemSettings()));
    public static final Item SINGULARITY_PICKAXE = registerItem("singularity_pickaxe",
            new SingularityPickaxe(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        ITEMS.add(item.getDefaultStack());
        return Registry.register(Registries.ITEM, new Identifier(Industrialis.MOD_ID, name), item);
    }

    public static void registerItems() {
    }
}
