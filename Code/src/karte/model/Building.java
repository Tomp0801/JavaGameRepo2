package karte.model;

import javafx.geometry.Point2D;
import javafx.scene.shape.*;

/**
 * Abstrakte Klasse f�r Geb�ude, die auf einer Karte platziert werden k�nnen
 * Erbt von der abstrakten klasse RunningObjekt
 * 
 * @author Thomas
 *
 */
public abstract class Building extends RunningObject implements Placeable {
	/**
	 * Rechteckiger Grundriss des Geb�udes
	 */
	protected Rectangle body;
		
	/**
	 * Position, an der das Geb�ude steht
	 */
	protected Point2D position;
	
	/**
	 * Karte, auf der das Geb�ude platziert ist
	 */
	protected Map parent;
	
	
	/**
	 * Erstellt ein Geb�ude der Gr��e 1x1
	 */
	public Building() {
		position = Point2D.ZERO;
		body = new Rectangle(1, 1);
	}
	
	/**
	 * Erstellt ein Geb�ude der angegebenen Gr��e
	 * @param width Breite des Geb�udes
	 * @param height H�he des Geb�udes
	 */
	public Building(int width, int height) {
		position = Point2D.ZERO;
		body = new Rectangle(width, height);
	}
	
	@Override
	public Shape getBody() {
		return body;
	}

	@Override
	public Point2D getPosition() {
		return position;
	}
	
	@Override
	public void setPosition(Point2D point) {
		position = point;
	}
	
	@Override
	public void place(Map parent, Point2D position) {
		int x = 0, y = 0;
		try {
			for (x = 0; x < body.getWidth(); x++) {
				for (y = 0; y < body.getHeight(); y++) {
					parent.getFeld(position.add(new Point2D(x, y))).place(this);
				}
			}
			this.parent = parent;
			this.setPosition(position);
		} catch (IllegalStateException e) {
			System.out.println("Objekt konnte nicht auf Feld " + x + "|" + y + " platziert werden");
			System.out.println(e.getMessage());
		}
		
	}
	
	@Override
	public Map getParent() {
		return parent;
	}
}
