package view.weltraum.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * 
 * Hier wird das Spiel abgebildete mit saemtlichen Menus
 * @author Dennis
 *
 */
public class SpielUmgebungController implements Initializable
{
	/**
	 * die oberste Node. 
	 * 
	 * OnTop befindet sich saemtliche Menueinstellungen
	 * 
	 * im Centrum befindet sich das Spielfeld. Das Zentrum ist austauschbar.
	 */
	@FXML
	private BorderPane borderPane;
	
	/**
	 * dieses StackPane befindet sich in der Mitte der BorderPane
	 */
	@FXML 
	private StackPane centerPane;
	
	
	
	//---------------------------Oberre-MenuLeiste--------------------------------------------------//
	/**
	 * oeffnet das Spielmenu
	 */
	@FXML
	private Button spielMenuButton;
	
	/**
	 * wechselt in den Aufbau Modus
	 */
	@FXML
	private Button modusAufbau;
	
	/**
	 * wechselt in den Kampf Modus
	 */
	@FXML
	private Button modusKampf;
	
	/**
	 * wechselt in den Handels Modus
	 */
	@FXML
	private Button modusHandel;
	
	/**
	 * wechselt in den Diplomaten Modus
	 */
	@FXML
	private Button modusDiplomatie;
	
	/**
	 * wechselte in den Abendteuermodus
	 */
	@FXML
	private Button modusAbenteuer;
	
	//----------------------------------------------------------------------------------------------//
	
	
	/**
	 * initialisiert den Controller
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{}
	
	
	/**
	 * behandelt die Events von Buttons
	 */
	@FXML
	public void actionHandler(ActionEvent e)
	{
		if(e.getSource() == spielMenuButton)
		{
			//TODO oeffne das Menu (SpielMenu.fxml)
		}
		else if (e.getSource() == modusAufbau)
		{
			System.err.println("Aufbaumodus noch nicht vorhanden");
			//TODO	
		}
		else if (e.getSource() == modusKampf)
		{
			System.err.println("Kampfmodus noch nicht vorhanden");
			//TODO	
		}
		else if (e.getSource() == modusHandel)
		{
			System.err.println("Handelsmodus noch nicht vorhanden");
			//TODO	
		}
		else if (e.getSource() == modusDiplomatie)
		{
			System.err.println("Aufbaumodus noch nicht vorhanden");
			//TODO	
		}
		else if (e.getSource() == modusAbenteuer)
		{
			System.err.println("Diplomatiemodus noch nicht vorhanden");
			//TODO	
		}
			
	}
	
	
	/**
	 * setzt ein neues Zentrum
	 * @param subScene wird in die mitte des BorderPanes gesetzt. 
	 */
	public void wechsleZentrum(SubScene subScene)
	{
		centerPane.getChildren().clear();
		centerPane.getChildren().add(subScene);
	}
	
	
	/**
	 * gib das Zentrum von der BorderPane wieder die zum wechseln der Sicht wichtig ist
	 */
	public StackPane getStackPaneZentrum()
	{
		return centerPane;
	}
}
