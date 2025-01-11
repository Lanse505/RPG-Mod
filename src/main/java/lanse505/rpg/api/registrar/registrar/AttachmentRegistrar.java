package lanse505.rpg.api.registrar.registrar;

import lanse505.rpg.api.sheet.CharacterSheetData;
import lanse505.rpg.api.util.RPGConstants;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class AttachmentRegistrar
{
  private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, RPGConstants.MODID);

  public static final Supplier<AttachmentType<CharacterSheetData>> CHARACTER_SHEET = ATTACHMENT_TYPES.register(
    "character_sheet", () -> AttachmentType.builder(CharacterSheetData::new).serialize(CharacterSheetData.CODEC).build()
  );

  public static void registerAttachmentTypes(IEventBus bus)
  {
    ATTACHMENT_TYPES.register(bus);
  }
}
