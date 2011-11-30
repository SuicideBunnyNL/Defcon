package me.suicidebunnynl.defcon;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Defcon extends JavaPlugin {

	private final DefconPlayerListener playerListener = new DefconPlayerListener(this);
	ArrayList<String> frozen = new ArrayList<String>();
	ArrayList<String> burning = new ArrayList<String>();
	private DefconCommandExecutor myExecutor;
	
	// Get the logger for Minecraft
	Logger log = Logger.getLogger("Minecraft");
	
	@Override
	public void onDisable() {
		
		
		// On plugin disabled
		log.info("Plugin Defcon Disabled");
		
	}

	@Override
	public void onEnable() {
		
		// On plugin enabled
		log.info("Plugin Defcon Enabled");
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Event.Priority.Normal, this);
		
		myExecutor = new DefconCommandExecutor(this);
		getCommand("defcon").setExecutor(myExecutor);
		
		
	}

}
