package personensicht.view.gameObjekte;

import javafx.scene.Node;
import javafx.scene.paint.Color;

public abstract class GameObjektV
{
	private Node node;

	public synchronized Node getNode() 
	{
		return node;
	}

	public synchronized void setNode(Node node) 
	{
		this.node = node;
	}
	
	public abstract void setWidth(double value);
	
	public abstract void setHeight(double value);
	
	public abstract void setDepth(double value);
	
	public abstract void setColor(Color color);
	
//	/**
//	 * setzt die Position der Node
//	 * @param value
//	 */
//	public void setLayoutX(double value){
//		if (node != null)
//		this.node.setLayoutX(value);
//		else
//		System.out.println("Node wurde noch nicht geladen");
//	}
//	
//	/**
//	 * setzt die Position der Node
//	 * @param value
//	 */
//	public void setLayoutY(double value){
//		if (node != null)
//		this.node.setLayoutY(value);
//		else
//		System.out.println("Node wurde noch nicht geladen");	
//	}
}
