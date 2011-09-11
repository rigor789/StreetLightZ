package me.rigi.StreetLightZ;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StreetLightZCmdExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel,
			String[] args) {
			// TODO Auto-generated method stub
		if(sender.isOp()|| sender.hasPermission("streetlightz.edit")){			
		if(CommandLabel.equalsIgnoreCase("streetlightz")||CommandLabel.equalsIgnoreCase("sl")){
		if(args.length==0){
			sender.sendMessage(ChatColor.GOLD+"StreetLightZ needs arguments!");
			sender.sendMessage(ChatColor.YELLOW+"Notice that /StreetLightZ can be used as /sl!");
			sender.sendMessage(ChatColor.AQUA+"/StreetLightZ edit");
			sender.sendMessage(ChatColor.AQUA+"/StreetLightZ finish");
		}else{
			if(args[0].equalsIgnoreCase("edit")){
				//System.out.println("[StreetLightZ] Command add used");
				Player player = (Player)sender;
				StreetLightZMain.players.add(player);
				//System.out.println("[StreetLightZ] "+player+"is editing lights!");
				sender.sendMessage(ChatColor.GOLD+"You are now adding streetlights!");
				sender.sendMessage(ChatColor.GOLD+"Place torches and then type "+ChatColor.AQUA+"/StreetLightZ finish");
			}
			else if(args[0].equalsIgnoreCase("finish")){
				Player player = (Player) sender;
				StreetLightZMain.players.remove(player);
				sender.sendMessage(ChatColor.GOLD+"Finished adding lights!");
				StreetLightZReaderWriter.WriteAll();
			}else{
				sender.sendMessage(ChatColor.GOLD+"Use"+ChatColor.AQUA+"/StreetLightZ"+ ChatColor.GOLD+"for help!");
			}
		
		}
		}
		}else{
			sender.sendMessage(ChatColor.RED+"You don't have acces to that command!");
		}
		
		
		
		
		return false;
	}

}
