package com.bukkit.tcial.stats;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerItemEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class StatsPlayerListener extends PlayerListener
{
  private static Random rand = new Random(); // TODO

  private Plugin        plugin;

  class Distance
  {
    double rawX;
    double rawYUp;
    double rawYDown;
    double rawZ;
  }

  // randomstuff
  private long newNextMoved()
  {
    return (this.nextMoved = 100 + rand.nextInt(100));
  }

  private long newNextJumped()
  {
    return (this.nextJumped = 100 + rand.nextInt(100));
  }

  private long newNextFallen()
  {
    return (this.nextFallen = -100 - rand.nextInt(100));
  }

  long                                 nextMoved   = this.newNextMoved();
  long                                 nextJumped  = this.newNextJumped();
  long                                 nextFallen  = this.newNextFallen();

  private static Map<String, Distance> playerMoved = new HashMap<String, Distance>();

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
    // TODO: adding x,y,z calculate distance every 100 or so events
    String userName = event.getPlayer().getName();
    Distance playerDist = playerMoved.get(userName);
    if (playerDist == null)
    {
      playerDist = new Distance();
      playerMoved.put(userName, playerDist);
    }

    Location from = event.getFrom();
    Location to = event.getTo();
    double xDist = Math.abs(to.getX() - from.getX());
    double yDist = to.getY() - from.getY();
    double zDist = Math.abs(to.getZ() - from.getZ());

    playerDist.rawX += xDist;
    if (yDist > 0)
    {
      playerDist.rawYUp += yDist;
    }
    else
    {
      playerDist.rawYDown += yDist;
    }
    playerDist.rawZ += zDist;

    // moved
    if (playerDist.rawX + playerDist.rawZ > this.nextMoved)
    {
      long moved = (long) Math.sqrt(playerDist.rawX * playerDist.rawX + playerDist.rawZ
          * playerDist.rawZ);

      boolean result = StatsSender.addMoved(userName, moved);
      if (result)
      {
        playerDist.rawX = 0;
        playerDist.rawZ = 0;
        this.newNextMoved();
      }
    }

    // jumped
    if (playerDist.rawYUp > this.nextJumped)
    {

      boolean result = StatsSender.addJumped(userName, (long) playerDist.rawYUp);
      if (result)
      {
        playerDist.rawYUp = 0;
        this.newNextJumped();
      }
    }

    // fallen
    if (playerDist.rawYDown < this.nextFallen)
    {

      boolean result = StatsSender.addFallen(userName, (long) playerDist.rawYDown);
      if (result)
      {
        playerDist.rawYDown = 0;
        this.newNextFallen();
      }

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
