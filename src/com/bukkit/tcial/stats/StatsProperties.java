package com.bukkit.tcial.stats;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class StatsProperties
{
  // properties
  public static Properties   prop                 = new Properties();

  // constants
  public static final String C_EventSubmitTrigger = "eventSubmitTrigger";
  public static final String C_StatsPage          = "statsPage";

  // init
  public static void init(String propertyFilename)
  {
    try
    {
      File propFile = new File(propertyFilename);
      if (!propFile.isFile())
      {
        // file does not exist - create default one
        Properties defaultProperties = new Properties();
        defaultProperties.put(C_EventSubmitTrigger, "15");
        defaultProperties.put(C_StatsPage, "http://localhost/test/stats.php");
        defaultProperties.store(new FileWriter(propFile), "");
      }
      prop.load(new FileInputStream(propFile));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
