package lanse505.rpg.api.sheet;

import lanse505.rpg.api.job.IJob;
import lanse505.rpg.api.network.packets.AddJobPayload;
import lanse505.rpg.api.network.packets.ClearJobsPayload;
import lanse505.rpg.api.network.packets.RemoveJobPayload;
import lanse505.rpg.api.registrar.registrar.AttachmentRegistrar;
import lanse505.rpg.api.util.RPGExceptionThrower;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;

public class CharacterSheetAccessor implements ICharacterSheetAccessor
{

  @Override
  public boolean isDebug(Player player)
  {
    if (!player.hasData(AttachmentRegistrar.CHARACTER_SHEET)) RPGExceptionThrower.missingDataException(player);
    CharacterSheetData data = player.getData(AttachmentRegistrar.CHARACTER_SHEET);
    return data.isDebug();
  }

  @Override
  public boolean addJob(Player player, IJob job)
  {
    if (!player.hasData(AttachmentRegistrar.CHARACTER_SHEET)) RPGExceptionThrower.missingDataException(player);
    CharacterSheetData data = player.getData(AttachmentRegistrar.CHARACTER_SHEET);
    boolean success = data.getJobs().addJob(job);
    if (success) player.setData(AttachmentRegistrar.CHARACTER_SHEET, data);
    if (player instanceof ServerPlayer serverPlayer) PacketDistributor.sendToPlayer(serverPlayer, new AddJobPayload(job));
    return success;
  }

  @Override
  public boolean removeJob(Player player, IJob job)
  {
    if (!player.hasData(AttachmentRegistrar.CHARACTER_SHEET)) RPGExceptionThrower.missingDataException(player);
    CharacterSheetData data = player.getData(AttachmentRegistrar.CHARACTER_SHEET);
    boolean success = data.getJobs().removeJob(job);
    if (success) player.setData(AttachmentRegistrar.CHARACTER_SHEET, data);
    if (player instanceof ServerPlayer serverPlayer) PacketDistributor.sendToPlayer(serverPlayer, new RemoveJobPayload(job));
    return success;
  }

  @Override
  public boolean clearJobs(Player player)
  {
    if (!player.hasData(AttachmentRegistrar.CHARACTER_SHEET)) RPGExceptionThrower.missingDataException(player);
    CharacterSheetData data = player.getData(AttachmentRegistrar.CHARACTER_SHEET);
    boolean success = data.getJobs().clearJobs();
    if (success) player.setData(AttachmentRegistrar.CHARACTER_SHEET, data);
    if (player instanceof ServerPlayer serverPlayer) PacketDistributor.sendToPlayer(serverPlayer, new ClearJobsPayload());
    return success;
  }


}
