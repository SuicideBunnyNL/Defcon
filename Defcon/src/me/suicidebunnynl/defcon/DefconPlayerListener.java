package me.suicidebunnynl.defcon;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

public class DefconPlayerListener extends PlayerListener {

	public static Defcon plugin;

	public DefconPlayerListener(Defcon instance) {
		plugin = instance;
	}
	
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (plugin.frozen.contains(player.getDisplayName())) {
			event.setCancelled(true);
		}
	}

}
