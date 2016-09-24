package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

//TODO erstelle eine Liste mit Scenen die verwendet wurden. Um einfach wieder zurueck zu wechseln
/**
 * Die Main Klasse. Diese Klasse startet das Programm als eine JavaFX Anwengun und verwaltet zu gleich die Stage.
 * Sie bietet eine Methode an um das wechseln zu einer neuen Scenen zu ermoeglichen.
 * 
 * @author Dennis
 *
 */
public class StageController extends Application
{
	/**
	 * eigene Instance
	 */
	private static StageController instance; 
	
	/**
	 * die Stage auf der das Spielgeschehen zu sehen ist.
	 */
	private Stage primaryStage; 
		
	/**
	 * Die Mainklasse 
	 * @param args
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
	
	
	/**
	 * lade die view
	 */
	@Override
	public void start(Stage primaryStage) throws Exception 
	{    
		instance = this; 
		this.primaryStage = primaryStage;
		//Damit man nicht mit ESCAP den FullScreen schließen kann
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		//Damit keine Nachricht erscheint nach dem eröffnen des FullScreens
		primaryStage.setFullScreenExitHint("");
		primaryStage.setFullScreen(true);
		primaryStage.show();

		this.setScene(this.generateScen(new FXMLLoader(getClass().getResource("/view/hauptmenu/fxml/Hauptmenu.fxml"))));
		//nicht loeschen
	}
	
	
	/**
	 * setzt eine neue Scene
	 * @param scene
	 */
	public void setScene(Scene scene)
	{
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.setX(0);
		primaryStage.setY(0);		
		primaryStage.show(); 	
	}
	
	
	/**
	 * generriert eine Scene mit hilfe des FXMLLoaders und setzt sie danach. 
	 * @param loader
	 */
	public void setScene(FXMLLoader loader)
	{
		try 
		{
			this.setScene(this.generateScen(loader));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}	
	}
	
	
	/**
	 * Gebe mir die Instance der Klasse.
	 * @return instance
	 */
	public static StageController getInstance()
	{
		return instance; 
	}
	
	
	/**
	 * generriert eine Scene aus dem FXMLLoader der uebergeben wird. In dieser Methode wird 
	 * die funktion load vom FXMLLoader mir aufgerufen
	 * @param loader
	 * @return eine Scene mit den Root des FXMLLoaders das uebergeben wurde
	 */
	public Scene generateScen(FXMLLoader loader) throws IOException
	{
		loader.load();
		return new Scene(loader.getRoot());
	}
}
