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

	private Box root = new Box();
	
	public MauerV(Mauer model)
	{
		this.setNode(this.root);
		this.root.heightProperty().bindBidirectional(model.getHeight());
		this.root.widthProperty().bindBidirectional(model.getWidth());
		this.root.depthProperty().bindBidirectional(model.getDepth());
		this.root.layoutXProperty().bindBidirectional(model.getLayoutX());
		this.root.layoutYProperty().bindBidirectional(model.getLayoutY());
	}
}
