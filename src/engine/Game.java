package engine;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.TrapCell;

public class Game {
	
	public static Cell [][] map ;
	public static ArrayList <Hero> availableHeroes = new ArrayList<Hero>();
	public static ArrayList <Hero> heroes =  new ArrayList<Hero>();
	public static ArrayList <Zombie> zombies =  new ArrayList<Zombie>();
	
	
	
		
	public static void loadHeroes(String filePath)  throws IOException {
		
		
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = br.readLine();
		while (line != null) {
			String[] content = line.split(",");
			Hero hero=null;
			switch (content[1]) {
			case "FIGH":
				hero = new Fighter(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3]));
				break;
			case "MED":  
				hero = new Medic(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3])) ;
				break;
			case "EXP":  
				hero = new Explorer(content[0], Integer.parseInt(content[2]), Integer.parseInt(content[4]), Integer.parseInt(content[3]));
				break;
			}
			availableHeroes.add(hero);
			line = br.readLine();
			
			
		}
		br.close();

		
		
	}
	@org.jetbrains.annotations.Contract("_ -> new")
	private static Point getAvailablePoint(ArrayList<Point> occupiedCells) {
		int RandomX = (int) (Math.random() * 15);
		int RandomY = (int) (Math.random() * 15);
		// Check if the cell is empty
		while(occupiedCells.contains(new Point(RandomX, RandomY))) {
			RandomX = (int) (Math.random() * 15);
			RandomY = (int) (Math.random() * 15);
		}
		return new Point(RandomX, RandomY);
	}
	public static void startGame(Hero h) {
		heroes.add(h);
		availableHeroes.remove(h);
		ArrayList<Point> occupiedCells = new ArrayList<Point>();

		// Add the hero to the map
		h.setLocation(new Point(0, 0));
		occupiedCells.add(h.getLocation());

		// Add 10 zombies randomly the map
		for(int i=0; i < 10; i++) {
			Zombie newZombie = new Zombie();
			Point newPoint = getAvailablePoint(occupiedCells);
			occupiedCells.add(newPoint);
			newZombie.setLocation(newPoint);
			zombies.add(newZombie);
		}
		// Add 5 traps randomly around the map
		//TODO: Where should the trap location be added?
		for(int i=0; i < 5; i++){
			TrapCell newTrap = new TrapCell();
			Point newPoint = getAvailablePoint(occupiedCells);
			occupiedCells.add(newPoint);
//			newTrap.setLocation(newPoint);

		}
		// Add 5 supplies and 5 vaccines to the hero
		for(int i=0; i < 5; i++) {
			h.addSupply(new Supply());
			h.addVaccine(new Vaccine());
		}
	}



}
