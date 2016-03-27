package com.bexxkie.bkcp.util;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.classes.interfaces.ClassBase;

public class Formatter
implements Listener
{
	@EventHandler(priority=EventPriority.LOWEST)
	public void prefixes(AsyncPlayerChatEvent e)
	{
		if (e.getPlayer().hasPermission("bcp.color")) 
		{
			e.setFormat(((ClassBase)BkCP.onlinePlayers.get(e.getPlayer().getName())).getPrefix() +"<" + e.getPlayer().getDisplayName() + ">" + e.getMessage());
		} else {
			e.setFormat(((ClassBase)BkCP.onlinePlayers.get(e.getPlayer().getName())).getPrefix() + "<" + e.getPlayer().getDisplayName() + "> "+  e.getMessage().replaceAll("&", "§"));
		} 
	}
}


