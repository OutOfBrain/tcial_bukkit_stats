package com.bukkit.tcial.stats;

import org.bukkit.Location;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerItemEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class StatsPlayerListener extends PlayerListener
{
  private Plugin plugin;

  private double xDist;
  private double yDist;
  private double zDist;

  static double  dist;
  static long    count = 0;

  public StatsPlayerListener(Plugin plugin)
  {
    this.plugin = plugin;
  }

  @Override
  public void onPlayerCommand(PlayerChatEvent event)
  {
    // this.plugin.getServer().broadcastMessage("onPlayerCommand:" + event.getMessage());
    // if (event.getMessage().toLowerCase().startsWith("/stats"))
    // {
    // StatsBlockListener.outputStats();
    // }
  }

  @Override
  public void onPlayerMove(PlayerMoveEvent event)
  {
    ++count;
    // TODO: adding x,y,z calculate distance every 100 or so events
    // TODO: dont be stupid
    Location from = event.getFrom();
    Location to = event.getTo();
    this.xDist += Math.abs(to.getX() - from.getX());
    this.yDist += Math.abs(to.getY() - from.getY());
    this.zDist += Math.abs(to.getZ() - from.getZ());

    if ((count % 50) == 0)
    {
      dist += Math
          .sqrt(this.xDist * this.xDist + this.yDist * this.yDist + this.zDist * this.zDist);
      this.xDist = 0;
      this.yDist = 0;
      this.zDist = 0;

      event.getPlayer().sendMessage(
          "onPlayerMove(): count:" + (count) + " dist:" + dist);
    }
  }

  @Override
  public void onPlayerItem(PlayerItemEvent event)
  {
    event.getPlayer().sendMessage(
        "onPlayerItem(): pitch:" + event.getPlayer().getLocation().getPitch() + " yaw:"
            + event.getPlayer().getLocation().getYaw());
  }
}
