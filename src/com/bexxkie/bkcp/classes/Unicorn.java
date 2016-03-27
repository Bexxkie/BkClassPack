package com.bexxkie.bkcp.classes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.classes.interfaces.ClassBase;
import com.bexxkie.bkcp.classes.interfaces.MagicUser;
import com.bexxkie.bkcp.modules.economy.interfaces.EconInterface;


public class Unicorn 
extends ClassBase
implements MagicUser, EconInterface
{

	private List<String> SpecSpells;
	//defaultBranch names (hard-Coded)
	private String[] debN =
		{"default","protector","healer","illusionist","destruction","fire","ice","shadow"};
	private Map<String, Boolean> coolIDs = new HashMap<String, Boolean>();
	private Map<String, Integer> coolTime = new HashMap<String, Integer>();
	private List<String> deNames = new ArrayList<String>(Arrays.asList(debN));
	private String Branch;
	private String cubCol;
	private double mana;
	private double manaCap;
	private int selSpell = 0;
	private int level = 0;
	private int saveTime;
	private int pGol;
	private int pGem;
	private String pCur;
	private double maxHealth;
	public int taskReMa;
	public int taskCool;
	public int taskSave;

	public Unicorn(String name)
	{
		super(name);
		this.classID = 1;
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
		this.coolIDs.put("recalli", false);      this.coolTime.put("recalli", 0);
		this.coolIDs.put("recallii", false);      this.coolTime.put("recallii", 0);  

		this.Branch = BkCP.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".branch");
		if(this.getBranch().equalsIgnoreCase("custom"))
		{
			this.cubCol = BkCP.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".branchCustom.color");
			//this.Branch = PpackPP.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".branchCustom.name");
			this.SpecSpells = BkCP.playerClass.getConfig().getStringList(this.getPlayer().getUniqueId()+".spells");
			this.prefix = "["+this.cubCol.replaceAll("&", "§")+ChatColor.RESET+ChatColor.GOLD+"Unicorn"+ChatColor.RESET+"]";
		}else{
			this.prefix = BkCP.advCfg.getConfig().getString("Prefixes.Unicorn."+Branch).replaceAll("&", "§");
			this.SpecSpells = BkCP.BranchSpells.getConfig().getStringList(Branch+".spells");
		}
		this.manaCap = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".maxMana");
		this.mana = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId()+".mana");
		this.level = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".level");
		this.selSpell = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId()+".currentSpell");
		this.saveTime = BkCP.advCfg.getConfig().getInt("classDataAutoSave");
		this.maxHealth = BkCP.advCfg.getConfig().getInt("Classes.Unicorn.maxHealth"); 
		this.getPlayer().setMaxHealth(maxHealth);
		if (BkCP.econEnabled==true)
		{
			//System.out.print(BkCP.econEnabled);
			pGol = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".currency.gold");
			pGem = BkCP.playerClass.getConfig().getInt(this.getPlayer().getUniqueId().toString()+".currency.gem");
			pCur = pGol+"::"+pGem;
		}
		
		refreshMana(1);
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
		if(Unicorn.this.getPlayer()!=null&&this.getClassNm().equalsIgnoreCase("unicorn")){
			ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard uni = manager.getNewScoreboard();
			Objective unis = uni.registerNewObjective("unicorn", this.getPlayer().getUniqueId().toString());
			unis.setDisplaySlot(DisplaySlot.SIDEBAR);
			String title = "";

			if(!this.deNames.contains(this.getBranch()))
			{
				title = this.getCubCol().replaceAll("&", "§")+ChatColor.RESET;
			}else
			{
				title = this.prefix.substring(0,this.prefix.indexOf("U")).replace("[", "").replace("]", "");
			}
			/*if(this.getPlayer().getDisplayName().length() <31)
			{
				unis.setDisplayName(title+this.getPlayer().getDisplayName()+title);
			}else{*/
				//unis.setDisplayName(title+this.getPlayer().getName()+title);
			//}
				unis.setDisplayName(title+"Stats"+title);
			if(BkCP.econEnabled)
			{
				Score score2 = unis.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "GLD: "+ChatColor.DARK_PURPLE + this.hudCur()));
				score2.setScore(-5);
			}
			Double regen = (((this.manaCap/1.4)*0.025)*2);
			DecimalFormat numberFormat = new DecimalFormat("#.00");
			Score score2 = unis.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "MNP: "+ChatColor.DARK_PURPLE + numberFormat.format(this.getMana()).toString()));
			score2.setScore(1);
			if(this.coolIDs.containsKey(this.currentSpell().toLowerCase())&&this.getCool(this.currentSpell().toLowerCase())==true)
			{
				Score spell = unis.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD+ "SPL: "+ChatColor.RED + this.currentSpell()));
				spell.setScore(0);
			}else
			{
				Score spell = unis.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD+ "SPL: "+ChatColor.AQUA + this.currentSpell()));
				spell.setScore(0);
			}
			Score level = unis.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "MRG: "+ChatColor.DARK_PURPLE +"+" + numberFormat.format(regen)+"/s"));
			level.setScore(-1);
			this.getPlayer().setScoreboard(uni);
		}
	}
	public int getCur(String type)
	{
		switch(type.toLowerCase())
		{
		case "gold":
			return this.pGol;
		case "gem":
			return this.pGem;
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
		if ((getPlayer() != null)&&(getPlayer().isOnline())&&this.getClassNm().equalsIgnoreCase("unicorn")) {
			taskReMa = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
			{
				public void run()
				{
					if(Unicorn.this.getPlayer()!=null){
						if(Unicorn.this.getPlayer()!=null&&Unicorn.this.getPlayer().isOnline()&&Unicorn.this.getPlayer().getGameMode()!=GameMode.SURVIVAL)
						{
							Unicorn.this.setMana(Unicorn.this.getMana()+20);
						}
						Unicorn.this.setMana(Unicorn.this.getMana()+((manaCap/1.4)*0.025));
						Unicorn.this.refreshMana(classID);
						Unicorn.this.showHud();
					}
				}
			},10L);
		}
	}
	public String getBranch()
	{
		return this.Branch;
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
	public void getSpells() 
	{
		getPlayer().sendMessage(BkCP.prefix+this.SpecSpells);
	}
	public void setBranch(String bName)
	{
		if(!bName.equalsIgnoreCase("custom"))
		{
			this.Branch = bName;
			this.selSpell = 0;
			this.SpecSpells.clear();
			this.SpecSpells = BkCP.BranchSpells.getConfig().getStringList(bName+".spells");
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".branch", this.Branch);
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			this.prefix = BkCP.advCfg.getConfig().getString("Prefixes.Unicorn."+Branch).replaceAll("&", "§");
		}else if(bName.equalsIgnoreCase("custom"))
		{
			this.Branch = bName;
			this.selSpell = 0;
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".branch", this.Branch);
			this.cubCol = BkCP.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".branchCustom.color");
			//this.Branch = PpackPP.playerClass.getConfig().getString(this.getPlayer().getUniqueId()+".branchCustom.name");
			this.SpecSpells = BkCP.playerClass.getConfig().getStringList(this.getPlayer().getUniqueId()+".spells");
			this.prefix = "["+this.cubCol.replaceAll("&", "§")+ChatColor.RESET+ChatColor.GOLD+"Unicorn"+ChatColor.RESET+"]";
		}

	}
	public int getLevel() 
	{
		return this.level;
	}
	public void setLevel() 
	{
		if(level <10)
		{
			this.level = this.getLevel()+1;
			this.showHud();
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".level", this.getLevel());
			BkCP.playerClass.getConfig().set(this.getPlayer().getUniqueId().toString()+".maxMana",manaCap+5);
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			this.manaCap = BkCP.playerClass.getConfig().getDouble(this.getPlayer().getUniqueId().toString()+".maxMana");
		}else
		{
			this.getPlayer().sendMessage(BkCP.prefix+"You cannot learn anything more from these tomes, you destroyed the material.");
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
					if(Unicorn.this.getPlayer()!=null){
						if(Unicorn.this.getPlayer()!=null&&Unicorn.this.coolTime.get(spellID.toLowerCase())==0)
						{
							Unicorn.this.coolIDs.put(spellID.toLowerCase(), false);
							return;
						}
						Unicorn.this.coolTime.put(spellID.toLowerCase(), Unicorn.this.coolTime.get(spellID.toLowerCase())-1);
						setCool(spellID, Unicorn.this.coolTime.get(spellID.toLowerCase()));
					}
				}
			},20L);
		}
	}
	public boolean getCool(String spellID)
	{
		return coolIDs.get(spellID.toLowerCase());
	}

	public void saveFile()
	{
		Player p = Unicorn.this.getPlayer();
		p.sendMessage(BkCP.prefix+"autosaving classData.");
		BkCP.playerClass.getConfig().set(p.getUniqueId()+".branch", Unicorn.this.getBranch());
		if(Unicorn.this.getBranch().equalsIgnoreCase("custom"))
		{
			BkCP.playerClass.getConfig().set(p.getUniqueId()+".branch","custom");
		}

		BkCP.playerClass.getConfig().set(p.getUniqueId()+".mana", Unicorn.this.getMana());
		BkCP.playerClass.getConfig().set(p.getUniqueId()+".level", Unicorn.this.getLevel());
		BkCP.playerClass.getConfig().set(p.getUniqueId()+".currentSpell", Unicorn.this.selSpell);
		BkCP.playerClass.getConfig().set(p.getUniqueId()+".spells", Unicorn.this.SpecSpells);
		BkCP.playerClass.saveConfig();
		BkCP.playerClass.reloadConfig();
	}
	public void saveAll()
	{
		taskSave = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
		{
			public void run()
			{
				if(Unicorn.this.getPlayer()!=null)
				{
					saveFile();
					saveAll();
				}
			}
		},saveTime);
	}
	public void stop()
	{
		Bukkit.getScheduler().cancelTask(taskCool);
		Bukkit.getScheduler().cancelTask(taskReMa);
		Bukkit.getScheduler().cancelTask(taskSave);
	}
}
