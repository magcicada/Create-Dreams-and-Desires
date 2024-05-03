package uwu.lopyluna.create_dd.infrastructure.utility;

import com.simibubi.create.foundation.utility.AbstractBlockBreakQueue;
import com.simibubi.create.foundation.utility.Iterate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import uwu.lopyluna.create_dd.registry.DesiresTags;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

@SuppressWarnings({"all"})
public class VeinMining {

    public static final Vein NO_VEIN =
            new Vein(Collections.emptyList());

    @Nonnull
    public static Vein findVein(@Nullable BlockGetter reader, BlockPos pos) {
        if (reader == null)
            return NO_VEIN;

        List<BlockPos> ores = new ArrayList<>();
        Set<BlockPos> visited = new HashSet<>();
        List<BlockPos> frontier = new LinkedList<>();

        if (!validateExcavation(reader, pos))
            return NO_VEIN;

        visited.add(pos);
        BlockPos.betweenClosedStream(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))
                .forEach(p -> frontier.add(new BlockPos(p)));

        while (!frontier.isEmpty()) {
            BlockPos currentPos = frontier.remove(0);
            if (!visited.add(currentPos))
                continue;

            BlockState currentState = reader.getBlockState(currentPos);
            if (!isOre(currentState))
                continue;
            ores.add(currentPos);
            BlockPos.betweenClosedStream(currentPos.offset(-1, -1, -1), currentPos.offset(1, 1, 1))
                    .filter(((Predicate<BlockPos>) visited::contains).negate())
                    .forEach(p -> frontier.add(new BlockPos(p)));
        }

        visited.clear();
        visited.addAll(ores);
        frontier.addAll(ores);

        return new Vein(ores);
    }

    private static boolean validateExcavation(BlockGetter reader, BlockPos pos) {
        Set<BlockPos> visited = new HashSet<>();
        List<BlockPos> frontier = new LinkedList<>();
        frontier.add(pos);
        frontier.add(pos.above());
        int posY = pos.getY();

        while (!frontier.isEmpty()) {
            BlockPos currentPos = frontier.remove(0);
            BlockPos belowPos = currentPos.below();

            visited.add(currentPos);
            boolean lowerLayer = currentPos.getY() == posY;

            BlockState currentState = reader.getBlockState(currentPos);
            BlockState belowState = reader.getBlockState(belowPos);

            if (!isOre(currentState))
                continue;
            if (!lowerLayer && !pos.equals(belowPos) && (isOre(belowState)))
                return false;

            for (Direction direction : Iterate.directions) {
                if (direction == Direction.DOWN)
                    continue;
                if (direction == Direction.UP && !lowerLayer)
                    continue;
                BlockPos offset = currentPos.relative(direction);
                if (visited.contains(offset))
                    continue;
                frontier.add(offset);
            }

        }

        return true;
    }

    public static boolean isOre(BlockState state) {
        return state.is(DesiresTags.forgeBlockTag("ores"));
    }
    
    public static class Vein extends AbstractBlockBreakQueue {
        private final List<BlockPos> ores;

        public Vein(List<BlockPos> ores) {
            this.ores = ores;
        }

        @Override
        public void destroyBlocks(Level world, ItemStack toDamage, @Nullable Player playerEntity, BiConsumer<BlockPos, ItemStack> drop) {
            ores.forEach(makeCallbackFor(world, 1 / 2f, toDamage, playerEntity, drop));
        }
    }
}
