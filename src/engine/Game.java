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
	
	public static Cell [][] map =new Cell[15][15]; ;
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
		}while(Game.map[x][y] instanceof CollectibleCell || Game.map[x][y] instanceof TrapCell || ((CharacterCell) Game.map[x][y]).getCharacter() instanceof Zombie || ((CharacterCell) Game.map[x][y]).getCharacter() instanceof Hero);
		return new Point(x,y);
	}

	public static void startGame(Hero h){


		// initialize Game.map with empty character cells
		for(int i =0;i<15;i++){
			for(int j =0;j<15;j++){
				Game.map[j][i]=new CharacterCell(null);
			}
		}
		// Add vaccines to the Game.map
		for(int i=0; i<5; i++){
			Point p = getEmptyLocation();
			Game.map[p.x][p.y]=new CollectibleCell(new Vaccine());
		}

		// Add supplies to the Game.map
		for(int i=0; i<5; i++){
			Point p = getEmptyLocation();
			Game.map[p.x][p.y]=new CollectibleCell(new Supply());
		}
		// Add trap cells to the Game.map
		for(int i=0; i<5; i++){
			Point p = getEmptyLocation();
			Game.map[p.x][p.y]=new TrapCell();
		}
		// Add zombies to the Game.map
		int x,y;
		for(int i=0; i<10; i++){
			do{
				x = (int)(Math.random()*14)+1;
				y = (int)(Math.random()*14)+1;
			}while((Game.map[x][y] instanceof CollectibleCell)||(Game.map[x][y] instanceof TrapCell)|| (((CharacterCell) Game.map[x][y]).getCharacter() instanceof Zombie));
			Zombie newZombie = new Zombie();
			zombies.add(newZombie);
			Game.map[x][y]=new CharacterCell(newZombie);
			Point p = new Point(x,y);
			newZombie.setLocation(p);
		}
		Game.map[0][0]=new CharacterCell(h);
		heroes.add(h);
		availableHeroes.remove(h);
		Point p = new Point(0,0);
		h.setLocation(p);
		Game.map[0][1].setVisible(true);
		Game.map[1][1].setVisible(true);
		Game.map[1][0].setVisible(true);
		Game.map[0][0].setVisible(true);
		
	}

	public static int getVaccinesOnmap(){
		int count = 0;
		for(int i =0;i<15;i++){
			for(int j =0;j<15;j++){
				if(Game.map[j][i] instanceof CollectibleCell && ((CollectibleCell) (Game.map[j][i])).getCollectible() instanceof Vaccine)
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
		return ((getVaccinesOnmap() == 0) && heroes.size()>=5 && getVaccinesInInventory()==0 );
	}
	public static boolean checkGameOver(){
		// Check if all vaccines have been collected and used when there are still zombies on the Game.map
		if((getVaccinesOnmap()+getVaccinesInInventory())==0)
			return true;

		return heroes.isEmpty() || checkWin() || availableHeroes.isEmpty() && heroes.get(0).getVaccineInventory().isEmpty() ;
	}
	public static void endTurn() throws NotEnoughActionsException, InvalidTargetException{
		int x;int y;
		do{
			x = (int)(Math.random()*15);
			y = (int)(Math.random()*15);
		}while((Game.map[x][y] instanceof CollectibleCell)||(Game.map[x][y] instanceof TrapCell)|| (((CharacterCell) Game.map[x][y]).getCharacter() instanceof Zombie)|| (((CharacterCell) Game.map[x][y]).getCharacter() instanceof Hero));
		Zombie s = new Zombie();
		zombies.add(s);
		Game.map[x][y]=new CharacterCell(s);
		Point p = new Point(x,y);
		s.setLocation(p);
		
		for(int i =0;i<15;i++){
			for(int j =0;j<15;j++){
				Game.map[j][i].setVisible(false);}}
		
		for(int i =0;i<zombies.size();i++){
			Zombie z = zombies.get(i);
	    	z.attack();
	    	z.setTarget(null);
		}
		for(int c =0;c<heroes.size();c++){
					Hero h = heroes.get(c);
					int i =h.getLocation().y; int j =h.getLocation().x;
					h.setActionsAvailable(h.getMaxActions());
					h.setSpecialAction(false);
					h.setTarget(null);
					for(int m=-1;m<=1;m++){
						for(int n=-1;n<=1;n++ ){
							if((n+i>=0) && n+i<=14 &&(m+j>=0)&& m+j<=14) {
								Game.map[j+m][i+n].setVisible(true);
					}
				}
			}
		}
	}
}
