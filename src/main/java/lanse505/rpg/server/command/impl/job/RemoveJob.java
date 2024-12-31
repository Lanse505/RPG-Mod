package lanse505.rpg.server.command.impl.job;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lanse505.rpg.RPG;
import lanse505.rpg.api.RPGRegistries;
import lanse505.rpg.api.sheet.ICharacterSheet;
import lanse505.rpg.api.sheet.job.IJob;
import lanse505.rpg.server.command.RPGCommandUtils;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceArgument;
import net.minecraft.commands.arguments.ResourceKeyArgument;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;

public class RemoveJob implements Command<CommandSourceStack>
{

  public static LiteralArgumentBuilder<CommandSourceStack> registerRemove(CommandDispatcher<CommandSourceStack> dispatcher)
  {
    return Commands.literal("remove")
            .then(Commands.argument("job", ResourceKeyArgument.key(RPGRegistries.JOBS))
                    .suggests((ctx, builder) -> RPGCommandUtils.getRegistrySuggestions(ctx, builder, RPGRegistries.JOBS))
                    .executes(RemoveJob::removeJob));
  }

  private static int removeJob(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
  {
    ServerPlayer target = context.getSource().getPlayerOrException();
    ICharacterSheet sheet = target.getCapability(ICharacterSheet.CAPABILITY);
    if (sheet == null) return error(context.getArgument("job", ResourceKey.class), target, "ICharacterSheer Capability was Null!");
    try
    {
      ResourceKey<IJob> key = context.getArgument("job", ResourceKey.class);
      Holder.Reference<IJob> job = target.level().registryAccess().lookupOrThrow(RPGRegistries.JOBS).getOrThrow(key);
      sheet.removeJob(job.value());
      if (sheet.isDebug()) RPG.LOGGER.info("Debug: Successfully removed Job {} to Player {}", job.key(), target.getName().getContents());
    } catch (Exception e)
    {
      return error(context.getArgument("job", ResourceKey.class), target, "Error: Failed to remove job from player, this shouldn't happen!");
    }
    return 1;
  }

  private static int error(ResourceKey<?> job, ServerPlayer player, String reason)
  {
    RPG.LOGGER.error("Failed to remove Job {}, to player {}, because {}", job, player, reason);
    return 0;
  }

  @Override
  public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
  {
    return 1;
  }
}
