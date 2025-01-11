package lanse505.rpg.api.util;

import net.minecraft.world.entity.player.Player;

public class RPGExceptionThrower
{
  public static void missingDataException(Player player)
  {
    String playerName = player.getDisplayName().getString();
    throw new IllegalStateException(String.format("Player[%s] has no Character Data! This shouldn't happen!", playerName));
  }
}
