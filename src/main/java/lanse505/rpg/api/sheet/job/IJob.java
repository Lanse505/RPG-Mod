package lanse505.rpg.api.sheet.job;

import com.mojang.serialization.Codec;
import lanse505.rpg.api.RPGRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public interface IJob
{
  Codec<IJob> TYPED_CODEC = RPGRegistries.JOB_TYPE_REGISTRY.byNameCodec().dispatch("type", IJob::getJobType, JobType::codec);
  StreamCodec<RegistryFriendlyByteBuf, IJob> NETWORK_TYPED_CODEC = ByteBufCodecs.registry(RPGRegistries.JOB_TYPES).dispatch(IJob::getJobType, JobType::streamCodec);

  JobType getJobType();

  String getIdentifier();

  Component getTranslatableName();

  int getMinimumLevel();

  int getDefaultLevel();

  int getMaximumLevel();

}
