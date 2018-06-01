package com.codingcrusader.discubeplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegisterCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length > 0) {
                DiscubePlugin.registerPlayer(player.getUniqueId(), args[0]);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}