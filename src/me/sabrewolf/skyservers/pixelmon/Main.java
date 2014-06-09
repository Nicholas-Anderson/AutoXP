package me.sabrewolf.skyservers.pixelmon;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin 
{
	public void onEnable() 
	{
		getServer().getScheduler().scheduleSyncRepeatingTask(this,
				new Runnable() {
					public void run() {
						giveXp();
						}
				}, 0, 200);

	}
	
	public void giveXp() 
	{
		for (Player p : Bukkit.getOnlinePlayers())
			{
				float CurrentExp = p.getExp();
				if (CurrentExp > 300)
				     p.giveExp(1);
				if (CurrentExp < 300)
				     p.giveExp(5);
			}
	}
}
