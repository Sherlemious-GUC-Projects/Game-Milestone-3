package model.characters;

import java.awt.Point;
import java.util.ArrayList;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;


public abstract class Hero extends Character {
	

		private int actionsAvailable;
		private int maxActions;
		private ArrayList<Vaccine> vaccineInventory;
		private ArrayList<Supply> supplyInventory;
		private boolean specialAction;
	
		
		public Hero(String name,int maxHp, int attackDmg, int maxActions) {
			super(name,maxHp, attackDmg);
			this.maxActions = maxActions;
			this.actionsAvailable = maxActions;
			this.vaccineInventory = new ArrayList<Vaccine>();
			this.supplyInventory=new ArrayList<Supply>();
			this.specialAction=false;
		
		}

		
	


		public boolean isSpecialAction() {
			return specialAction;
		}



		public void setSpecialAction(boolean specialAction) {
			this.specialAction = specialAction;
		}



		public int getActionsAvailable() {
			return actionsAvailable;
		}



		public void setActionsAvailable(int actionsAvailable) {
			this.actionsAvailable = actionsAvailable;
		}



		public int getMaxActions() {
			return maxActions;
		}



		public ArrayList<Vaccine> getVaccineInventory() {
			return vaccineInventory;
		}


		public ArrayList<Supply> getSupplyInventory() {
			return supplyInventory;
		}
		
		public void attack()throws NotEnoughActionsException, InvalidTargetException {
			if(this.getTarget() instanceof Hero || this.getTarget()==null){
				throw new InvalidTargetException("can't attack a hero");
			}
			if(getActionsAvailable()>0){
				super.attack();
				if(!this.isSpecialAction())
			    this.setActionsAvailable(this.getActionsAvailable()-1);
			    }
			else
				throw new NotEnoughActionsException("there is not enough action point to start an attack");
					
				}
		
		
		
		public void move(Direction d) throws MovementException, NotEnoughActionsException{
			boolean flag = true;
			Point oldLocation = new Point(this.getLocation());
			Point location = new Point(this.getLocation());
			if(this.getActionsAvailable()==0)throw new NotEnoughActionsException(); 
			switch(d) {
		      case UP:
                  location.x = location.x +1 ;
		        break;
		      case DOWN:
	                 location.x = location.x -1 ;
		        break;
		      case LEFT:
	                 location.y = location.y -1 ;
	                 break;
		      case RIGHT:
	                 location.y = location.y +1 ;
		        break;}
			if(location.y<0||location.x<0||location.y>14||location.x>14)throw new MovementException();
			
			
			if(Game.map[location.x][location.y] instanceof CollectibleCell){				
				if(((CollectibleCell) Game.map[location.x][location.y]).getCollectible() instanceof Supply){
					((Supply) ((CollectibleCell) Game.map[location.x][location.y]).getCollectible()).pickUp(this);}
				if(((CollectibleCell) Game.map[location.x][location.y]).getCollectible() instanceof Vaccine){
				((Vaccine) ((CollectibleCell) Game.map[location.x][location.y]).getCollectible()).pickUp(this);
		    }}
			
			
			if((Game.map[location.x][location.y] instanceof CharacterCell)&& !(((CharacterCell) Game.map[location.x][location.y]).getCharacter() == null)){
					throw new MovementException("your path is blocked by someone");
			}
			if(Game.map[location.x][location.y] instanceof TrapCell){
				this.setCurrentHp(this.getCurrentHp() - ((TrapCell) (Game.map[location.x][location.y])).getTrapDamage());
				if(this.getCurrentHp()==0)this.onCharacterDeath();
				flag = false;
		}
	    	  Game.map[location.x][location.y]=new CharacterCell(this);
	    	  Game.map[oldLocation.x][oldLocation.y]=new CharacterCell(null);
	    	  this.setLocation(location);
	    	  this.setActionsAvailable(this.getActionsAvailable()-1);
	    	  for(int i=-1;i<=1;i++){
	    		  for(int j=-1;j<=1;j++ ){
	    	    	  if(flag&&location.x+i>=0&&location.x+i<=14&&location.y+i>=0&&location.y+i<=14)Game.map[location.x+i][location.y+j].setVisible(true);
	    		  }
	    	  }

		}
		public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException{
			if(this.getSupplyInventory().isEmpty())throw new NoAvailableResourcesException();
			this.getSupplyInventory().get(0).use(this);
		}
		
		public void cure()throws NoAvailableResourcesException, InvalidTargetException, NotEnoughActionsException{	
			if(this.getActionsAvailable()<=0)throw new NotEnoughActionsException();
			if(this.getTarget()==null||!(this.getTarget() instanceof Zombie)) throw new InvalidTargetException();
			if(!(isAdjacent(this.getTarget(),this))) throw new InvalidTargetException();
			    this.setActionsAvailable(this.getActionsAvailable()-1);
				Vaccine v = this.getVaccineInventory().get(0);
				v.use(this);			
		}
		public void onCharacterDeath(){
			super.onCharacterDeath();
			Game.heroes.remove(this);
		}
		}


		

	
