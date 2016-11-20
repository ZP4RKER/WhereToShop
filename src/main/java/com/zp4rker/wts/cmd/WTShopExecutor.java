package com.zp4rker.wts.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.zp4rker.wts.WhereToShop;

public class WTShopExecutor implements CommandExecutor {
	private JavaPlugin plugin;

	public WTShopExecutor(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if (cmd.getLabel().equalsIgnoreCase("wheretoshop")) {
			if (args.length >= 1) {
				Help help = new Help(plugin, sender, args);
				About about = new About(plugin, sender, args);
				Reload reload = new Reload(plugin, sender, args);
				Add add = new Add(plugin, sender, args);
				Remove remove = new Remove(plugin, sender, args);
				Edit edit = new Edit(plugin, sender, args);
				List list = new List(plugin, sender, args);
				switch (args[0].toLowerCase()) {
				case "about":
					about.run();
					break;
				case "help":
					help.run();
					break;
				case "reload":
					reload.run();
					break;
				case "add":
					add.run();
					break;
				case "remove":
					remove.run();
					break;
				case "edit":
					edit.run();
					break;
				case "list":
					list.run();
					break;
				default:
					sender.sendMessage(WhereToShop.INVALIDA);
				}
			} else {
				sender.sendMessage(WhereToShop.NEARGS);
			}
		}
		return true;
	}
}
