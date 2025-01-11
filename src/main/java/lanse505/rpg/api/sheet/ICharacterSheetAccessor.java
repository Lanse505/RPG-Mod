package lanse505.rpg.api.sheet;

import lanse505.rpg.api.job.IJob;
import lanse505.rpg.api.util.RPGUtils;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.capabilities.EntityCapability;
import org.jetbrains.annotations.Nullable;


public interface ICharacterSheetAccessor
{
  // Capability
  EntityCapability<ICharacterSheetAccessor, @Nullable Void> CAPABILITY = EntityCapability.createVoid(RPGUtils.create("character_sheet"), ICharacterSheetAccessor.class);

  // Debug Methods
  boolean isDebug(Player player);

  // Methods
  boolean addJob(Player player, IJob job);
  boolean removeJob(Player player, IJob job);
  boolean clearJobs(Player player);

}
