package com.codingcrusader.discubeplugin;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

import java.util.HashMap;

public class DiscubePlugin extends JavaPlugin {
    private static HashMap<String, Player> discordPlayerMap;

    public static final String NAME = "Discube";

    private IDiscordClient discordClient;

    @Override
    public void onEnable() {
        if (!getConfig().contains("discordToken")) {
            getConfig().set("discordToken", "");
        }
        saveConfig();

        discordPlayerMap = new HashMap<String, Player>();

        discordClient = createDiscordClient(getConfig().getString("discordToken"),  true);
        discordClient.getDispatcher().registerListener(new DiscordListener());

        getCommand("register").setExecutor(new RegisterCommand());

        getLogger().info(NAME + " enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info(NAME + " disabled!");
    }

    public static void registerPlayer(Player player, String discriminator) {
        discordPlayerMap.put(discriminator, player);
    }

    public static Player getPlayer(String discriminator) {
        return discordPlayerMap.get(discriminator);
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
