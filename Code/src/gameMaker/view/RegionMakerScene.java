package gameMaker.view;

import gameMaker.controll.RegionMakerCrt;
import gameMaker.model.RegionMaker;
import gameMaker.view.einstellungGameObjekte.EinstellungGameObjekt;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import personensicht.model.gameObjekte.GameObjekt;
import personensicht.view.welt.map.RegionV;

/**
 * Diese Scene soll das erstellen von Regionen mit einer GUI ermoeglichen
 * @author Dennis
 *
 */
public class RegionMakerScene
{
	RegionMaker regionMaker; 
	
	/**
	 * die Scene die verwendet wird
	 */
	private Scene scene; 
	
	/**
	 * hier befindet sich die Node, die ausgewaeahlt wurde
	 */
	private GameObjekt ausgewaehterNode; 
	
	/**
	 * root Pane
	 */
	private BorderPane root = new BorderPane();
	/**
	 * Auf dem Pane werden die GameObjekte platziert. 
	 */
	private AnchorPaneRegion regionPane;
	public synchronized BorderPane getRoot() {
		return root;
	}

	private StackPane zentrumVomBoarderPane = new StackPane();
	private SubScene subScene;
//	private static RegionMakerScene instance ;
	
	public RegionMakerScene() {
//		instance = this; 
		initScene();
		regionPane = new AnchorPaneRegion();
		regionMaker = new RegionMaker();
		initBoarderPane();
//		initRegionPane();
		initZentrumBoarderPane();
		initSubScene();	
	}
	
	public RegionMakerScene(RegionMaker regionMaker){
		this();
		RegionMakerCrt.getInstance().setGameObjekt(regionMaker.getRegion().getChildren());
	}
	
	private void initBoarderPane(){
		MenuGameMaker obereMenuLeiste = new MenuGameMaker();
		ListeGameObjektLinks objektListe = new ListeGameObjektLinks();	
		root.setTop(obereMenuLeiste);
		root.setLeft(objektListe);
		root.setCenter(zentrumVomBoarderPane);
	}
	
//	private void initRegionPane(){
//		regionPane.setPrefSize(RegionV.SIZEMIN*2,RegionV.SIZEMIN*2);
//		regionPane.setStyle("-fx-background-color: GREY");
//		regionPane.setMinSize(RegionV.SIZEMIN, RegionV.SIZEMIN);
//		regionPane.setMaxSize(RegionV.SIZEMIN, RegionV.SIZEMIN);
//		regionPane.setPickOnBounds(false);	
//	}
	
	private void initZentrumBoarderPane(){
		zentrumVomBoarderPane.setPrefSize(RegionV.SIZEMIN + 200,RegionV.SIZEMIN+200);
		zentrumVomBoarderPane.setStyle("-fx-background-color: BLACK");
		zentrumVomBoarderPane.setAlignment(Pos.CENTER);
	}
	
	private void initSubScene(){
		
		AnchorPane subSceneRoot = new AnchorPane();
		subSceneRoot.setStyle("-fx-background-color: TRANSPARENT");
		subSceneRoot.setTranslateZ(1);
		subSceneRoot.getChildren().add(regionPane);
		subScene = new SubScene(subSceneRoot,zentrumVomBoarderPane.getPrefWidth(), zentrumVomBoarderPane.getPrefHeight(), true, SceneAntialiasing.BALANCED );
		subScene.heightProperty().bind(zentrumVomBoarderPane.heightProperty());
		subScene.widthProperty().bind(zentrumVomBoarderPane.widthProperty());
		Kamera kamera = new Kamera(this.scene);
		if (regionPane.getHeight() > regionPane.getWidth()){
			kamera.setTranslateZ(-regionPane.getHeight()); 	
		}
		else{
			kamera.setTranslateZ(-regionPane.getWidth()); 
		}
		kamera.setNearClip(0.01);
		kamera.setFieldOfView(45); 
		kamera.setRotationAxis(Rotate.X_AXIS);
		kamera.setRotate(0);
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
						RegionMakerCrt.getInstance().removeGameOjekt(ausgewaehterNode);
						ausgewaehterNode = null;
						root.setRight(null);
					}
				}
			}
		});
	}
	
//	private void setGameMakerInStartzustand(){ 
//		for (int i = 0;regionMaker.getGameObjekteOnRegion().size() > i ; i++){
//			RegionMakerCrt.getInstance().removeGameOjekt(regionMaker.getGameObjekteOnRegion().get(i).getGameObjekt());
//		}
//				
//		this.regionMaker.setGameObjekteOnRegion(new ArrayList<AuswahlGameObjekt>());
//		regionPane = new AnchorPaneRegion(); 
//		ausgewaehterNode = null; 
//		regionMaker.setDurtyBit(false);
//		regionPane.getChildren().removeAll();
//		ListeGameObjektLinks objektListe = new ListeGameObjektLinks();	
//		root.setLeft(objektListe);
////		initRegionPane();
//		initSubScene();
//	}
	
//	/**
//	 * loescht eine Node von der Region
//	 * @param node
//	 */
//	void removeNodeFromRegion(GameObjekt objekt){
//		regionPane.getChildren().remove(objekt.getNodeObjekt().getNode());
//		regionMaker.getGameObjekteOnRegion().remove(objekt);
//	}	
	
//	/**
//	 * platzierzt eine Mauer
//	 */
//	void platzierungMauer(GameObjektZuordnung objekt){
//		regionMaker.getGameObjekteOnRegion().add(objekt);
//		Node node = objekt.getNode();
//		setNodeOnRegion(node);
//		MauerSetzHandler mouseHandler = new MauerSetzHandler(objekt.getEinstellungGameObjket().getGameObjekt());
//		node.setOnMouseDragged(mouseHandler);
//		node.setOnMouseReleased(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//				mouseHandler.setStartZustand();
//			}
//		});	
//	}
	
//	/**
//	 * setzt eine Node auf die Region und gibt diese benoetigte Eigenschaften
//	 */
//	private void setNodeOnRegion(Node node){
//		//makiert die Region als bearbeitet
//		regionMaker.setDurtyBit(true); 
//		// setzt die Node auf die Region
//		regionPane.getChildren().add(node);
//	}

	
//	/**
//	 * seztz ein GameObjekt mithilfe der Kalsse AuswahlEinesGameObejekt auf das zentrum (Region).
//	 * Dabei wird das durtyBit auf true gesetzt, um zu makieren, das dass Projekt bearbeitet wurde. 
//	 * Rotiert die Node, um diese in 3D zu sehen
//	 * @param node
//	 */
//	void setzeGameObjektAufDieRegion(GameObjektZuordnung objektAuswahl){
//		GameObjekt objekt = objektAuswahl.getGameObjekt();
//		regionMaker.getGameObjekteOnRegion().add(objektAuswahl);
//		Node node = objektAuswahl.getNode();
//		setNodeOnRegion(node);	
//		//erstellt EventHandler. Um das Umsetzen einer Node zu ermoeglichen
//		node.setOnMouseDragged(new EventHandler<MouseEvent>(){
//			  @Override public void handle(MouseEvent mouseEvent) {
//				  //laesst die Node dem Courser folgen
//				  setPositionDesGameObjekt(objekt , mouseEvent);
//			  }
//		});	
//		//Dieses Event, beschreibt was passiert wenn die ein Node gesetzt wird. Dazu wird ueberprueft ob diese Position
//		//schon von einer andern Node besetzt ist. 
//		EventHandelerSetzeGameObjektNode eventHandlerReleased = new EventHandelerSetzeGameObjektNode(objektAuswahl.getGameObjekt());
//		node.setOnMouseReleased(eventHandlerReleased);
//		
//		node.setOnMousePressed(new EventHandler<Event>() {
//			@Override
//			public void handle(Event event) {
//				eventHandlerReleased.setStartPositionX(objekt.getLayoutX().get());
//				eventHandlerReleased.setStartPositionY(objekt.getLayoutY().get());	
//			}
//		});
//		
//		node.setOnMouseClicked(new EventHandler<Event>() {
//			@Override
//			public void handle(Event event) 	{
//				root.setRight(objektAuswahl.getEinstellungGameObjket());	
//			}
//		});
//	}
	
//	/**
//	 * 
//	 * @param x
//	 * @param y
//	 */
//	private void changPositionVonNode(double x , double y)
//	{
//		layoutX = x; 
//		layoutY = y; 
//	}
//	
	/**
	 * ueberprueft ob eine gesetzte Node innerhalb der Region liegt. Und setzt diese gegebenfalls auf den Rand
	 * @param objekt
	 */
	public void randNodeSetztTest(GameObjekt objekt)
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
		
		 objekt.setLayoutX( (mouseEvent.getSceneX() - root.getLeft().getLayoutBounds().getWidth()  + subScene.getCamera().getLayoutX()));  //Das setzten muss noch angepasst werden
		 objekt.setLayoutY( (mouseEvent.getSceneY() - root.getTop().getLayoutBounds().getHeight()  + subScene.getCamera().getLayoutY()));  //Bei Rotierter Kamera stimmt dies nicht mehr
		 
		 ausgewaehterNode = objekt;
		 regionMaker.setDurtyBit(true);
	}

	/**
	 * Ueberprueft ob eine Node setzbar ist.
	 * @param node
	 * @return
	 */
	public GameObjekt ueberpruefeDiePositionAufSetzbarkeit(GameObjekt objekt)
	{
		Node node = objekt.getNodeObjekt().getNode();
		for (int i = 0; this.regionMaker.getRegion().getChildren().size() > i ; i++)
		{
			if (this.regionMaker.getRegion().getChildren().get(i).getNodeObjekt().getNode() != node)
			{
				if (this.regionMaker.getRegion().getChildren().get(i).getNodeObjekt().getNode().getBoundsInParent().intersects(node.getBoundsInParent()))
				{
					return this.regionMaker.getRegion().getChildren().get(i); 
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
		for (int i = 0; this.regionMaker.getRegion().getChildren().size() > i ; i++)
		{
			if (this.regionMaker.getRegion().getChildren().get(i).getNodeObjekt().getNode() != objekt.getNodeObjekt().getNode())
			{
				if (this.regionMaker.getRegion().getChildren().get(i).getNodeObjekt().getNode().getBoundsInParent().intersects(objekt.getNodeObjekt().getNode().getBoundsInParent()))
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
	public Boolean wennNodeSetzbarDannSetzeAufDieMoeglichePosition(GameObjekt objekt, GameObjekt blockierndesObjekt)
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
	
	
//	void einstellungRegionGroeße()	
//	{
//		MapSizeDialog dialog = new MapSizeDialog(regionPane.getPrefWidth(), regionPane.getPrefHeight()); 
//        dialog.show();
//	}
	
//	public static synchronized RegionMakerScene getInstance() {
//		if (instance== null)
//			new RegionMakerScene(); 
//		return instance;
//	}

//	/**
//	 * speichert die erstellte Region in die Datei, die vorher verwendet wurde
//	 */
//	void fastSave(){
//		//nur moeglich wenn was geandert wurde. Sonst ausgegraut oder nicht anklickbar
//		//speichert unter den verwendeten namen
//		if (regionMaker.getDurtyBit() == true){
//			if (regionMaker.getFileRegion() != null){
//				speichern(regionMaker.getFileRegion());
//			}
//			else{
//				System.out.println("keine Speicherung Möglich. Path fehlt");
//			}
//		}
//	}
	
//	/**
//	 * oeffnet einen FileChooser, mit der die Region gespeichert werden kann.
//	 */
//	void fileChooserSave(){  
//		FileChooser fileChooser = new FileChooser();
//		fileChooser.setTitle("Speichern");
//	    FileChooser.ExtensionFilter extFilter = 
//                new FileChooser.ExtensionFilter("REGION" , "*.region");
//		fileChooser.getExtensionFilters().add(extFilter);
//		fileChooser.setInitialDirectory(
//		            new File(regionMaker.getVerzeichnisPath()) 
//		        );
//		File file = fileChooser.showSaveDialog(StageCrt.getInstance().getStage());
//		speichern(file);
//	}
//	
//	private void speichern(File file){
//		if (file != null){	
//			FileOutputStream fos = null;
//			//Modelklasse Region erstellen und die Region speichern
//			ArrayList<GameObjekt> objektListe = new ArrayList<GameObjekt>();
//			for (int i = 0; this.regionMaker.getGameObjekteOnRegion().size() > i ; i++){
//				objektListe.add(regionMaker.getGameObjekteOnRegion().get(i).getGameObjekt());
//			}
////			regionMaker = new RegionMaker(); 
////			regionMaker.getRegion().setHeight(this.regionPane.getHeight());
////			regionMaker.getRegion().setWidth(this.regionPane.getWidth());
//			regionMaker.serializ();
//			regionMaker.setVerzeichnisPath(file.getParentFile().toPath().toString());
//			regionMaker.setFileRegion(file);
//			try 
//			{
//				fos = new FileOutputStream (file.getAbsoluteFile()); 
//			} 
//			catch (FileNotFoundException e) 
//			{
//				e.printStackTrace();
//			}
//		    ObjectOutputStream oos = null;
//			
//		    try 
//			{
//				oos = new ObjectOutputStream (fos);
//				oos.writeObject(regionMaker);
//			} 
//		    catch (IOException e) 
//		    {
//				e.printStackTrace();
//			}	
//		    System.out.println("gespeichert");
//		}
//	}
	
//	/**
//	 * lead eine andere Region
//	 */
//   void ladeAndereRegion(){
//		//Oeffne ein Ordner in dennen Regionen gespeichert sind
//		//nach dem auswahlen einer Datei, nach speichern fragen, falls was geaendert wurde
//	   	FileChooser fileChooser = new FileChooser();
//		fileChooser.setTitle("Laden");
//	    FileChooser.ExtensionFilter extFilter = 
//               new FileChooser.ExtensionFilter("REGION" , "*.region");
//		fileChooser.getExtensionFilters().add(extFilter);
//		fileChooser.setInitialDirectory(
//		            new File(regionMaker.getVerzeichnisPath()) 
//		        );
//		File file = fileChooser.showOpenDialog(StageCrt.getInstance().getStage());
//	   
//		if (file != null){
//			FileInputStream fis = null;
//			try {
//				fis = new FileInputStream (file);
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//		    ObjectInputStream ois = null;
//			try {
//				ois = new ObjectInputStream (fis);
//			} catch (IOException e) {
//				e.printStackTrace();
//			} 
//		    try {
//			RegionMaker region = (RegionMaker) ois.readObject (); 
//			region.deserializ();
////			setGameMakerInStartzustand();
//			RegionMakerScene regionMakerV = new RegionMakerScene(region);
//			StageCrt.getInstance().setScene(regionMakerV.getScene());
//			
//		    } catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		    } catch (IOException e) {
//			e.printStackTrace();
//		    }
//		    System.out.println("geladen");
//		}
//	}


	public synchronized Scene getScene() {
		return scene;
	}			
	

	
	//--------------------------------------EventHandler---------------------------------------------------------------------------------------//
	
	
	
	
	
	
	public synchronized SubScene getSubScene() {
		return subScene;
	}

	public synchronized void setSubScene(SubScene subScene) {
		this.subScene = subScene;
	}

	public synchronized AnchorPane getRegionPane() {
		return regionPane;
	}

	public synchronized void setRegionPane(AnchorPaneRegion regionPane) {
		this.regionPane = regionPane;
	}



	//-----------------------------------------------------------------------------------------------------------------------------------------
//	private class MauerSetzHandler implements EventHandler<MouseEvent>{		
//		private GameObjekt objekt;
//		/**
//		 * zeigt an ob die Breite oder die hohe veraenderet werden soll. 
//		 */
//		private Boolean richtungRechts = true; 
//		private Boolean richtungUnten = true; 
//		private Boolean beendeDrucken = true; 
//		private double startPositionCourserX;
//		private double startPositionCourserY; 
//		private double startSizeX;
//		private double startSizeY;
//		private double startLayoutX;
//		private double startLayoutY;
//		private Box box;
//		
//		private MauerSetzHandler(GameObjekt objekt){
//			
//			this.box = (Box) objekt.getNodeObjekt().getNode();
//			this.objekt = objekt; 
//			startSizeX = objekt.getWidth().get();
//			startSizeY = objekt.getHeight().get();
//		}
//		
//		@Override
//			public void handle(MouseEvent mouseEvent) {
//
//			if (beendeDrucken){
//				//legt den start position des Coursers fest. dies beeinflußt ob die Mauer groeßer oder kleiner werdden soll
//				startPositionCourserX = mouseEvent.getSceneX();
//				startPositionCourserY = mouseEvent.getSceneY();
//				startSizeX = objekt.getWidth().get();
//				startSizeY = objekt.getHeight().get();
//				startLayoutX = objekt.getLayoutX().get();
//				startLayoutY = objekt.getLayoutY().get();
//				//wenn die Maus ehr weiter rechts gedruckt wurde, dann ...
//				if (mouseEvent.getX() > 0){
//					richtungRechts = true;
//				}
//				else{
//					richtungRechts = false;
//				}
//			
//				//wenn der Courser weiter unten gedruckt wurde, dann ...
//				if (mouseEvent.getY() > 0){
//					richtungUnten = true; 
//				}
//				else{
//					richtungUnten = false; 
//				}
//				beendeDrucken = false; 
//			}
//			if (mouseEvent.getButton() == MouseButton.PRIMARY)
//			{	
//				double sizeChange = mouseEvent.getSceneX()-startPositionCourserX;
//				//wenn nach recht vergroeßert werden soll dann ..
//				if (richtungRechts){
//					if (startSizeX+sizeChange > 3){
//						objekt.setWidth(startSizeX+sizeChange);	
//						objekt.setLayoutX(startLayoutX+sizeChange/2.0);
//					}
//				}//wenn nach links dann...
//				else{
//					if (startSizeX-sizeChange > 3){
//						objekt.setWidth(startSizeX-sizeChange);
//						objekt.setLayoutX(startLayoutX+sizeChange/2.0);
//					}
//				}
//				
//			}
//			else if(mouseEvent.getButton() == MouseButton.SECONDARY){
//				
//				double sizeChange = mouseEvent.getSceneY()-startPositionCourserY;
//				//wenn nach recht vergroeßert werden soll dann ..
//				if (richtungUnten){
//					if (startSizeY+sizeChange > 3){
//						objekt.setHeight(startSizeY+sizeChange);		
//						objekt.setLayoutY(startLayoutY+sizeChange/2.0);
//					}
//				}//wenn nach links dann...
//				else{
//					if (startSizeY-sizeChange > 3){
//						objekt.setHeight(startSizeY-sizeChange);
//						objekt.setLayoutY(startLayoutY+sizeChange/2.0);
//					}
//				}
//			
//			}
//			else if (mouseEvent.getButton() == MouseButton.MIDDLE){
//				setPositionDesGameObjekt(objekt, mouseEvent);				
//		}	
//			positionAnpassen();	
//			objekt.setWidth(box.getWidth());
//			objekt.setHeight(box.getHeight());
//			objekt.setDepth(box.getDepth());
//	}	
//		
//		private void setStartZustand(){
//			beendeDrucken = true; 
//		}
//		
//		/**
//		 * wird verwendet damit die Mauer nicht ueber die Grenzen hinaus wachsen kann.
//		 */
//		private void positionAnpassen(){	
//			
//			if (box.getBoundsInParent().getMinX() < 0){
//				objekt.setLayoutX(objekt.getLayoutX().get()- box.getBoundsInParent().getMinX());
//				objekt.setWidth(objekt.getWidth().get()+ box.getBoundsInParent().getMinX());		
//			}
//			if (box.getBoundsInParent().getMinX()+objekt.getWidth().get() > regionPane.getWidth()){
//				objekt.setWidth(regionPane.getWidth()-box.getBoundsInParent().getMinX());	
//				objekt.setLayoutX(regionPane.getWidth()-objekt.getWidth().get()/2);		
//			}
//			if (box.getBoundsInParent().getMinY() < 0){
//				objekt.setLayoutY(box.getLayoutY()- box.getBoundsInParent().getMinY());
//				objekt.setHeight(objekt.getHeight().get()+ box.getBoundsInParent().getMinY());		
//			}
//			if (box.getBoundsInParent().getMinY()+objekt.getHeight().get() > regionPane.getHeight()){
//				objekt.setHeight(regionPane.getHeight()-box.getBoundsInParent().getMinY());	
//				objekt.setLayoutY(regionPane.getHeight()-objekt.getHeight().get()/2);		
//			}
//		}
//	}
//
//	
//	private class EventHandelerSetzeGameObjektNode implements EventHandler<MouseEvent>{
//
//		private double startPositionX;
//		private double startPositionY;
//		
//		private GameObjekt objekt;
//		EventHandelerSetzeGameObjektNode(GameObjekt objekt){
//			this.objekt = objekt;
//			startPositionX = objekt.getLayoutX().get();
//			startPositionY = objekt.getLayoutY().get();
//		}
//		
//		@Override
//		public void handle(MouseEvent mouseEvent) 	{
//			
//			//Ueberprueft ob die Position setzbar ist. Wenn dies nicht der Falls ist, wird de Node, die blockiert 
//			//auf nodeBlockierung referenziert
//			GameObjekt objektBlockierung = ueberpruefeDiePositionAufSetzbarkeit(objekt);
//			//blockiert eine Node den Ziel Ort, dann ...
//			if (objektBlockierung != null)
//			{
//				//schaue on die Node ueberhaupt setztbat ist und wenn nicht dann ...
//				if (wennNodeSetzbarDannSetzeAufDieMoeglichePosition(objekt, objektBlockierung) == false)
//				{
//					//versetze die Node auf ihre anfangsPosition zurueck
//					objekt.setLayoutX(startPositionX);
//					objekt.setLayoutY(startPositionY);
//				}
//			}
//				randNodeSetztTest(objekt);
//			}
//		
//		private synchronized void setStartPositionX(double startPositionX) {
//			this.startPositionX = startPositionX;
//		}
//
//		private synchronized void setStartPositionY(double startPositionY) {
//			this.startPositionY = startPositionY;
//		}	
//	}
//	
//	
//	public synchronized ArrayList<GameObjektZuordnung> getGameObjekteOnRegion() {
//		return regionMaker.getGameObjekteOnRegion();
//	}
//
	/**
	 * remove a GameObjekt from the pane
	 * @param objekt
	 */
	public void removeGameObjekt(GameObjekt objekt) {
		this.regionPane.getChildren().remove(objekt);
	}

	public void setRightEinstellung(EinstellungGameObjekt einstellungGameObjket) {
		this.root.setRight(einstellungGameObjket);
	}
}
