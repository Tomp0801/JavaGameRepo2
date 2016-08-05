package view.weltraum.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Das Spiel Menu
 * 
 * @author Dennis
 */
public class SpielMenuController implements Initializable
{
	/**
	 * ein Button um ein anders Spiel zu laden
	 */
	@FXML
	private Button laden;
	
	/**
	 * ein Button um ein Spiel zu speichern
	 */
	@FXML
	private Button speichern;
	
	/**
	 * ein Button um in die Einstellungen zu gelangen
	 */
	@FXML
	private Button einstellungen;
	
	/**
	 * ein Button um das Spiel zu beenden
	 */
	@FXML
	private Button beenden;
	
	/**
	 * in dieser Box befinden sich saemtliche Menubuttons
	 */
	@FXML
	private VBox menuBox;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
			
	}
	
	@FXML
	public void actinHandler(ActionEvent e)
	{
		if (e.getSource() == laden)
		{
			System.err.println("Die Ladefunktion ist noch nicht verfügbar");
		}
		else if (e.getSource() == speichern)
		{
			System.err.println("Die Speicherfunktion ist noch nicht verfügbar");
		}
		else if (e.getSource() == einstellungen)
		{
			System.err.println("Die Einstellungen sind noch nicht verfügbar");
		}
		else if (e.getSource() == beenden)
		{
			//TODO
		}
		
	}
	
}
