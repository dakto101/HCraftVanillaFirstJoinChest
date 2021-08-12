package me.dakto101;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import me.dakto101.loottable.HCraftVanillaLootTable;
import me.dakto101.loottable.HCraftVanillaLootTableElement;

public class HCraftVanillaSuperChest implements Listener {
	private static Plugin plugin = HCraftVanillaFirstJoinChest.plugin;
	private static ItemStack superChest;
	private static String superChestName = "§3§6§f§lRương khởi đầu";
	
	public HCraftVanillaSuperChest(HCraftVanillaFirstJoinChest plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void placeBlock(BlockPlaceEvent e) {
		if (e.getItemInHand().equals(superChest)) {
			if (e.getBlockPlaced().getType().equals(Material.CHEST)) {
				Chest chest = (Chest) e.getBlockPlaced().getState();

				//RunTaskLater
				BukkitScheduler scheduler = Bukkit.getScheduler();	
		  		scheduler.runTaskLater(plugin, new Runnable() {
		  			
		  			@Override
		  			public void run() {
						List<ItemStack> itemList = new ArrayList<ItemStack>();
						itemList = createSuperChestItemList();
						for (int i = 0; i < itemList.size(); i++) {
							if (chest.isPlaced()) chest.getInventory().setItem(i, itemList.get(i));
						}
		  			}
		  			
		  		}, 3L);
				//
			}
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (!HCraftVanillaFirstJoinChestConfig.config.getBoolean(HCraftVanillaFirstJoinChestConfig.ENABLE)) return;
		if (!e.getPlayer().hasPlayedBefore()) {
			//Add SuperChest
			e.getPlayer().getInventory().addItem(superChest);
			//Add NewbieBed
			/*
			ItemStack bed = new ItemStack(Material.WHITE_BED);
			ItemMeta bedMeta = bed.getItemMeta();
			bedMeta.setLore(Arrays.asList("§7Dùng để làm nơi hồi sinh."));
			bedMeta.setDisplayName("§fGiường tân thủ");
			bed.setItemMeta(bedMeta);
			e.getPlayer().getInventory().addItem(bed);
			*/
		}
	}
	
	public static void setup() {
		//
		superChest = new ItemStack(Material.CHEST, 1);
		ItemMeta superChestMeta = superChest.getItemMeta();
		superChestMeta.setDisplayName(superChestName);
		List<String> superChestLore = new ArrayList<String>();
		superChestLore.add("§7Hãy tìm một nơi lý tưởng, đặt hộp xuống và mở ra.");
		superChestLore.add("§7Chứa một vài vật phẩm gì đó...");
		superChestMeta.setLore(superChestLore);
		superChest.setItemMeta(superChestMeta);
		//
	}
	
	public static ItemStack getSuperChest() {
		return superChest;
	}
	
	public static List<ItemStack> createSuperChestItemList() {
		List<ItemStack> itemList = new ArrayList<ItemStack>();
		for (int i = 0; i < 27; i ++) {
			//TestCode
			ItemStack item;
			item = getFirstJoinChestLootTable().generateItem();
			if (item != null) itemList.add(item);
			//TestCode
		}
		return itemList;
	}
	
	private static HCraftVanillaLootTable getFirstJoinChestLootTable() {
		HCraftVanillaLootTable lootTable = new HCraftVanillaLootTable();
		
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.APPLE), 1, 1, 3));
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.DRIED_KELP), 1, 1, 10));
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.BREAD), 0.75, 1, 6));
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.COOKIE), 0.75, 2, 10));
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.SALMON), 1.5, 1, 5));
		
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.IRON_INGOT), 1, 1, 2));
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.WOODEN_PICKAXE), 0.750, 1, 1));
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.WOODEN_AXE), 0.750, 1, 1));
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.STONE_AXE), 0.25, 1, 1));
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.STONE_PICKAXE), 0.25, 1, 1));
		
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.OAK_PLANKS), 6.5, 1, 12));
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.STICK), 4, 1, 12));
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.ACACIA_LOG), 0.632, 1, 3));
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.BIRCH_LOG), 0.632, 1, 3));
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.DARK_OAK_LOG), 0.632, 1, 3));
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.JUNGLE_LOG), 0.632, 1, 3));
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.OAK_LOG), 0.632, 1, 3));
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.SPRUCE_LOG), 0.632, 1, 3));

		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.ACACIA_SAPLING), 0.5, 5, 10));
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.BIRCH_SAPLING), 0.5, 5, 10));
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.DARK_OAK_SAPLING), 0.5, 5, 10));
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.OAK_SAPLING), 1, 5, 10));
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.SPRUCE_SAPLING), 0.5, 5, 10));
		//Add air
		lootTable.addElement(new HCraftVanillaLootTableElement(new ItemStack(Material.AIR), lootTable.getChanceInTotal()*0.3, 1, 1));
		return lootTable;
	}
	
}
