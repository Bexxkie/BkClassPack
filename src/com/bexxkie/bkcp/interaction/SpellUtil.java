package com.bexxkie.bkcp.interaction;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.classes.interfaces.ClassBase;
import com.bexxkie.bkcp.classes.interfaces.MagicUser;
import com.bexxkie.bkcp.interaction.spells.Blink;
import com.bexxkie.bkcp.interaction.spells.Courage;
import com.bexxkie.bkcp.interaction.spells.CurePoison;
import com.bexxkie.bkcp.interaction.spells.Flame;
import com.bexxkie.bkcp.interaction.spells.Heal;
import com.bexxkie.bkcp.interaction.spells.Ice;
import com.bexxkie.bkcp.interaction.spells.LifeDrain;
import com.bexxkie.bkcp.interaction.spells.MiningBuff;
import com.bexxkie.bkcp.interaction.spells.Recall;
import com.bexxkie.bkcp.interaction.spells.Shadow;
import com.bexxkie.bkcp.interaction.spells.silly;



public class SpellUtil 
implements Listener
{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void cycle_use_Spell(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		if(BkCP.onlinePlayers.get(p.getName())instanceof MagicUser){
		if(p.getItemInHand().getType().equals(Material.STICK))
		{
			if(e.getAction() == (Action.LEFT_CLICK_AIR)||e.getAction() == (Action.LEFT_CLICK_BLOCK))
			{
		
				((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).cycleSpell();

			}else if(e.getAction() == (Action.RIGHT_CLICK_AIR)||e.getAction() == (Action.RIGHT_CLICK_BLOCK))
			{
				useSpell((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName()), ((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).currentSpell());
			}
		}
		}
	}
	//34 spells
	public static void useSpell(MagicUser user, String s)
	{
		if (user.hasSpell(s))
		{
			//Teleport spells
			if (s.equalsIgnoreCase("blink"))
			{
				Blink.spell(((ClassBase)user).getPlayer(),user);
			}
			if(s.equalsIgnoreCase("save"))
			{
				Recall.save(((ClassBase)user).getPlayer(),user);
			}
			if(s.equalsIgnoreCase("recallI"))
			{
				Recall.recallI(((ClassBase)user).getPlayer(),user);
			}
			if(s.equalsIgnoreCase("recallII"))
			{
				Recall.recallII(((ClassBase)user).getPlayer(),user);
			}
			//Fire spells
			if(s.equalsIgnoreCase("flameI"))
			{
				Flame.flameI(((ClassBase)user).getPlayer(),user);
			}
			if(s.equalsIgnoreCase("flameII"))
			{
				Flame.flameII(((ClassBase)user).getPlayer(),user);
			}
			if(s.equalsIgnoreCase("flameIII"))
			{
				Flame.flameIII(((ClassBase)user).getPlayer(),user);
			}
			if(s.equalsIgnoreCase("fireBlast"))
			{
				Flame.fireBlast(((ClassBase)user).getPlayer(),user);
			}
			if(s.equalsIgnoreCase("fireBall"))
			{
				Flame.fireBall(((ClassBase)user).getPlayer(),user);
			}
			if(s.equalsIgnoreCase("fireAura"))
			{
				Flame.fireAura(((ClassBase)user).getPlayer(),user);
			}
			//Healing spells
			if(s.equalsIgnoreCase("healI"))
			{
				Heal.healI(((ClassBase)user).getPlayer(),user);
			}
			if(s.equalsIgnoreCase("healII"))
			{
				Heal.healII(((ClassBase)user).getPlayer(),user);
			}
			if(s.equalsIgnoreCase("healIII"))
			{
				Heal.healIII(((ClassBase)user).getPlayer(),user);
			}
			if(s.equalsIgnoreCase("healIV"))
			{
				Heal.healIV(((ClassBase)user).getPlayer(),user);
			}
			if(s.equalsIgnoreCase("healTargetI"))
			{
				Heal.healOtherI(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("healTargetII"))
			{
				Heal.healOtherII(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("healAura"))
			{
				Heal.healAura(((ClassBase)user).getPlayer(), user);
			}
			//Buffs
			if(s.equalsIgnoreCase("courage"))
			{
				Courage.courage(((ClassBase)user).getPlayer(),user);
			}
			if(s.equalsIgnoreCase("curePoisonI"))
			{
				CurePoison.cureI(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("curePoisonII"))
			{
				CurePoison.cureII(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("cureAll"))
			{
				CurePoison.cureAll(((ClassBase)user).getPlayer(), user);
			}
			//Frost spells
			if(s.equalsIgnoreCase("frostI"))
			{
				Ice.FrostI(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("frostII"))
			{
				Ice.FrostII(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("frostIII"))
			{
				Ice.FrostIII(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("iceSpike"))
			{
				Ice.IceSpike(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("iceBlast"))
			{
				Ice.iceBlast(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("frostAura"))
			{
				Ice.frostAura(((ClassBase)user).getPlayer(), user);
			}
			//Shadow spells
			if(s.equalsIgnoreCase("shadowBeamI"))
			{
				Shadow.beamI(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("shadowBeamII"))
			{
				Shadow.beamII(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("shadowBeamIII"))
			{
				Shadow.beamIII(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("shadowBlast"))
			{
				Shadow.shadowBlast(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("shadowAura"))
			{
				Shadow.shadowAura(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("shadowBolt"))
			{
				Shadow.ShadowBolt(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("lifeDrainI"))
			{
				LifeDrain.drainI(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("lifeDrainII"))
			{
				LifeDrain.drainII(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("feedI"))
			{
				LifeDrain.feedI(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("feedII"))
			{
				LifeDrain.feedII(((ClassBase)user).getPlayer(), user);
			}
			//silly
			if(s.equalsIgnoreCase("throwCake"))
			{
				silly.cake(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("tntRain"))
			{
				silly.tntRain(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("creeperRain"))
			{
				silly.creeperRain(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("throwFish"))
			{
				silly.fish(((ClassBase)user).getPlayer(), user);
			}
			if(s.equalsIgnoreCase("potionRain"))
			{
				silly.potionRain(((ClassBase)user).getPlayer(), user);
			}
			//randomBuffs
			if(s.equalsIgnoreCase("miningBuff"))
			{
				MiningBuff.buff(((ClassBase)user).getPlayer(), user);
			}
			
		}
	}





}
