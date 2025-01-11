package lanse505.rpg.api.sheet;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class CharacterSheetData
{
  // Codecs
  public static final Codec<CharacterSheetData> CODEC = RecordCodecBuilder.create(instance ->
          instance.group(
               Codec.BOOL.fieldOf("isDebug").forGetter(sheet -> false), // Placeholder, remove this.
               Jobs.CODEC.fieldOf("jobs").forGetter(CharacterSheetData::getJobs)
          ).apply(instance, CharacterSheetData::new)
  );

  public static final StreamCodec<RegistryFriendlyByteBuf, CharacterSheetData> STREAM_CODEC = StreamCodec.composite(
          ByteBufCodecs.BOOL, sheet -> false, // Placeholder, remove this.
          Jobs.STREAM_CODEC, CharacterSheetData::getJobs,
          CharacterSheetData::new
  );

  // Fields
  private final boolean isDebug;
  private final Jobs jobs;

  public CharacterSheetData()
  {
    this(false, new Jobs());
  }

  public CharacterSheetData(boolean isDebug, Jobs jobs)
  {
    this.isDebug = isDebug;
    this.jobs = jobs;
  }

  public boolean isDebug() {
    return this.isDebug;
  }

  public Jobs getJobs()
  {
    return this.jobs;
  }

}
