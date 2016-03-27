package com.bexxkie.bkcp.util;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;


import com.bexxkie.bkcp.BkCP;


public class Upgrade 
{
	public FileConfiguration playerClassOld;
	public void getFile()
	{
		Bukkit.getServer().getConsoleSender().sendMessage("[BkClassPack] To import playerClass config from '"+ChatColor.RED+"P3Pplus"+ChatColor.RESET+"' set '"+ChatColor.RED+"Module.upgrade = true"+ChatColor.RESET+"' inside '"+ChatColor.RED+"config.yml"+ChatColor.RESET+"'");
		if(BkCP.config.getConfig().getBoolean("Module.upgrade"))
		{
			playerClassOld = YamlConfiguration.loadConfiguration(new File("plugins/PonyPackPlus", "playerClass.yml"));
			if(playerClassOld!=null)
			{
				getPlayerData();
			}
		}
	}
	public void getPlayerData()
	{
		for(String key :playerClassOld.getKeys(false))
		{
			String playerName = ChatColor.RED+Bukkit.getOfflinePlayer(UUID.fromString(key)).getName()+ChatColor.RESET;
			Bukkit.getServer().getConsoleSender().sendMessage("[BkClassPack] Found "+ChatColor.RED+key+ChatColor.RESET+" ["+playerName+"]");
			System.out.print("[BkClassPack] Updating playerData...");
			
			String pCs = playerClassOld.getString(key+".class");
			Bukkit.getServer().getConsoleSender().sendMessage("[BkClassPack] playerClass ["+playerName+"] found: "+ChatColor.RED+pCs);
			
			
			switch(pCs.toLowerCase())
			{
			case"unicorn":
				String[] spells = {"flameI","frostI","blink","healI","shadowBeamI"};
				BkCP.playerClass.getConfig().set(key, null);
				BkCP.playerClass.getConfig().set(key+".class", "Unicorn");
				BkCP.playerClass.getConfig().set(key+".mana", 50.0);
				BkCP.playerClass.getConfig().set(key+".maxMana", BkCP.advCfg.getConfig().getDouble("Classes.Unicorn.maxMana"));
				BkCP.playerClass.getConfig().set(key+".level", 0);
				BkCP.playerClass.getConfig().set(key+".branch", "default");
				BkCP.playerClass.getConfig().set(key+".currentSpell", 0);
				BkCP.playerClass.getConfig().set(key+".spells", spells);
				commitData(key);
				break;
			case"pegasus":
				BkCP.playerClass.getConfig().set(key, null);
				BkCP.playerClass.getConfig().set(key+".class", "Pegasus");
				BkCP.playerClass.getConfig().set(key+".energy", 50.0);
				BkCP.playerClass.getConfig().set(key+".maxEnergy", BkCP.advCfg.getConfig().getDouble("Classes.Pegasus.maxEnergy"));
				BkCP.playerClass.getConfig().set(key+".level", 0);
				BkCP.playerClass.getConfig().set(key+".fxp", 0);
				BkCP.playerClass.getConfig().set(key+".ftime", 0);
				commitData(key);
				break;
			case"earth":
				BkCP.playerClass.getConfig().set(key, null);
				BkCP.playerClass.getConfig().set(key+".class", "Earth");
				BkCP.playerClass.getConfig().set(key+".energy", 50.0);
				BkCP.playerClass.getConfig().set(key+".maxEnergy", BkCP.advCfg.getConfig().getDouble("Classes.Earth.maxEnergy"));
				BkCP.playerClass.getConfig().set(key+".level", 0);
				BkCP.playerClass.getConfig().set(key+".fxp", 0);
				BkCP.playerClass.getConfig().set(key+".branch", "default");
				commitData(key);
				break;
			case "alicorn":
				BkCP.playerClass.getConfig().set(key, null);
				BkCP.playerClass.getConfig().set(key+".class", "Alicorn");
				BkCP.playerClass.getConfig().set(key+".energy", BkCP.advCfg.getConfig().getDouble("Classes.Alicorn.maxEnergy"));
				BkCP.playerClass.getConfig().set(key+".mana", BkCP.advCfg.getConfig().getDouble("Classes.Alicorn.maxMana"));
				BkCP.playerClass.getConfig().set(key+".maxEnergy", BkCP.advCfg.getConfig().getDouble("Classes.Alicorn.maxEnergy"));
				BkCP.playerClass.getConfig().set(key+".maxMana", BkCP.advCfg.getConfig().getDouble("Classes.Alicorn.maxMana"));
				BkCP.playerClass.getConfig().set(key+".flevel", 0);
				BkCP.playerClass.getConfig().set(key+".mlevel", 0);
				BkCP.playerClass.getConfig().set(key+".fxp", 0);
				BkCP.playerClass.getConfig().set(key+".ftime", 0);
				BkCP.playerClass.getConfig().set(key+".branch", "default");
				commitData(key);
				break;
			case "changeling":
				BkCP.playerClass.getConfig().set(key, null);
				BkCP.playerClass.getConfig().set(key+".class", "Changeling");
				BkCP.playerClass.getConfig().set(key+".energy", BkCP.advCfg.getConfig().getDouble("Classes.Changeling.maxEnergy"));
				BkCP.playerClass.getConfig().set(key+".mana", BkCP.advCfg.getConfig().getDouble("Classes.Changeling.maxMana"));
				BkCP.playerClass.getConfig().set(key+".maxEnergy", BkCP.advCfg.getConfig().getDouble("Classes.Changeling.maxEnergy"));
				BkCP.playerClass.getConfig().set(key+".maxMana", BkCP.advCfg.getConfig().getDouble("Classes.Changeling.maxMana"));
				BkCP.playerClass.getConfig().set(key+".flevel", 0);
				BkCP.playerClass.getConfig().set(key+".mlevel", 0);
				BkCP.playerClass.getConfig().set(key+".fxp", 0);
				BkCP.playerClass.getConfig().set(key+".ftime", 0);
				BkCP.playerClass.getConfig().set(key+".branch", "default");
				BkCP.playerClass.getConfig().set(key+".disguises",BkCP.advCfg.getConfig().getDouble("Classes.Changeling.disguises.default"));
				commitData(key);
				break;
			default:
				System.out.print("[BkClassPack] playerClass ["+pCs+"] does not exist! skipping...");
				break;
			}
		}
		Bukkit.getServer().getConsoleSender().sendMessage("[BkClassPack]"+ChatColor.RED+" Upgrade complete, disabling for future startups");
		BkCP.config.getConfig().set("Module.upgrade", false);
		BkCP.config.saveConfig();
	}

	public void commitData(String key)
	{
		String playerName = ChatColor.RED+Bukkit.getOfflinePlayer(UUID.fromString(key)).getName()+ChatColor.RESET;
		if (BkCP.econEnabled==true)
		{
			System.out.print("[BkClassPack] Additional module installed, updating required fields...");
			if(BkCP.playerClass.getConfig().contains(key))
			{
				BkCP.playerClass.getConfig().set(key+".currency.gold",100);
				BkCP.playerClass.getConfig().set(key+".currency.gem",10);
				//BkCP.playerClass.saveConfig();
				//BkCP.playerClass.reloadConfig();
			}
		}
		Bukkit.getServer().getConsoleSender().sendMessage("[BkClassPack] Saving playerData for ["+playerName+"]");
		BkCP.playerClass.saveConfig();
		BkCP.playerClass.reloadConfig();
		Bukkit.getServer().getConsoleSender().sendMessage("[BkClassPack] playerData for ["+playerName+"] upgraded successfully");
	}

}
