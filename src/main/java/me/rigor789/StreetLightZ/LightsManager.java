package me.rigor789.StreetLightZ;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;

/**
 * Created by rigor789 on 2014.06.22..
 */
public class LightsManager {

    private ArrayList<Location> ligts;

    private LightsManager() {
        ligts = new ArrayList<Location>();
    }

    private static LightsManager instance;

    public static LightsManager getInstance() {
        if(instance == null) {
            instance = new LightsManager();
        }
        return instance;
    }

    public ArrayList<Location> getLigts() {
        return ligts;
    }

    public void setLigts(ArrayList<Location> ligts) {
        this.ligts = ligts;
    }

    public void addLight(Location light) {
        if(!ligts.contains(light))
            ligts.add(light);
    }

    public void addLights(ArrayList<Location> ligts) {
        for(Location light : ligts) {
            addLight(light);
        }
    }

    public boolean hasLight(Location location) {
        return ligts.contains(location);
    }

    public boolean hasLight(Block block) {
        return hasLight(block.getLocation());
    }
}
