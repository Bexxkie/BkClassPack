package com.bexxkie.bkcp.interaction.spells;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import com.bexxkie.bkcp.classes.interfaces.MagicUser;
import com.bexxkie.bkcp.modules.pvpControl.PvpControlMain;
import com.bexxkie.bkcp.util.LineOfSight;
import com.bexxkie.bkcp.util.ParticleEffect;
import com.bexxkie.bkcp.util.ParticleEffect.ParticleType;

public class LifeDrain 
{
	//TODO ManaDrain/ minRequiered = healI_Values*2/3 
	//player p = target
	public static void drainI(Player p, MagicUser user)
	{
		Entity entity = LineOfSight.getNearestEntityInSight(p, 3);
		if(entity instanceof Player){
			Player player = (Player) entity;
			if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
			{
			}
			else
			{
				ParticleEffect dr = new ParticleEffect(ParticleType.REDSTONE, 0, 15, 0.5);
				ParticleEffect hp = new ParticleEffect(ParticleType.HEART, 0, 15, 0.5);
				dr.sendToLocation(p.getLocation().add(0,1,0));
				hp.sendToLocation(p.getLocation().add(0,1,0));
				player.setHealth(player.getHealth()-2);
				p.setHealth(p.getHealth()+1);
			}
		}
		if((entity instanceof Damageable)&&!(entity instanceof Player))
		{
			if(((LivingEntity)entity).getHealth()>5&&p.getHealth()<p.getMaxHealth())
			{
				if(user.getMana() > 20.0)
				{
					user.setMana(user.getMana()-10.2);

					ParticleEffect dr = new ParticleEffect(ParticleType.REDSTONE, 0, 15, 0.5);
					ParticleEffect hp = new ParticleEffect(ParticleType.HEART, 0, 15, 0.5);
					dr.sendToLocation(p.getLocation().add(0,1,0));
					hp.sendToLocation(p.getLocation().add(0,1,0));
					((LivingEntity)entity).setHealth(((LivingEntity)entity).getHealth()-2);
					p.setHealth(p.getHealth()+1);
				}
			}
		}
	}

	public static void drainII(Player p, MagicUser user)
	{
		Entity entity = LineOfSight.getNearestEntityInSight(p, 3);
		if(entity instanceof Player){
			Player player = (Player) entity;
			if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
			{
			}
			else
			{
				ParticleEffect dr = new ParticleEffect(ParticleType.REDSTONE, 0, 15, 0.5);
				ParticleEffect hp = new ParticleEffect(ParticleType.HEART, 0, 15, 0.5);
				dr.sendToLocation(p.getLocation().add(0,1,0));
				hp.sendToLocation(p.getLocation().add(0,1,0));
				player.setHealth(player.getHealth()-2);
				p.setHealth(p.getHealth()+2);
			}
		}
		if((entity instanceof Damageable)&&!(entity instanceof Player))
		{
			if(((LivingEntity)entity).getHealth()>3&&p.getHealth()<p.getMaxHealth()){
				if(user.getMana() > 13.33)
				{
					user.setMana(user.getMana()-6.8);
					ParticleEffect dr = new ParticleEffect(ParticleType.REDSTONE, 0, 15, 0.5);
					ParticleEffect hp = new ParticleEffect(ParticleType.HEART, 0, 15, 0.5);
					dr.sendToLocation(p.getLocation().add(0,1,0));
					hp.sendToLocation(p.getLocation().add(0,1,0));
					((LivingEntity)entity).setHealth(((LivingEntity)entity).getHealth()-2);
					p.setHealth(p.getHealth()+2);
				}
			}
		}
	}
	public static void feedI(Player p, MagicUser user)
	{
		Entity entity = LineOfSight.getNearestEntityInSight(p, 3);
		if(entity instanceof Player)
		{
			Player player = (Player) entity;
			if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
			{
			}
			else
			{
				ParticleEffect dr = new ParticleEffect(ParticleType.REDSTONE, 0, 15, 0.5);
				ParticleEffect hp = new ParticleEffect(ParticleType.HEART, 0, 15, 0.5);
				dr.sendToLocation(p.getLocation().add(0,1,0));
				hp.sendToLocation(p.getLocation().add(0,1,0));
				player.setFoodLevel(player.getFoodLevel()-3);
				p.setHealth(p.getFoodLevel()+1);
			}
		}
		if((entity instanceof Damageable)&&!(entity instanceof Player))
		{
			if(p.getFoodLevel()>2)
			{
				if(user.getMana() > 21.0)
				{
					user.setMana(user.getMana()-20.0);

					ParticleEffect dr = new ParticleEffect(ParticleType.SPELL_INSTANT, 0, 15, 0.5);
					ParticleEffect hp = new ParticleEffect(ParticleType.SPELL_WITCH, 0, 15, 0.5);
					dr.sendToLocation(p.getLocation().add(0,1,0));
					hp.sendToLocation(p.getLocation().add(0,1,0));
					((Player)entity).setFoodLevel(((Player)entity).getFoodLevel()-3);
					p.setFoodLevel(p.getFoodLevel()+1);
				}
			}	
		}
	}
	public static void feedII(Player p, MagicUser user)
	{
		Entity entity = LineOfSight.getNearestEntityInSight(p, 3);
		if(entity instanceof Player)
		{
			Player player = (Player) entity;
			if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
			{
			}
			else
			{
				ParticleEffect dr = new ParticleEffect(ParticleType.REDSTONE, 0, 15, 0.5);
				ParticleEffect hp = new ParticleEffect(ParticleType.HEART, 0, 15, 0.5);
				dr.sendToLocation(p.getLocation().add(0,1,0));
				hp.sendToLocation(p.getLocation().add(0,1,0));
				player.setFoodLevel(player.getFoodLevel()-3);
				p.setHealth(p.getFoodLevel()+2);
			}
		}
		if((entity instanceof Damageable)&&!(entity instanceof Player))
		{
			if(p.getFoodLevel()>2)
			{
				if(user.getMana() > 15)
				{
					user.setMana(user.getMana()-13.33);

					ParticleEffect dr = new ParticleEffect(ParticleType.SPELL_INSTANT, 0, 15, 0.5);
					ParticleEffect hp = new ParticleEffect(ParticleType.SPELL_WITCH, 0, 15, 0.5);
					dr.sendToLocation(p.getLocation().add(0,1,0));
					hp.sendToLocation(p.getLocation().add(0,1,0));
					((Player)entity).setFoodLevel(((Player)entity).getFoodLevel()-3);
					p.setFoodLevel(p.getFoodLevel()+2);
				}
			}
		}	
	}

}
