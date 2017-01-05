package view.aufbaumodus.fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * 
 * Hier wird das Spiel abgebildete mit saemtlichen Menus
 * @author Dennis
 *
 */
public class SpielUmgebungController implements Initializable
{
	
	@FXML
	private StackPane oberst;
	
	public synchronized StackPane getOberst() {
		return oberst;
	}


	public synchronized void setOberst(StackPane oberst) {
		this.oberst = oberst;
	}

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
	
	/**
	 * Die Obere Menuleiste
	 */
	@FXML
	private HBox menuOnTop;
	
	/**
	 * ein Spiel Menu
	 */
	@FXML 
	private VBox spielMenu;
	
	@FXML
	private VBox informationsBox;
	
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
	
	//-------------------------Buttons-Spiel-Menu-------------------------------------------------------------------//
	
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
	 * ein Button um das Spielmen wieder zu verlassen
	 */
	@FXML
	private Button back;
	//-----------------------------------------------------------------------------------------------------//
	
	
	/**
	 * unterstuetzt das navigieren in einer Sektion. Beinhaltet eine Liste von Planeten
	 */
	@FXML
	private VBox navigationBox;
		
	
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
		//----------------------------Obere-Menuleiste-Buttons------------------------------------//
		if(e.getSource() == spielMenuButton)
		{
			oeffneMenu();
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
		//-------------------------------------Spielmenu-Buttons-----------------------------------------------------//
		else if (e.getSource() == laden)
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
		else if (e.getSource() == back)
		{
			verlasseDasSpielMenu();
		}
	}
	
	
	/**
	 * setzt ein neues Zentrum
	 * @param subScene wird in die mitte des BorderPanes gesetzt. 
	 */
	public void wechsleZentrum(SubScene subScene)
	{
		subScene.widthProperty().bind(getStackPaneZentrum().widthProperty());
		subScene.heightProperty().bind(getStackPaneZentrum().heightProperty());
		centerPane.getChildren().clear();
		centerPane.getChildren().addAll(subScene);
	}
	
	
//	/**
//	 * setzt eine Scene von der KeyEvents abgefangen werden
//	 */
//	public void setScene(Scene scene)
//	{
//		System.out.println("Die Scene wurde der Spielumgebung übergeben");
//		this.scene = scene;
//	}
	
	
	/**
	 * gib das Zentrum von der BorderPane wieder die zum wechseln der Sicht wichtig ist
	 */
	public StackPane getStackPaneZentrum()
	{
		return centerPane;
	}
	
	
	/**
	 * gibt die HBox der Oberen Menuleiste wieder
	 * @return
	 */
	public HBox getMenuOnTop()
	{
		return this.menuOnTop;
	}
	
	
	/**
	 * offnet das SpielMenu
	 */
	private void oeffneMenu()
	{
		ScaleTransition trainsition = new ScaleTransition(Duration.seconds(2) ,  this.spielMenu);
		trainsition.setFromX(0.001);
		trainsition.setFromY(0.001);
		trainsition.setToX(2);
		trainsition.setToY(2);
		
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), this.spielMenu);
		fadeTransition.setFromValue(0.0001);
		fadeTransition.setToValue(1);
		
		fadeTransition.play();
		trainsition.play();

		spielMenu.setVisible(true);
	}
	
	
	/**
	 * verlaest das Spielmenu wieder
	 */
	private void verlasseDasSpielMenu()
	{
		ScaleTransition trainsition = new ScaleTransition(Duration.seconds(2) ,  this.spielMenu);
		
		trainsition.setFromX(2);
		trainsition.setFromY(2);
		trainsition.setToX(0.1);
		trainsition.setToY(0.1);
		
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), this.spielMenu);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0.0001);
		
		fadeTransition.play();
		trainsition.play();
		
		new Runnable()
		{
			@Override
			public void run() 
			{
				while (fadeTransition.getStatus() == Status.RUNNING)
				{
					try {
						Thread.sleep(100);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				spielMenu.setVisible(false);
				
			}	
		};
	}
	
	
	/**
	 * fuegt informationen einer VBox hinzu, die der Spieler sehen soll
	 * @param node
	 */
	public void setzeInformationen(Node node)
	{
		informationsBox.getChildren().add(node);
	}
	
	
	/**
	 * loescht die Informationen aus derVBox
	 */
	public void clearInformationen()
	{
		informationsBox.getChildren().clear();
	}
	
	public void setRightPane(Node node)
	{
		this.borderPane.setRight(node);
	}
	
	public void setLeftPane(Node node)
	{
		this.borderPane.setLeft(node);
	}
	
	public void setBottomPane(Node node)
	{
		 this.borderPane.setBottom(node);
	}
}
