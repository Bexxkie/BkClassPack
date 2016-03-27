package com.bexxkie.bkcp.classes;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.Vector;

import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.classes.interfaces.ClassBase;
import com.bexxkie.bkcp.classes.interfaces.EnergyUser;
import com.bexxkie.bkcp.classes.interfaces.Flier;
import com.bexxkie.bkcp.classes.interfaces.MagicUser;
import com.bexxkie.bkcp.interaction.Glide;
import com.bexxkie.bkcp.modules.economy.interfaces.EconInterface;
import com.bexxkie.bkcp.util.RandInt;


public class Changeling 
extends ClassBase
implements EnergyUser, Flier, MagicUser, EconInterface

{

	private List<String> SpecSpells;
	private List<String> SpecDisguises;
	private Map<String, Boolean> coolIDs = new HashMap<String, Boolean>();
	private Map<String, Integer> coolTime = new HashMap<String, Integer>();
	private double mana;
	private double manaCap;
	private double energy;
	private double energyCap;
	private int selSpell;
	private int selDisguise;
	private int mLevel;
	private int fLevel;
	private int saveTime;
	private double cool;
	private int fxp;
	private int fxpTarget;
	private int pGol;
	private int pGem;
	private String pCur;
	private Boolean isDisguised;
	private double maxHealth;
	public int taskReEn;
	public int taskReMa;
	public int taskGlide;
	public int taskCool;
	public int taskCool2;
	public int taskSave;

	public Changeling(String name) 
	{
		super(name);
		this.classID = 5;
		this.coolIDs.clear();
		this.coolIDs.put("potionrain", false);       this.coolTime.put("potionrain", 0);
		this.coolIDs.put("tntrain", false);       this.coolTime.put("tntrain", 0);
		this.coolIDs.put("throwcake", false);    this.coolTime.put("throwcake", 0);
		this.coolIDs.put("creeperrain", false);   this.coolTime.put("creeperrain", 0);
		this.coolIDs.put("throwfish", false);   this.coolTime.put("throwfish", 0);this.coolTime.clear();
		this.coolIDs.put("blink", false);       this.coolTime.put("blink", 0);
		this.coolIDs.put("fireball", false);    this.coolTime.put("fireball", 0);
		this.coolIDs.put("fireblast", false);   this.coolTime.put("fireblast", 0);
		this.coolIDs.put("icespike", false);    this.coolTime.put("icespike", 0);
		this.coolIDs.put("iceblast", false);    this.coolTime.put("iceblast", 0);
		this.coolIDs.put("shadowbolt", false);  this.coolTime.put("shadowbolt", 0);
		this.coolIDs.put("shadowblast", false); this.coolTime.put("shadowblast", 0);
		this.coolIDs.put("save", false);        this.coolTime.put("save", 0);
		this.coolIDs.put("recallI", false);      this.coolTime.put("recallI", 0);
		this.coolIDs.put("recallII", false);      this.coolTime.put("recallII", 0);

		this.manaCap = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".maxMana");
		this.mana = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".mana");
		this.energyCap = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".maxEnergy");
		this.energy = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".energy");
		this.mLevel = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".mlevel");
		this.fLevel = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".flevel");
		this.selSpell = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".currentSpell");
		this.selDisguise = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".currentDisguise");
		this.saveTime = BkCP.advCfg.getConfig().getInt("classDataAutoSave");
		this.fxp = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".fxp");
		this.fxpTarget = BkCP.advCfg.getConfig().getInt("fxpTarget");
		this.cool = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".ftime");
		this.prefix = BkCP.advCfg.getConfig().getString("Prefixes.Changeling.default").replaceAll("&", "§");
		this.SpecSpells = BkCP.BranchSpells.getConfig().getStringList("ch.default.spells");
		this.SpecDisguises = BkCP.advCfg.getConfig().getStringList("Classes.Changeling.disguises.default");
		this.isDisguised=false;
		this.maxHealth = BkCP.advCfg.getConfig().getInt("Classes.Changeling.maxHealth"); 
		this.getPlayer().setMaxHealth(maxHealth);
		if (BkCP.econEnabled==true)
		{
			pGol = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".currency.gold");
			pGem = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".currency.gem");
			pCur = pGol+"::"+pGem;
		}
		this.refreshMana(5);
		this.refreshEnergy(5);
		this.showHud();
		this.saveAll();
		this.coolDown();
	}

	@SuppressWarnings("deprecation")
	public void showHud() 
	{
		if(Changeling.this.getPlayer()!=null&&this.getClassNm().equalsIgnoreCase("Changeling")){
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard cha = manager.getNewScoreboard();
			Objective chan = cha.registerNewObjective("changeling", this.getPlayer().getUniqueId().toString());
			chan.setDisplaySlot(DisplaySlot.SIDEBAR);
			String title = "";
			title = this.prefix.substring(0,this.prefix.indexOf("C")).replace("[", "").replace("]", "");
			//if(this.getPlayer().getDisplayName().length() <31)
			//{
			//	chan.setDisplayName(title+this.getPlayer().getDisplayName()+title);
			//}else{
			//	chan.setDisplayName(title+this.getPlayer().getName()+title);
			//}
				chan.setDisplayName(title+"Stats"+title);
			if (BkCP.econEnabled==true)
			{
				Score score2 = chan.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "GLD: "+ChatColor.DARK_PURPLE + this.hudCur()));
				score2.setScore(-5);
			}
			Double mRegen = (((this.manaCap/1.5)*0.025)*2);
			Double fRegen = (((this.energyCap/1.5)*0.025)*2);
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			Score score2 = chan.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "MNP: "+ChatColor.DARK_PURPLE + numberFormat.format(this.getMana()).toString()));
			score2.setScore(3);
			Score score3 = chan.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ENP: "+ChatColor.DARK_PURPLE + numberFormat.format(this.getEnergy()).toString()));
			score3.setScore(2);

			if(this.coolIDs.containsKey(this.currentSpell().toLowerCase())&&this.getCool(this.currentSpell().toLowerCase())==true)
			{
				Score spell = chan.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD+ "SPL: "+ChatColor.RED + this.currentSpell()));
				spell.setScore(1);
			}else
			{
				Score spell = chan.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD+ "SPL: "+ChatColor.AQUA + this.currentSpell()));
				spell.setScore(1);
			}
			Score flt = chan.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FLT: "+ChatColor.AQUA+ChatColor.STRIKETHROUGH +numberFormat.format(this.cool)));
			if(this.cool>0)
			{
				flt = chan.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FLT: "+ChatColor.AQUA +numberFormat.format(this.cool/60)));	
			}
			flt.setScore(0);
			Score sfxp = chan.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FXP: "+ChatColor.AQUA +getFxp()));
			sfxp.setScore(-4);
			Score manRegen = chan.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "MRG: "+ChatColor.DARK_PURPLE +"+" + numberFormat.format(mRegen)+"/s"));
			manRegen.setScore(-1);
			Score fltRegen = chan.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ERG: "+ChatColor.DARK_PURPLE +"+" + numberFormat.format(fRegen)+"/s"));
			fltRegen.setScore(-2);
			if(this.getDisguised()==true){
				Score dis = chan.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "DSG: "+ChatColor.DARK_PURPLE + this.currentDisguise()));
				dis.setScore(-3);
			}else{
				Score dis = chan.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "DSG: "+ChatColor.AQUA + this.currentDisguise()));
				dis.setScore(-3);	
			}
			this.getPlayer().setScoreboard(cha);
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
	public String getClassNm()
	{
		return BkCP.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".class");
	}
	public Double getMana() 
	{
		return this.mana;
	}

	public void setMana(Double newMana) 
	{
		this.mana = newMana;
		if(this.mana > manaCap)
		{
			this.mana = manaCap;
		}
	}

	public void refreshMana(final int classID) 
	{
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("Changeling")) {
			taskReMa = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
			{
				public void run()
				{
					if(Changeling.this.getPlayer()!=null){
						if(Changeling.this.getPlayer()!=null&&Changeling.this.getPlayer().getGameMode()!=GameMode.SURVIVAL)
						{
							Changeling.this.setMana(Changeling.this.getMana()+20);
						}
						Changeling.this.setMana(Changeling.this.getMana()+((manaCap/1.5)*0.025));
						Changeling.this.refreshMana(classID);
						Changeling.this.showHud();
					}
				}
			},10L);
		}
	}

	public void disguise(Player p, EntityType e)
	{
		this.setDisguised();
		MobDisguise dis = new MobDisguise(DisguiseType.getType(e));
		DisguiseAPI.disguiseToAll(p, dis);
	}
	public void undisguise(Player p)
	{
		this.setDisguised();
		DisguiseAPI.undisguiseToAll(p);
	}
	public void setDisguised()
	{
		if(getDisguised()==false)
		{
			this.isDisguised=true;
		}else
		{
			this.isDisguised=false;
		}
	}
	public boolean getDisguised()
	{
		return this.isDisguised;
	}
	public String currentDisguise()
	{
		return SpecDisguises.get(this.selDisguise);
		//return "test";
	}
	public void cycleDisguise()
	{
		if(!getPlayer().isSneaking())
		{
			this.selDisguise+=1;
			if (this.selDisguise > this.SpecDisguises.size()-1)
			{
				this.selDisguise = 0;
			}
		}else
		{
			this.selDisguise-=1;
			if (this.selDisguise < 0)
			{
				this.selDisguise = this.SpecDisguises.size()-1;
			}
		}

		this.showHud();
	}
	public boolean hasDisguise(String disguise)
	{
		for(String dis : SpecDisguises)
		{
			if(dis.equalsIgnoreCase(disguise))
			{
				return true;
			}
		}
		return false;
	}
	public void getDisguises() 
	{
		getPlayer().sendMessage(BkCP.prefix+this.SpecDisguises);
	}
	public String currentSpell() 
	{
		return SpecSpells.get(this.selSpell);
	}
	public void cycleSpell() 
	{
		if(!getPlayer().isSneaking())
		{
			this.selSpell+=1;
			if (this.selSpell > this.SpecSpells.size()-1)
			{
				this.selSpell = 0;
			}
		}else
		{
			this.selSpell-=1;
			if (this.selSpell < 0)
			{
				this.selSpell = this.SpecSpells.size()-1;
			}
		}
		this.showHud();
	}

	public boolean hasSpell(String spell) 
	{
		for(String sp : SpecSpells)
		{
			if(sp.equalsIgnoreCase(spell))
			{
				return true;
			}
		}
		return false;
	}

	public void getSpells() 
	{
		getPlayer().sendMessage(BkCP.prefix+this.SpecSpells);
	}

	public void setLevel() {}
	public void addSpell(String sName) {}
	public void remSpell(String sName) {}

	public void setCool(final String spellID, int coolTime) 
	{
		if(this.coolIDs.containsKey((spellID.toLowerCase())))
		{
			if(this.coolIDs.get(spellID.toLowerCase())==false)
			{
				this.coolIDs.put(spellID.toLowerCase(), true);
				this.coolTime.put(spellID.toLowerCase(), coolTime);
			}
			taskCool = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
			{
				public void run()
				{
					if(Changeling.this.getPlayer()!=null){
						if(Changeling.this.getPlayer()!=null&&Changeling.this.coolTime.get(spellID.toLowerCase())==0)
						{
							Changeling.this.coolIDs.put(spellID.toLowerCase(), false);
							return;
						}
						Changeling.this.coolTime.put(spellID.toLowerCase(), Changeling.this.coolTime.get(spellID.toLowerCase())-1);
						setCool(spellID, Changeling.this.coolTime.get(spellID.toLowerCase()));
					}
				}
			},20L);
		}
	}

	public boolean getCool(String spellID) 
	{
		return coolIDs.get(spellID.toLowerCase());
	}

	public void setCool(double intCool) 
	{
		this.cool = intCool;
	}

	public double getCool()
	{
		return this.cool;
	}

	public void saveAll()
	{
		taskSave = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
		{
			public void run()
			{
				if(Changeling.this.getPlayer()!=null){
					Player p = Changeling.this.getPlayer();
					p.sendMessage(BkCP.prefix+"autosaving classData.");
					//BkCP.playerClass.getConfig().set(p.getUniqueId()+".branch", Changeling.this.getBranch());

					BkCP.playerClass.getConfig().set(p.getUniqueId()+".mana", Changeling.this.getMana());
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".flevel", Changeling.this.getFlierLevel());
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".mlevel", Changeling.this.getMagicLevel());
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".currentSpell", Changeling.this.selSpell);
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".currentDisguise", Changeling.this.selDisguise);
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".spells", Changeling.this.SpecSpells);
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".energy", Changeling.this.getEnergy());
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".fxp", Changeling.this.getFxp());
					BkCP.playerClass.saveConfig();
					BkCP.playerClass.reloadConfig();
					saveAll();
				}
			}
		},saveTime);
	}
	public int getFlierLevel()
	{
		return this.fLevel;
	}
	public int getMagicLevel()
	{
		return this.mLevel;
	}
	public void setMagicLevel() 
	{
		if(this.getMagicLevel() <10)
		{
			this.mLevel = this.getMagicLevel()+1;
			this.showHud();
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".mlevel", this.getMagicLevel());
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".maxMana", this.manaCap+5);
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			this.manaCap = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId().toString()+".maxMana");
		}else
		{
			this.getPlayer().sendMessage(BkCP.prefix+"You cannot learn anything more from these tomes, you destroyed the material.");
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
			this.setFlierLevel();
			this.fxp=0;
		}
	}
	public void setFlierLevel()
	{
		if(this.getFlierLevel() <10)
		{
			this.fLevel = this.getFlierLevel()+1;
			this.getPlayer().playSound(this.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, RandInt.randInt(0, 5));
			this.fLevel = this.getFlierLevel()+1;
			this.showHud();
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".flevel", this.getFlierLevel());
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
	public int getFxp() 
	{
		return this.fxp;
	}
	public void coolDown()
	{
		taskCool2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
		{
			public void run()
			{	
				if(Changeling.this.getPlayer()!=null)
				{
					if(Changeling.this.getPlayer()!=null&&Changeling.this.cool>0.0)
					{
						Changeling.this.setCool(Changeling.this.getCool()-1);
					}
					BkCP.playerClass.getConfig().set(Changeling.this.getPlayer().getUniqueId()+".ftime", Changeling.this.getCool());
					BkCP.playerClass.saveConfig();
					BkCP.playerClass.reloadConfig();
					Changeling.this.coolDown();
				}
			}
		},20l);
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
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("Changeling")) {
			taskReEn = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
			{
				@SuppressWarnings("deprecation")
				public void run()
				{
					if(Changeling.this.getPlayer()!=null){
						if(Changeling.this.getPlayer()!=null&&Changeling.this.getPlayer().getGameMode()!=GameMode.SURVIVAL)
						{
							Changeling.this.setEnergy(Changeling.this.getEnergy()+20);
						}
						if(Changeling.this.getPlayer()!=null&&Changeling.this.cool>=0.0||Changeling.this.getEnergy()>=0.0)
						{
							Changeling.this.getPlayer().setAllowFlight(true);
						}
						if(Changeling.this.getPlayer()!=null&&Changeling.this.getEnergy()<=0.0)
						{
							Changeling.this.getPlayer().setAllowFlight(false);
						}
						if(Changeling.this.getPlayer()!=null&&Changeling.this.getPlayer().isFlying())
						{
							Changeling.this.setEnergy(Changeling.this.getEnergy()-(Glide.lCost));
						}
						else if(Changeling.this.getPlayer()!=null&&Changeling.this.getPlayer().isOnGround())
						{
							Changeling.this.setEnergy(Changeling.this.getEnergy()+((energyCap/1.5)*0.025));
						}
						if(Changeling.this.getPlayer()!=null&&!Changeling.this.getPlayer().isOnGround()&&!Changeling.this.getDisguised()&&Changeling.this.getEnergy()<20)
						{
							Changeling.this.setEnergy(Changeling.this.getEnergy()+(Glide.lCost/10));
						}
						if(Changeling.this.getPlayer()!=null&&Changeling.this.getDisguised()==true&&Changeling.this.getEnergy()<15)
						{
							Changeling.this.undisguise(Changeling.this.getPlayer());
						}
						if(Changeling.this.getPlayer()!=null&&Changeling.this.getDisguised())
						{
							Changeling.this.setEnergy(Changeling.this.getEnergy()-1.2);
						}
						Changeling.this.refreshEnergy(classID);
						Changeling.this.showHud();
					}
				}
			},10L);
		}
	}
	public void isGlide()
	{
		taskGlide = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
		{
			public void run()
			{
				if(Changeling.this.getPlayer()!=null){
					Player p = Changeling.this.getPlayer();
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
					Changeling.this.isGlide();
				}
			}
		},10);
	}
	public void stop() 
	{
		Bukkit.getScheduler().cancelTask(taskCool);
		Bukkit.getScheduler().cancelTask(taskCool2);
		Bukkit.getScheduler().cancelTask(taskGlide);
		Bukkit.getScheduler().cancelTask(taskReEn);
		Bukkit.getScheduler().cancelTask(taskReMa);
		Bukkit.getScheduler().cancelTask(taskSave);
	}



	public String getBranch(){return "default";}
	public void setBranch(String args) {}
	public void setLevel(int i) {}
	public int getLevel() {return 0;}
}
