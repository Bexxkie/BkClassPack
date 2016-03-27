package com.bexxkie.bkcp.classes.interfaces;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClassBase 
{
	protected String prefix = "[" + ChatColor.GRAY + "null" + ChatColor.RESET + "]";
	protected String myClass;
	protected String playerUID;
	/**
	 * classID's (add to this after id is used)
	 * "null 0, Unicorn 1", "Pegasus 2","Earth 3","Alicorn 4","Changeling 5","Dragon 6","TimberWolf 7"
	 */
	protected int classID;
	protected String[] classIDList = 
		{
			"null 0, Unicorn 1","pegasus 2","Earth 3","Alicorn 4","Changeling 5","Dragon 6","TimberWolf 7","Griffon 8", "Draconequus 9"
		};

	public ClassBase(String name)
	{
		this.playerUID = name;
		this.myClass = (ChatColor.GRAY + "Class Base");
		this.classID = 0;
	}
	public void setPrefix(String s)
	{
		this.prefix = s;
	}

	public String getPrefix()
	{
		if (this.prefix == null) {
			return "";
		}
		return this.prefix;
	}
	@SuppressWarnings("deprecation")
	public Player getPlayer()
	{
		return Bukkit.getPlayer(this.playerUID);
	}
	public void sendInfo(CommandSender sender)
	{
		sender.sendMessage(ChatColor.AQUA + "Username: " + ChatColor.GRAY + 
				getPlayer().getName());
		sender.sendMessage(ChatColor.AQUA + "Nickname: " + ChatColor.GRAY + 
				getPlayer().getDisplayName());
		sender.sendMessage(ChatColor.AQUA + "ClassName: " + this.myClass);
		sender.sendMessage(ChatColor.AQUA + "Can Fly?: " + ChatColor.GRAY + 
				getPlayer().getAllowFlight());
		sender.sendMessage(ChatColor.AQUA + "Is Op?: " + ChatColor.GRAY + 
				getPlayer().isOp());
	}
	public int getType()
	{
		return this.classID;
	}

}
