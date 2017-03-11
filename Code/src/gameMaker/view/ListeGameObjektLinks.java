package gameMaker.view;

import gameMaker.controll.RegionMakerCrt;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import personensicht.model.gameObjekte.GameObjekt;
import personensicht.model.gameObjekte.GameObjektType;
import personensicht.model.gameObjekte.Mauer;

public class ListeGameObjektLinks extends VBox {

	public ListeGameObjektLinks()
	{
		this.setMaxWidth(200);
		GameObjektAuswalListeTitledPane regionEinstellungenTitlePane = new GameObjektAuswalListeTitledPane("Eigenschaften");
		regionEinstellungenTitlePane.addGameObjekt("Mauer");
		regionEinstellungenTitlePane.addGameObjekt("Nichts");
		GameObjektAuswalListeTitledPane moebel = new GameObjektAuswalListeTitledPane("Möbel");
		moebel.addGameObjekt("Schrank");
		moebel.addGameObjekt("Tür");
		moebel.addGameObjekt("Tisch");
		moebel.addGameObjekt("Stuhl");
		moebel.addGameObjekt("Bett");
		GameObjektAuswalListeTitledPane lebewesen = new GameObjektAuswalListeTitledPane("Lebewesen");
		lebewesen.addGameObjekt("Mensch");
		this.getChildren().addAll(regionEinstellungenTitlePane,moebel,lebewesen);
	}
	
	
	
	/**
	 * Eine Klasse um unterschiedliche Kategorien von GameObjekten geordnet darzustellen. 
	 * @author Dennis
	 */
	private class GameObjektAuswalListeTitledPane extends TitledPane
	{		
		/**
		 * In dieser Liste befinden sich die GameObejkte, die dargestellt werden
		 */
		private ListView<String> gameObjektAuswalListeTitledPane = new ListView<String>();
		private Boolean auswahlGetroffen = false; 	
		//das Objekt, das ausgewaehlt wurde.
		private GameObjekt ausgewaehltesObjekt = null;
		
		protected GameObjektAuswalListeTitledPane(String nameDerListe)
		{
			//Einstellungen
			this.setMaxWidth(200);
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setContent(gameObjektAuswalListeTitledPane);
			this.setText(nameDerListe);
			this.setContent(scrollPane);
			
			gameObjektAuswalListeTitledPane.setOnMouseDragged(new EventHandler<MouseEvent>(){
			
				
				  @Override public void handle(MouseEvent mouseEvent) {
					  RegionMakerScene instance = RegionMakerCrt.getInstance().getRegionMakerScene();
					  
					  if (ausgewaehltesObjekt == null)
					  {
//						  GameObjektZuordnung auswahl =  new GameObjektZuordnung( GameObjekt.getTypeOfGameObjekt(GameObjektAuswalListeTitledPane.getSelectionModel().getSelectedItem() ) ); 
//						  instance.getGameObjekteOnRegion().add(auswahl);
//						  if (auswahl.getEinstellungGameObjket().getGameObjekt() instanceof Mauer){
//							  instance.platzierungMauer(auswahl);
//						  }
//						  else{
//							  instance.setzeGameObjektAufDieRegion(instance.getGameObjekteOnRegion().get(instance.getGameObjekteOnRegion().size()-1));
//							
//						  }
						  ausgewaehltesObjekt = GameObjekt.getANewGameObjektOfType(GameObjekt.getTypeOfGameObjekt(gameObjektAuswalListeTitledPane.getSelectionModel().getSelectedItem()));
						  RegionMakerCrt.getInstance().setGameObjekt(ausgewaehltesObjekt);
//						  setAuswahlGetroffen(true);
						}
					  instance.setPositionDesGameObjekt(ausgewaehltesObjekt, mouseEvent);
				  }
			});	
			gameObjektAuswalListeTitledPane.setOnMouseReleased(new EventHandler<MouseEvent>(){
				
				  @Override public void handle(MouseEvent mouseEvent) {
					  RegionMakerScene instance = RegionMakerCrt.getInstance().getRegionMakerScene();
					  if (instance.getRegionPane().getChildren().size() > 0)
					  { 
//						GameObjekt gameObjekt = instance.getGameObjekteOnRegion().get(instance.getGameObjekteOnRegion().size()-1).getGameObjekt();
//						Node node = instance.getGameObjekteOnRegion().get(instance.getGameObjekteOnRegion().size()-1).getNode();
						instance.randNodeSetztTest(ausgewaehltesObjekt);
						GameObjekt objektBlockerung =  instance.ueberpruefeDiePositionAufSetzbarkeit(ausgewaehltesObjekt);
						if (objektBlockerung != null)
						{
							//wenn die Node nicht setzbar ist dann ...
							if (instance.wennNodeSetzbarDannSetzeAufDieMoeglichePosition(ausgewaehltesObjekt, objektBlockerung) ==  false)
							{
								RegionMakerCrt.getInstance().removeGameOjekt(ausgewaehltesObjekt);	
							}	
						}
					  }  
					  //das Objekt wurde gsetzt und die Auswahl wird nun wieder auf null gesetzt.
					  	ausgewaehltesObjekt = null; 
				  }
			});
			
		}
		

		public void addGameObjekt(String name){
			this.gameObjektAuswalListeTitledPane.getItems().add(name);
		}
		
//		private void setAuswahlGetroffen(Boolean wahl)
//		{
//			auswahlGetroffen= wahl;
//		}
	}	
}
