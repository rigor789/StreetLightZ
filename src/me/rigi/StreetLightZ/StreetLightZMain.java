package me.rigi.StreetLightZ;

import java.util.ArrayList;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class StreetLightZMain extends JavaPlugin{

	public static ArrayList<Player> players = new ArrayList<Player>();
	public static ArrayList<Location> lights = new ArrayList<Location>();
	private StreetLightZBlockListener bListener = new StreetLightZBlockListener(this);
	
	Logger Log = Logger.getLogger("Minecraft");
	public static Configuration config;
	public static int LightsOffTime;
	public static int LightsOnTime;
	//@Override
	public void onDisable() {
		Log.info("[StreetLightZ] StreetLightZ plugin succesfully disabled!");
	}
	//@Override
	public void onEnable() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_PLACE, bListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_BREAK, bListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.REDSTONE_CHANGE, bListener, Event.Priority.Normal, this);
		StreetLightZReaderWriter readerwriter = new StreetLightZReaderWriter();
		readerwriter.createDir();	
		readerwriter.ReadAll();	
		Log.info("[StreetLightZ] Loaded "+ lights.size()+" lights!");
		config = getConfiguration();
		LightsOffTime = config.getInt("LightsOffTime", 0);	
		LightsOnTime = config.getInt("LightsOnTime", 13000);
		config.save();		
		Runnable timelistener = new StreetLightZRunnable();
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, timelistener, 10,10);
		getCommand("StreetLightZ").setExecutor(new StreetLightZCmdExecutor());
		
		Log.info("[StreetLightZ] StreetLightZ plugin succesfully enabled!");
		

		
	}
	
	public static void TimeChecker(){
		long Time = Bukkit.getServer().getWorld("world").getTime();
		
		boolean invert = false;
		if (LightsOnTime > LightsOffTime){
			int tempOff = LightsOffTime ;
			LightsOffTime= LightsOnTime ;
			LightsOnTime = tempOff;
			invert = true;
		}
		boolean triggerTime = false;
		if (Time > LightsOnTime && Time < LightsOffTime){
			triggerTime = true;
		}
		if((triggerTime && !invert) || (!triggerTime && invert)){
		// turn lights off
			for(Location blockloc : lights){
				Block block = Bukkit.getServer().getWorld("world").getBlockAt(blockloc);
				block.setType(Material.REDSTONE_TORCH_OFF);
			}

		}else{
		// turn lights on
			for(Location blockloc : lights){
				Block block = Bukkit.getServer().getWorld("world").getBlockAt(blockloc);
				block.setType(Material.TORCH);
			}
			
			
		}
	}
}
