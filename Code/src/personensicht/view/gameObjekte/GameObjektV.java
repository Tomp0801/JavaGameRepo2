package personensicht.view.gameObjekte;

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
	
	public abstract void setHohe(int hohe);
	
	public abstract void setBreite(int hohe);
	
	public abstract void setLaenge(int hohe);
}
