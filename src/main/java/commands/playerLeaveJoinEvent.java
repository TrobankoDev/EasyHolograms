package commands;

import com.trobanko.holograms.Holograms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;



public class playerLeaveJoinEvent implements Listener {

    private Holograms plugin;

    public playerLeaveJoinEvent(Holograms plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoinLeave(PlayerJoinEvent e) {
        for (ArmorStand holo : createHologramCommand.getUpdate()) {
            holo.setCustomName(ChatColor.GREEN + String.valueOf(Bukkit.getOnlinePlayers().size()));
            holo.setCustomNameVisible(true);

        }

    }

    @EventHandler
    public void onPlayerJoinLeave(PlayerQuitEvent e) {
        for (ArmorStand holo : createHologramCommand.getUpdate()) {
            holo.setCustomName(ChatColor.GREEN + String.valueOf(Bukkit.getOnlinePlayers().size()));
            holo.setCustomNameVisible(true);
            e.getPlayer().getLocation().getChunk().unload(true);
            e.getPlayer().getLocation().getChunk().load();
        }
    }

}
