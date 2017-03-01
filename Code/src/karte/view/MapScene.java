package karte.view;

import javafx.event.EventHandler;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import karte.model.*;

public class MapScene {
	private static ParallelCamera camera;
	
	private static void initScene(Scene scene, Map map) {
		BorderPane layout = new BorderPane();
		
		new MapGrafics(map, 800, 600);

		
		VBox buttons = new VBox();
		Button plus = new Button("+");
		plus.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					camera.setScaleX(camera.getScaleX() / 1.5);
					camera.setScaleY(camera.getScaleY() / 1.5);
				}
			
			}
		);
		buttons.getChildren().add(plus);
		Button minus = new Button("-");
		minus.setPrefSize(plus.getWidth(), plus.getHeight());
		minus.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					camera.setScaleX(camera.getScaleX() * 1.5);
					camera.setScaleY(camera.getScaleY() * 1.5);
				}
			
			}
		);
		buttons.getChildren().add(minus);
		
		SubScene subScene = new SubScene(map.getGrafics(), map.getGrafics().getMapWidth(), map.getGrafics().getMapHeight());
		camera = new ParallelCamera();
		subScene.setCamera(camera);
		camera.setNearClip(0.01);
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getText().equals("w")) {
					camera.setTranslateY(camera.getTranslateY() - 20);
				} else if (event.getText().equals("s")) {
					camera.setTranslateY(camera.getTranslateY() + 20);					
				} else if (event.getText().equals("a")) {
					camera.setTranslateX(camera.getTranslateX() - 20);					
				} else if (event.getText().equals("d")) {
					camera.setTranslateX(camera.getTranslateX() + 20);					
				}
				
			}
		});
		
		layout.setLeft(buttons);
		layout.setCenter(subScene);
		
		scene.setRoot(layout);
	}
	
	public static Scene makeScene(Map map) {
		Scene scene = new Scene(new StackPane());
		initScene(scene, map);
		return scene;
	}
}
