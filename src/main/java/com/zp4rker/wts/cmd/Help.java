package com.zp4rker.wts.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

class Help extends CMD {

	static List<String> commands = new ArrayList<>();

	Help(JavaPlugin plugin, CommandSender sender, String[] args) {
		super(plugin, sender, args, true);
	}

	public void command() {

		getSender().sendMessage(ChatColor.translateAlternateColorCodes('§',
				"§6§l" + getPlugin().getDescription().getName() + " §6command list"));

		for (String command : commands) {
			getSender().sendMessage(command);
		}

	}

	public boolean validArgs(String[] args) {
		return true;
	}

	public boolean hasPermission(CommandSender sender) {
		return true;
	}

}
