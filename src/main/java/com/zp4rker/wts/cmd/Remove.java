package com.zp4rker.wts.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.zp4rker.wts.WhereToShop;

class Remove extends CMD {

	Remove(JavaPlugin plugin, CommandSender sender, String[] args) {
		super(plugin, sender, args, true);
		Help.commands.add(ChatColor.translateAlternateColorCodes('§',
				"§e/wheretoshop remove [item <itemname> <shopname>|shop <shopname>]"));
	}

	public void command() {
		setArgs(CMD.updateArgs(getArgs()));
		if (getArgs()[0].equalsIgnoreCase("shop")) {
			// Shop subcommand
			if (WhereToShop.shops.containsKey(getArgs()[1].toLowerCase())) {
				WhereToShop.shops.get(getArgs()[1].toLowerCase()).deleteFile();
				WhereToShop.shops.remove(getArgs()[1].toLowerCase());
				getSender().sendMessage(WhereToShop.SHOPD.replace("%shop%", getArgs()[1]));
			} else {
				getSender().sendMessage(WhereToShop.SHOPNE);
			}
		} else {
			// Item subcommand
			String item = getArgs()[0].toLowerCase();
			String shop = getArgs()[1].toLowerCase();
			if (WhereToShop.shops.containsKey(shop)) {
				if (WhereToShop.shops.get(shop).get(item) != null) {
					WhereToShop.shops.get(shop).set(item, null);
					getSender().sendMessage(WhereToShop.ITEMD.replace("%item%", item).replace("%shop%", shop));
				} else {
					getSender().sendMessage(WhereToShop.ITEMNE);
				}
			} else {
				getSender().sendMessage(WhereToShop.SHOPNE);
			}
		}
	}

	public boolean validArgs(String[] args) {
		if (args.length > 1) {
			switch (args[1].toLowerCase()) {
			case "item":
				return (args.length == 4);
			case "shop":
				return (args.length == 3);
			}
		}
		return false;
	}

	public boolean hasPermission(CommandSender sender) {
		return getSender().hasPermission("wheretoshop.remove");
	}

}
