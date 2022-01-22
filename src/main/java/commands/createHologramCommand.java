package commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.*;

public class createHologramCommand implements CommandExecutor {

    public static ArrayList<ArmorStand> ids = new ArrayList<>();

    public static ArrayList<ArmorStand> getUpdate(){
        return ids;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            Location loc = player.getLocation();
            if(args[0].equalsIgnoreCase("add") && args.length > 1) {

                loc.setY(loc.getY() - 0.3);
                ArrayList<String> argsAsList = new ArrayList<>(Arrays.asList(args));
                argsAsList.remove(0);
                String words = String.join(" ", argsAsList);
                String[] wordsSplit = words.split(":n");

                for (String i : wordsSplit){

                    ArmorStand hologram = (ArmorStand) player.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);

                    if(i.contains(":o")) {
                        ids.add(hologram);
                        hologram.setCustomName(ChatColor.GREEN + String.valueOf(Bukkit.getOnlinePlayers().size()));
                    }else{hologram.setCustomName(ChatColor.translateAlternateColorCodes('&',i.trim()));}

                    hologram.setCustomNameVisible(true);
                    hologram.setInvisible(true);
                    hologram.setInvulnerable(true);
                    hologram.setGravity(false);
                    loc.setY(loc.getY() - 0.25);
                    if (i.isBlank()) {
                        hologram.setCustomNameVisible(false);
                    }

                }

                player.sendMessage(ChatColor.GREEN + "The hologram has been successfully placed. Do /remove to remove it.");



            }

            else if(args[0].equalsIgnoreCase("remove")) {
                List<Entity> nearBy = player.getNearbyEntities(0.5, 0.5, 0.5);
                if (nearBy.size() > 0) {
                    for (Entity e : nearBy){
                        if (e.getType().equals(EntityType.ARMOR_STAND)){
                            player.sendMessage(ChatColor.GREEN + "The hologram has been successfully removed");
                            break;
                        } else{
                            player.sendMessage(ChatColor.RED + "There are no close-by holograms to remove, please stand closer!");
                            break;
                        }
                    }
                    for (Entity e : nearBy) {
                       if (e.getType().equals(EntityType.ARMOR_STAND)){
                           e.remove();

                       }
                    }
                }
                else{
                    player.sendMessage(ChatColor.RED + "There are no close-by holograms to remove, please stand closer!");
                }
            } else{
                player.sendMessage(ChatColor.RED + "Incorrect Usage! /hologram [add | remove] <args>");
            }
        }


        return true;
    }
}
