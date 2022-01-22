package com.trobanko.holograms;

import commands.createHologramCommand;
import commands.playerLeaveJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Holograms extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new playerLeaveJoinEvent(this), this);

        getCommand("hologram").setExecutor(new createHologramCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
