package com.craftilandia.mobspawners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("no console");
			return true;
		}
		Player p = (Player)sender;
		if(command.getName().equalsIgnoreCase("setspawner")){
			@SuppressWarnings("deprecation")
			Block b = p.getPlayer().getTargetBlock(null, 10);
			if(b.getType().equals(Material.MOB_SPAWNER)){
				EntityType mob = EntityType.valueOf(args[0].toUpperCase());
				CreatureSpawner mobspawn = (CreatureSpawner) b.getState();
				mobspawn.setSpawnedType(mob);
				mobspawn.setDelay(40);
				mobspawn.update();
			}else {
				p.sendMessage("you need to be looking a mob spawner to set the mob");
				return false;
			}
		
			}
		if(command.getName().equalsIgnoreCase("getspawner")){
			ItemStack mobspawn = new ItemStack(Material.MOB_SPAWNER, 1);
			p.getInventory().addItem(mobspawn);
		}
		return false;
	}
}
