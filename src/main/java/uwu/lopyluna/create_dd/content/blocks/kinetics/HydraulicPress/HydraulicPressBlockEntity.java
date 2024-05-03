package uwu.lopyluna.create_dd.content.blocks.kinetics.HydraulicPress;

import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.kinetics.belt.transport.TransportedItemStack;
import com.simibubi.create.content.kinetics.crafter.MechanicalCraftingRecipe;
import com.simibubi.create.content.kinetics.press.PressingBehaviour;
import com.simibubi.create.content.kinetics.press.PressingRecipe;
import com.simibubi.create.content.processing.basin.BasinBlockEntity;
import com.simibubi.create.content.processing.basin.BasinOperatingBlockEntity;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipe;
import com.simibubi.create.foundation.advancement.AdvancementBehaviour;
import com.simibubi.create.foundation.advancement.AllAdvancements;
import com.simibubi.create.foundation.advancement.CreateAdvancement;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import com.simibubi.create.foundation.item.ItemHelper;
import com.simibubi.create.foundation.item.SmartInventory;
import com.simibubi.create.foundation.recipe.RecipeApplier;
import com.simibubi.create.foundation.utility.Lang;
import com.simibubi.create.foundation.utility.LangBuilder;
import com.simibubi.create.foundation.utility.VecHelper;
import com.simibubi.create.infrastructure.config.AllConfigs;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import uwu.lopyluna.create_dd.DesiresCreate;

import java.util.List;
import java.util.Optional;

public class HydraulicPressBlockEntity extends BasinOperatingBlockEntity implements PressingBehaviour.PressingBehaviourSpecifics {
    private static final Object compressingRecipesKey = new Object();

    public PressingBehaviour pressingBehaviour;
    private int tracksCreated;

    private int cooldown;
    private int fluidNeeded;

    SmartFluidTankBehaviour tank;

    public HydraulicPressBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        addFluidTip(tooltip);

        addCooldownAndFluidNeeded(tooltip);

        return super.addToGoggleTooltip(tooltip, isPlayerSneaking);
    }

    protected void addCooldownAndFluidNeeded(List<Component> tooltip) {
        Lang.text("Cooldown:")
                .space()
                .add(Lang.number(Math.round(cooldown/20))
                        .space()
                        .translate("generic.unit.seconds")
                        .style(ChatFormatting.AQUA))
                .forGoggles(tooltip);

        Lang.text("Fluid Needed:")
                .space()
                .add(Lang.number(fluidNeeded)
                        .space()
                        .translate("generic.unit.millibuckets")
                        .style(ChatFormatting.AQUA))
                .forGoggles(tooltip);
    }

    protected void addFluidTip(List<Component> tooltip) {
        LangBuilder mb = Lang.translate("generic.unit.millibuckets");
        Lang.translate("gui.goggles.fluid_container")
                .forGoggles(tooltip);

        FluidStack fluidStack = tank.getPrimaryHandler().getFluidInTank(0);

        Lang.fluidName(fluidStack)
                .style(ChatFormatting.GRAY)
                .forGoggles(tooltip, 1);

        Lang.builder()
                .add(Lang.number(fluidStack.getAmount())
                        .add(mb)
                        .style(ChatFormatting.GOLD))
                .text(ChatFormatting.GRAY, " / ")
                .add(Lang.number(tank.getPrimaryHandler().getTankCapacity(0))
                        .add(mb)
                        .style(ChatFormatting.DARK_GRAY))
                .forGoggles(tooltip, 1);
    }

    @Override
    protected AABB createRenderBoundingBox() {
        return new AABB(worldPosition).expandTowards(0, -1.5, 0)
                .expandTowards(0, 1, 0);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        super.addBehaviours(behaviours);
        tank = SmartFluidTankBehaviour.single(this, 3000);
        behaviours.add(tank);

        pressingBehaviour = new PressingBehaviour(this);
        behaviours.add(pressingBehaviour);

        registerAwardables(behaviours, AllAdvancements.PRESS, AllAdvancements.COMPACTING,
                AllAdvancements.TRACK_CRAFTING);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY && side.getAxis() == getBlockState().getValue(HydraulicPressBlock.HORIZONTAL_FACING).getClockWise().getAxis() && side.getAxis() != Direction.Axis.Y)
            return tank.getCapability()
                    .cast();
        return super.getCapability(cap, side);
    }

    public void onItemPressed(ItemStack result) {
        award(AllAdvancements.PRESS);
        if (AllTags.AllBlockTags.TRACKS.matches(result))
            tracksCreated += result.getCount();
        if (tracksCreated >= 1000) {
            award(AllAdvancements.TRACK_CRAFTING);
            tracksCreated = 0;
        }
    }

    public PressingBehaviour getPressingBehaviour() {
        return pressingBehaviour;
    }

    @Override
    public boolean tryProcessInBasin(boolean simulate) {
        applyBasinRecipe();

        if (!canProcess())
            return false;

        Optional<BasinBlockEntity> basin = getBasin();
        if (basin.isPresent()) {
            SmartInventory inputs = basin.get()
                    .getInputInventory();
            for (int slot = 0; slot < inputs.getSlots(); slot++) {
                ItemStack stackInSlot = inputs.getItem(slot);
                if (stackInSlot.isEmpty())
                    continue;
                pressingBehaviour.particleItems.add(stackInSlot);
            }
        }

        return true;
    }

    @Override
    protected void write(CompoundTag compound, boolean clientPacket) {
        super.write(compound, clientPacket);
        if (getBehaviour(AdvancementBehaviour.TYPE).isOwnerPresent())
            compound.putInt("TracksCreated", tracksCreated);

        compound.putInt("Cooldown", cooldown);
        compound.putInt("FluidNeeded", fluidNeeded);
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        super.read(compound, clientPacket);
        tracksCreated = compound.getInt("TracksCreated");
        cooldown = compound.getInt("Cooldown");
        fluidNeeded = compound.getInt("FluidNeeded");
    }

    @Override
    public boolean tryProcessOnBelt(TransportedItemStack input, List<ItemStack> outputList, boolean simulate) {
        Optional<PressingRecipe> recipe = getRecipe(input.stack);
        if (!recipe.isPresent())
            return false;
        if (simulate)
            return true;
        if (!canProcess())
            return false;

        pressingBehaviour.particleItems.add(input.stack);
        List<ItemStack> outputs = RecipeApplier.applyRecipeOn(
                canProcessInBulk() ? input.stack : ItemHandlerHelper.copyStackWithSize(input.stack, 1), recipe.get());

        for (ItemStack created : outputs) {
            if (!created.isEmpty()) {
                onItemPressed(created);
                break;
            }
        }

        outputList.addAll(outputs);
        return true;
    }

    @Override
    public boolean tryProcessInWorld(ItemEntity itemEntity, boolean simulate) {
        ItemStack item = itemEntity.getItem();
        Optional<PressingRecipe> recipe = getRecipe(item);
        if (!recipe.isPresent())
            return false;
        if (simulate)
            return true;
        if (!canProcess())
            return false;

        ItemStack itemCreated = ItemStack.EMPTY;
        pressingBehaviour.particleItems.add(item);
        if (canProcessInBulk() || item.getCount() == 1) {
            RecipeApplier.applyRecipeOn(itemEntity, recipe.get());
            itemCreated = itemEntity.getItem()
                    .copy();
        } else {
            for (ItemStack result : RecipeApplier.applyRecipeOn(ItemHandlerHelper.copyStackWithSize(item, 1),
                    recipe.get())) {
                if (itemCreated.isEmpty())
                    itemCreated = result.copy();
                ItemEntity created =
                        new ItemEntity(level, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), result);
                created.setDefaultPickUpDelay();
                created.setDeltaMovement(VecHelper.offsetRandomly(Vec3.ZERO, level.random, .05f));
                level.addFreshEntity(created);
            }
            item.shrink(1);
        }

        if (!itemCreated.isEmpty())
            onItemPressed(itemCreated);
        return true;
    }

    @Override
    public void onPressingCompleted() {
        if (canProcess()) {
            this.tank.getPrimaryHandler().drain(fluidNeeded, IFluidHandler.FluidAction.EXECUTE);

            if (!(fluidNeeded == 3000))
                fluidNeeded += 250;
        }

        if (pressingBehaviour.onBasin() && matchBasinRecipe(currentRecipe)
                && getBasin().filter(BasinBlockEntity::canContinueProcessing)
                .isPresent())
            startProcessingBasin();
        else
            basinChecker.scheduleUpdate();
    }

    private static final RecipeWrapper pressingInv = new RecipeWrapper(new ItemStackHandler(1));

    public Optional<PressingRecipe> getRecipe(ItemStack item) {
        Optional<PressingRecipe> assemblyRecipe =
                SequencedAssemblyRecipe.getRecipe(level, item, AllRecipeTypes.PRESSING.getType(), PressingRecipe.class);
        if (assemblyRecipe.isPresent())
            return assemblyRecipe;

        pressingInv.setItem(0, item);
        return AllRecipeTypes.PRESSING.find(pressingInv, level);
    }

    public static <C extends Container> boolean canCompress(Recipe<C> recipe) {
        if (!(recipe instanceof CraftingRecipe) || !AllConfigs.server().recipes.allowShapedSquareInPress.get())
            return false;
        NonNullList<Ingredient> ingredients = recipe.getIngredients();
        return (ingredients.size() == 4 || ingredients.size() == 9) && ItemHelper.matchAllIngredients(ingredients);
    }

    @Override
    protected <C extends Container> boolean matchStaticFilters(Recipe<C> recipe) {
        return (recipe instanceof CraftingRecipe && !(recipe instanceof MechanicalCraftingRecipe) && canCompress(recipe)
                && !AllRecipeTypes.shouldIgnoreInAutomation(recipe))
                || recipe.getType() == AllRecipeTypes.COMPACTING.getType();
    }

    @Override
    public float getKineticSpeed() {
        return getSpeed();
    }

    @Override
    public boolean canProcessInBulk() {
        return true;
    }

    @Override
    protected Object getRecipeCacheKey() {
        return compressingRecipesKey;
    }

    @Override
    public int getParticleAmount() {
        return 15;
    }

    @Override
    public void startProcessingBasin() {
        if (pressingBehaviour.running && pressingBehaviour.runningTicks <= PressingBehaviour.CYCLE / 2)
            return;
        super.startProcessingBasin();
        pressingBehaviour.start(PressingBehaviour.Mode.BASIN);
    }

    @Override
    protected void onBasinRemoved() {
        pressingBehaviour.particleItems.clear();
        pressingBehaviour.running = false;
        pressingBehaviour.runningTicks = 0;
        sendData();
    }

    @Override
    protected boolean isRunning() {
        return pressingBehaviour.running;
    }

    @Override
    protected Optional<CreateAdvancement> getProcessedRecipeTrigger() {
        return Optional.of(AllAdvancements.COMPACTING);
    }

    protected boolean canProcess() {
        if (tank.isEmpty())
            return false;
        if (tank.getPrimaryHandler().getFluidAmount() < fluidNeeded || !(tank.getPrimaryHandler().getFluid().getFluid() == Fluids.WATER))
            return false;
        return true;
    }

    @Override
    public void tick() {
        if (cooldown > 0)
            cooldown -= 1;

        if (cooldown == 0 && fluidNeeded > 250) {
            fluidNeeded -= 250;
            cooldown = 200;
        }

        super.tick();
    }
}
