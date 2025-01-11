package lanse505.rpg.server;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.CompletableFuture;

public class RPGCommandUtils
{
  public static CompletableFuture<Suggestions> getRegistrySuggestions(Iterable<ResourceLocation> resources, SuggestionsBuilder builder)
  {
    return SharedSuggestionProvider.suggestResource(resources, builder);
  }

  public static <T extends Registry<?>> CompletableFuture<Suggestions> getRegistrySuggestions(CommandContext<CommandSourceStack> ctx, SuggestionsBuilder builder, ResourceKey<T> registryKey)
  {
    return getRegistrySuggestions(ctx.getSource().registryAccess().lookupOrThrow(registryKey).keySet(), builder);
  }
}
