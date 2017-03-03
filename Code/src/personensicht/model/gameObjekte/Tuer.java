package personensicht.model.gameObjekte;

import javafx.scene.Node;
import javafx.scene.layout.Region;
import personensicht.view.gameObjekte.TuerV;

/**
 * eine Tuer verweist auf eine andere Region
 * @author Dennis
 *
 */
public class Tuer extends GameObjekt
{
	private Region zielRegion;
	
	public Tuer()
	{
		super(GameObjektType.Tuer, 150, 40, 1000);
	}
	
	public Tuer(Region region)
	{
		this();
		this.zielRegion = region;
	}
	
	@Override
	public void refleshAktionsListe() 
	{
		
	}

	@Override
	public Node ladeNodeObjekt() 
	{
		this.setNodeObjekt(new TuerV(this)); 
		return this.getNodeObjekt().getNode();
	}

	public synchronized Region getZielRegion() {
		return zielRegion;
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
