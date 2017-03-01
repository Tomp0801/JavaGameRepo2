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
	
	private Box root = new Box();
	
	public TuerV(Tuer tuer)
	{
		this.setNode(this.root);
		this.root.heightProperty().bind(tuer.getHeight());
		this.root.widthProperty().bind(tuer.getWidth());
		this.root.depthProperty().bind(tuer.getDepth());
		this.root.layoutXProperty().bind(tuer.getLayoutX());
		this.root.layoutYProperty().bind(tuer.getLayoutY());
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
	public void setColor(Color color) {
		Shape3DZusatzMethoden.hintergundFarbeSetzen(this.root, color);	
	}
}
