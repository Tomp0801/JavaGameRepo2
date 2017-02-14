package personensicht.model.gameObjekte;

import java.util.ArrayList;

import javafx.scene.Node;
import personensicht.model.aktionen.AktionInventarOeffnen;
import personensicht.model.inventar.items.Item;

/**
 * definiert einen Schrank
 * @author Dennis
 *
 */
public class Schrank extends GameObjekt implements Inventataeger
{
	private Inventar inventar;

	/**
	 * laenge des Schranks in Centimeter
	 */
	private int laenge = 100;
	/**
	 * breite des Schranks in Centimeter
	 */
	private int breite = 50;
	/**
	 * hohe des Schranksin Centimeter
	 */
	private int hohe = 200;
	
	public Schrank()
	{
		super(GameObjektType.Schrank);
		this.setName("Schrank");
		ArrayList<Inventar> inventare = new ArrayList<Inventar>();
		inventar = new Inventar(50, "Schrank");
		inventare.add(inventar);
		getAktionen().add(new AktionInventarOeffnen(inventare));
	}
	
	public Schrank(Item[] items) 
	{
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

	public synchronized int getLaenge() {
		return laenge;
	}

	public synchronized void setLaenge(int laenge) {
		this.laenge = laenge;
	}

	public synchronized int getBreite() {
		return breite;
	}

	public synchronized void setBreite(int breite) {
		this.breite = breite;
	}

	public synchronized int getHohe() {
		return hohe;
	}

	public synchronized void setHohe(int hohe) {
		this.hohe = hohe;
	}

	@Override
	public void refleshAktionsListe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node ladeNodeObjekt()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node ladeNodeObjekt(int localX, int localY) {
		// TODO Auto-generated method stub
		return null;
	}
}
