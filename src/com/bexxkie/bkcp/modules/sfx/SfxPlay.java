package com.bexxkie.bkcp.modules.sfx;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.util.ParticleEffect;
import com.bexxkie.bkcp.util.ParticleEffect.ParticleType;

public class SfxPlay
implements Listener
{
	public Map<Player, Boolean> isRunning = new HashMap<Player, Boolean>();
	public Map<Player, ParticleEffect> playerEffects = new HashMap<Player, ParticleEffect>();
	public static int maxCount;
	public static double maxSpeed;
	public static double maxRadius;
	public static int emitRate;
	
	public void startEffect()
	{
		//While true keepAlive
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BkCP.getInstance(), new Runnable()
		{
			public void run()
			{
				for(Player p :Bukkit.getOnlinePlayers())
				{
					if(playerEffects.containsKey(p)&&isRunning.containsKey(p))
					{
						if(isRunning.get(p).equals(true))
						{
							ParticleEffect sendToPlayer = playerEffects.get(p);
							sendToPlayer.sendToLocation(p.getLocation().add(0,.5,0));
						}
					}
				}
				startEffect();
			}
		},emitRate);
	}
	public void addToMap(Player p, ParticleEffect eff)
	{
		playerEffects.put(p, eff);
	}
	public void addToRun(Player p, Boolean bool)
	{
		isRunning.put(p,bool);
	}
	
	public void removePlayer(Player p)
	{
		playerEffects.remove(p);
		isRunning.remove(p);
	}
	public void buildParticle(Player p, String pName,double speed, int count, double radius, boolean run)
	{
		//System.out.print(ParticleType.valueOf(pName));
		if(ParticleType.valueOf(pName)!=null)
		{
			if(speed>maxSpeed)
			{speed = 0.5;}
			if(count<=0||count>maxCount)
			{count = maxCount;}
			if(radius<=0||radius>=maxRadius)
			{radius = 0.5;}
			ParticleEffect toSend = new ParticleEffect(ParticleType.valueOf(pName.toUpperCase()), speed, count, radius);
			addToMap(p,toSend);
			addToRun(p,run);
		}
	}
}
