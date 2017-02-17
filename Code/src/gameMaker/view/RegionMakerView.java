package gameMaker.view;

import java.util.ArrayList;

import gameMaker.view.einstellungGameObjekte.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import personensicht.model.gameObjekte.Bett;
import personensicht.model.gameObjekte.GameObjekt;
import personensicht.model.gameObjekte.GameObjektType;
import personensicht.model.gameObjekte.Mensch;
import personensicht.model.gameObjekte.Schrank;
import personensicht.model.gameObjekte.Tuer;
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
	 * der String enthaelt den namen der Datei, die verwendet wird um die Region zu speichern
	 */
	private String path;
	
	/**
	 * die Scene die verwendet wird
	 */
	private Scene scene; 
	
	/**
	 * hier befindet sich die Node, die ausgewaeahlt wurde
	 */
	private Node ausgewaehterNode; 
	
	private BorderPane root = new BorderPane();
	
	/**
	 * Die Region, auf dem Objekte Platziert werden koennen
	 */
	private AnchorPane zentrumRegion = new AnchorPane();
	
	/**
	 * hier werden die GameObjekte drin gespeichert, die verwendet werden
	 */
	private ArrayList<AuswahlEinesGameObejekt> verwendeteGameObjekte = new ArrayList<AuswahlEinesGameObejekt>(); 
	
	public RegionMakerView() 
	{		
		//Eine MenuLeiste mit Menues. 
		MenuBar obereMenuLeiste= new MenuBar();
		//------------------Datei-------------------------------------//
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
		//--------------------------Region---------------------------//
		//Einstellungen zur Region zu ´m Beipiel die Groeße
		Menu regionEinstellungMenu = new Menu("Region");
		MenuItem size = new MenuItem("Größe");
		size.setOnAction(e -> einstellungRegionGroeße());
		regionEinstellungMenu.getItems().add(size);
		//------------------Hilfe------------------------------------//
		Menu hilfe = new Menu("Hilfe");
		MenuItem tutorial = new MenuItem("Tutorial"); //Funktion noch nicht vorhanden
		hilfe.getItems().addAll(tutorial);
		obereMenuLeiste.getMenus().addAll(datei,regionEinstellungMenu,hilfe);
		//-------------Eigenschaftenn-------------------------------------------------
		RegionEinstellungen regionEinstellungenTitlePane = new RegionEinstellungen();
		
		//in dieser Liste sollen sich alle GameObjekte aufhalten, die hinzugefuegt werden koennen
		VBox linkeGameObjektListe = new VBox();
		linkeGameObjektListe.setMaxWidth(200);
		GameObjektAuswalListeTitledPane moebel = new GameObjektAuswalListeTitledPane("Möbel");
		moebel.addGameObjekt("Schrank");
		moebel.addGameObjekt("Tür");
		moebel.addGameObjekt("Tisch");
		moebel.addGameObjekt("Stuhl");
		moebel.addGameObjekt("Sessel");
		moebel.addGameObjekt("Bett");
		moebel.addGameObjekt("Tischlampe");
		GameObjektAuswalListeTitledPane lebewesen = new GameObjektAuswalListeTitledPane("Mensch");
		moebel.addGameObjekt("Hund");
		moebel.addGameObjekt("Katze");
		moebel.addGameObjekt("Fisch");
		moebel.addGameObjekt("Affe");
		moebel.addGameObjekt("Maus");
		linkeGameObjektListe.getChildren().addAll(regionEinstellungenTitlePane,moebel,lebewesen);
		//--------------Zentrum-------------------------------------------------
		zentrumRegion.setPrefSize(Region.getSizemin(),Region.getSizemin());
		zentrumRegion.setStyle("-fx-background-color: GREY");
		zentrumRegion.setMinSize(Region.getSizemin(), Region.getSizemin());
		zentrumRegion.setMaxSize(Region.getSizemin(), Region.getSizemin());
		StackPane zentrum = new StackPane();
		zentrum.setPrefSize(Region.getSizemin()+200,Region.getSizemin()+200);
		zentrum.setStyle("-fx-background-color: BLACK");
		zentrum.setAlignment(Pos.CENTER);
		zentrum.getChildren().add(zentrumRegion);
		
		root.setTop(obereMenuLeiste);
		root.setLeft(linkeGameObjektListe);
		root.setCenter(zentrum);
		
		this.scene = new Scene(root);
		scene.setOnKeyReleased(new EventHandler<KeyEvent>(){			
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.DELETE){
					if (ausgewaehterNode != null){
						zentrumRegion.getChildren().remove(ausgewaehterNode);
						ausgewaehterNode = null;
						root.setRight(null);
					}
				}
			}
		});
	}
	
	
	public RegionMakerView(int sizeX, int sizeY)
	{
		this();
		if (sizeX >= Region.getSizemin() && sizeY >= Region.getSizemin())
			if (sizeX < Region.getSizemax() && sizeY < Region.getSizemax())
				zentrumRegion.setPrefSize(sizeX, sizeY);
			else
				System.err.println("Die eingestellte Größe ist zu Groß");
		else
			System.err.println("Die groeße für die Region ist zu klein");
	}
	
	
	/**
	 * laesst die Node den zeiger folgen und setzt das wirdGesetzt Boolen auf true
	 * @param node
	 */
	private void setzeGameObjektAufDieRegion(AuswahlEinesGameObejekt objekt)
	{
		//makiert die Reion als bearbeitet
		durtyBit = true; 
		//setzt Eigenschaften der Node
		Node node = objekt.getNode();
		node.setRotationAxis(Rotate.X_AXIS);
		node.setRotate(20);		
		
		zentrumRegion.getChildren().add(node);
		node.setOnMouseDragged(new EventHandler<MouseEvent>(){
			
			  @Override public void handle(MouseEvent mouseEvent) {
				  setPositionDesGameObjekt(node , mouseEvent);
			  }
		});	
		
		node.setOnMouseReleased(new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent mouseEvent) 
			{
				//Ueberprueft ob die Position setzbar ist. 
				Node nodeBlockerung = ueberpruefeDiePositionAufSetzbarkeit(node);
	
				if ( nodeBlockerung != null)  
				{
					setzteDiePositionDesNodesNeu(mouseEvent, node ,nodeBlockerung);
				}

					 if (node.getLayoutX()+node.getBoundsInLocal().getWidth() > zentrumRegion.getPrefWidth())
					 {
						 node.setLayoutX(zentrumRegion.getPrefWidth()-node.getBoundsInLocal().getWidth());
					 }
					 if (node.getLayoutY()+node.getBoundsInLocal().getHeight() > zentrumRegion.getPrefHeight())
					 {
						 node.setLayoutY(zentrumRegion.getPrefHeight()-node.getBoundsInLocal().getHeight());
					 }
					 if (node.getLayoutX() < 0)
					 {
						 node.setLayoutX(1);
					 }
					 if (node.getLayoutY() < 0)
					 {
						 node.setLayoutY(1);
					 }
				}	
		});
		
		node.setOnMouseClicked(new EventHandler<Event>() 
		{
			@Override
			public void handle(Event event) 	{
				root.setRight(objekt.getEinstellungGameObjket());	
			}
		});
	}
	

	//setzt die Position einer Node auf die Position des Coursers
	private void setPositionDesGameObjekt(Node node, MouseEvent mouseEvent){
		 node.setLayoutX(mouseEvent.getSceneX()-root.getCenter().getLayoutX()-zentrumRegion.getLayoutX());
		 node.setLayoutY(mouseEvent.getSceneY()-root.getCenter().getLayoutY()-zentrumRegion.getLayoutY());
		 ausgewaehterNode = node; 
		 durtyBit = true; 
	}

	/**
	 * Ueberprueft ob eine Node setzbar ist.
	 * @param node
	 * @return
	 */
	private Node ueberpruefeDiePositionAufSetzbarkeit(Node node)
	{
		for (int i = 0; this.verwendeteGameObjekte.size() > i ; i++)
		{
			if (this.verwendeteGameObjekte.get(i).getNode().intersects(node.getBoundsInLocal())){
				System.out.println(i);
//				System.out.println("Node      Positionin X: "+node.getLayoutX()+" Y: "+node.getLayoutY()+"   Bounds: X "+node.getBoundsInLocal().getWidth()+" Y: "+node.getBoundsInLocal().getHeight());
//				System.out.println("BlockNode Positionin X: "+this.verwendeteGameObjekte.get(i).getNode().getLayoutX()+" Y: "+this.verwendeteGameObjekte.get(i).getNode().getLayoutY()+"   Bounds: X "+this.verwendeteGameObjekte.get(i).getNode().getBoundsInLocal().getWidth()+" Y: "+this.verwendeteGameObjekte.get(i).getNode().getBoundsInLocal().getHeight());
				return this.verwendeteGameObjekte.get(i).getNode(); 
			}			
		}
		return null;
	}
	
	/**
	 * falls eine Node nicht gesetzt werden kann, da eine andere Node diese Blockiert, dann wird diese auf eine neue 
	 * Position umgestzt
	 * 
	 * @param node
	 * @param blockiernderNode
	 */
	private Boolean setzteDiePositionDesNodesNeu(MouseEvent mouseEvent, Node node, Node blockiernderNode)
	{
		//nach den bestmoeglichen setzbare freien Platz ausschau halten.
	    Bounds bounds = node.getBoundsInLocal();
	    
	    double x =  blockiernderNode.getLayoutX();
	    double y =  blockiernderNode.getLayoutY();
	    
	    int xRichtung = 2; 
	    int yRichtung = 2; 
	    Node testNode = null;
	   for (int r = 0; yRichtung > r ; r++)
	   {
		   for (int i = 0; xRichtung > i ; i++)
		   {
				node.setLayoutX(x+bounds.getWidth()*i);
				node.setLayoutY(y+bounds.getWidth()*r);
				testNode = ueberpruefeDiePositionAufSetzbarkeit(node);
				if (testNode != null)
						break;
				node.setLayoutX(x-bounds.getWidth()*i);
				node.setLayoutY(y-bounds.getWidth()*r);
				testNode = ueberpruefeDiePositionAufSetzbarkeit(node);
				if (testNode != null)
					break;
				node.setLayoutX(x+bounds.getWidth()*i);
				node.setLayoutY(y-bounds.getWidth()*r);
				testNode = ueberpruefeDiePositionAufSetzbarkeit(node);
				if (testNode != null)
					break;
				node.setLayoutX(x-bounds.getWidth()*i);
				node.setLayoutY(y+bounds.getWidth()*r);
				testNode = ueberpruefeDiePositionAufSetzbarkeit(node);
				if (testNode != null)
					break;
				
			
		   }
		   if (testNode != null)
				break;
	   } 
	   if (testNode == null)
	   {
		   return false; 
	   }
	   return true; 
	}
	
	
	private void einstellungRegionGroeße()	
	{
		//oeffne ein Wizard um die Große einzustellen
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
	 * wird verwendet von der RegionMakerView um GameObjekte auszuwaehlen. 
	 * Diese befinden sich am Linken Bildschirmrand 
	 * @author Dennis
	 *
	 */
	private class GameObjektAuswalListeTitledPane extends TitledPane
	{		
		/**
		 * In dieser Liste befinden sich die GameObejkte, die dargestellt werden
		 */
		private ListView<String> GameObjektAuswalListeTitledPane = new ListView<String>();
		private Boolean auswahlGetroffen = false; 	
		
		protected GameObjektAuswalListeTitledPane(String nameDerListe)
		{
			this.setMaxWidth(200);
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setContent(GameObjektAuswalListeTitledPane);
			this.setText(nameDerListe);
			this.setContent(scrollPane);
				
			GameObjektAuswalListeTitledPane.setOnMouseDragged(new EventHandler<MouseEvent>(){
			
				  @Override public void handle(MouseEvent mouseEvent) {
					  if (auswahlGetroffen == false)
					  {
						  verwendeteGameObjekte.add(new AuswahlEinesGameObejekt( GameObjekt.getTypeOfGameObjekt(GameObjektAuswalListeTitledPane.getSelectionModel().getSelectedItem() ) ) );
						  setzeGameObjektAufDieRegion(verwendeteGameObjekte.get(verwendeteGameObjekte.size()-1));
						  setAuswahlGetroffen(true);
					  }
						  setPositionDesGameObjekt( verwendeteGameObjekte.get(verwendeteGameObjekte.size()-1).getNode(), mouseEvent);
				  }
			});	
			GameObjektAuswalListeTitledPane.setOnMouseReleased(new EventHandler<MouseEvent>(){
				
				  @Override public void handle(MouseEvent mouseEvent) {
					  setAuswahlGetroffen(false);
				  }
			});
			
		}
		

		public void addGameObjekt(String name){
			this.GameObjektAuswalListeTitledPane.getItems().add(name);
		}
		
		private void setAuswahlGetroffen(Boolean wahl)
		{
			auswahlGetroffen= wahl;
		}
	}
	
	/**
	 * Diese Klasse beinhaltet Methoden, die es ermoeglichen sollen, GaameObjekte aus einer ViewList auszuwaehlen 
	 * und auf der Region zu platzieren.
	 * 
	 * @author Dennis
	 */
	private class AuswahlEinesGameObejekt
	{
		private Node node; 
		/**
		 * Rechte Anzeige, mit der Einstellungen am GameObjet unternommen werden koennen
		 */
		private EinstellungGameObjekt einstellungGameObjket; 
		
		
		private AuswahlEinesGameObejekt(GameObjektType type) 
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
				Mensch mensch = new Mensch("DefaultName");
				einstellungGameObjket = new EinstellungMensch(mensch);
				break;
			case Schrank:
				Schrank schrank = new Schrank();
				einstellungGameObjket = new EinstellungSchrank(schrank);
				break;
			case Tuer:
				Tuer tuer = new Tuer();
				einstellungGameObjket = new EinstellungTuer(tuer); 
				break;
			default:
				break;
			}
			
			this.node = einstellungGameObjket.getObjekt().ladeNodeObjekt(); 
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
