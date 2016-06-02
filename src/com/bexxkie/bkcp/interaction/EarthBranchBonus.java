package com.bexxkie.bkcp.interaction;

import java.util.Random;

import net.minecraft.server.v1_10_R1.NBTTagCompound;

import org.bukkit.CropState;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NetherWartsState;
import org.bukkit.TreeSpecies;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftAnimals;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Ocelot.Type;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.NetherWarts;
import org.bukkit.material.Tree;
import org.bukkit.entity.Damageable;

import com.bexxkie.bkcp.BkCP;
import com.bexxkie.bkcp.classes.Alicorn;
import com.bexxkie.bkcp.classes.Earth;
import com.bexxkie.bkcp.classes.Griffon;
import com.bexxkie.bkcp.classes.interfaces.EnergyUser;
import com.bexxkie.bkcp.util.ParticleEffect;
import com.bexxkie.bkcp.util.RandInt;
import com.bexxkie.bkcp.util.ParticleEffect.ParticleType;


public class EarthBranchBonus 
implements Listener
{
	@EventHandler
	public void strength(EntityDamageByEntityEvent e)
	{
		if(e.getEntity() instanceof Damageable){
			if(e.getDamager() instanceof Player)
			{
				Player p = (Player)e.getDamager();
				if(BkCP.onlinePlayers.get(p.getName())instanceof Earth)
				{
					for(Entity en : p.getNearbyEntities(2, 2, 2))
					{
						if(en instanceof Damageable)
						{
							((Damageable)en).damage(e.getDamage());
						}
					}
					e.setDamage(e.getDamage()+RandInt.randInt(1, 3));
				}
				if(BkCP.onlinePlayers.get(p.getName())instanceof Griffon)
				{
					for(Entity en : p.getNearbyEntities(2, 2, 2))
					{
						if(en instanceof Damageable)
						{
							((Damageable)en).damage(e.getDamage());
						}
					}
					e.setDamage(e.getDamage()+RandInt.randInt(1, 3));
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void growCrop(PlayerInteractEvent e)
	{
		if(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof EnergyUser)
		{
			if(((BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof Earth)&&((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).getBranch().equalsIgnoreCase("farmer")&&((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).getEnergy()>11)
					||(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof Alicorn)&&((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).getEnergy()>11)
			{
				if(e.getAction()==Action.RIGHT_CLICK_BLOCK)
				{
					Location loc = e.getClickedBlock().getLocation().add(+0.5, +0.5, +0.5);
					Material mat = e.getClickedBlock().getType();
					ParticleEffect portal = new ParticleEffect(ParticleType.PORTAL, 2, 32, 0);

					switch(mat)
					{
					case CROPS:
						((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setEnergy(((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).getEnergy()-10.5);
						e.getClickedBlock().setData(CropState.RIPE.getData());
						try {
							portal.sendToLocation(loc.add(0.5,.05,0.5));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						break;
					case POTATO:
						((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setEnergy(((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).getEnergy()-10.5);
						e.getClickedBlock().setData(CropState.RIPE.getData());
						try {
							portal.sendToLocation(loc.add(0.5,.05,0.5));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						break;
					case CARROT:
						((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setEnergy(((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).getEnergy()-10.5);
						e.getClickedBlock().setData(CropState.RIPE.getData());
						try {
							portal.sendToLocation(loc.add(0.5,.05,0.5));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						break;
					case NETHER_WARTS:
						((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setEnergy(((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).getEnergy()-10.5);
						NetherWarts nw = new NetherWarts(e.getClickedBlock().getTypeId(), e.getClickedBlock().getData());
						nw.setState(NetherWartsState.RIPE);
						e.getClickedBlock().setData(nw.getData());
						try {
							portal.sendToLocation(loc.add(0.5,.05,0.5));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						break;
					case SAPLING:
						((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setEnergy(((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).getEnergy()-10.5);
						growTree(e.getClickedBlock());
						try {
							portal.sendToLocation(loc.add(0.5,.05,0.5));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						break;
					default:
						break;
					}
				}
			}
			if(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof Earth){
				if(((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).getBranch().equalsIgnoreCase("miner")&&((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).getEnergy()>20)
				{
					if(e.getPlayer().isSneaking()&&e.getAction()==Action.LEFT_CLICK_AIR)
					{
						if((e.getPlayer().getItemInHand().getType().equals(Material.LOG)||e.getPlayer().getItemInHand().getType().equals(Material.LOG_2))&&e.getPlayer().getItemInHand().getAmount()==64)
						{
							((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setEnergy(((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).getEnergy()-20);
							e.getPlayer().setItemInHand(new ItemStack(Material.COAL,32));
						}
						if(e.getPlayer().getItemInHand().getType().equals(Material.COAL)&&e.getPlayer().getItemInHand().getAmount()==16)
						{
							((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).setEnergy(((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).getEnergy()-32);
							e.getPlayer().setItemInHand(new ItemStack(Material.TORCH,16));
						}
					}
				}
			}
		}
	}
	@EventHandler
	public void breakCrop(BlockBreakEvent e)
	{
		if(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof Earth)
		{
			if(((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).getBranch().equalsIgnoreCase("farmer"))
			{
				Material mat = e.getBlock().getType();
				switch(mat)
				{
				case CROPS:
					setDrop(e);
					break;
				case CARROT:
					setDrop(e);
					break;
				case POTATO:
					setDrop(e);
					break;
				case CACTUS:
					setDrop(e);
					break;
				case NETHER_STALK:
					setDrop(e);
					break;
				case SUGAR_CANE:
					setDrop(e);
					break;
				default:
					break;
				}
			}

		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void setDrop(BlockBreakEvent e)
	{
		if(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof Earth)
		{
			for(ItemStack drops : e.getBlock().getDrops())
			{
				int dat = e.getBlock().getData();
				if(dat ==(CropState.RIPE.getData())){
					drops.setAmount(drops.getAmount()*RandInt.randInt(1,3));
					e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), drops);
					break;
				}
			}
		}
	}
	@EventHandler
	public void setMobDrop(EntityDeathEvent e)
	{
		if(e.getEntity().getLastDamageCause() instanceof Player)
		{
			Player p = (Player) e.getEntity().getLastDamageCause();
			if(BkCP.onlinePlayers.get(p.getName())instanceof Earth)
			{
				for(ItemStack drops : e.getDrops())
				{
					drops.setAmount(drops.getAmount()*RandInt.randInt(1,2));
					e.getEntity().getLocation().getWorld().dropItemNaturally(e.getEntity().getLocation(), drops);
					break;
				}
			}
		}
	}

	@EventHandler
	public void breedAnimal(PlayerInteractEntityEvent e)
	{
		if(BkCP.onlinePlayers.get(e.getPlayer().getName())instanceof Earth)
		{
			if(((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).getBranch().equalsIgnoreCase("breeder")&&((EnergyUser)BkCP.onlinePlayers.get(e.getPlayer().getName())).getEnergy()>11)
			{
				switch(e.getRightClicked().getType())
				{
				case COW:
					Cow cow = (Cow) e.getRightClicked();
					NBTTagCompound tag = new NBTTagCompound();
					((CraftAnimals)cow).getHandle().b(tag);

					tag.setInt("InLove", 600);
					cow.playEffect(EntityEffect.VILLAGER_HEART);
					((CraftAnimals)cow).getHandle().a(tag);

					break;
				case MUSHROOM_COW:
					MushroomCow mCOW = (MushroomCow) e.getRightClicked();
					NBTTagCompound tag0 = new NBTTagCompound();
					((CraftAnimals)mCOW).getHandle().b(tag0);
					tag0.setInt("InLove", 600);
					mCOW.playEffect(EntityEffect.VILLAGER_HEART);
					((CraftAnimals)mCOW).getHandle().a(tag0);

					break;
				case SHEEP:
					Sheep sheep = (Sheep) e.getRightClicked();
					NBTTagCompound tag1 = new NBTTagCompound();
					((CraftAnimals)sheep).getHandle().b(tag1);
					tag1.setInt("InLove", 600);
					sheep.playEffect(EntityEffect.VILLAGER_HEART);
					((CraftAnimals)sheep).getHandle().a(tag1);

					break;
				case PIG:
					Pig pig = (Pig) e.getRightClicked();
					NBTTagCompound tag11 = new NBTTagCompound();
					((CraftAnimals)pig).getHandle().b(tag11);
					tag11.setInt("InLove", 600);
					pig.playEffect(EntityEffect.VILLAGER_HEART);
					((CraftAnimals)pig).getHandle().a(tag11);

					break;
				case WOLF:
					Wolf wolf = (Wolf) e.getRightClicked();
					if(!wolf.isTamed())
					{
						wolf.setTamed(true);
						wolf.setOwner(e.getPlayer());
					}else{
						NBTTagCompound tag111 = new NBTTagCompound();
						((CraftAnimals)wolf).getHandle().b(tag111);
						tag111.setInt("InLove", 600);
						wolf.playEffect(EntityEffect.VILLAGER_HEART);
						((CraftAnimals)wolf).getHandle().a(tag111);

					}
					break;
				case OCELOT:
					Ocelot ocelot = (Ocelot) e.getRightClicked();
					if(!ocelot.isTamed())
					{
						ocelot.setTamed(true);
						ocelot.setCatType(setCatType());
						ocelot.setOwner(e.getPlayer());
					}else{

						NBTTagCompound tag111 = new NBTTagCompound();
						((CraftAnimals)ocelot).getHandle().b(tag111);
						tag111.setInt("InLove", 600);
						ocelot.playEffect(EntityEffect.VILLAGER_HEART);
						((CraftAnimals)ocelot).getHandle().a(tag111);

					}
					break;
				default:
					break;
				}
			}
		}
	}

	public Type setCatType()
	{
		int x = RandInt.randInt(0,2);
		switch (x)
		{
		case 0:
			return Type.BLACK_CAT;
		case 1:
			return Type.RED_CAT;
		case 2:
			return Type.SIAMESE_CAT;
		default:
			break;
		}
		return null;
	}

	@EventHandler
	public void killMob(EntityDeathEvent ev)
	{
		Entity ent = ev.getEntity();
		if(ent.getLastDamageCause() instanceof EntityDamageByEntityEvent)
		{
			EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) ent.getLastDamageCause();
			if(e.getDamager() instanceof Player)
			{
				Player p = (Player)e.getDamager();
				if(BkCP.onlinePlayers.get(p.getName())instanceof Earth)
				{
					if(((EnergyUser)BkCP.onlinePlayers.get(p.getName())).getBranch().equalsIgnoreCase("breeder"))
					{
						switch(ent.getType())
						{
						case COW:
							setMobDrop(ev);
							break;
						case MUSHROOM_COW:
							setMobDrop(ev);
							break;
						case SHEEP:
							setMobDrop(ev);
							break;
						case PIG:
							setMobDrop(ev);
							break;
						default:
							break;
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static boolean growTree(Block block) 
	{
		Byte data = block.getData();
		int id = block.getTypeId();
		Tree tree = new Tree(block.getTypeId(), block.getData());
		TreeSpecies species = tree.getSpecies();
		TreeType type = null;

		if (species.equals(TreeSpecies.BIRCH)) type = (new Random().nextDouble() < 5) ? TreeType.TALL_BIRCH : TreeType.BIRCH;
		else if (species.equals(TreeSpecies.ACACIA)) type = TreeType.ACACIA;
		else if (species.equals(TreeSpecies.DARK_OAK)) type = TreeType.DARK_OAK;
		else if (species.equals(TreeSpecies.JUNGLE)) return generateLargeTree(block, TreeType.SMALL_JUNGLE, TreeType.JUNGLE);
		else if (species.equals(TreeSpecies.REDWOOD)) return generateLargeTree(block, (new Random().nextDouble() < 5) ? TreeType.TALL_REDWOOD : TreeType.REDWOOD, TreeType.MEGA_REDWOOD);
		else if (species.equals(TreeSpecies.GENERIC))  type = (new Random().nextDouble() < 5) ? TreeType.BIG_TREE : TreeType.TREE;
		else return false;

		block.setType(Material.AIR);
		boolean used = block.getWorld().generateTree(block.getLocation(), type);

		if (!used) block.setTypeIdAndData(id, data, true);
		return used;
	}

	@SuppressWarnings("deprecation")
	public static boolean generateLargeTree(Block block, TreeType typeSmall, TreeType typeLarge) 
	{
		// backup failure data
		int typeid = block.getTypeId();
		byte data = block.getData();

		Block [] group = getFourGroup(block);
		Block grow_location = (group == null) ? block : getNorthWestBlock(group[0], group[1], group[2], group[3]);
		TreeType type = (group == null) ? typeSmall : typeLarge;

		if (group == null) block.setType(Material.AIR);
		else for (Block mBlock : group) mBlock.setType(Material.AIR);

		if (!grow_location.getWorld().generateTree(grow_location.getLocation(), type)) 
		{
			if (group == null) block.setTypeIdAndData(typeid, data, true);
			else for (Block mBlock : group) mBlock.setTypeIdAndData(typeid, data, true);

			return false;
		}

		return true;
	} 
	public static Block getNorthWestBlock(Block b1, Block b2, Block b3, Block b4) 
	{
		Block grow_location = b1.getRelative(BlockFace.NORTH).equals(b2) ? b2 : b1.getRelative(BlockFace.NORTH).equals(b3) ? b3 
				: b1.getRelative(BlockFace.NORTH).equals(b4) ? b4 : b1;
		grow_location = grow_location.getRelative(BlockFace.WEST).equals(b2) ? b2 : grow_location.getRelative(BlockFace.WEST).equals(b3) ? b3 
				: grow_location.getRelative(BlockFace.WEST).equals(b4) ? b4 : grow_location;
		return grow_location;
	}

	@SuppressWarnings("deprecation")
	public static Block[] getFourGroup(Block block) 
	{
		Block[] blocks = {block, null, null, null};

		World world = block.getWorld();
		int x = block.getX(), y = block.getY(), z = block.getZ();

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (j == 0 || i == 0) continue;
				blocks[1] = world.getBlockAt(x - i, y, z);
				blocks[2] = world.getBlockAt(x - i, y, z - j);
				blocks[3] = world.getBlockAt(x, y, z - j);

				boolean pass = true;
				for (Block mBlock : blocks) {
					if (!(mBlock.getData() == blocks[0].getData())) {
						pass = false;
						break;
					}
				}
				if (pass) return blocks;
			}
		}
		return null;
	}


}