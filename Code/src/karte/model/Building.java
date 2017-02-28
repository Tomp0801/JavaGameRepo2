package karte.model;

import javafx.geometry.Point2D;

/**
 * Abstrakte Klasse für Gebäude, die auf einer Karte platziert werden können
 * Erbt von der abstrakten klasse RunningObjekt
 * 
 * @author Thomas
 *
 */
public abstract class Building extends RunningObject implements Placeable {	
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
