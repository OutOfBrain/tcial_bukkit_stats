package com.bukkit.tcial.stats;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.plugin.Plugin;

public class StatsEntityListener extends EntityListener
{
  private Plugin plugin;

  public StatsEntityListener(Plugin plugin)
  {
    this.plugin = plugin;
  }

  @Override
  public void onEntityDamage(EntityDamageEvent event)
  {
    // // check for damage delt by a player
    // if (event instanceof EntityDamageByEntityEvent
    // && ((EntityDamageByEntityEvent) event).getDamager() instanceof Player)
    // {
    // ((EntityDamageByEntityEvent) event).getDamager();
    // }

    // if (((EntityDamageByEntityEvent) event).getDamager() instanceof Player))

    // check damage tagen by a player
    if (event.getEntity() instanceof Player)
    {
      Player player = (Player) event.getEntity();
      // StatsSender.damageTaken(player.getName(), event.getCause().ordinal(), event.getDamage());

      String reason = "";
      switch (event.getCause())
      {
        case BLOCK_EXPLOSION:
          reason = "explosion";
          break;
        case CONTACT:
          reason = "contact";
          break;
        case DROWNING:
          reason = "drowning";
          break;
        case ENTITY_ATTACK:
          reason = "attack";
          break;
        case FALL:
          reason = "falling";
          break;
        case ENTITY_EXPLOSION:
          reason = "explosion";
          break;
        case FIRE:
          reason = "fire";
          break;
        case FIRE_TICK:
          reason = "firetick";
          break;
        case LAVA:
          reason = "lava";
          break;
        case SUFFOCATION:
          reason = "suffocation";
          break;
      }

      // this.plugin.getServer().broadcastMessage(
      // "damage cause of " + reason + " by " + event.getDamage());
    }
  }

  @Override
  public void onEntityDeath(EntityDeathEvent event)
  {
    if (event.getEntity() instanceof Player)
    {
      // dont cache player death - send immediately
      StatsSender.addDeath((((Player) event.getEntity()).getName()), 1);
    }
  }
  // @Override
  // public void onEntityDamageByEntity(EntityDamageByEntityEvent event)
  // {
  // Entity damager = event.getDamager();
  // if (damager instanceof Player)
  // {
  // Player player = (Player) damager;
  // String target = "<none>";
  // Entity e = event.getEntity();
  // if (e instanceof Chicken)
  // {
  // target = "chicken";
  // }
  // else if (e instanceof Cow)
  // {
  // target = "cow";
  // }
  // else if (e instanceof Pig)
  // {
  // target = "pig";
  // }
  // else if (e instanceof Sheep)
  // {
  // target = "sheep";
  // }
  //
  // player.sendMessage("onEntityDamageByEntity(): damage:" + event.getDamage() + " to:" + target);
  // }
  // }

}
