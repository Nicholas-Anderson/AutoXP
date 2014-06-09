package me.sabrewolf.skyservers.pixelmon;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.earth2me.essentials.Essentials;

public class Main extends JavaPlugin {
	// public static ess = com.earth2me.essentials.commands.Commandexp;
	Essentials ess = (Essentials) Bukkit.getServer().getPluginManager()
			.getPlugin("Essentials");

	public void onEnable() {
		getServer().getScheduler().scheduleSyncRepeatingTask(this,
				new Runnable() {
					public void run() {
						giveXp();
					}
				}, 0, 100);

	}

	// Initialise giving their Exp
	public void giveXp() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			float CurrentExp = p.getTotalExperience();
			if (CurrentExp < 150) {

				ess.getUser(p).giveExp(3);
			} else {

				ess.getUser(p).giveExp(1);
			}
		}
	}
}