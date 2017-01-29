package com.bexxkie.bkcp.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.bexxkie.bkcp.BkCP;

public class VeryifyIntegrity 
{

	private static List<String> configKeysDefault = new ArrayList<String>(); 
	private static List<String> advConfigKeysDefault = new ArrayList<String>();
	private static List<String> configKeys = new ArrayList<String>();
	private static List<String> advCfgKeys = new ArrayList<String>();

	public void getDefault()
	{
		if(configKeysDefault.isEmpty())
		{
			configKeysDefault.clear();
			configKeysDefault.addAll(BkCP.config_DefaultKeyCheck.getConfig().getKeys(true));
		}
		if(advConfigKeysDefault.isEmpty())
		{
			advConfigKeysDefault.clear();
			advConfigKeysDefault.addAll(BkCP.advCfg_DefaultKeyCheck.getConfig().getKeys(true));
		}
		if(configKeys.isEmpty())
		{
			configKeys.clear();
			configKeys.addAll(BkCP.config.getConfig().getKeys(true));
		}
		if(advCfgKeys.isEmpty())
		{
			advCfgKeys.clear();
			advCfgKeys.addAll(BkCP.advCfg.getConfig().getKeys(true));
		}


	}
	public void INIT()
	{
		getDefault();
		for(String key: BkCP.config.getConfig().getKeys(true))
		{
			/*if(!testFor(key, configKeysDefault))
			{
				//System.out.println(BkCP.prefix+"KEY MISMATCH::"+key+"--config");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED+"["+ChatColor.AQUA+"BkCP"+ChatColor.RED+"] KEY MISMATCH::"+ChatColor.AQUA+key+ChatColor.RED+"--config");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED+"["+ChatColor.AQUA+"BkCP"+ChatColor.RED+"]"+ChatColor.AQUA+" CONTACT DEVELOPER OR MANUALLY REPAIR THE KEY (CHECK configDefault(DO NOT TOUCH).yml FOR DEFAULT KEYS");
				//System.out.println(BkCP.prefix+"Seek dev help or manualy add the KEY");
			}else{
				//System.out.println(BkCP.prefix+"KEY CONFIRMED::"+key+"--config");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.BLUE+"["+ChatColor.LIGHT_PURPLE+"BkCP"+ChatColor.BLUE+"] KEY CONFIRMED::"+ChatColor.LIGHT_PURPLE+key+ChatColor.BLUE+"--config");
			}*/
		}
		for(String key :BkCP.config_DefaultKeyCheck.getConfig().getKeys(true))
		{
			if(!testFor(key, configKeys))
			{
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED+"["+ChatColor.AQUA+"BkCP"+ChatColor.RED+"] KEY MISMATCH::"+ChatColor.AQUA+key+ChatColor.RED+"--config");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED+"["+ChatColor.AQUA+"BkCP"+ChatColor.RED+"]"+ChatColor.AQUA+" CONTACT DEVELOPER OR MANUALLY REPAIR THE KEY (CHECK configDefault(DO NOT TOUCH).yml FOR DEFAULT KEYS");
			}else{
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.BLUE+"["+ChatColor.LIGHT_PURPLE+"BkCP"+ChatColor.BLUE+"] KEY CONFIRMED::"+ChatColor.LIGHT_PURPLE+key+ChatColor.BLUE+"--config");
			}
		}
		for(String key: BkCP.advCfg_DefaultKeyCheck.getConfig().getKeys(true))
		{
			if(!testFor(key,advCfgKeys))
			{
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED+"["+ChatColor.AQUA+"BkCP"+ChatColor.RED+"] KEY MISMATCH::"+ChatColor.AQUA+key+ChatColor.RED+"--advCfg");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED+"["+ChatColor.AQUA+"BkCP"+ChatColor.RED+"]"+ChatColor.AQUA+" CONTACT DEVELOPER OR MANUALLY REPAIR THE KEY (CHECK advCfgDefault(DO NOT TOUCH).yml FOR DEFAULT KEYS");
			}else{
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.BLUE+"["+ChatColor.LIGHT_PURPLE+"BkCP"+ChatColor.BLUE+"] KEY CONFIRMED::"+ChatColor.LIGHT_PURPLE+key+ChatColor.BLUE+"--advCfg");
			}
		}
		/*advConfigCheck
		for(String key: BkCP.advCfg.getConfig().getKeys(true))	
		{
			if(!testFor(key,advConfigKeysDefault))
			{
				//System.out.println(BkCP.prefix+"KEY MISMATCH::"+key+"--advCfg");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED+"["+ChatColor.AQUA+"BkCP"+ChatColor.RED+"] KEY MISMATCH::"+ChatColor.AQUA+key+ChatColor.RED+"--advCfg");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED+"["+ChatColor.AQUA+"BkCP"+ChatColor.RED+"]"+ChatColor.AQUA+" CONTACT DEVELOPER OR MANUALLY REPAIR THE KEY (CHECK advCfgDefault(DO NOT TOUCH).yml FOR DEFAULT KEYS");
				//System.out.println(BkCP.prefix+"Seek dev help or manualy add the KEY");
			}else{
				//System.out.println(BkCP.prefix+"KEY CONFIRMED::"+key+"--advCfg");
				Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.BLUE+"["+ChatColor.LIGHT_PURPLE+"BkCP"+ChatColor.BLUE+"] KEY CONFIRMED::"+ChatColor.LIGHT_PURPLE+key+ChatColor.BLUE+"--advCfg");
			}
		}*/
	}
	public boolean testFor(String KeyName, List<String> ConfigName)
	{
		if(ConfigName.contains(KeyName))
		{
			return true;
		}
		return false;
	}
}
