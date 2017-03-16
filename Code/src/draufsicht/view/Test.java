package draufsicht.view;


import draufsicht.model.*;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.stage.Stage;
import spielctr.controller.Random;
import spielmodel.ressource.model.Material;

/**
 * Klasse zum Testen der Map View
 * erstellt eine eigene Application, getrennt von dem restlichen Programm
 * 
 * @author Thomas
 *
 */
public class Test extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		Random prng = new Random("test");
		
		Generator gen = new Generator(prng, Material.ERDE);
		gen.addMaterial(Material.WASSER, 10);
		gen.addMaterial(Material.STEIN, 50);
		gen.generateMap(40, 20);
		
		gen.addBodenschatz(Material.GOLD, 5f);
		gen.addBodenschatz(Material.STEIN, 20f);
		
		gen.setSmoothIterations(10000);
		gen.setSmoothRadius(1);
		gen.setMinimumNeightbors(4);
		gen.smoothMapRandom();
		
		gen.setSmoothRadius(1);
		gen.setMinimumNeightbors(3);
		gen.smoothMap();
		
		Map map = gen.translateToMap();
		
		primaryStage.setScene(MapScene.makeScene(map));
		primaryStage.show();
		
		Mine mine1 = new Mine(Material.STEIN);
		mine1.place(map, new Point2D(3, 6));
		Mine mine2 = new Mine(Material.GOLD);
		mine2.place(map, new Point2D(3, 10));
		Mine mine3 = new Mine(Material.ERDE);
		mine3.place(map, new Point2D(32, 19));
		
		Wohnhaus w1 = new Wohnhaus();
		w1.place(map, 4, 7);
		
		//map.getGrafics().setMapWidth(1024, true);
		
	}
	
	
}
