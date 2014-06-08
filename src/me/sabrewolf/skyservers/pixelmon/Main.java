package me.sabrewolf.skyservers.pixelmon;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

<<<<<<< HEAD
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
=======
public class Main extends JavaPlugin 
{
	public void onEnable()
	{
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
		{
			public void run()
			{
				for(Player p : Bukkit.getOnlinePlayers()) 
				{
					p.giveExp(1);
			        }				
			}
		}, 0, 200);
>>>>>>> b4e27731b051bee9b6e05a1d053c454c8102e676
	}

}
