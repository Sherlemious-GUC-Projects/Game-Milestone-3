package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;


public class Fighter extends Hero{

	
	public Fighter(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
		
	}

	@Override
	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException {
		super.useSpecial();
			this.setSpecialAction(true);		
	}
		
	}

	

	
	
	
	


