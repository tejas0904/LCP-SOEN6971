package ca.concordia.soen6461.lcp.controller;

public interface IInitGame {
	public void initBuilding();
	public void initFloors();
	public void initRooms();
	public void initFurniture();
	public void initOccupant();
	public void run();
}
