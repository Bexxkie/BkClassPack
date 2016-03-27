package com.bexxkie.bkcp.interaction.spells;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import com.bexxkie.bkcp.classes.interfaces.MagicUser;
import com.bexxkie.bkcp.util.LineOfSight;
import com.bexxkie.bkcp.util.ParticleEffect;
import com.bexxkie.bkcp.util.RandInt;
import com.bexxkie.bkcp.util.ParticleEffect.ParticleType;

public class Heal 
{

	//TODO ManaDrain/ minRequiered = healI_Values*2/3 

	public static void healI(Player p, MagicUser user)
	{
		if(user.getMana() > 20.0)
		{
			user.setMana(user.getMana()-10.2);
			double x =RandInt.randInt(1,5);
			ParticleEffect hp = new ParticleEffect(ParticleType.VILLAGER_HAPPY, x/100, 15, 0.5);
			hp.sendToLocation(p.getLocation().add(0,1,0));
			if(p.getHealth()<p.getMaxHealth())
			{
				p.setHealth(p.getHealth()+.5);
			}
		}
	}
	//Min = 20*2/3==13.33
	//Drain = 10.2*2/3==6.8
	public static void healII(Player p, MagicUser user)
	{
		if(user.getMana() > 13.33)
		{
			user.setMana(user.getMana()-6.8);
			double x =RandInt.randInt(1,5);
			ParticleEffect hp = new ParticleEffect(ParticleType.VILLAGER_HAPPY, x/100, 15, 0.5);
			hp.sendToLocation(p.getLocation().add(0,1,0));
			if(p.getHealth()<p.getMaxHealth())
			{

				p.setHealth(p.getHealth()+1);
			}
		}
	}
	//13.33*2/3==8.88
	//6.8*2/3==4.53
	public static void healIII(Player p, MagicUser user)
	{
		if(user.getMana() > 8.88)
		{
			user.setMana(user.getMana()-4.53);
			double x =RandInt.randInt(1,5);
			ParticleEffect hp = new ParticleEffect(ParticleType.VILLAGER_HAPPY, x/100, 15, 0.5);

			hp.sendToLocation(p.getLocation().add(0,1,0));
			if(p.getHealth()<p.getMaxHealth())
			{
				p.setHealth(p.getHealth()+1.5);
			}
		}
	}
	//8.88*2/3==5.92
	//4.53*2/3==3.02
	public static void healIV(Player p, MagicUser user)
	{
		if(user.getMana() > 5.92)
		{
			user.setMana(user.getMana()-3.02);
			double x =RandInt.randInt(1,5);
			ParticleEffect hp = new ParticleEffect(ParticleType.VILLAGER_HAPPY, x/100, 15, 0.5);

			hp.sendToLocation(p.getLocation().add(0,1,0));

			if(p.getHealth()<p.getMaxHealth())
			{

				p.setHealth(p.getHealth()+2);
			}
		}
	}

	//healOther
	public static void healOtherI(Player p, MagicUser user)
	{
		Entity ent = LineOfSight.getNearestEntityInSight(p, 3);
		if((ent)instanceof LivingEntity)
		{
			if(user.getMana() > 20.5)
			{
				user.setMana(user.getMana()-10.15);
				double x =RandInt.randInt(1,5);
				ParticleEffect hp = new ParticleEffect(ParticleType.VILLAGER_HAPPY, x/100, 15, 0.5);
				LivingEntity toHeal = ((LivingEntity)ent);
				hp.sendToLocation(toHeal.getLocation());

				if(toHeal.getHealth()>toHeal.getMaxHealth())
				{
					toHeal.setHealth(toHeal.getHealth()+0.5);
				}
			}
		}
	}

	public static void healOtherII(Player p, MagicUser user)
	{
		Entity ent = LineOfSight.getNearestEntityInSight(p, 3);
		if((ent)instanceof LivingEntity)
		{
			if(user.getMana() > 13.66)
			{
				user.setMana(user.getMana()-6.76);
				double x =RandInt.randInt(1,5);
				ParticleEffect hp = new ParticleEffect(ParticleType.VILLAGER_HAPPY, x/100, 15, 0.5);
				LivingEntity toHeal = ((LivingEntity)ent);
				hp.sendToLocation(toHeal.getLocation());
				if(toHeal.getHealth()>toHeal.getMaxHealth())
				{
					toHeal.setHealth(toHeal.getHealth()+1);
				}
			}
		}
	}
	//HealAura
	public static void healAura(Player p, MagicUser user)
	{
		if(user.getMana() > 20.5)
		{
			user.setMana(user.getMana()-10.15);
			double x =RandInt.randInt(1,5);
			ParticleEffect hp = new ParticleEffect(ParticleType.VILLAGER_HAPPY, x/100, 15, 0.5);

			List<Entity> ent = p.getNearbyEntities(5, 5, 5);
			for(Entity e :ent)
			{
				if((e)instanceof LivingEntity)
				{
					LivingEntity healTarget = ((LivingEntity)e);
					hp.sendToLocation(healTarget.getLocation());
					if(healTarget.getHealth()<healTarget.getMaxHealth())
					{
						healTarget.setHealth(healTarget.getHealth()+0.5);
					}
				}
			}
		}
	}

}
