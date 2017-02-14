package gameMaker.view;

import java.util.ArrayList;

import gameMaker.view.einstellungGameObjekte.EinstellungBett;
import gameMaker.view.einstellungGameObjekte.EinstellungGameObjekt;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import personensicht.model.gameObjekte.Bett;
import personensicht.model.gameObjekte.GameObjektType;
import personensicht.model.welt.map.Region;

/**
 * Diese Scene soll das erstellen von Regionen mit einer GUI ermoeglichen
 * @author Dennis
 *
 */
public class RegionMakerView
{
	/**
	 * falls aenderungen an der Region vorgenommen wurde, muss das durtyBit auf true gesetzt werden, um 
	 * bei eventuellen neu laden oder Beenden ect. Nach dem Speichern gefragt werden kann. 
	 */
	private Boolean durtyBit = false; 
	
	/**
	 * wenn ein GameObjekt gesetzt wird, dann muss dises Boolean auf true gesetzt werden. 
	 */
	private Boolean wirdGesetzt = false;
	
	/**
	 * der String enthaelt den namen der Datei, die verwendet wird um die Region zu speichern
	 */
	private String path;
	
	/**
	 * die Scene die verwendet wird
	 */
	private Scene scene; 
	
	
	private BorderPane root = new BorderPane();
	
	/**
	 * Die Region, auf dem Objekte Platziert werden koennen
	 */
	private AnchorPane zentrumRegion = new AnchorPane();
	
	/**
	 * hier werden die GameObjekte drin gespeichert, die verwendet werden
	 */
	private ArrayList<AusgewaehltesGameObejekt> verwendeteGameObjekte = new ArrayList<AusgewaehltesGameObejekt>(); 
	
	public RegionMakerView() 
	{
		//Eine MenuLeiste mit Menues. 
		MenuBar obereMenuLeiste= new MenuBar();
		//------------------Datei-------------------------------//
		Menu datei = new Menu("Datei");
		MenuItem neu = new MenuItem("neu");
		neu.setOnAction(e -> erstelleNeueRegion());
		MenuItem laden = new MenuItem("laden");
		laden.setOnAction(e -> ladeAndereRegion());
		MenuItem speichern = new MenuItem("speichern");
		speichern.setOnAction(e -> speicherRegion());
		MenuItem speichernAls = new MenuItem("speichern unter");
		speichernAls.setOnAction(e -> speicherUnterNameRegion());
		datei.getItems().addAll(neu,laden,speichern,speichernAls);
		//--------------------------Region-------------------------//
		//Einstellungen zur Region zu ¥m Beipiel die Groeﬂe
		Menu regionEinstellung = new Menu("Region");
		MenuItem size = new MenuItem("Grˆﬂe");
		size.setOnAction(e -> einstellungRegionGroeﬂe());
		regionEinstellung.getItems().add(size);
		obereMenuLeiste.getMenus().addAll(datei,regionEinstellung);
		
		//in dieser Liste sollen sich alle GameObjekte aufhalten, die hinzugefuegt werden koennen
		VBox linkeGameObjektListe = new VBox();
		linkeGameObjektListe.setMaxWidth(200);
		GameObjektListe moebel = new GameObjektListe("Mˆbel");
		moebel.addGameObjekt("Schrank");
		moebel.addGameObjekt("T¸r");
		moebel.addGameObjekt("Tisch");
		moebel.addGameObjekt("Stuhl");
		moebel.addGameObjekt("Sessel");
		moebel.addGameObjekt("Bett");
		moebel.addGameObjekt("Tischlampe");
		GameObjektListe lebewesen = new GameObjektListe("Mensch");
		moebel.addGameObjekt("Hund");
		moebel.addGameObjekt("Katze");
		moebel.addGameObjekt("Fisch");
		moebel.addGameObjekt("Affe");
		moebel.addGameObjekt("Maus");
		linkeGameObjektListe.getChildren().addAll(moebel,lebewesen);
		//--------------Zentrum-------------------------------------------------
		zentrumRegion.setPrefSize(Region.getSizemin(),Region.getSizemin());

		
		root.setTop(obereMenuLeiste);
		root.setLeft(linkeGameObjektListe);
		root.setCenter(zentrumRegion);
		
		this.scene = new Scene(root);
	}
	
	
	public RegionMakerView(int sizeX, int sizeY)
	{
		this();
		if (sizeX >= Region.getSizemin() && sizeY >= Region.getSizemin())
			if (sizeX < Region.getSizemax() && sizeY < Region.getSizemax())
				zentrumRegion.setPrefSize(sizeX, sizeY);
			else
				System.err.println("Die eingestellte Grˆﬂe ist zu Groﬂ");
		else
			System.err.println("Die groeﬂe f¸r die Region ist zu klein");
	}
	
	
	/**
	 * laesst die Node den zeiger folgen und setzt das wirdGesetzt Boolen auf true
	 * @param node
	 */
	private void setzeGameObjekt(AusgewaehltesGameObejekt objekt)
	{
		Node node = objekt.getNode();
		wirdGesetzt = true; 
		zentrumRegion.getChildren().add(node);
		node.setOnMouseDragged(new EventHandler<MouseEvent>() 
		{
			  @Override public void handle(MouseEvent mouseEvent) 
			  {
				 node.setLayoutX(mouseEvent.getSceneX()-root.getCenter().getLayoutX());
				 node.setLayoutY(mouseEvent.getSceneY()-root.getCenter().getLayoutY());
			  }
		});	
		
		node.setOnMouseClicked(new EventHandler<Event>() 
		{

			@Override
			public void handle(Event event) 
			{
				root.setRight(objekt.getEinstellungGameObjket());
				
			}
		});
	}
	
	private void einstellungRegionGroeﬂe()	
	{
		//oeffne ein Wizard um die Groﬂe einzustellen
	}
	
	
	/**
	 * speichert die erstellte Region in die Datei, die vorher verwendet wurde
	 */
	private void speicherRegion()
	{
		//nur moeglich wenn was geandert wurde. Sonst ausgegraut oder nicht anklickbar
		//speichert unter den verwendeten namen
	}
	
	/**
	 * speichert die erstellte Region
	 */
	private void speicherUnterNameRegion()
	{
		//suche eine Datei unter der gespeichert werden soll
		//endung hinzufuegen
	}
	
	/**
	 * lead eine andere Region
	 */
	private void ladeAndereRegion()
	{
		//Oeffne ein Ordner in dennen Regionen gespeichert sind
		//nach dem auswahlen einer Datei, nach speichern fragen, falls was geaendert wurde
	}
	
	/**
	 * erstellt eine Neue Region
	 */
	private void erstelleNeueRegion()
	{
		//nach dem Speichern fragen, falls was geaendert wurde
		//oeffne eine Wizard um dort die Einsstellungen fuer die neue Region fest zu legen
	}


	public synchronized Scene getScene() {
		return scene;
	}
	
	
	/**
	 * gibt den Typ eines GamObjektes zurueck. 
	 * @param type
	 * @return
	 */
	private static GameObjektType getTypeOfGameObjekt(String type)
	{
		switch (type)
		{
		case "Schrank":
			return GameObjektType.Schrank;
		case "Bett":
			return GameObjektType.Bett;
		default: 
			System.err.println("Fehler der Typ: "+type+" gib es nicht in Der Klasse RegionMakerView, Methode GameObjekteType");
			return null;
		}
	}
	
	
	
	/**
	 * wird verwendet von der RegionMakerView um GameObjekte auszuwaehlen. 
	 *  
	 * @author Dennis
	 *
	 */
	private class GameObjektListe extends TitledPane
	{		
		/**
		 * In dieser Liste befinden sich die GameObejkte, die dargestellt werden
		 */
		private ListView<String> gameObjekteListe = new ListView<String>();
		
		
		protected GameObjektListe(String nameDerListe)
		{
			this.setMaxWidth(200);
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setContent(gameObjekteListe);
			this.setText(nameDerListe);
			this.setContent(scrollPane);
			gameObjekteListe.setOnMousePressed(new EventHandler<Event>() 
			{
				@Override
				public void handle(Event event) 
				{
					if (gameObjekteListe.getSelectionModel().getSelectedItem() != null)
					{
						//ein GameObjekt des ausgewaehlten typs wird erstellt
						verwendeteGameObjekte.add(new AusgewaehltesGameObejekt( getTypeOfGameObjekt( gameObjekteListe.getSelectionModel().getSelectedItem() ) ) );
						setzeGameObjekt(verwendeteGameObjekte.get(verwendeteGameObjekte.size()-1));
					}
				}
			});
		}
		

		public void addGameObjekt(String name)
		{
			this.gameObjekteListe.getItems().add(name);
		}
		
	}
	
	private class AusgewaehltesGameObejekt
	{
		private Node node; 
		/**
		 * Rechte Anzeige, mit der Einstellungen am GameObjet unternommen werden koennen
		 */
		private EinstellungGameObjekt einstellungGameObjket; 
		
		
		private AusgewaehltesGameObejekt(GameObjektType type) 
		{
			switch (type)
			{
			case Bett:
				Bett bett = new Bett(); 
				einstellungGameObjket = new EinstellungBett(bett); 
				break; 
			case Item:
				break;
			case Mensch:
				break;
			case Schrank:
				break;
			case Tuer:
				break;
			default:
				break;
			}
			//TODO richtigen einstellungGameObjekt waehlen
			
			
			this.node = einstellungGameObjket.getObjekt().ladeNodeObjekt(); 
		}
		
		/**
		 * ueberprueft ob die uebergebene Node mit der zugehoerigen Node uebereinstimmt
		 * @param node 
		 * @return
		 */
		private Boolean isNode(Node node)
		{
			return this.node == node; 
		}

		public synchronized EinstellungGameObjekt getEinstellungGameObjket() 
		{
			return einstellungGameObjket;
		}

		public synchronized Node getNode() {
			return node;
		}	
	}
}
