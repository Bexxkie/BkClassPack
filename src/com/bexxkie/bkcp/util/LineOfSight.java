package com.bexxkie.bkcp.util;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.minecraft.server.v1_9_R1.AxisAlignedBB;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class LineOfSight 
{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Entity getNearestEntityInSight(Player player, int range) {
	    List<Entity> entities = player.getNearbyEntities(range, range, range); //Get the entities within range
	    Iterator<Entity> iterator = entities.iterator(); //Create an iterator
	    while (iterator.hasNext()) {
	        Entity next = iterator.next(); //Get the next entity in the iterator
	        if (!(next instanceof LivingEntity) || next == player) { //If the entity is not a living entity or the player itself, remove it from the list
	            iterator.remove();
	        }
	    }
	    List<Block> sight = player.getLineOfSight((Set) null, range); //Get the blocks in the player's line of sight (the Set is null to not ignore any blocks)
	    for (Block block : sight) { //For each block in the list
	        if (block.getType() != Material.AIR) { //If the block is not air -> obstruction reached, exit loop/seach
	            break;
	        }
	        Location low = block.getLocation(); //Lower corner of the block
	        Location high = low.clone().add(1, 1, 1); //Higher corner of the block
	        AxisAlignedBB blockBoundingBox = new AxisAlignedBB(low.getX(), low.getY(), low.getZ(), high.getX(), high.getY(), high.getZ()); //The bounding or collision box of the block
	        for (Entity entity : entities) { //For every living entity in the player's range
	            //If the entity is truly close enough and the bounding box of the block (1x1x1 box) intersects with the entity's bounding box, return it
	            if (entity.getLocation().distance(player.getEyeLocation()) <= range && ((CraftEntity) entity).getHandle().getBoundingBox().b(blockBoundingBox)) {
	                return entity;
	            }
	        }
	    }
	    return null; //Return null/nothing if no entity was found
	}
	
	
}
