package com.codingcrusader.discubeplugin;

import org.bukkit.Server;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class DiscordListener {
    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {
        System.out.println(event.getMessage());
    }
}
