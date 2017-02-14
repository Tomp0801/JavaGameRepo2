package personensicht.view.gameObjekte;

import javafx.scene.shape.Box;

public class BettV extends GameObjektV
{
	private Box bett = new Box();
	
	public BettV()
	{
		this.setNode(bett);
		this.bett.setHeight(20);
		this.bett.setWidth(20);
		this.bett.setDepth(20);
	}
}
