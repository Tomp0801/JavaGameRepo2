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
	 * Höhe des Feldes
	 */
	static final int HEIGHT = 30;
	
	/**
	 * Feld, zu dem dieser Körper gehört
	 */
	private Feld parent;
	
	/**
	 * Erstellt einen Feldkörper zu dem angegebenen Feld
	 * @param parent
	 */
	public FeldNode(Feld parent) {
		super(WIDTH, HEIGHT);
		
		this.parent = parent;
		
	}

}
