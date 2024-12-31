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
import net.minecraft.commands.arguments.ResourceKeyArgument;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;

public class AddJob implements Command<CommandSourceStack>
{

  public static LiteralArgumentBuilder<CommandSourceStack> registerAdd(CommandDispatcher<CommandSourceStack> dispatcher)
  {
    return Commands.literal("add")
            .then(Commands.argument("job", ResourceKeyArgument.key(RPGRegistries.JOBS))
                    .suggests((ctx, builder) -> RPGCommandUtils.getRegistrySuggestions(ctx, builder, RPGRegistries.JOBS))
                    .executes(AddJob::addJob));
  }

  private static int addJob(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
  {
    ServerPlayer target = context.getSource().getPlayerOrException();
    ICharacterSheet sheet = target.getCapability(ICharacterSheet.CAPABILITY);
    if (sheet == null) return error(context.getArgument("job", ResourceKey.class), target, "ICharacterSheer Capability was Null!");
    ResourceKey<IJob> key = context.getArgument("job", ResourceKey.class);
    try {
      Holder.Reference<IJob> job = target.level().registryAccess().lookupOrThrow(RPGRegistries.JOBS).getOrThrow(key);
      boolean success = sheet.addJob(job.value());
      if (success) target.sendSystemMessage(Component.literal(String.format("Successfully Added Job[%s] to Player[%s]", key.location(), target.getName().getContents())), true);
      return success ? 1 : 0;
    } catch (Exception e)
    {
      return error(key, target, "Error: Failed to apply job to player, this shouldn't happen!");
    }
  }

  private static int error(ResourceKey<?> job, ServerPlayer player, String reason)
  {
    RPG.LOGGER.error("Failed to add Job {}, to player {}, because {}", job, player, reason);
    return 0;
  }

  @Override
  public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
  {
    return 1;
  }

}
