package com.codingcrusader.discubeplugin;

import org.bukkit.plugin.java.JavaPlugin;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

public class DiscubePlugin extends JavaPlugin {
    public static final String NAME = "Discube";

    private IDiscordClient discordClient;

    @Override
    public void onEnable() {
        if (!getConfig().contains("discordToken")) {
            getConfig().set("discordToken", "");
        }
        saveConfig();

        discordClient = createDiscordClient(getConfig().getString("discordToken"),  true);
        discordClient.getDispatcher().registerListener(new DiscordListener());

        getLogger().info(NAME + " enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info(NAME + " disabled!");
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
