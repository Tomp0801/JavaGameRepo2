package personensicht.model.gameObjekte;

import javafx.scene.Node;
import personensicht.view.gameObjekte.MauerV;

public class Mauer extends GameObjekt
{

	public Mauer() 
	{
		super(GameObjektType.Mauer,50,50,200);
	}

	@Override
	public void refleshAktionsListe() {	
	}

	@Override
	public Node ladeNodeObjekt() {
		this.setNodeObjekt(new MauerV(this)); 
		return this.getNodeObjekt().getNode();
	}

	@Override
	public Node ladeNodeObjekt(int localX, int localY) {
		return null;
	}
}
