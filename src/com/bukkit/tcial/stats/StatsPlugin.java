package com.bukkit.tcial.stats;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class StatsPlugin extends JavaPlugin
{
  private final StatsBlockListener  blockListener  = new StatsBlockListener(this);
  private final StatsPlayerListener playerListener = new StatsPlayerListener(this);
  private final StatsEntityListener entityListener = new StatsEntityListener(this);

  // public StatsPlugin(PluginLoader pluginLoader, Server instance, PluginDescriptionFile desc,
  // File folder, File plugin, ClassLoader cLoader)
  // {
  // super(pluginLoader, instance, desc, folder, plugin, cLoader);
  // }

  @Override
  public void onEnable()
  {
    // init properties
    StatsProperties.init("tcial_stats.properties");

    // Register our events
    PluginManager pm = this.getServer().getPluginManager();
    pm.registerEvent(Type.PLAYER_COMMAND, this.playerListener, Priority.Normal, this);
    pm.registerEvent(Type.BLOCK_PLACED, this.blockListener, Priority.Normal, this);
    pm.registerEvent(Type.BLOCK_BREAK, this.blockListener, Priority.Normal, this);
    pm.registerEvent(Type.ENTITY_DEATH, this.entityListener, Priority.Normal, this);

    // pm.registerEvent(Type.BLOCK_DAMAGED, this.blockListener, Priority.Normal, this);
    // pm.registerEvent(Type.PLAYER_MOVE, this.playerListener, Priority.Normal, this);
    // pm.registerEvent(Type.PLAYER_ITEM, this.playerListener, Priority.Normal, this);

    PluginDescriptionFile pdfFile = this.getDescription();
    System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    // this.getServer().broadcastMessage("onCommand:" + cmd.getName());
    return super.onCommand(sender, cmd, commandLabel, args);
  }

  @Override
  public void onDisable()
  {
    System.out.println("Goodbye world!");
  }
}
