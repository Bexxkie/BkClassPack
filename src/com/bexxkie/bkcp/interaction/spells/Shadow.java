package com.bexxkie.bkcp.interaction.spells;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.classes.interfaces.MagicUser;
import com.bexxkie.bkcp.modules.pvpControl.PvpControlMain;
import com.bexxkie.bkcp.util.LocationIterator;
import com.bexxkie.bkcp.util.ParticleEffect;
import com.bexxkie.bkcp.util.RandInt;
import com.bexxkie.bkcp.util.ParticleEffect.ParticleType;

public class Shadow 
{	
	public static int effectInt = 0;

	public static void beamI(Player p, MagicUser user)
	{
		if(user.getMana()>25.0){
			user.setMana(user.getMana() - 2.5);
			double x =RandInt.randInt(1,5);
			ParticleEffect shadow = new ParticleEffect(ParticleType.SPELL_MOB, x/100, 15, 0.5);
			ParticleEffect shadowII = new ParticleEffect(ParticleType.SPELL_WITCH, x/100, 5, 0.5);
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
							if(entity instanceof Player&&entity.getType()!=EntityType.ENDERMITE)
							{
								Player player = (Player) entity;
								if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
								{
								}
								else
								{
									double dama = 10;
									((Damageable) entity).damage(dama-dam+Math.random()*1.2);
									if(RandInt.randInt(1, 15)==3)
									{
										Endermite emt = (Endermite) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ENDERMITE);
										emt.setTarget(player);
									}
								}
							}
							if(entity instanceof LivingEntity&&entity.getType()!=EntityType.ENDERMITE&&!(entity instanceof Player))
							{
								double dama = 10;
								((Damageable) entity).damage(dama-dam+Math.random()*1.2);
								if(RandInt.randInt(1, 15)==3)
								{
									Endermite emt = (Endermite) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ENDERMITE);
									emt.setTarget((LivingEntity) entity);
								}
							}
						}
					}
				}
				if(blockToAdd.getBlock().getType().equals(Material.AIR)){
					try{
						p.getWorld().playSound(blockToAdd, Sound.ENTITY_ENDERDRAGON_GROWL, 4, 10+RandInt.randInt(0, 3));
						shadow.sendToLocation(blockToAdd);
						shadowII.sendToLocation(blockToAdd);

					} catch (Exception e) 
					{
						e.printStackTrace();
					}	
				}
			}
		}
	}
	public static void beamII(Player p, MagicUser user)
	{
		if(user.getMana()>25.0){
			user.setMana(user.getMana() - 2.2);
			double x =RandInt.randInt(1,5);
			ParticleEffect shadow = new ParticleEffect(ParticleType.SPELL_MOB, x/100, 15, 0.5);
			ParticleEffect shadowII = new ParticleEffect(ParticleType.SPELL_WITCH, x/100, 5, 0.5);
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
							if(entity instanceof Player&&entity.getType()!=EntityType.ENDERMITE)
							{
								Player player = (Player) entity;
								if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
								{
								}
								else
								{
									double dama = 10;
									((Damageable) entity).damage(dama-dam+Math.random()*1.6);
									if(RandInt.randInt(1, 12)==3)
									{
										Endermite emt = (Endermite) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ENDERMITE);
										emt.setTarget(player);
									}
								}
							}
							if(entity instanceof LivingEntity&&entity.getType()!=EntityType.ENDERMITE&&!(entity instanceof Player))
							{
								double dama = 10;
								((Damageable) entity).damage(dama-dam+Math.random()*1.6);
								if(RandInt.randInt(1, 12)==3)
								{
									Endermite emt = (Endermite) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ENDERMITE);
									emt.setTarget((LivingEntity) entity);
								}
							}
						}
					}
				}
				if(blockToAdd.getBlock().getType().equals(Material.AIR)){
					try{
						p.getWorld().playSound(blockToAdd, Sound.ENTITY_ENDERDRAGON_GROWL, 4, 10+RandInt.randInt(0, 3));
						shadow.sendToLocation(blockToAdd);
						shadowII.sendToLocation(blockToAdd);

					} catch (Exception e) 
					{
						e.printStackTrace();
					}	
				}
			}
		}
	}
	public static void beamIII(Player p, MagicUser user)
	{
		if(user.getMana()>25.0){
			user.setMana(user.getMana() - 2.0);
			double x =RandInt.randInt(1,5);
			ParticleEffect shadow = new ParticleEffect(ParticleType.SPELL_MOB, x/100, 15, 0.5);
			ParticleEffect shadowII = new ParticleEffect(ParticleType.SPELL_WITCH, x/100, 5, 0.5);
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
							if(entity instanceof Player&&entity.getType()!=EntityType.ENDERMITE)
							{
								Player player = (Player) entity;
								if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
								{
								}
								else
								{
									double dama = 10;
									((Damageable) entity).damage(dama-dam+Math.random()*2);
									if(RandInt.randInt(1, 5)==3)
									{
										Endermite emt = (Endermite) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ENDERMITE);
										emt.setTarget(player);
									}
								}
							}
							if(entity instanceof LivingEntity&&entity.getType()!=EntityType.ENDERMITE&&!(entity instanceof Player))
							{
								double dama = 10;
								((Damageable) entity).damage(dama-dam+Math.random()*2);
								if(RandInt.randInt(1, 5)==3)
								{
									Endermite emt = (Endermite) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ENDERMITE);
									emt.setTarget((LivingEntity) entity);
								}
							}
						}
					}
				}
				if(blockToAdd.getBlock().getType().equals(Material.AIR)){
					try{
						p.getWorld().playSound(blockToAdd, Sound.ENTITY_ENDERDRAGON_GROWL, 4, 10+RandInt.randInt(0, 3));
						shadow.sendToLocation(blockToAdd);
						shadowII.sendToLocation(blockToAdd);

					} catch (Exception e) 
					{
						e.printStackTrace();
					}	
				}
			}
		}
	}
	public static void ShadowBolt(Player p,MagicUser user)
	{
		if(((MagicUser)BkCP.onlinePlayers.get(p.getName())).getCool("shadowbolt")==true){return;}
		else if(user.getMana()>20.0)
		{
			user.setMana(user.getMana() - 15.0);
			((MagicUser)BkCP.onlinePlayers.get(p.getName())).setCool("shadowbolt", 5);
			Entity snowBall = p.getPlayer().getWorld().spawnEntity(p.getPlayer().getEyeLocation().add(p.getPlayer().getLocation().getDirection().normalize()), EntityType.SPLASH_POTION);
			snowBall.setVelocity(p.getPlayer().getLocation().getDirection().multiply(1.5));
			snowBall.setCustomName("spell.shadowBolt");
			snowBall.setCustomNameVisible(false);
			effect(snowBall);
		}
	}

	public static void shadowBlast(Player p,MagicUser user)
	{
		if(((MagicUser)BkCP.onlinePlayers.get(p.getName())).getCool("shadowblast")==true){return;}
		else if(user.getMana()>41.0)
		{
			user.setMana(user.getMana() - 20.5);
			((MagicUser)BkCP.onlinePlayers.get(p.getName())).setCool("shadowblast", 10);
			ParticleEffect portal = new ParticleEffect(ParticleType.SPELL_MOB, 0.1, 650, 3);
			ParticleEffect par1 = new ParticleEffect(ParticleType.SPELL_WITCH, 0.5, 450, 1);
			portal.sendToLocation(p.getLocation().add(0,0.5,0));
			par1.sendToLocation(p.getLocation().add(0,0.5,0));
			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_HURT, 4, 10+RandInt.randInt(0, 3));
			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 4, 10+RandInt.randInt(0, 3));
			for(Entity entity : p.getNearbyEntities(6, 6, 6))
			{
				if(entity instanceof Player&&entity.getType()!=EntityType.ENDERMITE)
				{
					Player player = (Player) entity;
					if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
					{
					}
					else
					{
						Endermite emt = (Endermite) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ENDERMITE);
						emt.setTarget((LivingEntity) entity);
						((Damageable) entity).damage(10);
					}
				}
				if(entity instanceof LivingEntity&&entity.getType()!=EntityType.ENDERMITE&&!(entity instanceof Player))
				{
					Endermite emt = (Endermite) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ENDERMITE);
					emt.setTarget((LivingEntity) entity);
					((Damageable) entity).damage(10);
				}
			}
		}
	}
	public static void shadowAura(Player p,MagicUser user)
	{
		if(user.getMana()>5.0)
		{
			user.setMana(user.getMana() - 2.5);
			ParticleEffect flame = new ParticleEffect(ParticleType.SPELL_MOB, 0, 50, 0.5);
			ParticleEffect par1 = new ParticleEffect(ParticleType.SPELL_WITCH, 0.1, 25, .25);
			flame.sendToLocation(p.getLocation().add(0,0.5,0));
			par1.sendToLocation(p.getLocation().add(0,0.5,0));
			p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_HURT, 1, 0+RandInt.randInt(0, 1));
			//p.getWorld().playSound(p.getLocation(), Sound.EXPLODE, 4, 10+RandInt.randInt(0, 3));
			for(Entity entity : p.getNearbyEntities(3, 3, 3))
			{
				if(entity instanceof Player)//&&entity.getType()!=EntityType.ENDERMITE)
				{
					Player player = (Player) entity;
					if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
					{
					}
					else
					{
						//Endermite emt = (Endermite) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.ENDERMITE);
						//emt.setTarget((LivingEntity) entity);
						((Damageable) entity).damage(1);
					}
				}
				if(entity instanceof LivingEntity/*&&entity.getType()!=EntityType.ENDERMITE*/&&!(entity instanceof Player))
				{
					//Endermite emt = (Endermite) ent.getWorld().spawnEntity(ent.getLocation(), EntityType.ENDERMITE);
					//emt.setTarget((LivingEntity) ent);
					((Damageable) entity).damage(1);
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
					//loc.add(-0.5, -0.5, -0.5);
					World w = e.getWorld();
					ParticleEffect par1 = new ParticleEffect(ParticleType.SPELL_WITCH, 0.1, 10, 0);
					ParticleEffect par2 = new ParticleEffect(ParticleType.CRIT_MAGIC, 0.02, 5, 0.1);
					try {
						w.playSound(loc, Sound.ENTITY_ENDERDRAGON_HURT, 4,0+RandInt.randInt(0, 3));
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

}
