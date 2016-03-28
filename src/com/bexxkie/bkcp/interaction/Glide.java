package com.bexxkie.bkcp.interaction;


import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;


import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.classes.interfaces.Flier;
import com.bexxkie.bkcp.util.RandInt;

public class Glide 
implements Listener
{
	public static boolean cool;
	public static Double intCool;
	public static Double lCost = BkCP.advCfg.getConfig().getDouble("flightCost");
	public static Double levCost = BkCP.advCfg.getConfig().getDouble("cFlightCost");
	public static Double fTime = BkCP.advCfg.getConfig().getDouble("flightTime");
	@SuppressWarnings("deprecation")
	@EventHandler
	public void fly(PlayerInteractEvent e)
	{
		//CreativeFlight
		if(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof Flier)
		{
			Player p = e.getPlayer();
			if(p.isSneaking()&&p.getItemInHand().getType().equals(Material.FEATHER)&&p.getItemInHand().hasItemMeta()&&p.getItemInHand().getItemMeta().hasDisplayName()&&p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("wings")&&p.getLevel()>=levCost)
			{
				p.setLevel((int) (p.getLevel()-levCost));
				if(p.getItemInHand().getAmount()>1)
				{
					p.getItemInHand().setAmount(p.getItemInHand().getAmount()-1);
				}
				else{
					p.setItemInHand(null);
				}

				((Flier)BkCP.onlinePlayers.get(p.getPlayer().getName())).setCool(((Flier)BkCP.onlinePlayers.get(p.getPlayer().getName())).getCool()+fTime);
				p.setAllowFlight(true);
				//setCanFly(p);
			}
			//train
			if(p.isSneaking()&&p.getItemInHand().getType().equals(Material.FEATHER)&&p.getItemInHand().hasItemMeta()&&p.getItemInHand().getItemMeta().hasDisplayName()&&p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("trainer"))
			{
				if(e.getAction()==Action.RIGHT_CLICK_BLOCK)
				{
					if(((Flier)BkCP.onlinePlayers.get(e.getPlayer().getName())).getEnergy()>10)
					{
						((Flier)BkCP.onlinePlayers.get(e.getPlayer().getName())).setEnergy(((Flier)BkCP.onlinePlayers.get(e.getPlayer().getName())).getEnergy()-2);
						((Flier)BkCP.onlinePlayers.get(e.getPlayer().getName())).showHud();
						e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDERDRAGON_FLAP, 1,RandInt.randInt(0, 3));
						e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation(), Effect.STEP_SOUND, e.getPlayer().getLocation().subtract(0,1,0).getBlock().getType());
						((Flier)BkCP.onlinePlayers.get(e.getPlayer().getName())).setFxp(1);
					}
				}
			}
			//standardFlight
			if(p.getItemInHand().getType().equals(Material.FEATHER)&&!p.getItemInHand().hasItemMeta())
			{
				if(e.getAction()==Action.RIGHT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_BLOCK)
				{
					for(String s: BkCP.flightMap.keySet())
					{
						if(BkCP.playerClass.getConfig().getString(p.getUniqueId().toString()+".class").equalsIgnoreCase(s))
						{
							if(BkCP.flightMap.get(s)==false)
							{	
								if(((Flier)BkCP.onlinePlayers.get(e.getPlayer().getName())).getEnergy()>2)
								{
									((Flier)BkCP.onlinePlayers.get(e.getPlayer().getName())).setEnergy(((Flier)BkCP.onlinePlayers.get(e.getPlayer().getName())).getEnergy()-.05);
									((Flier)BkCP.onlinePlayers.get(e.getPlayer().getName())).showHud();
									Vector v = e.getPlayer().getVelocity();
									Vector d = e.getPlayer().getLocation().getDirection();
									if(e.getPlayer().getLocation().getY()>115)
									{
										d.multiply(2.5);	
									}else if(e.getPlayer().getLocation().getBlockY()<50){
										d.multiply(.5);
									}else{
										d.multiply(1);
									}
									e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDERDRAGON_FLAP, 1, (float) (9*v.getY()+Math.random()));
									e.getPlayer().setVelocity(d);
									e.getPlayer().setFallDistance(0);
									e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN,1).getLocation(), Effect.STEP_SOUND, e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN,2).getType());
								}
							}
						}
					}
				}
			}
		}
	}
	@EventHandler
	public void doubleJump(PlayerToggleFlightEvent e)
	{
		if(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof Flier)
		{
			Player p = e.getPlayer();
			if(((Flier)BkCP.onlinePlayers.get(e.getPlayer().getName())).getCool()==0)
			{
				for(String s: BkCP.flightMap.keySet())
				{
					if(BkCP.playerClass.getConfig().getString(p.getUniqueId().toString()+".class").equalsIgnoreCase(s))
					{
						if(BkCP.flightMap.get(s)==false)
						{	
							if(p.isFlying()&&(!p.getGameMode().equals(GameMode.SURVIVAL)||!p.getGameMode().equals(GameMode.ADVENTURE)))
							{
								return;
							}
							e.setCancelled(true);
							p.sendMessage(BkCP.prefix+"type /bcClassInfo for class info!");
						}
					}
				}
			}
		}

		if(!(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof Flier))
		{
			if(e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)||e.getPlayer().getGameMode().equals(GameMode.ADVENTURE))
			{
				e.setCancelled(true);
			}
		}

	}
}
