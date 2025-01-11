package lanse505.rpg.server.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lanse505.rpg.server.command.job.AddJob;
import lanse505.rpg.server.command.job.ClearJobs;
import lanse505.rpg.server.command.job.RemoveJob;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class JobCommand implements Command<CommandSourceStack>
{

  public static LiteralArgumentBuilder<CommandSourceStack> register(CommandDispatcher<CommandSourceStack> dispatcher)
  {
    return Commands.literal("job")
            .then(AddJob.registerAdd(dispatcher))
            .then(RemoveJob.registerRemove(dispatcher))
            .then(ClearJobs.registerClear(dispatcher))
            .executes(JobCommand::runHelper);
  }

  private static int runHelper(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
  {

    return 1;
  }


  @Override
  public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
  {
    return 1;
  }
}
