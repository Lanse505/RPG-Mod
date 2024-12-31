package lanse505.rpg.api.sheet;

import lanse505.rpg.RPG;
import lanse505.rpg.api.sheet.job.IJob;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.Nullable;

public interface ICharacterSheet extends INBTSerializable<CompoundTag>
{
  EntityCapability<ICharacterSheet, @Nullable Void> CAPABILITY = EntityCapability.createVoid(RPG.create("character_sheet"), ICharacterSheet.class);

  void updateSheet(LivingEntity entity, Level level);

  boolean isDebug();

  boolean addJob(IJob job);

  boolean removeJob(IJob job);

  boolean clearJobs();

}
