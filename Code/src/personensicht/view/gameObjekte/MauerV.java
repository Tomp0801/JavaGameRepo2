package personensicht.view.gameObjekte;

import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import personensicht.model.gameObjekte.Mauer;
import personensicht.view.Shape3DZusatzMethoden;

public class MauerV extends GameObjektV
{

	public final static int MAXSIZE_X = 100; 
	public final static int MAXSIZE_Y = 100; 
	public final static int MAXSIZE_Z = 50; 
	public final static int MINSIZE_X = 20; 
	public final static int MINSIZE_Y = 20; 
	public final static int MINSIZE_Z = 20; 

	private Box mauer = new Box();
	
	public MauerV(Mauer mauer)
	{
		this.setNode(this.mauer);
		this.mauer.setHeight(mauer.getY());
		this.mauer.setWidth(mauer.getX());
		this.mauer.setDepth(mauer.getZ());
	}
	


	@Override
	public void setX(double hohe) {
		this.mauer.setWidth(hohe);
	}
	
	@Override
	public void setY(double hohe) {
		this.mauer.setHeight(hohe);	
	}

	@Override
	public void setZ(double hohe) {
		this.mauer.setDepth(hohe);
	}

	@Override
	public void setColor(Color color) {
		Shape3DZusatzMethoden.hintergundFarbeSetzen(this.mauer, color);	
	}
}
