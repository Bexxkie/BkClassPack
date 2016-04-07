package com.bexxkie.bkcp.interaction;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.classes.Alicorn;
import com.bexxkie.bkcp.classes.Changeling;
import com.bexxkie.bkcp.classes.Earth;
import com.bexxkie.bkcp.classes.Unicorn;
import com.bexxkie.bkcp.classes.interfaces.EnergyUser;
import com.bexxkie.bkcp.classes.interfaces.MagicUser;
import com.bexxkie.bkcp.util.ParticleEffect;
import com.bexxkie.bkcp.util.RandInt;
import com.bexxkie.bkcp.util.ParticleEffect.ParticleType;


public class Research implements Listener
{
	/**
	 * BranchSelection, levelUp, spell[rem/add]
	 */
	@SuppressWarnings("deprecation")
	@EventHandler
	public void levelUp(PlayerInteractEvent e)
	{
		//Unicorn_Alicorn
		if(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof MagicUser){
			ItemStack rp = new ItemStack(Material.BOOK);
			ItemMeta rm = rp.getItemMeta();
			rm.setDisplayName(ChatColor.DARK_PURPLE+"Tome of Arcane Knowlege");
			List<String> lores = new ArrayList<String>();
			lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"+1 int");
			rm.setLore(lores);
			rm.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			rp.setItemMeta(rm);

			ItemStack b1 = new ItemStack(Material.BOOK);
			ItemMeta b1m = b1.getItemMeta();
			b1m.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
			lores.clear();
			lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Protector");
			b1m.setLore(lores);
			b1m.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			b1.setItemMeta(b1m);

			//---
			ItemStack ali = new ItemStack(Material.BOOK);
			ItemMeta alim = ali.getItemMeta();
			alim.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
			lores.clear();
			lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Corrupter");
			alim.setLore(lores);
			alim.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			ali.setItemMeta(alim);
			//-	
			ItemStack ali1 = new ItemStack(Material.BOOK);
			ItemMeta alim1 = ali1.getItemMeta();
			alim1.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
			lores.clear();
			lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Saviour");
			alim1.setLore(lores);
			alim1.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			ali1.setItemMeta(alim1);
			//--

			ItemStack b2 = new ItemStack(Material.BOOK);
			ItemMeta b2m = b2.getItemMeta();
			b2m.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
			lores.clear();
			lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Destroyer");
			b2m.setLore(lores);
			b2m.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			b2.setItemMeta(b2m);

			ItemStack b1a = new ItemStack(Material.BOOK);
			ItemMeta b1am = b1a.getItemMeta();
			b1am.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
			lores.clear();
			lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Healer");
			b1am.setLore(lores);
			b1am.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			b1a.setItemMeta(b1am);

			ItemStack b1b = new ItemStack(Material.BOOK);
			ItemMeta b1bm = b1b.getItemMeta();
			b1bm.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
			lores.clear();
			lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Illusionist");
			b1bm.setLore(lores);
			b1bm.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			b1b.setItemMeta(b1bm);

			ItemStack b2a = new ItemStack(Material.BOOK);
			ItemMeta b2am = b2a.getItemMeta();
			b2am.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
			lores.clear();
			lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Fire");
			b2am.setLore(lores);
			b2am.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			b2a.setItemMeta(b2am);

			ItemStack b2b = new ItemStack(Material.BOOK);
			ItemMeta b2bm = b2b.getItemMeta();
			b2bm.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
			lores.clear();
			lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Ice");
			b2bm.setLore(lores);
			b2bm.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			b2b.setItemMeta(b2bm);

			ItemStack b2c = new ItemStack(Material.BOOK);
			ItemMeta b2cm = b2c.getItemMeta();
			b2cm.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
			lores.clear();
			lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Shadow");
			b2cm.setLore(lores);
			b2cm.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			b2c.setItemMeta(b2cm);
			ItemStack ns = new ItemStack(Material.NETHER_STAR);

			Player p = e.getPlayer();
			if(e.getAction()==(Action.RIGHT_CLICK_BLOCK))
			{
				if(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof Unicorn&&p.getLevel()>=30&&p.getItemInHand().getType().equals(Material.BOOK)&&p.getItemInHand().getAmount()==1&&e.getClickedBlock().getType().equals(Material.DIAMOND_BLOCK)&&p.getInventory().containsAtLeast(ns,3))
				{
					ns.setAmount(3);
					//p.setLevel(p.getLevel()-10);
					if(p.getItemInHand().hasItemMeta()&&p.getItemInHand().getItemMeta().hasDisplayName()){
						switch(p.getItemInHand().getItemMeta().getDisplayName().toLowerCase())
						{
						case "br: protector":
							p.setLevel(p.getLevel()-30);
							p.getInventory().removeItem(ns);
							p.getInventory().removeItem(p.getItemInHand());
							p.getInventory().addItem(b1);
							break;
						case "br: healer":
							p.setLevel(p.getLevel()-30);
							p.getInventory().removeItem(ns);
							p.getInventory().removeItem(p.getItemInHand());
							p.getInventory().addItem(b1a);
							break;
						case "br: illusionist":
							p.setLevel(p.getLevel()-30);
							p.getInventory().removeItem(ns);
							p.getInventory().removeItem(p.getItemInHand());
							p.getInventory().addItem(b1b);
							break;
						case "br: destruction":
							p.setLevel(p.getLevel()-30);
							p.getInventory().removeItem(ns);
							p.getInventory().removeItem(p.getItemInHand());
							p.getInventory().addItem(b2);
							break;
						case "br: fire":
							p.setLevel(p.getLevel()-30);
							p.getInventory().removeItem(ns);
							p.getInventory().removeItem(p.getItemInHand());
							p.getInventory().addItem(b2a);
							break;
						case "br: ice":
							p.setLevel(p.getLevel()-30);
							p.getInventory().removeItem(ns);
							p.getInventory().removeItem(p.getItemInHand());
							p.getInventory().addItem(b2b);
							break;
						case "br: shadow":
							p.setLevel(p.getLevel()-30);
							p.getInventory().removeItem(ns);
							p.getInventory().removeItem(p.getItemInHand());
							p.getInventory().addItem(b2c);
							break;
						default:
							break;
						}

					}else
					{
						p.setLevel(p.getLevel()-30);
						p.getInventory().removeItem(p.getItemInHand());
						p.getInventory().remove(ns);
						p.getInventory().addItem(rp);
					}
				}
				if(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof Alicorn&&p.getLevel()>=30&&p.getItemInHand().getType().equals(Material.BOOK)&&p.getItemInHand().getAmount()==1&&e.getClickedBlock().getType().equals(Material.DIAMOND_BLOCK)&&p.getInventory().containsAtLeast(ns,3))
				{
					ns.setAmount(3);
					//p.setLevel(p.getLevel()-10);
					if(p.getItemInHand().hasItemMeta()&&p.getItemInHand().getItemMeta().hasDisplayName()){
						switch(p.getItemInHand().getItemMeta().getDisplayName().toLowerCase())
						{
						case "br: corrupter":
							p.setLevel(p.getLevel()-30);
							p.getInventory().removeItem(ns);
							p.getInventory().removeItem(p.getItemInHand());
							p.getInventory().addItem(ali);
							break;
						case "br: saviour":
							p.setLevel(p.getLevel()-30);
							p.getInventory().removeItem(ns);
							p.getInventory().removeItem(p.getItemInHand());
							p.getInventory().addItem(ali1);
							break;
						}
					}else
					{
						p.setLevel(p.getLevel()-30);
						p.getInventory().removeItem(p.getItemInHand());
						p.getInventory().remove(ns);
						p.getInventory().addItem(rp);
					}
				}
				if(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof Changeling&&p.getLevel()>=30&&p.getItemInHand().getType().equals(Material.BOOK)&&p.getItemInHand().getAmount()==1&&e.getClickedBlock().getType().equals(Material.DIAMOND_BLOCK)&&p.getInventory().containsAtLeast(ns,3))
				{
					ns.setAmount(3);
					p.setLevel(p.getLevel()-30);
					p.getInventory().removeItem(p.getItemInHand());
					p.getInventory().remove(ns);
					p.getInventory().addItem(rp);
				}
				//Rem_AddSpells
				if(p.getItemInHand().getType().equals(Material.BOOK)&&p.getItemInHand().getAmount()==1&&e.getClickedBlock().getType().equals(Material.ENCHANTMENT_TABLE))
				{
					if(p.getItemInHand().hasItemMeta()&&p.getItemInHand().getItemMeta().hasDisplayName()){
						if(((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).getBranch().equalsIgnoreCase("custom")){
							//create particles to use
							ParticleEffect add1 = new ParticleEffect(ParticleType.ENCHANTMENT_TABLE, RandInt.randDouble(0.1, 1.0), RandInt.randInt(100, 300), RandInt.randDouble(0.5, 1.0));
							ParticleEffect add2 = new ParticleEffect(ParticleType.PORTAL, RandInt.randDouble(0.1, 0.2), RandInt.randInt(5, 200), RandInt.randDouble(0.5, 1.0));
							ParticleEffect rem1 = new ParticleEffect(ParticleType.CRIT, RandInt.randDouble(0.1, 0.2), RandInt.randInt(50, 100), RandInt.randDouble(0.5, 1.0));
							ParticleEffect rem2 = new ParticleEffect(ParticleType.SMOKE_NORMAL, RandInt.randDouble(0.1, 0.2), RandInt.randInt(50, 100), RandInt.randDouble(0.5, 1.0));

							ParticleEffect fail = new ParticleEffect(ParticleType.VILLAGER_ANGRY, RandInt.randDouble(0.1, 0.2), RandInt.randInt(10, 25), RandInt.randDouble(0.1, 1.0));
							if(p.getItemInHand().getItemMeta().getDisplayName().toUpperCase().startsWith("RE_"))
							{
								//TODO remove spell method
								String spellName = p.getItemInHand().getItemMeta().getDisplayName().substring(3);
								if(!BkCP.SpellList.contains(spellName))
								{
									e.getPlayer().sendMessage(BkCP.prefix+spellName+" is not a spell");
									fail.sendToLocation(e.getPlayer().getLocation());
									return;
								}
								if(!((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).hasSpell(spellName))
								{
									e.getPlayer().sendMessage(BkCP.prefix+"You do not know "+spellName);
									fail.sendToLocation(e.getPlayer().getLocation());
									return;
								}

								if(spellName.equalsIgnoreCase("recallI")||spellName.equalsIgnoreCase("recallII"))
								{
									String format = spellName.toLowerCase().replaceAll("i", "I");
									((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).remSpell(format);
									((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).remSpell("save");
									e.getPlayer().sendMessage(BkCP.prefix+"Forgot "+spellName);
									rem1.sendToLocation(e.getPlayer().getEyeLocation());
									rem2.sendToLocation(e.getPlayer().getEyeLocation());
									e.getPlayer().getLocation().getWorld().playSound(e.getPlayer().getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, RandInt.randFloat(0,10), RandInt.randFloat(0,10));
									return;
								}
								for(String lookup: BkCP.SpellList)
								{
									if(lookup.equalsIgnoreCase(spellName))
									{
										((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).remSpell(lookup);
										e.getPlayer().sendMessage(BkCP.prefix+"Forgot "+spellName);
										rem1.sendToLocation(e.getPlayer().getEyeLocation());
										rem2.sendToLocation(e.getPlayer().getEyeLocation());
										e.getPlayer().getLocation().getWorld().playSound(e.getPlayer().getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, RandInt.randFloat(0,10), RandInt.randFloat(0,10));
										return;
									}
								}
							}
							if(p.getItemInHand().getItemMeta().getDisplayName().toUpperCase().startsWith("AD_"))
							{
								//TODO add spell method
								String spellName = p.getItemInHand().getItemMeta().getDisplayName().substring(3);
								if(!BkCP.SpellList.contains(spellName))
								{
									e.getPlayer().sendMessage(BkCP.prefix+spellName+" is not a spell");
									fail.sendToLocation(e.getPlayer().getLocation());
									return;
								}
								if(((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).hasSpell(spellName))
								{
									e.getPlayer().sendMessage(BkCP.prefix+"You already know "+spellName);
									fail.sendToLocation(e.getPlayer().getLocation());
									return;
								}
								if(spellName.equalsIgnoreCase("recallI")||spellName.equalsIgnoreCase("recallII"))
								{
									String format = spellName.toLowerCase().replaceAll("i", "I");
									((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).addSpell(format);
									((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).addSpell("save");
									e.getPlayer().sendMessage(BkCP.prefix+"Learned "+spellName);
									add1.sendToLocation(e.getPlayer().getEyeLocation());
									add2.sendToLocation(e.getPlayer().getEyeLocation());
									e.getPlayer().getLocation().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, RandInt.randFloat(0,10), RandInt.randFloat(0,10));
									return;
								}
								for(String lookup: BkCP.SpellList)
								{
									if(lookup.equalsIgnoreCase(spellName))
									{
										((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).addSpell(lookup);
										e.getPlayer().sendMessage(BkCP.prefix+"Learned "+spellName);
										add1.sendToLocation(e.getPlayer().getEyeLocation());
										add2.sendToLocation(e.getPlayer().getEyeLocation());
										e.getPlayer().getLocation().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, RandInt.randFloat(0,10), RandInt.randFloat(0,10));
										return;
									}
								}

							}
						}
						else{
							e.getPlayer().sendMessage(BkCP.prefix+((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).getBranch()+" cannot learn new spells.");
						}
					}
				}
			}
			if(p.getItemInHand().hasItemMeta()&&e.getAction()==(Action.RIGHT_CLICK_AIR))
			{
				if(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof Unicorn){
					if(p.getItemInHand().equals(rp))
					{
						p.getInventory().removeItem(rp);
						((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setLevel();
						return;
					}
					if(p.getItemInHand().equals(b1))
					{
						p.getInventory().removeItem(b1);
						((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setBranch("protector");
						return;
					}
					if(p.getItemInHand().equals(b1a))
					{
						p.getInventory().removeItem(b1a);
						((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setBranch("healer");
						return;
					}
					if(p.getItemInHand().equals(b1b))
					{
						p.getInventory().removeItem(b1b);
						((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setBranch("illusionist");
						return;
					}
					if(p.getItemInHand().equals(b2))
					{
						p.getInventory().removeItem(b2);
						((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setBranch("destruction");
						return;
					}
					if(p.getItemInHand().equals(b2a))
					{
						p.getInventory().removeItem(b2a);
						((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setBranch("fire");
						return;
					}
					if(p.getItemInHand().equals(b2b))
					{
						p.getInventory().removeItem(b2b);
						((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setBranch("ice");
						return;
					}
					if(p.getItemInHand().equals(b2c))
					{
						p.getInventory().removeItem(b2c);
						((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setBranch("shadow");
						return;
					}
				}
				if(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof Alicorn){
					if(p.getItemInHand().equals(rp))
					{
						p.getInventory().removeItem(rp);
						((Alicorn)BkCP.onlinePlayers.get(e.getPlayer().getName())).setMagicLevel();
						return;
					}
					if(p.getItemInHand().equals(ali))
					{
						p.getInventory().removeItem(ali);
						((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setBranch("corrupter");
						return;
					}
					if(p.getItemInHand().equals(ali1))
					{
						p.getInventory().removeItem(ali1);
						((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setBranch("saviour");
						return;
					}
				}
				if(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof Changeling){
					if(p.getItemInHand().equals(rp))
					{
						p.getInventory().removeItem(rp);
						((Changeling)BkCP.onlinePlayers.get(e.getPlayer().getName())).setMagicLevel();
						return;
					}
				}
				if(p.getItemInHand().getItemMeta().hasDisplayName()&&p.getItemInHand().getItemMeta().getDisplayName().startsWith("cubr: "))
				{
					//Format custom branch name like so <cubr: &[0123456789abdef]_bName>
					//Convert to <§[0123456789abcdef]bName>
					//<.branch: custom>
					//<.branchCustom.color: §[0123456789abcdef]>
					//<.branchCustom.name: bName>
					String rawData = p.getItemInHand().getItemMeta().getDisplayName().substring(6);
					String colourData = rawData.substring(0,rawData.indexOf("_"));
					String nameData = rawData.substring(rawData.indexOf("_")+1);
					if(p.getInventory().getItemInHand().getAmount()>1)
					{
						p.getInventory().getItemInHand().setAmount(p.getItemInHand().getAmount()-1);
					}else{
						p.getInventory().removeItem(p.getItemInHand());
					}
					BkCP.playerClass.getConfig().set(e.getPlayer().getUniqueId()+".branchCustom.color",colourData);
					BkCP.playerClass.getConfig().set(e.getPlayer().getUniqueId()+".branchCustom.name",nameData);
					BkCP.playerClass.getConfig().set(e.getPlayer().getUniqueId()+".branch","custom");
					((MagicUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setBranch("custom");
					BkCP.playerClass.saveConfig();
					BkCP.playerClass.reloadConfig();

					return;
				}
			}
		}
		//Earth
		if(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof Earth)
		{
			ItemStack rp = new ItemStack(Material.BOOK);
			ItemMeta rm = rp.getItemMeta();
			rm.setDisplayName(ChatColor.DARK_GREEN+"Tome of Strength");
			List<String> lores = new ArrayList<String>();
			lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"+1 str");
			rm.setLore(lores);
			rm.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			rp.setItemMeta(rm);

			ItemStack b1 = new ItemStack(Material.BOOK);
			ItemMeta b1m = b1.getItemMeta();
			b1m.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
			lores.clear();
			lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Miner");
			b1m.setLore(lores);
			b1m.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			b1.setItemMeta(b1m);

			ItemStack b2 = new ItemStack(Material.BOOK);
			ItemMeta b2m = b2.getItemMeta();
			b2m.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
			lores.clear();
			lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Farmer");
			b2m.setLore(lores);
			b2m.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			b2.setItemMeta(b2m);

			ItemStack b3 = new ItemStack(Material.BOOK);
			ItemMeta b3m = b3.getItemMeta();
			b3m.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
			lores.clear();
			lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Breeder");
			b3m.setLore(lores);
			b3m.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
			b3.setItemMeta(b3m);

			Player p = e.getPlayer();
			if(e.getAction()==(Action.RIGHT_CLICK_BLOCK))
			{
				if(p.getLevel()>=30&&p.getItemInHand().getType().equals(Material.BOOK)&&p.getItemInHand().getAmount()==1&&e.getClickedBlock().getType().equals(Material.DIAMOND_BLOCK))
				{
					if(p.getItemInHand().hasItemMeta()&&p.getItemInHand().getItemMeta().hasDisplayName())
					{
						switch(p.getItemInHand().getItemMeta().getDisplayName().toLowerCase())
						{
						case "br_miner":
							p.getInventory().removeItem(p.getItemInHand());
							p.getInventory().addItem(b1);
							break;
						case "br_farmer":
							p.getInventory().removeItem(p.getItemInHand());
							p.getInventory().addItem(b2);
							break;
						case "br_breeder":
							p.getInventory().removeItem(p.getItemInHand());
							p.getInventory().addItem(b3);
							break;
						default:
							break;
						}
					}
					else
					{
						p.getInventory().removeItem(p.getItemInHand());
						p.getInventory().addItem(rp);
					}
				}
			}
			if(p.getItemInHand().hasItemMeta()&&e.getAction()==(Action.RIGHT_CLICK_AIR))
			{
				if(p.getItemInHand().equals(rp))
				{
					p.getInventory().removeItem(rp);
					((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setLevel(((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).getLevel()+1);
					return;
				}
				if(p.getItemInHand().equals(b1))
				{
					p.getInventory().removeItem(b1);
					((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setBranch("miner");
					return;
				}
				if(p.getItemInHand().equals(b2))
				{
					p.getInventory().removeItem(b2);
					((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setBranch("farmer");
					return;
				}
				if(p.getItemInHand().equals(b3))
				{
					p.getInventory().removeItem(b3);
					((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setBranch("breeder");
					return;
				}

			}
		}
	}
}
