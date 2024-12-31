package lanse505.rpg.server;


import lanse505.rpg.server.command.RPGCommands;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class RPGServer
{

  public static void registerServerEvents(IEventBus bus)
  {
    bus.addListener(RPGServer::registerCommands);
  }

  private static void registerCommands(RegisterCommandsEvent event)
  {
    RPGCommands.registerCommands(event.getDispatcher());
  }

}
