package lanse505.rpg.api.registrar;

import lanse505.rpg.RPG;
import lanse505.rpg.api.RPGRegistries;
import lanse505.rpg.api.sheet.job.JobType;
import lanse505.rpg.common.sheet.job.Job;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class JobTypeRegistrar
{
  private static final DeferredRegister<JobType> JOB_TYPES = DeferredRegister.create(RPGRegistries.JOB_TYPES, RPG.MODID);

  public static final DeferredHolder<JobType, JobType> DEFAULT = JOB_TYPES.register("default", () -> new JobType(Job.CODEC, Job.STREAM_CODEC));

  public static void init(IEventBus bus)
  {
    JOB_TYPES.register(bus);
  }
}
