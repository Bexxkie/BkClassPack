package com.bexxkie.bkcp.interaction;


import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;


public class TntRainCancel implements Listener
{
	@EventHandler
	public void cancelTNT(ExplosionPrimeEvent e)
	{
		if(e.getEntity().getCustomName()!=null)
		{
			if(e.getEntity().getType().equals(EntityType.PRIMED_TNT))
			{
				TNTPrimed tnt = (TNTPrimed)e.getEntity();
				if(tnt.getCustomName().equalsIgnoreCase("tntRain"))
				{
					Location loc = tnt.getLocation();
					e.setRadius(0.0f);
					loc.getWorld().createExplosion(loc, 0.0F);
					e.getEntity().remove();
					e.setCancelled(true);
				}

			}
			if(e.getEntity().getType().equals(EntityType.CREEPER))
			{
				Creeper creeper = (Creeper)e.getEntity();
				if(creeper.getCustomName().equalsIgnoreCase("creeperRain"))
				{
					Location loc = creeper.getLocation();
					e.setRadius(0.0f);
					loc.getWorld().createExplosion(loc, 0.0F);
					e.getEntity().remove();
					e.setCancelled(true);
				}
			}
		}
	}
	@EventHandler
	public void cancelSandFall(EntityChangeBlockEvent e)
	{
		if(e.getEntity().getCustomName()!=null)
		{
			if(e.getEntity().getType().equals(EntityType.FALLING_BLOCK))
			{
				if(e.getEntity().getCustomName().equalsIgnoreCase("thrownCake"))
				{
					e.setCancelled(true);
				}
			}
		}
	}
}