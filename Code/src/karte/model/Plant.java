package karte.model;

import javafx.geometry.Point2D;
import javafx.scene.Group;
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
	public void run() {
		// TODO Essen wächst
		long time = getPassedTime();
		
		System.out.println("Essen ist gewachsen in " + time + " ms.");
	}

	@Override
	public Group getGrafics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feld getGrund() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void place(Feld feld) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void place(Map parent, Point2D position) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unplace() {
		// TODO Auto-generated method stub
		
	}

}
