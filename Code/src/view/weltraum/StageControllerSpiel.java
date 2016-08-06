package view.weltraum;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.KeyCombination;
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
		System.out.println("Sind in der Klasse StageControllerSpiel");
		//--------------------Spiel-Umgebunng-wird-geladen-------------------------------------------//
		System.out.println("lade fxml Datei: "+"/view/weltraum/fxml/SpielUmgebung.fxml");
		loader = new FXMLLoader(getClass().getResource("/view/weltraum/fxml/SpielUmgebung.fxml"));
		try{loader.load();}catch (IOException e){e.printStackTrace();}
		spielSceneDemo = new Scene(loader.getRoot());
		spielUmgebungController = loader.getController();
		spielUmgebungController.setScene(spielSceneDemo);
		System.out.println("der Controller ist = null?:    "+(spielUmgebungController == null));
		//---------------------Demospiel-wird-erstellt--------------------------------------------------------------------//
		
		Sektion demoSektion = new Sektion();
		WeltraumSicht3 demo = new WeltraumSicht3(demoSektion, spielSceneDemo);
		SubScene subScene = demo.getSubScene();
		System.out.println("Sind wieder im StageControllerSpiel");
		System.out.println("Sektion = null=? "+(demoSektion==null));
		System.out.println("ist die SubScene = null?   "+(subScene==null));
		System.out.println("ist demo = null??   "+(demo==null));

		//-------------------die-groeﬂe-der-SubScene-wird-an-der-groeﬂe-einer-StackPane-gebunden---------------------//
		subScene.widthProperty().bind(spielUmgebungController.getStackPaneZentrum().widthProperty());
		subScene.heightProperty().bind(spielUmgebungController.getStackPaneZentrum().heightProperty());
		
		//---------------------In-der-Mitte-des-Fensters-wird-die-spielumgebeung-plaziert------------------------------------------------------------------------------//
		spielUmgebungController.wechsleZentrum(subScene);	
		//-------------------Stage wird erstellt-----------------------------------------------------//
		stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		stage.setFullScreenExitHint("");
		stage.setFullScreen(true);
		System.out.println("Scene wird gesetzt");
		stage.setScene(spielSceneDemo);
		stage.show();
		
		System.out.println("groﬂe der SubScene(x/y):      "+subScene.getWidth()+"/"+subScene.getHeight()+"  die grˆﬂe muss nicht identisch sein. sie darf aber nicht klein sein also nicht zwischen 0 und 100 liegen   ");
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
