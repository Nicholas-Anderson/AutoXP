package me.sabrewolf.skyservers.pixelmon;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.earth2me.essentials.Essentials;

public class Main extends JavaPlugin {
	Essentials ess = (Essentials) Bukkit.getServer().getPluginManager()
			.getPlugin("Essentials");

	public void onEnable() {
		File file = new File(getDataFolder() + File.separator + "config.yml");
		if (!file.exists()) {
			getLogger().info("Generating the config..");

			getConfig().addDefault("time", 100);
			getConfig().addDefault("minExpPoints", 150);
			getConfig().addDefault("expGivenForUnderMin", 3);
			getConfig().addDefault("expGivenForAboveMin", 1);
			getConfig().options().copyDefaults(true);
			saveConfig();
		}
		getServer().getScheduler().scheduleSyncRepeatingTask(this,
				new Runnable() {
					public void run() {
						giveXp();
					}
				}, 0, getConfig().getInt("time")); // this is in ticks (time)

	}
	
	// Initialise giving their Exp
	public void giveXp() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			float CurrentExp = p.getTotalExperience();
			if (!(ess.getUser(p).isJailed() == true)) {
				if ((!ess.getUser(p).isAfk() == true)) {
					if (CurrentExp < getConfig().getInt("minExpPoints")) {
						ess.getUser(p).giveExp(
								getConfig().getInt("expGivenForUnderMin"));
					} else {
						ess.getUser(p).giveExp(
								getConfig().getInt("expGivenForAboveMin"));
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
				sender.sendMessage("§b=-----------------+  §9 AutoExp §b  +-----------------=");
				sender.sendMessage("§b= §3Available commands§f:");
				if (sender.hasPermission("autoexp.reload")) {
					sender.sendMessage("§b=  §5  - §a /autoexp §creload  §f-§e  Reloads the configration.");
				}
				sender.sendMessage("§b=---------------------------------------------------=");
				return true;
			}
		}
		// Reload command
		if (args[0].equalsIgnoreCase("reload")) {

			if (sender.hasPermission("autoexp.reload")) {
				reloadConfig();
				saveConfig();
				sender.sendMessage(ChatColor.GREEN
						+ "Configuration has been Reloaded!");
				return true;
			}
			sender.sendMessage(ChatColor.RED
					+ "You're not allowed to use that!");
			return true;
		}
		return false;

	}

}
