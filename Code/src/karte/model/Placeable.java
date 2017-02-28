package karte.model;

import javafx.geometry.Point2D;
import javafx.scene.Group;

/**
 * Interface für ein Objekt, dass auf einem Map-Objekt plaziert werden kann
 * 
 * @author Thomas
 *
 */
public interface Placeable {
	/**
	 * Gibt die Position des Objekts auf der Karte wieder
	 * @return die Position des Objekts auf der Karte
	 */
	public Point2D getPosition();
	
	/**
	 * Setzt die Position des Objekts
	 * @param point Position des Objekts
	 */
	public void setPosition(Point2D point);
	
	/**
	 * Setzt die Position des Objekts
	 * @param x x-Position des Objekts
	 * @param y y-Position des Objekts
	 */
	public default void setPosition(int x, int y) {
		setPosition(new Point2D(x, y));
	}
	
	/**
	 * Platziert das Objekt auf der Angegebenen Karte an der angegebenen Position
	 * @param parent Karte, auf der das Objekt platziert werden soll 
	 * @param position Position, an der das Objekt platziert werden soll
	 */
	public void place(Map parent, Point2D position);
	
	/**
	 * Platziert das Objekt auf der Angegebenen Karte an der angegebenen Position
	 * @param parent Karte, auf der das Objekt platziert werden soll 
	 * @param x x-Wert der Position des Objekts
	 * @param y y-Wert der Position des Objekts
	 */
	public default void place(Map parent, int x, int y) {
		place(parent, new Point2D(x, y));
	}
	
	/**
	 * TODO eine Area?
	 * 
	 * Gibt den Platz des Objekts wieder, den es auf der Karte ein einnimmt
	 * @return ein Shape-Objekt, dass die Maße des Objekts wiedergibt
	 */
	public Group getGrafics();
	
	/**
	 * TODO eigentlich ein Feld
	 * Gibt die Karte zurück, auf der das Objekt platziert ist
	 * @return die Karte, auf der das Objekt platziert ist
	 */
	public Map getParent();
	
	public String getName();
}
