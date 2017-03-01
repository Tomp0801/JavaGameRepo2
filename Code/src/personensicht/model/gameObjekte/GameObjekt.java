package personensicht.model.gameObjekte;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.shape.Shape3D;
import personensicht.model.aktionen.Aktion;
import personensicht.view.gameObjekte.GameObjektV;
import sun.java2d.pipe.SpanShapeRenderer.Simple;


/**
 * GameObjekte sind objekte mit denen der Spieler auf der Spielwelt interagieren kann
 * @author Demix
 *
 */
public abstract class GameObjekt implements Serializable
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
	private transient GameObjektV nodeObjekt; 
	
	/**
	 * x size der root Node.
	 */
	private SimpleDoubleProperty width = new SimpleDoubleProperty(60);
	
	/**
	 * y size der root Node.
	 */
	private SimpleDoubleProperty height = new SimpleDoubleProperty(40);
	
	/**
	 * z size der root Node.
	 */
	private SimpleDoubleProperty depth = new SimpleDoubleProperty(20); 
	
	/**
	 * gibt die Position X an, auf der sich die Node befindet
	 */
	private SimpleDoubleProperty layoutX = new SimpleDoubleProperty(0);
	
	/**
	 * Gibt die PositionY an auf der sich die Node befindet
	 */
	private SimpleDoubleProperty layoutY = new SimpleDoubleProperty(0);
	
	public GameObjekt(GameObjektType type) 
	{
		this.TYPE = type;
	}
	
	/**
	 * erstellt ein GameObjekt mit vorgegebener Laenge Breite und Hohe
	 * @param type
	 * @param width
	 * @param height
	 * @param depth
	 */
	public GameObjekt(GameObjektType type, int width, int height, int depth) 
	{
		this.TYPE = type;
		this.width.setValue(width);
		this.height.setValue(height);
		this.depth.setValue(depth);		
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
	
	public synchronized void setNodeObjekt(GameObjektV nodeObjekt) {
		this.nodeObjekt = nodeObjekt;
	}

	public synchronized GameObjektV getNodeObjekt() {
		return nodeObjekt;
	}

	public synchronized GameObjektType getTyp() {
		return TYPE;
	}

	public synchronized void setWidth(double value) {
		this.width.setValue(value);
	}


	public synchronized void setHeight(double value) {
		this.height.setValue(value);
	}


	public synchronized void setDepth(double value) {
		this.depth.setValue(value);
	} 
	
	public synchronized SimpleDoubleProperty getWidth() {
		return width;
	}

	public synchronized SimpleDoubleProperty getHeight() {
		return height;
	}

	public synchronized SimpleDoubleProperty getDepth() {
		return depth;
	}

	public synchronized SimpleDoubleProperty getLayoutX() {
		return layoutX;
	}

	public synchronized SimpleDoubleProperty getLayoutY() {
		return layoutY;
	}

	public synchronized void setLayoutX(double value) {
		this.layoutX.setValue(value);
	}

	public synchronized void setLayoutY(double value) {
		this.layoutY.setValue(value);
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
