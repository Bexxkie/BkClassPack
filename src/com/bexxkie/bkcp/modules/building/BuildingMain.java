package com.bexxkie.bkcp.modules.building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.util.LocationIterator;	
import com.bexxkie.bkcp.util.ParticleEffect;
import com.bexxkie.bkcp.util.ParticleEffect.ParticleType;

import sun.security.util.DisabledAlgorithmConstraints;


public class BuildingMain implements Listener 
{

	public List<String> buildTools = new ArrayList<String>();
	public Map<String, String> tapes = new HashMap<String, String>();
	public Boolean isRunning = false;
	@EventHandler
	public void usage(PlayerInteractEvent e)
	{
		if(buildTools.isEmpty())
		{
			buildTools.clear();
			buildTools.add("tape measure");
			buildTools.add("paint bucket");
		}
		if(!isRunning)
		{
			System.out.println("run");
			isRunning=true;
			run();
		}
		EquipmentSlot es = e.getHand();
		if (es.equals(EquipmentSlot.HAND)) 
		{
			Player p = e.getPlayer();
			ItemStack heldItem = p.getInventory().getItemInMainHand();
			Material heldItemType = p.getInventory().getItemInMainHand().getType();
			if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			{
				if(!heldItemType.equals(Material.WATCH))
				{
					return;
				}
				String pKey = e.getPlayer().getName().toLowerCase();
				if(buildTools.contains(heldItem.getItemMeta().getDisplayName().toLowerCase()))
				{
					String toolName = heldItem.getItemMeta().getDisplayName().toLowerCase();
					//get the tool being used
					if(toolName.equalsIgnoreCase("tape measure"))
					{
						//Block clicked = e.getClickedBlock();
						Location loc = e.getClickedBlock().getLocation();
						//Bukkit.broadcastMessage("pop'd");
						if(tapes.containsKey(pKey+"x")&&tapes.containsKey(pKey+"y")&&tapes.containsKey(pKey+"z")&&tapes.containsKey(pKey+"b"))
						{
							/*if(tapes.get(pKey).equals(loc))
								{
									Bukkit.broadcastMessage("same");
								}*/
							ParticleEffect reddust = new ParticleEffect(ParticleType.REDSTONE, 0, 20, .3);
							Integer lsx = Integer.decode(tapes.get(pKey+"x"));
							Integer lsy = Integer.decode(tapes.get(pKey+"y"));
							Integer lsz = Integer.decode(tapes.get(pKey+"z"));
							Location loS = new Location(p.getWorld(), lsx,lsy,lsz);
							Vector dir = e.getClickedBlock().getLocation().subtract(loS).multiply(-1).toVector();
							int dis = (int) e.getClickedBlock().getLocation().distance(loS);
							Material mat = Material.valueOf(tapes.get(pKey+"b"));

							LocationIterator blocksToAdd = new LocationIterator(p.getWorld(), e.getClickedBlock().getLocation().toVector(), dir, 0, dis);
							Location blockToAdd;
							while(blocksToAdd.hasNext()) 
							{
								blockToAdd = blocksToAdd.next().getBlock().getLocation();
								try {
									reddust.sendToLocation(blockToAdd.add(0.5,.5,.5));
								} catch (Exception ex) {
									ex.printStackTrace();
								}


							}
							p.sendMessage(BkCP.prefix+dis+"m");
							loS.getBlock().setType(mat);
							tapes.remove(pKey+"x");
							tapes.remove(pKey+"y");
							tapes.remove(pKey+"z");
							tapes.remove(pKey+"b");
							tapes.remove(pKey+"e");
							//playEffect(loc, p);
							//p.sendMessage(BkCP.prefix+"Locations Cleared");
							return;
						}
						tapes.put(pKey+"x", loc.getBlockX()+"");
						tapes.put(pKey+"y", loc.getBlockY()+"");
						tapes.put(pKey+"z", loc.getBlockZ()+"");
						tapes.put(pKey+"e", "run");
						//Integer locx = Integer.decode(tapes.get(pKey+"x"));
						//Integer locy = Integer.decode(tapes.get(pKey+"y"));
						//Integer locz = Integer.decode(tapes.get(pKey+"z"));
						//Location locS = new Location(p.getWorld(), locx,locy,locz);
						p.sendMessage(BkCP.prefix+"location set");
						Material mat = e.getClickedBlock().getType();
						tapes.put(pKey+"b", mat.toString());
						//playEffect(e.getClickedBlock().getLocation(), p);

					}
				}
			}
			if(e.getAction().equals(Action.LEFT_CLICK_BLOCK))
			{
				if(!heldItemType.equals(Material.BUCKET))
				{
					return;
				}
				ParticleEffect reddust = new ParticleEffect(ParticleType.PORTAL, 0, 20, .3);
				String toolName = heldItem.getItemMeta().getDisplayName().toLowerCase();
				if(toolName.equalsIgnoreCase("paint bucket"))
				{
					Location loc = e.getClickedBlock().getLocation();
					Material mat = e.getClickedBlock().getType();
					Material reMat = p.getInventory().getItemInOffHand().getType();
					if(reMat.isBlock()&&reMat!=Material.AIR)
					{
						if(reMat!=mat)
						{
							//loc.getBlock().setType(reMat);
							for(ItemStack item :p.getInventory().getContents())
							{
								if(item!=null){
									if(item.getType().equals(reMat))
									{
										if(item.getAmount()==1)
										{
											item.setType(mat);
										}
										else
										{
											item.setAmount(item.getAmount()-1);
											p.getInventory().addItem(new ItemStack(mat));
										}
										loc.getBlock().setType(reMat);
										reddust.sendToLocation(loc.getBlock().getLocation().add(0.5,1,.5));
										loc.getBlock().setType(reMat);
										break;
									}
								}
							}

						}
						/*if(p.getInventory().getItemInOffHand().getAmount()<=1)
							{
								//p.getInventory().getItemInOffHand().setType(Material.AIR);
								//p.getInventory().remove(p.getInventory().getItemInOffHand());
								p.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
							}else{
								p.getInventory().getItemInOffHand().setAmount(p.getInventory().getItemInOffHand().getAmount()-1);
							}*/
						//p.getInventory().addItem(new ItemStack(mat));

						//}
					}

				}
			}

		}
	}
	@EventHandler
	public void playerLeave(PlayerQuitEvent e)
	{
		String[] vars = {"x","y","z","e","b"};
		Player p = e.getPlayer();
		for(String s :vars)
		{
			if(tapes.containsKey(p.getName()+s))
			{
				tapes.remove(p.getName().toLowerCase()+s);
				t.cancel();
				isRunning= false;
			}

		}
	}
	public Timer t = new Timer();
	public void run()
	{
		t.schedule(new TimerTask() 
		{
			@Override
			public void run() {
				String pKey = null;
				ParticleEffect reddust = new ParticleEffect(ParticleType.PORTAL, .1, 30, 0);
				for(Player p : Bukkit.getOnlinePlayers())
				{
					pKey = p.getName();
					if(tapes.containsKey(pKey+"e"))
					{
						Integer x = Integer.decode(tapes.get(pKey+"x"));
						Integer y = Integer.decode(tapes.get(pKey+"y"));
						Integer z = Integer.decode(tapes.get(pKey+"z"));
						tapes.get(pKey+"e");
						Location loc = new Location(p.getWorld(),x,y,z);
						reddust.sendToLocation(loc.getBlock().getLocation().add(0.5,1,.5));
					}
				}
			}
		}, 0, 1000);
	}

}
