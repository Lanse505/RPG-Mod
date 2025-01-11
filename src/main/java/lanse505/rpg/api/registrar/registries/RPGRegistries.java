package lanse505.rpg.api.registrar.registries;

import lanse505.rpg.api.job.IJob;
import lanse505.rpg.api.job.JobType;
import lanse505.rpg.api.util.RPGUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

public class RPGRegistries
{
  public static final ResourceKey<Registry<JobType>> JOB_TYPES = ResourceKey.createRegistryKey(RPGUtils.create("job_types"));
  public static final Registry<JobType> JOB_TYPE_REGISTRY = new RegistryBuilder<>(JOB_TYPES).sync(true).create();
  public static final ResourceKey<Registry<IJob>> JOBS = ResourceKey.createRegistryKey(RPGUtils.create("jobs"));

  public static void registerRegistries(final NewRegistryEvent event)
  {
    event.register(JOB_TYPE_REGISTRY);
  }

  public static void registerDatapackRegistries(final DataPackRegistryEvent.NewRegistry event)
  {
    event.dataPackRegistry(JOBS, IJob.TYPED_CODEC, IJob.TYPED_CODEC);
  }
}
