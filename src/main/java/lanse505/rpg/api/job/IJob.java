package lanse505.rpg.api.job;


import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;

public interface IJob
{
  // Codecs
  Codec<IJob> TYPED_CODEC = JobType.CODEC.dispatch("type", IJob::getJobType, JobType::codec);
  StreamCodec<RegistryFriendlyByteBuf, IJob> NETWORK_TYPED_CODEC = JobType.STREAM_CODEC.dispatch(IJob::getJobType, JobType::streamCodec);

  // Methods
  JobType getJobType();
  String getIdentifier();
  Component getTranslatableName();
}
