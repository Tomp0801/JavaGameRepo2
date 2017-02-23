package personensicht.view.gameObjekte;

import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import personensicht.model.gameObjekte.Stuhl;
import personensicht.view.Shape3DZusatzMethoden;

public class StuhlV extends GameObjektV
{
	
	public final static int MAXSIZE_X = 200; 
	public final static int MAXSIZE_Y = 200; 
	public final static int MAXSIZE_Z = 50; 
	public final static int MINSIZE_X = 20; 
	public final static int MINSIZE_Y = 20; 
	public final static int MINSIZE_Z = 20; 
	
	Box stuhl = new Box(); 
	
	public StuhlV(Stuhl stuhl)
	{
		this.setNode(this.stuhl);
		this.stuhl.setHeight(stuhl.getY());
		this.stuhl.setWidth(stuhl.getX());
		this.stuhl.setDepth(stuhl.getZ());
	}

	@Override
	public void setY(double hohe) {
		this.stuhl.setHeight(hohe);
		
	}

	@Override
	public void setX(double hohe) {
		this.stuhl.setWidth(hohe);
		
	}

	@Override
	public void setZ(double hohe) {
		this.stuhl.setDepth(hohe);
		
	}

	@Override
	public void setColor(Color color) {
		Shape3DZusatzMethoden.hintergundFarbeSetzen(this.stuhl, color);
		
	}

}
