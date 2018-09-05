package modelIMPL;

import model.IDisplayableDimension;

public class DisplayableDimension implements IDisplayableDimension { // Why not use Java Point and Dimension?
	Integer Y;
	Integer X;
	Integer Height;
	Integer Width;
	
	public DisplayableDimension(Integer Y, Integer X, Integer Height, Integer Width) {
		this.Y = Y;
		this.X = X;
		this.Height = Height;
		this.Width = Width;
	}
	
	@SuppressWarnings("unused")
	private DisplayableDimension() {}
	
	@Override
	public Integer getX() {
		return X;
	}
	
	@Override
	public Integer getY() {
		return Y;
	}
	
	@Override
	public Integer getHeight() {
		return Height;
	}
	
	@Override
	public Integer getWidth() {
		return Width;
	}
}
