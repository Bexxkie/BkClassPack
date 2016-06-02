package com.bexxkie.bkcp.modules.pvpControl;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PvpControlMain 
implements Listener
{
	public static Map<Player, Boolean> pvpList = new HashMap<Player, Boolean>();

	@EventHandler
	public void takeDamage(EntityDamageByEntityEvent e)
	{
		if(e.getEntity() instanceof Player&&e.getDamager() instanceof Player)
		{
			Player player = (Player)e.getEntity();
			Entity damager = e.getDamager();
			if(damager instanceof Player)
			{
				Player dplayer = (Player) damager;
				if(pvpList.containsKey(dplayer))
				{
					if(pvpList.get(dplayer)==false)
					{
						e.setCancelled(true);
					}
				}
			}
			if(pvpList.containsKey(player))
			{
				if(pvpList.get(player)==false)
				{
					e.setCancelled(true);
				}
			}	
		}
	}
}
