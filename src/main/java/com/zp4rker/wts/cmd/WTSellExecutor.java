package com.zp4rker.wts.cmd;

import com.zp4rker.wts.WhereToShop;
import com.zp4rker.wts.config.Config;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WTSellExecutor implements CommandExecutor {

	public WTSellExecutor() {
		Help.commands.add(ChatColor.translateAlternateColorCodes('§', "§e/wheretosell <item>"));
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getLabel().equalsIgnoreCase("wheretosell")) {
			if (sender instanceof Player) {
				if (sender.hasPermission("wheretoshop.sell")) {
					if (args.length == 1) {
						for (Config config : WhereToShop.shops.values()) {
							if (config.get(args[0]) != null) {
								if (config.getBoolean(args[0] + ".sell", false)) {
									Location location = WhereToShop.getLocationFromConfig(args[0] + ".location",
											config);
									Player player = (Player) sender;
									player.sendMessage(WhereToShop.TELEPORT);
									player.teleport(location);
									return true;
								}
							}
						}
						sender.sendMessage(WhereToShop.NOSELL);
					} else {
						sender.sendMessage(WhereToShop.INVALIDA);
					}
				} else {
					sender.sendMessage(WhereToShop.INVALIDP);
				}
			} else {
				sender.sendMessage(WhereToShop.INVALIDS);
			}
		}
		return true;
	}

}