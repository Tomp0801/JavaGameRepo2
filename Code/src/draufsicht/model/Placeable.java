package draufsicht.model;

import javafx.geometry.Point2D;
import javafx.scene.Node;

/**
 * Interface für ein Objekt, dass auf einem Map-Objekt plaziert werden kann
 * 
 * @author Thomas
 *
 */
public interface Placeable {
	/**
	 * Gibt das Feld des Objekts wieder
	 * @return das Feld, auf dem das Objekt steht
	 */
	public PlaceableOnThis getGrund();
	
	/**
	 * Platziert das Objekt auf dem angegebenen Feld
	 * @param feld Feld, auf dem das Objekt platziert werden soll 
	 */
	public void place(Feld feld);
	
	/**
	 * Platziert das Objekt auf der Angegebenen Karte an der angegebenen Position
	 * @param parent Karte, auf der das Objekt platziert werden soll 
	 * @param position Position, an der das Objekt platziert werden soll
	 */
	public default void place(Map parent, Point2D position) throws IllegalArgumentException {
		place(parent.getFeld(position));
	}
	
	/**
	 * Platziert das Objekt auf der Angegebenen Karte an der angegebenen Position
	 * @param parent Karte, auf der das Objekt platziert werden soll 
	 * @param x x-Wert der Position des Objekts
	 * @param y y-Wert der Position des Objekts
	 */
	public default void place(Map parent, int x, int y) throws IllegalArgumentException {
		place(parent.getFeld(x, y));
	}
	
	/**
	 * Entfernt das Objekt von dem Feld, auf dem es steht
	 */
	public void unplace();
	
	/**
	 * Gibt an, ob das Objekt platziert ist
	 * 
	 * @return true, wenn Objekt platziert
	 */
	public default boolean isPlaced() {
		return (getGrund() != null);
	}
	
	/**
	 * Gibt die Grafik des Objekts als Node wieder
	 * @return Grafische darstellung des Objekts als Node
	 */
	public Node getGrafics();
		
	/**
	 * Gibt die Bezeichnung des Objekts wieder
	 * @return Name des Objekts
	 */
	public String getName();
}
