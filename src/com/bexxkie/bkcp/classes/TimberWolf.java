package com.bexxkie.bkcp.classes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Biome;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.classes.interfaces.ClassBase;
import com.bexxkie.bkcp.classes.interfaces.EnergyUser;
import com.bexxkie.bkcp.util.RandInt;

public class TimberWolf 
extends ClassBase
implements EnergyUser
{
	private String Branch;
	private double energyCap;
	private double energy;
	private int level;
	private int saveTime;
	private double maxHealth;
	private Map<Biome, String> biomeMap;
	private double getRegen;
	private double cool;
	private int invisTime;
	private boolean alwaysRun;
	private String bPrefix;
	//econ_param
	private int pGol;
	private int pGem;
	private String pCur;
	public int taskReEn;
	public int taskSave;
	public int taskNear;
	public int taskEnvVar;
	public int taskCool;
	@SuppressWarnings("unchecked")
	public TimberWolf(String name) {
		super(name);
		this.classID = 7;

		this.Branch = BkCP.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".pack");
		this.bPrefix = BkCP.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".packPref");
		this.energyCap = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".maxEnergy");
		this.energy = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".energy");
		this.level = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".level");
		this.prefix = BkCP.advCfg.getConfig().getString("Prefixes.TimberWolf.default").replaceAll("&", "§");
		this.saveTime = BkCP.advCfg.getConfig().getInt("classDataAutoSave");
		this.maxHealth = BkCP.advCfg.getConfig().getInt("Classes.TimberWolf.maxHealth"); 
		this.getPlayer().setMaxHealth(maxHealth);
		if(BkCP.advCfg.getConfig().getInt("Classes.TimberWolf.alwaysRun")==1)
		{
			this.alwaysRun = true;
		}
		this.invisTime = BkCP.advCfg.getConfig().getInt("Classes.TimberWolf.invisTime");
		if(BkCP.EnvData.getConfig().getBoolean("enabled")==true)
		{
			biomeMap = (Map<Biome, String>) (BkCP.EnvData.getConfig().getMapList("biomes"));
			environmentVars();
		}
		if (BkCP.econEnabled==true)
		{
			pGol = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".currency.gold");
			pGem = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".currency.gem");
			pCur = pGol+"::"+pGem;
		}
		
		refreshEnergy(7);
		showHud();
		saveAll(); 
		this.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
		this.getPlayer().removePotionEffect(PotionEffectType.SPEED);
	}
	public String getClassNm()
	{
		return BkCP.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".class");
	}
	@SuppressWarnings("deprecation")
	public void showHud()
	{
		if(TimberWolf.this.getPlayer()!=null&&this.getClassNm().equalsIgnoreCase("TimberWolf")){
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard tw = manager.getNewScoreboard();
			Objective tws = tw.registerNewObjective("TimberWolf", this.getPlayer().getUniqueId().toString());

			tws.setDisplaySlot(DisplaySlot.SIDEBAR);
			String title = "";
			if(!this.getBranch().equalsIgnoreCase("null"))
			{
				title = this.bPrefix.replaceAll("&", "§");;
			}
			/*if(this.getPlayer().getDisplayName().length() <31){
				tws.setDisplayName(title+this.getPlayer().getDisplayName()+title);
			}else{*/
			//tws.setDisplayName(title+this.getPlayer().getName()+title);
			//}
				tws.setDisplayName(title+"Stats"+title);
			if (BkCP.econEnabled==true)
			{
				Score score2 = tws.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "GLD: "+ChatColor.DARK_PURPLE + this.hudCur()));
				score2.setScore(-5);
			}
			Double regen = this.getRegen;
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			Score score2 = tws.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ENP: "+ChatColor.GREEN + numberFormat.format(this.getEnergy()).toString()));
			score2.setScore(1);
			Score level = tws.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ERG: "+ChatColor.GREEN +"+" + numberFormat.format(regen)+"/s"));
			level.setScore(-1);
			this.getPlayer().setScoreboard(tw);
		}
	}

	public int getCur(String type)
	{
		switch(type.toLowerCase())
		{
		case "gold":
			return pGol;
		case "gem":
			return pGem;
		default:
			break;
		}
		return 0;
	}
	public String hudCur()
	{
		this.pCur = ChatColor.YELLOW+""+this.getCur("gold")+ChatColor.DARK_PURPLE+"::"+ChatColor.GREEN+this.getCur("gem");
		return this.pCur;
	}
	public void setCur(String type, int amount)
	{
		switch(type.toLowerCase())
		{
		case "gold":
			pGol = amount;
			break;
		case "gem":
			pGem = amount;
			break;
		default:
			break;
		}
	}
	public double getEnergy() 
	{
		return this.energy;
	}

	public void setEnergy(Double newEnergy) 
	{
		this.energy = newEnergy;
		if(this.energy > energyCap)
		{
			this.energy = energyCap;
		}
	}
	public void refreshEnergy(final int classID) 
	{
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("TimberWolf")) {
			taskReEn = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
			{
				public void run()
				{
					//System.out.print("RUNNING");
					if(TimberWolf.this.getPlayer()!=null){
						if(TimberWolf.this.getPlayer()!=null&&!TimberWolf.this.getPlayer().hasPotionEffect(PotionEffectType.SPEED)&&TimberWolf.this.alwaysRun==true)
						{
							TimberWolf.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,Integer.MAX_VALUE,0));
						}

						if(TimberWolf.this.getPlayer()!=null&&TimberWolf.this.getPlayer().getGameMode()!=GameMode.SURVIVAL)
						{
							TimberWolf.this.setEnergy(TimberWolf.this.getEnergy()+20);
						}
						if(TimberWolf.this.getPlayer()!=null&&TimberWolf.this.getPlayer().getLocation().getBlock().getType().equals(Material.STATIONARY_WATER))
						{
							TimberWolf.this.setEnergy(TimberWolf.this.getEnergy()+((energyCap/3)*0.075));
							getRegen = ((energyCap/3)*0.075)*2;

						}else{
							TimberWolf.this.setEnergy(TimberWolf.this.getEnergy()+((energyCap/3)*0.025));
							getRegen = ((energyCap/3)*0.025)*2;
						}
						if(TimberWolf.this.getPlayer()!=null&&TimberWolf.this.getPlayer().isSneaking())
						{
							if(TimberWolf.this.getEnergy()>=energyCap/2
									&&TimberWolf.this.getCool()==0)
							{

								TimberWolf.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,Integer.MAX_VALUE,4));
								TimberWolf.this.setEnergy(TimberWolf.this.getEnergy() - (energyCap/4));
								TimberWolf.this.setCool(TimberWolf.this.invisTime);
								TimberWolf.this.coolDown();
							}
						}

						TimberWolf.this.showHud();
						TimberWolf.this.refreshEnergy(classID);
					}
				}
			},10L);
		}
	}
	public void coolDown()
	{
		taskCool = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
		{
			public void run()
			{	
				if(TimberWolf.this.getPlayer()!=null)
				{
					if(TimberWolf.this.getPlayer()!=null&&TimberWolf.this.cool>0.0)
					{
						TimberWolf.this.setCool(TimberWolf.this.getCool()-1);
						TimberWolf.this.coolDown();
					}
					else if(TimberWolf.this.getPlayer()!=null&&TimberWolf.this.getCool()==0.0)
					{
						TimberWolf.this.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
					}
				}
			}
		},20l);
	}
	public void setCool(double i)
	{
		this.cool = i;
	}
	public double getCool()
	{
		return this.cool;
	}
	//pack
	public String getBranch() 
	{
		return this.Branch;
	}
	//pack
	public void createBranch(String bName, String colour)
	{
		if(BkCP.playerClass.getConfig().contains("Twol."+bName))
		{
			this.getPlayer().sendMessage(BkCP.prefix+ChatColor.RED+"This pack already exists");
		}
		else
		{
			List<String> memList = new ArrayList<String>();
			List<String> invList = new ArrayList<String>();
			memList.add(this.getPlayer().getUniqueId().toString());
			invList.clear();
			BkCP.guilds_packs.getConfig().set("Twol."+bName+".leader", this.getPlayer().getUniqueId().toString());
			BkCP.guilds_packs.getConfig().set("Twol."+bName+".prefix", colour+bName.substring(0,1)+"&r");
			BkCP.guilds_packs.getConfig().set("Twol."+bName+".invitation",invList);
			BkCP.guilds_packs.getConfig().set("Twol."+bName+".members", memList);
			this.Branch = bName;
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".pack", this.Branch);
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".packPref", colour+bName.substring(0,1)+"&r");
			this.bPrefix = BkCP.guilds_packs.getConfig().getString("Twol."+this.Branch+".prefix").replaceAll("&", "§");
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			BkCP.guilds_packs.saveConfig();
			BkCP.guilds_packs.reloadConfig();
		}
	}
	public void editBranch(String bName, String colour)
	{
		BkCP.guilds_packs.getConfig().set("Twol."+bName+".prefix", colour+bName.substring(0,1)+"&r");
		this.Branch = bName;
		BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".pack", this.Branch);
		BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".packPref", colour+bName.substring(0,1)+"&r");
		this.bPrefix = BkCP.guilds_packs.getConfig().getString("Twol."+this.Branch+".prefix").replaceAll("&", "§");
		BkCP.playerClass.saveConfig();
		BkCP.playerClass.reloadConfig();
		BkCP.guilds_packs.saveConfig();
		BkCP.guilds_packs.reloadConfig();
	}
	public void delBranchLeave(String bName)
	{
		this.Branch="null";
		BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".pack", "null");
		BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".packPref", "");
		BkCP.playerClass.saveConfig();
		BkCP.playerClass.reloadConfig();
	}
	public void leaveBranch(String bName)
	{
		List<String> memList = BkCP.guilds_packs.getConfig().getStringList("Twol."+bName+".members");
		String leader = BkCP.guilds_packs.getConfig().getString("Twol."+bName+".leader");

		if(leader.equals(this.getPlayer().getUniqueId().toString()))
		{
			this.getPlayer().sendMessage(BkCP.prefix+ChatColor.RED+"You cannot leave your own pack.");
			return;
		}
		if(memList.contains(this.getPlayer().getUniqueId().toString()))
		{
			this.Branch="null";
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".pack", "null");
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".packPref", "");
			memList.remove(this.getPlayer().getUniqueId().toString());
			BkCP.guilds_packs.getConfig().set("Twol."+bName+".members",memList);
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			BkCP.guilds_packs.saveConfig();
			BkCP.guilds_packs.reloadConfig();
			this.getPlayer().sendMessage("You have left the pack "+bName+".");
		}
		else{
			this.getPlayer().sendMessage("You cannot leave this pack because you are not a member.");
		}
	}
	public void stop(String name)
	{
		if(name.equals(this.getPlayer().getName()))
		{

		}
	}
	//pack
	public void setBranch(String bName) 
	{
		if(this.getBranch()!=null&&!this.getBranch().equalsIgnoreCase("null"))
		{
			if(this.getBranch().equalsIgnoreCase(bName))
			{
				this.getPlayer().sendMessage(BkCP.prefix+ChatColor.RED+"You are already a member of this pack...");
				return;
			}
			this.getPlayer().sendMessage(BkCP.prefix+ChatColor.RED+"You are already a member of the pack "+getBranch()+"! Leave the pack before joining another.");
			return;
		}
		else
		{
			String pPref = BkCP.guilds_packs.getConfig().getString("Twol."+bName+".prefix");
			List<String> memList = BkCP.guilds_packs.getConfig().getStringList("Twol."+bName+".members");
			List<String> invList = BkCP.guilds_packs.getConfig().getStringList("Twol."+bName+".invitation");
			invList.remove(this.getPlayer().getUniqueId().toString());
			memList.add(this.getPlayer().getUniqueId().toString());
			this.Branch = bName;
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".pack", this.Branch);
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".packPref", pPref);
			BkCP.guilds_packs.getConfig().set("Twol."+bName+".members", memList);
			BkCP.guilds_packs.getConfig().set("Twol."+bName+".invitation", invList);
			BkCP.guilds_packs.saveConfig();
			BkCP.guilds_packs.reloadConfig();
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			this.prefix = BkCP.guilds_packs.getConfig().getString("Twol."+this.Branch+".prefix").replaceAll("&", "§");
			this.getPlayer().sendMessage(BkCP.prefix+ChatColor.RED+"You are now a member of the pack "+bName+".");
		}

	}

	public void setLevel(int i) 
	{
		if(level <10)
		{
			this.level = this.getLevel()+1;
			this.showHud();
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".level", this.getLevel());
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".maxEnergy", +5);
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			this.energyCap = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId().toString()+".maxEnergy");
		}else
		{
			this.getPlayer().sendMessage(BkCP.prefix+"You cannot learn anything more from these tomes, you destroyed the material.");
		}
	}

	public int getLevel() 
	{
		return this.level;
	}
	public PotionEffect envDecode(String val)
	{
		switch(val.toLowerCase())
		{
		case "regen":
			return new PotionEffect(PotionEffectType.REGENERATION,60,1);
		case "speed":
			return new PotionEffect(PotionEffectType.SPEED,60,1);
		case "camoflage":
			return new PotionEffect(PotionEffectType.INVISIBILITY,60,0);
		case "noregen":
			return new PotionEffect(PotionEffectType.HUNGER,60,0);
		case "slow":
			return new PotionEffect(PotionEffectType.SLOW,60,0);
		case "slowmine":
			return new PotionEffect(PotionEffectType.SLOW_DIGGING,60,1);
		default:
			return null;
		}
	}
	public void nearPackMates()
	{
		taskNear = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
		{
			public void run()
			{
				if(TimberWolf.this.getPlayer()!=null){
					Player player = TimberWolf.this.getPlayer();
					for(Entity e : player.getNearbyEntities(20, 20, 20))
					{
						if(e instanceof Player)
						{
							Player p = (Player)e;

							if(BkCP.onlinePlayers.get(p.getName())instanceof TimberWolf)
							{
								TimberWolf.this.packBuffs(player);
							}
						}
					}
				}
			}
		},100);
	}
	public void packBuffs(Player p)
	{
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
		p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
		p.playSound(p.getLocation(), Sound.ENTITY_WOLF_HOWL, RandInt.randFloat(0, 5), RandInt.randFloat(0, 5));
	}

	public void environmentVars()
	{
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("TimberWolf")) {
			taskEnvVar = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
			{
				public void run()
				{
					if(TimberWolf.this.getPlayer()!=null){
						if(TimberWolf.this.getPlayer()!=null&&TimberWolf.this.biomeMap.containsKey(TimberWolf.this.getPlayer().getLocation().getBlock().getBiome()))
						{
							if(TimberWolf.this.getPlayer()!=null&&envDecode(biomeMap.get(TimberWolf.this.getPlayer().getLocation().getBlock().getBiome()))!=null)
							{
								TimberWolf.this.getPlayer().addPotionEffect(envDecode(biomeMap.get(TimberWolf.this.getPlayer().getLocation().getBlock().getBiome())));
							}
						}
						environmentVars();
					}
				}
			},10);
		}
	}


	public void saveAll()
	{
		taskSave = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
		{
			public void run()
			{

				if(TimberWolf.this.getPlayer()!=null)
				{
					Player p = TimberWolf.this.getPlayer();
					p.sendMessage(BkCP.prefix+"autosaving classData.");
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".pack", TimberWolf.this.getBranch());
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".mana", TimberWolf.this.getEnergy());
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".level", TimberWolf.this.getLevel());
					BkCP.playerClass.saveConfig();
					BkCP.playerClass.reloadConfig();
					saveAll();
				}
			}
		},saveTime);
	}
	public void stop()
	{
		Bukkit.getScheduler().cancelTask(taskCool);
		Bukkit.getScheduler().cancelTask(taskEnvVar);
		Bukkit.getScheduler().cancelTask(taskNear);
		Bukkit.getScheduler().cancelTask(taskReEn);
		Bukkit.getScheduler().cancelTask(taskSave);
	}

}