package modelIMPL;

import java.awt.Point;

import model.ILocationPoints;

public class LocationPoints implements ILocationPoints { // ?
	private Point nwPosition;
	private Point swPosition;
	private Point nePosition;
	private Point sePosition;

	public LocationPoints(Point nwPosition, Integer tileHeight, Integer tileWidth) {
		this.nwPosition = nwPosition;
		setPositions(tileHeight, tileWidth);
	}
	
	public LocationPoints(Integer tileHeight, Integer tileWidth) {
		this.nwPosition = new Point(0,0);
		setPositions(tileHeight, tileWidth);
	}
	
	@Override
	public void setPositions(Integer tileHeight, Integer tileWidth) {
		this.swPosition = new Point();
		this.swPosition.setLocation(this.nwPosition.getX(), this.nwPosition.getY() + tileHeight);
		this.nePosition = new Point();
		this.nePosition.setLocation(this.nwPosition.getX() + tileWidth, this.nwPosition.getY());
		this.sePosition = new Point();
		this.sePosition.setLocation(this.nePosition.getX() + tileWidth, this.nePosition.getY() + tileHeight);
	}
	
	@Override
	public Double getNEX() {
		return this.nePosition.getX();
	}
	
	@Override
	public Point getSWPoint() {
		return this.swPosition;
	}
	
	@Override
	public Point getNEPoint() {
		return this.nePosition;
	}
}
