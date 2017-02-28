package karte.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import karte.model.Building;

public class MineGrafics extends BuildingGrafics {

	private Rectangle mainRoom;
	private Rectangle sideRoom;
	
	public MineGrafics(Building parent) {
		super(parent);
		
		init();
	}
	
	private void init() {
		mainRoom = new Rectangle(10, 10, Color.BROWN);
		sideRoom = new Rectangle(2, 2, Color.DARKGRAY);
		mainRoom.setX(1);
		mainRoom.setY(1);
		sideRoom.setX(11);
		sideRoom.setY(1);
		this.getChildren().add(mainRoom);
		this.getChildren().add(sideRoom);
	}

}
