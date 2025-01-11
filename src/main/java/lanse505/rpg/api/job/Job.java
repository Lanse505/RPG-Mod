package lanse505.rpg.api.job;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class Job implements IJob
{

  public static MapCodec<IJob> CODEC = RecordCodecBuilder.mapCodec(
          instance -> instance.group(
                  JobType.CODEC.fieldOf("type").forGetter(IJob::getJobType),
                  Codec.STRING.fieldOf("identifier").forGetter(IJob::getIdentifier)
          ).apply(instance, Job::new)
  );

  public static StreamCodec<RegistryFriendlyByteBuf, IJob> STREAM_CODEC = StreamCodec.composite(
          JobType.STREAM_CODEC, IJob::getJobType,
          ByteBufCodecs.STRING_UTF8, IJob::getIdentifier,
          Job::new
  );

  // Fields
  private final JobType type;
  private final String identifier;
  private Component translated;

  public Job(JobType type, String identifier)
  {
    this.type = type;
    this.identifier = identifier;
  }

  @Override
  public JobType getJobType()
  {
    return this.type;
  }

  @Override
  public String getIdentifier()
  {
    return this.identifier;
  }

  @Override
  public Component getTranslatableName()
  {
    if (translated == null) translated = Component.translatable(identifier);
    return translated;
  }
}
