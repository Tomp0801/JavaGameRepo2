package draufsicht.view; 

import draufsicht.model.*;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MapScene {
	private static ParallelCamera camera;
	
	private static void initScene(Scene scene, Map map) {
		BorderPane layout = new BorderPane();
				
		SubScene subScene = new SubScene(map.getGrafics(), map.getGrafics().getMapWidth(), map.getGrafics().getMapHeight());
		camera = new ParallelCamera();
		subScene.setCamera(camera);
		camera.setNearClip(0.01);
		
		scene.addEventHandler(MouseEvent.ANY, new Node2DNavigator(map.getGrafics()));
		
		scene.addEventHandler(ScrollEvent.ANY, new Node2DZoomer(map.getGrafics()));
		
		layout.setCenter(subScene);
		
		scene.setRoot(layout);
	}
	
	public static Scene makeScene(Map map) {
		Scene scene = new Scene(new StackPane());
		initScene(scene, map);
		return scene;
	}
}
