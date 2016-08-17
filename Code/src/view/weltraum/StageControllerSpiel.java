package view.weltraum;

import java.io.IOException;
import java.util.Vector;

import himmelskoerper.SchwarzesLoch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
	 * die Main Scene ist die wichtigste Scene. Sie beinhaltet eine BoarderPane in dieser befinde
	 * sich oben eine Menuleiste die immer bestehen bleibt und das im Zentrum befindet sich das Spielgeschehen. Das Zentrum 
	 * kann jeder zeit ausgetauscht werden 
	 */
	private Scene mainScene;
	
	/**
	 * das ist der Controller dier mainScene. Dieser wird zum beispiel benoetigt um das Zentrum auszutauschen
	 */
	private SpielUmgebungController spielUmgebungController; 
	
	/**
	 * die Stage des Spiels auf der das Spielgeschehn ablaeuft 
	 */
	private Stage stage; 
	

	public StageControllerSpiel(Stage stage)
	{	
		//--------------------Spiel-Umgebunng-wird-geladen-------------------------------------------//
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/weltraum/fxml/SpielUmgebung.fxml"));
		try{loader.load();}catch (IOException e){e.printStackTrace();}
		mainScene = new Scene(loader.getRoot());
		spielUmgebungController = loader.getController();
		spielUmgebungController.setScene(mainScene);
		
		//---------------------Demospiel-wird-erstellt-TODO-------------------------------------------------------------------//
		Sektion demoSektion = new Sektion();
		
		Vector<Double> position = new Vector<Double>();
		position.add(0.0);
		position.add(0.0);
		position.add(-200.0);
		WeltraumSicht demo = new WeltraumSicht(new SchwarzesLoch(4925), mainScene, position);
		SubScene subScene = demo.getSubScene();
		
		//---------------------In-der-Mitte-des-Fensters-wird-die-Spielumgebeung-plaziert------------------------------------------------------------------------------//
		this.wechselSicht(subScene);
	
		//-------------------Stage wird erstellt-----------------------------------------------------//
		stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		stage.setFullScreenExitHint("");
		stage.setFullScreen(true);
		stage.setScene(mainScene);
		stage.show();		
	}
	
	
	/**
	 * wechselt die Scene
	 * @param scene zu der gewechselt werden soll
	 */
	public void wechselScene(Scene scene)
	{
		this.stage.setScene(scene);
	}
	
	
	/**
	 * wechselet die Sicht es Gepielgesehens zu einer neuen Sicht. zuseatzlich wird die groe�e der SubScene an die groe�e des Zentrums gebunden
	 * 
	 * @param subScene wird in das Zentrum gesetzt
	 */
	public void wechselSicht(SubScene subScene)
	{
		subScene.widthProperty().bind(spielUmgebungController.getStackPaneZentrum().widthProperty());
		subScene.heightProperty().bind(spielUmgebungController.getStackPaneZentrum().heightProperty());
		spielUmgebungController.wechsleZentrum(subScene);	
	}
	
	
	/**
	 * wechselet die Sicht es Gepielgesehens zu einer neuen Sicht.
	 * @param node wird in das Zentrum gesetzt
	 */
	public void wechselSicht(Node node)
	{
		spielUmgebungController.wechsleZentrum(node);	
	}
}
