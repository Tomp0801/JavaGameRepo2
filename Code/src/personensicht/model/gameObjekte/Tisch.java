package personensicht.model.gameObjekte;

import javafx.scene.Node;
import personensicht.view.gameObjekte.BettV;
import personensicht.view.gameObjekte.TischV;

public class Tisch extends GameObjekt
{

	public Tisch() {
		super(GameObjektType.Tisch);
		
	}

	@Override
	public void refleshAktionsListe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node ladeNodeObjekt() {
		this.setNodeObjekt(new TischV(this)); 
		return this.getNodeObjekt().getNode();
	}

	@Override
	public Node ladeNodeObjekt(int localX, int localY) {
		// TODO Auto-generated method stub
		return null;
	}

}
