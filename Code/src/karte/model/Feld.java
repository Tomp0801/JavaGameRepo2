package karte.model;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import karte.view.FeldNode;
import ressource.Material;

/**
 * Ein Feld einer Karte
 * 
 * @author Thomas
 *
 */
public class Feld implements EventHandler<MouseEvent>{

	/**
	 * Objekt, das auf diesem Feld steht
	 */
	private Placeable object;
	
	/**
	 * Karte, zu der dieses  Feld geh�rt
	 */
	private Map karte;
	
	/**
	 * x-Koordinate dieses Feldes auf der parent Karte
	 */
	private int x;
	
	/**
	 * y-Koordinate dieses Feldes auf der parent Karte
	 */
	private int y;
	
	/**
	 * Die Grafik des Feldes
	 */
	private FeldNode node;
	
	/**
	 * Das BodenMaterial des Feldes
	 */
	private Material boden;
		
	/**
	 * Erstellt ein Feld, das zu der angegebenen Karte geh�rt
	 * @param parent
	 * @param x x-Position des Felds
	 * @param y y-Position des Felds
	 */
	public Feld(Map parent, int x, int y) {
		this.karte = parent;
		this.x = x;
		this.y = y;
		
		node = new FeldNode(this);
	}
	
	/**
	 * entfernt das aktuell auf diesem Feld befindliche Objekt von diesem Feld
	 */
	public void removeObject() {
		this.object = null;
	}
	
	/**
	 * Platziert das angegebene Objekt auf diesem Feld, wenn das Feld nicht schon besetzt ist
	 * @param object Objekt, das auf diesem Feld platziert werden soll
	 *
	 * @throws IllegalStateException wenn bereits ein Objekt auf dem Feld platziert ist
	 */
	public void place(Placeable object) throws IllegalStateException {
		if (this.object != null) throw new IllegalStateException("Es steht bereits ein Objekt auf dem Feld.");
		this.object = object;
	}
	
	/**
	 * Gibt die Karte wieder, zu der dieses Feld geh�rt
	 * @return die Karte, zu der dieses Feld geh�rt
	 */
	public Map getKarte() {
		return karte;
	}
	
	/**
	 * gibt die x-Koordinate dieses Feldes auf der parent Karte zur�ck
	 * @return die x-Koordinate dieses Feldes auf der parent Karte
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * gibt die y-Koordinate dieses Feldes auf der parent Karte zur�ck
	 * @return die y-Koordinate dieses Feldes auf der parent Karte
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gibt den Node zur�ck, der in einer Scene gezeichnet werden kann
	 * @return Node (Rectangle Objekt)
	 */
	public FeldNode getNode() {
		return node;
	}
	
	/**
	 * Gibt zur�ck, aus welchem Material der Boden auf diesem Feld besteht
	 * @return Das Bodenmaterial des Feldes
	 */
	public Material getBodenMaterial() {
		return boden;
	}

	@Override
	public void handle(MouseEvent event) {
		//TODO set this Feld als Scene
		System.out.println("Feld " + x + "|" + y + " angeklickt.");
	}
	

}