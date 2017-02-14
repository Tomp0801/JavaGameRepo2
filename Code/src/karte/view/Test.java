package karte.view;

import javafx.application.Application;
import javafx.stage.Stage;
import karte.model.*;

public class Test extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Map map = new Map(30, 30);
		
		primaryStage.setScene(MapScene.makeScene(map));
		primaryStage.show();
		
	}
}
