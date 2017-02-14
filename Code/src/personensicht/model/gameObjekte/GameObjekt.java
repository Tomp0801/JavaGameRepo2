package personensicht.model.gameObjekte;

import java.util.ArrayList;

import javafx.scene.Node;
import personensicht.model.aktionen.Aktion;


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

	private final GameObjektType TYPE; 
	
	/**
	 * Lieste der Funktionen die mit dem Objekt zusammenhagen
	 */
	private ArrayList<Aktion> aktionen = new ArrayList<Aktion>();
	
	/**
	 * Ein GameObjekt besitzt eine Node, die das Aussehen in der GUI festlegt
	 */
	private Node nodeObjekt; 
	
	public GameObjekt(GameObjektType type) 
	{
		this.TYPE = type;
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

	
	public synchronized void setNodeObjekt(Node nodeObjekt) {
		this.nodeObjekt = nodeObjekt;
	}

	public synchronized Node getNodeObjekt() {
		return nodeObjekt;
	}

	public synchronized GameObjektType getTyp() {
		return TYPE;
	} 
}
