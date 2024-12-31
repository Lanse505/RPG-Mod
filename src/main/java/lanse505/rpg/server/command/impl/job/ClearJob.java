package lanse505.rpg.server.command.impl.job;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lanse505.rpg.RPG;
import lanse505.rpg.api.sheet.ICharacterSheet;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;

public class ClearJob implements Command<CommandSourceStack>
{
  public static LiteralArgumentBuilder<CommandSourceStack> registerClear(CommandDispatcher<CommandSourceStack> dispatcher)
  {
    return Commands.literal("clear").executes(ClearJob::clearJob);
  }

  private static int clearJob(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
  {
    ServerPlayer target = context.getSource().getPlayerOrException();
    ICharacterSheet sheet = target.getCapability(ICharacterSheet.CAPABILITY);
    if (sheet == null) return error(target, "ICharacterSheer Capability was Null!");
    try
    {
      sheet.clearJobs();
      if (sheet.isDebug()) RPG.LOGGER.info("Debug: Successfully cleared Jobs for Player {}", target.getName().getContents());
    } catch (Exception e)
    {
      return error(target, "Error: Failed to clear jobs for player, this shouldn't happen!");
    }
    return 1;
  }

  private static int error(ServerPlayer player, String reason)
  {
    RPG.LOGGER.error("Failed to clear jobs for player {}, because {}", player, reason);
    return 0;
  }

  @Override
  public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
  {
    return 1;
  }
}
