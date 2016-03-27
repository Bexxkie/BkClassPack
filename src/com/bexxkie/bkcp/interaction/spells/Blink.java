package com.bexxkie.bkcp.interaction.spells;

import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;


import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.classes.interfaces.MagicUser;
import com.bexxkie.bkcp.util.LocationIterator;
import com.bexxkie.bkcp.util.ParticleEffect;
import com.bexxkie.bkcp.util.ParticleEffect.ParticleType;

public class Blink
{
	public static void spell(Player p, MagicUser user)
	{
		
		if(((MagicUser)BkCP.onlinePlayers.get(p.getName())).getCool("blink")==true){return;
		}else
			if (user.getMana() > 15.0)
			{
				user.setMana(user.getMana() - 7.5);
			}
			if(p.getLocation().getBlock().getType()==Material.AIR&&p.getLocation().add(0, 1, 0).getBlock().getType()==Material.AIR){
				((MagicUser)BkCP.onlinePlayers.get(p.getName())).setCool("blink", 5);
				World w = p.getWorld();
				Block block = p.getTargetBlock((Set<Material>)null, 15);

				Location loc = block.getLocation();
				if (block.getType() != Material.AIR)
				{
					List<Block> lastBlocks = p.getLastTwoTargetBlocks((Set<Material>)null, 15);

					BlockFace face = ((Block)lastBlocks.get(1)).getFace((Block)lastBlocks.get(0));

					if (block.getRelative(BlockFace.UP).getType() == Material.AIR)
					{
						block = block.getRelative(BlockFace.UP);
						loc = block.getLocation();
						loc.setX(loc.getX() + 0.5D);
						loc.setZ(loc.getZ() + 0.5D);
					}
					else if (block.getRelative(face).getType() == Material.AIR)
					{
						block = block.getRelative(face);
						switch (face)
						{
						case DOWN: 
							loc.setX(loc.getX() + 0.5D);
							loc.setZ(loc.getZ() - 0.5D);
							break;
						case EAST: 
							loc = block.getLocation();
							loc.setX(loc.getX() + 0.5D);
							loc.setZ(loc.getZ() + 0.5D);
							break;
						case EAST_NORTH_EAST: 
							loc = block.getLocation();
							loc.setX(loc.getX() + 0.5D);
							loc.setZ(loc.getZ() + 0.5D);
							break;
						case EAST_SOUTH_EAST: 
							loc.setX(loc.getX() - 0.5D);
							loc.setZ(loc.getZ() + 0.5D);
							break;
						default: 
							loc = block.getLocation();
						}
					}
				}
				if (block.getType() == Material.AIR)
				{
					loc.setPitch(p.getLocation().getPitch());
					loc.setYaw(p.getLocation().getYaw());
					ParticleEffect portal = new ParticleEffect(ParticleType.PORTAL, 2, 32, .1);
					ParticleEffect reddust = new ParticleEffect(ParticleType.REDSTONE, 0, 16, .2);
					
					Vector direction = p.getPlayer().getEyeLocation().subtract(loc).toVector();
					LocationIterator blocksToAdd = new LocationIterator(p.getWorld(), loc.toVector().setY(loc.getY()-.5), direction, 0, 16);
					Location blockToAdd;
					while(blocksToAdd.hasNext()) {
						blockToAdd = blocksToAdd.next().getBlock().getLocation();
						try {
							w.playSound(p.getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, (float) (Math.random()*Math.random()/Math.random()));
							portal.sendToLocation(blockToAdd);
							reddust.sendToLocation(blockToAdd);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
					p.teleport(loc);
				}
				else
				{
					p.sendMessage(ChatColor.RED + "Invalid location.");
				}
			}
	}

	public Block getTargetBlock(Player player, int range)
	{
		Location loc = player.getEyeLocation();
		Vector dir = loc.getDirection().normalize();

		Block b = null;
		for (int i = 0; i <= range; i++) {
			b = loc.add(dir).getBlock();
		}
		return b;
	}
}


