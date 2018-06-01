package com.codingcrusader.discubeplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import sx.blah.discord.handle.obj.IChannel;

import java.util.UUID;

public class ServerListener implements Listener {
    @EventHandler
    public void playerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        IChannel channel = DiscubePlugin.getDiscordClient().getChannels().get(0);

        String discriminator = DiscubePlugin.getDiscriminator(uuid);

        if(discriminator != null)
            channel.sendMessage(player.getDisplayName() + "#" + discriminator + ": " + event.getMessage());
    }
}
