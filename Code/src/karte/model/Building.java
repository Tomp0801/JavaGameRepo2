package karte.model;

import javafx.geometry.Point2D;

/**
 * Abstrakte Klasse f�r Geb�ude, die auf einer Karte platziert werden k�nnen
 * Erbt von der abstrakten klasse RunningObjekt
 * 
 * @author Thomas
 *
 */
public abstract class Building extends RunningObject implements Placeable {	
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
		parent.getFeld(position).place(this);
		this.parent = parent;
		this.setPosition(position);
	}
	
	@Override
	public Map getParent() {
		return parent;
	}
}
