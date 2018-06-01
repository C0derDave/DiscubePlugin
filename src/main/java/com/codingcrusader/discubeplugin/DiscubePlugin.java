package com.codingcrusader.discubeplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;

import java.util.HashMap;
import java.util.UUID;

public class DiscubePlugin extends JavaPlugin {
    private static HashMap<UUID, String> playerDiscordMap;

    public static final String NAME = "Discube";

    private static IDiscordClient discordClient;

    @Override
    public void onEnable() {
        if (!getConfig().contains("discordToken")) {
            getConfig().set("discordToken", "");
        }
        saveConfig();

        playerDiscordMap = new HashMap<UUID, String>();

        discordClient = createDiscordClient(getConfig().getString("discordToken"),  true);
        discordClient.getDispatcher().registerListener(new DiscordListener());

        getServer().getPluginManager().registerEvents(new ServerListener(), this);
        getCommand("register").setExecutor(new RegisterCommand());

        getLogger().info(NAME + " enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info(NAME + " disabled!");
    }

    public static void registerPlayer(UUID uuid, String discriminator) {
        playerDiscordMap.put(uuid, discriminator);
    }

    public static String getDiscriminator(UUID uuid) {
        return playerDiscordMap.get(uuid);
    }

    public static void sendMessage(IUser author, IMessage message) {
        String toSend = ChatColor.LIGHT_PURPLE + author.getName() + "#" + author.getDiscriminator() + ": " + message.getContent();

        for (UUID uuid : playerDiscordMap.keySet()) {
            Player player = Bukkit.getServer().getPlayer(uuid);

            player.sendMessage(toSend);

            for(IUser mentioned : message.getMentions()) {
                if(playerDiscordMap.values().contains(mentioned.getDiscriminator())) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_CHIME, 10, 1.0f);
                }
            }
        }
    }

    public static IDiscordClient getDiscordClient() {
        return discordClient;
    }

    private IDiscordClient createDiscordClient(String token, boolean login) {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(token);
        try {
            if (login) {
                return clientBuilder.login();
            } else {
                return clientBuilder.build();
            }
        } catch (DiscordException e) {
            e.printStackTrace();
            return null;
        }
    }
}
