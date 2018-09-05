package modelIMPL;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import factory.Factory;
import model.IBuilding;
import model.IDisplayContent;
import model.IDisplayable;
import model.IDisplayableDimension;
import model.IEvent;
import model.IFloor;
import model.ILCPDriver;
import model.IObserver;
import model.IRoom;
import model.ITile;
import view.ButtonGrid;

public class LCPDriver implements ILCPDriver,Runnable { // Is that actually the Game?
	
	
	private final Integer HORIZONTAL_SIZE = 840;
	private final Integer VERTICAL_SIZE = 840;
	private final Integer TILE_MATRIX_SIZE = 21; /* 21 x 21 */
	/*
	private final Integer numFloors = 3;
	private final Integer roomsFF = 1;
	private final Integer roomsSF = 3;
	private final Integer roomsTF = 2;
	*/
	
	private IBuilding building;
	private ArrayList<IObserver> observers;
	/* Floor = 3, Rest room = 4  = Total tiles = 7 in 1 floor = 21 in 3 floors*/

	@Override
	public final Integer getNumTilesHorizontally() { // Why would a user need access to these values?
		return this.TILE_MATRIX_SIZE;
	}
	
	@Override
	public final Integer getNumTilesVertically() {
		return this.TILE_MATRIX_SIZE;
	}
	
	public LCPDriver() {
		this.building = null;
		this.observers = new ArrayList<IObserver>();
	}
	
	@Override
	public void initBuilding() {
		IDisplayContent buildingDisplay = this.building.getDisplay();
		buildingDisplay.setDisplay("Building");
		buildingDisplay.setBackground(new Color(0,0,255));
	}
	
	private final void createBuilding() {
		IDisplayContent buildingDisplay = new DisplayContent();
		IBuilding building = (IBuilding) Factory.getInstance().getBuilding(buildingDisplay);
		this.building = building;
		initBuilding();
	}

	private final void initTiles() {
		ITile tiles[][] = Factory.getInstance().getTiles(HORIZONTAL_SIZE, VERTICAL_SIZE, TILE_MATRIX_SIZE);
		this.building.initTiles(tiles);
	}

	private final void initFloors() {
		IDisplayContent floorDisplay = new DisplayContent();
		floorDisplay.setDisplay("Floor");
		//brown
		floorDisplay.setBackground(new Color(165,42,42));
		
		IDisplayableDimension objTileDetails = new DisplayableDimension(6, 0, 3, 21);
		Integer floorNumber = 1;
		IDisplayable floorOne = Factory.getInstance().getFloor(floorNumber, floorDisplay, objTileDetails);
		
		IDisplayableDimension objTileDetails2 = new DisplayableDimension(13, 0, 3, 21);
		Integer floorNumber2 = 2;
		IDisplayable floorTwo = Factory.getInstance().getFloor(floorNumber2, floorDisplay, objTileDetails2);
		
		IDisplayableDimension objTileDetails3 = new DisplayableDimension(20, 0, 3, 21);
		Integer floorNumber3 = 3;
		IDisplayable floorThree = Factory.getInstance().getFloor(floorNumber3, floorDisplay, objTileDetails3);
		
		for(int i = 0; i < this.observers.size(); i++)
		{
			floorOne.addObserver(this.observers.get(i)); // Isn't the list of Observers empty at this point?
			floorTwo.addObserver(this.observers.get(i));
			floorThree.addObserver(this.observers.get(i));
		}
		
		this.building.addFloor(floorOne);
		this.building.addFloor(floorTwo);
		this.building.addFloor(floorThree);
	}

	private final void initRooms() {
		////Room1
		IDisplayContent roomDisplay1 = new DisplayContent();
		roomDisplay1.setDisplay("Room1");
		//light gray
		roomDisplay1.setBackground(new Color(169,169,169));
		IDisplayableDimension objTileDetails1 = new DisplayableDimension(3, 0, 4, 21); // Why all these computations? The Room should add themselves next to the others...
		IDisplayable room1 = Factory.getInstance().getRoom(roomDisplay1, objTileDetails1);
		
		////Room2
		IDisplayContent roomDisplay2 = new DisplayContent();
		//green
		roomDisplay2.setDisplay("Room2");
		roomDisplay2.setBackground(new Color(0,255,0));
		IDisplayableDimension objTileDetails2 = new DisplayableDimension(10, 0, 4, 9);
		IDisplayable room2 = Factory.getInstance().getRoom(roomDisplay2, objTileDetails2);
		
		////Room3
		IDisplayContent roomDisplay3 = new DisplayContent();
		roomDisplay3.setDisplay("Room3");
		//purple
		roomDisplay3.setBackground(new Color(153, 102, 255));
		IDisplayableDimension objTileDetails3 = new DisplayableDimension(10, 9, 4, 7);
		IDisplayable room3 = Factory.getInstance().getRoom(roomDisplay3, objTileDetails3);
		
		////Room4
		IDisplayContent roomDisplay4 = new DisplayContent();
		roomDisplay4.setDisplay("Room4");
		//turquoise blue
		roomDisplay4.setBackground(new Color(51, 204, 204));
		IDisplayableDimension objTileDetails4 = new DisplayableDimension(10, 16, 4, 5);
		IDisplayable room4 = Factory.getInstance().getRoom(roomDisplay4, objTileDetails4);
		
		////Room5
		IDisplayContent roomDisplay5 = new DisplayContent();
		roomDisplay5.setDisplay("Room5");
		//light yellow
		roomDisplay5.setBackground(new Color(255, 255, 153));
		IDisplayableDimension objTileDetails5 = new DisplayableDimension(17, 0, 4, 12);
		IDisplayable room5 = Factory.getInstance().getRoom(roomDisplay5, objTileDetails5);
		
		////Room6
		IDisplayContent roomDisplay6 = new DisplayContent();
		roomDisplay6.setDisplay("Room6");
		//purple
		roomDisplay6.setBackground(new Color(153, 102, 255));
		IDisplayableDimension objTileDetails6 = new DisplayableDimension(17, 12, 4, 9);
		IDisplayable room6 = Factory.getInstance().getRoom(roomDisplay6, objTileDetails6);
		
		for(int i = 0; i < this.observers.size(); i++)
		{
			room1.addObserver(this.observers.get(i));
			room2.addObserver(this.observers.get(i));
			room3.addObserver(this.observers.get(i));
			room4.addObserver(this.observers.get(i));
			room5.addObserver(this.observers.get(i));
			room6.addObserver(this.observers.get(i));
		}
		
		Iterator<IDisplayable> itrFloors = this.building.getFloors();
		int ctr = 0;
		while(itrFloors.hasNext()) {
			IFloor tmpFloor = (IFloor)itrFloors.next();
			if(ctr == 0) {
				tmpFloor.addRoom(room1);
			}
			else if(ctr == 1) {
				tmpFloor.addRoom(room2);
				tmpFloor.addRoom(room3);
				tmpFloor.addRoom(room4);
			}
			else if(ctr == 2) {
				tmpFloor.addRoom(room5);
				tmpFloor.addRoom(room6);
			}
			ctr += 1;
		}
	}

	private final void initFurnitures() {
		IDisplayContent stoveDisp = new DisplayContent();
		stoveDisp.setDisplay("Stove");
		stoveDisp.setBackground(new Color(211,211,211));
		
		IDisplayableDimension stoveTileDetails = new DisplayableDimension(18, 0, 2, 1);
		IDisplayable stove = Factory.getInstance().getNonMoveableFurniture(stoveDisp, stoveTileDetails);
		
		IDisplayContent fridgeDisp = new DisplayContent();
		fridgeDisp.setDisplay("Fridge");
		fridgeDisp.setBackground(new Color(255,250,250));
		
		IDisplayableDimension fridgeTileDetails = new DisplayableDimension(18, 1, 4, 2);
		IDisplayable fridge = Factory.getInstance().getNonMoveableFurniture(fridgeDisp, fridgeTileDetails);
		
		IDisplayContent cupboardDisp1 = new DisplayContent();
		cupboardDisp1.setDisplay("CupBoard1");
		cupboardDisp1.setBackground(new Color(210,105,30));
		IDisplayableDimension cupboardTileDetails1 = new DisplayableDimension(14, 1, 1, 2);
		IDisplayable cupBoard1 = Factory.getInstance().getNonMoveableFurniture(cupboardDisp1, cupboardTileDetails1);
		
		IDisplayContent cupboardDisp2 = new DisplayContent(); 
	    cupboardDisp2.setDisplay("CupBoard2"); 
	    //Chocolate 
	    cupboardDisp2.setBackground(new Color(210,105,30)); 
		IDisplayableDimension cupboardTileDetails2 = new DisplayableDimension(15, 3, 2, 2);
		IDisplayable cupBoard2 = Factory.getInstance().getNonMoveableFurniture(cupboardDisp2, cupboardTileDetails2);
		
		IDisplayContent cupboardDisp3 = new DisplayContent(); 
	    cupboardDisp3.setDisplay("CupBoard3"); 
	    //Chocolate 
	    cupboardDisp3.setBackground(new Color(210,105,30));
		IDisplayableDimension cupboardTileDetails3 = new DisplayableDimension(15, 5, 2, 2);
		IDisplayable cupBoard3 = Factory.getInstance().getNonMoveableFurniture(cupboardDisp3, cupboardTileDetails3);
		
		IDisplayContent cupboardDisp4 = new DisplayContent(); 
	    cupboardDisp4.setDisplay("CupBoard4"); 
	    //same as fridge 
	    cupboardDisp4.setBackground(new Color(255,250,250)); 
		IDisplayableDimension cupboardTileDetails4 = new DisplayableDimension(18, 3, 2, 4);
		IDisplayable cupBoard4 = Factory.getInstance().getNonMoveableFurniture(cupboardDisp4, cupboardTileDetails4);
		
		IDisplayContent windowDisp = new DisplayContent();
		windowDisp.setDisplay("Window");
		windowDisp.setBackground(new Color(135,206,235));
		IDisplayableDimension windowTileDetails = new DisplayableDimension(16, 7, 2, 2);
		IDisplayable kitchenWindow = Factory.getInstance().getNonMoveableFurniture(windowDisp, windowTileDetails);
		
		IDisplayContent tableDisp = new DisplayContent();
		tableDisp.setDisplay("Table");
		tableDisp.setBackground(new Color(128,0,0));
		IDisplayableDimension tableTileDetails = new DisplayableDimension(19, 7, 2, 2);
		IDisplayable kitchenTable = Factory.getInstance().getNonMoveableFurniture(tableDisp, tableTileDetails);
		
		IDisplayContent waterDisp = new DisplayContent();
		waterDisp.setDisplay("Water");
		waterDisp.setBackground(new Color(240,248,255));
		IDisplayableDimension waterTileDetails = new DisplayableDimension(18, 10, 2, 1);
		IDisplayable water = Factory.getInstance().getNonMoveableFurniture(waterDisp, waterTileDetails);
		
		IDisplayContent stairDiagonalDisp = new DisplayContent();
		stairDiagonalDisp.setDisplay("StairDiag");
		stairDiagonalDisp.setBackground(new Color(205,133,63));
		IDisplayableDimension stairDiagTileDetails = new DisplayableDimension(15, 10, 2, 1);
		IDisplayable stairDiag = Factory.getInstance().getNonMoveableFurniture(stairDiagonalDisp, stairDiagTileDetails);
		
		IDisplayContent stairDownDisp = new DisplayContent();
		stairDownDisp.setDisplay("StairDown");
		stairDownDisp.setBackground(new Color(205,133,63));
		IDisplayableDimension stairDownTileDetails = new DisplayableDimension(18, 11, 5, 1);
		IDisplayable stairDown = Factory.getInstance().getNonMoveableFurniture(stairDownDisp, stairDownTileDetails);
		
		for(int i = 0; i < this.observers.size(); i++)
		{
			stove.addObserver(this.observers.get(i));
			stairDown.addObserver(this.observers.get(i));
			stairDiag.addObserver(this.observers.get(i));
			water.addObserver(this.observers.get(i));
			kitchenTable.addObserver(this.observers.get(i));
			kitchenWindow.addObserver(this.observers.get(i));
			cupBoard1.addObserver(this.observers.get(i));
			cupBoard2.addObserver(this.observers.get(i));
			cupBoard3.addObserver(this.observers.get(i));
			cupBoard4.addObserver(this.observers.get(i));
			fridge.addObserver(this.observers.get(i));
		}
		
		Iterator<IDisplayable> itr = ((IBuilding)this.building).getFloors();
		itr.next();
		itr.next();
		IFloor floor3 = (IFloor)itr.next();
		Iterator<IDisplayable> itrRoomsTF = floor3.getRooms();
		IRoom roomF3R1 = (IRoom) itrRoomsTF.next();
		roomF3R1.addFurniture(stove);
		roomF3R1.addFurniture(fridge);
		roomF3R1.addFurniture(cupBoard1);
		roomF3R1.addFurniture(cupBoard2);
		roomF3R1.addFurniture(cupBoard3);
		roomF3R1.addFurniture(cupBoard4);
		roomF3R1.addFurniture(kitchenWindow);
		roomF3R1.addFurniture(kitchenTable);
		roomF3R1.addFurniture(water);
		roomF3R1.addFurniture(stairDiag);
		roomF3R1.addFurniture(stairDown);
	}
	
	private final void initPerson() {
		IDisplayContent personDisplay = new DisplayContent();
		personDisplay.setDisplay("Person");
		//skin color
		personDisplay.setBackground(new Color(234,192,134));
		
		//adding person to floor 3 room 1
		Iterator<IDisplayable> itr = ((IBuilding)this.building).getFloors();
		itr.next();
		itr.next();
		IFloor floor3 = (IFloor)itr.next();
		Iterator<IDisplayable> itrRoomsTF = floor3.getRooms();
		IRoom roomF3R1 = (IRoom) itrRoomsTF.next();
		
		IDisplayableDimension personDimension = new DisplayableDimension(18, 9, 3, 1);
		IDisplayable person = Factory.getInstance().getPerson(roomF3R1, personDisplay, personDimension);
		for(int i = 0; i < this.observers.size(); i++)
		{
			person.addObserver(this.observers.get(i));
		}
		
		roomF3R1.addPerson(person);
	}

	@Override
	public final IDisplayable getBuilding() {
		this.createBuilding();
		this.initTiles();
		this.initFloors();
		this.initRooms();
		this.initFurnitures();
		this.initPerson();
		return this.building;
	}

	@Override
	public final void notifyObservers(IEvent event) {
		return;
	}

	@Override
	public final void addObserver(IObserver observer) {
		if(observer != null)
			this.observers.add(observer);
	}

	@Override
	public void removeObserver(IObserver observer) {
		if(observer != null)
			this.observers.remove(observer);
	}

	@Override
	public Iterator<IObserver> getObserver() {
		return this.observers.iterator();
	}

	@Override
	public void run() {
	//	Thread.sleep(millis);
		
	}
	
	@Override
	public void movePerson() {
		Iterator<IDisplayable> itr = ((IBuilding)this.building).getFloors();
		itr.next();
		itr.next();
		IFloor floor3 = (IFloor)itr.next();
		Iterator<IDisplayable> itrRoomsTF = floor3.getRooms();
		IRoom roomF3R1 = (IRoom) itrRoomsTF.next();
		IDisplayable person = null;
		Iterator<IDisplayable> persons = roomF3R1.getPersons();
		
		person = persons.next();
		IDisplayableDimension personDimension = new DisplayableDimension(18, 8, 3, 1);	
		person.setTiles(personDimension);
		this.checkForUpdate();
	}
	
	public void checkForUpdate()  {
		

		Iterator<IDisplayable> floors = ((IBuilding) building).getFloors();
		IDisplayable person = null;
		while (floors.hasNext()) {
			IDisplayable floor = floors.next();
			floor.checkAddition();

			Iterator<IDisplayable> rooms = ((IFloor) floor).getRooms();
			while (rooms.hasNext()) {
				IDisplayable room = rooms.next();
				room.checkAddition();

				Iterator<IDisplayable> furns = ((IRoom) room).getFurniture();
				while (furns.hasNext()) {
					IDisplayable furn = furns.next();
					furn.checkAddition();
				}
				
				Iterator<IDisplayable> persons = ((IRoom) room).getPersons();
				while (persons.hasNext()) {
					person = persons.next();
					
					person.checkAddition();
				}
			}
		}
		
	}

}
