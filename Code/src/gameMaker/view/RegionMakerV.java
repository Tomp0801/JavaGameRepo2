package gameMaker.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import gameMaker.controll.StageCrt;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import personensicht.model.gameObjekte.GameObjekt;
import personensicht.model.gameObjekte.GameObjektType;
import personensicht.model.welt.map.Region;
import personensicht.view.welt.map.RegionV;

/**
 * Diese Scene soll das erstellen von Regionen mit einer GUI ermoeglichen
 * @author Dennis
 *
 */
public class RegionMakerV
{
	/**
	 * falls aenderungen an der Region vorgenommen wurde, muss das durtyBit auf true gesetzt werden, um 
	 * bei eventuellen neu laden oder Beenden ect. Nach dem Speichern gefragt werden kann. 
	 */
	private Boolean durtyBit = false; 
		
	/**
	 * enthaehlt den weg zum Verzeichnis in dem gespeichert wird.
	 */
	private String verzeichnisPath = System.getProperty("user.home");
	
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
	
	/**
	 * root Pane
	 */
	private BorderPane root = new BorderPane();
	/**
	 * Auf dem Pane werden die GameObjekte platziert. 
	 */
	private AnchorPane regionPane = new AnchorPane();
	private StackPane zentrumVomBoarderPane = new StackPane();
	private SubScene subScene;
	
	/**
	 * hier werden die GameObjekte drin gespeichert, die sich auf der Region befinden
	 */
	private ArrayList<AuswahlGameObjekt> gameObjekteOnRegion = new ArrayList<AuswahlGameObjekt>(); 
	
	/**
	 * variable fuer das Umsetzten von Nodes.
	 */
	private double layoutX = 0;
	/**
	 * variable fier das Umsetzen von Nodes
	 */
	private double layoutY = 0;  
	private static RegionMakerV instance ;
	
	private RegionMakerV() {		
		instance = this; 
		initBoarderPane();
		initRegionPane();
		initZentrumBoarderPane();
		initSubScene();
		initScene();
	}
	
	private void initBoarderPane(){
		MenuGameMaker obereMenuLeiste = new MenuGameMaker();
		ListeGameObjektLinks objektListe = new ListeGameObjektLinks();	
		root.setTop(obereMenuLeiste);
		root.setLeft(objektListe);
		root.setCenter(zentrumVomBoarderPane);
	}
	
	private void initRegionPane(){
		regionPane.setPrefSize(RegionV.SIZEMIN,RegionV.SIZEMIN);
		regionPane.setStyle("-fx-background-color: GREY");
		regionPane.setMinSize(RegionV.SIZEMIN, RegionV.SIZEMIN);
		regionPane.setMaxSize(RegionV.SIZEMIN, RegionV.SIZEMIN);
		regionPane.setPickOnBounds(false);	
	}
	
	private void initZentrumBoarderPane(){
		zentrumVomBoarderPane.setPrefSize(RegionV.SIZEMIN + 200,RegionV.SIZEMIN+200);
		zentrumVomBoarderPane.setStyle("-fx-background-color: BLACK");
		zentrumVomBoarderPane.setAlignment(Pos.CENTER);
	}
	
	private void initSubScene(){
		subScene = new SubScene(regionPane,zentrumVomBoarderPane.getPrefWidth(), zentrumVomBoarderPane.getPrefHeight(), true, SceneAntialiasing.BALANCED );
		PerspectiveCamera kamera = new PerspectiveCamera();
		kamera.setTranslateZ(-300); 
		kamera.setNearClip(0.01);
		kamera.setFieldOfView(45);
		kamera.setRotationAxis(Rotate.X_AXIS);
		kamera.setRotate(30);
		subScene.setCamera(kamera);
		zentrumVomBoarderPane.getChildren().add(subScene); 
	}
	
	private void initScene(){
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
	
	private void setGameMakerInStartzustand(){
		layoutX = 0; 
		layoutY = 0; 
		this.gameObjekteOnRegion = new ArrayList<AuswahlGameObjekt>();
		regionPane = new AnchorPane(); 
		ausgewaehterNode = null; 
		durtyBit = false;
		regionPane.getChildren().removeAll();
		ListeGameObjektLinks objektListe = new ListeGameObjektLinks();	
		root.setLeft(objektListe);
	}
	
	/**
	 * loescht eine Node von der Region
	 * @param node
	 */
	void removeNodeFromRegion(Node node){
		regionPane.getChildren().remove(node);
		gameObjekteOnRegion.remove(node);
	}
	
//	/**
//	 * erstellt eine RegionMakerView mit vorgegebener Groeﬂe des Zentrums
//	 * @param sizeX
//	 * @param sizeY
//	 */
//	public RegionMakerV(int sizeX, int sizeY){
//		this();
//		if (sizeX >= RegionV.SIZEMIN && sizeY >= RegionV.SIZEMAX)
//			if (sizeX < RegionV.SIZEMAX && sizeY < RegionV.SIZEMAX){
//				regionPane.setPrefSize(sizeX, sizeY);
//				subScene.setWidth(sizeY);
//				subScene.setHeight(sizeX);
//			}
//			else
//				System.err.println("Die eingestellte Grˆﬂe ist zu Groﬂ");
//		else
//			System.err.println("Die groeﬂe f¸r die Region ist zu klein");
//	}
	
	
	/**
	 * platzierzt eine Mauer
	 */
	void platzierungMauer(AuswahlGameObjekt objekt){
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
	}
	
	/**
	 * setzt eine Node auf die Region und gibt diese benoetigte Eigenschaften
	 */
	private void setNodeOnRegion(Node node){
		//makiert die Region als bearbeitet
		durtyBit = true; 
		// setzt die Node auf die Region
		regionPane.getChildren().add(node);
	}

	
	/**
	 * seztz ein GameObjekt mithilfe der Kalsse AuswahlEinesGameObejekt auf das zentrum (Region).
	 * Dabei wird das durtyBit auf true gesetzt, um zu makieren, das dass Projekt bearbeitet wurde. 
	 * Rotiert die Node, um diese in 3D zu sehen
	 * @param node
	 */
	void setzeGameObjektAufDieRegion(AuswahlGameObjekt objektAuswahl){
		GameObjekt objekt = objektAuswahl.getGameObjekt();
		Node node = objektAuswahl.getNode();
		setNodeOnRegion(node);	
		//erstellt EventHandler. Um das Umsetzen einer Node zu ermoeglichen
		node.setOnMouseDragged(new EventHandler<MouseEvent>(){
			  @Override public void handle(MouseEvent mouseEvent) {
				  //laesst die Node dem Courser folgen
				  setPositionDesGameObjekt(objekt , mouseEvent);
			  }
		});	
		//Dieses Event, beschreibt was passiert wenn die ein Node gesetzt wird. Dazu wird ueberprueft ob diese Position
		//schon von einer andern Node besetzt ist.
		node.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) 	{
				//Ueberprueft ob die Position setzbar ist. Wenn dies nicht der Falls ist, wird de Node, die blockiert 
				//auf nodeBlockierung referenziert
				GameObjekt objektBlockierung = ueberpruefeDiePositionAufSetzbarkeit(objekt);
				//blockiert eine Node den Ziel Ort, dann ...
				if (objektBlockierung != null)
				{
					//schaue on die Node ueberhaupt setztbat ist und wenn nicht dann ...
					if (wennNodeSetzbarDannSetzeAufDieMoeglichePosition(objekt, objektBlockierung) == false)
					{
						//versetze die Node auf ihre anfangsPosition zurueck
						objekt.setLayoutX(layoutX);
						objekt.setLayoutY(layoutY);
					}
				}
					randNodeSetztTest(objektAuswahl.getGameObjekt());
				}	
		});
		node.setOnMousePressed(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				changPositionVonNode(objekt.getLayoutX().get(), objekt.getLayoutY().get());	
			}
		});
		
		node.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) 	{
				root.setRight(objektAuswahl.getEinstellungGameObjket());	
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
	 * @param objekt
	 */
	void randNodeSetztTest(GameObjekt objekt)
	{
		 if (objekt.getLayoutX().get()+objekt.getWidth().get()/2 > subScene.getWidth())
		 {
			 objekt.setLayoutX(subScene.getWidth()-objekt.getWidth().get()/2);
		 }
		 if (objekt.getLayoutY().get()+objekt.getHeight().get()/2 > subScene.getHeight())
		 {
			 objekt.setLayoutY(subScene.getHeight()-objekt.getHeight().get()/2);
		 }
		 if (objekt.getLayoutX().get()-objekt.getWidth().get()/2 < 0)
		 {
			 objekt.setLayoutX(0+objekt.getWidth().get()/2);
		 }
		 if (objekt.getLayoutY().get()-objekt.getHeight().get()/2 < 0)
		 {
			 objekt.setLayoutY(objekt.getHeight().get()/2);
		 }
	}

	//setzt die Position einer Node auf die Position des Coursers
	public	void setPositionDesGameObjekt(GameObjekt objekt, MouseEvent mouseEvent){ 
		 objekt.setLayoutX(mouseEvent.getSceneX()-root.getCenter().getLayoutX()-subScene.getLayoutX());
		 objekt.setLayoutY(mouseEvent.getSceneY()-root.getCenter().getLayoutY()-subScene.getLayoutY());
		 ausgewaehterNode = objekt.getNodeObjekt().getNode();
		 durtyBit = true;
	}

	/**
	 * Ueberprueft ob eine Node setzbar ist.
	 * @param node
	 * @return
	 */
	public GameObjekt ueberpruefeDiePositionAufSetzbarkeit(GameObjekt objekt)
	{
		Node node = objekt.getNodeObjekt().getNode();
		for (int i = 0; this.gameObjekteOnRegion.size() > i ; i++)
		{
			if (this.gameObjekteOnRegion.get(i).getNode() != node)
			{
				if (this.gameObjekteOnRegion.get(i).getNode().getBoundsInParent().intersects(node.getBoundsInParent()))
				{
					return this.gameObjekteOnRegion.get(i).getGameObjekt(); 
				}	
			}
		}
		return null;
	}
	
	/**
	 * Ueberprueft ob eine Node setzbar ist.
	 * @param objekt
	 * @return
	 */
	private Boolean ueberpruefeDiePositionAufSetzbarkeitMitBoolean(GameObjekt objekt)
	{
		for (int i = 0; this.gameObjekteOnRegion.size() > i ; i++)
		{
			if (this.gameObjekteOnRegion.get(i).getNode() != objekt.getNodeObjekt().getNode())
			{
				if (this.gameObjekteOnRegion.get(i).getNode().getBoundsInParent().intersects(objekt.getNodeObjekt().getNode().getBoundsInParent()))
				{
					return false; 
				}	
				if (objekt.getLayoutX().get() < 0 || objekt.getLayoutY().get() < 0 || objekt.getLayoutX().get()+objekt.getWidth().get() > regionPane.getPrefWidth() || objekt.getHeight().get() + objekt.getLayoutY().get() > regionPane.getPrefHeight())
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
	 * @param objekt
	 * @param blockiernderNode
	 */
	Boolean wennNodeSetzbarDannSetzeAufDieMoeglichePosition(GameObjekt objekt, GameObjekt blockierndesObjekt)
	{
		//nach den bestmoeglichen setzbare freien Platz ausschau halten.
	    Bounds bounds = objekt.getNodeObjekt().getNode().getBoundsInParent();
	    
	    double x =  blockierndesObjekt.getLayoutX().get();
	    double y =  blockierndesObjekt.getLayoutY().get();
	    
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
			   
				objekt.setLayoutX(x+xZusatz+bounds.getWidth()*i);
				objekt.setLayoutY(y+yZusatz+bounds.getHeight()*r);
				if (ueberpruefeDiePositionAufSetzbarkeitMitBoolean(objekt)){
					return true; 
				}
				objekt.setLayoutX(x-xZusatz-objekt.getWidth().get()*i);
				objekt.setLayoutY(y-yZusatz-objekt.getHeight().get()*r);
				if (ueberpruefeDiePositionAufSetzbarkeitMitBoolean(objekt)){	
					return true; 
				}
				objekt.setLayoutX(x+xZusatz+objekt.getWidth().get()*i);
				objekt.setLayoutY(y-yZusatz-objekt.getHeight().get()*r);
				if (ueberpruefeDiePositionAufSetzbarkeitMitBoolean(objekt)){	
					return true; 
				}
				objekt.setLayoutX(x-xZusatz-objekt.getWidth().get()*i);
				objekt.setLayoutY(y+yZusatz+objekt.getHeight().get()*r);
				if (ueberpruefeDiePositionAufSetzbarkeitMitBoolean(objekt)){	
					return true; 
				}
				objekt.setLayoutX(x+xZusatz+objekt.getWidth().get()*i);
				objekt.setLayoutY(y-yZusatz-objekt.getHeight().get()*r);
		   }
	   } 
	   return false; 
	}
	
	
	void einstellungRegionGroeﬂe()	
	{
		//oeffne ein Wizard um die Groﬂe einzustellen
	}
	
	public static synchronized RegionMakerV getInstance() {
		if (instance== null)
			new RegionMakerV(); 
		return instance;
	}

	/**
	 * speichert die erstellte Region in die Datei, die vorher verwendet wurde
	 */
	void speicherRegion(){
		//nur moeglich wenn was geandert wurde. Sonst ausgegraut oder nicht anklickbar
		//speichert unter den verwendeten namen
		if (durtyBit == true){
			speichern(new File(path));
		}
	}
	
	/**
	 * oeffnet einen FileChooser, mit der die Region gespeichert werden kann.
	 */
	void speicherUnterNameRegion(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Speichern");
	    FileChooser.ExtensionFilter extFilter = 
                new FileChooser.ExtensionFilter("TEXT files (*.region)" , "(*.region)");
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.setInitialDirectory(
		            new File(verzeichnisPath) 
		        );
		File file = fileChooser.showSaveDialog(StageCrt.getInstance().getStage());
		speichern(file);
	}
	
	private void speichern(File file){
		if (file != null){	
			FileOutputStream fos = null;
			//Modelklasse Region erstellen und die Region speichern
			ArrayList<GameObjekt> objektListe = new ArrayList<GameObjekt>();
			for (int i = 0; this.gameObjekteOnRegion.size() > i ; i++){
				objektListe.add(gameObjekteOnRegion.get(i).getGameObjekt());
			}
			Region region = new Region("defaul",objektListe); //TODO der Name muss noch gewahlt werden
			region.serializ();
			
			
			verzeichnisPath = file.getParentFile().toPath().toString();
			path = file.getAbsoluteFile().getPath();
			try 
			{
				fos = new FileOutputStream (file.getAbsoluteFile()); 
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
		    ObjectOutputStream oos = null;
			
		    try 
			{
				oos = new ObjectOutputStream (fos);
				oos.writeObject(region);
			} 
		    catch (IOException e) 
		    {
				e.printStackTrace();
			}	
		    System.out.println("gespeichert");
		}
	}
	
	/**
	 * lead eine andere Region
	 */
   void ladeAndereRegion(){
		//Oeffne ein Ordner in dennen Regionen gespeichert sind
		//nach dem auswahlen einer Datei, nach speichern fragen, falls was geaendert wurde
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream ("demo.edit"); //TODO auswaehlen welche Region geladen werden soll.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream (fis);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	   try {
		Region region = (Region) ois.readObject (); //TODO
		region.deserializ();
		setGameMakerInStartzustand();
		initRegionPane();
		for (int i = 0 ; region.getChildren().size() > i; i++)
		{
			AuswahlGameObjekt auswahlGameObjekt = new AuswahlGameObjekt(region.getChildren().get(i));
			if (GameObjektType.Mauer == region.getChildren().get(i).getTyp()){
				this.platzierungMauer(auswahlGameObjekt);
			}
			else{
				this.setzeGameObjektAufDieRegion(auswahlGameObjekt);
			}
		}
		
		
		initSubScene();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	   System.out.println("geladen");
	}
	
	/**
	 * erstellt eine Neue Region
	 */
	void erstelleNeueRegion(){
		//nach dem Speichern fragen, falls was geaendert wurde
		//oeffne eine Wizard um dort die Einsstellungen fuer die neue Region fest zu legen
	}


	public synchronized Scene getScene() {
		return scene;
	}			
	

	
	//--------------------------------------EventHandler---------------------------------------------------------------------------------------//
	
	
	
	
	
	
	//-----------------------------------------------------------------------------------------------------------------------------------------
	private class MauerSetzHandler implements EventHandler<MouseEvent>{		
		private GameObjekt objekt;
		/**
		 * zeigt an ob die Breite oder die hohe veraenderet werden soll. 
		 */
		private Boolean richtungRechts = true; 
		private Boolean richtungUnten = true; 
		private Boolean beendeDrucken = true; 
		private double startPositionCourserX;
		private double startPositionCourserY; 
		private double startSizeX;
		private double startSizeY;
		private double startLayoutX;
		private double startLayoutY;
		private Box box;
		
		private MauerSetzHandler(GameObjekt objekt){
			
			this.box = (Box) objekt.getNodeObjekt().getNode();
			this.objekt = objekt; 
			startSizeX = objekt.getWidth().get();
			startSizeY = objekt.getHeight().get();
		}
		
		@Override
			public void handle(MouseEvent mouseEvent) {

			if (beendeDrucken){
				//legt den start position des Coursers fest. dies beeinfluﬂt ob die Mauer groeﬂer oder kleiner werdden soll
				startPositionCourserX = mouseEvent.getSceneX();
				startPositionCourserY = mouseEvent.getSceneY();
				startSizeX = objekt.getWidth().get();
				startSizeY = objekt.getHeight().get();
				startLayoutX = objekt.getLayoutX().get();
				startLayoutY = objekt.getLayoutY().get();
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
			if (mouseEvent.getButton() == MouseButton.PRIMARY)
			{	
				double sizeChange = mouseEvent.getSceneX()-startPositionCourserX;
				//wenn nach recht vergroeﬂert werden soll dann ..
				if (richtungRechts){
					if (startSizeX+sizeChange > 3){
						objekt.setWidth(startSizeX+sizeChange);	
						objekt.setLayoutX(startLayoutX+sizeChange/2.0);
					}
				}//wenn nach links dann...
				else{
					if (startSizeX-sizeChange > 3){
						objekt.setWidth(startSizeX-sizeChange);
						objekt.setLayoutX(startLayoutX+sizeChange/2.0);
					}
				}
				
			}
			else if(mouseEvent.getButton() == MouseButton.SECONDARY){
				
				double sizeChange = mouseEvent.getSceneY()-startPositionCourserY;
				//wenn nach recht vergroeﬂert werden soll dann ..
				if (richtungUnten){
					if (startSizeY+sizeChange > 3){
						objekt.setHeight(startSizeY+sizeChange);		
						objekt.setLayoutY(startLayoutY+sizeChange/2.0);
					}
				}//wenn nach links dann...
				else{
					if (startSizeY-sizeChange > 3){
						objekt.setHeight(startSizeY-sizeChange);
						objekt.setLayoutY(startLayoutY+sizeChange/2.0);
					}
				}
			
			}
			else if (mouseEvent.getButton() == MouseButton.MIDDLE){
				setPositionDesGameObjekt(objekt, mouseEvent);				
		}	
			positionAnpassen();	
			objekt.setWidth(box.getWidth());
			objekt.setHeight(box.getHeight());
			objekt.setDepth(box.getDepth());
	}	
		
		private void setStartZustand(){
			beendeDrucken = true; 
		}
		
		/**
		 * wird verwendet damit die Mauer nicht ueber die Grenzen hinaus wachsen kann.
		 */
		private void positionAnpassen(){	
			
			if (box.getBoundsInParent().getMinX() < 0){
				objekt.setLayoutX(objekt.getLayoutX().get()- box.getBoundsInParent().getMinX());
				objekt.setWidth(objekt.getWidth().get()+ box.getBoundsInParent().getMinX());		
			}
			if (box.getBoundsInParent().getMinX()+objekt.getWidth().get() > regionPane.getWidth()){
				objekt.setWidth(regionPane.getWidth()-box.getBoundsInParent().getMinX());	
				objekt.setLayoutX(regionPane.getWidth()-objekt.getWidth().get()/2);		
			}
			if (box.getBoundsInParent().getMinY() < 0){
				objekt.setLayoutY(box.getLayoutY()- box.getBoundsInParent().getMinY());
				objekt.setHeight(objekt.getHeight().get()+ box.getBoundsInParent().getMinY());		
			}
			if (box.getBoundsInParent().getMinY()+objekt.getHeight().get() > regionPane.getHeight()){
				objekt.setHeight(regionPane.getHeight()-box.getBoundsInParent().getMinY());	
				objekt.setLayoutY(regionPane.getHeight()-objekt.getHeight().get()/2);		
			}
		}
	}

	public synchronized ArrayList<AuswahlGameObjekt> getGameObjekteOnRegion() {
		return gameObjekteOnRegion;
	}
}
