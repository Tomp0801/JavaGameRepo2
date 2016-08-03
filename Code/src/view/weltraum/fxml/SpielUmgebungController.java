package view.weltraum.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
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
	
	//---------------------------Oberre-MenuLeiste--------------------------------------------------//
	@FXML
	private Button spielMenuButton;
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
	}
	
	
	/**
	 * setzt ein neues Zentrum
	 * @param subScene wird in die mitte des BorderPanes gesetzt. 
	 */
	public void wechsleZentrum(SubScene subScene)
	{
		borderPane.setCenter(subScene);
	}
}
