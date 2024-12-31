package lanse505.rpg;

import lanse505.rpg.api.RPGRegistries;
import lanse505.rpg.api.registrar.JobTypeRegistrar;
import lanse505.rpg.api.sheet.ICharacterSheet;
import lanse505.rpg.common.config.Config;
import lanse505.rpg.common.sheet.CharacterSheet;
import lanse505.rpg.common.util.RPGCapabilities;
import lanse505.rpg.server.RPGServer;
import lanse505.rpg.server.command.RPGCommands;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.profiling.jfr.event.WorldLoadFinishedEvent;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

/**
 * TODO:
 *  -
 */
@Mod(RPG.MODID)
public class RPG
{
    public static final String MODID = "rpg";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation create(String path)
    {
        return ResourceLocation.tryBuild(MODID, path);
    }

    public RPG(IEventBus bus, ModContainer container)
    {
        bus.addListener(RPGCapabilities::registerCapabilities);
        bus.addListener(RPGRegistries::registerRegistries);
        bus.addListener(RPGRegistries::registerDatapackRegistries);
        JobTypeRegistrar.init(bus);
        container.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        NeoForge.EVENT_BUS.addListener(RPG::onWorldTick);
        NeoForge.EVENT_BUS.addListener(RPG::registerCommands);
        // TODO: Uncomment this once we add Client-stuff
        /*
            if (FMLEnvironment.dist == Dist.CLIENT)
            {

            }
         */
    }

    private static void registerCommands(RegisterCommandsEvent event)
    {
        RPGCommands.registerCommands(event.getDispatcher());
    }

    private static boolean hasTicked = false;
    private static void onWorldTick(PlayerTickEvent.Post event)
    {
       if (!hasTicked)
       {
           try
           {
               LOGGER.info("boop");
               event.getEntity().level()
                       .registryAccess()
                       .lookupOrThrow(RPGRegistries.JOBS)
                       .keySet().forEach(rl -> LOGGER.info(rl.toString()));
               hasTicked = true;
           } catch (Exception ignored) {}
       }

    }
}
