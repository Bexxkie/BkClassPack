package com.bexxkie.bkcp.interaction.spells;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.classes.interfaces.MagicUser;
import com.bexxkie.bkcp.util.ParticleEffect;
import com.bexxkie.bkcp.util.ParticleEffect.ParticleType;

public class Recall 
{

	public static void save(Player p, MagicUser user)
	{
		if(((MagicUser)BkCP.onlinePlayers.get(p.getName())).getCool("save")==true){return;
		}else
			if (user.getMana() > 15.0)
			{
				user.setMana(user.getMana() - 7.5);
			}
		((MagicUser)BkCP.onlinePlayers.get(p.getName())).setCool("save", 20);
		ParticleEffect porterSet1 = new ParticleEffect(ParticleType.PORTAL, 2, 32, .1);
		ParticleEffect porterSet2 = new ParticleEffect(ParticleType.REDSTONE, 0, 16, .2);

		porterSet1.sendToLocation(p.getLocation());
		porterSet2.sendToLocation(p.getLocation());
		World world = p.getLocation().getWorld();
		double x = p.getLocation().getX();
		double y = p.getLocation().getY();
		double z = p.getLocation().getZ();
		BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".setRecall.world", world.getName());
		BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".setRecall.x", x);
		BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".setRecall.y", y);
		BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".setRecall.z", z);
		BkCP.playerClass.saveConfig();
		BkCP.playerClass.reloadConfig();

	}	

	public static void recallI(Player p, MagicUser user)
	{
		if(((MagicUser)BkCP.onlinePlayers.get(p.getName())).getCool("recalli")==true){return;
		}else
			if (user.getMana() > 15.0)
			{
				user.setMana(user.getMana() - 7.5);
			}
		((MagicUser)BkCP.onlinePlayers.get(p.getName())).setCool("recalli", 5);
		ParticleEffect porterCall1 = new ParticleEffect(ParticleType.PORTAL, 2, 32, .1);
		ParticleEffect porterCall2 = new ParticleEffect(ParticleType.SPELL_INSTANT, 0, 16, .2);

		if(BkCP.playerClass.getConfig().contains(p.getUniqueId().toString()+".setRecall"))
		{
			World world = Bukkit.getWorld(BkCP.playerClass.getConfig().getString(p.getUniqueId()+".setRecall.world"));
			double x = BkCP.playerClass.getConfig().getDouble(p.getUniqueId().toString()+".setRecall.x");
			double y = BkCP.playerClass.getConfig().getDouble(p.getUniqueId().toString()+".setRecall.y");
			double z = BkCP.playerClass.getConfig().getDouble(p.getUniqueId().toString()+".setRecall.z");
			Location loc = new Location(world,x,y,z);
			if(p.getLocation().getWorld()==world&&p.getLocation().distance(loc)<500){
				
				p.teleport(loc);
				porterCall1.sendToLocation(loc);
				porterCall2.sendToLocation(loc);
			}
		}
	}
	//TransDim
	public static void recallII(Player p, MagicUser user)
	{
		if(((MagicUser)BkCP.onlinePlayers.get(p.getName())).getCool("recallii")==true){return;
		}else
			if (user.getMana() > 31.0)
			{
				user.setMana(user.getMana() - 30.0);
			}
		((MagicUser)BkCP.onlinePlayers.get(p.getName())).setCool("recallii", 5);
		ParticleEffect porterCall1 = new ParticleEffect(ParticleType.PORTAL, 2, 32, .1);
		ParticleEffect porterCall2 = new ParticleEffect(ParticleType.SPELL_INSTANT, 0, 16, .2);

		if(BkCP.playerClass.getConfig().contains(p.getUniqueId().toString()+".setRecall"))
		{
			World world = Bukkit.getWorld(BkCP.playerClass.getConfig().getString(p.getUniqueId()+".setRecall.world"));
			double x = BkCP.playerClass.getConfig().getDouble(p.getUniqueId().toString()+".setRecall.x");
			double y = BkCP.playerClass.getConfig().getDouble(p.getUniqueId().toString()+".setRecall.y");
			double z = BkCP.playerClass.getConfig().getDouble(p.getUniqueId().toString()+".setRecall.z");
			Location loc = new Location(world,x,y,z);

			p.teleport(loc);
			porterCall1.sendToLocation(loc);
			porterCall2.sendToLocation(loc);
		}
	}
}
