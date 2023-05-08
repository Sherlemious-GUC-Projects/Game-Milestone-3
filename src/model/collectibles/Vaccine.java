package model.collectibles;



public class Vaccine implements Collectible {

	public Vaccine() {
		
	}

	public pickUp(Hero h) {
		h.vaccineInventory.add(this);
	}

	public void use(Hero h) {
		h.vaccineInventory.remove(this);
	}

}
