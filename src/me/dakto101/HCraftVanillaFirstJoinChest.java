package me.dakto101;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HCraftVanillaFirstJoinChest extends JavaPlugin {

	public static HCraftVanillaFirstJoinChest plugin;
	
	public void onEnable() {
		
		plugin = this;
		
		new HCraftVanillaSuperChest(this);
		
		getLogger().info("Plugin cua server HCraft da chay thanh cong!" + this.getName());
		HCraftVanillaFirstJoinChestConfig.setup();
		HCraftVanillaSuperChest.setup();
		
	}
	
	
	public void onDisable() { 
		getLogger().info("Plugin cua server HCraft dang tat!" + this.getName());
		HCraftVanillaFirstJoinChestConfig.reloadConfig();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equals("firstjoinchest")) {
			if (!sender.hasPermission("hcraftvanillafirstjoinchest.admin")) return true;
			if (args.length == 1) {
				if (args[0].toString().equals("reload")) {
					HCraftVanillaFirstJoinChestConfig.reloadConfig();
					sender.sendMessage("§aReload thanh cong.");
				}
				if (args[0].toString().equals("test")) {
					if (sender instanceof Player) {
						Player p = (Player) sender;
						p.getInventory().addItem(HCraftVanillaSuperChest.getSuperChest());
					}
				}
			}
			
			if (args.length == 0) {
				sender.sendMessage("§a======HCraftVanillaFirstJoinChest======");
				sender.sendMessage("§a/firstjoinchest reload: Reload plugin.");
				sender.sendMessage("§a/firstjoinchest test: Test plugin.");
			}
			
			
			return true;
		}
		
		return false;
		
	}
	
}



	  
	


