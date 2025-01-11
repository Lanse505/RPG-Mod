package lanse505.rpg.api.util;

import net.minecraft.resources.ResourceLocation;

public class RPGUtils
{
  public static ResourceLocation create(String path)
  {
    return ResourceLocation.tryBuild(RPGConstants.MODID, path);
  }
}
