package karte.model;

import javafx.geometry.Point2D;
import javafx.scene.shape.*;

/**
 * Abstrakte Klasse für Gebäude, die auf einer Karte platziert werden können
 * Erbt von der abstrakten klasse RunningObjekt
 * 
 * @author Thomas
 *
 */
public abstract class Building extends RunningObject implements Placeable {
	/**
	 * Rechteckiger Grundriss des Gebäudes
	 */
	protected Rectangle body;
		
	/**
	 * Position, an der das Gebäude steht
	 */
	protected Point2D position;
	
	/**
	 * Karte, auf der das Gebäude platziert ist
	 */
	protected Map parent;
	
	
	/**
	 * Erstellt ein Gebäude der Größe 1x1
	 */
	public Building() {
		position = Point2D.ZERO;
		body = new Rectangle(1, 1);
	}
	
	/**
	 * Erstellt ein Gebäude der angegebenen Größe
	 * @param width Breite des Gebäudes
	 * @param height Höhe des Gebäudes
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
