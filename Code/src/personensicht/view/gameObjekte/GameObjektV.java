package personensicht.view.gameObjekte;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape3D;

public abstract class GameObjektV
{
	private Shape3D node;

	public synchronized Shape3D getNode() 
	{
		return node;
	}

	public synchronized void setNode(Shape3D node) 
	{
		this.node = node;
	}
	
	public abstract void setX(double hohe);
	
	public abstract void setY(double hohe);
	
	public abstract void setZ(double hohe);
	
	/**
	 * setzt eine Farbe
	 * @param color
	 */
	public abstract void setColor(Color color);
}
