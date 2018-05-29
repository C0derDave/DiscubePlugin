package com.codingcrusader.discubeplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class DiscubePlugin extends JavaPlugin
{
    public static final String NAME = "Discube";

    @Override
    public void onEnable()
    {
        getLogger().info(NAME + " enabled!");
    }

    @Override
    public void onDisable()
    {
        getLogger().info(NAME + " disabled!");
    }
}
