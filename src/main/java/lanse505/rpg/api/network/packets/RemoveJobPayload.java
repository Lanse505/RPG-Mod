package lanse505.rpg.api.network.packets;

import lanse505.rpg.api.job.IJob;
import lanse505.rpg.api.util.RPGUtils;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record RemoveJobPayload(IJob job) implements CustomPacketPayload
{
  public static final CustomPacketPayload.Type<RemoveJobPayload> TYPE = new CustomPacketPayload.Type<>(RPGUtils.create("remove_job"));

  public static final StreamCodec<RegistryFriendlyByteBuf, RemoveJobPayload> STREAM_CODEC = StreamCodec.composite(
          IJob.NETWORK_TYPED_CODEC, RemoveJobPayload::job,
          RemoveJobPayload::new
  );

  @Override
  public Type<? extends CustomPacketPayload> type()
  {
    return TYPE;
  }
}
