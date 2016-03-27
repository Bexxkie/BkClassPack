package com.bexxkie.bkcp.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.util.ParticleEffect.ParticleType;


public class TabAutoCompletion implements TabCompleter
{
	public List<String> onTabComplete(CommandSender sender,Command cmd, String label,String[] args) 
	{
		//String name = sender.getName();
		//SPlayer p = Bukkit.getPlayer(name);
		//World world = p.getWorld();
		//PluginManager pm = Bukkit.getServer().getPluginManager();
		if(cmd.getName().equalsIgnoreCase("bcp"))
		{
			if(args.length>0&&args.length<2)
			{
				if(!args[0].isEmpty()){
					List<String> commands = new ArrayList<String>();
					for(String com : BkCP.commands)
					{
						if(com.toLowerCase().startsWith(args[0].toLowerCase())||com.startsWith(args[0]))
						{
							commands.add(com);
						}
					}
					if(!commands.isEmpty()){
						return commands;}
					else{return BkCP.commands;}
				}
				else{
					return BkCP.commands;}
			}
			if(args[0].equalsIgnoreCase("removePack")||args[0].equalsIgnoreCase("getPack")||args[0].equalsIgnoreCase("joinPack")||args[0].equalsIgnoreCase("leavePack"))
			{
				if(args.length==2)
				{
					List<String> packNames = new ArrayList<String>();
					for(String gName : BkCP.guilds_packs.getConfig().getConfigurationSection("Twol").getKeys(false))
					{
						if(!packNames.contains(gName)){
							packNames.add(gName);
						}
						if(!args[1].isEmpty()){
							List<String> packList = new ArrayList<String>();
							for(String pack : BkCP.guilds_packs.getConfig().getConfigurationSection("Twol").getKeys(false))
							{
								if(pack.toLowerCase().startsWith(args[1].toLowerCase())||pack.startsWith(args[1]))
								{
									packList.add(pack);
								}
							}
							if(!packList.isEmpty()){
								return packList;}
							else{return packNames;}
						}


					}
					return packNames;
				}
			}
			if(args[0].equalsIgnoreCase("createPack"))
			{
				if(args.length==2)
				{
					List<String> returnVal = new ArrayList<String>();
					returnVal.add("<desiredName>");
					return returnVal;
				}
				if(args.length==3)
				{
					return BkCP.colour;
				}
			}
			if(args[0].equalsIgnoreCase("setClass"))
			{
				if(args.length==3)
				{
					List<String> classes = Arrays.asList(BkCP.classNames);
					if(!args[2].isEmpty()){
						List<String> cList = new ArrayList<String>();
						for(String clas : classes)
						{
							if(clas.toLowerCase().startsWith(args[2].toLowerCase())||clas.startsWith(args[2]))
							{
								cList.add(clas);
							}
						}
						if(!cList.isEmpty()){
							return cList;}
						else{return classes;}
					}
					return classes;
				}
			}
			if(args[0].equalsIgnoreCase("editPack"))
			{
				List<String> packNames = new ArrayList<String>();
				if(args.length==2){
					for(String gName : BkCP.guilds_packs.getConfig().getConfigurationSection("Twol").getKeys(false))
					{
						if(!packNames.contains(gName))
						{
							packNames.add(gName);
						}
						if(!args[1].isEmpty()){
							List<String> packList = new ArrayList<String>();
							for(String pack : BkCP.guilds_packs.getConfig().getConfigurationSection("Twol").getKeys(false))
							{
								if(pack.toLowerCase().startsWith(args[1].toLowerCase())||pack.startsWith(args[1]))
								{
									packList.add(pack);
								}
							}
							if(!packList.isEmpty()){
								return packList;}
							else{return packNames;}
						}


					}
					return packNames;
				}
				if(args.length==3)
				{
					return BkCP.colour;
				}
			}
			if(args[0].equalsIgnoreCase("runParticles"))
			{
				if(args.length==2){
					List<String> bol = new ArrayList<String>();
					bol.add("True");
					bol.add("False");
					return bol;
				}
			}
			if(args[0].equalsIgnoreCase("setParticles"))
			{
				if(args.length==2)
				{
					List<String> parL = new ArrayList<String>();
					for(ParticleType par : BkCP.pars)
					{
						parL.add(par.toString());
					}
					parL.remove("BARRIER");
					parL.remove("ITEM_CRACK");
					parL.remove("BLOCK_CRACK");
					parL.remove("BLOCK_DUST");
					parL.remove("ITEM_TAKE");
					if(!args[1].isEmpty()){
						List<String> pars = new ArrayList<String>();
						for(ParticleType part : BkCP.pars)
						{
							if(part.toString().toLowerCase().startsWith(args[1].toLowerCase())||part.toString().startsWith(args[1]))
							{
								pars.add(part.toString());
							}
						}
						if(!pars.isEmpty()){
							return pars;}
						else{return parL;}
					}else{
						return parL;
					}
				}
				if(args.length==3)
				{
					List<String> speed = new ArrayList<String>();
					speed.add("0.0");
					return speed;
				}
				if(args.length==4)
				{
					List<String> count = new ArrayList<String>();
					count.add("5");
					return count;
				}
				if(args.length==5)
				{
					List<String> rad = new ArrayList<String>();
					rad.add("0.5");
					return rad;
				}
			}
			if(args[0].equalsIgnoreCase("SetSpawn"))
			{
				if(args.length==2)
				{
					List<String> classes = Arrays.asList(BkCP.classNames);
					if(!args[1].isEmpty()){
						List<String> cList = new ArrayList<String>();
						for(String clas : classes)
						{
							if(clas.toLowerCase().startsWith(args[1].toLowerCase())||clas.startsWith(args[1]))
							{
								cList.add(clas);
							}
						}
						if(!cList.isEmpty()){
							return cList;}
						else{return classes;}
					}

					return classes;
				}
			}
			if(args[0].equalsIgnoreCase("SetGender"))
			{
				if(args.length==1||args.length==2)
				{
					List<String> genders = new ArrayList<String>();
					genders.add("Male");genders.add("Female");genders.add("None");
					return(genders);
				}
			}


		}

		return null;

	}

}
