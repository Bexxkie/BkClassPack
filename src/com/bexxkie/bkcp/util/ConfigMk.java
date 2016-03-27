 package com.bexxkie.bkcp.util;
 
 import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
 
 public class ConfigMk
 {
   private final String fileName;
   private final JavaPlugin plugin;
   private final String dir;
   private File configFile;
   private FileConfiguration fileConfiguration;

   
   public ConfigMk(JavaPlugin plugin, String fileName, String dir)
   {
     this.plugin = plugin;
     this.dir = dir;
     this.fileName = fileName;
   }
   
   @SuppressWarnings("deprecation")
public void reloadConfig()
   {
     if (this.configFile == null)
     {
       File dataFolder = this.plugin.getDataFolder();
       if (dataFolder == null) {
         throw new IllegalStateException();
       }
       if(dir != null){
    	   this.configFile = new File(dataFolder.getPath()+dir, this.fileName);
       }else{
    	   
    	   this.configFile = new File(dataFolder, this.fileName);
       }
    	   
     }
     this.fileConfiguration = YamlConfiguration.loadConfiguration(this.configFile);
     
 
     InputStream defConfigStream = this.plugin.getResource(this.fileName);
     if (defConfigStream != null)
     {
       YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
       this.fileConfiguration.setDefaults(defConfig);
     }
   }
   
   public FileConfiguration getConfig()
   {
     if (this.fileConfiguration == null) {
       reloadConfig();
     }
     return this.fileConfiguration;
     
   }
   
   public void saveConfig()
   {
     if ((this.fileConfiguration == null) || (this.configFile == null)) {
       return;
     }
     try
     {
       getConfig().save(this.configFile);
     }
     catch (IOException ex)
     {
       this.plugin.getLogger().log(Level.SEVERE, 
         "Could not save config to " + this.configFile, ex);
     }
//--
     
   }
 }


