package model.collectibles;
import model.characters.Hero;



public class Vaccine implements Collectible {

	public Vaccine() {
		
	}

	public void pickUp(Hero h) {
		h.addVaccine(this);
	}

	public void use(Hero h) {
		h.removeVaccine(this);
	}

}
