package modelIMPL;

import model.IDisplayContent;
import model.IDisplayable;
import model.IEvent;
import model.IDisplayableDimension;
import model.IObserver;
import model.ITile;

public class ImplObserver implements IObserver {
	String toShow[][] = new String[21][21];

	public ImplObserver() { // What is that method for???
		for(int i = 0; i < 21; i++)
		{
			for(int j = 0; j < 21; j++)
			{
				toShow[i][j] = "--";
			}
		}
	}
	
	@Override
	public void update(ITile observable) {
		// Nothing to do?
	}

	@Override
	public void update(IDisplayable observable, IEvent event) {
		IDisplayableDimension tileDet = observable.getTileDetails(); // Also, in general, put all variables "final".
		IDisplayContent disp = observable.getDisplay();
		
		Integer startX = tileDet.getX();
		Integer startY = tileDet.getY();
		Integer ht = tileDet.getHeight();
		Integer wd = tileDet.getWidth();
		System.out.println("STARTX : " + startX.toString() + ", STARTY : " + startY.toString() +
				", HEIGHT: " + ht.toString() + ", Width : " + wd.toString() + " --- DISPLAY : " + disp.getDisplay());
		for(int i = 0; i < ht; i++) {
			for(int j = 0; j < wd; j++) {
				toShow[startY - i][j + startX] += disp.getDisplay();
			}
		}
		 
		System.out.println("*********************************************");
		for(int i = 0; i < 21; i++) // What are these magic numbers?
		{
			for(int j = 0; j < 21; j++)
			{
				System.out.print(toShow[i][j] + ", ");
			}
			System.out.println();
		}
		System.out.println("*********************************************");
		return;
	}
	
}
