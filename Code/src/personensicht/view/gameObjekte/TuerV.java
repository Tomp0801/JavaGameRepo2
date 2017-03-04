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
	
	public TuerV(Tuer model)
	{
		this.setNode(this.root);
		this.root.heightProperty().bindBidirectional(model.getHeight());
		this.root.widthProperty().bindBidirectional(model.getWidth());
		this.root.depthProperty().bindBidirectional(model.getDepth());
		this.root.layoutXProperty().bindBidirectional(model.getLayoutX());
		this.root.layoutYProperty().bindBidirectional(model.getLayoutY());
	}
}
