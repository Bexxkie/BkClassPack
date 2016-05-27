package com.bexxkie.bkcp.modules.control;

import java.util.ArrayList;
import java.util.List;

import com.bexxkie.bkcp.BkCP;

public class ClassControl 
{

	/**
	 * 
	 * @param file ConfigName
	 * @param path yml node path
	 * @param sender sender as player
	 */
	public String getData(String file, String path)
	{
		switch(file.toLowerCase())
		{
		case "config":
			if(!BkCP.config.getConfig().isConfigurationSection(path)){		
				return BkCP.config.getConfig().getString(path);
			}
			return "invalid node";
		case "advconfig":
			if(!BkCP.advCfg.getConfig().isConfigurationSection(path)){
				return BkCP.advCfg.getConfig().getString(path);
			}
			return "invalid node";
		case "envdata":
			if(!BkCP.EnvData.getConfig().isConfigurationSection(path)){
				return BkCP.EnvData.getConfig().getString(path);
			}
			return "invalid node";
		default:
			return null;
		}
	}
	public static boolean isInteger(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) { 
			return false; 
		} catch(NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}
	public static boolean isDouble(String s) {
		try { 
			Double.parseDouble(s); 
		} catch(NumberFormatException e) { 
			return false; 
		} catch(NullPointerException e) {
			return false;
		}
		// only got here if we didn't return false
		return true;
	}
	public static boolean isBoolean(String s)
	{
		if(s.equalsIgnoreCase("true")||s.equalsIgnoreCase("false"))
		{
			return true;
		}
		return false;
	}
	public Object getType(String fullPath, String data)
	{
		List<String> returnList = new ArrayList<String>();
		returnList.clear();
		if(BkCP.varType.containsKey(fullPath))
		{
			String type = BkCP.varType.get(fullPath);
			if(type.equalsIgnoreCase("boolean"))
			{
				if(isBoolean(data)){
					return Boolean.parseBoolean(data);
				}
			}
			if(type.equalsIgnoreCase("integer"))
			{
				if(isInteger(data)){
					return Integer.parseInt(data);
				}
			}
			if(type.equalsIgnoreCase("double"))
			{
				if(isDouble(data)){
					return Double.parseDouble(data);
				}
			}

		}
		return false;
	}
	/**
	 * 
	 * @param file ConfigName
	 * @param path yml node path
	 * @param writeData new value of path
	 */
	public boolean setData(String file, String path, String data)
	{
		switch(file.toLowerCase())
		{
		case "config":
			if(!BkCP.config.getConfig().isConfigurationSection(path)){		
				Object saveData = getType(file.toLowerCase()+"."+path.toLowerCase(), data.toLowerCase());
				BkCP.config.getConfig().set(path, saveData);
				saveData(file);
				return true;
			}
			return false;
		case "advconfig":
			if(!BkCP.advCfg.getConfig().isConfigurationSection(path)){
				Object saveData = getType(file.toLowerCase()+"."+path.toLowerCase(), data.toLowerCase());
				BkCP.advCfg.getConfig().set(path, saveData);
				saveData(file);
				return true;
			}
			return false;
		case "envdata":
			if(!BkCP.EnvData.getConfig().isConfigurationSection(path)){
			Object saveData = getType(file.toLowerCase()+"."+path.toLowerCase(), data.toLowerCase());
					BkCP.EnvData.getConfig().set(path, saveData);
					saveData(file);
					return true;
			}
			return false;
		default:
			return false;
		}
	}
	/**
	 * 
	 * @param file ConfigName
	 */
	public void saveData(String file)
	{
		switch(file.toLowerCase())
		{
		case "config":
			BkCP.config.saveConfig();
			BkCP.config.reloadConfig();
			return;
		case "advconfig":
			BkCP.advCfg.saveConfig();
			BkCP.advCfg.reloadConfig();
			return;
		case "envdata":
			BkCP.EnvData.saveConfig();
			BkCP.EnvData.reloadConfig();
			return;
		}

	}
	/**
	 * 
	 * @param file ConfigName
	 * @param path yml node path
	 * @return data value of path
	 */
	public String checkData()
	{
		return null;
	}
}
