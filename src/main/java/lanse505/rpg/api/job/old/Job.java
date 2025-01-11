package lanse505.rpg.api.job.old;

public class Job
{
  /*
  public static MapCodec<IJob> CODEC = RecordCodecBuilder.mapCodec(
          instance -> instance.group(
                  JobType.CODEC.fieldOf("type").forGetter(IJob::getJobType),
                  Codec.STRING.fieldOf("identifier").forGetter(IJob::getIdentifier),
                  Codec.INT.optionalFieldOf("minimumLevel", 1).forGetter(IJob::getMinimumLevel),
                  Codec.INT.optionalFieldOf("defaultLevel").forGetter(job -> Optional.of(job.getDefaultLevel())),
                  Codec.INT.optionalFieldOf("maximumLevel").forGetter(job -> Optional.of(job.getMaximumLevel()))
          ).apply(instance, (type, identifier, minimumLevel, defaultLevel, maximumLevel) ->
                  new Job(type, identifier, minimumLevel, defaultLevel.orElse(minimumLevel), maximumLevel.orElse(minimumLevel)))
  );

  public static StreamCodec<RegistryFriendlyByteBuf, Job> STREAM_CODEC = StreamCodec.composite(
          JobType.NETWORK_CODEC, IJob::getJobType,
          ByteBufCodecs.STRING_UTF8, IJob::getIdentifier,
          ByteBufCodecs.INT, IJob::getMinimumLevel,
          ByteBufCodecs.INT, IJob::getDefaultLevel,
          ByteBufCodecs.INT, IJob::getMaximumLevel,
          Job::new
  );

  // Serialized Fields
  public final JobType type;
  public final String identifier;
  public final int minimumLevel;
  public final int defaultLevel;
  public final int maximumLevel;

  // Non-Serialized Fields
  public Component translatedName;

  public Job(JobType type, String identifier, int minimumLevel, int defaultLevel, int maximumLevel)
  {
    this.type = type;
    this.identifier = identifier;
    this.minimumLevel = minimumLevel;
    this.defaultLevel = defaultLevel;
    this.maximumLevel = maximumLevel;
  }

  @Override
  public JobType getJobType()
  {
    return type;
  }

  @Override
  public String getIdentifier()
  {
    return identifier;
  }

  @Override
  public Component getTranslatableName()
  {
    if (translatedName == null) translatedName = Component.translatable(identifier);
    return translatedName;
  }

  @Override
  public int getMinimumLevel()
  {
    return minimumLevel;
  }

  @Override
  public int getDefaultLevel()
  {
    return defaultLevel;
  }

  @Override
  public int getMaximumLevel()
  {
    return maximumLevel;
  }

   */
}
