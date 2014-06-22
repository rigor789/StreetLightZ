package me.rigor789.StreetLightZ;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdExecutor implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
        if(!(sender.isOp() || sender.hasPermission("streetlightz.edit"))) {
            sender.sendMessage(ChatColor.RED + "You don't have access to that command!");
            return false;
        }
		if(!(CommandLabel.equalsIgnoreCase("StreetLightZ") || CommandLabel.equalsIgnoreCase("sl"))) {
            return false;
        }

		if(args.length==0) {
			sender.sendMessage(ChatColor.GOLD +     "StreetLightZ needs arguments!");
			sender.sendMessage(ChatColor.YELLOW +   "Notice: /StreetLightZ can be used as /sl!");
			sender.sendMessage(ChatColor.AQUA +     "/StreetLightZ edit");
			sender.sendMessage(ChatColor.AQUA +     "/StreetLightZ finish");
            return false;
		}

        if(args[0].equalsIgnoreCase("edit")) {
            Player player = (Player)sender;
            SessionManager.getInstance().createSession(player);
            sender.sendMessage(ChatColor.GOLD + "You are now adding streetlights!");
            sender.sendMessage(ChatColor.GOLD + "Place torches and then type " + ChatColor.AQUA + "/StreetLightZ finish");
            return false;
        } else if(args[0].equalsIgnoreCase("finish")) {
            Player player = (Player) sender;
            SessionManager.getInstance().removeSession(player);
            sender.sendMessage(ChatColor.GOLD + "Finished adding lights!");
            return false;
        }

        sender.sendMessage(ChatColor.GOLD + "Use" + ChatColor.AQUA + "/StreetLightZ" + ChatColor.GOLD + "for help!");
        return false;
    }
}
