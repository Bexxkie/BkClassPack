package com.bexxkie.bkcp.interaction.spells;

import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.bexxkie.bkcp.classes.interfaces.MagicUser;
import com.bexxkie.bkcp.util.ParticleEffect;
import com.bexxkie.bkcp.util.RandInt;
import com.bexxkie.bkcp.util.ParticleEffect.ParticleType;

public class Courage 
{
	//AOE (10x5x10)
	public static void courage(Player p, MagicUser user)
	{
		if(user.getMana()>25.0){
			user.setMana(user.getMana() - 15.1);
			double x =RandInt.randInt(1,5);
			double amp =RandInt.randInt(1, 3);
			ParticleEffect buff = new ParticleEffect(ParticleType.SPELL_INSTANT, x/100, 30, 0.5);
			List<Entity> ent = p.getNearbyEntities(10, 5, 10);
			p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 0));
			buff.sendToLocation(p.getLocation());
			for(Entity e : ent)
			{
				if((e)instanceof Player)
				{
					Player pl = (Player)e;
					double dist = p.getLocation().distance(((Player)e).getLocation());
					pl.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, (int) (300-dist), 0));
					pl.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, (int) (300-dist), 0));
					e.getLocation().getWorld().playSound(e.getLocation(), Sound.BLOCK_ANVIL_BREAK, (float) amp, (float) amp);
					buff.sendToLocation(e.getLocation());
					
				}
			}
		}
	}
}