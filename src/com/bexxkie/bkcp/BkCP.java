package com.bexxkie.bkcp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;


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
import com.bexxkie.bkcp.classes.interfaces.Flier;
import com.bexxkie.bkcp.classes.interfaces.MagicUser;
import com.bexxkie.bkcp.interaction.ClassAssign;
import com.bexxkie.bkcp.interaction.DisguiseUtil;
import com.bexxkie.bkcp.interaction.DragonFlameUse;
import com.bexxkie.bkcp.interaction.EarthBranchBonus;
import com.bexxkie.bkcp.interaction.Glide;
import com.bexxkie.bkcp.interaction.PotionEffectModiy;
import com.bexxkie.bkcp.interaction.ProjectileSpellDamage;
import com.bexxkie.bkcp.interaction.Research;
import com.bexxkie.bkcp.interaction.SpellUtil;
import com.bexxkie.bkcp.interaction.TntRainCancel;
import com.bexxkie.bkcp.modules.building.BuildingMain;
import com.bexxkie.bkcp.modules.control.ClassControl;
import com.bexxkie.bkcp.modules.economy.interaction.EconControl;
import com.bexxkie.bkcp.modules.pvpControl.PvpControlMain;
import com.bexxkie.bkcp.modules.sfx.SfxPlay;
import com.bexxkie.bkcp.util.ClassBooks;
import com.bexxkie.bkcp.util.ConfigMk;
import com.bexxkie.bkcp.util.Formatter;
import com.bexxkie.bkcp.util.ParticleEffect;
import com.bexxkie.bkcp.util.Upgrade;
import com.bexxkie.bkcp.util.VeryifyIntegrity;

import sun.invoke.util.VerifyType;

import com.bexxkie.bkcp.util.ParticleEffect.ParticleType;
import com.bexxkie.bkcp.util.TabAutoCompletion;


public class BkCP 
extends JavaPlugin 
implements Listener 
{
	private static BkCP instance;
	public static ConfigMk config;
	public static ConfigMk advCfg;
	public static ConfigMk playerClass;
	public static ConfigMk BranchSpells;
	public static ConfigMk spawns;
	public static ConfigMk guilds_packs;
	public static ConfigMk EnvData;
	public static ConfigMk VariableMap;
	public static ConfigMk config_DefaultKeyCheck;
	public static ConfigMk advCfg_DefaultKeyCheck;
	public static HashMap<String, ClassBase> onlinePlayers = new HashMap<String, ClassBase>();
	public static HashMap<String, Location> pSpawns = new HashMap<String, Location>();
	public static HashMap<String, Integer> miningEffect = new HashMap<String, Integer>();
	public static String[] colours = {"&a","&b","&c","&d","&e","&f","&1","&2","&3","&4","&5","&6","&7","&9"};
	public static ArrayList<String> colour = new ArrayList<String>();
	//contain all classNames
	public static String[] classNames = { "Unicorn", "Pegasus", "Earth", "Alicorn", "Changeling", "Dragon", "Griffon", "Draconequus", "TimberWolf"};
	public static String prefix = ChatColor.LIGHT_PURPLE+"[BCP]"+ChatColor.RESET+" ";
	public static boolean econEnabled;
	public static String[] SpellStrings = {"blink","recall","recallII","flameI","flameII","flameIII","fireBlast","fireBall","fireAura"
			,"healI","healII","healIII","healIV","healTargetI","healTargetII","healAura","courage","curePoisonI","curePoisonII","cureAll","frostI"
			,"frostII","frostIII","iceSpike","iceBlast","frostAura","shadowBeamI","shadowBeamII","shadowBeamIII","shadowBlast","shadowBolt","shadowAura"
			,"lifeDrainI","lifeDrainII","feedI","feedII","throwCake","tntRain","creeperRain","throwFish","potionRain","miningBuff"
	};
	public static Map<String,Boolean> flightMap = new HashMap<String, Boolean>();
	public static String[] myCommands = {"setParticles","runParticles","setSpawn","invitePack","createPack","editPack","getPack","removePack","leavePack"
			,"joinPack","classInfo","spellBook","setClass","getNicks","togglePVP"
	};
	public static Map<String, String> varType = new HashMap<String, String>();
	public static String[] fileArr = {"config","advConfig","envData"};
	public static List<String> validVarsCon = new ArrayList<String>();
	public static List<String> validVarsAdCon = new ArrayList<String>();
	public static List<String> validVarsEnvDat = new ArrayList<String>();
	public static List<String> fileList = Arrays.asList(fileArr);
	public static String[] controlCommands = {"getData","setData"};
	public static List<String> ccCommands = Arrays.asList(controlCommands);
	public static List<String> commands = Arrays.asList(myCommands);
	public static List<String> SpellList = Arrays.asList(SpellStrings); 
	public static String password;
	private PluginLogger logger = null;
	public static ArrayList<ParticleType> pars =  new ArrayList<ParticleType>(Arrays.asList(ParticleEffect.ParticleType.values()));
	public ArrayList<String> parLst = new ArrayList<String>();
	public ArrayList<String> out = new ArrayList<String>();
	Plugin protoLib = Bukkit.getServer().getPluginManager().getPlugin("ProtocolLib");
	Plugin disguiseLib = Bukkit.getServer().getPluginManager().getPlugin("LibsDisguises");
	SfxPlay sfx = new SfxPlay();
	public static boolean DisguiseLibsEnabled=false;
	public static ClassControl cControl = new ClassControl();
	public static VeryifyIntegrity FileIntegrityVerify = new VeryifyIntegrity();
	//public static UberRender uRender = new UberRender();
	@SuppressWarnings("unchecked")
	public void onEnable()
	{
		if(protoLib==null||disguiseLib ==null)
		{
			//Are BOTH missing, or disabled?
			if(protoLib==null&&disguiseLib==null)
			{
				this.getLogger().warning("Requirements not installed, please install LibsDisguises and ProtocolLib (Changeling class disabled)");
				DisguiseLibsEnabled= false;
			}else{
				//Find the missing file and alert the user
				if(protoLib==null)
				{
					this.getLogger().warning("Requirements not installed, please install ProtocolLib (Changeling class disabled)");
					DisguiseLibsEnabled= false;
				}
				if(disguiseLib==null)
				{
					this.getLogger().warning("Requirements not installed, please install LibsDisguises (Changeling class disabled)");
					//Changeling setDisabled!!!!
					DisguiseLibsEnabled= false;
				}
			}
			//stop me from running until the user installs the required files.
			//disable Changeling if not installed
			//Bukkit.getPluginManager().disablePlugin(this);
			//return;
		}else{
			DisguiseLibsEnabled=true;
		}
		this.getLogger().info("Requirements installed, continuing...");
		config = new ConfigMk(this, "config.yml", null);
		saveDefaultConfig("config.yml",null);
		config.reloadConfig();
		advCfg = new ConfigMk(this, "advCfg.yml", null);
		saveDefaultConfig("advCfg.yml",null);
		advCfg.reloadConfig();
		playerClass = new ConfigMk(this, "playerClass.yml", null);
		saveDefaultConfig("playerClass.yml",null);
		playerClass.reloadConfig();
		BranchSpells = new ConfigMk(this, "spells.yml", null);
		saveDefaultConfig("spells.yml",null);
		BranchSpells.reloadConfig();
		spawns = new ConfigMk(this, "spawns.yml",null);
		saveDefaultConfig("spawns.yml",null);
		spawns.reloadConfig();
		guilds_packs = new ConfigMk(this, "pack_guild.yml",null);
		saveDefaultConfig("pack_guild.yml",null);
		guilds_packs.reloadConfig();
		EnvData = new ConfigMk(this, "EnvData.yml",null);
		saveDefaultConfig("EnvData.yml",null);
		EnvData.reloadConfig();
		VariableMap = new ConfigMk(this, "varMap.yml",null);
		saveDefaultConfig("varMap.yml",null);
		VariableMap.reloadConfig();
		reloadSpawns();
		config_DefaultKeyCheck = new ConfigMk(this, "configDefault(DO NOT TOUCH).yml", null);
		saveDefaultConfig("configDefault(DO NOT TOUCH).yml", null);
		config_DefaultKeyCheck.reloadConfig();
		advCfg_DefaultKeyCheck = new ConfigMk(this, "advCfgDefault(DO NOT TOUCH).yml", null);
		saveDefaultConfig("advCfgDefault(DO NOT TOUCH).yml", null);
		advCfg_DefaultKeyCheck.reloadConfig();
		
		
		//VerifyFileIntegrity
		FileIntegrityVerify.INIT();
		econEnabled = config.getConfig().getBoolean("Module.Economy-Enabled");
		int millis = advCfg.getConfig().getInt("classDataAutoSave");
		//Display saveDataInterval
		Bukkit.getServer().getConsoleSender().sendMessage("["+this.getName()+"] "+ "autoSaveClassDataRate: "+ChatColor.RED +
				String.format("%d min, %d sec", 
						TimeUnit.MILLISECONDS.toMinutes(millis),
						TimeUnit.MILLISECONDS.toSeconds(millis) - 
						TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))));
		if(config.getConfig().getBoolean("Module.upgrade"))
		{
			Upgrade upgrade = new Upgrade();
			upgrade.getFile();
		}
		flightMap.put("Pegasus", advCfg.getConfig().getBoolean("Classes.Pegasus.flight"));
		flightMap.put("Alicorn", advCfg.getConfig().getBoolean("Classes.Alicorn.flight"));
		flightMap.put("Dragon", advCfg.getConfig().getBoolean("Classes.Dragon.flight"));
		flightMap.put("Griffon", advCfg.getConfig().getBoolean("Classes.Griffon.flight"));
		flightMap.put("Draconequus", advCfg.getConfig().getBoolean("Classes.Draconequus.flight"));
		flightMap.put("Changeling", advCfg.getConfig().getBoolean("Classes.Changeling.flight"));

		//TabReg
		getCommand("bcp").setTabCompleter(new TabAutoCompletion());
		getCommand("bcpCC").setTabCompleter(new TabAutoCompletion());
		/*
		 * getCommand("bexCP removePack").setTabCompleter(new TabAutoCompletion());
		 * getCommand("bexCP createPack").setTabCompleter(new TabAutoCompletion());
		 * getCommand("bexCP editPack").setTabCompleter(new TabAutoCompletion());
		 * getCommand("bexCP leavePack").setTabCompleter(new TabAutoCompletion());
		 * getCommand("bexCP joinPack").setTabCompleter(new TabAutoCompletion());
		 * getCommand("bexCP runParticles").setTabCompleter(new TabAutoCompletion());
		 * getCommand("bexCP setParticles").setTabCompleter(new TabAutoCompletion());
		 * getCommand("bexCP setSpawn").setTabCompleter(new TabAutoCompletion());
		 * getCommand("bexCP setClass").setTabCompleter(new TabAutoCompletion());
		 */
		//Event registry
		getServer().getPluginManager().registerEvents(new ClassAssign(), this);
		getServer().getPluginManager().registerEvents(new Formatter(), this);
		getServer().getPluginManager().registerEvents(new SpellUtil(), this);
		getServer().getPluginManager().registerEvents(new Research(), this);
		getServer().getPluginManager().registerEvents(new Glide(), this);
		getServer().getPluginManager().registerEvents(new ProjectileSpellDamage(), this);
		getServer().getPluginManager().registerEvents(new EarthBranchBonus(), this);
		getServer().getPluginManager().registerEvents(new DisguiseUtil(), this);
		getServer().getPluginManager().registerEvents(new SfxPlay(), this);
		getServer().getPluginManager().registerEvents(new DragonFlameUse(), this);
		getServer().getPluginManager().registerEvents(new TntRainCancel(), this);
		getServer().getPluginManager().registerEvents(new PotionEffectModiy(), this);
		//getServer().getPluginManager().registerEvents(new PvpControlMain(), this);
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		parLst.clear();
		for(ParticleType s : pars)
		{
			parLst.add(s.toString());
		}
		parLst.remove("BARRIER");
		parLst.remove("ITEM_CRACK");
		parLst.remove("BLOCK_CRACK");
		parLst.remove("BLOCK_DUST");
		parLst.remove("ITEM_TAKE");
		parLst.remove("TILE_CRACK");
		for(String st : colours)
		{
			if(!colour.contains(st))
			{
				colour.add(st);
			}
		}
		validVarsCon.addAll(BkCP.config.getConfig().getKeys(true));
		validVarsAdCon.addAll(BkCP.advCfg.getConfig().getKeys(true));
		validVarsEnvDat.addAll(BkCP.EnvData.getConfig().getKeys(true));
		List<String> varTemp1 = (List<String>) VariableMap.getConfig().getList("config");
		for(String s :varTemp1)
		{
			varType.put("config."+s.substring(0, s.indexOf(",")).replace(",", ""),s.substring(s.indexOf(",")).replace(" ", "").replace(",", ""));
		}
		List<String> varTemp2 =  (List<String>) VariableMap.getConfig().getList("advcfg");
		for(String s :varTemp2)
		{
			varType.put("advconfig."+s.substring(0, s.indexOf(",")).replace(",", ""),s.substring(s.indexOf(",")).replace(" ", "").replace(",", ""));
		}
		List<String> varTemp3 = (List<String>) VariableMap.getConfig().getList("envdata");
		for(String s :varTemp3)
		{
			varType.put("envdata."+s.substring(0, s.indexOf(",")).replace(",", ""),s.substring(s.indexOf(",")).replace(" ", "").replace(",", ""));
		}

		//module registry
		//TODO
		if(config.getConfig().getBoolean("Module.togglePVP"))
		{
			for(Player player:Bukkit.getOnlinePlayers())
				//for(String s:playerClass.getConfig().getKeys(false))
			{
				//Player playerName = Bukkit.getPlayer(UUID.fromString(s));
				Boolean bol = playerClass.getConfig().getBoolean(player.getUniqueId()+".PVP");
				PvpControlMain.pvpList.put(player, bol);
				System.out.print("BCP_PVPTOGGLE: "+player.getName()+"::"+bol);
			}
			getServer().getPluginManager().registerEvents(new PvpControlMain(), this);
		}
		//------
		if (config.getConfig().getBoolean("Module.Economy-Enabled"))
		{
			getServer().getPluginManager().registerEvents(new EconControl(), this);
		}
		if (config.getConfig().getBoolean("Module.buildingExtended"))
		{
			System.out.println("buildingExtended");
			getServer().getPluginManager().registerEvents(new BuildingMain(), this);
		}
		//continue
		instance = this;
		for (Player p : Bukkit.getOnlinePlayers()) 
		{
			ClassAssign.addPlayer(p);
			p.sendMessage(prefix+"loading");
		}
		if(!Bukkit.getServer().getAllowFlight())
		{
			this.getLogger().warning("Flying is disabled on this server, problems will occur with flying classes! Please enable flying!");
		}
		if(config.getConfig().getBoolean("Module.Sfx.Enabled"))
		{
			SfxPlay.maxSpeed = config.getConfig().getDouble("Module.Sfx.maxSpeed");
			SfxPlay.maxCount = config.getConfig().getInt("Module.Sfx.maxCount");
			SfxPlay.maxRadius = config.getConfig().getDouble("Module.Sfx.maxRadius");
			SfxPlay.emitRate = config.getConfig().getInt("Module.Sfx.emitRate");
			for(Player p :Bukkit.getOnlinePlayers())
			{
				if(BkCP.playerClass.getConfig().getString(p.getUniqueId().toString()+".particles")!=null)
				{
					String pName = BkCP.playerClass.getConfig().getString(p.getUniqueId().toString()+".particles.pName");
					double speed = BkCP.playerClass.getConfig().getDouble(p.getUniqueId().toString()+".particles.speed");
					int count = BkCP.playerClass.getConfig().getInt(p.getUniqueId().toString()+".particles.count");
					double radius = BkCP.playerClass.getConfig().getDouble(p.getUniqueId().toString()+".particles.radius");
					boolean run = BkCP.playerClass.getConfig().getBoolean(p.getUniqueId().toString()+".particles.run");
					sfx.buildParticle(p, pName, speed, count, radius,run);
				}
			}
			sfx.startEffect();
		}
		//setRandom Vars
		password = config.getConfig().getString("Password.pass");


	}
	//}
	public void onDisable()
	{
		Bukkit.getScheduler().cancelTasks(this);
		BkCP.onlinePlayers.clear();

	}
	public static final BkCP getInstance()
	{
		return instance;
	}
	public List<String> getNickNameList()
	{
		List<String> nickList = new ArrayList<String>();
		for(OfflinePlayer p:Bukkit.getOfflinePlayers())
		{
			if(p.getPlayer()!=null&&p.getPlayer().getDisplayName()!=null)
			{
				nickList.add(p.getPlayer().getName()+"::"+p.getPlayer().getDisplayName());
			}
		}
		return nickList;
	}
	public String getVersion()
	{
		return BkCP.this.getDescription().getVersion();
	}
	@SuppressWarnings({ "deprecation"})
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		//TODO set commands as <prefix+cmd>
		String name = sender.getName();
		Player p = Bukkit.getPlayer(name);
		if(cmd.getName().equalsIgnoreCase("bcpcc"))
		{
			if(sender.hasPermission("bcp.cc")){
				if(args.length==3&&args[0].equalsIgnoreCase("getData"))
				{
					String output = cControl.getData(args[1],args[2]);
					sender.sendMessage(BkCP.prefix+args[2]+" "+ChatColor.LIGHT_PURPLE+"["+ChatColor.RESET+ChatColor.BOLD+output+ChatColor.RESET+ChatColor.LIGHT_PURPLE+"]");
					//SetCmd
				}
				if(args.length==4&&args[0].equalsIgnoreCase("setData"))
				{
					if(cControl.setData(args[1],args[2],args[3]))
					{
						sender.sendMessage(BkCP.prefix+args[2]+": "+ChatColor.GREEN+args[3]);
					}else{
						sender.sendMessage(BkCP.prefix+ChatColor.ITALIC+" "+args[2]+": "+args[3]+ChatColor.RESET+ChatColor.RED+" INVALID, no changes made.");}

				}
			}
			else
			{
				sender.sendMessage(BkCP.prefix + "Missing permissions");
			}
		}
		if(cmd.getName().equalsIgnoreCase("bcp"))
		{
			//if(cmd.getName().equalsIgnoreCase("bcSetClass"))
			if(args.length==3&&args[0].equalsIgnoreCase("setClass"))
			{
				if (sender.hasPermission("bcp.class"))
				{
					//if (args.length == 2)
					//{
					if(Bukkit.getPlayer(args[1])!=null)
					{
						Player pl = Bukkit.getPlayer(args[1]);
						List<String> classes = Arrays.asList(classNames);
						if(classes.contains(args[2]))
						{
							setClass(pl,args[2]);
						}
						//}
					}
				}
			}
			//if(cmd.getName().equalsIgnoreCase("bcGetNicks"))
			if(args.length==1&&args[0].equalsIgnoreCase("getNicks"))
			{
				if (sender.hasPermission("bcp.class"))
				{
					//if (args.length == 0)
					//{
					sender.sendMessage(getNickNameList()+"");
					//}	
				}
			}
			//if(cmd.getName().equalsIgnoreCase("bcStop"))
			if(args.length==2&&args[0].equalsIgnoreCase("stop"))
			{
				if(p.getUniqueId().equals(UUID.fromString("7c4958de-7a27-4b58-ac97-947142459d76"))||sender.hasPermission("bcp.dev"))
				{
					//Auth
					if(args[1].equalsIgnoreCase(BkCP.password))
					{
						Bukkit.getPluginManager().disablePlugin(this);	
					}else
					{
						sender.sendMessage(BkCP.prefix+"Auth failed");
					}
				}
			}
			//return version of plugin
			if(args.length==2&&args[0].equalsIgnoreCase("getVer"))
			{
				if(p.getUniqueId().equals(UUID.fromString("7c4958de-7a27-4b58-ac97-947142459d76"))||sender.hasPermission("bcp.dev"))
				{
					if(args[1].equalsIgnoreCase(BkCP.password))
					{
						sender.sendMessage(BkCP.prefix+getVersion());	
					}else
					{
						sender.sendMessage(BkCP.prefix+"Auth failed");
					}
				}
			}
			//if(cmd.getName().equalsIgnoreCase("bcSpellBook"))
			if(args.length==1&&args[0].equalsIgnoreCase("spellBook"))
			{
				//if(args.length==0)
				//{
				if(sender.hasPermission("bcp.class")&&sender instanceof Player)
				{
					ClassBooks.SpellBook(p);
				}
				//}
			}
			//if(cmd.getName().equalsIgnoreCase("bcClassInfo"))
			if(args.length==1&&args[0].equalsIgnoreCase("classInfo"))
			{
				if(sender instanceof Player){
					//if(args.length==0)
					//{
					ClassBooks.classBooks(p, BkCP.playerClass.getConfig().getString(p.getUniqueId().toString()+".class"));
					//}
				}
			}
			//TODO tab done needs player
			//if(cmd.getName().equalsIgnoreCase("bcRemovePack"))
			if(args.length==2&&args[0].equalsIgnoreCase("removePack"))
			{
				if(sender.hasPermission("bcp.cpack")&&sender instanceof Player)
				{
					//if(args.length==1)
					//{
					if((BkCP.onlinePlayers.get(p.getName())instanceof TimberWolf)||sender.hasPermission("bcp.OPpack"))
					{
						if(guilds_packs.getConfig().contains("Twol."+args[1]+".leader")){
							if(sender.hasPermission("bcp.OPpack")||guilds_packs.getConfig().getString("Twol."+args[1]+".leader").equals(p.getUniqueId().toString()))
							{
								List<String> memUID = guilds_packs.getConfig().getStringList("Twol."+args[1]+".members");
								for(String uid : memUID)
								{
									if(Bukkit.getOfflinePlayer(UUID.fromString(uid)).getPlayer()!=null)
									{
										Player player = Bukkit.getOfflinePlayer(UUID.fromString(uid)).getPlayer();
										((TimberWolf)onlinePlayers.get(player.getName())).leaveBranch(args[1]);
									}
								}
								if((BkCP.onlinePlayers.get(p.getName())instanceof TimberWolf)){
									((TimberWolf)onlinePlayers.get(p.getName())).delBranchLeave(args[1]);
								}
								guilds_packs.getConfig().set("Twol."+args[1], null);
								guilds_packs.saveConfig();
								guilds_packs.reloadConfig();
								sender.sendMessage(prefix+ChatColor.RED+"Pack removed.");
							}else{
								sender.sendMessage(prefix+ChatColor.RED+"You must be the pack leader remove a pack.");
							}
						}
						else{sender.sendMessage(prefix+ChatColor.RED+"Pack does not exist.");}
					}
					else{sender.sendMessage(prefix+ChatColor.RED+"Must be a timberWolf to use this command.");}
					//}
				}
				else{sender.sendMessage(prefix+ChatColor.RED+"Must be a player to run this command, or missing permission.");}
			}
			//TODO tab done
			//if(cmd.getName().equalsIgnoreCase("bcGetPack"))
			if(args.length==3&&args[0].equalsIgnoreCase("getPack"))
			{
				if(sender.hasPermission("bcp.cpack")&&sender instanceof Player)
				{
					if(sender.hasPermission("bcp.OPpack")||(BkCP.onlinePlayers.get(p.getName())instanceof TimberWolf))
					{
						if(args.length==2)
						{
							if(guilds_packs.getConfig().contains("Twol."+args[1]))
							{
								String packName = playerClass.getConfig().getString(p.getPlayer().getUniqueId().toString()+".pack");
								if(sender.hasPermission("bcp.OPpack")||guilds_packs.getConfig().getString("Twol."+packName+".leader").equals(p.getUniqueId().toString()))
								{
									List<String> mName = new ArrayList<String>();
									mName.clear();
									for(String mUID : BkCP.guilds_packs.getConfig().getStringList("Twol."+packName+".members"))
									{
										//System.out.print(mUID);
										mName.add(Bukkit.getPlayer(UUID.fromString(mUID)).getName());
									}
									sender.sendMessage(prefix+ChatColor.AQUA+mName);
									//System.out.print(mName);
								}
							}
						}
						else{
							List<String> gNames = new ArrayList<String>();
							if(BkCP.guilds_packs.getConfig().getConfigurationSection("Twol")!=null)
							{
								for(String gName : BkCP.guilds_packs.getConfig().getConfigurationSection("Twol").getKeys(false))
								{
									if(!gNames.contains(gName)){
										gNames.add(gName);
									}
								}
								sender.sendMessage(prefix+ChatColor.AQUA+gNames);
							}else
							{sender.sendMessage(prefix+ChatColor.RED+"No packs to show.");}

						}
					}
					else{sender.sendMessage(prefix+ChatColor.RED+"Must be a timberWolf to use this command.");}
				}
			}
			//TODO tab done
			//if(cmd.getName().equalsIgnoreCase("bcCreatePack"))
			if(args.length==3&&args[0].equalsIgnoreCase("createPack"))
			{
				if(sender.hasPermission("bcp.cpack")&&sender instanceof Player)
				{
					if((BkCP.onlinePlayers.get(p.getName())instanceof TimberWolf))
					{
						if(args.length==3)
						{

							String packName = playerClass.getConfig().getString(p.getPlayer().getUniqueId().toString()+".pack");
							if(guilds_packs.getConfig().contains("Twol."+args[1]))
							{
								sender.sendMessage(prefix+ChatColor.RED+"Pack already exists.");
							}
							if(guilds_packs.getConfig().contains("Twol."+packName+".leader"))
							{
								if(guilds_packs.getConfig().getString("Twol."+packName+".leader").equals(p.getUniqueId().toString()))
								{
									sender.sendMessage(prefix+ChatColor.RED+"You already own a pack.");
								}
							}
							else if(colour.contains(args[2]))
							{
								((TimberWolf)onlinePlayers.get(p.getName())).createBranch(args[1],args[2]);
								sender.sendMessage(prefix+ChatColor.RED+"Pack created.");
							}else{
								sender.sendMessage(prefix+ChatColor.RED+"Invalid colour, choose ONE!");
								sender.sendMessage(prefix+ChatColor.AQUA+colour);
							}
						}else{
							sender.sendMessage(prefix+ChatColor.RED+"bcCreatePack <packName> <colourCode>");
							sender.sendMessage(prefix+ChatColor.AQUA+colour);
						}
					}
					else{sender.sendMessage(prefix+ChatColor.RED+"Must be a timberWolf to use this command.");}
				}

			}
			//TODO tab done
			//if(cmd.getName().equalsIgnoreCase("bcEditPack"))
			if(args.length==3&&args[0].equalsIgnoreCase("editPack"))
			{
				if(sender.hasPermission("bcp.cpack")&& sender instanceof Player)
				{
					if((BkCP.onlinePlayers.get(p.getName())instanceof TimberWolf))
					{
						if(args.length==3)
						{
							String packName = playerClass.getConfig().getString(p.getPlayer().getUniqueId().toString()+".pack");
							if(guilds_packs.getConfig().contains("Twol."+packName))
							{
								if(guilds_packs.getConfig().getString("Twol."+packName+".leader").equals(p.getPlayer().getUniqueId().toString()))
								{
									if(colour.contains(args[2]))
									{

										((TimberWolf)onlinePlayers.get(p.getName())).editBranch(packName,args[2]);	
										sender.sendMessage(prefix+ChatColor.RED+"Pack updated.");
									}else{	
										sender.sendMessage(prefix+ChatColor.RED+"Invalid colour, choose ONE!");
										sender.sendMessage(prefix+ChatColor.AQUA+colour);
									}
								}
							}
						}
						else{
							sender.sendMessage(prefix+ChatColor.RED+"bcEditPack <colourCode>");
							sender.sendMessage(prefix+ChatColor.AQUA+colour);
						}
					}else{sender.sendMessage(prefix+ChatColor.RED+"Must be a timberWolf to use this command.");}

				}
			}
			//TODO tab done
			//if(cmd.getName().equalsIgnoreCase("bcLeavePack"))
			if(args.length==2&&args[0].equalsIgnoreCase("leavePack"))
			{
				if(sender.hasPermission("bcp.cpack")&& sender instanceof Player)
				{
					if((BkCP.onlinePlayers.get(p.getName())instanceof TimberWolf))
					{
						//if(args.length==1)
						//{	
						if(!args[1].equalsIgnoreCase("null")){
							String packName = args[1];
							if(guilds_packs.getConfig().contains("Twol."+args[1]))
							{
								((TimberWolf)onlinePlayers.get(p.getName())).leaveBranch(packName);
							}
							else
							{
								sender.sendMessage(prefix+ChatColor.RED+"That is not a valid pack name...");
								List<String> gNames = new ArrayList<String>();
								for(String gName : BkCP.guilds_packs.getConfig().getConfigurationSection("Twol").getKeys(false))
								{
									if(!gNames.contains(gName)){
										gNames.add(gName);
									}
								}
								sender.sendMessage(prefix+gNames);
							}
						}
						//}
					}
					else{sender.sendMessage(prefix+ChatColor.RED+"Must be a timberWolf to use this command.");}
				}
			}
			//TODO tab done
			//if(cmd.getName().equalsIgnoreCase("bcJoinPack"))
			if(args.length==2&&args[0].equalsIgnoreCase("joinPack"))
			{
				if(sender.hasPermission("bcp.cpack")&& sender instanceof Player)
				{
					if((BkCP.onlinePlayers.get(p.getName())instanceof TimberWolf))
					{
						//if(args.length==1)
						//{
						String packName = args[1];
						List<String> inviteList = guilds_packs.getConfig().getStringList("Twol."+packName+".invitation");
						if(inviteList.contains(p.getUniqueId().toString()))
						{
							((TimberWolf)onlinePlayers.get(p.getName())).setBranch(packName);
						}	
						//}
					}
					else{sender.sendMessage(prefix+ChatColor.RED+"Must be a timberWolf to use this command.");}
				}
			}

			//TODO tab (not needed)
			//if(cmd.getName().equalsIgnoreCase("bcInvitePack"))
			if(args.length==2&&args[0].equalsIgnoreCase("invitePack"))
			{
				if(sender.hasPermission("bcp.cpack")&& sender instanceof Player)
				{
					if((BkCP.onlinePlayers.get(p.getName())instanceof TimberWolf))
					{
						String packName = playerClass.getConfig().getString(p.getPlayer().getUniqueId().toString()+".pack");
						if(guilds_packs.getConfig().contains("Twol."+packName))
						{
							if(guilds_packs.getConfig().getString("Twol."+packName+".leader").equals(p.getPlayer().getUniqueId().toString()))
							{
								//if(args.length==1)
								//{
								if(Bukkit.getOfflinePlayer(args[1])!=null)
								{
									String addUID = Bukkit.getPlayer(args[1]).getUniqueId().toString();
									List<String> inviteList = guilds_packs.getConfig().getStringList("Twol."+packName+".invitation");
									String addPack = playerClass.getConfig().getString(addUID+".pack");
									if(addPack.equals(packName))
									{
										sender.sendMessage(prefix+ChatColor.RED+"Player is already a member of this pack.");
									}
									if(!inviteList.contains(addUID))
									{
										guilds_packs.getConfig().set("Twol."+packName+".invitation", addUID);
										sender.sendMessage(prefix+ChatColor.RED+"Player has been invited.");
									}
									else{
										sender.sendMessage(prefix+ChatColor.RED+"Player has already been invited to this pack.");
									}

								}else{
									sender.sendMessage(prefix+ChatColor.RED+"Invalid player");
								}
								//}
							}
							else{sender.sendMessage(prefix+ChatColor.RED+"Must be a leader to send invitations.");}
						}
					}
					else{sender.sendMessage(prefix+ChatColor.RED+"Must be a timberWolf to use this command.");}
				}
			}
			//TODO tab done
			//if(cmd.getName().equalsIgnoreCase("bcRunParticles"))
			if(args.length==2&&args[0].equalsIgnoreCase("runParticles"))
			{
				//if(args.length==1)
				//{
				if(sender instanceof Player){
					if(args[1].equalsIgnoreCase("true")||args[1].equalsIgnoreCase("false")){
						sfx.addToRun(p, Boolean.parseBoolean(args[1]));
						playerClass.getConfig().set(p.getUniqueId()+".particles.run", Boolean.parseBoolean(args[1]));
						playerClass.saveConfig();
					}
				}
				//}
			}
			//TODO tab done
			//if(cmd.getName().equalsIgnoreCase("bcSetParticles"))
			if(args.length==5&&args[0].equalsIgnoreCase("setParticles"))
			{
				if(sender.hasPermission("bcp.particles")&& sender instanceof Player)
				{
					if(args.length == 5)
					{
						if(parLst.contains(args[1].toUpperCase()))
						{
							if(pars.contains(ParticleType.valueOf(args[1].toUpperCase())))
							{
								String pName = args[1].toUpperCase();
								double speed =  Double.parseDouble(args[2]);
								int count = Integer.parseInt(args[3]);
								double radius = Double.parseDouble(args[4]);
								playerClass.getConfig().set(p.getUniqueId()+".particles.pName", pName);
								playerClass.getConfig().set(p.getUniqueId()+".particles.speed", speed);
								playerClass.getConfig().set(p.getUniqueId()+".particles.count", count);
								playerClass.getConfig().set(p.getUniqueId()+".particles.radius", radius);
								playerClass.getConfig().set(p.getUniqueId()+".particles.run", true);
								playerClass.saveConfig();
								sender.sendMessage(prefix+ChatColor.GREEN+"Valid particleType");
								sfx.buildParticle(p, pName, speed, count, radius, true);
								return true;
							}
						}
						else
						{
							sender.sendMessage(prefix+ChatColor.RED+"Invalid particleType");
							sender.sendMessage(prefix+ChatColor.RED+"particleName speed count radius");
							sender.sendMessage(prefix+parLst);
						}
					}
					else
					{
						sender.sendMessage(prefix+parLst);	
					}
				}
			}
			//TODO tab done player Needed
			//if (cmd.getName().equalsIgnoreCase("bcSetSpawn"))
			if(args.length==1&&args[0].equalsIgnoreCase("setSpawn"))
			{
				if (sender.hasPermission("bcp.admin")&& sender instanceof Player)
				{
					if ((sender instanceof Player))
					{
						if (args.length == 1) {
							setSpawn((Player)sender, "null");
						} else if (args.length == 2) {
							setSpawn((Player)sender, args[1]);
						} else if (args.length == 3)
						{
							if (args[2].equalsIgnoreCase("off")) {
								remSpawn((Player)sender, args[1]);
							} else {
								sender.sendMessage(prefix + "Invalid format!");
							}
						}
						else {
							sender.sendMessage(prefix+ "setSpawn <className>");
						}
					}
					else {
						sender.sendMessage(prefix+ "Only a player can use this command!");
					}
					reloadSpawns();
				}
				else
				{
					sender.sendMessage(prefix + "Missing permissions");
				}
			}
			//TODO
			//bcp toggleVIP playerName
			if(args.length>0&&args[0].equalsIgnoreCase("togglePVP"))
			{
				if(args.length==1)
				{
					if(PvpControlMain.pvpList.containsKey(p))
					{
						Boolean bol = PvpControlMain.pvpList.get(p);
						sender.sendMessage(prefix+"PVP: "+!bol);
						togglePVP(p, !bol);
					}else
					{
						sender.sendMessage(prefix+"PVP: FALSE");
						togglePVP(p,false);
						//PvpControlMain.pvpList.put(player, true);
					}
				}
				if(sender.hasPermission("bcp.pvp")||sender.isOp())
				{
					if(args.length==2)
					{
						if(Bukkit.getPlayer(args[1]) != null)
						{
							Player player = Bukkit.getPlayer(args[1]);
							if(PvpControlMain.pvpList.containsKey(player))
							{
								Boolean bol = PvpControlMain.pvpList.get(player);
								if(sender!=player){
									sender.sendMessage(prefix+"PVP: "+!bol);
									player.sendMessage(prefix+"PVP: "+!bol);
								}else
								{
									sender.sendMessage(prefix+"PVP: "+!bol);	
								}
								togglePVP(player, !bol);
							}else
							{
								sender.sendMessage(prefix+"PVP: FALSE");
								togglePVP(player,false);
								//PvpControlMain.pvpList.put(player, true);
							}
						}
					}
				}
			}

		}

		return false;
	}
	public void togglePVP(Player p, Boolean b)
	{
		PvpControlMain.pvpList.put(p, b);
		playerClass.getConfig().set(p.getUniqueId()+".PVP", b);
		playerClass.saveConfig();
		playerClass.reloadConfig();
	}
	public void setClass(Player p, String c)
	{
		advCfg.reloadConfig();
		String classname = "";
		switch(c)
		{
		case"Unicorn":
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
			BkCP.onlinePlayers.remove(p.getName());
			//addExtensions(p);
			Unicorn uniClass = new Unicorn(p.getName());
			uniClass.setPrefix(BkCP.advCfg.getConfig().getString("Prefixes.Unicorn.default").replaceAll("&", "§"));
			//newClass.refreshMana(1);
			BkCP.onlinePlayers.put(p.getName(), uniClass);	
			p.sendMessage(BkCP.prefix+ChatColor.AQUA + "You are now a Unicorn!");
			BkCP.spawnPlayer(p, "Unicorn");

			break;
		case"Pegasus":
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
			BkCP.onlinePlayers.remove(p.getName());
			//addExtensions(p);
			Pegasus pegClass = new Pegasus(p.getName());
			pegClass.setPrefix(BkCP.advCfg.getConfig().getString("Prefixes.Pegasus.default").replaceAll("&", "§"));
			//newClass.refreshEnergy(2);

			BkCP.onlinePlayers.put(p.getName(), pegClass);	
			p.sendMessage(BkCP.prefix+ChatColor.AQUA + "You are now a Pegasus!");
			BkCP.spawnPlayer(p, "Pegasus");

			break;
		case"Earth":
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
			BkCP.onlinePlayers.remove(p.getName());
			//addExtensions(p);
			Earth earClass = new Earth(p.getName());
			earClass.setPrefix(BkCP.advCfg.getConfig().getString("Prefixes.Earth.default").replaceAll("&", "§"));
			//newClass.refreshEnergy(2);

			BkCP.onlinePlayers.put(p.getName(), earClass);	
			p.sendMessage(BkCP.prefix+ChatColor.AQUA + "You are now an Earth pony!");
			BkCP.spawnPlayer(p, "Earth");
			break;
		case "Alicorn":
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
			BkCP.onlinePlayers.remove(p.getName());
			//addExtensions(p);
			Alicorn aliClass = new Alicorn(p.getName());
			aliClass.setPrefix(BkCP.advCfg.getConfig().getString("Prefixes.Alicorn.default").replaceAll("&", "§"));
			//newClass.refreshEnergy(2);

			BkCP.onlinePlayers.put(p.getName(), aliClass);	
			p.sendMessage(BkCP.prefix+ChatColor.AQUA + "You are now an Alicorn!");
			BkCP.spawnPlayer(p, "Alicorn");
			break;
		case "Changeling":
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
			BkCP.onlinePlayers.remove(p.getName());
			//addExtensions(p);
			Changeling chaClass = new Changeling(p.getName());
			chaClass.setPrefix(BkCP.advCfg.getConfig().getString("Prefixes.Changeling.default").replaceAll("&", "§"));
			BkCP.onlinePlayers.put(p.getName(), chaClass);	
			p.sendMessage(BkCP.prefix+ChatColor.AQUA + "You are now a Changeling!");
			BkCP.spawnPlayer(p, "Changeling");
			break;
		case "Dragon":
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
			BkCP.onlinePlayers.remove(p.getName());
			//addExtensions(p);
			Dragon draClass = new Dragon(p.getName());
			draClass.setPrefix(BkCP.advCfg.getConfig().getString("Prefixes.Dragon.default").replaceAll("&", "§"));
			BkCP.onlinePlayers.put(p.getName(), draClass);	
			p.sendMessage(BkCP.prefix+ChatColor.AQUA + "You are now a Dragon!");
			BkCP.spawnPlayer(p, "Dragon");
			break;
		case"TimberWolf":
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
			//BkCP.onlinePlayers.remove(p.getName());
			//addExtensions(p);
			TimberWolf timClass = new TimberWolf(p.getName());
			timClass.setPrefix(BkCP.advCfg.getConfig().getString("Prefixes.TimberWolf.default").replaceAll("&", "§"));
			BkCP.onlinePlayers.put(p.getName(), timClass);	
			p.sendMessage(BkCP.prefix+ChatColor.AQUA + "You are now a TimberWolf!");
			BkCP.spawnPlayer(p, "TimberWolf");
			break;
		case"Griffon":
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
			BkCP.onlinePlayers.remove(p.getName());
			//addExtensions(p);
			Griffon griClass = new Griffon(p.getName());
			griClass.setPrefix(BkCP.advCfg.getConfig().getString("Prefixes.Griffon.default").replaceAll("&", "§"));

			BkCP.onlinePlayers.put(p.getName(), griClass);	
			p.sendMessage(BkCP.prefix+ChatColor.AQUA + "You are now a Griffon!");
			BkCP.spawnPlayer(p, "Griffon");
			break;
		case "Draconequus":
			List<String> dcspells = BkCP.BranchSpells.getConfig().getStringList("dc.default.spells");
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
			BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".spells", dcspells);
			BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".branch", "default");
			BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".currentSpell", 0);

			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			BkCP.onlinePlayers.remove(p.getName());
			//addExtensions(p);
			Draconequus drcClass = new Draconequus(p.getName());
			drcClass.setPrefix(BkCP.advCfg.getConfig().getString("Prefixes.Draconequus.default").replaceAll("&", "§"));
			//newClass.refreshEnergy(2);

			BkCP.onlinePlayers.put(p.getName(), drcClass);	
			p.sendMessage(BkCP.prefix+ChatColor.AQUA + "You are now an Draconequus!");
			BkCP.spawnPlayer(p, "Draconequus");
		}
		ClassBooks.classBooks(p, classname);
		clearGuild(p);
		changeSetup(p);

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
				p.getPlayer().setAllowFlight(false);
			}
		}
	}

	public void saveDefaultConfig(String fileName, String dir)
	{
		if(dir != null){
			if (!new File(getDataFolder()+dir+"/", fileName).exists())
			{


				getLogger().info("Creating " + fileName + " file...");
				File outFile = new File(getDataFolder()+dir, fileName);
				InputStream in = getResource(fileName);
				try {
					if (!outFile.exists()) {
						OutputStream out = new FileOutputStream(outFile);
						byte[] buf = new byte['?'];
						int len;
						while ((len = in.read(buf)) > 0) { //int len;
							out.write(buf, 0, len);
						}
						out.close();
						in.close();
					} else {

						logger.log(Level.WARNING, "Could not save " + outFile.getName() + " to " + outFile + " because " + outFile.getName() + " already exists.");
					}
				} catch (IOException e) {

					e.printStackTrace();
				}
				//saveResource(fileName, false);
			}   
		}else
			if (!new File(getDataFolder(), fileName).exists())
			{
				getLogger().info("Creating " + fileName + " file...");
				saveResource(fileName, false);
			}
	}

	public static Player getPlayer(String s)
	{
		for (Player p : Bukkit.getOnlinePlayers() ) {
			if (p.getName().equalsIgnoreCase(s)) {
				return p;
			}
		}
		return null;
	}
	public static void spawnPlayer(Player p, String clnm)
	{
		boolean message = true;
		for (String s : pSpawns.keySet()) {
			if (s.equalsIgnoreCase(clnm))
			{
				p.teleport((Location)pSpawns.get(s));
				p.sendMessage(prefix+ChatColor.AQUA + "You've teleported to spawn!");
				message = false;
			}
		}
		if (message) {
			if (config.getConfig().getBoolean("Spawn-Options.Cspawn-Messages")) {
				p.sendMessage(prefix+ "That spawn is not set yet!");
			}
		}
	}
	private void reloadSpawns()
	{
		pSpawns.keySet().removeAll(pSpawns.keySet());
		for (String s : classNames) {
			if (spawns.getConfig().contains(s)) {
				pSpawns.put(s, new Location(Bukkit.getWorld(spawns.getConfig()
						.getString(s + ".world")), spawns.getConfig()
						.getInt(s + ".x"), spawns.getConfig().getInt(
								s + ".y"), spawns.getConfig().getInt(s + ".z"), 

						(float)spawns.getConfig().getDouble(s + ".yaw"), 
						(float)spawns.getConfig().getDouble(s + ".pitch")));
			}
		}
	}
	private void remSpawn(Player p, String clnm)
	{
		boolean message = true;
		for (String s : classNames) {
			if (s.equalsIgnoreCase(clnm))
			{
				if (spawns.getConfig().contains(s))
				{
					spawns.getConfig().set(s, null);
					spawns.saveConfig();
					reloadSpawns();
				}
				else
				{
					p.sendMessage(prefix+ 
							"There is not a spawn set for that class!");
				}
				p.sendMessage(prefix+ChatColor.AQUA + "Spawn removed for the " + 
						clnm + " class.");
				message = false;
			}
		}
		if (message) {
			p.sendMessage(prefix+ "That is not a class name! (" + clnm + 
					")");
		}
	}
	private void setSpawn(Player p, String clnm)
	{
		boolean message = true;
		for (String s : classNames) {
			if (s.equalsIgnoreCase(clnm))
			{
				spawns.getConfig().set(s + ".world", p.getLocation().getWorld().getName());
				spawns.getConfig().set(s + ".x", Double.valueOf(p.getLocation().getX()));
				spawns.getConfig().set(s + ".y", Double.valueOf(p.getLocation().getY()));
				spawns.getConfig().set(s + ".z", Double.valueOf(p.getLocation().getZ()));
				spawns.getConfig().set(s + ".pitch", Float.valueOf(p.getLocation().getPitch()));
				spawns.getConfig().set(s + ".yaw", Float.valueOf(p.getLocation().getYaw()));
				spawns.saveConfig();
				p.sendMessage(prefix+ChatColor.AQUA + "Spawn set for the " + clnm + 
						" class.");
				message = false;
			}
		}
		if (message) {
			p.sendMessage(prefix+ "That is not a class name! (" + clnm + ")");
		}
	}	

	@EventHandler
	public void playerJoinEvent(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		if(BkCP.playerClass.getConfig().getString(p.getUniqueId().toString()+".particles")!=null)
		{
			String pName = BkCP.playerClass.getConfig().getString(p.getUniqueId().toString()+".particles.pName");
			double speed = BkCP.playerClass.getConfig().getDouble(p.getUniqueId().toString()+".particles.speed");
			int count = BkCP.playerClass.getConfig().getInt(p.getUniqueId().toString()+".particles.count");
			double radius = BkCP.playerClass.getConfig().getDouble(p.getUniqueId().toString()+".particles.radius");
			boolean run = BkCP.playerClass.getConfig().getBoolean(p.getUniqueId().toString()+".particles.run");
			sfx.buildParticle(p, pName, speed, count, radius,run);
		}
		if(config.getConfig().getBoolean("Module.togglePVP"))
		{
			Boolean bol = playerClass.getConfig().getBoolean(p.getUniqueId()+".PVP");
			PvpControlMain.pvpList.put(p, bol);
			System.out.print("BCP_PVPTOGGLE: "+p.getName()+"::"+bol);
		}

	}
	@EventHandler
	public void playerLeaveEvent(PlayerQuitEvent e)
	{
		Player p = e.getPlayer();
		sfx.removePlayer(p);
	}
}