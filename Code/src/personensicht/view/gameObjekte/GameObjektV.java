package personensicht.view.gameObjekte;

import javafx.scene.Node;

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
