package controller;

import java.io.IOException;
import java.util.LinkedList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
	 * diese Liste beinhaltet eine Reihe von Verweisungen auf unterschedlichenen Scenen.
	 * Dies wird verwendet um schnelle und einfacher in die vorherige Scene zu wechseln. 
	 * Zum Beispiel beim hinein zoomen auf einem Planeten. Hier wird schnell zwischen einzelnen Scenen 
	 * hin und wieder zurueckgewechselt. 
	 * 
	 * in der Liste werden alle Scenen gespeichert die mit der setScene() Methode aufgerufen wurden. 
	 * wird die openLastScene methode aufgerufen, wird die aktuell verwendete Scene aus der Liste entfernt
	 */
	private LinkedList<Scene> scenListe = new LinkedList<Scene>();
	
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
		primaryStage.centerOnScreen();
		primaryStage.show();

		this.setScene(this.generateScen(new FXMLLoader(getClass().getResource("/view/hauptmenu/fxml/Hauptmenu.fxml"))));
	}
	
	
	/**
	 * setzt eine neue Scene
	 * @param scene
	 */
	public void setScene(Scene scene)
	{		
		if (scenListe.size() > 200)
		{
			scenListe.removeFirst();
		}
		if (scenListe.isEmpty()==false)
		{
		if(this.scenListe.getLast() != scene)
		this.scenListe.add(scene);
		}
		else
		{
			this.scenListe.add(scene);
		}
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.setFullScreen(true);
		primaryStage.setX(0);
		primaryStage.setY(0);
		primaryStage.show();
		
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() 
		{@Override public void handle(MouseEvent event) {System.out.println("SubScene wurd angeklickt Ziel: "+event.getTarget().toString());}});
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
	
	/**
	 * oeffnet die scene die sich in der ScenenListe befindet und loescht aus der Liste
	 *  die aktuell verwendete Scene
	 */
	public void openLastScene()
	{
		this.scenListe.removeLast();
		if (scenListe.isEmpty() == false)
		{
			this.setScene(this.scenListe.getLast());
		}
	}
}
