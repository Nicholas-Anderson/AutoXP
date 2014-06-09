package me.sabrewolf.skyservers.pixelmon;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public void onEnable() {
		getServer().getScheduler().scheduleSyncRepeatingTask(this,
				new Runnable() {
					public void run() {
						for (Player p : Bukkit.getOnlinePlayers()) {
							p.giveExp(1);
						}
					}
				}, 0, 200);
}
}
