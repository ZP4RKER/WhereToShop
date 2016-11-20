package com.zp4rker.wts.cmd;

import com.zp4rker.wts.WhereToShop;
import com.zp4rker.wts.config.Config;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

class Add extends CMD {

	Add(JavaPlugin plugin, CommandSender sender, String[] args) {
		super(plugin, sender, args, false);
		Help.commands.add(ChatColor.translateAlternateColorCodes('§',
				"§e/wheretoshop add [shop|item] <itemname> [buy|sell|both] <shop>"));
	}

	public void command() {
		setArgs(CMD.updateArgs(getArgs()));
		if (getArgs()[0].equalsIgnoreCase("shop")) {
			// Shop subcommand
			if (WhereToShop.shops.containsKey(getArgs()[1].toLowerCase())) {
				getSender().sendMessage(WhereToShop.SHOPE);
			} else {
				WhereToShop.createConfig(getArgs()[1].toLowerCase());
				getSender().sendMessage(WhereToShop.SHOPC.replace("%shop%", getArgs()[1]));
			}
		} else {
			// Item subcommand
			String item = getArgs()[1].toLowerCase();
			String option = getArgs()[2].toLowerCase();
			String shop = getArgs()[3].toLowerCase();
			Location location = getPlayer().getLocation();
			if (WhereToShop.shops.containsKey(shop)) {
				Config config = WhereToShop.shops.get(shop);
				if (config.getString(item) == null) {
					switch (option.toLowerCase()) {
					case "buy":
						config.set(item + ".buy", true);
						config.set(item + ".sell", false);
						break;
					case "sell":
						config.set(item + ".buy", false);
						config.set(item + ".sell", true);
						break;
					case "both":
						config.set(item + ".buy", true);
						config.set(item + ".sell", true);
						break;
					default:
						getSender().sendMessage(WhereToShop.INVALIDBO);
					}
					WhereToShop.writeLocationToConfig(location, item + ".location", config);
					config.saveConfig();
					getSender().sendMessage(WhereToShop.ITEMA.replace("%shop%", shop).replace("%item%", item));
				} else {
					getSender().sendMessage(WhereToShop.ITEME);
				}
			} else {
				getSender().sendMessage(WhereToShop.SHOPNE);
			}
		}
	}

	public boolean validArgs(String[] args) {
		if (args.length > 1) {
			switch (args[1].toLowerCase()) {
			case "shop":
				return (args.length == 3);
			case "item":
				return (args.length == 5);
			}
		}
		return false;
	}

	public boolean hasPermission(CommandSender sender) {
		return sender.hasPermission("wheretoshop.add");
	}

}
