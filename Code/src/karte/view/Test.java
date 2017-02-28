package karte.view;


import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.stage.Stage;
import karte.model.*;
import obersteEbene.controller.Random;
import ressource.Material;

public class Test extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		Random prng = new Random("rghh4g");
		
		Generator gen = new Generator(prng, Material.ERDE);
		gen.addMaterial(Material.WASSER, 32);
		gen.addMaterial(Material.STEIN, 15);
		Map map = gen.generateMap(60, 40);
		
		map = gen.smoothMap();
		
		primaryStage.setScene(MapScene.makeScene(map));
		primaryStage.show();
		
		Mine mine1 = new Mine();
		mine1.place(map, new Point2D(3, 6));
		Mine mine2 = new Mine();
		mine2.place(map, new Point2D(3, 9));
		Mine mine3 = new Mine();
		mine3.place(map, new Point2D(32, 20));
	}
	
	
}
