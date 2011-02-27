package com.bukkit.tcial.stats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class StatsSender
{
  private static boolean addBlock(String action, String userName, long blockType, long blockCount)
  {
    try
    {
      URL url = new URL(StatsProperties.prop.getProperty(StatsProperties.C_StatsPage)
          + "?action=" + action + "&user=" + userName + "&block_id=" + blockType
          + "&block_count=" + blockCount);
      BufferedReader statsSender = new BufferedReader(new InputStreamReader(url.openStream()));
      StringBuilder response = new StringBuilder();
      String line = null;
      while ((line = statsSender.readLine()) != null)
      {
        response.append(line);
      }

      // response will throw number format exception if not valid
      Long.parseLong(response.toString());
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

  public static boolean addPlaced(String userName, long blockType, long blockCount)
  {
    return addBlock("addplaced", userName, blockType, blockCount);
  }

  public static boolean addBroken(String userName, long blockType, Long blockCount)
  {
    return addBlock("addbroken", userName, blockType, blockCount);
  }

}
