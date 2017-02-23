package personensicht.view.gameObjekte;

import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import personensicht.model.gameObjekte.Bett;
import personensicht.view.Shape3DZusatzMethoden;

public class BettV extends GameObjektV
{
	public final static int MAXSIZE_X = 200; 
	public final static int MAXSIZE_Y = 200; 
	public final static int MAXSIZE_Z = 50; 
	public final static int MINSIZE_X = 20; 
	public final static int MINSIZE_Y = 20; 
	public final static int MINSIZE_Z = 20; 
	
	
	private Box bett = new Box();
	
	public BettV(Bett bettModel)
	{
		this.setNode(bett);
		this.bett.setHeight(bettModel.getY());
		this.bett.setWidth(bettModel.getX());
		this.bett.setDepth(bettModel.getZ());
	}

	@Override
	public void setZ(double hohe)
	{
		this.bett.setDepth(hohe);
	}

	@Override
	public void setY(double breite) 
	{
		this.bett.setHeight(breite);
	}

	@Override
	public void setX(double laenge) 
	{
		this.bett.setWidth(laenge);
	}
	
	/**
	 * setzt eine Farbe
	 * @param color
	 */
	public void setColor(Color color)
	{
		Shape3DZusatzMethoden.hintergundFarbeSetzen(this.bett, color);
	}
}
