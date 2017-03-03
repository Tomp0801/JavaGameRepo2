package personensicht.view.gameObjekte;

import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import personensicht.model.gameObjekte.Tisch;
import personensicht.view.Shape3DZusatzMethoden;

public class TischV extends GameObjektV
{

	public final static int MAXSIZE_X = 1000; 
	public final static int MAXSIZE_Y = 1000; 
	public final static int MAXSIZE_Z = 400; 
	public final static int MINSIZE_X = 20; 
	public final static int MINSIZE_Y = 20; 
	public final static int MINSIZE_Z = 20; 
	
	/**
	 * die Tischplatte
	 */
	private Box root = new Box();
	
	/**
	 * vier Tischbeine
	 */
	private Box[] tischBeine = new Box[4];
	
	public TischV(Tisch model)
	{
		this.setNode(this.root);
		this.root.heightProperty().bindBidirectional(model.getHeight());
		this.root.widthProperty().bindBidirectional(model.getWidth());
		this.root.depthProperty().bindBidirectional(model.getDepth());
		this.root.layoutXProperty().bindBidirectional(model.getLayoutX());
		this.root.layoutYProperty().bindBidirectional(model.getLayoutY());
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

	public synchronized Box getTischplatte() {
		return root;
	}

	public synchronized Box[] getTischBeine() {
		return tischBeine;
	}

	public synchronized void setTischplatte(Box tischplatte) {
		this.root = tischplatte;
	}

	public synchronized void setTischBeine(Box[] tischBeine) {
		this.tischBeine = tischBeine;
	}

}
