package com.bexxkie.bkcp.classes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

public class Alicorn 
extends ClassBase
implements EnergyUser, MagicUser, Flier, EconInterface
{
	private List<String> SpecSpells;
	private String[] debN =
		{"default","corrupter","saviour"};
	private List<String> deNames = new ArrayList<String>(Arrays.asList(debN));
	private Map<String, Boolean> coolIDs = new HashMap<String, Boolean>();
	private Map<String, Integer> coolTime = new HashMap<String, Integer>();
	private String branch;
	private String cubCol;
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


	public Alicorn(String name) 
	{
		super(name);
		this.classID = 4;
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

		this.branch = BkCP.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".branch");
		if(this.getBranch().equalsIgnoreCase("custom"))
		{
			this.cubCol = BkCP.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".branchCustom.color");
			//this.Branch = PpackPP.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".branchCustom.name");
			this.SpecSpells = BkCP.playerClass.getConfig().getStringList(this.getPlayer().getUniqueId()+".spells");
			this.prefix = "["+this.cubCol.replaceAll("&", "§")+ChatColor.RESET+ChatColor.YELLOW+"Alicorn"+ChatColor.RESET+"]";
		}else{
			this.prefix = BkCP.advCfg.getConfig().getString("Prefixes.Alicorn."+branch).replaceAll("&", "§");
			this.SpecSpells = BkCP.BranchSpells.getConfig().getStringList("ac."+branch+".spells");
		}
		//this.prefix = PpackPP.advCfg.getConfig().getString("Prefixes.Alicorn."+branch).replaceAll("&", "§");
		//this.SpecSpells = PpackPP.BranchSpells.getConfig().getStringList(branch+".spells");
		this.manaCap = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".maxMana");
		this.mana = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".mana");
		this.energyCap = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".maxEnergy");
		this.energy = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".energy");
		this.mLevel = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".mlevel");
		this.fLevel = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".flevel");
		this.selSpell = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".currentSpell");
		this.saveTime = BkCP.advCfg.getConfig().getInt("classDataAutoSave");
		this.fxp = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".fxp");
		this.fxpTarget = BkCP.advCfg.getConfig().getInt("fxpTarget");
		this.cool = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".ftime");
		this.maxHealth = BkCP.advCfg.getConfig().getInt("Classes.Alicorn.maxHealth"); 
		this.getPlayer().setMaxHealth(maxHealth);
		if (BkCP.econEnabled==true)
		{
			pGol = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".currency.gold");
			pGem = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".currency.gem");
			pCur = pGol+"::"+pGem;
		}
		this.refreshMana(4);
		this.refreshEnergy(4);
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
		if(Alicorn.this.getPlayer()!=null&&this.getClassNm().equalsIgnoreCase("Alicorn")){
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard ali = manager.getNewScoreboard();
			Objective alis = ali.registerNewObjective("alicorn", this.getPlayer().getUniqueId().toString());
			alis.setDisplaySlot(DisplaySlot.SIDEBAR);
			String title = "";

			if(!this.deNames.contains(this.getBranch()))
			{
				title = this.getCubCol().replaceAll("&", "§")+ChatColor.RESET;
			}else
			{
				title = this.prefix.substring(0,this.prefix.indexOf("A")).replace("[", "").replace("]", "");
			}
			//if(this.getPlayer().getDisplayName().length() <31)
			//{
			//	alis.setDisplayName(title+this.getPlayer().getDisplayName()+title);
			//}else{
			//	alis.setDisplayName(title+this.getPlayer().getName()+title);
			//}
				alis.setDisplayName(title+"Stats"+title);
			if (BkCP.econEnabled==true)
			{
				Score score2 = alis.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "GLD: "+ChatColor.DARK_PURPLE + this.hudCur()));
				score2.setScore(-5);
			}
			Score sfxp = alis.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FXP: "+ChatColor.AQUA +getFxp()));
			sfxp.setScore(-4);
			Double mRegen = (((this.manaCap/1.5)*0.025)*2);
			Double fRegen = (((this.energyCap/1.5)*0.025)*2);
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			Score score2 = alis.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "MNP: "+ChatColor.DARK_PURPLE + numberFormat.format(this.getMana()).toString()));
			score2.setScore(3);
			Score score3 = alis.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ENP: "+ChatColor.DARK_PURPLE + numberFormat.format(this.getEnergy()).toString()));
			score3.setScore(2);
			if(this.coolIDs.containsKey(this.currentSpell().toLowerCase())&&this.getCool(this.currentSpell().toLowerCase())==true)
			{
				Score spell = alis.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD+ "SPL: "+ChatColor.RED + this.currentSpell()));
				spell.setScore(1);
			}else
			{
				Score spell = alis.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD+ "SPL: "+ChatColor.AQUA + this.currentSpell()));
				spell.setScore(1);
			}
			Score flt = alis.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FLT: "+ChatColor.AQUA+ChatColor.STRIKETHROUGH +numberFormat.format(this.cool)));
			if(this.cool>0)
			{
				flt = alis.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "FLT: "+ChatColor.AQUA +numberFormat.format(this.cool/60)));	
			}
			flt.setScore(0);
			Score manRegen = alis.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "MRG: "+ChatColor.DARK_PURPLE +"+" + numberFormat.format(mRegen)+"/s"));
			manRegen.setScore(-1);
			Score fltRegen = alis.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ERG: "+ChatColor.DARK_PURPLE +"+" + numberFormat.format(fRegen)+"/s"));
			fltRegen.setScore(-2);
			this.getPlayer().setScoreboard(ali);
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
	public String getCubCol()
	{
		return this.cubCol;
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
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("alicorn")) {
			taskReMa = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
			{
				public void run()
				{
					if(Alicorn.this.getPlayer()!=null){
						if(Alicorn.this.getPlayer()!=null&&Alicorn.this.getPlayer().getGameMode()!=GameMode.SURVIVAL)
						{
							Alicorn.this.setMana(Alicorn.this.getMana()+20);
						}
						Alicorn.this.setMana(Alicorn.this.getMana()+((manaCap/1.5)*0.025));
						Alicorn.this.refreshMana(classID);
						Alicorn.this.showHud();
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

	//-------
	public void setLevel()
	{
		setMagicLevel();	
	}
	//-------
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
				if(Alicorn.this.getPlayer()!=null){
					Player p = Alicorn.this.getPlayer();
					p.sendMessage(BkCP.prefix+"autosaving classData.");
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".branch", Alicorn.this.getBranch());
					if(Alicorn.this.getBranch().equalsIgnoreCase("custom"))
					{
						BkCP.playerClass.getConfig().set(p.getUniqueId()+".branch","custom");
					}

					BkCP.playerClass.getConfig().set(p.getUniqueId()+".mana", Alicorn.this.getMana());
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".flevel", Alicorn.this.getFlierLevel());
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".mlevel", Alicorn.this.getMagicLevel());
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".currentSpell", Alicorn.this.selSpell);
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".spells", Alicorn.this.SpecSpells);
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".energy", Alicorn.this.getEnergy());
					BkCP.playerClass.getConfig().set(p.getUniqueId()+".fxp", Alicorn.this.getFxp());
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
			taskCool = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
			{
				public void run()
				{
					if(Alicorn.this.getPlayer()!=null){
						if(Alicorn.this.getPlayer()!=null&&Alicorn.this.coolTime.get(spellID.toLowerCase())==0)
						{
							Alicorn.this.coolIDs.put(spellID.toLowerCase(), false);
							return;
						}
						Alicorn.this.coolTime.put(spellID.toLowerCase(), Alicorn.this.coolTime.get(spellID.toLowerCase())-1);
						setCool(spellID, Alicorn.this.coolTime.get(spellID.toLowerCase()));
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
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("alicorn")) {
			taskReEn = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
			{
				@SuppressWarnings("deprecation")
				public void run()
				{
					if(Alicorn.this.getPlayer()!=null){
						if(Alicorn.this.getPlayer()!=null&&Alicorn.this.getPlayer().getGameMode()!=GameMode.SURVIVAL)
						{
							Alicorn.this.setEnergy(Alicorn.this.getEnergy()+20);
						}
						if(Alicorn.this.getPlayer()!=null&&Alicorn.this.cool>=0.0||Alicorn.this.getEnergy()>=0.0)
						{
							Alicorn.this.getPlayer().setAllowFlight(true);
						}
						if(Alicorn.this.getPlayer()!=null&&Alicorn.this.getEnergy()<=0.0)
						{
							Alicorn.this.getPlayer().setAllowFlight(false);
						}
						if(Alicorn.this.getPlayer()!=null&&Alicorn.this.getPlayer().isFlying())
						{
							Alicorn.this.setEnergy(Alicorn.this.getEnergy()-(Glide.lCost));
						}
						else if(Alicorn.this.getPlayer()!=null&&Alicorn.this.getPlayer().isOnGround())
						{
							Alicorn.this.setEnergy(Alicorn.this.getEnergy()+((energyCap/1.5)*0.025));
						}
						if(Alicorn.this.getPlayer()!=null&&!Alicorn.this.getPlayer().isOnGround()&&Alicorn.this.getEnergy()<20)
						{
							Alicorn.this.setEnergy(Alicorn.this.getEnergy()+(Glide.lCost/10));
						}
						Alicorn.this.refreshEnergy(classID);
						Alicorn.this.showHud();
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
				if(Alicorn.this.getPlayer()!=null){
					Player p = Alicorn.this.getPlayer();
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
									p.playSound(p.getLocation(),Sound.ENTITY_ENDERDRAGON_FLAP, 1, (float) (9*v.getY()+Math.random()));
									p.setVelocity(v);
									p.setVelocity(d);
									p.setFallDistance(0);
								}
								p.getWorld().playEffect(p.getLocation().getBlock().getRelative(BlockFace.DOWN,3).getLocation(), Effect.STEP_SOUND, p.getLocation().getBlock().getRelative(BlockFace.DOWN,3).getType());
							}
						}
					}
					Alicorn.this.isGlide();
				}
			}
		},10);
	}

	public void coolDown()
	{
		taskCool2 = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
		{
			public void run()
			{	
				if(Alicorn.this.getPlayer()!=null)
				{
					if(Alicorn.this.cool>0.0)
					{
						Alicorn.this.setCool(Alicorn.this.getCool()-1);
					}
					BkCP.playerClass.getConfig().set(Alicorn.this.getPlayer().getUniqueId()+".ftime", Alicorn.this.getCool());
					BkCP.playerClass.saveConfig();
					BkCP.playerClass.reloadConfig();
					Alicorn.this.coolDown();
				}
			}
		},20l);
	}

	public String getBranch() 
	{
		return this.branch;
	}


	public void setBranch(String bName) 
	{
		if(!bName.equalsIgnoreCase("custom"))
		{
			this.branch = bName;
			this.SpecSpells.clear();
			this.SpecSpells = BkCP.BranchSpells.getConfig().getStringList(bName+".spells");
			this.selSpell = 0;
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".branch", this.branch);
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			this.prefix = BkCP.advCfg.getConfig().getString("Prefixes.Alicorn."+branch).replaceAll("&", "§");
		}else if(bName.equalsIgnoreCase("custom"))
		{
			this.branch = bName;
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".branch", this.branch);
			this.cubCol = BkCP.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".branchCustom.color");
			//this.Branch = PpackPP.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".branchCustom.name");
			this.SpecSpells = BkCP.playerClass.getConfig().getStringList(this.getPlayer().getUniqueId()+".spells");
			this.prefix = "["+this.cubCol.replaceAll("&", "§")+ChatColor.RESET+ChatColor.YELLOW+"Alicorn"+ChatColor.RESET+"]";
		}

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
	//----
	public void setLevel(int i) {}
	//----
	public int getLevel() {return 0;}
	//----
}
