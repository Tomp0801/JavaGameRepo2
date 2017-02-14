package karte.view;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import karte.model.*;

public class MapScene {
	
	private static void initScene(Scene scene, Map map) {
		StackPane layout = new StackPane();
		
		GridPane mapPaint = new GridPane();		
		
		//fields zeichnen
		Rectangle feld;
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				feld = map.getFeld(x, y);
				mapPaint.add(feld, x, y);
			}
		}
		
		layout.getChildren().add(mapPaint);
		
		scene.setRoot(layout);
	}
	
	public static Scene makeScene(Map map) {
		Scene scene = new Scene(new StackPane());
		initScene(scene, map);
		return scene;
	}
}
