package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;



public class Medic extends Hero {
	//Heal amount  attribute - quiz idea
	

	public Medic(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
		
		
	}

	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException {
		if(!(this.getTarget() instanceof Hero))throw new InvalidTargetException();
		if(!(isAdjacent(this.getTarget(),this)))throw new InvalidTargetException();
			this.getTarget().setCurrentHp(this.getTarget().getCurrentHp());	
			super.useSpecial();
	}
	


}
