package lanse505.rpg.api.registrar;

import lanse505.rpg.api.registrar.registrar.*;
import lanse505.rpg.api.registrar.registries.RPGRegistries;
import net.neoforged.bus.api.IEventBus;

public class RPGRegistrar
{

  public static void register(IEventBus bus)
  {
    // Registrars
    bus.addListener(RPGRegistries::registerRegistries);
    bus.addListener(RPGRegistries::registerDatapackRegistries);
    bus.addListener(CapabilityRegistrar::registerCapabilities);
    bus.addListener(NetworkRegistrar::registerNetworkPayloads);

    // Deferred Registrars
    AttachmentRegistrar.registerAttachmentTypes(bus);
    JobTypeRegistrar.registerJobTypes(bus);
  }
}
