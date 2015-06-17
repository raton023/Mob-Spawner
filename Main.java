package com.craftilandia.mobspawners;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
public class Main extends JavaPlugin implements Listener{
@Override
public void onEnable() 
{
getServer().getPluginManager().registerEvents(this, this);
saveDefaultConfig();
}

@EventHandler
public void rompiendo(BlockBreakEvent e) {
if(e.getBlock().getType().equals(Material.MOB_SPAWNER)) {
if(getConfig().getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+","+e.getBlock().getX()+","+e.getBlock().getY()+","+e.getBlock().getZ()) != null){
String thx=getConfig().getString(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+","+e.getBlock().getX()+","+e.getBlock().getY()+","+e.getBlock().getZ());
if(!thx.isEmpty()){
	if(!e.getPlayer().hasPermission("mobspawners.getdrop")){
		return;
	}
getConfig().set(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+","+e.getBlock().getX()+","+e.getBlock().getY()+","+e.getBlock().getZ(), "");
saveConfig();
e.getBlock().getDrops().clear();
ItemStack mobspawner = new ItemStack(Material.MOB_SPAWNER);
ItemMeta namespawn = mobspawner.getItemMeta();
namespawn.setDisplayName(thx.toUpperCase());
mobspawner.setItemMeta(namespawn);
e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), mobspawner);
e.getPlayer().sendMessage("spawner destroyed succesfuly");}}}}

@EventHandler
public void poniendo(BlockPlaceEvent e) {
if(e.getBlock().getType().equals(Material.MOB_SPAWNER)) {
if(e.getPlayer().getItemInHand().getItemMeta().hasDisplayName()){
getConfig().set(e.getPlayer().getWorld().getWorldFolder().toString().replace("./", "")+","+e.getBlock().getX()+","+e.getBlock().getY()+","+e.getBlock().getZ(), e.getItemInHand().getItemMeta().getDisplayName().toUpperCase());
saveConfig();
EntityType mob = EntityType.valueOf(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().toUpperCase());
CreatureSpawner mobspawn = (CreatureSpawner)e.getBlock().getState();
mobspawn.setSpawnedType(mob);
mobspawn.setDelay(40);
mobspawn.update();
e.getPlayer().sendMessage("spawner created succesfuly");}}}

public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
{
  if (!(sender instanceof Player))
  {
    sender.sendMessage("no console");
    return true;
  }
  Player p = (Player)sender;
  if (command.getName().equalsIgnoreCase("getspawner"))
  {
	  if(!p.hasPermission("mobspawners.getspawner")){
		  p.sendMessage("you do not have permission to use this command");
		  return true;
	  }
	  if(args.length == 1){
		if(args[0].equalsIgnoreCase("zombie")||args[0].equalsIgnoreCase("skeleton")||args[0].equalsIgnoreCase("horse")||args[0].equalsIgnoreCase("villager")||args[0].equalsIgnoreCase("guardian")||args[0].equalsIgnoreCase("snowman")||args[0].equalsIgnoreCase("mushroomcow")||args[0].equalsIgnoreCase("cow")||
				args[0].equalsIgnoreCase("sheep")||args[0].equalsIgnoreCase("pig")||args[0].equalsIgnoreCase("chicken")||args[0].equalsIgnoreCase("magma_cube")||args[0].equalsIgnoreCase("enderman")||args[0].equalsIgnoreCase("cave_spider")||args[0].equalsIgnoreCase("giant")||args[0].equalsIgnoreCase("iron_golem")||
				args[0].equalsIgnoreCase("endermite")||args[0].equalsIgnoreCase("creeper")||args[0].equalsIgnoreCase("rabbit")||args[0].equalsIgnoreCase("silverfish")||args[0].equalsIgnoreCase("squid")||args[0].equalsIgnoreCase("wolf")||args[0].equalsIgnoreCase("witch")||args[0].equalsIgnoreCase("ocelot")||
				args[0].equalsIgnoreCase("slime")||args[0].equalsIgnoreCase("bat")||args[0].equalsIgnoreCase("pig_zombie")||args[0].equalsIgnoreCase("spider")||
				args[0].equalsIgnoreCase("ghast")){
			//org.bukkit.entity.EntityType.
    ItemStack mobspawn = new ItemStack(Material.MOB_SPAWNER);
    ItemMeta namespawn = mobspawn.getItemMeta();
    namespawn.setDisplayName(args[0].toUpperCase());
    mobspawn.setItemMeta(namespawn);
    p.getInventory().addItem(new ItemStack[] { mobspawn });
    p.sendMessage("giving mob spawner of "+args[0]);
 return true;
		}p.sendMessage("Mobs: zombie,skeleton,horse,villager,guardian,snowman,mushroomcow,cow,sheep,pig,chicken,magma_cube,enderman,cave_spider,giant,iron_golem,endermite,creeper,rabbit,silverfish,squid,wolf,witch,ocelot,slime,bat,pig_zombie,spider,ghast");
		return true;
	  }}
  return false;
}

}
