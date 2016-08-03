package view.weltraum;

import com.sun.corba.se.impl.protocol.BootstrapServerRequestDispatcher;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import map.Sektion;

/**
 * 
 * Hier werden die Einzelnen Scenen verwaltet.
 * 
 * @author Dennis
 *
 */
public class StageController extends Application
{
		int i = 0;
	public static void main(String[] args) 
	{
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception 
	{
		Button testButton = new Button("Ich bin ein Test Button");
		BorderPane testPane = new BorderPane();
		testPane.setTop(testButton);
		
		Sektion demoSektion = new Sektion();
		
		
		Group group = new Group(testPane);
		Scene scene = new Scene(group);
		
		WeltraumSicht3 demo = new WeltraumSicht3(demoSektion, scene);

		SubScene subScene = demo.getSceneSicht();
		testPane.setCenter(subScene);

		stage.setScene(scene);
		stage.show();
		
	
		System.out.println("Demo Spiel wurde gestartet");
	}

}
