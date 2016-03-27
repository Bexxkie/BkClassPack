package com.bexxkie.bkcp.interaction;

import java.util.Collection;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.bexxkie.bkcp.modules.pvpControl.PvpControlMain;
import com.bexxkie.bkcp.util.RandInt;

public class ProjectileSpellDamage implements Listener
{
	@EventHandler
	public void spellProjectile(EntityDamageByEntityEvent e)
	{
		if(e.getDamager().getCustomName()!=null){
			if(e.getDamager().getCustomName().equalsIgnoreCase("spell.icespike"))
			{
				if(e.getEntity() instanceof Player)
				{
					Player player = (Player)e.getEntity();
					if(PvpControlMain.pvpList.containsKey(player)&&PvpControlMain.pvpList.get(player)==false)
					{

					}
					else
					{
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0));
						player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20, 5));
						player.damage(4);
						return;
					}
				}
				if(e.getEntity() instanceof LivingEntity&&!(e.getEntity() instanceof Player))
				{
					LivingEntity le = (LivingEntity) e.getEntity();
					le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0));
					le.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20, 5));
					le.damage(4);
					return;
				}
			}
		}
	}
	@EventHandler
	public void spellPotion(PotionSplashEvent e)
	{
		if(e.getPotion().getCustomName()!=null){
			if(e.getPotion().getCustomName().equalsIgnoreCase("spell.shadowbolt"))
			{
				Collection<LivingEntity> efe = e.getAffectedEntities();
				PotionEffectType[] pots = PotionEffectType.values();
				for(LivingEntity le : efe)
				{
					if(le instanceof Player)
					{
						if(PvpControlMain.pvpList.containsKey(le)&&PvpControlMain.pvpList.get(le)==false)
						{
						}
						else
						{
							PotionEffectType pot =pots[RandInt.randInt(0, pots.length-1)];
							if(pot!=null){
								le.addPotionEffect(new PotionEffect(pot, RandInt.randInt(100, 500), RandInt.randInt(0, 5)));
							}
						}
					}else{
						PotionEffectType pot =pots[RandInt.randInt(0, pots.length-1)];
						if(pot!=null){
							le.addPotionEffect(new PotionEffect(pot, RandInt.randInt(100, 500), RandInt.randInt(0, 5)));
						}
					}
				}
			}
			if(e.getPotion().getCustomName().equalsIgnoreCase("spell.creeperRain")||e.getPotion().getCustomName().equalsIgnoreCase("spell.tntRain"))
			{
				Collection<LivingEntity> efe = e.getAffectedEntities();
				PotionEffectType[] pots = PotionEffectType.values();
				for(LivingEntity le : efe)
				{
					if(le instanceof Player)
					{
						if(PvpControlMain.pvpList.containsKey(le)&&PvpControlMain.pvpList.get(le)==false)
						{
						}
						else
						{
							PotionEffectType pot =pots[RandInt.randInt(0, pots.length-1)];
							if(pot!=null){
								le.addPotionEffect(new PotionEffect(pot, RandInt.randInt(100, 500), RandInt.randInt(0, 5)));
							}
						}
					}else{
						PotionEffectType pot =pots[RandInt.randInt(0, pots.length-1)];
						if(pot!=null){
							le.addPotionEffect(new PotionEffect(pot, RandInt.randInt(100, 500), RandInt.randInt(0, 5)));
						}
					}
				}
			}
			if(e.getPotion().getCustomName().equalsIgnoreCase("spell.potionRain"))
			{
				Collection<LivingEntity> efe = e.getAffectedEntities();
				PotionEffectType[] pots = PotionEffectType.values();
				for(LivingEntity le : efe)
				{
					if(le instanceof Player)
					{
						if(PvpControlMain.pvpList.containsKey(le)&&PvpControlMain.pvpList.get(le)==false)
						{
						}
						else
						{
							PotionEffectType pot =pots[RandInt.randInt(0, pots.length-1)];
							if(pot!=null){
								le.addPotionEffect(new PotionEffect(pot, RandInt.randInt(100, 500), RandInt.randInt(0, 5)));
							}
						}
					}else{
						PotionEffectType pot =pots[RandInt.randInt(0, pots.length-1)];
						if(pot!=null){
							le.addPotionEffect(new PotionEffect(pot, RandInt.randInt(100, 500), RandInt.randInt(0, 5)));
						}
					}
				}
			}
		}
	}
}
