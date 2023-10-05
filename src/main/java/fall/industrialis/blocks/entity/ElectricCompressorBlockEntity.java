package fall.industrialis.blocks.entity;

import fall.industrialis.items.inventory.ImplementedInventory;
import fall.industrialis.mechanisms.ElectricCompressorBlock;
import fall.industrialis.recipe.CompressorRecipes;
import fall.industrialis.screen.ElectricCompressorScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ElectricCompressorBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int cookTime = 0;
    private static int cookTimeTotal = 200;

    public ElectricCompressorBlockEntity(BlockPos pos, BlockState state) {
        super(IBlockEntities.ELECTRIC_COMPRESSOR, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return ElectricCompressorBlockEntity.this.cookTime;
                    case 1: return ElectricCompressorBlockEntity.this.cookTimeTotal;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: ElectricCompressorBlockEntity.this.cookTime = value; break;
                    case 1: ElectricCompressorBlockEntity.this.cookTimeTotal = value; break;
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
        return new ElectricCompressorScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

        nbt.putShort("CookTime", (short)this.cookTime);
        nbt.putShort("CookTimeTotal", (short)this.cookTimeTotal);

        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("compressor.progress", cookTime);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        this.cookTime = nbt.getShort("CookTime");
        this.cookTimeTotal = nbt.getShort("CookTimeTotal");

        Inventories.readNbt(nbt, inventory);
        cookTime = nbt.getInt("compressor.progress");
    }

    private void resetProgress() {
        this.cookTime = 0;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        Direction direction = this.getWorld().getBlockState(this.pos).get(ElectricCompressorBlock.FACING);

        if (side == Direction.UP || side == direction.DOWN) {
            return false;
        }

        return ImplementedInventory.super.canInsert(slot, stack, side);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction side) {
        return ImplementedInventory.super.canExtract(slot, stack, side);
    }

    public static void tick(World world, BlockPos pos, BlockState state, ElectricCompressorBlockEntity blockEntity) {
        if (!world.isClient()) {
            if (hasRecipe(blockEntity)) {
                setCookTimeTotal(blockEntity);
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

    private static void setCookTimeTotal(ElectricCompressorBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<CompressorRecipes> recipe = entity.getWorld().getRecipeManager().getFirstMatch(CompressorRecipes.Type.INSTANCE, inventory, entity.getWorld());
        entity.propertyDelegate.set(1, recipe.get().getCookTime());
    }

    private static void craftItem(ElectricCompressorBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<CompressorRecipes> recipe = entity.getWorld().getRecipeManager().getFirstMatch(CompressorRecipes.Type.INSTANCE, inventory, entity.getWorld());

        if (hasRecipe(entity)) {
            entity.removeStack(0, 1);

            entity.setStack(1, new ItemStack(recipe.get().getOutput().getItem(), entity.getStack(1).getCount() + 1));
            //entity.propertyDelegate.set(1, recipe.get().getCookTime());

            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(ElectricCompressorBlockEntity entity) {
        SimpleInventory inventory = new SimpleInventory(entity.size());
        for (int i = 0; i < entity.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<CompressorRecipes> match = entity.getWorld().getRecipeManager().getFirstMatch(CompressorRecipes.Type.INSTANCE, inventory, entity.getWorld());

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
