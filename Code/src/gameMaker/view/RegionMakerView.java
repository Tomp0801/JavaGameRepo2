package gameMaker.view;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.scene.shape.Shape3D;
import javafx.scene.transform.Rotate;
import personensicht.model.gameObjekte.GameObjekt;
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
	 * hier werden die GameObjekte drin gespeichert, die sich auf der Region befinden
	 */
	ArrayList<AuswahlGameObjekt> gameObjekteOnRegion = new ArrayList<AuswahlGameObjekt>(); 
	
	/**
	 * variable fuer das Umsetzten von Nodes.
	 */
	private double layoutX = 0;
	/**
	 * variable fier das Umsetzen von Nodes
	 */
	private double layoutY = 0;  
	
	private static RegionMakerView instance ;
	//--------------------------------------------Konsturktor----------------------------------------------------------------------//
	private RegionMakerView() 
	{		
		instance = this; 
		MenuGameMaker obereMenuLeiste = new MenuGameMaker();
		
		ListeGameObjektLinks objektListe = new ListeGameObjektLinks();
		
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
		root.setLeft(objektListe);
		root.setCenter(zentrum);
		
		this.scene = new Scene(root);
		scene.setOnKeyReleased(new EventHandler<KeyEvent>(){			
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.DELETE){
					if (ausgewaehterNode != null){
						removeNodeFromRegion(ausgewaehterNode);
						ausgewaehterNode = null;
						root.setRight(null);
					}
				}
			}
		});
	}
	
	/**
	 * loescht eine Node von der Region
	 * @param node
	 */
	void removeNodeFromRegion(Node node)
	{
		zentrumRegion.getChildren().remove(node);
		gameObjekteOnRegion.remove(node);
	}
	
	/**
	 * erstellt eine RegionMakerView mit vorgegebener Groeﬂe des Zentrums
	 * @param sizeX
	 * @param sizeY
	 */
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
	
	//----------------------------------------------------------------------------------------------------------------------------//
	
	/**
	 * platzierzt eine Mauer
	 */
	void platzierungMauer(AuswahlGameObjekt objekt)
	{
		Node node = objekt.getNode();
		setNodeOnRegion(node);
		MauerSetzHandler mouseHandler = new MauerSetzHandler(objekt.getEinstellungGameObjket().getGameObjekt());
		node.setOnMouseDragged(mouseHandler);
	
		node.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				mouseHandler.setStartZustand();
			}
		});	
		//TODO
	}
	
	/**
	 * setzt eine Node auf die Region und gibt diese benoetigte Eigenschaften
	 */
	private void setNodeOnRegion(Node node){
		//makiert die Region als bearbeitet
		durtyBit = true; 
		//setzt Eigenschaften der Node
		node.setRotationAxis(Rotate.X_AXIS);
//		node.setRotate(20);	 vorerst	
		// setzt die Node auf die Region
		zentrumRegion.getChildren().add(node);
	}
	
	/**
	 * seztz ein GameObjekt mithilfe der Kalsse AuswahlEinesGameObejekt auf das zentrum (Region).
	 * Dabei wird das durtyBit auf true gesetzt, um zu makieren, das dass Projekt bearbeitet wurde. 
	 * Rotiert die Node, um diese in 3D zu sehen
	 * @param node
	 */
	void setzeGameObjektAufDieRegion(AuswahlGameObjekt objekt)
	{
		Node node = objekt.getNode();
		setNodeOnRegion(node);

		
		//erstellt EventHandler. Um das Umsetzen einer Node zu ermoeglichen
		node.setOnMouseDragged(new EventHandler<MouseEvent>(){
			
			  @Override public void handle(MouseEvent mouseEvent) {
				  //laesst die Node dem Courser folgen
				  setPositionDesGameObjekt(node , mouseEvent);
			  }
		});	
		
		//Dieses Event, beschreibt was passiert wenn die ein Node gesetzt wird. Dazu wird ueberprueft ob diese Position
		//schon von einer andern Node besetzt ist.
		node.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) 	{
				//Ueberprueft ob die Position setzbar ist. Wenn dies nicht der Falls ist, wird de Node, die blockiert 
				//auf nodeBlockierung referenziert
				Node nodeBlockierung = ueberpruefeDiePositionAufSetzbarkeit(node);
				//blockiert eine Node den Ziel Ort, dann ...
				if (nodeBlockierung != null)
				{
					//schaue on die Node ueberhaupt setztbat ist und wenn nicht dann ...
					if (wennNodeSetzbarDannSetzeAufDieMoeglichePosition(node, nodeBlockierung) == false)
					{
						//versetze die Node auf ihre anfangsPosition zurueck
						node.setLayoutX(layoutX);
						node.setLayoutY(layoutY);
					}
				}
					randNodeSetztTest(node);
				}	
		});
		node.setOnMousePressed(new EventHandler<Event>() 
		{
			@Override
			public void handle(Event event) {
				changPositionVonNode(node.getLayoutX(), node.getLayoutY());	
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
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	private void changPositionVonNode(double x , double y)
	{
		layoutX = x; 
		layoutY = y; 
	}
	
	/**
	 * ueberprueft ob eine gesetzte Node innerhalb der Region liegt. Und setzt diese gegebenfalls auf den Rand
	 * @param node
	 */
	void randNodeSetztTest(Node node)
	{
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

	//setzt die Position einer Node auf die Position des Coursers
	void setPositionDesGameObjekt(Node node, MouseEvent mouseEvent){
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
	Node ueberpruefeDiePositionAufSetzbarkeit(Node node)
	{
		for (int i = 0; this.gameObjekteOnRegion.size() > i ; i++)
		{
			if (this.gameObjekteOnRegion.get(i).getNode() != node)
			{
				if (this.gameObjekteOnRegion.get(i).getNode().getBoundsInParent().intersects(node.getBoundsInParent()))
				{
					return this.gameObjekteOnRegion.get(i).getNode(); 
				}	
			}
		}
		return null;
	}
	
	/**
	 * Ueberprueft ob eine Node setzbar ist.
	 * @param node
	 * @return
	 */
	private Boolean ueberpruefeDiePositionAufSetzbarkeitMitBoolean(Node node)
	{
		for (int i = 0; this.gameObjekteOnRegion.size() > i ; i++)
		{
			if (this.gameObjekteOnRegion.get(i).getNode() != node)
			{
				if (this.gameObjekteOnRegion.get(i).getNode().getBoundsInParent().intersects(node.getBoundsInParent()))
				{
					return false; 
				}	
				if (node.getLayoutX() < 0 || node.getLayoutY() < 0 || node.getLayoutX()+node.getBoundsInLocal().getWidth() > zentrumRegion.getPrefWidth() || node.getBoundsInLocal().getHeight() + node.getLayoutY() > zentrumRegion.getPrefHeight())
				{
					return false; 
				}
			}
		}
		return true;
	}
	
	
	/**
	 * falls eine Node von einer andern Node blockiert wird, wird diese Methode benoetigt. sie ueberprueft ob eine Alternative
	 * vorhanden ist und setzt die Node dann auf diese Alternative.
	 * 
	 * @param node
	 * @param blockiernderNode
	 */
	Boolean wennNodeSetzbarDannSetzeAufDieMoeglichePosition(Node node, Node blockiernderNode)
	{
		//nach den bestmoeglichen setzbare freien Platz ausschau halten.
	    Bounds bounds = node.getBoundsInParent();
	    
	    double x =  blockiernderNode.getLayoutX();
	    double y =  blockiernderNode.getLayoutY();
	    
	    int xRichtung = 2; 
	    int yRichtung = 2; 
	    
	   for (int r = 0; yRichtung > r ; r++)
	   {
		   for (int i = 0; xRichtung > i ; i++)
		   {
			   int yZusatz = 1; 
			   int xZusatz = 1;
			   if (i == 0)
				   xZusatz = 0;
			   if (r == 0)
				   yZusatz = 0; 
			   
				node.setLayoutX(x+xZusatz+bounds.getWidth()*i);
				node.setLayoutY(y+yZusatz+bounds.getHeight()*r);
				if (ueberpruefeDiePositionAufSetzbarkeitMitBoolean(node)){
					return true; 
				}
				node.setLayoutX(x-xZusatz-bounds.getWidth()*i);
				node.setLayoutY(y-yZusatz-bounds.getHeight()*r);
				if (ueberpruefeDiePositionAufSetzbarkeitMitBoolean(node)){	
					return true; 
				}
				node.setLayoutX(x+xZusatz+bounds.getWidth()*i);
				node.setLayoutY(y-yZusatz-bounds.getHeight()*r);
				if (ueberpruefeDiePositionAufSetzbarkeitMitBoolean(node)){	
					return true; 
				}
				node.setLayoutX(x-xZusatz-bounds.getWidth()*i);
				node.setLayoutY(y+yZusatz+bounds.getHeight()*r);
				if (ueberpruefeDiePositionAufSetzbarkeitMitBoolean(node)){	
					return true; 
				}
				node.setLayoutX(x+xZusatz+bounds.getWidth()*i);
				node.setLayoutY(y-yZusatz-bounds.getHeight()*r);
		   }
	   } 
	   return false; 
	}
	
	
	void einstellungRegionGroeﬂe()	
	{
		//oeffne ein Wizard um die Groﬂe einzustellen
	}
	
	public static synchronized RegionMakerView getInstance() {
		if (instance== null)
			new RegionMakerView(); 
		return instance;
	}

	/**
	 * speichert die erstellte Region in die Datei, die vorher verwendet wurde
	 */
	void speicherRegion()
	{
		//nur moeglich wenn was geandert wurde. Sonst ausgegraut oder nicht anklickbar
		//speichert unter den verwendeten namen
	}
	
	/**
	 * speichert die erstellte Region
	 */
	void speicherUnterNameRegion()
	{
		//suche eine Datei unter der gespeichert werden soll
		//endung hinzufuegen
	}
	
	/**
	 * lead eine andere Region
	 */
	void ladeAndereRegion()
	{
		//Oeffne ein Ordner in dennen Regionen gespeichert sind
		//nach dem auswahlen einer Datei, nach speichern fragen, falls was geaendert wurde
	}
	
	/**
	 * erstellt eine Neue Region
	 */
	void erstelleNeueRegion()
	{
		//nach dem Speichern fragen, falls was geaendert wurde
		//oeffne eine Wizard um dort die Einsstellungen fuer die neue Region fest zu legen
	}


	public synchronized Scene getScene() {
		return scene;
	}			
	

	private class MauerSetzHandler implements EventHandler<MouseEvent>
	{		
		private GameObjekt objekt;
		/**
		 * zeigt an ob die Breite oder die hohe veraenderet werden soll. 
		 */
		private Boolean richtungRechts = true; 
		private Boolean richtungUnten = true; 
		private Boolean beendeDrucken = true; 
		private double startPositionCourserX;
		private double startPositionCourserY;
		private double startPositionCourserXDelta = 0; 
		private double startPositionCourserYDelta = 0; 
		private double startSizeX;
		private double startSizeY;
		private double startLayoutX;
		private double startLayoutY;
		
		private Box box;
		
		private MauerSetzHandler(GameObjekt objekt){
			
			this.box = (Box) objekt.getNodeObjekt().getNode();
			this.objekt = objekt; 
			startSizeX = box.getWidth();
			startSizeY = box.getHeight();
		}
		
		@Override
			public void handle(MouseEvent mouseEvent) {

			if (beendeDrucken){
				//legt den start position des Coursers fest. dies beeinfluﬂt ob die Mauer groeﬂer oder kleiner werdden soll
				startPositionCourserX = mouseEvent.getSceneX();
				startPositionCourserY = mouseEvent.getSceneY();
				startSizeX = box.getWidth();
				startSizeY = box.getHeight();
				startPositionCourserXDelta = 0; 
				startPositionCourserYDelta = 0; 
				startLayoutX = box.getLayoutX();
				startLayoutY = box.getLayoutY();
				//wenn die Maus ehr weiter rechts gedruckt wurde, dann ...
				if (mouseEvent.getX() > 0){
					richtungRechts = true;
				}
				else{
					richtungRechts = false;
				}
			
				//wenn der Courser weiter unten gedruckt wurde, dann ...
				if (mouseEvent.getY() > 0){
					richtungUnten = true; 
				}
				else{
					richtungUnten = false; 
				}
				beendeDrucken = false; 
			}
			//TODO
			if (mouseEvent.getButton() == MouseButton.PRIMARY)
			{	
				double sizeChange = mouseEvent.getSceneX()-startPositionCourserX;
				//wenn nach recht vergroeﬂert werden soll dann ..
				if (richtungRechts){
					if (startSizeX+sizeChange > 3){
						objekt.setX(startSizeX+sizeChange);	
						this.box.setLayoutX(startLayoutX+sizeChange/2.0);
					}
				}//wenn nach links dann...
				else{
					if (startSizeX-sizeChange > 3){
						objekt.setX(startSizeX-sizeChange);
						this.box.setLayoutX(startLayoutX+sizeChange/2.0);
					}
				}
				
			}
			else if(mouseEvent.getButton() == MouseButton.SECONDARY){
				
				double sizeChange = mouseEvent.getSceneY()-startPositionCourserY;
				//wenn nach recht vergroeﬂert werden soll dann ..
				if (richtungUnten){
					if (startSizeY+sizeChange > 3){
						objekt.setY(startSizeY+sizeChange);		
						this.box.setLayoutY(startLayoutY+sizeChange/2.0);
					}
				}//wenn nach links dann...
				else{
					if (startSizeY-sizeChange > 3){
						objekt.setY(startSizeY-sizeChange);
						this.box.setLayoutY(startLayoutY+sizeChange/2.0);
					}
				}
			
			}
			else if (mouseEvent.getButton() == MouseButton.MIDDLE){
				setPositionDesGameObjekt(box, mouseEvent);				
		}	
			positionAnpassen();	
	}	
		
		private void setStartZustand(){
			beendeDrucken = true; 
		}
		
		/**
		 * wird verwendet damit die Mauer nicht ueber die Grenzen hinaus wachsen kann.
		 */
		private void positionAnpassen(){	
			
			if (box.getBoundsInParent().getMinX() < 0){
				box.setLayoutX(box.getLayoutX()- box.getBoundsInParent().getMinX());
				objekt.setX(objekt.getX()+ box.getBoundsInParent().getMinX());		
			}
			if (box.getBoundsInParent().getMinX()+box.getWidth() > zentrumRegion.getWidth()){
				objekt.setX(zentrumRegion.getWidth()-box.getBoundsInParent().getMinX());	
				box.setLayoutX(zentrumRegion.getWidth()-box.getWidth()/2);		
			}
			if (box.getBoundsInParent().getMinY() < 0){
				box.setLayoutY(box.getLayoutY()- box.getBoundsInParent().getMinY());
				objekt.setY(objekt.getY()+ box.getBoundsInParent().getMinY());		
			}
			if (box.getBoundsInParent().getMinY()+box.getHeight() > zentrumRegion.getHeight()){
				objekt.setY(zentrumRegion.getHeight()-box.getBoundsInParent().getMinY());	
				box.setLayoutY(zentrumRegion.getHeight()-box.getHeight()/2);		
			}
		}

		public synchronized void setStartPositionCourserX(double startPositionCourserX) {
			this.startPositionCourserX = startPositionCourserX;
		}

		public synchronized void setStartPositionCourserY(double startPositionCourserY) {
			this.startPositionCourserY = startPositionCourserY;
		}
	}
}
