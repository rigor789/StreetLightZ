package me.rigor789.StreetLightZ;

import org.bukkit.plugin.java.JavaPlugin;

public class StreetLightZ extends JavaPlugin{

    @Override
	public void onDisable() {
		getLogger().info("[StreetLightZ] StreetLightZ plugin succesfully disabled!");
	}

    @Override
	public void onEnable() {
        getConfig().options().copyDefaults(true);
        this.getServer().getPluginManager().registerEvents(new BlockListener(), this);
		getCommand("StreetLightZ").setExecutor(new CmdExecutor());
		getLogger().info("[StreetLightZ] StreetLightZ plugin succesfully enabled!");
	}
	
//	public static void TimeChecker() {
//		long Time = Bukkit.getServer().getWorld("world").getTime();
//
//		boolean invert = false;
//		if (LightsOnTime > LightsOffTime){
//			int tempOff = LightsOffTime ;
//			LightsOffTime= LightsOnTime ;
//			LightsOnTime = tempOff;
//			invert = true;
//		}
//		boolean triggerTime = false;
//		if (Time > LightsOnTime && Time < LightsOffTime){
//			triggerTime = true;
//		}
//		if((triggerTime && !invert) || (!triggerTime && invert)){
//		// turn lights off
//			for(Location blockloc : lights){
//				Block block = Bukkit.getServer().getWorld("world").getBlockAt(blockloc);
//				block.setType(Material.REDSTONE_TORCH_OFF);
//			}
//		}else{
//		// turn lights on
//			for(Location blockloc : lights){
//				Block block = Bukkit.getServer().getWorld("world").getBlockAt(blockloc);
//				block.setType(Material.TORCH);
//			}
//		}
//	}
}
