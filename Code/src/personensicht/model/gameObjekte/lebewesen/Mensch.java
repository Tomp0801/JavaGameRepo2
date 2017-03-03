package personensicht.model.gameObjekte.lebewesen;

import javafx.scene.Node;
import personensicht.model.gameObjekte.GameObjekt;
import personensicht.model.gameObjekte.GameObjektType;
import personensicht.view.gameObjekte.lebewesen.MenschV;

public class Mensch extends GameObjekt
{	
	/**
	 * size der Arme. (links, rechts) (x,y,z)
	 */
	private int[][] sizeArme = new int[2][3];
	
	/**
	 * size der Beine. (links, rechts) (x,y,z)
	 */
	private int[][] sizeBeine = new int[2][3];
	
	/**
	 * groﬂe des Koerpers (x,y,z)
	 */
	private int[] sizeKoerper = new int[3];
	
	private int radiusKopf = 40; 
	
	public Mensch(String name)
	{
		super(GameObjektType.Mensch);
		this.setName(name);
		initAktionsListe(null);
	}
	
	/**
	 * 
	 * @param name
	 * @param path der weg zu der Datei, die fuer das Gespraech verwendet wird
	 */
	public Mensch(String name, String path)
	{
		super(GameObjektType.Mensch);
		this.setName(name);
		initAktionsListe(path);
	}

	public void initAktionsListe(String path)
	{
//		if (path != null)
		this.getAktionen().add(new GespraechAktion("C:/G1.txt"));
	}

	@Override
	public void refleshAktionsListe() {	
	}

	@Override
	public Node ladeNodeObjekt() {
		MenschV menschV = new MenschV();
		return menschV.getRoot();
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
