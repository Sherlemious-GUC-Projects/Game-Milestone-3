package model.collectibles;

import java.awt.Point;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import model.characters.Hero;
import model.characters.Zombie;
import model.world.CharacterCell;



public class Vaccine implements Collectible {
    public static int Count;
	public Vaccine() {
		
	}

	public void pickUp(Hero h) {
		h.getVaccineInventory().add(this);	
	}

	public void use(Hero h) throws NoAvailableResourcesException, InvalidTargetException {
		if(!(h.getTarget() instanceof Zombie)) throw new InvalidTargetException();
		if(h.getVaccineInventory().size()==0) throw new NoAvailableResourcesException();
		Point p = new Point(h.getTarget().getLocation().x,h.getTarget().getLocation().y);
		Game.zombies.remove(h.getTarget());
		Game.availableHeroes.get(0).setLocation(p);
		Game.map[h.getTarget().getLocation().x][h.getTarget().getLocation().y]= new CharacterCell(Game.availableHeroes.get(0));
		Game.heroes.add(Game.availableHeroes.remove(0));
		h.getVaccineInventory().remove(this);
		Count++;
		}
		
	}
