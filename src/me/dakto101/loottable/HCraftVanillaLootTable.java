package me.dakto101.loottable;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

public class HCraftVanillaLootTable {
	
	List<HCraftVanillaLootTableElement> lootData = new ArrayList<HCraftVanillaLootTableElement>();
	double chance = 0;
	
	public ItemStack generateItem() {
		if (chance == 0) {
			return null;
		} else {
			for (int i = 0; i < lootData.size()*10; i++) {
				HCraftVanillaLootTableElement element = lootData.get(i % lootData.size());
				double c = element.getChance() / chance;
				if (c > Math.random()) {
					ItemStack result = element.getItem();
					int amount = 1;
					if (element.getMinQuantity() == element.getMaxQuantity()) amount = element.getMinQuantity();
					else {
						amount = (int) (Math.random() * (element.getMaxQuantity() - element.getMinQuantity() + 1)) + element.getMinQuantity();
					}
					result.setAmount(amount);
					return result;
				}
			}
		}
		
		return null;
	}
	
	public void addElement(HCraftVanillaLootTableElement element) {
		this.chance = this.chance + element.getChance();
		lootData.add(element);
	}
	
	public double getChanceInTotal() {
		return this.chance;
	}
	
}
