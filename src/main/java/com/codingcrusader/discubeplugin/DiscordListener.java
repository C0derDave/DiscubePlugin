package com.codingcrusader.discubeplugin;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class DiscordListener {
    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {
        String author = event.getAuthor().getName();
        String message = event.getMessage().getContent();

        DiscubePlugin.sendMessage(author + ": " + message);
    }
}
