package com.bexxkie.bkcp.interaction;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.classes.Dragon;
import com.bexxkie.bkcp.interaction.spells.DragonFire;

public class DragonFlameUse 
implements Listener
{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void UseFire(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		if((BkCP.onlinePlayers.get(p.getName())instanceof Dragon)
				&&(p.getItemInHand().getType().equals(Material.BLAZE_ROD)))
		{
			if(e.getAction() == (Action.RIGHT_CLICK_AIR)||e.getAction() == (Action.RIGHT_CLICK_BLOCK))
			{
				DragonFire.breath(p, ((Dragon)BkCP.onlinePlayers.get(p.getName())));
			}
		}
	}
}
