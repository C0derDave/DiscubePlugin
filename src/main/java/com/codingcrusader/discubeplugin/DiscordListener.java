package com.codingcrusader.discubeplugin;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class DiscordListener {
    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {
        String discriminator = event.getAuthor().getDiscriminator();
        String message = event.getMessage().getContent();

        Player player = DiscubePlugin.getPlayer(discriminator);
        if(player != null) {
            player.sendMessage(event.getAuthor().getName() + ": " + message);
        }

        System.out.println(discriminator + ": " + message);
    }
}
