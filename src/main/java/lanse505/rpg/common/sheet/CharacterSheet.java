package lanse505.rpg.common.sheet;

import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import lanse505.rpg.RPG;
import lanse505.rpg.api.RPGRegistries;
import lanse505.rpg.api.sheet.ICharacterSheet;
import lanse505.rpg.api.sheet.job.IJob;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.UnknownNullability;

import java.util.HashMap;
import java.util.Map;

public class CharacterSheet implements ICharacterSheet
{

  public boolean isDebug;

  public Map<IJob, Integer> jobs;

  public CharacterSheet(boolean isDebug)
  {
    this.isDebug = isDebug;
    this.jobs = new HashMap<>();
  }

  @Override
  public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider)
  {
    CompoundTag tag = new CompoundTag();
    tag.putBoolean("isDebug", isDebug);
    HolderLookup.RegistryLookup<IJob> lookup = provider.lookupOrThrow(RPGRegistries.JOBS);
    CompoundTag jobsTag = new CompoundTag();
    for (Map.Entry<IJob, Integer> set : jobs.entrySet())
    {
      String key = lookup.listElements().filter(ref -> ref.isBound() && ref.value() == set.getKey()).findFirst().map(Holder.Reference::getKey).toString();
      jobsTag.putInt(key, set.getValue());
    }
    jobsTag.putInt("jobs", jobs.size());
    return tag;
  }

  @Override
  public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt)
  {
    this.isDebug = nbt.contains("isDebug") && nbt.getBoolean("isDebug");
    CompoundTag jobsTag = nbt.getCompound("jobs");
    for (String key : jobsTag.getAllKeys())
    {
      Holder<IJob> holder = provider.holderOrThrow(ResourceKey.create(RPGRegistries.JOBS, ResourceLocation.parse(key)));
      if (!holder.isBound())
      {
        RPG.LOGGER.info("Tried to deserialize job: {}, which is currently not a valid job! SKIPPING", key);
        continue;
      }
      IJob job = holder.value();
      if (jobs.containsKey(job))
      {
        if (jobs.get(job) == jobsTag.getInt(key)) continue;
        jobs.replace(job, jobsTag.getInt(key));
        continue;
      }
      jobs.put(job, jobsTag.getInt(key));
    }
  }

  @Override
  public void updateSheet(LivingEntity entity, Level level)
  {

  }

  @Override
  public boolean isDebug()
  {
    return isDebug;
  }

  @Override
  public boolean addJob(IJob job)
  {
    if (jobs.containsKey(job)) return false;
    jobs.putIfAbsent(job, job.getDefaultLevel());
    return true;
  }

  @Override
  public boolean removeJob(IJob job)
  {
    if (!jobs.containsKey(job)) return false;
    jobs.remove(job);
    return true;
  }

  @Override
  public boolean clearJobs()
  {
    jobs.clear();
    return true;
  }

}
