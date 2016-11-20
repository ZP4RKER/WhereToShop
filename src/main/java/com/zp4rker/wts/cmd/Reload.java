package com.zp4rker.wts.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.zp4rker.wts.WhereToShop;

class Reload extends CMD {

	Reload(JavaPlugin plugin, CommandSender sender, String[] args) {
		super(plugin, sender, args, true);
		Help.commands.add(ChatColor.translateAlternateColorCodes('§', "§e/wheretoshop reload"));
	}

	public void command() {

		getPlugin().getPluginLoader().disablePlugin(getPlugin());
		getPlugin().getPluginLoader().enablePlugin(getPlugin());
		getSender().sendMessage(WhereToShop.RELOAD);

	}

	public boolean validArgs(String[] args) {
		return true;
	}

	public boolean hasPermission(CommandSender sender) {
		return sender.hasPermission("wheretoshop.admin");
	}

}
