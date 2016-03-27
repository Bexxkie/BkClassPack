package com.bexxkie.bkcp.modules.gender;

import java.util.HashMap;
import java.util.Map;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


import com.bexxkie.bkcp.BkCP;

public class GenderMain
{
	public static Map<Player, ChatColor> genderMap = new HashMap<Player, ChatColor>();

	public static void setGender(Player p, ChatColor col)
	{
		genderMap.put(p, col);
		String gen="none";
		for(String s : BkCP.genCol.keySet())
		{
			if(col==BkCP.genCol.get(s))
			{
				gen = s; 
				break;
			}
		}
		BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".gender", gen);
		BkCP.playerClass.saveConfig();
		BkCP.playerClass.reloadConfig();
		displayGender(p);
	}

	public static void displayGender(Player p)
	{
		p.getPlayer().setPlayerListName(genderMap.get(p)+p.getDisplayName());
		p.setDisplayName(genderMap.get(p)+p.getDisplayName());
		p.sendMessage("test");
	}
}
