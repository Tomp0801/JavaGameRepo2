package gameMaker.controll;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * setzt die Scene und bietet eine Methode um diese weder zu wechseln
 *  
 * @author Dennis
 *
 */
public class StageCrt extends Application
{
	/**
	 * Die Stage des Spiels
	 */
	private Stage stage;
	
	/**
	 * verweist aus sich selbst
	 */
	private static StageCrt stageController;
	
	public static void main(String[] args) 
	{
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		StageCrt.stageController = this;
		this.stage = primaryStage; 
//		this.setScene(RegionMakerScene.getInstance().getScene());
		RegionMakerCrt.getInstance().startGameMakerCrt();
		primaryStage.show();
	}
	
	public void setScene(Scene scene)
	{
		this.stage.setScene(scene);
		this.stage.setFullScreen(true);
	}
	
	public static StageCrt getInstance()
	{
		return stageController;
	}

	public synchronized Stage getStage() {
		return stage;
	}
}
