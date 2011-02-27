package com.bukkit.tcial.stats;

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
  public void onEntityDeath(EntityDeathEvent event)
  {
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
