package personensicht.model.gameObjekte;

import javafx.scene.Node;
import javafx.scene.layout.Region;

/**
 * eine Tuer verweist auf eine andere Region
 * @author Dennis
 *
 */
public class Tuer extends GameObjekt
{
	private Region zielRegion;
	
	public Tuer(Region region)
	{
		super(GameObjektType.Tuer);
		this.zielRegion = region;
	}
	
	@Override
	public void refleshAktionsListe() 
	{
		
	}

	@Override
	public Node ladeNodeObjekt() 
	{
		return null;
	}

	public synchronized Region getZielRegion() {
		return zielRegion;
	}

	@Override
	public Node ladeNodeObjekt(int localX, int localY) {
		// TODO Auto-generated method stub
		return null;
	}

}
