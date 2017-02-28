package karte.view;

import javafx.scene.shape.Rectangle;
import karte.model.Feld;

/**
 * Die Grafik Komponente eines Feldes 
 * @author Thomas
 *
 */
public class FeldNode extends Rectangle {
	/**
	 * Breite des Feldes
	 */
	static final int WIDTH = 30;
	/**
	 * H�he des Feldes
	 */
	static final int HEIGHT = 30;
	
	/**
	 * Feld, zu dem dieser K�rper geh�rt
	 */
	private Feld parent;
	
	/**
	 * Erstellt einen Feldk�rper zu dem angegebenen Feld
	 * @param parent
	 */
	public FeldNode(Feld parent) {
		super(WIDTH, HEIGHT);
		
		this.parent = parent;
		
	}

}
