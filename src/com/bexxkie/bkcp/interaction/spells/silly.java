package com.bexxkie.bkcp.interaction.spells;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.classes.interfaces.MagicUser;
import com.bexxkie.bkcp.modules.pvpControl.PvpControlMain;
import com.bexxkie.bkcp.util.LineOfSight;
import com.bexxkie.bkcp.util.ParticleEffect;
import com.bexxkie.bkcp.util.RandInt;
import com.bexxkie.bkcp.util.ParticleEffect.ParticleType;

public class silly 
{

	public static int effectInt = 0;
	@SuppressWarnings("deprecation")
	public static void cake(Player p, MagicUser user)
	{
		if(((MagicUser)BkCP.onlinePlayers.get(p.getName())).getCool("throwcake")==true){return;}
		if(user.getMana()>15)
		{
			user.setMana(user.getMana() - 12.5);
			((MagicUser)BkCP.onlinePlayers.get(p.getName())).setCool("throwcake", 1);
			FallingBlock cake = p.getPlayer().getWorld().spawnFallingBlock(p.getPlayer().getEyeLocation().add(p.getPlayer().getLocation().getDirection().normalize()), Material.CAKE_BLOCK, (byte)0);
			cake.setVelocity(p.getPlayer().getLocation().getDirection().multiply(1.5));
			cake.setDropItem(false);
			cake.setCustomName("thrownCake");
			effect(cake);
		}
	}
	public static void fish(Player p, MagicUser user)
	{
		if(((MagicUser)BkCP.onlinePlayers.get(p.getName())).getCool("throwfish")==true){return;}

		else
			if(user.getMana()>15)
			{
				user.setMana(user.getMana() - 12.5);
				((MagicUser)BkCP.onlinePlayers.get(p.getName())).setCool("throwfish", 1);
				//FallingBlock cake = p.getPlayer().getWorld().spawnFallingBlock(p.getPlayer().getEyeLocation().add(p.getPlayer().getLocation().getDirection().normalize()), Material.CAKE_BLOCK, (byte)0);
				Guardian cake = (Guardian) p.getPlayer().getWorld().spawnEntity(p.getPlayer().getEyeLocation().add(p.getPlayer().getLocation().getDirection()), EntityType.GUARDIAN);
				cake.setVelocity(p.getPlayer().getLocation().getDirection().multiply(1.5));
				cake.setNoDamageTicks(600);
				cake.setCustomName("thrownFish");
				cake.setElder(true);
				effect(cake);
			}
	}
	public static void tntRain(Player p, MagicUser user)
	{
		if(((MagicUser)BkCP.onlinePlayers.get(p.getName())).getCool("tntrain")==true){return;}
		else{
			Entity ent = LineOfSight.getNearestEntityInSight(p, 15);
			if((ent)instanceof LivingEntity)
			{
				if(user.getMana()>20)
				{
					user.setMana(user.getMana()-20);
					((MagicUser)BkCP.onlinePlayers.get(p.getName())).setCool("tntrain", 5);
					LivingEntity toTroll = ((LivingEntity)ent);
					Location toDrop = toTroll.getLocation();
					for(int i =0; i< RandInt.randInt(2,6);i++)
					{
						toDrop.add(RandInt.randDouble(0, 1),RandInt.randDouble(10, 15),RandInt.randDouble(0, 1));
						TNTPrimed tnt = ent.getWorld().spawn(toDrop, TNTPrimed.class);
						Entity snowBall = p.getPlayer().getWorld().spawnEntity(toDrop.add(0,3,0), EntityType.SPLASH_POTION);
						tnt.setPassenger(snowBall);
						snowBall.setCustomName("spell.creeperRain");
						tnt.setFuseTicks(100);
						tnt.setCustomName("tntRain");

					}
				}
			}
		}
	}
	public static void creeperRain(Player p, MagicUser user)
	{
		if(((MagicUser)BkCP.onlinePlayers.get(p.getName())).getCool("creeperrain")==true){return;}
		else{
			Entity ent = LineOfSight.getNearestEntityInSight(p, 15);
			if((ent)instanceof LivingEntity)
			{
				if(user.getMana()>20)
				{
					user.setMana(user.getMana()-20);
					((MagicUser)BkCP.onlinePlayers.get(p.getName())).setCool("creeperrain", 5);
					LivingEntity toTroll = ((LivingEntity)ent);
					Location toDrop = toTroll.getLocation();
					for(int i =0; i< RandInt.randInt(2,6);i++)
					{
						toDrop.add(RandInt.randDouble(0, 1),RandInt.randDouble(4, 6),RandInt.randDouble(0, 1));
						Creeper creeper = (Creeper)ent.getWorld().spawnEntity(toDrop, EntityType.CREEPER);
						Entity snowBall = p.getPlayer().getWorld().spawnEntity(toDrop.add(0,3,0), EntityType.SPLASH_POTION);
						snowBall.setCustomName("spell.creeperRain");
						creeper.setPassenger(snowBall);
						creeper.setTarget(toTroll);
						creeper.setPowered(true);
						creeper.setCustomName("creeperRain");
					}				
				}
			}
		}
	}

	public static void potionRain(Player p, MagicUser user)
	{
		if(((MagicUser)BkCP.onlinePlayers.get(p.getName())).getCool("potionrain")==true){return;}
		else{
			Entity ent = LineOfSight.getNearestEntityInSight(p, 15);
			if((ent)instanceof LivingEntity)
			{
				if(user.getMana()>=50)
				{
					user.setMana(user.getMana()-50);
					((MagicUser)BkCP.onlinePlayers.get(p.getName())).setCool("potionrain", 30);
					LivingEntity toTroll = ((LivingEntity)ent);
					Location toDrop = toTroll.getLocation();
					for(int i =0; i< RandInt.randInt(10,30);i++)
					{
						toDrop.add(RandInt.randDouble(-1, 1),RandInt.randDouble(-6, 6),RandInt.randDouble(-1, 1));
						Entity snowBall = p.getPlayer().getWorld().spawnEntity(toDrop.add(0,3,0), EntityType.SPLASH_POTION);
						snowBall.setCustomName("spell.potionRain");
					}				
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
					ParticleEffect eff = new ParticleEffect(ParticleType.CRIT, RandInt.randDouble(0,1), RandInt.randInt(1, 3), 0.5);
					ParticleEffect eff2 = new ParticleEffect(ParticleType.SNOW_SHOVEL, RandInt.randDouble(0,1), RandInt.randInt(1, 5), 0.5);

					try {
						w.playSound(loc, Sound.BLOCK_SNOW_BREAK, 4,0+RandInt.randInt(0, 3));
						eff.sendToLocation(loc);
						eff2.sendToLocation(loc);
					} catch (Exception e) {
						e.printStackTrace();
					}
					effect(e);
				}else{
					for(Entity ent : e.getNearbyEntities(5, 5, 5))
					{
						if(ent instanceof Player)
						{
							Player player = (Player) ent;
							if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
							{
							}
							else
							{
								Location loc = e.getLocation();
								Vector dir = player.getLocation().subtract(loc.add(0,-2,0)).toVector();
								loc.getWorld().createExplosion(loc, 0.0f);
								player.setFallDistance(0);
								player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
								player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,200,10));
								player.setVelocity(dir.multiply(RandInt.randDouble(0.5, 1.5)));
							}
						}
						if(ent instanceof LivingEntity&&!(ent instanceof Player))
						{
							LivingEntity le = (LivingEntity) ent;
							Location loc = e.getLocation();
							Vector dir = le.getLocation().subtract(loc.add(0,-2,0)).toVector();
							loc.getWorld().createExplosion(loc, 0.0f);
							le.setFallDistance(0);
							le.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
							le.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,200,10));
							le.setVelocity(dir.multiply(RandInt.randDouble(0.5, 1.5)));
						}
					}
					e.remove();
					//System.out.print("Killed");
					effectInt=0;}
			}

		} ,1l);
	}

}
