package dev.whatsappuser.minestom.lib.world;

import dev.whatsappuser.minestom.lib.location.Location;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.block.Block;

import java.io.File;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * development by TimoH created on 12:26:30 | 30.12.2022
 */

public interface World {

    Block getBlockAt(int x, int y, int z);

    Block getBlockAt(Location location);

    @Deprecated
    int getBlockTypeIdAt(int x, int y, int z);

    @Deprecated
    int getBlockTypeIdAt(Location location);

    int getHighestBlockYAt(int x, int z);

    int getHighestBlockYAt(Location location);

    Block getHighestBlockAt(int x, int z);

    Block getHighestBlockAt(Location location);

    Chunk getChunkAt(int x, int z);

    Chunk getChunkAt(Location location);

    Chunk getChunkAt(Block block);

    boolean isChunkLoaded(Chunk chunk);

    Chunk[] getLoadedChunks();

    boolean isChunkLoaded(int x, int z);

    boolean isChunkInUse(int x, int z);

    Entity spawnEntity(Location loc, EntityType type);

    Set<Entity> getEntities();

    WorldInfo info();

    File directory();

    CompletableFuture<Void> save();
}
