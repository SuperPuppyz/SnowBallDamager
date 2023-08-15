package com.wytheria.damager;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSnowballHit(ProjectileHitEvent event) {
        if (event.getHitEntity() != null && event.getHitEntity() instanceof Damageable){
            if (event.getEntity().getType() == EntityType.SNOWBALL) {
                System.out.println("snowball hit event");
                ((Damageable) event.getHitEntity()).damage(getConfig().getDouble("damage"));
                getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                    final Vector plrV = event.getEntity().getVelocity();
                    double increase = getConfig().getDouble("increase");
                    double horizontalModifier = getConfig().getDouble("horizontalModifier");
                    double verticalModifier = getConfig().getDouble("verticalModifier");
                    event.getEntity().setVelocity(new Vector(plrV.getX() *
                            horizontalModifier + increase, plrV.getY() * verticalModifier + increase, plrV.getZ() * horizontalModifier + increase));
                }, 0L);
            }
        }
    }

}