package view.weltraum;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import map.Sektion;
import view.weltraum.fxml.SpielUmgebungController;

/**
 * 
 * Hier werden die Einzelnen Scenen verwaltet. Der StageController beinhaltet eine Stage. 
 * Auf Dieser Stage laeuft das gesammte Spielgeschehen ab. 
 * 
 * @author Dennis
 *
 */
public class StageControllerSpiel 
{
	/**
	 * diese Scene wird zur DemoVersion verwendet. 
	 */
	private Scene spielSceneDemo;
	
	/**
	 * zum laden der Scene
	 */
	private FXMLLoader loader; 
	
	private SpielUmgebungController spielUmgebungController; 
	
	public StageControllerSpiel(Stage stage)
	{
		//-------------------Stage wird erstellt-----------------------------------------------------//
		stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		stage.setFullScreenExitHint("");
		stage.setFullScreen(true);
		//--------------------Spiel-Umgebunng-wird-geladen-------------------------------------------//
		loader = new FXMLLoader(getClass().getResource("/view/weltraum/fxml/SpielUmgebung.fxml"));
	
		try{loader.load();}catch (IOException e){e.printStackTrace();}
		
		spielUmgebungController = loader.getController();
		spielSceneDemo = new Scene(loader.getRoot());
		//-------------------------------------------------------------------------------------------//
		
		Sektion demoSektion = new Sektion();
		WeltraumSicht3 demo = new WeltraumSicht3(demoSektion, spielSceneDemo);
		SubScene subScene = demo.getSubScene();
		
		subScene.widthProperty().bind(spielUmgebungController.getStackPaneZentrum().widthProperty());
		subScene.heightProperty().bind(spielUmgebungController.getStackPaneZentrum().heightProperty());
		
		spielUmgebungController.wechsleZentrum(subScene);
		
		stage.setScene(spielSceneDemo);
		stage.show();
		
	
		//-----------------------Test-TODO---loechen------------------------------------
//		Button testButton = new Button("Ich bin ein Test Button");
//		BorderPane testPane = new BorderPane();
//		testPane.setTop(testButton);
//		
//		Sektion demoSektion = new Sektion();
//		
//		
//		Group group = new Group(testPane);
//		Scene scene = new Scene(group);
//		
//		WeltraumSicht3 demo = new WeltraumSicht3(demoSektion, scene);
//
//		SubScene subScene = demo.getSceneSicht();
//		testPane.setCenter(subScene);
//
//		stage.setScene(scene);
//		stage.show();
		
	//---------------------------------------------------------------------------------------------------//
	}
}
