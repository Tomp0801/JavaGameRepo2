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
	Box root = new Box(); 
	
	public StuhlV(Stuhl stuhl){
		this.setNode(this.root);
		this.root.heightProperty().bind(stuhl.getHeight());
		this.root.widthProperty().bind(stuhl.getWidth());
		this.root.depthProperty().bind(stuhl.getDepth());
		this.root.layoutXProperty().bind(stuhl.getLayoutX());
		this.root.layoutYProperty().bind(stuhl.getLayoutY());
	}

	@Override
	public void setHeight(double value) {
		this.root.setHeight(value);
		
	}

	@Override
	public void setWidth(double value) {
		this.root.setWidth(value);
		
	}

	@Override
	public void setDepth(double value) {
		this.root.setDepth(value);
		
	}

	@Override
	public void setColor(Color color) {
		Shape3DZusatzMethoden.hintergundFarbeSetzen(this.root, color);
		
	}

}
