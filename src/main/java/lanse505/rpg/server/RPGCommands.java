package lanse505.rpg.server;

import com.mojang.brigadier.CommandDispatcher;
import lanse505.rpg.server.command.JobCommand;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class RPGCommands
{
  public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher)
  {
    dispatcher.register(Commands.literal("rpg")
            .then(JobCommand.register(dispatcher))
    );
  }
}
