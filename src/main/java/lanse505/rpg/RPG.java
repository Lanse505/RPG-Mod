package lanse505.rpg;

import lanse505.rpg.api.network.packets.SyncSheetDataPayload;
import lanse505.rpg.api.registrar.registrar.AttachmentRegistrar;
import lanse505.rpg.api.registrar.RPGRegistrar;
import lanse505.rpg.api.sheet.CharacterSheetData;
import lanse505.rpg.api.sheet.ICharacterSheetAccessor;
import lanse505.rpg.api.sheet.Jobs;
import lanse505.rpg.api.util.RPGConstants;
import lanse505.rpg.server.RPGCommands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(RPGConstants.MODID)
public class RPG
{
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public RPG(IEventBus bus, ModContainer container)
    {
        RPGRegistrar.register(bus);
        NeoForge.EVENT_BUS.addListener(RPG::onLoggedIn);
        NeoForge.EVENT_BUS.addListener(RPG::doesSheetsWork);
        NeoForge.EVENT_BUS.addListener(RPG::cloneDataOnDeath);
        NeoForge.EVENT_BUS.addListener(RPG::registerCommands);
    }

    private static void registerCommands(final RegisterCommandsEvent event)
    {
        RPGCommands.registerCommands(event.getDispatcher());
    }

    private static void doesSheetsWork(final PlayerTickEvent.Post event)
    {
        Player player = event.getEntity();
        if (player.tickCount % 100 == 0)
        {
            LOGGER.info("Has Capability: {}", player.getCapability(ICharacterSheetAccessor.CAPABILITY) != null);
            LOGGER.info("Has Data: {}", player.hasData(AttachmentRegistrar.CHARACTER_SHEET));
            if (player.hasData(AttachmentRegistrar.CHARACTER_SHEET))
            {
                CharacterSheetData data = player.getData(AttachmentRegistrar.CHARACTER_SHEET);
                Jobs jobs = data.getJobs();
                jobs.jobLevelMap.keySet().forEach(job -> LOGGER.info(job.getIdentifier()));
            }
        }
    }

    private static void onLoggedIn(final PlayerEvent.PlayerLoggedInEvent event)
    {
        Player player = event.getEntity();
        if (player instanceof ServerPlayer serverPlayer) {
            if (!player.hasData(AttachmentRegistrar.CHARACTER_SHEET)) {
                player.setData(AttachmentRegistrar.CHARACTER_SHEET, new CharacterSheetData());
            }
            PacketDistributor.sendToPlayer(serverPlayer, new SyncSheetDataPayload(player.getData(AttachmentRegistrar.CHARACTER_SHEET)));
        }
    }

    private static void cloneDataOnDeath(final PlayerEvent.Clone event)
    {
        if (event.isWasDeath() && event.getOriginal().hasData(AttachmentRegistrar.CHARACTER_SHEET))
        {
            event.getEntity().setData(AttachmentRegistrar.CHARACTER_SHEET, event.getOriginal().getData(AttachmentRegistrar.CHARACTER_SHEET));
        }
    }
}
