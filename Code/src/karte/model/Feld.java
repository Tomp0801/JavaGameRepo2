package karte.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Ein Feld einer Karte
 * 
 * @author Thomas
 *
 */
public class Feld extends Rectangle {
	static final int WIDTH = 30;
	static final int HEIGHT = 30;
	
	/**
	 * Objekt, das auf diesem Feld steht
	 */
	private Placeable object;
	
	/**
	 * Karte, zu der dieses  Feld gehört
	 */
	private Map karte;
	
	/**
	 * Erstellt ein Feld, das zu der angegebenen Karte gehört
	 * @param parent
	 * @param x x-Position des Felds
	 * @param y y-Position des Felds
	 */
	public Feld(Map parent) {
		super(WIDTH, HEIGHT);
		this.karte = parent;
		
		if (Math.round(Math.random()) == 0) {
			this.setFill(Color.RED); 
		} else {
			this.setFill(Color.BLUE);
		}
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
	 * Gibt die Karte wieder, zu der dieses Feld gehört
	 * @return die Karte, zu der dieses Feld gehört
	 */
	public Map getKarte() {
		return karte;
	}
}
