package com.bexxkie.bkcp.classes;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
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
import com.bexxkie.bkcp.modules.economy.interfaces.EconInterface;

public class Earth 
extends ClassBase
implements EnergyUser,
EconInterface
{

	private String Branch;
	private double energyCap;
	private double energy;
	private int level;
	private int saveTime;
	private int pGol;
	private int pGem;
	private String pCur;
	private double maxHealth;
	public int taskSave;
	public int taskReEn;

	public Earth(String name) 
	{
		super(name);
		this.classID = 3;

		this.Branch = BkCP.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".branch");
		this.energyCap = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".maxEnergy");
		this.energy = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".energy");
		this.level = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".level");
		this.prefix = BkCP.advCfg.getConfig().getString("Prefixes.Earth."+Branch).replaceAll("&", "§");
		this.saveTime = BkCP.advCfg.getConfig().getInt("classDataAutoSave");
		this.maxHealth = BkCP.advCfg.getConfig().getInt("Classes.Earth.maxHealth"); 
		this.getPlayer().setMaxHealth(maxHealth);
		if (BkCP.econEnabled==true)
		{
			pGol = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".currency.gold");
			pGem = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".currency.gem");
			pCur = pGol+"::"+pGem;
		}
		
		refreshEnergy(3);
		showHud();
		saveAll();
	}

	public String getClassNm()
	{
		return BkCP.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".class");
	}

	@SuppressWarnings("deprecation")
	public void showHud()
	{
		if(Earth.this.getPlayer()!=null&&this.getClassNm().equalsIgnoreCase("Earth")){
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard ear = manager.getNewScoreboard();
			Objective ears = ear.registerNewObjective("Earth", this.getPlayer().getUniqueId().toString());

			ears.setDisplaySlot(DisplaySlot.SIDEBAR);
			String title = "";
			if(!this.getBranch().equalsIgnoreCase("default"))
			{
				title = this.prefix.substring(0,this.prefix.indexOf("E")).replace("[", "").replace("]", "");
			}
			//if(this.getPlayer().getDisplayName().length() <31){
			//	ears.setDisplayName(title+this.getPlayer().getDisplayName()+title);
			//}else{
			//ears.setDisplayName(title+this.getPlayer().getName()+title);
			//}
			ears.setDisplayName(title+"Stats"+title);
			if (BkCP.econEnabled==true)
			{
				Score score2 = ears.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "GLD: "+ChatColor.DARK_PURPLE + this.hudCur()));
				score2.setScore(-5);
			}
			Double regen = (((this.energyCap/3)*0.025)*2);
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			Score score2 = ears.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ENP: "+ChatColor.GREEN + numberFormat.format(this.getEnergy()).toString()));
			score2.setScore(1);
			Score level = ears.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ERG: "+ChatColor.GREEN +"+" + numberFormat.format(regen)+"/s"));
			level.setScore(-1);
			this.getPlayer().setScoreboard(ear);
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


	public double getEnergy() {
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
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("Earth")) {
			taskReEn = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
			{
				public void run()
				{
					if(Earth.this.getPlayer()!=null){
						if(Earth.this.getPlayer()!=null&&Earth.this.getPlayer().getGameMode()!=GameMode.SURVIVAL)
						{
							Earth.this.setEnergy(Earth.this.getEnergy()+20);
						}
						Earth.this.setEnergy(Earth.this.getEnergy()+((energyCap/3)*0.025));
						if(Earth.this.getPlayer()!=null&&Earth.this.getBranch().equalsIgnoreCase("miner"))
						{
							if(Earth.this.getPlayer()!=null&&Earth.this.getPlayer().getLocation().getY()<50)
							{
								Earth.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0));
								Earth.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
								if(Earth.this.getPlayer()!=null&&Earth.this.getPlayer().getLocation().getY()<25)
								{
									Earth.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
									Earth.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0));
									Earth.this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 0));
								}
								else
								{
									Earth.this.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
									Earth.this.getPlayer().removePotionEffect(PotionEffectType.REGENERATION);
									Earth.this.getPlayer().removePotionEffect(PotionEffectType.SATURATION);

								}
							}else{
								Earth.this.getPlayer().removePotionEffect(PotionEffectType.FAST_DIGGING);
								Earth.this.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
							}
						}
						Earth.this.showHud();
						Earth.this.refreshEnergy(classID);
					}
				}
			},10L);
		}

	}
	public String getBranch()
	{
		return this.Branch;
	}
	public void setBranch(String bName)
	{
		//Three branches (miner/farmer/breeder)
		if(this.getBranch()!=bName)
		{
			this.Branch = bName;
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".branch", this.Branch);
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			this.prefix = BkCP.advCfg.getConfig().getString("Prefixes.Earth."+Branch).replaceAll("&", "§");
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
	public void saveAll()
	{
		taskSave = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
		{
			public void run()
			{
				if(Earth.this.getPlayer()!=null)
				{
					Player p = Earth.this.getPlayer();
					p.sendMessage(BkCP.prefix+"autosaving classData.");
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".branch", Earth.this.getBranch());
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".mana", Earth.this.getEnergy());
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".level", Earth.this.getLevel());
					BkCP.playerClass.saveConfig();
					BkCP.playerClass.reloadConfig();
					saveAll();
				}
			}
		},saveTime);
	}
	public void stop()
	{
		Bukkit.getScheduler().cancelTask(taskSave);
		Bukkit.getScheduler().cancelTask(taskReEn);
	}


}
