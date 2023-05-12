package model.characters;

import java.awt.Point;

import model.world.CharacterCell;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import engine.Game;

public abstract class Character {
	private String name;
	private Point location;
	private int maxHp;
	private int currentHp;
	private int attackDmg;
	private Character target;

	
	public Character() {
	}
	

	public Character(String name, int maxHp, int attackDmg) {
		this.name=name;
		this.maxHp = maxHp;
		this.currentHp = maxHp;
		this.attackDmg = attackDmg;
	}
		
	public Character getTarget() {
		return target;
	}

	public void setTarget(Character target) {
		this.target = target;
	}
	
	public String getName() {
		return name;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		if(currentHp < 0) 
			this.currentHp = 0;
		else if(currentHp > maxHp) 
			this.currentHp = maxHp;
		else 
			this.currentHp = currentHp;
	}

	public int getAttackDmg() {
		return attackDmg;
	}
	
	public void attack() throws NotEnoughActionsException,InvalidTargetException{

		if(this.getTarget() instanceof Zombie){
			Zombie target = (Zombie)this.getTarget();
			if(isAdjacent(target, this)){
				target.setCurrentHp(target.getCurrentHp()-this.getAttackDmg());
				target.defend(this);
				if(target.getCurrentHp()<=0) {
					target.onCharacterDeath();
				}
			}
			else throw new InvalidTargetException();
		}
		if(this.getTarget() instanceof Hero){
			Hero target = (Hero)this.getTarget();
			if(isAdjacent(target, this)){
				target.setCurrentHp(target.getCurrentHp()-this.getAttackDmg());
				target.defend(this);
				if(target.getCurrentHp()<=0) {
					target.onCharacterDeath();
				}
			}
			else throw new InvalidTargetException();
		}
		if(!(this.getTarget() instanceof Hero)&&!(this.getTarget() instanceof Zombie))
		throw new InvalidTargetException();
	}
	
	public void defend(Character c){
		c.setCurrentHp(c.getCurrentHp()-(this.getAttackDmg())/2);
		
		c.onCharacterDeath();
	}
	
	public void onCharacterDeath(){		
		if(this.getCurrentHp()<=0) {
			Game.map[this.getLocation().x][this.getLocation().y] = new CharacterCell(null);
		}
	}
	public Boolean isAdjacent(Character char1, Character char2){
		int x1 =char1.getLocation().x; int x2 = char2.getLocation().x;
		int y1 =char1.getLocation().y; int y2 = char2.getLocation().y;
		return ((x1 == x2)&&((y2+1<=14)&&(y1 == y2 + 1)||((y2-1>=0)&&(y1 == y2 - 1)))||
				((y1 == y2)&&((x2+1<=14)&&(x1 == x2 + 1)||((y2-1>=0)&&(x1 == x2 - 1))))||
				((x2+1<=14)&&(x1 == x2+1)&&((y2+1<=14)&&(y1 == y2 + 1)||((y2-1>=0)&&(y1 == y2 - 1))))||
				((x2-1>=0)&&(x1 == x2-1)&&((y2+1<=14)&&(y1 == y2 + 1)||((y2-1>=0)&&(y1 == y2 - 1)))));
	}

}
