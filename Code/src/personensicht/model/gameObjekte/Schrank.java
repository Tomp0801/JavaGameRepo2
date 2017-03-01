package personensicht.model.gameObjekte;

import java.util.ArrayList;

import javafx.scene.Node;
import personensicht.model.aktionen.AktionInventarOeffnen;
import personensicht.model.inventar.items.Item;
import personensicht.view.gameObjekte.SchrankV;

/**
 * definiert einen Schrank
 * @author Dennis
 *
 */
public class Schrank extends GameObjekt implements Inventataeger
{
	private Inventar inventar;
	
	public Schrank()
	{
		super(GameObjektType.Schrank, 100, 50, 200);
		this.setName("Schrank");
		ArrayList<Inventar> inventare = new ArrayList<Inventar>();
		inventar = new Inventar(50, "Schrank");
		inventare.add(inventar);
		getAktionen().add(new AktionInventarOeffnen(inventare));
	}
	
	public Schrank(Item[] items) {
		this();
		try
		{
			inventar.addAllItem(items);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public synchronized Inventar getInventar() {
		return inventar;
	}

	public synchronized void setInventar(Inventar inventar) {
		this.inventar = inventar;
	}

	@Override
	public void refleshAktionsListe() {
		// TODO Auto-generated method stub	
	}

	/**
	 * lead die Node, die ein Schrank darstellt
	 */
	@Override
	public Node ladeNodeObjekt()
	{
		this.setNodeObjekt(new SchrankV(this)); 
		return this.getNodeObjekt().getNode();
	}
}
