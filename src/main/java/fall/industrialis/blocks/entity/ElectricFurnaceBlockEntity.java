package fall.industrialis.blocks.entity;

import com.mojang.serialization.Decoder;
import fall.industrialis.items.IItems;
import fall.industrialis.items.inventory.ImplementedInventory;
import fall.industrialis.mechanisms.ElectricFurnaceBlock;
import fall.industrialis.recipe.ElectricFurnaceRecipes;
import fall.industrialis.screen.ElectricFurnaceScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ElectricFurnaceBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int cookTime = 0;
    private static int cookTimeTotal = 200;

    public ElectricFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(IBlockEntities.ELECTRIC_FURNACE, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return ElectricFurnaceBlockEntity.this.cookTime;
                    case 1: return ElectricFurnaceBlockEntity.this.cookTimeTotal;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: ElectricFurnaceBlockEntity.this.cookTime = value; break;
                    case 1: ElectricFurnaceBlockEntity.this.cookTimeTotal = value; break;
                }
            }

            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.furnace");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new ElectricFurnaceScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("electric_furnace.progress", cookTime);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        cookTime = nbt.getInt("electric_furnace.progress");
    }

    private void resetProgress() {
        this.cookTime = 0;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        Direction direction = this.getWorld().getBlockState(this.pos).get(ElectricFurnaceBlock.FACING);

        if (side == Direction.UP || side == direction.DOWN) {
            return false;
        }

        return ImplementedInventory.super.canInsert(slot, stack, side);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return ImplementedInventory.super.canExtract(slot, stack, side);
    }

    public static void tick(World world, BlockPos pos, BlockState state, ElectricFurnaceBlockEntity blockEntity) {
        if (!world.isClient()) {
            if (hasRecipe(blockEntity)) {
                blockEntity.cookTime++;
                markDirty(world, pos, state);
                if (blockEntity.cookTime >= blockEntity.cookTimeTotal) {
                    craftItem(blockEntity);
                }
            } else {
                blockEntity.resetProgress();
                markDirty(world, pos, state);
            }
        }
    }

    private static void craftItem(ElectricFurnaceBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<ElectricFurnaceRecipes> recipe = entity.getWorld().getRecipeManager().getFirstMatch(ElectricFurnaceRecipes.Type.INSTANCE, inventory, entity.getWorld());

        if (hasRecipe(entity)) {
            entity.removeStack(0, 1);

            entity.setStack(1, new ItemStack(recipe.get().getOutput().getItem(), entity.getStack(1).getCount() + 1));

            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(ElectricFurnaceBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<ElectricFurnaceRecipes> match = entity.getWorld().getRecipeManager().getFirstMatch(ElectricFurnaceRecipes.Type.INSTANCE, inventory, entity.getWorld());

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory) &&
                canInsertItemIntoOutputSlot(inventory, match.get().getOutput().getItem());
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, Item output) {
        return inventory.getStack(1).getItem() == output || inventory.getStack(1).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(1).getMaxCount() > inventory.getStack(1).getCount();
    }
}
