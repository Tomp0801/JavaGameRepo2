package gameMaker.controll;

import gameMaker.view.einstellungGameObjekte.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Box;
import personensicht.model.gameObjekte.*;
import personensicht.model.gameObjekte.lebewesen.*;
import personensicht.model.gameObjekte.lebewesen.mensch.Koerper;

public class EventCrt {

	private static EventCrt instance; 
	private EventCrt(){
		instance = this; 
	}
	
	public static EventCrt getInstance(){
		if (instance == null)
			new EventCrt(); 
		return instance;
	}
	
	public void setNodePassendenEventHandler(GameObjekt objekt){
		//Viewkomponente erstellen, die bei einem Klick aufgerufen und rechts angezeigt werden soll
		EinstellungGameObjekt einstellungGameObjket = initEinstellungGameObjekt(objekt);
		//Dieser Node sollen die EvenetHandler zugeordneet werden
		Node node = objekt.getNodeObjekt().getNode();
		//Beim anklicken der Node, soll die Viewkomponente geladen werden ...
		node.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				RegionMakerCrt.getInstance().getRegionMakerScene().setRightEinstellung(einstellungGameObjket);	
			}
		});
		
		//unterscheide nun veerschiedene GameObjekte ...
		//EventHandler bei einer Mauer ...
		if (objekt instanceof Mauer){
			MauerSetzHandler mouseHandler = new MauerSetzHandler(objekt);
			node.setOnMouseDragged(mouseHandler);
			node.setOnMouseReleased(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					mouseHandler.setStartZustand();
				}
			});	
		  }
		//EventHander bei den andern Nodes ...
		  else{  
				//erstellt EventHandler. Um das Umsetzen einer Node zu ermoeglichen
				node.setOnMouseDragged(new EventHandler<MouseEvent>(){
					  @Override public void handle(MouseEvent mouseEvent) {
						  //laesst die Node dem Courser folgen
						  RegionMakerCrt.getInstance().getRegionMakerScene().setPositionDesGameObjekt(objekt , mouseEvent);
					  }
				});	
				//Dieses Event, beschreibt was passiert wenn die ein Node gesetzt wird. Dazu wird ueberprueft ob diese Position
				//schon von einer andern Node besetzt ist. 
				EventHandelerSetzeGameObjektNode eventHandlerReleased = new EventHandelerSetzeGameObjektNode(objekt);
				node.setOnMouseReleased(eventHandlerReleased);
				
				node.setOnMousePressed(new EventHandler<Event>() {
					@Override
					public void handle(Event event) {
						eventHandlerReleased.setStartPositionX(objekt.getLayoutX().get());
						eventHandlerReleased.setStartPositionY(objekt.getLayoutY().get());	
					}
				});
		  }	
	}
	
	
	
	/**
	 * erstellt eine Viewkomponente zum Einstellen des Objektes.
	 * ist noch keine Modelklasse des Objektes verfuegbar wird eine neue erstellt
	 */
	private static EinstellungGameObjekt initEinstellungGameObjekt(GameObjekt objekt){
		EinstellungGameObjekt einstellungGameObjket = null;
		switch (objekt.getTyp())
		{
		case Bett:
			Bett bett = (Bett) objekt;
			einstellungGameObjket = new EinstellungBett(bett); 
			break; 
		case Item://TODO
			break;
		case Mensch:
			Koerper mensch = new Koerper("DefaultName");
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
		case Stuhl:
			Stuhl stuhl = new Stuhl();
			einstellungGameObjket = new EinstellungStuhl(stuhl); 
			break;
		case Tisch:
			Tisch tisch = new Tisch();
			einstellungGameObjket = new EinstellungTisch(tisch); 
			break;
		case Mauer:
			Mauer mauer = new Mauer();
			einstellungGameObjket = new EinstellungMauer(mauer); 
			break;
		default:
			break;
		}
		return einstellungGameObjket;
	}
	
	
	
	
	
	
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
			private AnchorPane regionPane = RegionMakerCrt.getInstance().getRegionMakerScene().getRegionPane(); 
			
			private MauerSetzHandler(GameObjekt objekt){
				
				this.box = (Box) objekt.getNodeObjekt().getNode();
				this.objekt = objekt; 
				startSizeX = objekt.getWidth().get();
				startSizeY = objekt.getHeight().get();
			}
			
			@Override
				public void handle(MouseEvent mouseEvent) {

				if (beendeDrucken){
					//legt den start position des Coursers fest. dies beeinflußt ob die Mauer groeßer oder kleiner werdden soll
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
					//wenn nach recht vergroeßert werden soll dann ..
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
					//wenn nach recht vergroeßert werden soll dann ..
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
					RegionMakerCrt.getInstance().getRegionMakerScene().setPositionDesGameObjekt(objekt, mouseEvent);				
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
		
		private class EventHandelerSetzeGameObjektNode implements EventHandler<MouseEvent>{

			private double startPositionX;
			private double startPositionY;
			
			private GameObjekt objekt;
			EventHandelerSetzeGameObjektNode(GameObjekt objekt){
				this.objekt = objekt;
				startPositionX = objekt.getLayoutX().get();
				startPositionY = objekt.getLayoutY().get();
			}
			
			@Override
			public void handle(MouseEvent mouseEvent) 	{
				
				//Ueberprueft ob die Position setzbar ist. Wenn dies nicht der Falls ist, wird de Node, die blockiert 
				//auf nodeBlockierung referenziert
				GameObjekt objektBlockierung = RegionMakerCrt.getInstance().getRegionMakerScene().ueberpruefeDiePositionAufSetzbarkeit(objekt);
				//blockiert eine Node den Ziel Ort, dann ...
				if (objektBlockierung != null)
				{
					//schaue on die Node ueberhaupt setztbat ist und wenn nicht dann ...
					if (RegionMakerCrt.getInstance().getRegionMakerScene().wennNodeSetzbarDannSetzeAufDieMoeglichePosition(objekt, objektBlockierung) == false)
					{
						//versetze die Node auf ihre anfangsPosition zurueck
						objekt.setLayoutX(startPositionX);
						objekt.setLayoutY(startPositionY);
					}
				}
				RegionMakerCrt.getInstance().getRegionMakerScene().randNodeSetztTest(objekt);
				}
			
			private synchronized void setStartPositionX(double startPositionX) {
				this.startPositionX = startPositionX;
			}

			private synchronized void setStartPositionY(double startPositionY) {
				this.startPositionY = startPositionY;
			}	
		}
}
