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

    try
    {
      URL url = new URL(StatsProperties.prop.getProperty(StatsProperties.C_StatsPage) + "?"
          + parameterString.toString());
      BufferedReader statsSender = new BufferedReader(new InputStreamReader(url.openStream()));
      StringBuilder response = new StringBuilder();
      String line = null;
      while ((line = statsSender.readLine()) != null)
      {
        response.append(line);
      }

      // response will throw number format exception if not valid
      Long.parseLong(response.toString());

      // clear parameter stack
      parameterStack.clear();
      return true;
    }
    catch (MalformedURLException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
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
}
