package me.dakto101.loottable;

import org.bukkit.inventory.ItemStack;

public class HCraftVanillaLootTableElement {
	
	private ItemStack item;
	private double chance;
	private int min;
	private int max;

	public HCraftVanillaLootTableElement(ItemStack item, double chance, int min, int max) {
		this.item = item;
		this.chance = chance;
		this.min = min;
		this.max = max;
	}
	
	public ItemStack getItem() {
		return item;
	}
	
	public double getChance() {
		return chance;
	}
	
	public int getMinQuantity() {
		return min;
	}
	
	public int getMaxQuantity() {
		return max;
	}
	
}
