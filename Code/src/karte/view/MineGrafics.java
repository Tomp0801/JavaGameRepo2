package karte.view;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import karte.model.Mine;

public class MineGrafics extends Group {

	private Rectangle mainRoom;
	private Rectangle sideRoom;
	
	public MineGrafics(Mine parent) {
		init();
	}
	
	private void init() {
		mainRoom = new Rectangle(10, 10, Color.BROWN);
		sideRoom = new Rectangle(2, 2, Color.DARKGRAY);
		mainRoom.setX(0);
		mainRoom.setY(0);
		sideRoom.setX(10);
		sideRoom.setY(0);
		this.getChildren().add(mainRoom);
		this.getChildren().add(sideRoom);
	}

}
