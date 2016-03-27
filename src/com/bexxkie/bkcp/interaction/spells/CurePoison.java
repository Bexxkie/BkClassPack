package com.bexxkie.bkcp.interaction.spells;


import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import com.bexxkie.bkcp.classes.interfaces.MagicUser;
import com.bexxkie.bkcp.util.ParticleEffect;
import com.bexxkie.bkcp.util.RandInt;
import com.bexxkie.bkcp.util.ParticleEffect.ParticleType;

public class CurePoison 
{
	private static PotionEffectType[] pots = 
		{
		(PotionEffectType.BLINDNESS),
		(PotionEffectType.CONFUSION),
		(PotionEffectType.HUNGER),
		(PotionEffectType.POISON),
		(PotionEffectType.SLOW),
		(PotionEffectType.SLOW_DIGGING),
		(PotionEffectType.WEAKNESS),
		(PotionEffectType.WITHER)
		};
	private static PotionEffectType[] poisons = 
		{
		(PotionEffectType.HUNGER),
		(PotionEffectType.POISON),
		(PotionEffectType.WEAKNESS)
		};

	public static void cureI(Player p, MagicUser user)
	{
		double x =RandInt.randInt(1,5);
		ParticleEffect clearPot = new ParticleEffect(ParticleType.CRIT_MAGIC, x/100, 15, 0.5);
		for(PotionEffectType pot : poisons)
		{
			if(p.hasPotionEffect(pot)){
				if(user.getMana() > 14.7){
					user.setMana(user.getMana() - 14.7);
					p.removePotionEffect(pot);
					clearPot.sendToLocation(p.getLocation());
				}
			}
		}
	}
	public static void cureII(Player p, MagicUser user)
	{
		double x =RandInt.randInt(1,5);
		ParticleEffect clearPot = new ParticleEffect(ParticleType.CRIT_MAGIC, x/100, 15, 0.5);
		for(PotionEffectType pot : poisons)
		{
			if(p.hasPotionEffect(pot)){
				if(user.getMana() > 9.8){
					user.setMana(user.getMana() - 9.8);
					p.removePotionEffect(pot);
					clearPot.sendToLocation(p.getLocation());
				}
			}
		}
	}
	public static void cureAll(Player p, MagicUser user)
	{
		double x =RandInt.randInt(1,5);
		ParticleEffect clearPot = new ParticleEffect(ParticleType.CRIT_MAGIC, x/100, 15, 0.5);
		for(PotionEffectType pot : pots)
		{
			if(p.hasPotionEffect(pot)){
				if(user.getMana() > 12.4){
					user.setMana(user.getMana() - 12.4);
					p.removePotionEffect(pot);
					clearPot.sendToLocation(p.getLocation());
				}
			}
		}
	}
}

