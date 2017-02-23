package personensicht.view.gameObjekte;

import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import personensicht.model.gameObjekte.Schrank;
import personensicht.view.Shape3DZusatzMethoden;

public class SchrankV extends GameObjektV
{
	public final static int MAXSIZE_X = 100; 
	public final static int MAXSIZE_Y = 100; 
	public final static int MAXSIZE_Z = 50; 
	public final static int MINSIZE_X = 20; 
	public final static int MINSIZE_Y = 20; 
	public final static int MINSIZE_Z = 20; 
	
	private Box schrank = new Box(); 
	
	public SchrankV(Schrank schrank)
	{
		this.setNode(this.schrank);
		this.schrank.setHeight(schrank.getY());
		this.schrank.setWidth(schrank.getX());
		this.schrank.setDepth(schrank.getZ());
	}
	
	
	@Override
	public void setY(double hohe) {
		
		this.schrank.setHeight(hohe);
	}

	@Override
	public void setX(double hohe) {
		
		this.schrank.setWidth(hohe);
	}

	@Override
	public void setZ(double hohe) {
		this.schrank.setDepth(hohe);
	}


	@Override
	public void setColor(Color color)
	{
		Shape3DZusatzMethoden.hintergundFarbeSetzen(this.schrank, color);
	}
}
