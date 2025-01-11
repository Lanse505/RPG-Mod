package lanse505.rpg.api.sheet;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lanse505.rpg.api.job.IJob;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.HashMap;
import java.util.Map;

public class Jobs
{
  // Codecs
  public static final Codec<Map<IJob, Integer>> MAP_CODEC = Codec.unboundedMap(IJob.TYPED_CODEC, Codec.INT).xmap(HashMap::new, map -> map);
  public static final MapCodec<Jobs> CODEC = RecordCodecBuilder.mapCodec(
          instance -> instance.group(
                  MAP_CODEC.fieldOf("jobLevelMap").forGetter(Jobs::getJobLevelMap)
          ).apply(instance, Jobs::new)
  );
  public static final StreamCodec<RegistryFriendlyByteBuf, Jobs> STREAM_CODEC = StreamCodec.composite(
          ByteBufCodecs.map(HashMap::new, IJob.NETWORK_TYPED_CODEC, ByteBufCodecs.INT), Jobs::getJobLevelMap,
          Jobs::new
  );

  // Fields
  public final Map<IJob, Integer> jobLevelMap;

  public Jobs()
  {
    this.jobLevelMap = new HashMap<>();
  }

  public Jobs(Map<IJob, Integer> jobLevelMap)
  {
    this.jobLevelMap = jobLevelMap;
  }

  public boolean addJob(IJob job)
  {
    if (jobLevelMap.containsKey(job)) return false;
    jobLevelMap.put(job, 1);
    return true;
  }

  public boolean removeJob(IJob job)
  {
    if (!jobLevelMap.containsKey(job)) return false;
    jobLevelMap.remove(job);
    return true;
  }

  public boolean clearJobs()
  {
    jobLevelMap.clear();
    return true;
  }

  public Map<IJob, Integer> getJobLevelMap()
  {
    return this.jobLevelMap;
  }

}
