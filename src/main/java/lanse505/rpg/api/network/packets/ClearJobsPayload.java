package lanse505.rpg.api.network.packets;

import lanse505.rpg.api.util.RPGUtils;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record ClearJobsPayload() implements CustomPacketPayload
{
  public static final CustomPacketPayload.Type<ClearJobsPayload> TYPE = new CustomPacketPayload.Type<>(RPGUtils.create("clear_jobs"));

  @Override
  public Type<? extends CustomPacketPayload> type()
  {
    return TYPE;
  }
}
