package com.zp4rker.wts.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.zp4rker.wts.WhereToShop;

abstract class CMD {
	private JavaPlugin plugin;
	private CommandSender sender;
	private String[] args;
	private boolean nonPlayer;

	CMD(JavaPlugin plugin, CommandSender sender, String[] args, boolean nonPlayer) {
		this.plugin = plugin;
		this.sender = sender;
		this.args = args;
		this.nonPlayer = nonPlayer;
	}

	public abstract void command();

	public abstract boolean validArgs(String[] args);

	public abstract boolean hasPermission(CommandSender sender);

	void run() {
		if (validSender(sender)) {
			if (hasPermission(sender)) {
				if (validArgs(args)) {
					this.command();
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

	private boolean validSender(CommandSender sender) {
		if (nonPlayer || sender instanceof Player) {
			return true;
		} else {
			return false;
		}
	}

	JavaPlugin getPlugin() {
		return plugin;
	}

	CommandSender getSender() {
		return sender;
	}

	Player getPlayer() {
		if (validSender(sender)) {
			return (Player) sender;
		} else {
			return null;
		}
	}

	void setSender(CommandSender sender) {
		this.sender = sender;
	}

	String[] getArgs() {
		return args;
	}

	void setArgs(String[] args) {
		this.args = args;
	}

	static String[] updateArgs(String[] args) {
		List<String> list = new ArrayList<>();
		for (int i = 1; i < args.length; i++) {
			list.add(args[i]);
		}
		return list.toArray(new String[0]);
	}

}
