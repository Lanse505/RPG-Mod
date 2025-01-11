package lanse505.rpg.api.network.packets;

import lanse505.rpg.api.sheet.CharacterSheetData;
import lanse505.rpg.api.util.RPGUtils;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record SyncSheetDataPayload(CharacterSheetData data) implements CustomPacketPayload
{
  public static final CustomPacketPayload.Type<SyncSheetDataPayload> TYPE = new CustomPacketPayload.Type<>(RPGUtils.create("sync_sheet_data"));

  public static final StreamCodec<RegistryFriendlyByteBuf, SyncSheetDataPayload> STREAM_CODEC = StreamCodec.composite(
    CharacterSheetData.STREAM_CODEC, SyncSheetDataPayload::data,
    SyncSheetDataPayload::new
  );

  @Override
  public Type<? extends CustomPacketPayload> type()
  {
    return TYPE;
  }
}
