package com.bexxkie.bkcp.interaction;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.classes.Changeling;


public class DisguiseUtil 
implements Listener
{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void cycle_use_disguise(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		if(BkCP.onlinePlayers.get(p.getName())instanceof Changeling)
		{
			if(p.getItemInHand().getType().equals(Material.LEATHER))
			{
				if(e.getAction() == (Action.LEFT_CLICK_AIR)||e.getAction() == (Action.LEFT_CLICK_BLOCK))
				{
					((Changeling)BkCP.onlinePlayers.get(e.getPlayer().getName())).cycleDisguise();
				}else if(e.getAction() == (Action.RIGHT_CLICK_AIR)||e.getAction() == (Action.RIGHT_CLICK_BLOCK))
				{
					if(((Changeling)BkCP.onlinePlayers.get(e.getPlayer().getName())).getDisguised()==false)
					{
						((Changeling)BkCP.onlinePlayers.get(e.getPlayer().getName())).disguise(p, EntityType.fromName(((Changeling)BkCP.onlinePlayers.get(e.getPlayer().getName())).currentDisguise().toUpperCase()));
					}else
					{
						((Changeling)BkCP.onlinePlayers.get(e.getPlayer().getName())).undisguise(p);
					}
				}
			}
		}
	}

}