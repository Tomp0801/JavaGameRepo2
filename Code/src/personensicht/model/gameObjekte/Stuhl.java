package personensicht.model.gameObjekte;

import javafx.scene.Node;
import personensicht.view.gameObjekte.BettV;
import personensicht.view.gameObjekte.StuhlV;

public class Stuhl extends GameObjekt
{

	public Stuhl()
	{
		super(GameObjektType.Stuhl);
	}

	@Override
	public void refleshAktionsListe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node ladeNodeObjekt() {
		this.setNodeObjekt(new StuhlV(this)); 
		return this.getNodeObjekt().getNode();
	}

	@Override
	public void serializ() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deserializ() {
		// TODO Auto-generated method stub
		
	}
}
