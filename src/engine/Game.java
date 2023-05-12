package engine;

import java.awt.Point;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.characters.Character;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class Game {
	
	public static Cell [][] map ;
	public static ArrayList <Hero> availableHeroes = new ArrayList<Hero>();
	public static ArrayList <Hero> heroes =  new ArrayList<Hero>();
	public static ArrayList <Zombie> zombies =  new ArrayList<Zombie>();
	public static int usedVaccinesCount = 0;

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

	public static Point getEmptyLocation() {
		int y; int x;
		do{
			x = (int)(Math.random()*14)+1;
			y = (int)(Math.random()*14)+1;
		}while(map[x][y] instanceof CollectibleCell || map[x][y] instanceof TrapCell);
		return new Point(x,y);
	}

	public static void spawnZombie(){
		Point p = getEmptyLocation();
		Zombie z = new Zombie();
		zombies.add(z);
		map[p.x][p.y]=new CharacterCell(z);
		z.setLocation(p);
	}

	public static void startGame(Hero h){
		map=new Cell[15][15];

		// initialize map with empty character cells
		for(int i =0;i<15;i++){
			for(int j =0;j<15;j++){
				map[j][i]=new CharacterCell(null);
			}
		}
		// Add collectibles to the map
		for(int i=0; i<5; i++){
			Point p = getEmptyLocation();
			map[p.x][p.y]=new CollectibleCell(new Vaccine());
		}

		// Add supplies to the map
		for(int i=0; i<5; i++){
			Point p = getEmptyLocation();
			map[p.x][p.y]=new CollectibleCell(new Supply());
		}
		// Add trap cells to the map
		for(int i=0; i<5; i++){
			Point p = getEmptyLocation();
			map[p.x][p.y]=new TrapCell();
		}
		// Add zombies to the map
		int x,y;
		for(int i=0; i<10; i++){
			spawnZombie();
		}
		map[0][0]=new CharacterCell(h);
		heroes.add(h);
		availableHeroes.remove(h);
		Point p = new Point(0,0);
		h.setLocation(p);
		map[0][1].setVisible(true);
		map[1][1].setVisible(true);
		map[1][0].setVisible(true);
		map[0][0].setVisible(true);
		
	}

	public static int getVaccinesOnMap(){
		int count = 0;
		for(int i =0;i<15;i++){
			for(int j =0;j<15;j++){
				if(map[j][i] instanceof CollectibleCell && ((CollectibleCell) (map[j][i])).getCollectible() instanceof Vaccine)
					count++;
			}
		}
		return count;
	}
	public static int getVaccinesInInventory(){
		int count = 0;
		for(Hero h: heroes){
			count+=h.getVaccineInventory().size();
		}
		return count;
	}

	public static boolean checkWin(){
		// Count Vaccines in inventory for all heroes
		return ((getVaccinesOnMap() == 0) && heroes.size()>=5 && getVaccinesInInventory()==0 );
	}
	public static boolean checkGameOver(){
		// Check if all vaccines have been collected and used when there are still zombies on the map
		if((getVaccinesOnMap()+getVaccinesInInventory())==0)
			return true;

		return heroes.isEmpty() || checkWin() || availableHeroes.isEmpty() && heroes.get(0).getVaccineInventory().isEmpty() ;
	}
	public static void endTurn() throws NotEnoughActionsException, InvalidTargetException{
		Game.spawnZombie();

		for(int i =0;i<15;i++){
			for(int j =0;j<15;j++){
				map[j][i].setVisible(false);}}
		for(int i =0;i<15;i++){
			for(int j =0;j<15;j++){
				if(map[j][i] instanceof CharacterCell && ((CharacterCell) (map[j][i])).getCharacter() instanceof Zombie){
					Zombie z = (Zombie)(((CharacterCell) (map[j][i])).getCharacter());
			    	z.attack();
			    	z.setTarget(null);
				}
				if(map[j][i] instanceof CharacterCell && ((CharacterCell) (map[j][i])).getCharacter() instanceof Hero){
					Hero h = (Hero)(((CharacterCell) (map[j][i])).getCharacter());
					h.setActionsAvailable(h.getMaxActions());
					h.setSpecialAction(false);
					h.setTarget(null);
					for(int m=-1;m<=1;m++){
						for(int n=-1;n<=1;n++ ){
							if((n+i>=0) && n+i<=14 &&(m+j>=0)&& m+j<=14) {
								map[j+m][i+n].setVisible(true);
							}
						}
					}
				}
			}
		}
	}
}
