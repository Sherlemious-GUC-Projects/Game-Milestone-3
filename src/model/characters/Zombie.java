package model.characters;

import java.awt.Point;

import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;



public class Zombie extends Character {
	public static int ZOMBIES_COUNT = 1;
	
	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT, 40, 10);
		ZOMBIES_COUNT++;
	}
	public void onCharacterDeath(){
			Game.map[this.getLocation().x][this.getLocation().y] = new CharacterCell(null);
			Game.zombies.remove(this);
			int x; int y;
			do{
				x = (int)(Math.random()*15);
				y = (int)(Math.random()*15);
			}while((Game.map[x][y] instanceof CollectibleCell)||(Game.map[x][y] instanceof TrapCell)|| ((CharacterCell) Game.map[x][y]).getCharacter() instanceof Zombie|| (((CharacterCell) Game.map[x][y]).getCharacter() instanceof Hero));
			Zombie s = new Zombie();
			Game.zombies.add(s);
			Game.map[x][y]=new CharacterCell(s);
			Point p = new Point(x,y);
			s.setLocation(p);	
			super.onCharacterDeath();
		
	}
	public void attack() throws NotEnoughActionsException, InvalidTargetException{
		int j =this.getLocation().x; int i = this.getLocation().y;
		boolean flag = false;
		for(int m=-1;m<=1;m++){
			for(int n=-1;n<=1;n++ ){
				if((!flag)&&(n+i>=0) && n+i<=14 &&(m+j>=0)&& m+j<=14 && Game.map[j+m][i+n] instanceof CharacterCell && ((CharacterCell) (Game.map[j+m][i+n])).getCharacter() instanceof Hero){
				  Hero h=(Hero)(((CharacterCell) (Game.map[j+m][i+n])).getCharacter());
				  this.setTarget(h);
				  super.attack();
				  flag = true;
				}
			}
		}
	}
}           


