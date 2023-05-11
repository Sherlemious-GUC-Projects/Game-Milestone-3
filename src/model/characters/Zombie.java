package model.characters;

import java.awt.Point;

import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;



public class Zombie extends Character {
	static int ZOMBIES_COUNT = 1;
	
	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT, 40, 10);
		ZOMBIES_COUNT++;
	}
	public void onCharacterDeath(){
		if(this.getCurrentHp()<=0){

			Game.zombies.remove(this);
			int x; int y;
			do{
				x = (int)(Math.random()*14)+1;
				y = (int)(Math.random()*14)+1;
			}while((Game.map[y][x] instanceof CollectibleCell)||(Game.map[y][x] instanceof TrapCell)|| ((CharacterCell) Game.map[y][x]).getCharacter() instanceof Zombie|| (((CharacterCell) Game.map[y][x]).getCharacter() instanceof Hero));
			Zombie s = new Zombie();
			Game.zombies.add(s);
			Game.map[y][x]=new CharacterCell(s);
			Point p = new Point(y,x);
			s.setLocation(p);
			super.onCharacterDeath();
		}
	}
	public void attack() throws NotEnoughActionsException, InvalidTargetException{
		int i =this.getLocation().x; int j = this.getLocation().y;
		boolean flag = false;
		for(int m=-1;m<=1;m++){
			for(int n=-1;n<=1;n++ ){
				if((!flag)&&(n+i>=0) && n+i<=14 &&(m+j>=0)&& m+j<=14&& Game.map[j+m][i+n] instanceof CharacterCell && ((CharacterCell) (Game.map[j+m][i+n])).getCharacter() instanceof Hero){
				  Hero h=(Hero)(((CharacterCell) (Game.map[j+m][i+n])).getCharacter());
				  this.setTarget(h);
				  super.attack();
				  flag = true;
				}
			}
		}
	}
}



