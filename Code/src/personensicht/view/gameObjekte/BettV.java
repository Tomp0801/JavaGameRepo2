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
		this.bett.setHeight(bettModel.getHohe());
		this.bett.setWidth(bettModel.getBreite());
		this.bett.setDepth(bettModel.getLaenge());
	}

	@Override
	public void setHohe(int hohe)
	{
		this.bett.setDepth(hohe);
	}

	@Override
	public void setBreite(int breite) 
	{
		this.bett.setHeight(breite);
	}

	@Override
	public void setLaenge(int laenge) 
	{
		this.bett.setWidth(laenge);
	}
	
	/**
	 * setzt eine Farbe
	 * @param color
	 */
	public void setColor(Color color)
	{
		Shape3DZusatzMethoden.hintergundFarbeSetzen(bett, color);
	}
}
