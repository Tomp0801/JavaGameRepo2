package personensicht.view.gameObjekte;

import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import personensicht.model.gameObjekte.Tisch;
import personensicht.view.Shape3DZusatzMethoden;

public class TischV extends GameObjektV
{

	public final static int MAXSIZE_X = 200; 
	public final static int MAXSIZE_Y = 200; 
	public final static int MAXSIZE_Z = 50; 
	public final static int MINSIZE_X = 20; 
	public final static int MINSIZE_Y = 20; 
	public final static int MINSIZE_Z = 20; 
	
	private Box tisch = new Box();
	
	public TischV(Tisch tisch)
	{
		this.setNode(this.tisch);
		this.tisch.setHeight(tisch.getY());
		this.tisch.setWidth(tisch.getX());
		this.tisch.setDepth(tisch.getZ());
	}

	@Override
	public void setY(double hohe) {
		this.tisch.setHeight(hohe);
		
	}

	@Override
	public void setX(double hohe) {
		this.tisch.setWidth(hohe);
		
	}

	@Override
	public void setZ(double hohe) {
		this.tisch.setDepth(hohe);
		
	}

	@Override
	public void setColor(Color color) 
	{
		Shape3DZusatzMethoden.hintergundFarbeSetzen(this.tisch, color);
		
	}

}
