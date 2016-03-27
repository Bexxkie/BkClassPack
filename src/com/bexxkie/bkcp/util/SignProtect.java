package com.bexxkie.bkcp.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import com.bexxkie.bkcp.BkCP;

public class SignProtect
implements Listener
{
	public static boolean checkSign(Location loc, Player player)
	{
		if ((loc.getBlock().getType() == Material.SIGN_POST) || (loc.getBlock().getType() == Material.WALL_SIGN))
		{
			Iterator<String> localIterator = Arrays.asList(new String[] {
					BkCP.advCfg.getConfig().getString("SignText.Unicorn")}).iterator();
			while (localIterator.hasNext())
			{
				String pClass = (String)localIterator.next();
				if (((Sign)loc.getBlock().getState()).getLine(0).contains(pClass))
				{
					if (player != null)
					{
						if (player.hasPermission("4p.admin"))
						{
							player.sendMessage(ChatColor.GREEN + "You just modified a class sign!");
							return true;
						}
						player.sendMessage(ChatColor.RED + "You cannot do that!");
						return false;
					}
					return false;
				}
			}
		}
		return true;
	}

	@EventHandler
	public void onSignPlace(SignChangeEvent event)
	{
		List<String> lList = Arrays.asList(new String[] {
				BkCP.advCfg.getConfig().getString("SignText.Unicorn")});
		for (String pClass : lList) {
			if (event.getLine(0).contains(pClass)) {
				if (event.getPlayer().hasPermission("4p.admin"))
				{
					event.getPlayer().sendMessage(ChatColor.GREEN + "You just modified a class sign!");
				}
				else
				{
					event.setCancelled(true);
					event.getPlayer().sendMessage(ChatColor.RED + "You cannot do that!");
					event.getBlock().setType(Material.AIR);
				}
			}
		}
	}
	@EventHandler
	public void pistonRetract(BlockPistonRetractEvent event)
	{
		for(Block block : event.getBlocks()){
			Location loc = block.getLocation();
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc.setY(loc.getY() + 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = block.getLocation();
			loc.setY(loc.getY() - 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = block.getLocation();
			loc.setX(loc.getX() + 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = block.getLocation();
			loc.setX(loc.getX() - 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = block.getLocation();
			loc.setZ(loc.getZ() + 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = block.getLocation();
			loc.setZ(loc.getZ() - 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBurn(BlockBurnEvent event)
	{
		Location loc = event.getBlock().getLocation();
		if (!checkSign(loc, null)) {
			event.setCancelled(true);
		}
		loc.setY(loc.getY() + 1.0D);
		if (!checkSign(loc, null)) {
			event.setCancelled(true);
		}
		loc = event.getBlock().getLocation();
		loc.setY(loc.getY() - 1.0D);
		if (!checkSign(loc, null)) {
			event.setCancelled(true);
		}
		loc = event.getBlock().getLocation();
		loc.setX(loc.getX() + 1.0D);
		if (!checkSign(loc, null)) {
			event.setCancelled(true);
		}
		loc = event.getBlock().getLocation();
		loc.setX(loc.getX() - 1.0D);
		if (!checkSign(loc, null)) {
			event.setCancelled(true);
		}
		loc = event.getBlock().getLocation();
		loc.setZ(loc.getZ() + 1.0D);
		if (!checkSign(loc, null)) {
			event.setCancelled(true);
		}
		loc = event.getBlock().getLocation();
		loc.setZ(loc.getZ() - 1.0D);
		if (!checkSign(loc, null)) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void enderPickup(EntityChangeBlockEvent event)
	{
		if (!(event.getEntity() instanceof Player))
		{
			Block block = event.getBlock();
			Location loc = block.getLocation();
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc.setY(loc.getY() + 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = block.getLocation();
			loc.setY(loc.getY() - 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = block.getLocation();
			loc.setX(loc.getX() + 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = block.getLocation();
			loc.setX(loc.getX() - 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = block.getLocation();
			loc.setZ(loc.getZ() + 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = block.getLocation();
			loc.setZ(loc.getZ() - 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event)
	{
		List<Block> destroyed = event.blockList();
		Iterator<Block> it = destroyed.iterator();
		while (it.hasNext())
		{
			Block block = (Block)it.next();
			Location loc = block.getLocation();
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc.setY(loc.getY() + 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = block.getLocation();
			loc.setY(loc.getY() - 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = block.getLocation();
			loc.setX(loc.getX() + 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = block.getLocation();
			loc.setX(loc.getX() - 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = block.getLocation();
			loc.setZ(loc.getZ() + 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = block.getLocation();
			loc.setZ(loc.getZ() - 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			event.getLocation().getWorld().playEffect(event.getLocation(), Effect.EXTINGUISH, 1);
		}
	}

	@EventHandler
	public void signPiston(BlockPistonExtendEvent event)
	{
		for (Block posSign : event.getBlocks())
		{
			Location loc = posSign.getLocation();
			checkSign(loc, null);
			loc.setY(loc.getY() + 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = posSign.getLocation();
			loc.setY(loc.getY() - 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = posSign.getLocation();
			loc.setX(loc.getX() + 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = posSign.getLocation();
			loc.setX(loc.getX() - 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = posSign.getLocation();
			loc.setZ(loc.getZ() + 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
			loc = posSign.getLocation();
			loc.setZ(loc.getZ() - 1.0D);
			if (!checkSign(loc, null)) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void breakSign(BlockBreakEvent event)
	{
		Location loc = event.getBlock().getLocation();
		if (!checkSign(loc, event.getPlayer())) {
			event.setCancelled(true);
		}
		loc.setY(loc.getY() + 1.0D);
		if (!checkSign(loc, event.getPlayer())) {
			event.setCancelled(true);
		}
		loc = event.getBlock().getLocation();
		loc.setY(loc.getY() - 1.0D);
		if (!checkSign(loc, event.getPlayer())) {
			event.setCancelled(true);
		}
		loc = event.getBlock().getLocation();
		loc.setX(loc.getX() + 1.0D);
		if (!checkSign(loc, event.getPlayer())) {
			event.setCancelled(true);
		}
		loc = event.getBlock().getLocation();
		loc.setX(loc.getX() - 1.0D);
		if (!checkSign(loc, event.getPlayer())) {
			event.setCancelled(true);
		}
		loc = event.getBlock().getLocation();
		loc.setZ(loc.getZ() + 1.0D);
		if (!checkSign(loc, event.getPlayer())) {
			event.setCancelled(true);
		}
		loc = event.getBlock().getLocation();
		loc.setZ(loc.getZ() - 1.0D);
		if (!checkSign(loc, event.getPlayer())) {
			event.setCancelled(true);
		}
	}
}


