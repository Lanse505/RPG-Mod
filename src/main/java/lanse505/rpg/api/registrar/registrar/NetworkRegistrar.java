package lanse505.rpg.api.registrar.registrar;

import lanse505.rpg.api.network.packets.AddJobPayload;
import lanse505.rpg.api.network.packets.ClearJobsPayload;
import lanse505.rpg.api.network.packets.RemoveJobPayload;
import lanse505.rpg.api.network.packets.SyncSheetDataPayload;
import lanse505.rpg.api.sheet.ICharacterSheetAccessor;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class NetworkRegistrar
{
  public static void registerNetworkPayloads(final RegisterPayloadHandlersEvent event)
  {
    final PayloadRegistrar registrar = event.registrar("1");
    registrar.playToClient(
            SyncSheetDataPayload.TYPE,
            SyncSheetDataPayload.STREAM_CODEC,
            new DirectionalPayloadHandler<>(
                    (payload, context) -> {
                      Player player = context.player();
                      player.setData(AttachmentRegistrar.CHARACTER_SHEET, payload.data());
                    }, (payload, context) -> {}
            )
    );
    registrar.playToClient(
            AddJobPayload.TYPE,
            AddJobPayload.STREAM_CODEC,
            new DirectionalPayloadHandler<>(
                    (payload, context) -> {
                      Player player = context.player();
                      ICharacterSheetAccessor accessor = player.getCapability(ICharacterSheetAccessor.CAPABILITY);
                      if (accessor != null) accessor.addJob(player, payload.job());
            }, (payload, context) -> {})
    );
    registrar.playToClient(
            RemoveJobPayload.TYPE,
            RemoveJobPayload.STREAM_CODEC,
            new DirectionalPayloadHandler<>(
                    (payload, context) -> {
                      Player player = context.player();
                      ICharacterSheetAccessor accessor = player.getCapability(ICharacterSheetAccessor.CAPABILITY);
                      if (accessor != null) accessor.removeJob(player, payload.job());
                    }, (payload, context) -> {})
    );
    registrar.playToClient(
            ClearJobsPayload.TYPE,
            StreamCodec.unit(null),
            new DirectionalPayloadHandler<>(
                    (payload, context) -> {
                      Player player = context.player();
                      ICharacterSheetAccessor accessor = player.getCapability(ICharacterSheetAccessor.CAPABILITY);
                      if (accessor != null) accessor.clearJobs(player);
                    }, (payload, context) -> {})
    );
  }
}
