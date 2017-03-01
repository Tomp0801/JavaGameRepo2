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
	
	private Box root = new Box(); 
	
	public SchrankV(Schrank schrank)
	{
		this.setNode(this.root);
		this.root.heightProperty().bind(schrank.getHeight());
		this.root.widthProperty().bind(schrank.getWidth());
		this.root.depthProperty().bind(schrank.getDepth());
		this.root.layoutXProperty().bind(schrank.getLayoutX());
		this.root.layoutYProperty().bind(schrank.getLayoutY());
	}
	
	
	@Override
	public void setHeight(double hohe) {
		
		this.root.setHeight(hohe);
	}

	@Override
	public void setWidth(double hohe) {
		
		this.root.setWidth(hohe);
	}

	@Override
	public void setDepth(double hohe) {
		this.root.setDepth(hohe);
	}


	@Override
	public void setColor(Color color)
	{
		Shape3DZusatzMethoden.hintergundFarbeSetzen(this.root, color);
	}
}
