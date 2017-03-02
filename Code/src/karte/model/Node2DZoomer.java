package karte.model;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;

public class Node2DZoomer implements EventHandler<ScrollEvent> {
	
	private Node graficsNode;
	
	public Node2DZoomer(Node node) {
		this.graficsNode = node;
	}

	@Override
	public void handle(ScrollEvent event) {
		if (event.getEventType() == ScrollEvent.SCROLL) {
			System.out.println("Scroll!" + event.getDeltaY() + " " + event.getDeltaX());
			graficsNode.setScaleX(graficsNode.getScaleX() + event.getDeltaY() / 200);
			graficsNode.setScaleY(graficsNode.getScaleY() + event.getDeltaY() / 200);
			//Scale nciht kleiner als 0
			if (graficsNode.getScaleX() < 0) {
				graficsNode.setScaleX(0.1);
			}
			if (graficsNode.getScaleY() < 0) {
				graficsNode.setScaleY(0.1);
			}
		}
	}

}
