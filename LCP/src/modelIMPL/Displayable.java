package modelIMPL;

import java.util.ArrayList;
import java.util.Iterator;

import model.IDisplayContent;
import model.IDisplayable;
import model.IEvent;
import model.IDisplayableDimension;
import model.IObserver;

public class Displayable implements IDisplayable {

	private IDisplayableDimension tileDetails;
	private IDisplayContent displayDetails;
	private ArrayList<IObserver> observers;

	public Displayable() {
		this.observers = new ArrayList<IObserver>();
	}
	
	@Override
	public IDisplayContent getDisplay() {
		return this.displayDetails;		
	}
	
	@Override
	public void setDisplay(IDisplayContent displayDetails) {
		this.displayDetails = displayDetails;
	}

	@Override
	public IDisplayableDimension getTileDetails() {
		return tileDetails;
	}

	@Override
	public void setTiles(IDisplayableDimension tileDetails) {
		this.tileDetails = tileDetails;
	}

	@Override
	public void notifyObservers(IEvent event) {
		Iterator<IObserver> itrObservers = this.observers.iterator();
		while(itrObservers.hasNext()) {
			IObserver o = itrObservers.next();
			o.update(this, event);
		}
	}
	@Override
	public void addObserver(IObserver observer) {
		this.observers.add(observer);
	}
	@Override
	public void removeObserver(IObserver observer) {
		this.observers.remove(observer);
	}
	@Override
	public Iterator<IObserver> getObserver() {
		return this.observers.iterator();
	}
	
	@Override
	public void checkAddition() {
		Event addedToTile = new Event("ADDED_TO_TILE"); // Why this method and this magic String?
		this.notifyObservers(addedToTile);
	}
}
