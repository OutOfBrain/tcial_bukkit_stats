package com.bukkit.tcial.stats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class StatsSender
{
  private static Map<String, String> parameterStack = new HashMap<String, String>();

  private static boolean callStatsServer()
  {
    StringBuilder parameterString = new StringBuilder();

    for (Entry<String, String> parameter : parameterStack.entrySet())
    {
      parameterString.append(parameter.getKey() + "=" + parameter.getValue() + "&");
    }
    parameterString.deleteCharAt(parameterString.length() - 1);

    try
    {
      URL url = new URL(StatsProperties.prop.getProperty(StatsProperties.C_StatsPage) + "?"
          + parameterString.toString());
      System.out.println("url:" + url.toString()); // DEBUG
      BufferedReader statsSender = new BufferedReader(new InputStreamReader(url.openStream()));
      StringBuilder response = new StringBuilder();
      String line = null;
      while ((line = statsSender.readLine()) != null)
      {
        response.append(line);
      }
      // clear parameter stack
      parameterStack.clear();

      // response will throw number format exception if not valid
      Long.parseLong(response.toString());

      // StatsPlugin.plugin.getServer().broadcastMessage("call successfull:" + url.toString());
      return true;
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return false;
  }

  private static boolean addBlock(String action, String userName, long blockType, long blockCount)
  {
    parameterStack.put("action", action);
    parameterStack.put("user", userName);
    parameterStack.put("block_id", "" + blockType);
    parameterStack.put("block_count", "" + blockCount);
    return callStatsServer();
  }

  public static boolean addPlaced(String userName, long blockType, long blockCount)
  {
    return addBlock("addplaced", userName, blockType, blockCount);
  }

  public static boolean addBroken(String userName, long blockType, Long blockCount)
  {
    return addBlock("addbroken", userName, blockType, blockCount);
  }

  public static boolean addDeath(String userName, int deathCount)
  {
    parameterStack.put("action", "adddeath");
    parameterStack.put("user", "" + userName);
    parameterStack.put("death_count", "" + deathCount);
    return callStatsServer();
  }

  public static boolean addMoved(String userName, long blocksMoved)
  {
    parameterStack.put("action", "addmoved");
    parameterStack.put("user", userName);
    parameterStack.put("moved", "" + blocksMoved);
    return callStatsServer();
  }

  public static boolean addFallen(String userName, long fallen)
  {
    parameterStack.put("action", "addfallen");
    parameterStack.put("user", userName);
    parameterStack.put("fallen", "" + fallen);
    return callStatsServer();
  }

  public static boolean addJumped(String userName, long jumped)
  {
    parameterStack.put("action", "addjumped");
    parameterStack.put("user", userName);
    parameterStack.put("jumped", "" + jumped);
    return callStatsServer();
  }
}
