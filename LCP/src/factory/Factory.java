package factory;

import java.util.ArrayList;

import model.IDisplayContent;
import model.IDisplayable;
import model.IFloor;
import model.IFurniture;
import model.IDisplayableDimension;
import model.IPerson;
import model.IPet;
import model.IRoom;
import model.ITile;
import modelIMPL.Building;
import modelIMPL.Floor;
import modelIMPL.LocationPoints;
import modelIMPL.MoveableFurniture;
import modelIMPL.NonMoveableFurniture;
import modelIMPL.Person;
import modelIMPL.Pet;
import modelIMPL.Room;
import modelIMPL.Tile;

public class Factory {
	
	private static class FactoryUniqueInstanceHolder{
		private static Factory THE_UNIQUE_FACTORY = new Factory();
	}
	
	public static Factory getInstance() {
		return FactoryUniqueInstanceHolder.THE_UNIQUE_FACTORY;
	}
	
	public IDisplayable getBuilding(IDisplayContent buildingDisplay) {
		// Why is this method a getter rather than a "create"?
		Building building = new Building();
		building.setDisplay(buildingDisplay);
		return building;
	}

	public IFloor getFloor(Integer floorNum, IDisplayContent floorDisplay, IDisplayableDimension objTileDetails) {
		// Why use an Integer rather than an int?
		IFloor floor = new Floor();
		floor.setFloorNum(floorNum);
		floor.setDisplay(floorDisplay);
		floor.setTiles(objTileDetails);
		return floor;
	}
	
	public IPerson getPerson(IRoom room, IDisplayContent personDisplay, IDisplayableDimension objTileDetails) {
		// How do we distinguish instances of Person?
		IPerson person = new Person();
		person.setRoom(room);
		person.setDisplay(personDisplay);
		person.setTiles(objTileDetails);
		return person;
	}

	public IPet getPet(IDisplayContent petDisplay, IDisplayableDimension objTileDetails) {
		IPet pet = new Pet();
		pet.setDisplay(petDisplay);
		pet.setTiles(objTileDetails);
		return pet;
	}

	public IRoom getRoom(IDisplayContent roomDisplay, IDisplayableDimension objTileDetails) {
		IRoom room = new Room();
		room.setDisplay(roomDisplay);
		room.setTiles(objTileDetails);
		return room;
	}

	public IFurniture getMoveableFurniture(IDisplayContent furnDisplay, IDisplayableDimension objTileDetails) {
		IFurniture furniture = new MoveableFurniture();
		furniture.setDisplay(furnDisplay);
		furniture.setTiles(objTileDetails);
		return furniture;
	}

	public IFurniture getNonMoveableFurniture(IDisplayContent furnDisplay, IDisplayableDimension objTileDetails) {
		IFurniture furniture = new NonMoveableFurniture();
		furniture.setDisplay(furnDisplay);
		furniture.setTiles(objTileDetails);
		return furniture;
	}
	
	
	/* All Functions for tiles */
	public void initTiles(ArrayList<ITile> tiles, Integer horizontalSize, 
			Integer verticalSize, Integer totalTiles) {
		// Why is this init
		while (tiles.size() < (totalTiles * totalTiles) - 1) {
			addTile(tiles, horizontalSize, verticalSize, totalTiles);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ITile[][] getTiles(Integer horizontalSize, Integer verticalSize, Integer totalTiles) {
		// What is this method for? Why would I want to get the tiles in the Game?
		ITile[][] tiles = null;	
		if(checkTileComposition(horizontalSize, verticalSize, totalTiles) == true) {
			tiles = new ITile[totalTiles][totalTiles];
			ArrayList<ITile> tmpTiles = new ArrayList<ITile>();
			initTiles(tmpTiles, horizontalSize, verticalSize, totalTiles);
			int ctr = 0;
			for(int i = 0; i < totalTiles; i++) {
				for(int j = 0; j < totalTiles; j++) {
					tiles[i][j] = tmpTiles.get(ctr);
					ctr++;
				}
			}
		}
		return tiles;
	}
	
	private void addTile(ArrayList<ITile> tiles, Integer horizontalSize, 
			Integer verticalSize, Integer totalTiles) {
			// Why is this method singular while it seems to add many tiles?
		
		// Why Integers and not int? 
		Integer tileHeight = horizontalSize / totalTiles;
		Integer tileWidth = verticalSize / totalTiles;
		
		if(checkTileComposition(horizontalSize, verticalSize, totalTiles) == false) {
			return;
		}
		
		if(tiles.size() == (totalTiles * totalTiles)) {
			System.out.println("Error: addTile: Maximum possible number of tiles already exist");
			// And it should not return?
		}
		// Why this test?
		if(tiles.size() == 0) {
			ITile tmpTileOne = new Tile();
			tmpTileOne.setLocation(new LocationPoints(tileHeight, tileWidth)); // Should be "LocationPoint", singular
			tmpTileOne.setTileX(0);
			tmpTileOne.setTileY(0);
			tiles.add(tmpTileOne);
			
			for(int i = 0; i < totalTiles - 1; i++) {
				ITile prevTile = tiles.get(tiles.size() - 1);
				ITile tmpTile = new Tile();
				tmpTile.setLocation(new LocationPoints(prevTile.getLocation().getNEPoint(), 
						tileHeight, tileWidth));
				
				tmpTile.setLeft(prevTile);
				prevTile.setRight(tmpTile);
				tmpTile.setTileX(prevTile.getTileX() + 1);
				tmpTile.setTileY(prevTile.getTileY());
				tiles.add(tmpTile);
			}
		}
		else {
			ITile topTile = null;
			if(tiles.size() >= totalTiles) {
				/* There is tiles at top */
				topTile = tiles.get(0);
				while(topTile.getDown() != null) {
					topTile = topTile.getDown();
				}
			}
			else {
				System.out.println("TOP NOT FOUND");
				// Why should a user care about that? What can it do about it anyhow?
			}
			
			for(int i = 0; i < totalTiles; i++) {
				ITile tmpTile = new Tile();
				if(topTile.getTileX() != 0) {
					ITile prevTile = tiles.get(tiles.size() - 1);
					tmpTile.setLeft(prevTile);
					prevTile.setRight(tmpTile);
				}
				
				tmpTile.setUp(topTile);
				topTile.setDown(tmpTile);
				tmpTile.setTileX(topTile.getTileX());
				tmpTile.setTileY(topTile.getTileY() + 1);
				topTile = topTile.getRight();
				tiles.add(tmpTile);
			}
		}
	}

	private Boolean checkTileComposition(Integer horizontalSize, 
			Integer verticalSize, Integer totalTiles) {
		Double tileWidth = horizontalSize.doubleValue() / totalTiles.doubleValue();
		Double tileHeight = verticalSize.doubleValue() / totalTiles.doubleValue(); 
		
		if((((horizontalSize / tileWidth) * tileWidth) == horizontalSize) &&
				(((verticalSize / tileHeight) * tileHeight) == verticalSize)) {
			return true;
		}
		System.out.println("Error: Tiles: Invalid Composition");
		return false;
	}
	
}
