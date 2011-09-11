package me.rigi.StreetLightZ;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class StreetLightZReaderWriter {
	static String mainDirectory = "plugins/StreetLightZ";
	static File lights = new File(mainDirectory + File.separator + "coordinates.dat");
	
	public void createDir() {
		new File(mainDirectory).mkdir();	
			if (!lights.exists()) {				
			try {
				lights.createNewFile();					
				} catch (IOException ex) {
				ex.printStackTrace();
			}
			}
		}

	public void ReadAll() {
		 try{
			 FileReader fstream = new FileReader(lights);			
				BufferedReader in = new BufferedReader(fstream);
				String input=in.readLine();
				String[] items = input.split(";");
				 for(String block : items){
					 String[] item = block.split(",");
					 int x = Integer.parseInt(item[0]);
					 int y = Integer.parseInt(item[1]);
					 int z = Integer.parseInt(item[2]);
				World world = Bukkit.getServer().getWorld("World");
				Block blockAt = world.getBlockAt(x,y,z);
				StreetLightZMain.lights.add(blockAt.getLocation());
				 }
			 }catch (Exception e){//Catch exception if any
				  System.err.println("Error: " + e.getMessage());
				  }
		
	}

	public static void WriteAll() {
		
		ArrayList<Location> blocks = StreetLightZMain.lights;
		ArrayList<String> towrite =new ArrayList<String>();
	for(Location block :blocks){
		int x= block.getBlockX();
		int y= block.getBlockY();
		int z= block.getBlockZ();
		towrite.add(x+","+y+","+z+";");
	}
			
		boolean append = false;
		try{
			  FileWriter fstream = new FileWriter(lights, append);
			  PrintWriter out = new PrintWriter(fstream, true); 
			  for(String coordinate : towrite){
				  out.write(coordinate);		 
			  }
						  
			  out.close(); //Close the output stream
			  }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
}}

	
