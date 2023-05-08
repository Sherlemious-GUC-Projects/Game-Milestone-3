package model.collectibles;
import model.characters.Hero;



public class Supply implements Collectible  {

	

	
	public Supply() {
		
	}

	public void pickUp(Hero h) {
		h.addSupply(this);
	}

	public void use(Hero h) {
		h.removeSupply(this);
	}


}
