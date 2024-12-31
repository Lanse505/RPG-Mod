package lanse505.rpg.common.util;

import lanse505.rpg.api.sheet.ICharacterSheet;
import lanse505.rpg.common.sheet.CharacterSheet;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

public class RPGCapabilities
{

  public static void registerCapabilities(RegisterCapabilitiesEvent event)
  {
    event.registerEntity(
            ICharacterSheet.CAPABILITY,
            EntityType.PLAYER,
            (entity, context) -> new CharacterSheet(true)
    );
  }
}
