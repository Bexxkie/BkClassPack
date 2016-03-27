package com.bexxkie.bkcp.interaction.spells;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.classes.Dragon;
import com.bexxkie.bkcp.modules.pvpControl.PvpControlMain;
import com.bexxkie.bkcp.util.LocationIterator;
import com.bexxkie.bkcp.util.RandInt;
import com.bexxkie.bkcp.util.particleeffect.*;

public class DragonFire 
{
	public static void breath(Player p, Dragon d)
	{
		if(((Dragon)BkCP.onlinePlayers.get(p.getName())).getEnergy()>5){
			((Dragon)BkCP.onlinePlayers.get(p.getName())).setEnergy(((Dragon)BkCP.onlinePlayers.get(p.getName())).getEnergy()-5);
			Location locP = p.getEyeLocation().add(0, Math.random()/2, 0);

			LocationIterator blocksToAdd = new LocationIterator(locP.getWorld(), locP.toVector(), locP.getDirection(), 0.2, 6);
			Location blockToAdd;
			ParticleEffect.FLAME.display(p.getLocation().getDirection(),(float) 0.1, 2, locP, 7);
			ParticleEffect.FLAME.display(p.getLocation().getDirection(),(float) 0.2, 2, locP.add(-Math.random(), -Math.random(), Math.random()), 7);
			ParticleEffect.FLAME.display(p.getLocation().getDirection(),(float) 0.1, 2, locP.add(-Math.random(), -Math.random(), -Math.random()), 7);
			ParticleEffect.FLAME.display(p.getLocation().getDirection(),(float) 0.3, 2, locP.add(Math.random(), -Math.random(), -Math.random()), 7);
			ParticleEffect.FLAME.display(p.getLocation().getDirection(),(float) 0.1, 2, locP.add(Math.random(), -Math.random(), Math.random()), 7);
			p.getWorld().playSound(locP, Sound.BLOCK_FIRE_AMBIENT, (float) RandInt.randDouble(0.1, 4.0), (float) RandInt.randDouble(0.1, 5.0));
			double rad = 2.5;
			while(blocksToAdd.hasNext()) {
				blockToAdd = blocksToAdd.next().getBlock().getLocation();
				if(!p.getNearbyEntities(6, 6, 6).isEmpty())
				{
					for(Entity entity : p.getNearbyEntities(6, 6, 6)) 
					{
						int dam = (int) p.getLocation().distance(entity.getLocation());
						if(dam >0)
						{
							dam = dam+1*dam;
						}else{}
						if(entity.getLocation().distance(blockToAdd)<=rad)
						{
							if(entity instanceof Player)
							{
								Player player = (Player) entity;
								if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
								{
									
								}
								else{
									double dama = 10;
									player.damage(dama-dam+Math.random()*2);
									player.setFireTicks(100-dam);
								}
							}
							if((entity instanceof Damageable)&&!(entity instanceof Player))
							{
								double dama = 10;
								((Damageable) entity).damage(dama-dam+Math.random()*2);
								entity.setFireTicks(100-dam);
							}
						}
					}
				}
			}
		}
	}

}
