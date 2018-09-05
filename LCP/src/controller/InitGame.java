package controller;

import factory.Factory;
import model.IDisplayContent;
import model.IDisplayable;
import modelIMPL.DisplayContent;

public class InitGame implements IInitGame {
	private final Integer NUM_FLOORS = 1; // Respect Java naming conventions.
	private IDisplayable building; // Why the "InitGame" (emphasis on Init) would know a Building? A Game could know a Building.
	
	@Override
	public void initBuilding() {
		IDisplayContent buildingDisplay = new DisplayContent();
		buildingDisplay.setDisplay("Building");
		this.building = Factory.getInstance().getBuilding(buildingDisplay);
	}

	@Override
	public void initFloors() {
		// Why are most of these initXXX() methods empty? Clean up your code!
	}

	@Override
	public void initRooms() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initFurniture() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initOccupant() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runAI() {
		// TODO Auto-generated method stub
		// Why the most important method is empty? How does the game starts then?	
	}

}
