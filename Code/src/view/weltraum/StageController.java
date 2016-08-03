package view.weltraum;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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
public class StageController extends Application
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
	
	public static void main(String[] args) 
	{
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception 
	{
		//Damit man nicht mit ESCAP den FullScreen schließen kann
		stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		//Damit keine Nachricht erscheint nach dem eröffnen des FullScreens
		stage.setFullScreenExitHint("");
		stage.setFullScreen(true);

		loader = new FXMLLoader(getClass().getResource("/view/weltraum/fxml/SpielUmgebung.fxml"));
		loader.load();
		
		spielUmgebungController = loader.getController();
		spielSceneDemo = new Scene(loader.getRoot());
		
		Sektion demoSektion = new Sektion();
		WeltraumSicht3 demo = new WeltraumSicht3(demoSektion, spielSceneDemo);
		spielUmgebungController.wechsleZentrum(demo.getSceneSicht());
		
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
