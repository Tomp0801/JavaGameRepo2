package personensicht.model.gameObjekte;

import java.util.ArrayList;

import ioHandler.SerializableNew;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import personensicht.model.aktionen.Aktion;
import personensicht.view.gameObjekte.GameObjektV;


/**
 * GameObjekte sind objekte mit denen der Spieler auf der Spielwelt interagieren kann
 * @author Demix
 *
 */
public abstract class GameObjekt implements SerializableNew
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
	private transient SimpleDoubleProperty width = new SimpleDoubleProperty(60);
	
	/**
	 * y size der root Node.
	 */
	private transient SimpleDoubleProperty height = new SimpleDoubleProperty(40);
	
	/**
	 * z size der root Node.
	 */
	private transient SimpleDoubleProperty depth = new SimpleDoubleProperty(20); 
		
	/**
	 * gibt die Position X an, auf der sich die Node befindet
	 */
	private transient SimpleDoubleProperty layoutX = new SimpleDoubleProperty(0);
	
	/**
	 * Gibt die PositionY an auf der sich die Node befindet
	 */
	private transient SimpleDoubleProperty layoutY = new SimpleDoubleProperty(0);
	
	/**
	 * the variable is for the serializ the value of width 
	 */
	private double widthSerializ = 0;
	/**
	 * the variable is for the serializ the value of height 
	 */
	private double heightSerializ = 0;
	/**
	 * the variable is for the serializ the value of depht 
	 */
	private double depthSerializ = 0;
	/**
	 * the variable is for the serializ the value of layoutX 
	 */
	private double layoutXSerializ = 0;
	/**
	 * the variable is for the serializ the value of layoutY
	 */
	private double layoutYSerializ = 0;
	
	
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
		if(this.width  != null){
			this.width.setValue(value);
		}
		else{
			this.width = new SimpleDoubleProperty(value);
		}
		
	}


	public synchronized void setHeight(double value) {
		if(this.height != null){
			this.height.setValue(value);
		}
		else{
			this.height = new SimpleDoubleProperty(value);
		}
		
	}


	public synchronized void setDepth(double value) {
		if (this.depth != null){
			this.depth.setValue(value);	
		}
		else{
			this.depth = new SimpleDoubleProperty(value);
		}
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
		if(this.layoutX != null){
			this.layoutX.setValue(value);
		}
		else{
			this.layoutX = new SimpleDoubleProperty(value);
		}
	}

	public synchronized void setLayoutY(double value) {
		if (this.layoutY != null){
			this.layoutY.setValue(value);
		}
		else{
			this.layoutY = new SimpleDoubleProperty(value);
		}

		
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

	protected synchronized double getWidthSerializ() {
		return widthSerializ;
	}

	protected synchronized double getHeightSerializ() {
		return heightSerializ;
	}

	protected synchronized double getDepthSerializ() {
		return depthSerializ;
	}

	protected synchronized double getLayoutXSerializ() {
		return layoutXSerializ;
	}

	protected synchronized double getLayoutYSerializ() {
		return layoutYSerializ;
	}

	protected synchronized void setWidthSerializ(double widthSerializ) {
		this.widthSerializ = widthSerializ;
	}

	protected synchronized void setHeightSerializ(double heightSerializ) {
		this.heightSerializ = heightSerializ;
	}

	protected synchronized void setDepthSerializ(double depthSerializ) {
		this.depthSerializ = depthSerializ;
	}

	protected synchronized void setLayoutXSerializ(double layoutXSerializ) {
		this.layoutXSerializ = layoutXSerializ;
	}

	protected synchronized void setLayoutYSerializ(double layoutYSerializ) {
		this.layoutYSerializ = layoutYSerializ;
	}
}
