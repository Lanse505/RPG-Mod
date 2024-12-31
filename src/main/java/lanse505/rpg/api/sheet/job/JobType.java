package lanse505.rpg.api.sheet.job;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import lanse505.rpg.api.RPGRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record JobType(MapCodec<? extends IJob> codec,
                      StreamCodec<RegistryFriendlyByteBuf, ? extends IJob> streamCodec)
{
  public static Codec<JobType> CODEC = RPGRegistries.JOB_TYPE_REGISTRY.byNameCodec();
  public static StreamCodec<RegistryFriendlyByteBuf, JobType> NETWORK_CODEC = ByteBufCodecs.registry(RPGRegistries.JOB_TYPES);
}
