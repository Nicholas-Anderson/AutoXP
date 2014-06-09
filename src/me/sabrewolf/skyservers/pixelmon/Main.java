package me.sabrewolf.skyservers.pixelmon;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.earth2me.essentials.Essentials;

public class Main extends JavaPlugin {
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
				if ((!ess.getUser(p).isJailed() == true)) {
					ess.getUser(p).giveExp(3);

				}
				// nothing

			} else {
				// nothing
			}
			if (CurrentExp > 150) {
				if ((!ess.getUser(p).isJailed() == false)) {
					ess.getUser(p).giveExp(1);
				}
			} else {
				// nothing
			}
		}

	}
}
