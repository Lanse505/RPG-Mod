package lanse505.rpg.api.network.packets;

import lanse505.rpg.api.job.IJob;
import lanse505.rpg.api.util.RPGUtils;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record AddJobPayload(IJob job) implements CustomPacketPayload
{
  public static final CustomPacketPayload.Type<AddJobPayload> TYPE = new CustomPacketPayload.Type<>(RPGUtils.create("add_job"));

  public static final StreamCodec<RegistryFriendlyByteBuf, AddJobPayload> STREAM_CODEC = StreamCodec.composite(
          IJob.NETWORK_TYPED_CODEC, AddJobPayload::job,
          AddJobPayload::new
  );

  @Override
  public Type<? extends CustomPacketPayload> type()
  {
    return TYPE;
  }
}
