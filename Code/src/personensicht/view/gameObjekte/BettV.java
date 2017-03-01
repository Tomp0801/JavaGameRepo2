package personensicht.view.gameObjekte;

import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import personensicht.model.gameObjekte.Bett;
import personensicht.view.Shape3DZusatzMethoden;

public class BettV extends GameObjektV
{
	public final static int MAXSIZE_X = 200; 
	public final static int MAXSIZE_Y = 200; 
	public final static int MAXSIZE_Z = 50; 
	public final static int MINSIZE_X = 20; 
	public final static int MINSIZE_Y = 20; 
	public final static int MINSIZE_Z = 20; 
	
	
	private Box root= new Box();
	
	public BettV(Bett bettModel)
	{
		this.setNode(root);
		this.root.heightProperty().bind(bettModel.getHeight());
		this.root.widthProperty().bind(bettModel.getWidth());
		this.root.depthProperty().bind(bettModel.getDepth());
		this.root.layoutXProperty().bind(bettModel.getLayoutX());
		this.root.layoutYProperty().bind(bettModel.getLayoutY());
	}

	@Override
	public void setDepth(double value)
	{
		this.root.setDepth(value);
	}

	@Override
	public void setHeight(double breite) 
	{
		this.root.setHeight(breite);
	}

	@Override
	public void setWidth(double breite) 
	{
		this.root.setWidth(breite);
	}
	
	/**
	 * setzt eine Farbe
	 * @param color
	 */
	@Override
	public void setColor(Color color)
	{
		Shape3DZusatzMethoden.hintergundFarbeSetzen(this.root, color);
	}
}
