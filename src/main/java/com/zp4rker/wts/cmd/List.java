package com.zp4rker.wts.cmd;

import com.zp4rker.wts.WhereToShop;
import com.zp4rker.wts.config.Config;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class List extends CMD {

	List(JavaPlugin plugin, CommandSender sender, String[] args) {
		super(plugin, sender, args, true);
		Help.commands.add(
				ChatColor.translateAlternateColorCodes('§', "§e/wheretoshop list §3OR §e/wheretoshop list <shop>"));
	}

	public void command() {
		if (getArgs().length == 3) {
			if (WhereToShop.shops.containsKey(getArgs()[2].toLowerCase())) {
				getSender()
						.sendMessage(ChatColor.translateAlternateColorCodes('§', "§6§lItem list of §2" + getArgs()[2]));
				Config config = WhereToShop.shops.get(getArgs()[2].toLowerCase());
				for (String item : Arrays.asList(config.getKeys().toArray(new String[0]))) {
					getSender().sendMessage(ChatColor.translateAlternateColorCodes('§', "§6- §2" + item));
				}
			}
			for (String shop : Arrays.asList(WhereToShop.shops.keySet().toArray(new String[0]))) {
				getSender().sendMessage(ChatColor.translateAlternateColorCodes('§', "§6- §2" + shop));
			}
		} else {
			getSender().sendMessage(ChatColor.translateAlternateColorCodes('§', "§6§lShop list"));
			for (String shop : Arrays.asList(WhereToShop.shops.keySet().toArray(new String[0]))) {
				getSender().sendMessage(ChatColor.translateAlternateColorCodes('§', "§6- §2" + shop));
			}
		}
	}

	public boolean validArgs(String[] args) {
		if (args.length >= 2) {
			if (args[1].equalsIgnoreCase("list")) {
				if (args.length == 3) {
					return WhereToShop.shops.containsKey(args[2]);
				} else {
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasPermission(CommandSender sender) {
		return (sender.hasPermission("wheretoshop.list"));
	}

}
