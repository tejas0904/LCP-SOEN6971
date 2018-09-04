package modelIMPL; // Definitely a wrong name!

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.IBuilding;
import model.IDisplayable;
import model.ITile;

public class Building extends Displayable implements IBuilding { // Aren't all Building Displayable too?

	private List<IDisplayable> listOfOccupants;
	private List<IDisplayable> listOfPets;
	private List<IDisplayable> listOfFloors;
	private ITile[][] listOfTiles; // This variable and the next seem very technical and low level compared to the previous ones, are they needed?
	private Integer numOfFloors;

	public Building(){
		super(); // Not necessary
		this.listOfFloors = new ArrayList<IDisplayable>();
		this.listOfOccupants = new ArrayList<IDisplayable>();
		this.listOfPets = new ArrayList<IDisplayable>();
		this.listOfTiles = new ITile[21][21];	/* TBD */ // Why these magic numbers???
		this.numOfFloors = 0;
	}
	
	@Override
	public void addOccupant(IDisplayable occupant) {
		this.listOfOccupants.add(occupant);
	}

	@Override
	public void removeOccupant(IDisplayable occupant) {
		this.listOfOccupants.remove(occupant);
	}

	@Override
	public Iterator<IDisplayable> getOccupants() {
		return this.listOfOccupants.iterator();
	}

	@Override
	public void addPet(IDisplayable pet) {
		this.listOfPets.add(pet);
	}

	@Override
	public void removePet(IDisplayable pet) {
		this.listOfPets.remove(pet);
	}

	@Override
	public Iterator<IDisplayable> getPets() {
		return this.listOfPets.iterator();
	}

	@Override
	public Integer getNumFloors() {
		return this.numOfFloors;
	}

	@Override
	public void addFloor(IDisplayable floor) {
		this.listOfFloors.add(floor);
		numOfFloors += 1;
	}

	@Override
	public void removeFloor(IDisplayable floor) {
		this.listOfFloors.remove(floor);
	}

	@Override
	public Iterator<IDisplayable> getFloors() {
		return this.listOfFloors.iterator();
	}

	@Override
	public void initTiles(ITile[][] tiles) {
		for(int i = 0; i < 21; i++) {
			for(int j = 0; j < 21; j++) {
				this.listOfTiles[i][j] = tiles[i][j]; // Why here?
			}
		}
	}

	@Override
	public ITile[][] getTiles() {
		return this.listOfTiles;
	}
}
