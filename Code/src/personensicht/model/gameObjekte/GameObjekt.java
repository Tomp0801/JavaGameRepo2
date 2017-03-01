package personensicht.model.gameObjekte;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.shape.Shape3D;
import personensicht.model.aktionen.Aktion;
import personensicht.view.gameObjekte.GameObjektV;


/**
 * GameObjekte sind objekte mit denen der Spieler auf der Spielwelt interagieren kann
 * @author Demix
 *
 */
public abstract class GameObjekt 
{
	/**
	 * Name des GameObjektes.
	 */
	private String name = "default"; 

	/**
	 * beschreibt den Typ des GameObjektes
	 */
	private final GameObjektType TYPE; 
	
	/**
	 * Lieste der Funktionen die mit dem Objekt zusammenhagen
	 */
	private ArrayList<Aktion> aktionen = new ArrayList<Aktion>();
	
	/**
	 * Ein GameObjekt besitzt eine Node, die das Aussehen in der GUI festlegt
	 */
	private GameObjektV nodeObjekt; 
	
	/**
	 * leange des Objektes
	 */
	private double laenge = 60;
	
	/**
	 * breite des Objektes
	 */
	private double breite = 40;
	
	/**
	 * hohe des Objektes
	 */
	private double hohe = 20; 
	
	public GameObjekt(GameObjektType type) 
	{
		this.TYPE = type;
	}
	
	public GameObjekt(GameObjektType type, int x, int y, int z) 
	{
		this.TYPE = type;
		laenge= x;
		breite = y;
		hohe = z;
		
	}
	
	public synchronized ArrayList<Aktion> getAktionen() {
		return aktionen;
	}

	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}
	
	public abstract void refleshAktionsListe();

	/**
	 * laed die Node des Objektes und gibt es zurueck
	 * @return
	 */
	public abstract Node ladeNodeObjekt();
	
	/**
	 * @param localX position in X
	 * @param localY position in Y
	 * @return
	 */
	public abstract Node ladeNodeObjekt(int localX, int localY);

	
	public synchronized void setNodeObjekt(GameObjektV nodeObjekt) {
		this.nodeObjekt = nodeObjekt;
	}

	public synchronized GameObjektV getNodeObjekt() {
		return nodeObjekt;
	}

	public synchronized GameObjektType getTyp() {
		return TYPE;
	}

	public synchronized double getX() {
		return laenge;
	}

	public synchronized void setX(double laenge) {
		this.laenge = laenge;
		if (nodeObjekt != null)
			this.nodeObjekt.setX(laenge);
	}

	public synchronized double getY() {
		return breite;
	}

	public synchronized void setY(double breite) {
		this.breite = breite;
		if (nodeObjekt != null)
			this.nodeObjekt.setY(breite);
	}

	public synchronized double getZ() {
		return hohe;
	}

	public synchronized void setZ(double hohe) {
		this.hohe = hohe;
		if (nodeObjekt != null)
			this.nodeObjekt.setZ(hohe);
	} 
	
	/**
	 * gibt den Typ eines GamObjektes (z.B. ein Moebelstueck) zurueck, passend zu dem String der Uebergeben wird. 
	 * @param type Beispiel: fuer dien Type Schrank, muss der String "Schrank" heißen
	 * @return den Typen
	 */
	public static GameObjektType getTypeOfGameObjekt(String type)
	{
		type = type.replace("ü", "ue");
		type = type.replace("Ü", "Ue");
		type = type.replace("ä", "ae");
		type = type.replace("Ä", "Ae");
		type = type.replace("ö", "Oe");
		type = type.replace("Ö", "Oe");
		
		switch (type)
		{
		case "Schrank":
			return GameObjektType.Schrank;
		case "Bett":
			return GameObjektType.Bett;
		case "Mensch":
			return GameObjektType.Mensch;
		case "Tuer":
			return GameObjektType.Tuer;
		case "Stuhl":
			return GameObjektType.Stuhl;
		case "Computer":
			return GameObjektType.Computer; 
		case "Tisch":
			return GameObjektType.Tisch; 
		case "Mauer":
			return GameObjektType.Mauer; 
		default: 
			System.err.println("Fehler der Typ: "+type+" gib es nicht in Der Klasse RegionMakerView, Methode GameObjekteType");
			return null;
		}
	}
}
