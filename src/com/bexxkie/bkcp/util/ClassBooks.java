package com.bexxkie.bkcp.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import com.bexxkie.bkcp.BkCP;

public class ClassBooks 
{
	/**
	 * Gives a player a book detailing their class's abilities
	 * @param p Player
	 * @param className String
	 */
	public static void classBooks(Player p, String className)
	{
		if(className==null)
		{
			return;
		}
		int fCost = BkCP.advCfg.getConfig().getInt("flightCost");
		int fTime = BkCP.advCfg.getConfig().getInt("flightTime");
		switch(className.toUpperCase())
		{
		case "UNICORN":
			//Unicorn
			ItemStack uniBook = new ItemStack(Material.WRITTEN_BOOK);
			BookMeta unibm = (BookMeta) uniBook.getItemMeta();
			unibm.setAuthor(ChatColor.DARK_PURPLE+"BKCP");
			unibm.setTitle("Unicorn");
			ArrayList<String> UNpages = new ArrayList<String>();
			UNpages.add(ChatColor.DARK_PURPLE+ "Spells " +ChatColor.BLUE+"\n left click a stick to choose a spell. \n right click a stick to cast a spell.");
			UNpages.add(ChatColor.DARK_PURPLE+ "Affinity tomes "+ChatColor.BLUE+"\n with 30 levels and 3 nether stars shift click a book* onto a diamond block. \n Book formatting: br: <formattingName>");
			UNpages.add(ChatColor.DARK_PURPLE+ "Formatting names "+ChatColor.BLUE+"\n protector - healer - illusionist \n destruction - fire - ice - shadow");
			UNpages.add(ChatColor.DARK_PURPLE+ "Proficiency "+ChatColor.BLUE+"\n Reading an affinity tome changes your spell loadout.");
			UNpages.add(ChatColor.DARK_PURPLE+ "Tome of Arcane Knowlege[1|2] "+ChatColor.BLUE+"\n Shift right click unnamed book onto Diamond block with 30 levels and 3 nether stars");
			UNpages.add(ChatColor.DARK_PURPLE+ "Tome of Arcane Knowlege[2|2] "+ChatColor.BLUE+"\n Increase maximum mana and regen.");
			unibm.setPages(UNpages);
			uniBook.setItemMeta(unibm);
			uniBook.setAmount(1);
			//p.getInventory().addItem(uniBook);
			p.getLocation().getWorld().dropItem(p.getLocation(), uniBook);
			BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".book",true);
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			break;
		case "PEGASUS":
			//Unicorn
			ItemStack pegBook = new ItemStack(Material.WRITTEN_BOOK);
			BookMeta pegbm = (BookMeta) pegBook.getItemMeta();
			pegbm.setAuthor(ChatColor.DARK_PURPLE+"BKCP");
			pegbm.setTitle("Pegasus");
			ArrayList<String> pegPages = new ArrayList<String>();
			pegPages.add(ChatColor.DARK_PURPLE+ "Flight "+ChatColor.BLUE+"\n right click a feather to flap. \n Hold right click a feather to fly. \n Named feathers have different effects.");
			pegPages.add(ChatColor.DARK_PURPLE+ "Advanced flight[1/2] "+ChatColor.BLUE+"\n Feather named trainer \n shift right click on the ground to increase maximum energy and regen.");
			pegPages.add(ChatColor.DARK_PURPLE+ "Advanced flight[2/2] "+ChatColor.BLUE+"\n Feather named wings \n with "+fCost +" levels right click to gain creative mode flight for "+fTime+" seconds.");
			pegbm.setPages(pegPages);
			pegBook.setItemMeta(pegbm);
			pegBook.setAmount(1);
			//p.getInventory().addItem(pegBook);
			p.getLocation().getWorld().dropItem(p.getLocation(), pegBook);
			BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".book",true);
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			break;
		case "EARTH":
			//Unicorn
			ItemStack eaBook = new ItemStack(Material.WRITTEN_BOOK);
			BookMeta eabm = (BookMeta) eaBook.getItemMeta();
			eabm.setAuthor(ChatColor.DARK_PURPLE+"BKCP");
			eabm.setTitle("Earth");
			ArrayList<String> eaPages = new ArrayList<String>();
			eaPages.add(ChatColor.DARK_PURPLE+ "Passive strength "+ChatColor.BLUE+"\n Area of effect strikes \n Increased damage \n increased health \n Increased mob dropRate");
			eaPages.add(ChatColor.DARK_PURPLE+ "Affinity tomes "+ChatColor.BLUE+"\n with 30 levels shift click a book* onto a diamond block. \n Book formatting: br_<formattingName>");
			eaPages.add(ChatColor.DARK_PURPLE+ "Formatting names "+ChatColor.BLUE+"\n miner - farmer - breeder");
			eaPages.add(ChatColor.DARK_PURPLE+ "Proficiency "+ChatColor.BLUE+"\n Reading an affinity tome grants different abilities.");
			eaPages.add(ChatColor.DARK_PURPLE+ "Miner[1|3] "+ChatColor.BLUE+"\n Sneak left click with 64 logs in hand, grants 32 coal. sneak left click with 16 coal, grants 16 torches.");
			eaPages.add(ChatColor.DARK_PURPLE+ "Miner[2|3] "+ChatColor.BLUE+"\n Below Y 50, grant night vision \n below Y 25 grant night vision, regeneration, saturation, strength.");
			eaPages.add(ChatColor.DARK_PURPLE+ "Miner[3|3] "+ChatColor.BLUE+"\n Break a 3x3x1 plane in front of you, shift to break only the target block.");
			eaPages.add(ChatColor.DARK_PURPLE+ "Farmer[1|1] "+ChatColor.BLUE+"\n Right click any plant to instantly grow it. \n slightly increased dropRate from crops.");
			eaPages.add(ChatColor.DARK_PURPLE+ "Breeder[1|1] "+ChatColor.BLUE+"\n Right click any passive, breedable mob to force breeding. \n Right click wolf, ocelot to instantly tame.");
			eabm.setPages(eaPages);
			eaBook.setItemMeta(eabm);
			eaBook.setAmount(1);
			//p.getInventory().addItem(eaBook);
			p.getLocation().getWorld().dropItem(p.getLocation(), eaBook);
			BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".book",true);
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			break;
		case "ALICORN":
			//Unicorn
			ItemStack acBook = new ItemStack(Material.WRITTEN_BOOK);
			BookMeta acbm = (BookMeta) acBook.getItemMeta();
			acbm.setAuthor(ChatColor.DARK_PURPLE+"BKCP");
			acbm.setTitle("Alicorn");
			ArrayList<String> acPages = new ArrayList<String>();
			acPages.add(ChatColor.DARK_PURPLE+ "Flight "+ChatColor.BLUE+"\n right click a feather to flap. \n Hold right click a feather to fly. \n Named feathers have different effects.");
			acPages.add(ChatColor.DARK_PURPLE+ "Advanced flight[1/2] "+ChatColor.BLUE+"\n Feather named trainer \n shift right click on the ground to increase maximum energy and regen.");
			acPages.add(ChatColor.DARK_PURPLE+ "Advanced flight[2/2] "+ChatColor.BLUE+"\n Feather named wings \n with "+fCost +" levels right click to gain creative mode flight for "+fTime+" seconds.");
			acPages.add(ChatColor.DARK_PURPLE+ "Spells "+ChatColor.BLUE+"\n left click a stick to choose a spell. \n right click a stick to cast a spell.");
			acPages.add(ChatColor.DARK_PURPLE+ "Affinity tomes "+ChatColor.BLUE+"\n with 30 levels and 3 nether stars shift click a book* onto a diamond block. \n Book formatting: br: <formattingName>");
			acPages.add(ChatColor.DARK_PURPLE+ "Formatting names "+ChatColor.BLUE+"\n corrupter - saviour");
			acPages.add(ChatColor.DARK_PURPLE+ "Proficiency "+ChatColor.BLUE+"\n Reading an affinity tome changes your spell loadout.");
			acPages.add(ChatColor.DARK_PURPLE+ "Tome of Arcane Knowlege[1|2] "+ChatColor.BLUE+"\n Shift right click unnamed book onto Diamond block with 30 levels and 3 nether stars");
			acPages.add(ChatColor.DARK_PURPLE+ "Tome of Arcane Knowlege[2|2] "+ChatColor.BLUE+"\n Increase maximum mana and regen.");
			acPages.add(ChatColor.DARK_PURPLE+ "Farming "+ChatColor.BLUE+"\n Right click any plant to instantly grow it.");
			acbm.setPages(acPages);
			acBook.setItemMeta(acbm);
			acBook.setAmount(1);
			//p.getInventory().addItem(acBook);
			p.getLocation().getWorld().dropItem(p.getLocation(), acBook);
			BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".book",true);
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			break;
		case "CHANGELING":
			//Changeling
			ItemStack chBook = new ItemStack(Material.WRITTEN_BOOK);
			BookMeta chbm = (BookMeta) chBook.getItemMeta();
			chbm.setAuthor(ChatColor.DARK_PURPLE+"BKCP");
			chbm.setTitle("Changeling");
			ArrayList<String> chPages = new ArrayList<String>();
			chPages.add(ChatColor.DARK_PURPLE+ "Flight "+ChatColor.BLUE+"\n right click a feather to flap. \n Hold right click a feather to fly. \n Named feathers have different effects.");
			chPages.add(ChatColor.DARK_PURPLE+ "Advanced flight[1|2] "+ChatColor.BLUE+"\n Feather named trainer \n shift right click on the ground to increase maximum energy and regen.");
			chPages.add(ChatColor.DARK_PURPLE+ "Advanced flight[2|2] "+ChatColor.BLUE+"\n Feather named wings \n with "+fCost +" levels right click to gain creative mode flight for "+fTime+" seconds.");
			chPages.add(ChatColor.DARK_PURPLE+ "Spells "+ChatColor.BLUE+"\n left click a stick to choose a spell. \n right click a stick to cast a spell.");
			chPages.add(ChatColor.DARK_PURPLE+ "Disguises "+ChatColor.BLUE+"\n left click a leather to choose a disguise. \n right click a leather to disguise/un-disguise.");
			chPages.add(ChatColor.DARK_PURPLE+ "Tome of Arcane Knowlege[1|2] "+ChatColor.BLUE+"\n Shift right click unnamed book onto Diamond block with 30 levels and 3 nether stars");
			chPages.add(ChatColor.DARK_PURPLE+ "Tome of Arcane Knowlege[2|2] "+ChatColor.BLUE+"\n Increase maximum mana and regen.");
			chbm.setPages(chPages);
			chBook.setItemMeta(chbm);
			chBook.setAmount(1);
			//p.getInventory().addItem(chBook);
			p.getLocation().getWorld().dropItem(p.getLocation(), chBook);
			BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".book",true);
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			break;
		case "DRAGON":
			ItemStack dgBook = new ItemStack(Material.WRITTEN_BOOK);
			BookMeta dgbm = (BookMeta) dgBook.getItemMeta();
			dgbm.setAuthor(ChatColor.DARK_PURPLE+"BKCP");
			dgbm.setTitle("Dragon");
			ArrayList<String> dgPages = new ArrayList<String>();
			dgPages.add(ChatColor.DARK_PURPLE+ "Flight "+ChatColor.BLUE+"\n right click a feather to flap. \n Hold right click a feather to fly. \n Named feathers have different effects.");
			dgPages.add(ChatColor.DARK_PURPLE+ "Advanced flight[1|2] "+ChatColor.BLUE+"\n Feather named trainer \n shift right click on the ground to increase maximum energy and regen.");
			dgPages.add(ChatColor.DARK_PURPLE+ "Advanced flight[2|2] "+ChatColor.BLUE+"\n Feather named wings \n with "+fCost +" levels right click to gain creative mode flight for "+fTime+" seconds.");
			dgPages.add(ChatColor.DARK_PURPLE+ "DragonsFire "+ChatColor.BLUE+"\n right click a blaze rod to breath fire.");
			dgbm.setPages(dgPages);
			dgBook.setItemMeta(dgbm);
			dgBook.setAmount(1);
			//p.getInventory().addItem(dgBook);
			p.getLocation().getWorld().dropItem(p.getLocation(), dgBook);
			BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".book",true);
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			break;
		case "TIMBERWOLF":
			ItemStack twBook = new ItemStack(Material.WRITTEN_BOOK);
			BookMeta twbm = (BookMeta) twBook.getItemMeta();
			twbm.setAuthor(ChatColor.DARK_PURPLE+"BKCP");
			twbm.setTitle("Timberwolf");
			ArrayList<String> twPages = new ArrayList<String>();
			twPages.add(ChatColor.DARK_PURPLE+ "Packs[1/2] "+ChatColor.BLUE+"\n If you have an invitation you may join <x> pack using /bcJoinPack");
			twPages.add(ChatColor.DARK_PURPLE+ "Packs[2/2] "+ChatColor.BLUE+"\n run /bcCreatePack <packName> <packColourCode>");
			twPages.add(ChatColor.DARK_PURPLE+ "Environment "+ChatColor.BLUE+"\n Some environments may have different effects on you.");
			twbm.setPages(twPages);
			twBook.setItemMeta(twbm);
			twBook.setAmount(1);
			//p.getInventory().addItem(twBook);
			p.getLocation().getWorld().dropItem(p.getLocation(), twBook);
			BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".book",true);
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			break;
		case "GRIFFON":
			ItemStack gfBook = new ItemStack(Material.WRITTEN_BOOK);
			BookMeta gfbm = (BookMeta) gfBook.getItemMeta();
			gfbm.setAuthor(ChatColor.DARK_PURPLE+"BKCP");
			gfbm.setTitle("Griffon");
			ArrayList<String> gfPages = new ArrayList<String>();
			gfPages.add(ChatColor.DARK_PURPLE+ "Flight "+ChatColor.BLUE+"\n right click a feather to flap. \n Hold right click a feather to fly. \n Named feathers have different effects.");
			gfPages.add(ChatColor.DARK_PURPLE+ "Advanced flight "+ChatColor.BLUE+"\n Feather named trainer \n shift right click on the ground to increase maximum energy and regen.");
			gfPages.add(ChatColor.DARK_PURPLE+ "Advanced flight "+ChatColor.BLUE+"\n Feather named wings \n with "+fCost +" levels right click to gain creative mode flight for "+fTime+" seconds.");
			gfbm.setPages(gfPages);
			gfBook.setItemMeta(gfbm);
			gfBook.setAmount(1);
			//p.getInventory().addItem(gfBook);
			p.getLocation().getWorld().dropItem(p.getLocation(), gfBook);
			BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".book",true);
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			break;
		case "DRACONEQUUS":
			ItemStack dcBook = new ItemStack(Material.WRITTEN_BOOK);
			BookMeta dcbm = (BookMeta) dcBook.getItemMeta();
			dcbm.setAuthor(ChatColor.DARK_PURPLE+"BKCP");
			dcbm.setTitle("Draconequus");
			ArrayList<String> dcPages = new ArrayList<String>();
			dcPages.add(ChatColor.DARK_PURPLE+ "Flight "+ChatColor.BLUE+"\n right click a feather to flap. \n Hold right click a feather to fly. \n Named feathers have different effects.");
			dcPages.add(ChatColor.DARK_PURPLE+ "Advanced flight "+ChatColor.BLUE+"\n Feather named trainer \n shift right click on the ground to increase maximum energy and regen.");
			dcPages.add(ChatColor.DARK_PURPLE+ "Advanced flight "+ChatColor.BLUE+"\n Feather named wings \n with "+fCost +" levels right click to gain creative mode flight for "+fTime+" seconds.");
			dcPages.add(ChatColor.DARK_PURPLE+ "Spells "+ChatColor.BLUE+"\n left click a stick to choose a spell. \n right click a stick to cast a spell.");
			dcPages.add(ChatColor.DARK_PURPLE+ "Affinity tomes "+ChatColor.BLUE+"\n with 30 levels and 3 nether stars shift click a book* onto a diamond block. \n Book formatting: br: <formattingName>");
			dcPages.add(ChatColor.DARK_PURPLE+ "Tome of Arcane Knowlege[1|2] "+ChatColor.BLUE+"\n Shift right click unnamed book onto Diamond block with 30 levels and 3 nether stars");
			dcPages.add(ChatColor.DARK_PURPLE+ "Tome of Arcane Knowlege[2|2] "+ChatColor.BLUE+"\n Increase maximum mana and regen.");
			dcbm.setPages(dcPages);
			dcBook.setItemMeta(dcbm);
			dcBook.setAmount(1);
			//p.getInventory().addItem(dcBook);
			p.getLocation().getWorld().dropItem(p.getLocation(), dcBook);
			BkCP.playerClass.getConfig().set(p.getUniqueId().toString()+".book",true);
			BkCP.playerClass.saveConfig();
			BkCP.playerClass.reloadConfig();
			break;
		default:
			break;
		}
	}
	public static void SpellBook(Player p)
	{
		ItemStack b = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta bm = (BookMeta) b.getItemMeta();
		bm.setAuthor(ChatColor.DARK_PURPLE+"BKCP");
		bm.setTitle(ChatColor.DARK_PURPLE+"Book of Spells");
		List<String> pages = new ArrayList<String>();
		//pages = BkCP.SpellList;
		//pages.add(ChatColor.DARK_PURPLE+"Add/Remove spells\n"+ChatColor.AQUA+"Adding: AD_<SPELLNAME>\n Removing: RE_<SPELLNAME>");
		for(String spell : BkCP.SpellList)
		{
			pages.add(ChatColor.BLUE+"AD_"+spell+ChatColor.RED+"\nRE_"+spell);
		}
		getSpellInfo(p);
		bm.setPages(pages);
		b.setItemMeta(bm);
		p.getLocation().getWorld().dropItem(p.getLocation(), b);
	}
	public static void getSpellInfo(Player p)//, String spell)
	{
		ItemStack b = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta bm = (BookMeta) b.getItemMeta();
		bm.setAuthor(ChatColor.DARK_PURPLE+"BKCP");
		bm.setTitle(ChatColor.DARK_PURPLE+p.getName()+"'s research notes");
		List<String> pages = new ArrayList<String>();
		for(String spell : BkCP.SpellList)
		{
			switch(spell.toLowerCase())
			{
			case "blink":
				pages.add(ChatColor.DARK_PURPLE+"BLINK\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Endermen can naturally tap into the ender dimension for localized close range teleportation, I believe i may of found a way to do the same.\nIt is a costly process and can only be done in short bursts.");
				break;
			case "recall":
				pages.add(ChatColor.DARK_PURPLE+"RECALL \n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Portals rip holes in the world, allowing travel between dimensions. I believe i can apply this principle to my 'blink' ability \nalthough i am unsure i can manage interdimensional travel. More research is needed.");
				break;
			case "recallii":
				pages.add(ChatColor.DARK_PURPLE+"RECALLII\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Breakthrough! I've perfected my 'recall' ability, i can now travel interdimensionally to a specific point.");
				break;
			case "flamei":
				pages.add(ChatColor.DARK_PURPLE+"FLAMEI\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Using magic I have devised a way to conjure flames fire at will.\nAlthough it only burns living creatures, more research into this is required");
				break;
			case "flameii":
				pages.add(ChatColor.DARK_PURPLE+"FLAMEII\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"I've improved my fire conjuration increasing the temperature. \nStill It only burns creatures, I think im close to something though.");
				break;
			case "flameiii":
				pages.add(ChatColor.DARK_PURPLE+"FLAMEIII\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Finally! I have finally managed to burn the world, I can only melt ice and snow and I believe I've reached the limit.");
				break;
			case "fireblast":
				pages.add(ChatColor.DARK_PURPLE+"FIREBLAST\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"I tried furthering my stufy of flame conjuration and believe i've found another path to explore. \nInstead of channeling a slow stream of magical energy, I use a large burst generating an explosion. ");
				break;
			case "fireball":
				pages.add(ChatColor.DARK_PURPLE+"FIREBALL\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Combining the focusing of fire and the explosion of fire, i believe i have developed a way to throw a volotile ball of fire.");
				break;
			case "fireaura":
				pages.add(ChatColor.DARK_PURPLE+"FIREAURA\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Again i tried combining the focus and burst of energy, this time It formed a cloak of fire.\nI must maintain concentration while casting otherwise the cloak will dissipate.");
				break;
			case "heali":
				pages.add(ChatColor.DARK_PURPLE+"HEALI\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"After breaking a leg, I began research into mending the body. It was a success, though its very costly.");
				break;
			case "healii":
				pages.add(ChatColor.DARK_PURPLE+"HEALII\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Upon further research I have improved the recovery rate, though it is still a very costly process.");
				break;
			case "healiii":
				pages.add(ChatColor.DARK_PURPLE+"HEALIII\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Again I have improved the recovery rate, though it is still a quite costly process.");
				break;
			case "healiv":
				pages.add(ChatColor.DARK_PURPLE+"HEALIV\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"I have perfected the recovery rate, I do not believe I can improve it anymore.");
				break;
			case "healtargeti":
				pages.add(ChatColor.DARK_PURPLE+"HEALTARGETI\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Using the principles i learned from healing myself I have been able to heal others.\n I must maintain a lign of sight though.");
				break;
			case "healtargetii":
				pages.add(ChatColor.DARK_PURPLE+"HEALTARGETII\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"I have improved the rate of healing others. I still have to maintain a line of sight, I may need to look into other methods.");
				break;	
			case "healaura":
				pages.add(ChatColor.DARK_PURPLE+"HEALAURA\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Applying the conecept of self healing and healing others I have deveolped a way to generate a healing aura.\n It is difficult to maintain however.");
				break;
			case "courage":
				pages.add(ChatColor.DARK_PURPLE+"COURAGE\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Healing is nice, but I believe I have found a way to negate an amount of all physical damage.");
				break;
			case "curepoisoni":
				pages.add(ChatColor.DARK_PURPLE+"CUREPOISONI\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Studying te effects of milk I have discovered a way to remove poisons from the system.");
				break;
			case "curepoisonii":
				pages.add(ChatColor.DARK_PURPLE+"CUREPOISONII\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"I studied different poisons more in depth, and improved the energy required.");
				break;	
			case "cureall":
				pages.add(ChatColor.DARK_PURPLE+"CUREALL\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Focusing on only poisons was a mistake, I have managed a way to completely negate many ailments.");
				break;
			case "frosti":
				pages.add(ChatColor.DARK_PURPLE+"FROSTI\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Watching snow fall gave me a idea, I have devised a way to freeze the water in the air for a short time.");
				break;
			case "frostii":
				pages.add(ChatColor.DARK_PURPLE+"FROSTII\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"I've made the ice colder, causing tiny shards of ice to explode before quickly melting.");
				break;
			case "frostiii":
				pages.add(ChatColor.DARK_PURPLE+"FROSTIII\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Freezing the ground and water proved to be more difficult, but i've managed to lower the tempurature even more.");
				break;
			case "icespike":
				pages.add(ChatColor.DARK_PURPLE+"ICESPIKE\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"The micro-shards exploding encouraged me to try forming a large shard that would impale creatures.");
				break;
			case "iceblast":
				pages.add(ChatColor.DARK_PURPLE+"ICEBLAST\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"I tried to channel too much energy into a single shard and it exploded around me.\n I was unharmed so this may prove useful.");
				break;
			case "frostaura":
				pages.add(ChatColor.DARK_PURPLE+"FROSTI\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Draping myself in a flurry of ice and snow turned out better than i had hoped.\n I can freeze the very land I walk on and water turns to ice.");
				break;
			case "shadowbeami":
				pages.add(ChatColor.DARK_PURPLE+"SHADOWBEAMI\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Channeling the energies of the void I have managed to form holes in reality, sometimes something comes out when it makes contact with a living being.");
				break;
			case "shadowbeamii":
				pages.add(ChatColor.DARK_PURPLE+"SHADOWBEAMII\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"I have looked deeper into the void, strange noises are everywhere but I cannot see, I must continue.");
				break;
			case "shadowbeamiii":
				pages.add(ChatColor.DARK_PURPLE+"SHADOWBEAMIII\n"+ChatColor.RED+""+ChatColor.ITALIC+""+ChatColor.MAGIC+"\u0388\u03c0\u03b5\u03c3\u03b1 \u03c3\u03b5 \u03ad\u03bd\u03b1 \u03c1\u03ae\u03b3\u03bc\u03b1 \u002c \u03b5\u03af\u03b4\u03b1 \u03c6\u03c1\u03b9\u03ba\u03c4\u03cc \u03c0\u03bb\u03ac\u03c3\u03bc\u03b1\u03c4\u03b1 \u002e \u0398\u03b1 \u03c0\u03c1\u03ad\u03c0\u03b5\u03b9 \u03bd\u03b1 \u03c3\u03c4\u03b1\u03bc\u03b1\u03c4\u03ae\u03c3\u03b5\u03b9\u002e"+ChatColor.RESET+"\n"+
						ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"What is this... something's wrong.");
				break;
			case "shadowblast":
				pages.add(ChatColor.DARK_PURPLE+"SHADOWBLAST\n"+ChatColor.RED+""+ChatColor.ITALIC+"...");
				break;
			case "shadowbolt":
				pages.add(ChatColor.DARK_PURPLE+"SHADOWBOLT\n"+ChatColor.RED+""+ChatColor.ITALIC+"...");
				break;
			case "shadowaura":
				pages.add(ChatColor.DARK_PURPLE+"SHADOWAURA\n"+ChatColor.RED+""+ChatColor.ITALIC+"...");
				break;
			case "lifedraini":
				pages.add(ChatColor.DARK_PURPLE+"LIFEDRAINI\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"I have managed a way to syphon the life force of others...");
				break;
			case "lifedrainii":
				pages.add(ChatColor.DARK_PURPLE+"LIFEDRAINII\n"+ChatColor.RED+""+ChatColor.ITALIC+"It feels wrong...");
				break;
			case "feedi":
				pages.add(ChatColor.DARK_PURPLE+"FEEDI\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Applying the conepts i learned from draining the life of others, I have managed a way to drain their strenght as well.");
				break;
			case "feedii":
				pages.add(ChatColor.DARK_PURPLE+"FEEDII\n"+ChatColor.RED+""+ChatColor.ITALIC+"The effects of such acts have not shown themselves, but I fear I'm beyond redemption.");
				break;
			case "throwcake":
				pages.add(ChatColor.DARK_PURPLE+"THROWCAKE\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"Exploding cake... I should really eat before I start researching.");
				break;
			case "tntrain":
				pages.add(ChatColor.DARK_PURPLE+"TNTRAIN\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"I managed to put the tnt out of phase, it will still affect living creatures, but the world is fine.");
				break;
			case "creeperrain":
				pages.add(ChatColor.DARK_PURPLE+"CREEPERRAIN\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"I applied the out of phase tnt to charged creepers. This could be fun.");
				break;
			case "throwfish":
				pages.add(ChatColor.DARK_PURPLE+"THROWFISH\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"I was hungry again... it explodes.");
				break;
			case "potionrain":
				pages.add(ChatColor.DARK_PURPLE+"POTIONRAIN\n"+ChatColor.DARK_PURPLE+""+ChatColor.ITALIC+"This is a terrible idea, I've devised a way to tear a hole into the dimension where it always rains potions...");
				break;
			}
		}
		bm.setPages(pages);
		b.setItemMeta(bm);
		p.getLocation().getWorld().dropItem(p.getLocation(), b);
	}
}
