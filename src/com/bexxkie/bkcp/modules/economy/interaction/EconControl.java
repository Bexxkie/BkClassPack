package com.bexxkie.bkcp.modules.economy.interaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.modules.economy.interfaces.EconInterface;

public class EconControl 
implements Listener
{
	/*
	 * add
	 * remove
	 * trade
	 */
	@SuppressWarnings("deprecation")
	@EventHandler
	public void initTrade(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			if(e.getClickedBlock().getType()==Material.SIGN||e.getClickedBlock().getType()==Material.SIGN_POST||e.getClickedBlock().getType()==Material.WALL_SIGN)
			{
				ItemStack rp = new ItemStack(Material.BOOK);
				ItemMeta rm = rp.getItemMeta();
				rm.setDisplayName(ChatColor.DARK_PURPLE+"Tome of Arcane Knowlege");
				List<String> lores = new ArrayList<String>();
				lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"+1 int");
				rm.setLore(lores);
				rm.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
				rp.setItemMeta(rm);

				ItemStack prot = new ItemStack(Material.BOOK);
				ItemMeta b1m = prot.getItemMeta();
				b1m.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
				lores.clear();
				lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Protector");
				b1m.setLore(lores);
				b1m.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
				prot.setItemMeta(b1m);

				//---
				ItemStack corr = new ItemStack(Material.BOOK);
				ItemMeta alim = corr.getItemMeta();
				alim.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
				lores.clear();
				lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Corrupter");
				alim.setLore(lores);
				alim.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
				corr.setItemMeta(alim);
				//-	
				ItemStack sav = new ItemStack(Material.BOOK);
				ItemMeta alim1 = sav.getItemMeta();
				alim1.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
				lores.clear();
				lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Saviour");
				alim1.setLore(lores);
				alim1.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
				sav.setItemMeta(alim1);
				//--

				ItemStack dest = new ItemStack(Material.BOOK);
				ItemMeta b2m = dest.getItemMeta();
				b2m.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
				lores.clear();
				lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Destroyer");
				b2m.setLore(lores);
				b2m.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
				dest.setItemMeta(b2m);

				ItemStack heal = new ItemStack(Material.BOOK);
				ItemMeta b1am = heal.getItemMeta();
				b1am.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
				lores.clear();
				lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Healer");
				b1am.setLore(lores);
				b1am.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
				heal.setItemMeta(b1am);

				ItemStack ill = new ItemStack(Material.BOOK);
				ItemMeta b1bm = ill.getItemMeta();
				b1bm.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
				lores.clear();
				lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Illusionist");
				b1bm.setLore(lores);
				b1bm.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
				ill.setItemMeta(b1bm);

				ItemStack fire = new ItemStack(Material.BOOK);
				ItemMeta b2am = fire.getItemMeta();
				b2am.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
				lores.clear();
				lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Fire");
				b2am.setLore(lores);
				b2am.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
				fire.setItemMeta(b2am);

				ItemStack ice = new ItemStack(Material.BOOK);
				ItemMeta b2bm = ice.getItemMeta();
				b2bm.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
				lores.clear();
				lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Ice");
				b2bm.setLore(lores);
				b2bm.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
				ice.setItemMeta(b2bm);

				ItemStack shad = new ItemStack(Material.BOOK);
				ItemMeta b2cm = shad.getItemMeta();
				b2cm.setDisplayName(ChatColor.DARK_PURPLE+"Proficiency Tome");
				lores.clear();
				lores.add(ChatColor.AQUA+""+ChatColor.ITALIC+"Shadow");
				b2cm.setLore(lores);
				b2cm.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
				shad.setItemMeta(b2cm);
				ItemStack proTome[] = 
					{
						rp,prot,corr,sav,dest,heal,ill,fire,ice,shad
					};
				List<ItemStack> pTome = Arrays.asList(proTome);
				String buySell = ((Sign)e.getClickedBlock().getState()).getLine(0);
				String owner = ((Sign)e.getClickedBlock().getState()).getLine(1);
				String price = ((Sign)e.getClickedBlock().getState()).getLine(2);
				String itm = ((Sign)e.getClickedBlock().getState()).getLine(3);
				if(!buySell.isEmpty()&&!owner.isEmpty()&&!price.isEmpty()&&!itm.isEmpty()){
					Block clicked = e.getClickedBlock();
					Chest chest;
					String type = price.substring(0,price.indexOf(":"));
					int intPrice = Integer.parseInt(price.substring(price.indexOf(":")+1));
					ItemStack item = null;

					switch(type)
					{
					case "g":
						type="gold";
						break;
					case "G":
						type="gem";
						break;
					}
					switch(itm.toLowerCase())
					{
					case "rp":
						item = pTome.get(pTome.indexOf(rp));
						break;
					case "protector":
						item = pTome.get(pTome.indexOf(prot));
						break;
					case "corruptor":
						item = pTome.get(pTome.indexOf(corr));
						break;
					case "savior":
						item = pTome.get(pTome.indexOf(sav));
						break;
					case "destroyer":
						item = pTome.get(pTome.indexOf(dest));
						break;
					case "healer":
						item = pTome.get(pTome.indexOf(heal));
						break;
					case "illusionist":
						item = pTome.get(pTome.indexOf(ill));
						break;
					case "fire":
						item = pTome.get(pTome.indexOf(fire));
						break;
					case "ice":
						item = pTome.get(pTome.indexOf(ice));
						break;
					case "shadow":
						item = pTome.get(pTome.indexOf(shad));
						break;
					default:
						break;
					}
					if(item!=null){
						if(clicked.getRelative(BlockFace.DOWN).getType().equals(Material.CHEST))
						{
							chest = (Chest) clicked.getRelative(BlockFace.DOWN).getState();
							switch(buySell.toLowerCase())
							{
							case "sell":
								if(trade(p,Bukkit.getPlayer(owner), type, intPrice, buySell))
								{
									if(chest.getInventory().containsAtLeast(item,1))
									{
										p.getInventory().addItem(item);
										chest.getInventory().removeItem(item);
									}
								}
								break;
							case "buy":
								if(p.getInventory().containsAtLeast(item, 1))
								{
									if(trade(Bukkit.getPlayer(owner),p, type, intPrice, buySell))
									{
										p.getInventory().removeItem(item);
										chest.getInventory().addItem(item);
									}
								}
								break;
							}
						}
						if(clicked.getRelative(BlockFace.UP).getType().equals(Material.CHEST))
						{
							chest = (Chest) clicked.getRelative(BlockFace.UP).getState();
							switch(buySell.toLowerCase())
							{
							case "sell":
								if(trade(p,Bukkit.getPlayer(owner), type, intPrice, buySell))
								{
									if(chest.getInventory().containsAtLeast(item,1))
									{
										p.getInventory().addItem(item);
										chest.getInventory().removeItem(item);
									}
								}
								break;
							case "buy":
								if(p.getInventory().containsAtLeast(item, 1))
								{
									if(trade(Bukkit.getPlayer(owner),p, type, intPrice, buySell))
									{
										p.getInventory().removeItem(item);
										chest.getInventory().addItem(item);
									}
								}
								break;
							}

						}
						if(clicked.getRelative(BlockFace.NORTH).getType().equals(Material.CHEST))
						{
							chest = (Chest) clicked.getRelative(BlockFace.NORTH).getState();
							switch(buySell.toLowerCase())
							{
							case "sell":
								if(trade(p,Bukkit.getPlayer(owner), type, intPrice, buySell))
								{
									if(chest.getInventory().containsAtLeast(item,1))
									{
										p.getInventory().addItem(item);
										chest.getInventory().removeItem(item);
									}
								}
								break;
							case "buy":
								if(p.getInventory().containsAtLeast(item, 1))
								{
									if(trade(Bukkit.getPlayer(owner),p, type, intPrice, buySell))
									{
										p.getInventory().removeItem(item);
										chest.getInventory().addItem(item);
									}
								}
								break;
							}
						}
						if(clicked.getRelative(BlockFace.EAST).getType().equals(Material.CHEST))
						{
							chest = (Chest) clicked.getRelative(BlockFace.EAST).getState();
							switch(buySell.toLowerCase())
							{
							case "sell":
								if(trade(p,Bukkit.getPlayer(owner), type, intPrice, buySell))
								{
									if(chest.getInventory().containsAtLeast(item,1))
									{
										p.getInventory().addItem(item);
										chest.getInventory().removeItem(item);
									}
								}
								break;
							case "buy":
								if(p.getInventory().containsAtLeast(item, 1))
								{
									if(trade(Bukkit.getPlayer(owner),p, type, intPrice, buySell))
									{
										p.getInventory().removeItem(item);
										chest.getInventory().addItem(item);
									}
								}
								break;
							}
						}
						if(clicked.getRelative(BlockFace.SOUTH).getType().equals(Material.CHEST))
						{
							chest = (Chest) clicked.getRelative(BlockFace.SOUTH).getState();
							switch(buySell.toLowerCase())
							{
							case "sell":
								if(trade(p,Bukkit.getPlayer(owner), type, intPrice, buySell))
								{
									if(chest.getInventory().containsAtLeast(item,1))
									{
										p.getInventory().addItem(item);
										chest.getInventory().removeItem(item);
									}
								}
								break;
							case "buy":
								if(p.getInventory().containsAtLeast(item, 1))
								{
									if(trade(Bukkit.getPlayer(owner),p, type, intPrice, buySell))
									{
										p.getInventory().removeItem(item);
										chest.getInventory().addItem(item);
									}
								}
								break;
							}
						}
						if(clicked.getRelative(BlockFace.WEST).getType().equals(Material.CHEST))
						{
							chest = (Chest) clicked.getRelative(BlockFace.WEST).getState();
							switch(buySell.toLowerCase())
							{
							case "sell":
								if(trade(p,Bukkit.getPlayer(owner), type, intPrice, buySell))
								{
									if(chest.getInventory().containsAtLeast(item,1))
									{
										p.getInventory().addItem(item);
										chest.getInventory().removeItem(item);
									}
								}
								break;
							case "buy":
								if(p.getInventory().containsAtLeast(item, 1))
								{
									if(trade(Bukkit.getPlayer(owner),p, type, intPrice, buySell))
									{
										p.getInventory().removeItem(item);
										chest.getInventory().addItem(item);
									}
								}
								break;
							}
						}
					}
				}
			}
		}
	}

	public boolean trade(Player seller, Player buyer, String type, int price,String buySell)
	{
		switch(buySell.toLowerCase())
		{
		case "buy":
			if(((EconInterface)BkCP.onlinePlayers.get(seller.getName())).getCur(type)>=price)
			{
				if(seller==buyer)
				{
					seller.sendMessage(BkCP.prefix+"You sold yourself an item...");
					return true;
				}else{
					buyer.sendMessage(BkCP.prefix+ChatColor.GREEN+"+ "+type+":"+price);
					seller.sendMessage(BkCP.prefix+ChatColor.RED+"- "+type+":"+price);
					add(buyer,type, price);
					remove(seller,type, price);	
					return true;
				}
			}
			else
			{
				buyer.sendMessage(BkCP.prefix+seller.getName()+" cannot afford " + type+"::"+price);
				return false;
			}
		case "sell":
			if(((EconInterface)BkCP.onlinePlayers.get(seller.getName())).getCur(type)>=price)
			{
				if(seller==buyer)
				{
					buyer.sendMessage(BkCP.prefix+"You sold yourself an item...");
					return true;
				}else{
					buyer.sendMessage(BkCP.prefix+ChatColor.GREEN+"+ "+type+":"+price);
					seller.sendMessage(BkCP.prefix+ChatColor.RED+"- "+type+":"+price);
					add(buyer,type, price);
					remove(seller,type, price);	
					return true;
				}
			}
			else
			{
				seller.sendMessage(BkCP.prefix+"You cannot afford " + type+"::"+price);
				return false;
			}
			default:
			return false;
		}

	}
	public void add(Player p, String type, int amount)
	{
		((EconInterface)BkCP.onlinePlayers.get(p.getName())).setCur(type, ((EconInterface)BkCP.onlinePlayers.get(p.getName())).getCur(type)+amount);
	}
	public void remove(Player p, String type, int amount)
	{
		((EconInterface)BkCP.onlinePlayers.get(p.getName())).setCur(type, ((EconInterface)BkCP.onlinePlayers.get(p.getName())).getCur(type)-amount);
	}


}
