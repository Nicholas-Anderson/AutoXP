package me.sabrewolf.skyservers.pixelmon;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.earth2me.essentials.Essentials;

public class Main extends JavaPlugin {
	Essentials ess = (Essentials) Bukkit.getServer().getPluginManager()
			.getPlugin("Essentials");

	public void onEnable() {
		File file = new File(getDataFolder() + File.separator + "config.yml");
		if (!file.exists()) {
			this.getLogger().info("Generating the config..");

			this.getConfig().addDefault("time", 100);
			this.getConfig().options().copyDefaults(true);
			this.saveConfig();
		}
		getServer().getScheduler().scheduleSyncRepeatingTask(this,
				new Runnable() {
					public void run() {
						giveXp();
					}
				}, 0, getConfig().getInt("time"));

	}

	// Initialise giving their Exp
	public void giveXp() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			float CurrentExp = p.getTotalExperience();
			System.out.println(!ess.getUser(p).isAfk());
			if (!(ess.getUser(p).isJailed() == true)) {
				if ((!ess.getUser(p).isAfk() == true)) {

					if (CurrentExp < 150) {
						ess.getUser(p).giveExp(3);
					} else {
						ess.getUser(p).giveExp(1);
					}
				}
			}
		}
	}

}
