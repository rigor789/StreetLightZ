package me.rigor789.StreetLightZ;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.material.Torch;

import java.util.ArrayList;

public class BlockListener implements Listener {

    private ArrayList<BlockFace> faces;

    public BlockListener() {
        faces = new ArrayList<BlockFace>();
        faces.add(BlockFace.NORTH);
        faces.add(BlockFace.EAST);
        faces.add(BlockFace.SOUTH);
        faces.add(BlockFace.WEST);
        faces.add(BlockFace.UP);
    }

    @EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlockPlaced();	
        if (!(block.getType() == Material.TORCH || block.getType() == Material.REDSTONE_TORCH_OFF)) return;
        if (!SessionManager.getInstance().hasSession(player)) return;

        Location blockLocation = block.getLocation();
        SessionManager.getInstance().getSession(player).addBlock(blockLocation);
        player.sendMessage(ChatColor.YELLOW + "Torch added!");
    }

    @EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player           = event.getPlayer();
		Block block             = event.getBlock();
        LightsManager manager   = LightsManager.getInstance();

        if(!SessionManager.getInstance().hasSession(player)) return;

		for (BlockFace face : faces){
		    Block attached = block.getRelative(face);
            if (!manager.hasLight(attached)) continue;
            if (!(attached.getType() == Material.TORCH || attached.getType() == Material.REDSTONE_TORCH_OFF)) continue;

            BlockFace torchAttachedFace = ((Torch)(attached.getState().getData())).getAttachedFace();
            Block attachedTo = attached.getRelative(torchAttachedFace);

            if (!attachedTo.equals(block)) continue;
            if (!SessionManager.getInstance().hasSession(player)) {
                EditSession session = SessionManager.getInstance().getSession(player);
                attached.setType(Material.TORCH);
                session.removeBlock(attached);
                player.sendMessage(ChatColor.YELLOW + "Torch removed");
            } else {
                player.sendMessage(ChatColor.GOLD + "That block is part of the lighting! Can't remove it!");
                event.setCancelled(true);
            }
        }

		if (!(block.getType() == Material.TORCH || block.getType() == Material.REDSTONE_TORCH_OFF)) return;
        if (!manager.hasLight(block)) return;
        if (SessionManager.getInstance().hasSession(player)){
            EditSession session = SessionManager.getInstance().getSession(player);
            block.setType(Material.TORCH);
            player.sendMessage(ChatColor.YELLOW + "Torch removed");
            session.removeBlock(block);
        }else{
            event.setCancelled(true);
            player.sendMessage(ChatColor.GOLD + "That torch is part of the lighting! Can't remove it!");
        }
    }

    @EventHandler
	public void onBlockRedstoneChange(BlockRedstoneEvent event){
		Block block = event.getBlock();
		if(!LightsManager.getInstance().hasLight(block)) return;
        if(block.getType() != Material.REDSTONE_TORCH_ON ) return;
        event.setNewCurrent(0);
	}
}

