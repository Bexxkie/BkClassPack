package com.bexxkie.bkcp.interaction;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.DisplaySlot;


import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.classes.Alicorn;
import com.bexxkie.bkcp.classes.Changeling;
import com.bexxkie.bkcp.classes.Draconequus;
import com.bexxkie.bkcp.classes.Dragon;
import com.bexxkie.bkcp.classes.Earth;
import com.bexxkie.bkcp.classes.Griffon;
import com.bexxkie.bkcp.classes.Pegasus;
import com.bexxkie.bkcp.classes.TimberWolf;
import com.bexxkie.bkcp.classes.Unicorn;
import com.bexxkie.bkcp.classes.interfaces.ClassBase;
import com.bexxkie.bkcp.classes.interfaces.EnergyUser;
import com.bexxkie.bkcp.classes.interfaces.Flier;
import com.bexxkie.bkcp.classes.interfaces.MagicUser;
import com.bexxkie.bkcp.util.ClassBooks;

public class ClassAssign implements Listener
{
	@EventHandler
	public void selectClass(PlayerInteractEvent e)
	{
		String classname = "";
		if ((e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&&((e.getClickedBlock().getType() == Material.WALL_SIGN) 
						|| (e.getClickedBlock().getType() == Material.SIGN_POST)))
		{
			String sign = ((Sign)e.getClickedBlock().getState()).getLine(0);
			Player p = e.getPlayer();
			if(Arrays.asList(BkCP.classNames).contains(sign.replace("[", "").replace("]", "")))
			{
				if(BkCP.config.getConfig().getStringList("vip").contains(sign.replace("[", "").replace("]", ""))
						&&!p.hasPermission("bcp.vip"))
				{
					e.getPlayer().sendMessage(BkCP.prefix+" You must be VIP to use this class.");
					return;

				}else if((!BkCP.config.getConfig().getStringList("vip").contains(sign.replace("[", "").replace("]", "")))
						||p.hasPermission("bcp.vip"))
				{
					if (sign.equals(BkCP.advCfg.getConfig().getString("SignText.Unicorn"))) 
					{
						if (BkCP.config.getConfig().getBoolean("Class-Options.Unicorn-Enabled"))
						{
							String[] spells = {"flameI","frostI","blink","healI","shadowBeamI"};
							//CLASSID 1
							classname = "Unicorn";
							p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString(), null);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".mana", BkCP.advCfg.getConfig().getDouble("Classes.Unicorn.maxMana"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".maxMana", BkCP.advCfg.getConfig().getDouble("Classes.Unicorn.maxMana"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".level", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".branch", "default");
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".currentSpell", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".spells", spells);

							BkCP.playerClass.saveConfig();
							BkCP.playerClass.reloadConfig();
							BkCP.onlinePlayers.remove(e.getPlayer().getName());
							addExtensions(e.getPlayer());
							Unicorn newClass = new Unicorn(e.getPlayer().getName());
							newClass.setPrefix(BkCP.advCfg.getConfig().getString("Prefixes.Unicorn.default").replaceAll("&", "§"));
							//newClass.refreshMana(1);
							BkCP.onlinePlayers.put(e.getPlayer().getName(), newClass);	
							e.getPlayer().sendMessage(BkCP.prefix+ChatColor.AQUA + "You are now a Unicorn!");
							BkCP.spawnPlayer(e.getPlayer(), "Unicorn");

						}
					}
					if (sign.equals(BkCP.advCfg.getConfig().getString("SignText.Pegasus"))) 
					{
						if (BkCP.config.getConfig().getBoolean("Class-Options.Pegasus-Enabled"))
						{
							//CLASSID 2
							classname = "Pegasus";
							p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString(), null);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", BkCP.advCfg.getConfig().getDouble("Classes.Pegasus.maxEnergy"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", BkCP.advCfg.getConfig().getDouble("Classes.Pegasus.maxEnergy"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".level", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".fxp", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".ftime", 0);
							BkCP.playerClass.saveConfig();
							BkCP.playerClass.reloadConfig();
							BkCP.onlinePlayers.remove(e.getPlayer().getName());
							addExtensions(e.getPlayer());
							Pegasus newClass = new Pegasus(e.getPlayer().getName());
							newClass.setPrefix(BkCP.advCfg.getConfig().getString("Prefixes.Pegasus.default").replaceAll("&", "§"));
							//newClass.refreshEnergy(2);

							BkCP.onlinePlayers.put(e.getPlayer().getName(), newClass);	
							e.getPlayer().sendMessage(BkCP.prefix+ChatColor.AQUA + "You are now a Pegasus!");
							BkCP.spawnPlayer(e.getPlayer(), "Pegasus");

						}
					}
					if (sign.equals(BkCP.advCfg.getConfig().getString("SignText.Earth"))) 
					{
						if (BkCP.config.getConfig().getBoolean("Class-Options.Earth-Enabled"))
						{
							//CLASSID 3
							classname = "Earth";
							p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString(), null);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", BkCP.advCfg.getConfig().getDouble("Classes.Earth.maxEnergy"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", BkCP.advCfg.getConfig().getDouble("Classes.Earth.maxEnergy"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".level", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".branch", "default");

							BkCP.playerClass.saveConfig();
							BkCP.playerClass.reloadConfig();
							BkCP.onlinePlayers.remove(e.getPlayer().getName());
							addExtensions(e.getPlayer());
							Earth newClass = new Earth(e.getPlayer().getName());
							newClass.setPrefix(BkCP.advCfg.getConfig().getString("Prefixes.Earth.default").replaceAll("&", "§"));
							//newClass.refreshEnergy(2);

							BkCP.onlinePlayers.put(e.getPlayer().getName(), newClass);	
							e.getPlayer().sendMessage(BkCP.prefix+ChatColor.AQUA + "You are now an Earth pony!");
							BkCP.spawnPlayer(e.getPlayer(), "Earth");

						}
					}
					if (sign.equals(BkCP.advCfg.getConfig().getString("SignText.Alicorn"))) 
					{
						if (BkCP.config.getConfig().getBoolean("Class-Options.Alicorn-Enabled"))
						{
							//CLASSID 4
							classname = "Alicorn";
							p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString(), null);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", BkCP.advCfg.getConfig().getDouble("Classes.Alicorn.maxEnergy"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".mana", BkCP.advCfg.getConfig().getDouble("Classes.Alicorn.maxMana"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", BkCP.advCfg.getConfig().getDouble("Classes.Alicorn.maxEnergy"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".maxMana", BkCP.advCfg.getConfig().getDouble("Classes.Alicorn.maxMana"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".flevel", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".mlevel", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".fxp", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".ftime", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".branch", "default");
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".currentSpell", 0);
							BkCP.playerClass.saveConfig();
							BkCP.playerClass.reloadConfig();
							BkCP.onlinePlayers.remove(e.getPlayer().getName());
							addExtensions(e.getPlayer());
							Alicorn newClass = new Alicorn(e.getPlayer().getName());
							newClass.setPrefix(BkCP.advCfg.getConfig().getString("Prefixes.Alicorn.default").replaceAll("&", "§"));
							//newClass.refreshEnergy(2);

							BkCP.onlinePlayers.put(e.getPlayer().getName(), newClass);	
							e.getPlayer().sendMessage(BkCP.prefix+ChatColor.AQUA + "You are now an Alicorn!");
							BkCP.spawnPlayer(e.getPlayer(), "Alicorn");

						}
					}
					if (sign.equals(BkCP.advCfg.getConfig().getString("SignText.Changeling"))) 
					{
						if (BkCP.DisguiseLibsEnabled==true&&BkCP.config.getConfig().getBoolean("Class-Options.Changeling-Enabled"))
						{
							//CLASSID 5
							classname = "Changeling";
							p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString(), null);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", BkCP.advCfg.getConfig().getDouble("Classes.Changeling.maxEnergy"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".mana", BkCP.advCfg.getConfig().getDouble("Classes.Changeling.maxMana"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", BkCP.advCfg.getConfig().getDouble("Classes.Changeling.maxEnergy"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".maxMana", BkCP.advCfg.getConfig().getDouble("Classes.Changeling.maxMana"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".flevel", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".mlevel", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".fxp", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".ftime", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".branch", "default");
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".disguises",BkCP.advCfg.getConfig().getDouble("Classes.Changeling.disguises.default"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".currentSpell", 0);
							BkCP.playerClass.saveConfig();
							BkCP.playerClass.reloadConfig();
							BkCP.onlinePlayers.remove(e.getPlayer().getName());
							addExtensions(e.getPlayer());
							Changeling newClass = new Changeling(e.getPlayer().getName());
							newClass.setPrefix(BkCP.advCfg.getConfig().getString("Prefixes.Changeling.default").replaceAll("&", "§"));
							BkCP.onlinePlayers.put(e.getPlayer().getName(), newClass);	
							e.getPlayer().sendMessage(BkCP.prefix+ChatColor.AQUA + "You are now a Changeling!");
							BkCP.spawnPlayer(e.getPlayer(), "Changeling");

						}
					}
					if (sign.equals(BkCP.advCfg.getConfig().getString("SignText.Dragon"))) 
					{
						if (BkCP.config.getConfig().getBoolean("Class-Options.Dragon-Enabled"))
						{
							//classID 6
							classname = "Dragon";
							p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);	
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString(), null);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", BkCP.advCfg.getConfig().getDouble("Classes.Dragon.maxEnergy"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", BkCP.advCfg.getConfig().getDouble("Classes.Dragon.maxEnergy"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".fxp", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".ftime", 0);
							BkCP.playerClass.saveConfig();
							BkCP.playerClass.reloadConfig();
							BkCP.onlinePlayers.remove(e.getPlayer().getName());
							addExtensions(e.getPlayer());
							Dragon newClass = new Dragon(e.getPlayer().getName());
							newClass.setPrefix(BkCP.advCfg.getConfig().getString("Prefixes.Dragon.default").replaceAll("&", "§"));
							BkCP.onlinePlayers.put(e.getPlayer().getName(), newClass);	
							e.getPlayer().sendMessage(BkCP.prefix+ChatColor.AQUA + "You are now a Dragon!");
							BkCP.spawnPlayer(e.getPlayer(), "Dragon");
						}
					}
					if (sign.equals(BkCP.advCfg.getConfig().getString("SignText.TimberWolf"))) 
					{
						if (BkCP.config.getConfig().getBoolean("Class-Options.TimberWolf-Enabled"))
						{
							//classID 6
							classname = "TimberWolf";
							p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);	
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString(), null);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", BkCP.advCfg.getConfig().getDouble("Classes.TimberWolf.maxEnergy"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", BkCP.advCfg.getConfig().getDouble("Classes.TimberWolf.maxEnergy"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".level", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".pack", "null");
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".packPref", "");
							BkCP.playerClass.saveConfig();
							BkCP.playerClass.reloadConfig();
							BkCP.onlinePlayers.remove(e.getPlayer().getName());
							addExtensions(e.getPlayer());
							TimberWolf newClass = new TimberWolf(e.getPlayer().getName());
							newClass.setPrefix(BkCP.advCfg.getConfig().getString("Prefixes.TimberWolf.default").replaceAll("&", "§"));
							BkCP.onlinePlayers.put(e.getPlayer().getName(), newClass);	
							e.getPlayer().sendMessage(BkCP.prefix+ChatColor.AQUA + "You are now a TimberWolf!");
							BkCP.spawnPlayer(e.getPlayer(), "TimberWolf");
						}
					}
					if (sign.equals(BkCP.advCfg.getConfig().getString("SignText.Griffon"))) 
					{
						if (BkCP.config.getConfig().getBoolean("Class-Options.Griffon-Enabled"))
						{
							//CLASSID 2
							classname = "Griffon";
							p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString(), null);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", BkCP.advCfg.getConfig().getDouble("Classes.Griffon.maxEnergy"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", BkCP.advCfg.getConfig().getDouble("Classes.Griffon.maxEnergy"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".level", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".fxp", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".ftime", 0);
							BkCP.playerClass.saveConfig();
							BkCP.playerClass.reloadConfig();
							BkCP.onlinePlayers.remove(e.getPlayer().getName());
							addExtensions(e.getPlayer());
							Griffon newClass = new Griffon(e.getPlayer().getName());
							newClass.setPrefix(BkCP.advCfg.getConfig().getString("Prefixes.Griffon.default").replaceAll("&", "§"));

							BkCP.onlinePlayers.put(e.getPlayer().getName(), newClass);	
							e.getPlayer().sendMessage(BkCP.prefix+ChatColor.AQUA + "You are now a Griffon!");
							BkCP.spawnPlayer(e.getPlayer(), "Griffon");

						}
					}
					if (sign.equals(BkCP.advCfg.getConfig().getString("SignText.Draconequus"))) 
					{
						if (BkCP.config.getConfig().getBoolean("Class-Options.Draconequus-Enabled"))
						{
							List<String> spells = BkCP.BranchSpells.getConfig().getStringList("dc.default.spells");
							//CLASSID 9
							classname = "Draconequus";
							p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString(), null);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".class", classname);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".energy", BkCP.advCfg.getConfig().getDouble("Classes.Draconequus.maxEnergy"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".mana", BkCP.advCfg.getConfig().getDouble("Classes.Draconequus.maxMana"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".maxEnergy", BkCP.advCfg.getConfig().getDouble("Classes.Draconequus.maxEnergy"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".maxMana", BkCP.advCfg.getConfig().getDouble("Classes.Draconequus.maxMana"));
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".flevel", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".mlevel", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".fxp", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".ftime", 0);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".spells", spells);
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".branch", "default");
							BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".currentSpell", 0);

							BkCP.playerClass.saveConfig();
							BkCP.playerClass.reloadConfig();
							BkCP.onlinePlayers.remove(e.getPlayer().getName());
							addExtensions(e.getPlayer());
							Draconequus newClass = new Draconequus(e.getPlayer().getName());
							newClass.setPrefix(BkCP.advCfg.getConfig().getString("Prefixes.Draconequus.default").replaceAll("&", "§"));
							//newClass.refreshEnergy(2);

							BkCP.onlinePlayers.put(e.getPlayer().getName(), newClass);	
							e.getPlayer().sendMessage(BkCP.prefix+ChatColor.AQUA + "You are now an Draconequus!");
							BkCP.spawnPlayer(e.getPlayer(), "Draconequus");

						}
					}
					e.getPlayer().sendMessage(BkCP.prefix+"run bcp classInfo to get information on your class.");
					//ClassBooks.classBooks(e.getPlayer(), classname);
					clearGuild(e.getPlayer());
					changeSetup(e.getPlayer());
				}
			}
		}

	}
	public void clearGuild(Player p)
	{
		//Clear from timberWolf packs
		try{
			for(String gid : BkCP.guilds_packs.getConfig().getConfigurationSection("Twol").getKeys(false))
			{
				if(!gid.equalsIgnoreCase("null")&&gid!=null)
				{
					String leader = BkCP.guilds_packs.getConfig().getString("Twol."+gid+".leader");
					if(leader.equals(p.getUniqueId().toString()))
					{
						for(OfflinePlayer op : Bukkit.getOfflinePlayers())
						{
							if(BkCP.playerClass.getConfig().contains(op.getUniqueId().toString()))
							{
								if(BkCP.playerClass.getConfig().getString(op.getUniqueId().toString()+".class").equalsIgnoreCase("TimberWolf"))
								{
									if(BkCP.playerClass.getConfig().getString(op.getUniqueId().toString()+".pack").equalsIgnoreCase(gid))
									{
										BkCP.playerClass.getConfig().set(op.getUniqueId().toString()+".pack", "null");
										BkCP.playerClass.getConfig().set(op.getUniqueId().toString()+".packPref", "");
										BkCP.playerClass.saveConfig();
										BkCP.playerClass.reloadConfig();
									}
								}
							}
						}
						BkCP.guilds_packs.getConfig().set("Twol."+gid, null);
						BkCP.guilds_packs.saveConfig();
						BkCP.guilds_packs.reloadConfig();
					}
					if(BkCP.guilds_packs.getConfig().getStringList("Twol."+gid+".members").contains(p.getUniqueId().toString()))
					{
						List<String> uidList = BkCP.guilds_packs.getConfig().getStringList("Twol."+gid+".members");
						uidList.remove(p.getUniqueId().toString());
						BkCP.guilds_packs.getConfig().set("Twol."+gid+".members",uidList);
						BkCP.guilds_packs.saveConfig();
						BkCP.guilds_packs.reloadConfig();
					}
				}
			}
		}catch(NullPointerException e)
		{
			System.out.print("bcp>clearGuild:"+e.getCause());
		}
	}
	//unused (experimental, in progress)
	public void addExtensions(Player p)
	{
		if (BkCP.econEnabled==true)
		{
			if(BkCP.playerClass.getConfig().contains(p.getUniqueId().toString()))
			{
				BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".currency.gold",100);
				BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".currency.gem",10);
				BkCP.playerClass.saveConfig();
				BkCP.playerClass.reloadConfig();
			}
		}
	}
	/**
	 * onClassChange fix any flight issues
	 * @param p player to edit
	 */
	public void changeSetup(Player p)
	{
		if(p.getPlayer().getGameMode()!=GameMode.SURVIVAL||p.getPlayer().getGameMode()!=GameMode.ADVENTURE)
		{
			if(BkCP.onlinePlayers.get(p.getName())instanceof Flier)
			{
				p.getLocation().getWorld().dropItem(p.getLocation(), new ItemStack(Material.FEATHER, 1));
				p.getPlayer().setAllowFlight(true);
			}
			if(BkCP.onlinePlayers.get(p.getName())instanceof MagicUser)
			{
				p.getLocation().getWorld().dropItem(p.getLocation(), new ItemStack(Material.STICK, 1));
				p.getPlayer().setAllowFlight(false);
				if(!(BkCP.onlinePlayers.get(p.getName())instanceof Changeling))
				{ClassBooks.SpellBook(p);}
			}
			if(BkCP.onlinePlayers.get(p.getName())instanceof Changeling)
			{
				p.getLocation().getWorld().dropItem(p.getLocation(), new ItemStack(Material.LEATHER, 1));
				p.getPlayer().setAllowFlight(true);
			}
			if(BkCP.onlinePlayers.get(p.getName())instanceof Dragon)
			{
				p.getLocation().getWorld().dropItem(p.getLocation(), new ItemStack(Material.BLAZE_ROD, 1));
				p.getPlayer().setAllowFlight(true);
			}
		}
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		addPlayer(p);
		if (!e.getPlayer().hasPlayedBefore()) 
		{
			BkCP.spawnPlayer(e.getPlayer(), "");
		}
	}
	@EventHandler
	public void onLeave(PlayerQuitEvent e)
	{
		//Player p = e.getPlayer();
		if(BkCP.onlinePlayers.containsKey(e.getPlayer().getName()))
		{
			if(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof Flier)
			{
				((Flier)BkCP.onlinePlayers.get(e.getPlayer().getName())).stop();
				BkCP.onlinePlayers.remove(e.getPlayer().getName());	
				return;
			}
			if(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof EnergyUser)
			{
				((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).stop();
				BkCP.onlinePlayers.remove(e.getPlayer().getName());	
				return;
			}
			if(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof MagicUser)
			{
				((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).stop();
				BkCP.onlinePlayers.remove(e.getPlayer().getName());	
				return;
			}
		}
	}
	public static void addPlayer(Player p)
	{
		String classname = BkCP.playerClass.getConfig().getString(p.getPlayer().getUniqueId().toString()+".class");	
		if(classname==null)
		{
			ClassBase cb = new ClassBase(p.getName());
			BkCP.onlinePlayers.put(p.getName(), cb);
			return;
		}
		if(!BkCP.playerClass.getConfig().contains(p.getUniqueId().toString()+".book")||!BkCP.playerClass.getConfig().getBoolean(p.getUniqueId().toString()+".book"))
		{
			ClassBooks.classBooks(p, BkCP.playerClass.getConfig().getString(p.getUniqueId().toString()+".class"));
		}
		switch(classname)
		{
		case"Unicorn":
			Unicorn uc = new Unicorn(p.getName());
			BkCP.onlinePlayers.put(p.getName(), uc);
			uc.refreshMana(1);
			p.setAllowFlight(false);
			break;
		case"Pegasus":
			Pegasus peg = new Pegasus(p.getName());
			BkCP.onlinePlayers.put(p.getName(), peg);
			peg.refreshEnergy(2);
			peg.isGlide();
			p.setAllowFlight(true);
			break;
		case"Earth":
			Earth ec = new Earth(p.getName());
			BkCP.onlinePlayers.put(p.getName(), ec);
			ec.refreshEnergy(3);
			p.setAllowFlight(false);
			break;
		case "Alicorn":
			Alicorn ac = new Alicorn(p.getName());
			BkCP.onlinePlayers.put(p.getName(), ac);
			ac.refreshEnergy(4);
			ac.refreshMana(4);
			ac.isGlide();
			p.setAllowFlight(true);
			break;
		case "Changeling":
			if(BkCP.DisguiseLibsEnabled==true)
			{
				Changeling ch = new Changeling(p.getName());
				BkCP.onlinePlayers.put(p.getName(), ch);
				ch.refreshEnergy(5);
				ch.refreshMana(5);
				ch.isGlide();
				p.setAllowFlight(true);
				break;
			}else{
				p.sendMessage(BkCP.prefix+"Changeling is disabled, libs disguises not found.");
				ClassBase cb = new ClassBase(p.getName());
				BkCP.onlinePlayers.put(p.getName(), cb);
				//setTo defaultClass
			}
			break;
		case "Dragon":
			Dragon dg = new Dragon(p.getName());
			BkCP.onlinePlayers.put(p.getName(), dg);
			dg.refreshEnergy(6);
			dg.isGlide();
			p.setAllowFlight(true);
			break;
		case"TimberWolf":
			TimberWolf tw = new TimberWolf(p.getName());
			BkCP.onlinePlayers.put(p.getName(), tw);
			tw.refreshEnergy(7);
			p.setAllowFlight(false);
			break;
		case"Griffon":
			Griffon grif = new Griffon(p.getName());
			BkCP.onlinePlayers.put(p.getName(), grif);
			grif.refreshEnergy(8);
			grif.isGlide();
			p.setAllowFlight(true);
			break;
		case "Draconequus":
			Draconequus dc = new Draconequus(p.getName());
			BkCP.onlinePlayers.put(p.getName(), dc);
			dc.refreshEnergy(9);
			dc.refreshMana(9);
			dc.isGlide();
			p.setAllowFlight(true);
			break;
		default:
			ClassBase cb = new ClassBase(p.getName());
			BkCP.onlinePlayers.put(p.getName(), cb);
			break;
		}
	}




}
