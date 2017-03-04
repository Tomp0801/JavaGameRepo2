package karte.view;

import com.sun.javafx.geom.Point2D;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class Node2DNavigator implements EventHandler<MouseEvent> {

	private Point2D dragStart;
	
	private Node graficsNode;
	
	public Node2DNavigator(Node node) {
		this.graficsNode = node;
		dragStart = new Point2D();
	}
	
	@Override
	public void handle(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
		
		} else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {		//dragstart merken
			dragStart.x = (float) event.getX();
			dragStart.y = (float) event.getY();
		} else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {		//dragstart reseten
			dragStart.x = 0f;
			dragStart.y = 0f;
		} else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {		//node verändern, verschieben
			double xDiff = (event.getX() - dragStart.x);
			double yDiff = (event.getY() - dragStart.y);
			graficsNode.setTranslateX(graficsNode.getTranslateX() + xDiff);
			graficsNode.setTranslateY(graficsNode.getTranslateY() + yDiff);
			dragStart.x = (float) event.getX();
			dragStart.y = (float) event.getY();
			
			begrenzungEinhalten();
		}
		
	}
	
	private void begrenzungEinhalten() {
		//TODO
	}

}
