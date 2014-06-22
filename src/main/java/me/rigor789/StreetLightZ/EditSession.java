package me.rigor789.StreetLightZ;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Created by rigor789 on 2014.06.22..
 */
public class EditSession {

    private Player player;
    private ArrayList<Location> blocks;

    public EditSession(Player player) {
        this.player = player;
        this.blocks = new ArrayList<Location>();
    }

    public ArrayList<Location> getBlocks() {
        return blocks;
    }

    public boolean hasBlock(Location location) {
        return blocks.contains(location);
    }

    public boolean hasBlock(Block block) {
        return hasBlock(block.getLocation());
    }

    public void addBlock(Location location) {
        if(!blocks.contains(location))
            blocks.add(location);
    }

    public void addBlock(Block block) {
        this.addBlock(block.getLocation());
    }

    public void removeBlock(Location location) {
        if(blocks.contains(location))
            blocks.remove(location);
    }

    public void removeBlock(Block block) {
        this.removeBlock(block.getLocation());
    }

    public Player getPlayer() {
        return player;
    }

}
