package me.rigi.StreetLightZ;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.material.Torch;

public class StreetLightZBlockListener extends BlockListener {
	@SuppressWarnings("unused")
	private StreetLightZMain plugin;

	public StreetLightZBlockListener(StreetLightZMain plugin) {
		this.plugin = plugin;
	}

	public void onBlockPlace(BlockPlaceEvent event) {
		// on block place
		Player player = event.getPlayer();
		Block block = event.getBlockPlaced();	
			if (block.getType() == Material.TORCH || block.getType() == Material.REDSTONE_TORCH_OFF) {//checks the block type(Torch or RedstoneTorchOff)
			if (StreetLightZMain.players.contains(player)) {
				player.sendMessage(ChatColor.YELLOW+"Torch added!");		
			Location blocklocation = block.getLocation();
			StreetLightZMain.lights.add(blocklocation);
			}}}	
	public void onBlockBreak(BlockBreakEvent event){
		Player player = event.getPlayer();
		Block block = event.getBlock();	
		ArrayList<BlockFace> faces = new ArrayList<BlockFace>();
		faces.add(BlockFace.NORTH);
		faces.add(BlockFace.EAST);
		faces.add(BlockFace.SOUTH);
		faces.add(BlockFace.WEST);
		faces.add(BlockFace.UP);

		for (BlockFace face : faces){
		    Block torch = block.getRelative(face);
		    Location torchLocation = torch.getLocation();
		    if (StreetLightZMain.lights.contains(torchLocation)&& (torch.getType()==Material.TORCH||torch.getType()==Material.REDSTONE_TORCH_OFF)){//&& torch instanceof Torch
		    	BlockFace torchAttachedFace =((Torch)(torch.getState().getData())).getAttachedFace();
		        Block attachedTo = torch.getRelative(torchAttachedFace);
		        if (attachedTo.equals(block)){
		            if(!(StreetLightZMain.players.contains(player))){
		            	//event.setCancelled(true);
			            if(!event.isCancelled()){
			            	player.sendMessage(ChatColor.GOLD+"That block is part of the lighting! Can't remove it!");
			            	event.setCancelled(true);
			            }
		            	//player.sendMessage(ChatColor.GOLD+"That block is part of the lighting! Can't remove it!");
		            }else{		            	
		            		torch.setType(Material.TORCH);	           
		            		StreetLightZMain.lights.remove(torchLocation);
			            	player.sendMessage(ChatColor.YELLOW + "Torch removed");            	
		            	   }
		        		}	
		    		}
				}		
		if (block.getType() == Material.TORCH || block.getType() == Material.REDSTONE_TORCH_OFF)
				{//we are checking that we are removing only block that is listed as a light
			if(StreetLightZMain.lights.contains(block.getLocation())){
				if(StreetLightZMain.players.contains(player)){
					block.setType(Material.TORCH);	
					player.sendMessage(ChatColor.YELLOW + "Torch removed");
					Location blocklocation = block.getLocation();
					StreetLightZMain.lights.remove(blocklocation);
				}else{
					event.setCancelled(true);
					player.sendMessage(ChatColor.GOLD+"That torch is part of the lighting! Can't remove it!");
				}
			}
		}
}
	
	public void onBlockRedstoneChange(BlockRedstoneEvent event){
		Block block = event.getBlock();
		if(StreetLightZMain.lights.contains(block.getLocation())||block.getType()==Material.REDSTONE_TORCH_ON){
			event.setNewCurrent(0);
		}
	}
}

