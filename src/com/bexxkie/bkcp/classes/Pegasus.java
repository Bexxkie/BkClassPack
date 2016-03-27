package com.bexxkie.bkcp.classes;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.Vector;

import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.classes.interfaces.ClassBase;
import com.bexxkie.bkcp.classes.interfaces.Flier;
import com.bexxkie.bkcp.interaction.Glide;
import com.bexxkie.bkcp.modules.economy.interfaces.EconInterface;
import com.bexxkie.bkcp.util.RandInt;

public class Pegasus 
extends ClassBase
implements Flier,
EconInterface
{
	private double energy;
	private double energyCap;
	private int level;
	private double cool;
	private int fxp;
	private int saveTime;
	private int fxpTarget;
	private int pGol;
	private int pGem;
	private String pCur;
	private double maxHealth;
	public int taskGlide;
	public int taskCool;
	public int taskSave;
	public int taskReEn;

	public Pegasus(String name) {
		super(name);
		this.classID = 2;
		this.prefix = BkCP.advCfg.getConfig().getString("Prefixes.Pegasus.default").replaceAll("&", "§");
		this.fxp = BkCP.playerClass.getConfig().getInt(Pegasus.this.getPlayer().getUniqueId()+".fxp");
		this.fxpTarget = BkCP.advCfg.getConfig().getInt("fxpTarget");
		this.energy = BkCP.playerClass.getConfig().getDouble(Pegasus.this.getPlayer().getUniqueId()+".energy");
		this.energyCap = BkCP.playerClass.getConfig().getDouble(Pegasus.this.getPlayer().getUniqueId()+".maxEnergy");
		this.level = BkCP.playerClass.getConfig().getInt(Pegasus.this.getPlayer().getUniqueId()+".level");
		this.saveTime = BkCP.advCfg.getConfig().getInt("classDataAutoSave");
		this.cool = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".ftime");
		this.maxHealth = BkCP.advCfg.getConfig().getInt("Classes.Pegasus.maxHealth"); 
		this.getPlayer().setMaxHealth(maxHealth);
		if (BkCP.config.getConfig().getBoolean("Module.Economy-Enabled"))
		{
			pGol = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".currency.gold");
			pGem = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".currency.gem");
			pCur = pGol+"::"+pGem;
		}

		this.refreshEnergy(2);
		this.showHud();
		this.saveAll();
		this.coolDown();
		this.isGlide();
	}
	public String getClassNm()
	{
		return BkCP.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".class");
	}
	@SuppressWarnings("deprecation")
	public void showHud()
	{
		if(Pegasus.this.getPlayer()!=null&&this.getClassNm().equalsIgnoreCase("pegasus")){
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard peg = manager.getNewScoreboard();
			Objective pegs = peg.registerNewObjective("pegasus", Pegasus.this.getPlayer().getUniqueId().toString());

			pegs.setDisplaySlot(DisplaySlot.SIDEBAR);
			//if(Pegasus.this.getPlayer().getDisplayName().length() <31){
			//	pegs.setDisplayName(Pegasus.this.getPlayer().getDisplayName());
			//}else{
			//	pegs.setDisplayName(Pegasus.this.getPlayer().getName());
			//}
			pegs.setDisplayName("Stats");
			if (BkCP.econEnabled==true)
			{
				Score score2 = pegs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "GLD: "+ChatColor.DARK_PURPLE + this.hudCur()));
				score2.setScore(-5);
			}
			Score sfxp = pegs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FXP: "+ChatColor.AQUA +getFxp()));
			sfxp.setScore(-4);
			Double regen = ((this.energyCap*0.025)*2);
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			Score score2 = pegs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ENP: "+ChatColor.AQUA + numberFormat.format(this.getEnergy()).toString()));
			score2.setScore(1);
			Score flt = pegs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FLT: "+ChatColor.AQUA+ChatColor.STRIKETHROUGH +numberFormat.format(this.cool)));
			if(this.cool>0)
			{
				flt = pegs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FLT: "+ChatColor.AQUA +numberFormat.format(this.cool/60)));	
			}
			flt.setScore(0);
			Score level = pegs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ERG: "+ChatColor.AQUA +"+" + numberFormat.format(regen)+"/s"));
			level.setScore(-1);
			Pegasus.this.getPlayer().setScoreboard(peg);
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
	public void coolDown()
	{
		taskCool = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
		{
			public void run()
			{	
				if(Pegasus.this.getPlayer()!=null)
				{
					if(Pegasus.this.cool>0.0)
					{
						Pegasus.this.setCool(Pegasus.this.getCool()-1);
					}
					BkCP.playerClass.getConfig().set(Pegasus.this.getPlayer().getUniqueId()+".ftime", Pegasus.this.getCool());
					BkCP.playerClass.saveConfig();
					BkCP.playerClass.reloadConfig();
					Pegasus.this.coolDown();
				}
			}
		},20l);
	}

	public void isGlide()
	{
		taskGlide = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
		{
			public void run()
			{
				if(Pegasus.this.getPlayer()!=null){
					Player p = Pegasus.this.getPlayer();
					if(BkCP.onlinePlayers.get(p.getName())instanceof Flier)
					{
						if (p.getVelocity().getY() < -.01&&p.isSneaking()){
							if(p.getLocation().getBlock().getRelative(BlockFace.DOWN, 2).getType().equals(Material.AIR)){
								if(((Flier)BkCP.onlinePlayers.get(p.getName())).getEnergy()>10){
									((Flier)BkCP.onlinePlayers.get(p.getName())).setEnergy(((Flier)BkCP.onlinePlayers.get(p.getName())).getEnergy()-1.1);
									((Flier)BkCP.onlinePlayers.get(p.getName())).showHud();
									Vector v = p.getVelocity();
									v.setY(v.getY()* -.1);
									Vector d = p.getLocation().getDirection();
									d.multiply(.1);
									p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_FLAP, 1, (float) (9*v.getY()+Math.random()));
									p.setVelocity(v);
									p.setVelocity(d);
									p.setFallDistance(0);
								}
								p.getWorld().playEffect(p.getLocation().getBlock().getRelative(BlockFace.DOWN,3).getLocation(), Effect.STEP_SOUND, p.getLocation().getBlock().getRelative(BlockFace.DOWN,3).getType());
							}
						}
					}
					Pegasus.this.isGlide();
				}
			}
		},10);
	}

	public void setCool(double i)
	{
		this.cool = i;
	}
	public double getCool()
	{
		return this.cool;
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
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("pegasus")) {
			taskReEn = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
			{
				@SuppressWarnings("deprecation")
				public void run()
				{
					if(Pegasus.this.getPlayer()!=null)
					{
						if(Pegasus.this.getPlayer()!=null&&Pegasus.this.getPlayer().getGameMode()!=GameMode.SURVIVAL)
						{
							Pegasus.this.setEnergy(Pegasus.this.getEnergy()+20);
						}
						if(Pegasus.this.getPlayer()!=null&&Pegasus.this.cool>=0.0||Pegasus.this.getEnergy()>=0.0)
						{
							Pegasus.this.getPlayer().setAllowFlight(true);
						}
						if(Pegasus.this.getPlayer()!=null&&Pegasus.this.getEnergy()<=0.0)
						{
							Pegasus.this.getPlayer().setAllowFlight(false);
						}
						if(Pegasus.this.getPlayer()!=null&&Pegasus.this.getPlayer().isFlying())
						{
							Pegasus.this.setEnergy(Pegasus.this.getEnergy()-Glide.lCost);
						}
						else if(Pegasus.this.getPlayer()!=null&&Pegasus.this.getPlayer().isOnGround())
						{
							Pegasus.this.setEnergy(Pegasus.this.getEnergy()+(energyCap*0.025));
						}
						if(Pegasus.this.getPlayer()!=null&&!Pegasus.this.getPlayer().isOnGround()&&Pegasus.this.getEnergy()<20)
						{
							Pegasus.this.setEnergy(Pegasus.this.getEnergy()+(Glide.lCost/10));
						}
						Pegasus.this.refreshEnergy(classID);
						Pegasus.this.showHud();
					}
				}
			},10L);
		}

	}

	public void setFxp(int i)
	{

		if(this.fxp<this.fxpTarget)
		{
			this.fxp=this.fxp+i;
		}
		else
		{
			this.setLevel(this.getLevel()+1);
			this.fxp=0;
		}
	}
	public int getFxp()
	{
		return this.fxp;
	}

	public void saveAll()
	{
		taskSave = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
		{
			public void run()
			{
				if(Pegasus.this.getPlayer()!=null){
					Player p = Pegasus.this.getPlayer();
					p.sendMessage(BkCP.prefix+"autosaving classData.");
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".energy", Pegasus.this.getEnergy());
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".level", Pegasus.this.getLevel());
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".fxp", Pegasus.this.getFxp());
					BkCP.playerClass.saveConfig();
					BkCP.playerClass.reloadConfig();
					saveAll();
				}
			}
		},saveTime);
	}
	public void setLevel(int i) {
		this.level = i;
		if(level <10)
		{
			this.getPlayer().playSound(this.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, RandInt.randInt(0, 5));
			this.level = this.getLevel()+1;
			this.showHud();
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".level", this.getLevel());
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".maxEnergy", this.energyCap*1.1);
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".fxp", this.getFxp());
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			this.energyCap = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId().toString()+".maxEnergy");
		}else
		{
			this.getPlayer().sendMessage(BkCP.prefix+"You have trained as much as possible.");
		}

	}
	public int getLevel() 
	{
		return this.level;
	}
	public void stop()
	{
		Bukkit.getScheduler().cancelTask(taskCool);
		Bukkit.getScheduler().cancelTask(taskGlide);
		Bukkit.getScheduler().cancelTask(taskReEn);
		Bukkit.getScheduler().cancelTask(taskSave);
	}

}
