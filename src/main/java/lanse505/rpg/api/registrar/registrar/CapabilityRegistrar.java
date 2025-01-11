package lanse505.rpg.api.registrar.registrar;

import lanse505.rpg.api.sheet.ICharacterSheetAccessor;
import lanse505.rpg.api.sheet.CharacterSheetAccessor;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class CapabilityRegistrar
{
  public static void registerCapabilities(RegisterCapabilitiesEvent event)
  {
    event.registerEntity(ICharacterSheetAccessor.CAPABILITY,
            EntityType.PLAYER,
            (entity, context) -> new CharacterSheetAccessor()
    );
  }
}
