package uwu.lopyluna.create_dd.registry;

import com.mojang.math.Vector3f;
import com.simibubi.create.content.kinetics.fan.processing.AllFanProcessingTypes;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingType;
import com.simibubi.create.content.kinetics.fan.processing.FanProcessingTypeRegistry;
import com.simibubi.create.foundation.recipe.RecipeApplier;
import com.simibubi.create.foundation.utility.Color;
import com.simibubi.create.foundation.utility.VecHelper;
import it.unimi.dsi.fastutil.objects.Object2ReferenceOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import uwu.lopyluna.create_dd.DesiresCreate;
import uwu.lopyluna.create_dd.content.recipes.FreezingRecipe;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DesireFanProcessingTypes extends AllFanProcessingTypes {
    public static final HauntingType SANDING = register("sanding", new HauntingType());
    public static final HauntingType FREEZING = register("freezing", new HauntingType());
    public static final HauntingType SEETHING = register("seething", new HauntingType());

    private static final Map<String, FanProcessingType> LEGACY_NAME_MAP;
    static {
        Object2ReferenceOpenHashMap<String, FanProcessingType> map = new Object2ReferenceOpenHashMap<>();
        map.put("SANDING", SANDING);
        map.put("FREEZING", FREEZING);
        map.put("SEETHING", SEETHING);
        map.trim();
        LEGACY_NAME_MAP = map;
    }

    private static <T extends FanProcessingType> T register(String id, T type) {
        FanProcessingTypeRegistry.register(DesiresCreate.asResource(id), type);
        return type;
    }

    @Nullable
    public static FanProcessingType ofLegacyName(String name) {
        return LEGACY_NAME_MAP.get(name);
    }

    public static void register() {
    }

    public static FanProcessingType parseLegacy(String str) {
        FanProcessingType type = ofLegacyName(str);
        if (type != null) {
            return type;
        }
        return FanProcessingType.parse(str);
    }

    public static class FreezingType implements FanProcessingType {
        private static final FreezingRecipe.FreezingWrapper FREEZING_WRAPPER = new FreezingRecipe.FreezingWrapper();

        @Override
        public boolean isValidAt(Level level, BlockPos pos) {
            FluidState fluidState = level.getFluidState(pos);
            if (DesiresTags.AllFluidTags.FAN_PROCESSING_CATALYSTS_FREEZING.matches(fluidState)) {
                return true;
            }
            BlockState blockState = level.getBlockState(pos);
            if (DesiresTags.AllBlockTags.FAN_PROCESSING_CATALYSTS_FREEZING.matches(blockState)) {
                return true;
            }
            return false;
        }

        @Override
        public int getPriority() {
            return 600;
        }

        @Override
        public boolean canProcess(ItemStack stack, Level level) {
            FREEZING_WRAPPER.setItem(0, stack);
            Optional<FreezingRecipe> recipe = DesiresRecipeTypes.FREEZING.find(FREEZING_WRAPPER, level);
            return recipe.isPresent();
        }

        @Override
        @Nullable
        public List<ItemStack> process(ItemStack stack, Level level) {
            FREEZING_WRAPPER.setItem(0, stack);
            Optional<FreezingRecipe> recipe = DesiresRecipeTypes.FREEZING.find(FREEZING_WRAPPER, level);
            if (recipe.isPresent())
                return RecipeApplier.applyRecipeOn(stack, recipe.get());
            return null;
        }

        @Override
        public void spawnProcessingParticles(Level level, Vec3 pos) {
            if (level.random.nextInt(8) != 0)
                return;
            Vector3f color = new Color(0xDDE8FF).asVectorF();
            level.addParticle(new DustParticleOptions(color, 1), pos.x + (level.random.nextFloat() - .5f) * .5f,
                    pos.y + .5f, pos.z + (level.random.nextFloat() - .5f) * .5f, 0, 1 / 8f, 0);
            level.addParticle(ParticleTypes.SNOWFLAKE, pos.x + (level.random.nextFloat() - .5f) * .5f, pos.y + .5f,
                    pos.z + (level.random.nextFloat() - .5f) * .5f, 0, 1 / 8f, 0);
        }

        @Override
        public void morphAirFlow(AirFlowParticleAccess particleAccess, RandomSource random) {
            particleAccess.setColor(Color.mixColors(0xEEEEFF, 0xDDE8FF, random.nextFloat()));
            particleAccess.setAlpha(1f);
            if (random.nextFloat() < 1 / 128f)
                particleAccess.spawnExtraParticle(ParticleTypes.SNOWFLAKE, .125f);
            if (random.nextFloat() < 1 / 32f)
                particleAccess.spawnExtraParticle(ParticleTypes.POOF, .125f);
        }

        @Override
        public void affectEntity(Entity entity, Level level) {
            if (level.isClientSide) {

                if (entity instanceof Skeleton) {
                    Vec3 p = entity.getPosition(0);
                    Vec3 v = p.add(0, 0.5f, 0)
                            .add(VecHelper.offsetRandomly(Vec3.ZERO, level.random, 1)
                                    .multiply(1, 0.2f, 1)
                                    .normalize()
                                    .scale(1f));
                    level.addParticle(ParticleTypes.SNOWFLAKE, v.x, v.y, v.z, 0, 0.1f, 0);
                    if (level.random.nextInt(3) == 0)
                        level.addParticle(ParticleTypes.SNOWFLAKE, p.x, p.y + .5f, p.z,
                                (level.random.nextFloat() - .5f) * .5f, 0.1f, (level.random.nextFloat() - .5f) * .5f);
                }
                return;
            }

            if (entity instanceof EnderMan || entity.getType() == EntityType.BLAZE) {
                entity.hurt(DamageSource.FREEZE, 8);
            }

            if (entity instanceof LivingEntity livingEntity) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 7, false, false));
            }

            if (entity instanceof SnowGolem snowgolem) {
                snowgolem.heal(4);
            }

            if (entity instanceof Stray stray) {
                stray.heal(2);
            }

            if (entity.isOnFire()) {
                entity.clearFire();
                level.playSound(null, entity.blockPosition(), SoundEvents.GENERIC_EXTINGUISH_FIRE,
                        SoundSource.NEUTRAL, 0.7F, 1.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.4F);
            }

            if (entity instanceof Skeleton skeleton) {
                int progress = skeleton.getPersistentData()
                        .getInt("CreateFreezing");
                if (progress < 50) {
                    if (progress % 10 == 0) {
                        level.playSound(null, entity.blockPosition(), SoundEvents.STRAY_AMBIENT, SoundSource.NEUTRAL,
                                1f, 1.5f * progress / 50f);
                    }
                    skeleton.getPersistentData()
                            .putInt("CreateFreezing", progress + 1);
                    return;
                }

                level.playSound(null, entity.blockPosition(), SoundEvents.SKELETON_CONVERTED_TO_STRAY,
                        SoundSource.NEUTRAL, 1.25f, 0.65f);

                Stray stray = EntityType.STRAY.create(level);
                CompoundTag serializeNBT = skeleton.saveWithoutId(new CompoundTag());
                serializeNBT.remove("UUID");

                assert stray != null;
                stray.deserializeNBT(serializeNBT);
                stray.setPos(skeleton.getPosition(0));
                level.addFreshEntity(stray);
                skeleton.discard();
            }
        }
    }

}
