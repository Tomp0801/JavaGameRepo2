package karte.view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import karte.model.*;

public class MapScene {
	
	private static void initScene(Scene scene, Map map) {
		StackPane layout = new StackPane();
		
		GridPane mapPaint = new GridPane();		
		
		//fields zeichnen
		Node feld;
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				feld = map.getFeld(x, y).getGroup();
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
