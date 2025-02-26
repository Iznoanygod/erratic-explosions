package xyz.srnyx.erraticexplosions.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

import org.jetbrains.annotations.NotNull;

import xyz.srnyx.annoyingapi.AnnoyingListener;

import xyz.srnyx.erraticexplosions.ErraticExplosions;


public class TNTListener implements AnnoyingListener {
    @NotNull private final ErraticExplosions plugin;

    @Override @NotNull
    public ErraticExplosions getAnnoyingPlugin() {
        return plugin;
    }

    public TNTListener(@NotNull ErraticExplosions plugin) {
        this.plugin = plugin;
    }

    /**
     * Called when an entity is spawned into a world.
     * If an Entity Spawn event is cancelled, the entity will not spawn.
     */
    @EventHandler
    public void onEntitySpawn(@NotNull EntitySpawnEvent event) {
        final Entity entity = event.getEntity();
        if (entity instanceof TNTPrimed && plugin.config.tnt) ((TNTPrimed) entity).setFuseTicks(plugin.config.getFuse());
    }

    /**
     * Called when an entity has made a decision to explode.
     */
    @EventHandler
    public void onExplosionPrime(@NotNull ExplosionPrimeEvent event) {
        if (event.getEntity() instanceof TNTPrimed && plugin.config.tnt) event.setRadius(plugin.config.getPower());
    }
}
