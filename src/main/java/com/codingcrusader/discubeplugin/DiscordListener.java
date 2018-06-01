package com.codingcrusader.discubeplugin;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class DiscordListener {
    @EventSubscriber
    public void onMessageReceived(MessageReceivedEvent event) {
        IUser author = event.getAuthor();
        IMessage message = event.getMessage();

        DiscubePlugin.sendMessage(author, message);
    }
}
