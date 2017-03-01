package personensicht.view.gameObjekte;

import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import personensicht.model.gameObjekte.Tuer;
import personensicht.view.Shape3DZusatzMethoden;

public class TuerV extends GameObjektV
{

	public final static int MAXSIZE_X = 1000; 
	public final static int MAXSIZE_Y = 1000; 
	public final static int MAXSIZE_Z = 1000; 
	public final static int MINSIZE_X = 100; 
	public final static int MINSIZE_Y = 20; 
	public final static int MINSIZE_Z = 400; 
	
	private Box tuer = new Box();
	
	public TuerV(Tuer tuer)
	{
		this.setNode(this.tuer);
		this.tuer.setHeight(tuer.getY());
		this.tuer.setWidth(tuer.getX());
		this.tuer.setDepth(tuer.getZ());
	}
	
	@Override
	public void setY(double hohe) {
		this.tuer.setHeight(hohe);
		
	}

	@Override
	public void setX(double hohe) {
		this.tuer.setWidth(hohe);
		
	}

	@Override
	public void setZ(double hohe) {
		this.tuer.setDepth(hohe);
		
	}

	@Override
	public void setColor(Color color) {
		Shape3DZusatzMethoden.hintergundFarbeSetzen(this.tuer, color);	
	}
}
