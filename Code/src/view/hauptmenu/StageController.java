package view.hauptmenu;

import java.io.IOException;

import controller.GameManager;
import himmelskoerper.SchwarzesLoch;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import view.weltraum.StageControllerSpiel;
import view.weltraum.WeltraumSicht;

//diese Methode ist vergleichbar mit der Mainmethode. Funktioniert quasie genau so
/**
 * JavaFX startMethode
 */
public class StageController extends Application
{
	/**
	 * eigene Instance
	 */
	private static StageController instance; 
	
	/**
	 * die aktuell verwendete Stage
	 */
	private Stage stage; 
		
	/**
	 * Scene der StartGameFXML Datei
	 */
	private Scene startGameScene;
	
	/**
	 * Scene der netzerkFXML Datei
	 */
	private Scene netzwerkFXMLScene;
	
	private WeltraumSicht weltraumSicht;
		
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
		System.out.println("Spiel wird gestartet");
//		//zum laden eines DemoSpiels
//		StageControllerSpiel demoSpiel = new StageControllerSpiel(primaryStage);	

		instance = this; 
		this.stage = primaryStage;
		//Damit man nicht mit ESCAP den FullScreen schließen kann
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		//Damit keine Nachricht erscheint nach dem eröffnen des FullScreens
		primaryStage.setFullScreenExitHint("");
		primaryStage.setFullScreen(true);
		primaryStage.show();
//		this.wechselScene(SceneEnum.WELTRAUMSICHT);
	
		this.wechselScene(SceneEnum.STARTGAME);
		//nicht loeschen
	}
	
	
	/**
	 * wechselt die Scene
	 * 
	 * @param scene die verwendet werden soll
	 */
	public void wechselScene(SceneEnum scene)
	{
		Scene newScene = null; 
		switch (scene) 
		{
		case STARTGAME:
			if (startGameScene == null)
				erstelleNeueScene(scene);
			newScene = startGameScene;
			break;
		case NETZWERK:
			if (netzwerkFXMLScene == null)
				erstelleNeueScene(scene);
			newScene = netzwerkFXMLScene;
			break;
		case WELTRAUMSICHT:
			if (weltraumSicht == null)
			{
				weltraumSicht = new WeltraumSicht(GameManager.getInstance().getSchwarzesLochSystem(), new Point3D(0, 0, -200));
			}
			newScene = weltraumSicht.getScene();
			break;
		case KARTENSICHT:
			break;
		}
		
		setScene(newScene);
	}

	
	
	/**
	 * setzt eine neue Scene
	 * @param scene
	 */
	public void setScene(Scene scene)
	{
		stage.setScene(scene);
		stage.setFullScreen(true);
	}
	
	
	
	/**
	 * erstellt einen Controller des Typs der uebergeben wird und speichert dieser als eine Objektvariable ab
	 * 
	 * @param controller ist ein Enum von FXMLCotrollerEnum. Dieser gibt den Typ des Controllers an, der erstellt werden soll
	 */
	private void erstelleNeueScene(SceneEnum scene)
	{
		FXMLLoader loader = null;
		
		switch (scene) 
		{
		case STARTGAME:		
			loader = ladeFXMLDatei("fxml/Hauptmenu.fxml"); 
			startGameScene = new Scene(loader.getRoot()); 
			break;
		case NETZWERK:
			loader = ladeFXMLDatei("fxml/NetzwerkspielEingangsraum.fxml"); 
			netzwerkFXMLScene = new Scene(loader.getRoot()); 
			break;		
		}
	}
	
	
	/**
	 * ladet eine FXML Datei
	 * @param datei die Datei die geladen werden soll
	 * @return den FXMLLoader der Dattei
	 */
	private FXMLLoader ladeFXMLDatei(String datei)
	{
		FXMLLoader 	loader = new FXMLLoader(getClass().getResource(datei)); 
		
		try 
		{
			loader.load();
			return loader;
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
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
}
