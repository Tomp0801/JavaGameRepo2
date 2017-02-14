package personensicht.model.gameObjekte;

import javafx.scene.Node;
import personensicht.model.gameObjekte.lebewesen.GespraechAktion;

public class Mensch extends GameObjekt
{	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node ladeNodeObjekt(int localX, int localY) {
		// TODO Auto-generated method stub
		return null;
	}
}
