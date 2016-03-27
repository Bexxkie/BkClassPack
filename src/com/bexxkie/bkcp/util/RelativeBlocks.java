package com.bexxkie.bkcp.util;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

public class RelativeBlocks 
{
	public static ItemStack processEnchantsAndReturnItemStack(Block block) 
	{
		Material blockType = block.getType();
		ItemStack drop = null;
		drop = new ItemStack(blockType, 1);
		return drop;
	}


	public static ArrayList<Block> getSurroundingBlocks(BlockFace blockFace, Block targetBlock) 
	{
		ArrayList<Block> blocks = new ArrayList<Block>();
		World world = targetBlock.getWorld();

		int x, y, z;
		x = targetBlock.getX();
		y = targetBlock.getY();
		z = targetBlock.getZ();

		// Check the block face from which the block is being broken in order to get the correct surrounding blocks
		switch(blockFace) {
		case UP:
		case DOWN:
			blocks.add(world.getBlockAt(x+1, y, z));
			blocks.add(world.getBlockAt(x-1, y, z));
			blocks.add(world.getBlockAt(x, y, z+1));
			blocks.add(world.getBlockAt(x, y, z-1));
			blocks.add(world.getBlockAt(x+1, y, z+1));
			blocks.add(world.getBlockAt(x-1, y, z-1));
			blocks.add(world.getBlockAt(x+1, y, z-1));
			blocks.add(world.getBlockAt(x-1, y, z+1));
			break;
		case EAST:
		case WEST:
			blocks.add(world.getBlockAt(x, y, z+1));
			blocks.add(world.getBlockAt(x, y, z-1));
			blocks.add(world.getBlockAt(x, y+1, z));
			blocks.add(world.getBlockAt(x, y-1, z));
			blocks.add(world.getBlockAt(x, y+1, z+1));
			blocks.add(world.getBlockAt(x, y-1, z-1));
			blocks.add(world.getBlockAt(x, y-1, z+1));
			blocks.add(world.getBlockAt(x, y+1, z-1));
			break;
		case NORTH:
		case SOUTH:
			blocks.add(world.getBlockAt(x+1, y, z));
			blocks.add(world.getBlockAt(x-1, y, z));
			blocks.add(world.getBlockAt(x, y+1, z));
			blocks.add(world.getBlockAt(x, y-1, z));
			blocks.add(world.getBlockAt(x+1, y+1, z));
			blocks.add(world.getBlockAt(x-1, y-1, z));
			blocks.add(world.getBlockAt(x+1, y-1, z));
			blocks.add(world.getBlockAt(x-1, y+1, z));
			break;
		default:
			break;
		}

		// Trim the nulls from the list
		blocks.removeAll(Collections.singleton(null));
		return blocks;
	}


}
