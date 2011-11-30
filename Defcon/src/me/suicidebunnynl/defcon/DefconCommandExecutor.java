package me.suicidebunnynl.defcon;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class DefconCommandExecutor implements CommandExecutor {

	private Defcon plugin;
	private Player player;
	private ConsoleCommandSender console;
	
	public DefconCommandExecutor(Defcon plugin){
		this.plugin = plugin;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if(sender instanceof Player){
			player = (Player) sender;
		}
		
		if(sender instanceof ConsoleCommandSender){
			console = (ConsoleCommandSender) sender;
		}
		
		// Handle player commands
		if(player != null){
			String command = cmd.getName();
			
			if(command.equalsIgnoreCase("defcon")){
				
				if(args[0].equalsIgnoreCase("freezeall")){
					if(player.hasPermission("defcon.freeze.all")){
						Player[] onlinePlayers = plugin.getServer().getOnlinePlayers();
						
						freeze(onlinePlayers, player);
					}else{
						Message(player, "You have no rights to use that command.");
					}
					return true;
				}
				
				
				if(args[0].equalsIgnoreCase("burnall")){
					if(player.hasPermission("defcon.burn.all")){
						Player[] onlinePlayers = plugin.getServer().getOnlinePlayers();
						
						burn(onlinePlayers, player);
					}else{
						Message(player, "You have no rights to use that command.");
					}
					return true;
				}
				
				if(args[0].equalsIgnoreCase("boomall")){
					if(player.hasPermission("defcon.explode.all")){
						Player[] onlinePlayers = plugin.getServer().getOnlinePlayers();
						
						explode(onlinePlayers, player);
					}else{
						Message(player, "You have no rights to use that command.");
					}
					return true;
				}
				
				
				if(args[0].equalsIgnoreCase("freezep")){
					if(player.hasPermission("defcon.freeze.player")){
						
						if(args.length > 1 && args[1] != null){
							Player getTarget = (Player) plugin.getServer().getPlayer((String)args[1]);
							
							
							if(getTarget.isOnline()){
								
								Player[] target = null;
								target[0] = getTarget;
								
								freeze(target, player);
							}
							
						}else{
							return true;
						}
						
					}else{
						Message(player, "You have no rights to use that command.");
					}
					return true;
				}
				
				
				if(args[0].equalsIgnoreCase("burnp")){
					if(player.hasPermission("defcon.burn.player")){
						if(args.length > 1 && args[1] != null){
							Player getTarget = (Player) plugin.getServer().getPlayer((String)args[1]);
							
							
							if(getTarget.isOnline()){
								
								Player[] target = null;
								target[0] = getTarget;
								
								burn(target, player);
							}
							
						}else{
							return true;
						}
					}else{
						Message(player, "You have no rights to use that command.");
					}
					return true;
				}
				
				if(args[0].equalsIgnoreCase("boomp")){
					if(player.hasPermission("defcon.explode.player")){
						if(args.length > 1 && args[1] != null){
							Player getTarget = (Player) plugin.getServer().getPlayer((String)args[1]);
							
							
							if(getTarget.isOnline()){
								
								Player[] target = null;
								target[0] = getTarget;
								
								explode(target, player);
							}
							
						}else{
							return true;
						}
					}else{
						Message(player, "You have no rights to use that command.");
					}
					return true;
				}
				
			}
			
		}
		
		return false;
	}

	public void Message(Player player, String msg) {
		player.sendMessage("*" + ChatColor.AQUA + "Freezer" + ChatColor.WHITE
				+ "* " + ChatColor.AQUA + msg);
	}
	
	private void freeze(Player[] targets, CommandSender sender){
		for(Player player: targets){
			if(player.getName() != sender.getName()){
				if(!plugin.frozen.contains(player.getDisplayName())){
					plugin.frozen.add(player.getDisplayName());
				}
				else if(plugin.frozen.contains(player.getDisplayName())){
					plugin.frozen.remove(player.getDisplayName());
				}
			}
		}
	}
	
	private void burn(Player[] targets, CommandSender sender){
		for(Player player: targets){
			if(player.getName() != sender.getName()){
				if(!plugin.burning.contains(player.getDisplayName())){
					plugin.burning.add(player.getDisplayName());
					player.setFireTicks(10000);
				}
				else if(plugin.burning.contains(player.getDisplayName())){
					plugin.burning.remove(player.getDisplayName());
					player.setFireTicks(0);
				}
			}
			
		}
		
	}
	
	private void explode(Player[] targets, CommandSender sender){
		for(Player player: targets){
			if(player.getName() != sender.getName()){
				player.getWorld().createExplosion(player.getLocation(), 4F);
			}
			
		}
	}

}
