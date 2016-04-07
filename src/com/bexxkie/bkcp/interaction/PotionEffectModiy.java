package com.bexxkie.bkcp.interaction;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.util.RelativeBlocks;

public class PotionEffectModiy implements Listener
{
	private HashMap<String, BlockFace> faces = new HashMap<String, BlockFace>();
	//private Material[] pickaxes = {Material.WOOD_PICKAXE,Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLD_PICKAXE, Material.DIAMOND_PICKAXE};
	//private List<Material> picks = Arrays.asList(pickaxes);
	@EventHandler
	public void speedMining(BlockBreakEvent e)
	{
		Player p = e.getPlayer();
		BlockFace face = getBlockFaceByPlayerName(p.getName()); 
		Block block = e.getBlock();
		if(BkCP.miningEffect.containsKey(p.getName())&&(BkCP.miningEffect.get(p.getName())>0||BkCP.miningEffect.get(p.getName())==-1))
		{
			if(p.hasPotionEffect(PotionEffectType.FAST_DIGGING))
			{
				if(p.isSneaking())
				{return;}
				for(Block b: RelativeBlocks.getSurroundingBlocks(face, block))
				{
					if(b.getType()!=Material.AIR)
					{
						b.breakNaturally(p.getInventory().getItemInMainHand());	
					}
				}
			}
		}
		return;
	}

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void saveBlockFace(PlayerInteractEvent event) 
	{
		Player player = event.getPlayer();
		BlockFace bf = event.getBlockFace();

		if (player != null && bf != null) 
		{
			String name = player.getName();
			faces.put(name, bf);
		}
	}
	public BlockFace getBlockFaceByPlayerName(String name) 
	{
		return faces.get(name);
	}

	public static void setTimer(final Player p, final int timer)
	{
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
		{
			public void run()
			{
				
				if(BkCP.miningEffect.get(p.getName())<timer)
				{
					BkCP.miningEffect.put(p.getName(), BkCP.miningEffect.get(p.getName())+1);
					setTimer(p,timer);
				}else
				{
					BkCP.miningEffect.put(p.getName(), 0);
					return;
				}
			}
		},20);


	}

}
