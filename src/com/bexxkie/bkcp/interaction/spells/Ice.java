package com.bexxkie.bkcp.interaction.spells;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.classes.interfaces.MagicUser;
import com.bexxkie.bkcp.modules.pvpControl.PvpControlMain;
import com.bexxkie.bkcp.util.LocationIterator;
import com.bexxkie.bkcp.util.ParticleEffect;
import com.bexxkie.bkcp.util.RandInt;
import com.bexxkie.bkcp.util.ParticleEffect.ParticleType;


public class Ice 
{
	//global Variables
	public static int effectInt = 0;
	public static HashMap<String, CopyOnWriteArrayList<Block>> iceWalking = new HashMap<String, CopyOnWriteArrayList<Block>>();

	public static void FrostI(Player p, MagicUser user)
	{
		if(user.getMana()>25.0){
			user.setMana(user.getMana() - 2.5);
			double x =RandInt.randInt(1,5);
			ParticleEffect ice = new ParticleEffect(ParticleType.SNOW_SHOVEL, x/100, 15, 0.5);
			ParticleEffect snow = new ParticleEffect(ParticleType.CRIT, x/100, 5, 0.5);
			Location a = p.getEyeLocation();
			LocationIterator blocksToAdd = new LocationIterator(a.getWorld(), a.toVector(), a.getDirection(), 0, 6);
			Location blockToAdd;
			double rad =2.5;
			while(blocksToAdd.hasNext()) {
				blockToAdd = blocksToAdd.next().getBlock().getLocation();
				if(!p.getNearbyEntities(6, 6, 6).isEmpty()){
					for(Entity entity : p.getNearbyEntities(6, 6, 6)) {
						int dam = (int) p.getLocation().distance(entity.getLocation());
						if(dam >0){
							dam = dam+1*dam;
						}else{}
						if(entity.getLocation().distance(blockToAdd)<=rad)
						{
							if(entity instanceof Player){
								Player player = (Player) entity;
								if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
								{
								}
								else
								{
									double dama = 10;
									player.damage(dama-dam+Math.random()*1.2);
									player.setVelocity(entity.getVelocity().multiply(0.75));
								}
							}
							if((entity instanceof Damageable)&&!(entity instanceof Player))
							{
								double dama = 10;
								((Damageable) entity).damage(dama-dam+Math.random()*1.2);
								entity.setVelocity(entity.getVelocity().multiply(0.75));
							}
						}
					}
				}
				if(blockToAdd.getBlock().getType().equals(Material.AIR)){
					try{
						p.getWorld().playSound(blockToAdd, Sound.BLOCK_GLASS_BREAK, 4, 9+RandInt.randInt(0, 5));
						ice.sendToLocation(blockToAdd);
						snow.sendToLocation(blockToAdd);

					} catch (Exception e) 
					{
						e.printStackTrace();
					}	
				}
			}
		}
	}

	public static void FrostII(Player p, MagicUser user)
	{
		if(user.getMana()>25.0){
			user.setMana(user.getMana() - 2.5);
			double x =RandInt.randInt(1,5);
			ParticleEffect ice = new ParticleEffect(ParticleType.SNOW_SHOVEL, x/100, 15, 0.5);
			ParticleEffect snow = new ParticleEffect(ParticleType.CRIT, x/100, 5, 0.5);
			Location a = p.getEyeLocation();
			LocationIterator blocksToAdd = new LocationIterator(a.getWorld(), a.toVector(), a.getDirection(), 0, 6);
			Location blockToAdd;
			double rad =2.5;
			while(blocksToAdd.hasNext()) {
				blockToAdd = blocksToAdd.next().getBlock().getLocation();
				if(!p.getNearbyEntities(6, 6, 6).isEmpty()){
					for(Entity entity : p.getNearbyEntities(6, 6, 6)) {
						int dam = (int) p.getLocation().distance(entity.getLocation());
						if(dam >0){
							dam = dam+1*dam;
						}else{}
						if(entity.getLocation().distance(blockToAdd)<=rad)
						{
							if(entity instanceof Player){
								Player player = (Player) entity;
								if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
								{
								}
								else
								{
									double dama = 10;
									player.damage(dama-dam+Math.random()*1.6);
									player.setVelocity(entity.getVelocity().multiply(0.5));
								}
							}
							if((entity instanceof Damageable)&&!(entity instanceof Player))
							{
								double dama = 10;
								((Damageable) entity).damage(dama-dam+Math.random()*1.6);
								entity.setVelocity(entity.getVelocity().multiply(0.5));
							}
						}
					}
				}
				if(blockToAdd.getBlock().getType().equals(Material.AIR)){
					try{
						p.getWorld().playSound(blockToAdd, Sound.BLOCK_GLASS_BREAK, 4, 9+RandInt.randInt(0, 5));
						ice.sendToLocation(blockToAdd);
						snow.sendToLocation(blockToAdd);

					} catch (Exception e) 
					{
						e.printStackTrace();
					}	
				}
			}
		}
	}
	public static void FrostIII(Player p, MagicUser user)
	{
		if(user.getMana()>25.0){
			user.setMana(user.getMana() - 2.5);
			double x =RandInt.randInt(1,5);
			ParticleEffect ice = new ParticleEffect(ParticleType.SNOW_SHOVEL, x/100, 15, 0.5);
			ParticleEffect snow = new ParticleEffect(ParticleType.CRIT, x/100, 5, 0.5);
			Location a = p.getEyeLocation();
			LocationIterator blocksToAdd = new LocationIterator(a.getWorld(), a.toVector(), a.getDirection(), 0, 6);
			Location blockToAdd;
			double rad =2.5;
			while(blocksToAdd.hasNext()) {
				blockToAdd = blocksToAdd.next().getBlock().getLocation();
				if(!p.getNearbyEntities(6, 6, 6).isEmpty()){
					for(Entity entity : p.getNearbyEntities(6, 6, 6)) {
						int dam = (int) p.getLocation().distance(entity.getLocation());
						if(dam >0){
							dam = dam+1*dam;
						}else{}
						if(entity.getLocation().distance(blockToAdd)<=rad)
						{
							if(entity instanceof Player){
								Player player = (Player) entity;
								if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
								{
								}
								else
								{
									double dama = 10;
									player.damage(dama-dam+Math.random()*1.6);
									player.setVelocity(entity.getVelocity().multiply(0.10));
								}
							}
							if((entity instanceof Damageable)&&!(entity instanceof Player))
							{
								double dama = 10;
								((Damageable) entity).damage(dama-dam+Math.random()*2);
								entity.setVelocity(entity.getVelocity().multiply(0.10));
							}
						}
					}
				}
				setBlock(blockToAdd.getBlock(),p);
				if(blockToAdd.getBlock().getType().equals(Material.AIR)){
					try{
						p.getWorld().playSound(blockToAdd, Sound.BLOCK_GLASS_BREAK, 4, 9+RandInt.randInt(0, 5));
						ice.sendToLocation(blockToAdd);
						snow.sendToLocation(blockToAdd);


					} catch (Exception e) 
					{
						e.printStackTrace();
					}	
				}
			}
		}
	}
	public static void IceSpike(Player p,MagicUser user)
	{
		if(((MagicUser)BkCP.onlinePlayers.get(p.getName())).getCool("icespike")==true){return;}
		//explosive projectile, ignite entities near target, high cost/cooldown
		else if(user.getMana()>20.0)
		{
			user.setMana(user.getMana() - 15.0);
			((MagicUser)BkCP.onlinePlayers.get(p.getName())).setCool("icespike", 5);
			Entity snowBall = p.getPlayer().getWorld().spawnEntity(p.getPlayer().getEyeLocation().add(p.getPlayer().getLocation().getDirection().normalize()), EntityType.SNOWBALL);
			snowBall.setVelocity(p.getPlayer().getLocation().getDirection().multiply(1.5));
			snowBall.setCustomName("spell.icespike");
			effect(snowBall);
		}
	}

	public static void iceBlast(Player p,MagicUser user)
	{
		if(((MagicUser)BkCP.onlinePlayers.get(p.getName())).getCool("iceblast")==true){return;}
		//AOE ignite entities near caster, high cost/cooldown
		else if(user.getMana()>41.0)
		{
			user.setMana(user.getMana() - 20.5);
			((MagicUser)BkCP.onlinePlayers.get(p.getName())).setCool("iceblast", 10);
			ParticleEffect ice = new ParticleEffect(ParticleType.SNOW_SHOVEL, 0, 200, 2);
			p.getWorld().playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 4, 10+RandInt.randInt(0,3));
			ice.sendToLocation(p.getLocation());
			freeze(p);

			for(Entity entity : p.getNearbyEntities(7, 7, 7))
			{
				if(entity instanceof Player){
					Player player = (Player) entity;
					if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
					{
					}
					else
					{
						player.setVelocity(player.getVelocity().multiply(0.50));
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0));
						player.damage(7);
					}
				}
				if((entity instanceof Damageable)&&!(entity instanceof Player))
				{
					LivingEntity le = (LivingEntity)entity;
					le.setVelocity(le.getVelocity().multiply(0.50));
					le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0));
					le.damage(7);
				}
			}
		}
	}
	public static void frostAura(Player p,MagicUser user)
	{
		if(user.getMana()>5.0)
		{
			user.setMana(user.getMana() - 2.5);
			ParticleEffect flame = new ParticleEffect(ParticleType.SNOW_SHOVEL, 0, 50, 0.5);
			flame.sendToLocation(p.getLocation().add(0,1,0));
			p.getWorld().playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 4, 10+RandInt.randInt(0, 3));
			freeze(p);
			for(Entity entity : p.getNearbyEntities(3, 3, 3))
			{
				if(entity instanceof Player){
					Player player = (Player) entity;
					if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
					{
					}
					else
					{
						player.setVelocity(player.getVelocity().multiply(0.10));
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 20, 0));
						player.damage(1);
					}
				}
				if((entity instanceof Damageable)&&!(entity instanceof Player))
				{
					LivingEntity le = (LivingEntity)entity;
					le.setVelocity(le.getVelocity().multiply(0.10));
					le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 20, 0));
					le.damage(1);
				}
			}

		}
	}
	public static void effect(final Entity e){
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable(){
			public void run(){	
				effectInt+=1;
				if(!e.isOnGround()&&!e.isDead()&&effectInt < 200)
				{
					//System.out.print(x);
					Location loc = e.getLocation();
					World w = e.getWorld();
					ParticleEffect par1 = new ParticleEffect(ParticleType.WATER_SPLASH, 0.02, 5, 0.0001);
					ParticleEffect par2 = new ParticleEffect(ParticleType.CRIT_MAGIC, 0.02, 5, 0.0001);
					try {
						w.playSound(loc, Sound.BLOCK_FIRE_EXTINGUISH, 4, 10+RandInt.randInt(0, 3));
						par1.sendToLocation(loc);
						par2.sendToLocation(loc);
					} catch (Exception e) {
						e.printStackTrace();
					}

					effect(e);
				}else{e.remove();
				//System.out.print("Killed");
				effectInt=0;}
			}

		} ,1l);
	}
	public static void freeze(final Player p)
	{
		Location loc = p.getPlayer().getLocation();
		loc.setY(loc.getY() - 1.0D);
		{
			setBlock(loc.getBlock(), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.NORTH), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.SOUTH), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.EAST), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.WEST), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.NORTH_EAST), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.NORTH_WEST), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.SOUTH_EAST), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.SOUTH_WEST), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.SOUTH).getRelative(BlockFace.SOUTH), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.EAST).getRelative(BlockFace.EAST), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.WEST).getRelative(BlockFace.WEST), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.NORTH_EAST).getRelative(BlockFace.NORTH), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.NORTH_EAST).getRelative(BlockFace.EAST), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.NORTH_WEST).getRelative(BlockFace.NORTH), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.NORTH_WEST).getRelative(BlockFace.WEST), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.SOUTH_EAST).getRelative(BlockFace.SOUTH), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.SOUTH_EAST).getRelative(BlockFace.EAST), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.SOUTH_WEST).getRelative(BlockFace.SOUTH), p.getPlayer());
			setBlock(loc.getBlock().getRelative(BlockFace.SOUTH_WEST).getRelative(BlockFace.WEST), p.getPlayer());
		}
	}
	public static void setBlock(Block b, Player p)
	{
		ParticleEffect snow = new ParticleEffect(ParticleType.EXPLOSION_NORMAL, 0.02, 5, 0.0001);
		p.playSound(b.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, (float) (1+Math.random()));
		try {
			snow.sendToLocation(b.getLocation().add(.5, 1, .5));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (b.getType().equals(Material.STATIONARY_WATER))
		{
			b.setType(Material.ICE);
		}
		if(!b.getType().equals(Material.AIR)&&b.getRelative(BlockFace.UP).getType().equals(Material.AIR))
		{
			b.getRelative(BlockFace.UP).setType(Material.SNOW);
		}

	}
}
