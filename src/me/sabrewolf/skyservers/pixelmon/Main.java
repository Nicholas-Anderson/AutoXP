package me.sabrewolf.skyservers.pixelmon;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.earth2me.essentials.Essentials;

public class Main extends JavaPlugin implements Listener {
	Essentials ess = (Essentials) Bukkit.getServer().getPluginManager()
			.getPlugin("Essentials");

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		getConfig().options().copyDefaults(true);
		saveConfig();

		initializeExpGiving();
	}

	public void initializeExpGiving() {
		getServer().getScheduler().scheduleSyncRepeatingTask(this,
				new Runnable() {
					public void run() {
						giveXp();
					}
				}, 0, (getConfig().getInt("timeInSeconds") * 20)); // this is in
																	// seconds
																	// converted
	}

	// Initialise giving their Exp
	public void giveXp() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			float CurrentExp = p.getTotalExperience();
			if (ess.getUser(p).isJailed() != true) {
				if (ess.getUser(p).isAfk() != true) {
					if (CurrentExp < getConfig().getInt("minExpPoints")) {
						p.giveExp(getConfig().getInt("expGivenForUnderMin"));
					} else {
						p.giveExp(getConfig().getInt("expGivenForAboveMin"));
					}
				}
			}
		}
	}

	// Commands
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		// Information on commands
		if (cmd.getName().equalsIgnoreCase("autoexp")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.AQUA + "=-----------------+"
						+ ChatColor.DARK_BLUE + "AutoExp" + ChatColor.AQUA
						+ " +-----------------=");
				sender.sendMessage(ChatColor.AQUA + "= Available commands:");
				if (sender.hasPermission("autoexp.reload"))
					sender.sendMessage(ChatColor.AQUA + "=  "
							+ ChatColor.DARK_PURPLE + " - " + ChatColor.GREEN
							+ "/autoexp " + ChatColor.RED + "reload  "
							+ ChatColor.YELLOW + "- Reloads the configration.");
				if (sender.hasPermission("autoexp.setval"))
					sender.sendMessage(ChatColor.AQUA + "=  "
							+ ChatColor.DARK_PURPLE + " - " + ChatColor.GREEN
							+ "/autoexp " + ChatColor.RED
							+ "setval (val) (amount) " + ChatColor.YELLOW
							+ "  Sets a value in the config.");
				sender.sendMessage(ChatColor.AQUA
						+ "=---------------------------------------------------=");
				return true;
			}
		}
		// Reload command
		if (args[0].equalsIgnoreCase("reload")) {
			if (sender.hasPermission("autoexp.reload")) {
				this.reloadConfig();
				sender.sendMessage(ChatColor.GREEN
						+ "Configuration has been Reloaded!");
				Bukkit.getPluginManager().disablePlugin(this);
				Bukkit.getPluginManager().enablePlugin(this);
				return true;
			}
			sender.sendMessage(ChatColor.RED
					+ "You're not allowed to use that!");
			return true;
		}
		// setval command
		else if (args[0].equalsIgnoreCase("setval")) {
			if (sender.hasPermission("autoexp.setval")) {
				if (args.length == 1 || args.length == 2) {
					sender.sendMessage(ChatColor.RED
							+ "Usage: /autoexp setval "
							+ ChatColor.RED
							+ "(expGivenForAboveMin / expGivenForUnderMin / minExpPoints / timeInSeconds) (value)");
				} else if (args.length > 1) {

					float num = Float.parseFloat(args[2]);
					this.getConfig().set(args[1], num);
					this.saveConfig();
					this.reloadConfig();
					sender.sendMessage(ChatColor.GREEN
							+ "Configuration value \"" + args[1]
							+ "\" set to \"" + args[2] + "\"");
					Bukkit.getPluginManager().disablePlugin(this);
					Bukkit.getPluginManager().enablePlugin(this);
					this.reloadConfig();
					sender.sendMessage(ChatColor.GREEN
							+ "Configuration has been Reloaded!");
					Bukkit.getPluginManager().disablePlugin(this);
					Bukkit.getPluginManager().enablePlugin(this);
					return true;
				}
				sender.sendMessage(ChatColor.RED
						+ "You're not allowed to use that!");
				return true;
			}
			// setval command
			else if (args[0].equalsIgnoreCase("setval")) {
				if (sender.hasPermission("autoexp.setval")) {
					if (args.length == 1 || args.length == 2) {
						sender.sendMessage(ChatColor.RED
								+ "Usage: /autoexp setval (expGivenForAboveMin / expGivenForUnderMin / minExpPoints / timeInSeconds) (value)");
					} else if (args.length > 1) {
						int num = Integer.parseInt(args[2]);
						this.getConfig().set(args[1], num);
						this.saveConfig();
						this.reloadConfig();
						sender.sendMessage(ChatColor.GREEN
								+ "Configuration value \"" + args[1]
								+ "\" set to \"" + args[2] + "\"");
						Bukkit.getPluginManager().disablePlugin(this);
						Bukkit.getPluginManager().enablePlugin(this);
					}
				}
				return true;
			}
			return false;

		}
		return false;

	}
}
