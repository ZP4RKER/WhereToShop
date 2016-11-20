package com.zp4rker.wts;

import com.zp4rker.wts.cmd.WTBuyExecutor;
import com.zp4rker.wts.cmd.WTSellExecutor;
import com.zp4rker.wts.cmd.WTShopExecutor;
import com.zp4rker.wts.config.Config;
import com.zp4rker.wts.config.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;

public class WhereToShop extends JavaPlugin {
	private static FileConfiguration config;
	private static JavaPlugin plugin;
	public static HashMap<String, Config> shops = new HashMap<>();
	private static ConfigManager manager;
	public static String INVALIDA, INVALIDP, INVALIDS, NEARGS, SHOPE, SHOPNE, ITEME, ITEMNE, SHOPC, ITEMA, INVALIDBO,
			ITEMED, RELOAD, TELEPORT, NOBUY, NOSELL, SHOPD, ITEMD;

	public void onEnable() {
		// Allow static access
		config = getConfig();
		plugin = this;
		// Intitialise the ConfigManager
		manager = new ConfigManager(this);
		// Shop list config
		Config shopList = manager.getNewConfig("shops.yml");
		// Load shop files
		for (String string : shopList.getStringList("shops")) {
			shops.put(string, manager.getNewConfig(string + ".yml"));
		}
		// Save default config
		saveDefaultConfig();
		// Set messages
		INVALIDA = ChatColor.translateAlternateColorCodes('&', config.getString("invalid-args"));
		INVALIDP = ChatColor.translateAlternateColorCodes('&', config.getString("invalid-perms"));
		INVALIDS = ChatColor.translateAlternateColorCodes('&', config.getString("invalid-sender"));
		NEARGS = ChatColor.translateAlternateColorCodes('&', config.getString("notenough-args"));
		SHOPE = ChatColor.translateAlternateColorCodes('&', config.getString("shop-exists"));
		SHOPNE = ChatColor.translateAlternateColorCodes('&', config.getString("shop-no-exist"));
		ITEME = ChatColor.translateAlternateColorCodes('&', config.getString("item-exists"));
		ITEMNE = ChatColor.translateAlternateColorCodes('&', config.getString("item-no-exist"));
		SHOPC = ChatColor.translateAlternateColorCodes('&', config.getString("shop-create"));
		ITEMA = ChatColor.translateAlternateColorCodes('&', config.getString("item-add"));
		INVALIDBO = ChatColor.translateAlternateColorCodes('&', config.getString("invalid-buy-option"));
		ITEMED = ChatColor.translateAlternateColorCodes('&', config.getString("item-edit"));
		RELOAD = ChatColor.translateAlternateColorCodes('&', config.getString("reload-msg"));
		TELEPORT = ChatColor.translateAlternateColorCodes('&', config.getString("teleport-msg"));
		NOBUY = ChatColor.translateAlternateColorCodes('&', config.getString("no-buy"));
		NOSELL = ChatColor.translateAlternateColorCodes('&', config.getString("no-sell"));
		SHOPD = ChatColor.translateAlternateColorCodes('&', config.getString("shop-delete"));
		ITEMD = ChatColor.translateAlternateColorCodes('&', config.getString("item-delete"));
		// Register the commands
		getCommand("wheretoshop").setExecutor(new WTShopExecutor(this));
		getCommand("wheretobuy").setExecutor(new WTBuyExecutor());
		getCommand("wheretosell").setExecutor(new WTSellExecutor());
	}

	public void onDisable() {
		Config config = manager.getNewConfig("shops.yml");
		config.set("shops", Arrays.asList(shops.keySet().toArray(new String[0])));
		config.saveConfig();
	}

	public static void writeLocationToConfig(Location location, String path, Config config) {
		config.set(path + ".world", location.getWorld().getName());
		config.set(path + ".x", location.getX());
		config.set(path + ".y", location.getY());
		config.set(path + ".z", location.getZ());
		config.set(path + ".yaw", location.getYaw());
		config.set(path + ".pitch", location.getPitch());
	}

	public static Location getLocationFromConfig(String path, Config config) {
		World world = plugin.getServer().getWorld(config.getString(path + ".world"));
		double x = config.getDouble(path + ".x");
		double y = config.getDouble(path + ".y");
		double z = config.getDouble(path + ".z");
		float yaw = Float.parseFloat(config.getString(path + ".yaw"));
		float pitch = Float.parseFloat(config.getString(path + ".pitch"));
		return new Location(world, x, y, z, yaw, pitch);
	}

	public static void createConfig(String name) {
		shops.put(name, manager.getNewConfig(name + ".yml"));
	}

}
