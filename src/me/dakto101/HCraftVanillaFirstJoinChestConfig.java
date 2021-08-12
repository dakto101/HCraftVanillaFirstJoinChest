package me.dakto101;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class HCraftVanillaFirstJoinChestConfig {

	private static File file;
	public static FileConfiguration config;
	private static Plugin plugin = HCraftVanillaFirstJoinChest.getPlugin(HCraftVanillaFirstJoinChest.class);
	
	public static final String FILENAME = "config.yml";	
	public static final String ENABLE = "enable";
	
	public static void setup() {
		file = new File(plugin.getDataFolder(), FILENAME);
		if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdirs();
		if (!file.exists()) {
			try {
				file.createNewFile();
				Bukkit.getServer().getConsoleSender().sendMessage("§aTao file thanh cong: " + FILENAME);
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage("§cKhong the tao ra file " + FILENAME);
			}
		}
		config = YamlConfiguration.loadConfiguration(file);
		configLoadData(config);
	    //
		
	}
	public static void saveConfig() {
	    try {
			config.save(file);
		} catch (IOException e) {
			Bukkit.getServer().getConsoleSender().sendMessage("§cKhong the luu file " + FILENAME);
			e.printStackTrace();
		}
	}
	public static void loadConfig() {
		config = YamlConfiguration.loadConfiguration(file);
	}
	public static void reloadConfig() {
		loadConfig();
		setup();
		saveConfig();
	}
	
	public static void configLoadData(FileConfiguration config) {
		if (config == null) return;

	    
	    Hashtable<String,Object> hash = new Hashtable<String, Object>();
	    hash.put(ENABLE, true);

	    
	    for (String string : hash.keySet()) {
	    	if (config.get(string) == null) config.set(string, hash.get(string));
	    	
	    }
	}
	
}
