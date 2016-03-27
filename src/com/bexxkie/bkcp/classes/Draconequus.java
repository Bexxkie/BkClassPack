package com.bexxkie.bkcp.classes;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.bexxkie.bkcp.classes.interfaces.EnergyUser;
import com.bexxkie.bkcp.classes.interfaces.Flier;
import com.bexxkie.bkcp.classes.interfaces.MagicUser;
import com.bexxkie.bkcp.interaction.Glide;
import com.bexxkie.bkcp.modules.economy.interfaces.EconInterface;
import com.bexxkie.bkcp.util.RandInt;

public class Draconequus extends ClassBase
implements EnergyUser, MagicUser, Flier, EconInterface
{
	private List<String> SpecSpells;
	//private String[] debN =
	//	{"default","corrupter","saviour"};
	//private List<String> deNames = new ArrayList<String>(Arrays.asList(debN));
	private Map<String, Boolean> coolIDs = new HashMap<String, Boolean>();
	private Map<String, Integer> coolTime = new HashMap<String, Integer>();
	//private String branch;
	//private String cubCol;
	private double mana;
	private double manaCap;
	private double energy;
	private double energyCap;
	private int selSpell;
	private int mLevel;
	private int fLevel;
	private int saveTime;
	private double cool;
	private int fxp;
	private int fxpTarget;
	private int pGol;
	private int pGem;
	private String pCur;
	private double maxHealth;
	public int taskReEn;
	public int taskReMa;
	public int taskGlide;
	public int taskCool;
	public int taskCool2;
	public int taskSave;


	public Draconequus(String name) {
		super(name);
		this.classID = 9;
		this.coolIDs.clear();
		this.coolIDs.put("potionrain", false);       this.coolTime.put("potionrain", 0);
		this.coolIDs.put("tntrain", false);       this.coolTime.put("tntrain", 0);
		this.coolIDs.put("throwcake", false);    this.coolTime.put("throwcake", 0);
		this.coolIDs.put("creeperrain", false);   this.coolTime.put("creeperrain", 0);
		this.coolIDs.put("throwfish", false);   this.coolTime.put("throwfish", 0);
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
		
		this.prefix = BkCP.advCfg.getConfig().getString("Prefixes.Draconequus.default").replaceAll("&", "§");
		this.manaCap = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".maxMana");
		this.mana = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".mana");
		this.energyCap = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".maxEnergy");
		this.energy = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".energy");
		this.mLevel = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".mlevel");
		this.fLevel = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".flevel");
		this.selSpell = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".currentSpell");
		this.SpecSpells = BkCP.playerClass.getConfig().getStringList(this.getPlayer().getUniqueId()+".spells");
		this.saveTime = BkCP.advCfg.getConfig().getInt("classDataAutoSave");
		this.fxp = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".fxp");
		this.fxpTarget = BkCP.advCfg.getConfig().getInt("fxpTarget");
		this.cool = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".ftime");
		this.maxHealth = BkCP.advCfg.getConfig().getInt("Classes.Draconequus.maxHealth"); 

		this.getPlayer().setMaxHealth(maxHealth);
		if (BkCP.econEnabled==true)
		{
			pGol = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".currency.gold");
			pGem = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".currency.gem");
			pCur = pGol+"::"+pGem;
		}
		this.refreshMana(9);
		this.refreshEnergy(9);
		this.showHud();
		this.saveAll();
		this.coolDown();


	}

	public String getClassNm()
	{
		return BkCP.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".class");
	}
	@SuppressWarnings("deprecation")
	public void showHud()
	{
		if(Draconequus.this.getPlayer()!=null&&this.getClassNm().equalsIgnoreCase("Draconequus")){
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard drc = manager.getNewScoreboard();
			Objective drcs = drc.registerNewObjective("Draconequus", this.getPlayer().getUniqueId().toString());
			drcs.setDisplaySlot(DisplaySlot.SIDEBAR);
			String title = "";

			title = this.prefix.substring(0,this.prefix.indexOf("D")).replace("[", "").replace("]", "");

			//if(this.getPlayer().getDisplayName().length() <31)
			//{
			//	drcs.setDisplayName(title+this.getPlayer().getDisplayName()+title);
			//}else{
			//	drcs.setDisplayName(title+this.getPlayer().getName()+title);
			//}
				drcs.setDisplayName(title+"Stats"+title);
			if (BkCP.econEnabled==true)
			{
				Score score2 = drcs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "GLD: "+ChatColor.DARK_PURPLE + this.hudCur()));
				score2.setScore(-5);
			}
			Score sfxp = drcs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FXP: "+ChatColor.AQUA +getFxp()));
			sfxp.setScore(-4);
			Double mRegen = (((this.manaCap/1.5)*0.025)*2);
			Double fRegen = (((this.energyCap/1.5)*0.025)*2);
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			Score score2 = drcs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "MNP: "+ChatColor.DARK_PURPLE + numberFormat.format(this.getMana()).toString()));
			score2.setScore(3);
			Score score3 = drcs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ENP: "+ChatColor.DARK_PURPLE + numberFormat.format(this.getEnergy()).toString()));
			score3.setScore(2);
			if(Draconequus.this.coolIDs.containsKey(this.currentSpell().toLowerCase())&&this.getCool(this.currentSpell().toLowerCase())==true)
			{
				Score spell = drcs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD+ "SPL: "+ChatColor.RED + this.currentSpell()));
				spell.setScore(1);
			}else
			{
				Score spell = drcs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD+ "SPL: "+ChatColor.AQUA + this.currentSpell()));
				spell.setScore(1);
			}
			Score flt = drcs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FLT: "+ChatColor.AQUA+ChatColor.STRIKETHROUGH +numberFormat.format(this.cool)));
			if(this.cool>0)
			{
				flt = drcs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FLT: "+ChatColor.AQUA +numberFormat.format(this.cool/60)));	
			}
			flt.setScore(0);
			Score manRegen = drcs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "MRG: "+ChatColor.DARK_PURPLE +"+" + numberFormat.format(mRegen)+"/s"));
			manRegen.setScore(-1);
			Score fltRegen = drcs.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ERG: "+ChatColor.DARK_PURPLE +"+" + numberFormat.format(fRegen)+"/s"));
			fltRegen.setScore(-2);
			this.getPlayer().setScoreboard(drc);
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

	public void setCool(double intCool) 
	{
		this.cool = intCool;
	}

	public double getCool()
	{
		return this.cool;
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

	public int getFxp() 
	{
		return this.fxp;
	}

	public void coolDown()
	{
		taskCool = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
		{
			public void run()
			{	
				if(Draconequus.this.getPlayer()!=null)
				{
					if(Draconequus.this.getPlayer()!=null&&Draconequus.this.cool>0.0)
					{
						Draconequus.this.setCool(Draconequus.this.getCool()-1);
					}
					BkCP.playerClass.getConfig().set(Draconequus.this.getPlayer().getUniqueId()+".ftime", Draconequus.this.getCool());
					BkCP.playerClass.saveConfig();
					BkCP.playerClass.reloadConfig();
					Draconequus.this.coolDown();
				}
			}
		},20l);
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
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("Draconequus")) {
			taskReMa = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
			{
				public void run()
				{
					if(Draconequus.this.getPlayer()!=null){
						if(Draconequus.this.getPlayer()!=null&&Draconequus.this.getPlayer().getGameMode()!=GameMode.SURVIVAL)
						{
							Draconequus.this.setMana(Draconequus.this.getMana()+20);
						}
						Draconequus.this.setMana(Draconequus.this.getMana()+((manaCap/1.5)*0.025));
						Draconequus.this.showHud();
						Draconequus.this.refreshMana(classID);
					}
				}
			},10L);
		}
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

	public void saveAll()
	{
		taskSave = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
		{
			public void run()
			{
				if(Draconequus.this.getPlayer()!=null){
					Player p = Draconequus.this.getPlayer();
					p.sendMessage(BkCP.prefix+"autosaving classData.");
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".branch", Draconequus.this.getBranch());
					if(Draconequus.this.getPlayer()!=null&&Draconequus.this.getBranch().equalsIgnoreCase("custom"))
					{
						BkCP.playerClass.getConfig().set(p.getUniqueId()+".branch","custom");
					}

					BkCP.playerClass.getConfig().set(p.getUniqueId()+".mana", Draconequus.this.getMana());
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".flevel", Draconequus.this.getFlierLevel());
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".mlevel", Draconequus.this.getMagicLevel());
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".currentSpell", Draconequus.this.selSpell);
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".spells", Draconequus.this.SpecSpells);
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".energy", Draconequus.this.getEnergy());
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".fxp", Draconequus.this.getFxp());
					BkCP.playerClass.saveConfig();
					BkCP.playerClass.reloadConfig();
					saveAll();
				}
			}
		},saveTime);
	}

	public void addSpell(String sName) 
	{
		if(!this.hasSpell(sName))
		{
			
			for(String sp : this.SpecSpells)
			{
				if(sName.length()>sp.length()&&sName.startsWith(sp)&&sName.length()<sp.length()+3)
				{

					SpecSpells.set(SpecSpells.indexOf(sp), sName);
					BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".spells", SpecSpells);
					BkCP.playerClass.saveConfig();
					BkCP.playerClass.reloadConfig();
					return;
				}
				if(sName.length()<sp.length()&&sp.startsWith(sName))
				{
					return;
				}
			}
			this.SpecSpells.add(sName);
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".spells", SpecSpells);
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
		}
		{
			
			for(String sp : this.SpecSpells)
			{
				if(sName.length()>sp.length()&&sName.startsWith(sp)&&sName.length()<sp.length()+3)
				{

					SpecSpells.set(SpecSpells.indexOf(sp), sName);
					BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".spells", SpecSpells);
					BkCP.playerClass.saveConfig();
					BkCP.playerClass.reloadConfig();
					return;
				}
				if(sName.length()<sp.length()&&sp.startsWith(sName))
				{
					return;
				}
			}
			this.SpecSpells.add(sName);
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".spells", SpecSpells);
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
		}

	}

	public void remSpell(String sName) 
	{
		if(this.hasSpell(sName))
		{
			
			this.SpecSpells.remove(sName);
			this.selSpell = 0;
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".spells", SpecSpells);
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
		}

	}

	public void setCool(final String spellID, int coolTime) 
	{
		if(this.coolIDs.containsKey((spellID.toLowerCase())))
		{
			if(this.coolIDs.get(spellID.toLowerCase())==false)
			{
				this.coolIDs.put(spellID.toLowerCase(), true);
				this.coolTime.put(spellID.toLowerCase(), coolTime);

			}
			taskCool2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
			{
				public void run()
				{
					if(Draconequus.this.getPlayer()!=null)
					{
						if(Draconequus.this.getPlayer()!=null&&Draconequus.this.coolTime.get(spellID.toLowerCase())==0)
						{
							Draconequus.this.coolIDs.put(spellID.toLowerCase(), false);
							return;
						}
						Draconequus.this.coolTime.put(spellID.toLowerCase(), Draconequus.this.coolTime.get(spellID.toLowerCase())-1);
						setCool(spellID, Draconequus.this.coolTime.get(spellID.toLowerCase()));
					}
				}
			},20L);
		}

	}

	public boolean getCool(String spellID) 
	{
		return coolIDs.get(spellID.toLowerCase());
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
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("Draconequus")) {
			taskReEn = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
			{
				@SuppressWarnings("deprecation")
				public void run()
				{
					if(Draconequus.this.getPlayer()!=null){
						if(Draconequus.this.getPlayer()!=null&&Draconequus.this.getPlayer().getGameMode()!=GameMode.SURVIVAL)
						{
							Draconequus.this.setEnergy(Draconequus.this.getEnergy()+20);
						}
						if(Draconequus.this.getPlayer()!=null&&Draconequus.this.cool>=0.0||Draconequus.this.getEnergy()>=0.0)
						{
							Draconequus.this.getPlayer().setAllowFlight(true);
						}
						if(Draconequus.this.getPlayer()!=null&&Draconequus.this.getEnergy()<=0.0)
						{
							Draconequus.this.getPlayer().setAllowFlight(false);
						}
						if(Draconequus.this.getPlayer()!=null&&Draconequus.this.getPlayer().isFlying())
						{
							Draconequus.this.setEnergy(Draconequus.this.getEnergy()-(Glide.lCost));
						}
						if(Draconequus.this.getPlayer()!=null&&Draconequus.this.getPlayer().isOnGround())
						{
							Draconequus.this.setEnergy(Draconequus.this.getEnergy()+((energyCap/1.5)*0.025));
						}
						if(Draconequus.this.getPlayer()!=null&&!Draconequus.this.getPlayer().isOnGround()&&Draconequus.this.getEnergy()<20)
						{
							Draconequus.this.setEnergy(Draconequus.this.getEnergy()+(Glide.lCost/10));
						}
						Draconequus.this.showHud();
						Draconequus.this.refreshEnergy(classID);
					}
				}
			},10L);
		}
	}

	public int getFlierLevel()
	{
		return this.fLevel;
	}
	public int getMagicLevel()
	{
		return this.mLevel;
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
	public void isGlide()
	{
		taskGlide = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
		{
			public void run()
			{
				if(Draconequus.this.getPlayer()!=null){
					Player p = Draconequus.this.getPlayer();
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
					Draconequus.this.isGlide();
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

	//unused
	public void setLevel(int i) {}
	public int getLevel() {return 0;}
	public String getBranch() {return null;}
	public void setBranch(String args) {}
	public void setLevel() {}


}
