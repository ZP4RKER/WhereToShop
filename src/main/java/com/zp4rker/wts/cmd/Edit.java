package com.zp4rker.wts.cmd;

import com.zp4rker.wts.WhereToShop;
import com.zp4rker.wts.config.Config;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

class Edit extends CMD {

	Edit(JavaPlugin plugin, CommandSender sender, String[] args) {
		super(plugin, sender, args, true);
		Help.commands.add(ChatColor.translateAlternateColorCodes('§',
				"§e/wheretoshop edit <itemname> [buy|sell|both] <shopname>"));
	}

	public void command() {
		String item = getArgs()[0];
		String option = getArgs()[1];
		String shop = getArgs()[2];
		if (WhereToShop.shops.containsKey(shop)) {
			if (WhereToShop.shops.get(shop).get(item) != null) {
				Config config = WhereToShop.shops.get(shop);
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
				getSender().sendMessage(
						WhereToShop.ITEMED.replace("%item%", item).replace("%option%", option).replace("%shop%", shop));
			} else {
				getSender().sendMessage(WhereToShop.ITEMNE);
			}
		} else {
			getSender().sendMessage(WhereToShop.SHOPNE);
		}
	}

	public boolean validArgs(String[] args) {
		return (args.length == 3);
	}

	public boolean hasPermission(CommandSender sender) {
		return sender.hasPermission("wheretoshop.edit");
	}

}
