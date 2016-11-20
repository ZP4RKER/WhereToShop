package com.zp4rker.wts.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

class About extends CMD {

	About(JavaPlugin plugin, CommandSender sender, String[] args) {
		super(plugin, sender, args, true);
		Help.commands.add(ChatColor.translateAlternateColorCodes('§', "§e/wheretoshop about"));
	}

	public void command() {

		getSender().sendMessage(
				ChatColor.translateAlternateColorCodes('§', "§6§l" + getPlugin().getDescription().getName()));
		getSender().sendMessage(ChatColor.translateAlternateColorCodes('§',
				"§3Description: §e" + getPlugin().getDescription().getDescription()));
		getSender().sendMessage(ChatColor.translateAlternateColorCodes('§',
				"§3Version: §e" + getPlugin().getDescription().getVersion()));
		getSender().sendMessage(ChatColor.translateAlternateColorCodes('§',
				"§3Author: §e" + getPlugin().getDescription().getAuthors().get(0)));

	}

	public boolean validArgs(String[] args) {
		return true;
	}

	public boolean hasPermission(CommandSender sender) {
		return sender.hasPermission("wheretoshop.about");
	}

}
