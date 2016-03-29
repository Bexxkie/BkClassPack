package com.bexxkie.bkcp.interaction.spells;

import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.classes.interfaces.MagicUser;
import com.bexxkie.bkcp.interaction.PotionEffectModiy;
import com.bexxkie.bkcp.util.ParticleEffect;
import com.bexxkie.bkcp.util.RandInt;
import com.bexxkie.bkcp.util.ParticleEffect.ParticleType;

public class MiningBuff 
{

	public static void buff(Player p, MagicUser user)
	{
		if(user.getMana()>30.0)
		{
			user.setMana(user.getMana() - 30.0);	
			ParticleEffect buff = new ParticleEffect(ParticleType.SPELL_INSTANT, 100, 30, 0.5);
			BkCP.miningEffect.put(p.getName(), 0);
			PotionEffectModiy.setTimer(p, 7200);
			List<Entity> ent = p.getNearbyEntities(10, 5, 10);
			double amp =RandInt.randInt(1, 3);
			p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 7200, 3));
			buff.sendToLocation(p.getLocation());
			for(Entity e : ent)
			{
				if((e)instanceof Player)
				{
					Player pl = (Player)e;
					double dist = p.getLocation().distance(((Player)e).getLocation());
					pl.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, (int) (7200-dist), 3));
					e.getLocation().getWorld().playSound(e.getLocation(), Sound.BLOCK_ANVIL_BREAK, (float) amp, (float) amp);
					buff.sendToLocation(e.getLocation());
				}
			}
			
		}
	}
}
