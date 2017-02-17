package karte.model;

import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Plant extends RunningObject implements Placeable  {
	private Point2D position;
	private Map map;
	private Circle body;
	
	public Plant() {
		position = Point2D.ZERO;
		body = new Circle(0.4);		//soll auf ein Feld passen
	}
	
	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public void place(Map parent, Point2D position) {
		try {
			parent.getFeld(position).place(this);
			this.map = parent;
			this.setPosition(position);
		} catch (IllegalStateException e) {
			System.out.println("Objekt konnte nicht auf Feld " + position.getX() + "|" + position.getY() + " platziert werden");
			System.out.println(e.getMessage());
		}
	}

	@Override
	public Shape getBody() {
		return body;
	}

	@Override
	public Map getParent() {
		return map;
	}

	@Override
	public void run() {
		// TODO Essen wächst
		long time = getPassedTime();
		
		System.out.println("Essen ist gewachsen in " + time + " ms.");
	}

	@Override
	public void setPosition(Point2D point) {
		position = point;
	}

}
