package model.collectibles;



public class Supply implements Collectible  {

	

	
	public Supply() {
		
	}

	public pickUp(Hero h) {
		h.supplyInventory.add(this);
	}

	public void use(Hero h) {
		h.supplyInventory.remove(this);
	}


}
