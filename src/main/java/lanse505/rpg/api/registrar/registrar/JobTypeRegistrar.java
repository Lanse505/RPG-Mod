package lanse505.rpg.api.registrar.registrar;

import lanse505.rpg.api.job.Job;
import lanse505.rpg.api.job.JobType;
import lanse505.rpg.api.registrar.registries.RPGRegistries;
import lanse505.rpg.api.util.RPGConstants;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class JobTypeRegistrar
{
  private static final DeferredRegister<JobType> JOB_TYPES = DeferredRegister.create(RPGRegistries.JOB_TYPES, RPGConstants.MODID);

  public static final DeferredHolder<JobType, JobType> DEFAULT = JOB_TYPES.register("default", () -> new JobType(Job.CODEC, Job.STREAM_CODEC));

  public static void registerJobTypes(IEventBus bus)
  {
    JOB_TYPES.register(bus);
  }
}
