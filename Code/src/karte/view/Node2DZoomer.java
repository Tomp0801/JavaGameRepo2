package karte.view;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;

public class Node2DZoomer implements EventHandler<ScrollEvent> {
	
	private Node graficsNode;
	
	private double zoomDivisor;
	
	public Node2DZoomer(Node node) {
		this.graficsNode = node;
		zoomDivisor = 200;
	}

	@Override
	public void handle(ScrollEvent event) {
		if (event.getEventType() == ScrollEvent.SCROLL) {
			double zoom = event.getDeltaY() / zoomDivisor;
			

			if (graficsNode.getScaleX() > 1) {		//in Mausposition reinZoomen
				Point2D mousePos = new Point2D(event.getSceneX(), event.getSceneY());
				Point2D center = new Point2D(graficsNode.getLayoutBounds().getWidth(), graficsNode.getLayoutBounds().getHeight());
				
				Point2D offset = mousePos.subtract(center.multiply(0.5));
				
				graficsNode.setTranslateX(graficsNode.getTranslateX() - offset.getX() * zoom);
				graficsNode.setTranslateY(graficsNode.getTranslateY() - offset.getY() * zoom);
				
				
			} else {
				graficsNode.setTranslateX(0);
				graficsNode.setTranslateY(0);
			}
			
			graficsNode.setScaleX(graficsNode.getScaleX() + zoom);
			graficsNode.setScaleY(graficsNode.getScaleY() + zoom);
			
			//Scale nciht kleiner als 0
			if (graficsNode.getScaleX() < 0) {
				graficsNode.setScaleX(0.1);
			}
			if (graficsNode.getScaleY() < 0) {
				graficsNode.setScaleY(0.1);
			}
			
			keinRand();

		}
	}
	
	private void keinRand() {
		//TODO
	}
	
	public void setZoomDivisor(double divisor) {
		zoomDivisor = divisor;
	}

}
