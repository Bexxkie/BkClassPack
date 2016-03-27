package com.bexxkie.bkcp.interaction.spells;


import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
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



public class Flame 
{
	//global Variables
	public static int effectInt = 0;

	public static void flameI(Player p,MagicUser user)
	{
		if(user.getMana()>25.0){
			user.setMana(user.getMana() - 2.5);
			double x =RandInt.randInt(1,5);
			ParticleEffect flame = new ParticleEffect(ParticleType.FLAME, x/100, 15, 0.5);
			ParticleEffect smoke = new ParticleEffect(ParticleType.SMOKE_LARGE, x/100, 5, 0.5);
			Location a = p.getEyeLocation();
			LocationIterator blocksToAdd = new LocationIterator(a.getWorld(), a.toVector(), a.getDirection(), 0, 6);
			Location blockToAdd;
			double rad = 2.5;
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
							if(entity instanceof Player)
							{
								Player player = (Player) entity;
								if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
								{
								}
								else
								{
									double dama = 10;
									player.damage(dama-dam+Math.random()*2);
									player.setFireTicks(100-dam);
								}
							}
							if((entity instanceof Damageable)&&!(entity instanceof Player))
							{
								double dama = 10;

								((Damageable) entity).damage(dama-dam+Math.random()*2);

								entity.setFireTicks(100-dam);
							}
						}
					}
				}
				if(blockToAdd.getBlock().getType().equals(Material.AIR)){
					try{
						//p.getWorld().playSound(blockToAdd, Sound.FIRE, 1, (float) (Math.random()*2+Math.random()/1.5));
						p.getWorld().playSound(blockToAdd, Sound.BLOCK_FIRE_AMBIENT, 4, 10);
						flame.sendToLocation(blockToAdd);
						smoke.sendToLocation(blockToAdd);

					} catch (Exception e) 
					{
						e.printStackTrace();
					}	
				}
			}
		}
	}
	public static void flameII(Player p,MagicUser user)
	{
		if(user.getMana()>17.7){
			user.setMana(user.getMana() - 2.2);
			double x =RandInt.randInt(1,5);
			ParticleEffect flame = new ParticleEffect(ParticleType.FLAME, x/100, 15, 0.5);
			ParticleEffect smoke = new ParticleEffect(ParticleType.LAVA, x/100, 5, 0.5);
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
							if(entity instanceof Player)
							{
								Player player = (Player) entity;
								if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
								{
								}
								else
								{
									double dama = 8;
									player.damage(dama-dam+Math.random()*2);
									player.setFireTicks(150-dam);
								}
							}
							if((entity instanceof Damageable)&&!(entity instanceof Player))
							{
								double dama = 8;

								((Damageable) entity).damage(dama-dam+Math.random()*2);

								entity.setFireTicks(150-dam);
							}


						}
					}
				}
				if(blockToAdd.getBlock().getType().equals(Material.AIR)){
					try{
						//p.getWorld().playSound(blockToAdd, Sound.FIRE, 1, (float) (Math.random()*2+Math.random()/1.5));
						p.getWorld().playSound(blockToAdd, Sound.ENTITY_BLAZE_DEATH, 1, (float) Math.random()*2);

						flame.sendToLocation(blockToAdd);
						smoke.sendToLocation(blockToAdd);

					} catch (Exception e) 
					{
						e.printStackTrace();
					}	
				}
			}
		}
	}
	public static void flameIII(Player p,MagicUser user)
	{
		if(user.getMana()>17.7){
			user.setMana(user.getMana() - 2.0);
			double x =RandInt.randInt(1,5);
			ParticleEffect flame = new ParticleEffect(ParticleType.FLAME, x/100, 15, 0.5);
			ParticleEffect smoke = new ParticleEffect(ParticleType.LAVA, x/100, 5, 0.5);
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
							if(entity instanceof Player)
							{
								Player player = (Player) entity;
								if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
								{
								}
								else
								{
									double dama = 8;
									player.damage(dama-dam+Math.random()*2);
									player.setFireTicks(150-dam);
								}
							}
							if((entity instanceof Damageable)&&!(entity instanceof Player))
							{
								double dama = 8;
								((Damageable) entity).damage(dama-dam+Math.random()*2);
								entity.setFireTicks(150-dam);
							}
						}
					}
				}
				setBlock(blockToAdd.getBlock(),p);
				if(blockToAdd.getBlock().getType().equals(Material.AIR)){

					try{
						//p.getWorld().playSound(blockToAdd, Sound.FIRE, 1, (float) (Math.random()*2+Math.random()/1.5));
						p.getWorld().playSound(blockToAdd, Sound.ENTITY_BLAZE_DEATH, 1, (float) Math.random()*2);

						flame.sendToLocation(blockToAdd);
						smoke.sendToLocation(blockToAdd);

					} catch (Exception e) 
					{
						e.printStackTrace();
					}	
				}
			}
		}
	}
	public static void fireBall(Player p,MagicUser user)
	{ 
		if(((MagicUser)BkCP.onlinePlayers.get(p.getName())).getCool("fireball")==true){return;}
		//explosive projectile, ignite entities near target, high cost/cooldown
		else if(user.getMana()>20.0)
		{
			user.setMana(user.getMana() - 15.0);

			((MagicUser)BkCP.onlinePlayers.get(p.getName())).setCool("fireball", 5);
			Entity fireBall = p.getPlayer().getWorld().spawnEntity(p.getPlayer().getEyeLocation().add(p.getPlayer().getLocation().getDirection().normalize()), EntityType.FIREBALL);
			fireBall.setVelocity(p.getPlayer().getLocation().getDirection().multiply(1.5));
			fireBall.setCustomName("fireBall");
			effect(fireBall);
		}
	}
	public static void fireBlast(Player p,MagicUser user)
	{
		if(((MagicUser)BkCP.onlinePlayers.get(p.getName())).getCool("fireblast")==true){return;}
		//AOE ignite entities near caster, high cost/cooldown
		else if(user.getMana()>41.0)
		{
			user.setMana(user.getMana() - 20.5);
			p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 60, 0));
			if(p.getFireTicks()>0){
				p.setFireTicks(0);
			}
			((MagicUser)BkCP.onlinePlayers.get(p.getName())).setCool("fireblast", 10);
			ParticleEffect flame = new ParticleEffect(ParticleType.FLAME, 0, 200, 2);
			ParticleEffect lava = new ParticleEffect(ParticleType.LAVA, 0, 15, 1);
			flame.sendToLocation(p.getLocation());
			lava.sendToLocation(p.getLocation());
			p.getWorld().playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);
			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 4, (float) (Math.random()+10/Math.random()));
			burn(p);
			for(Entity ent : p.getNearbyEntities(4, 3, 4))
			{
				if(ent instanceof Player)
				{
					Player player = (Player) ent;
					if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
					{
					}
					else
					{
						player.setFireTicks(80);
						player.damage(5);
					}
				}
				if((ent instanceof Damageable)&&!(ent instanceof Player))
				{
					ent.setFireTicks(80);
					((Damageable) ent).damage(5);
				}
			}
		}
	}
	public static void fireAura(Player p,MagicUser user)
	{
		// resist cold, ignite entities on contact, very low damage, focus spell
		if(user.getMana()>5.0)
		{
			user.setMana(user.getMana() - 2.5);
			p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 60, 0));
			if(p.getFireTicks()>0){
				p.setFireTicks(0);
			}

			ParticleEffect flame = new ParticleEffect(ParticleType.FLAME, 0, 50, 0.5);
			flame.sendToLocation(p.getLocation().add(0,1,0));
			p.getWorld().playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);
			burn(p);
			for(Entity ent : p.getNearbyEntities(3, 3, 3))
			{
				if(ent instanceof Player)
				{
					Player player = (Player) ent;
					if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
					{
					}
					else
					{
						player.setFireTicks(10);
					}
				}
				if((ent instanceof Damageable)&&!(ent instanceof Player))
				{
					ent.setFireTicks(10);
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
					loc.add(+0.5, +0.5, +0.5);
					World w = e.getWorld();
					ParticleEffect fire = new ParticleEffect(ParticleType.FLAME, 0.02, 5, 0.0001);
					ParticleEffect lava = new ParticleEffect(ParticleType.LAVA, 0.02, 5, 0.0001);
					try {
						w.playEffect(loc, Effect.BLAZE_SHOOT, 1);
						fire.sendToLocation(loc);
						lava.sendToLocation(loc);
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

	public static void burn(final Player p)
	{
		Location loc = p.getPlayer().getLocation();
		loc.setY(loc.getY() - 1.0D);
		{
			//setBlock(loc.getBlock(), p.getPlayer());
			//setBlock(loc.getBlock().getRelative(BlockFace.NORTH), p.getPlayer());
			//setBlock(loc.getBlock().getRelative(BlockFace.SOUTH), p.getPlayer());
			//setBlock(loc.getBlock().getRelative(BlockFace.EAST), p.getPlayer());
			//setBlock(loc.getBlock().getRelative(BlockFace.WEST), p.getPlayer());
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
		if (b.getType().isSolid())
		{
			ParticleEffect snow = new ParticleEffect(ParticleType.EXPLOSION_NORMAL, 0.02, 5, 0.0001);
			p.playSound(b.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, (float) (1+Math.random()));
			try {
				snow.sendToLocation(b.getLocation().add(.5, 1, .5));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(b.getType().equals(Material.ICE))
			{
				b.setType(Material.WATER);
			}
			if(b.getRelative(BlockFace.UP).getType().equals(Material.SNOW))
			{
				b.getRelative(BlockFace.UP).setType(Material.AIR);
			}
			if(b.getType().equals(Material.SNOW))
			{
				b.setType(Material.AIR);
			}
		}
	}


}
