package gameMaker.view;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import personensicht.model.gameObjekte.GameObjekt;
import personensicht.model.gameObjekte.Mauer;

public class ListeGameObjektLinks extends VBox{

	public ListeGameObjektLinks()
	{
		this.setMaxWidth(200);
		GameObjektAuswalListeTitledPane regionEinstellungenTitlePane = new GameObjektAuswalListeTitledPane("Eigenschaften");
		regionEinstellungenTitlePane.addGameObjekt("Mauer");
		regionEinstellungenTitlePane.addGameObjekt("Nichts");
		GameObjektAuswalListeTitledPane moebel = new GameObjektAuswalListeTitledPane("M�bel");
		moebel.addGameObjekt("Schrank");
		moebel.addGameObjekt("T�r");
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
		private ListView<String> GameObjektAuswalListeTitledPane = new ListView<String>();
		private Boolean auswahlGetroffen = false; 	
		
		protected GameObjektAuswalListeTitledPane(String nameDerListe)
		{
			//Einstellungen
			this.setMaxWidth(200);
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.setContent(GameObjektAuswalListeTitledPane);
			this.setText(nameDerListe);
			this.setContent(scrollPane);
			
			GameObjektAuswalListeTitledPane.setOnMouseDragged(new EventHandler<MouseEvent>(){
			
				RegionMakerV instance = RegionMakerV.getInstance();
				  @Override public void handle(MouseEvent mouseEvent) {
					  if (auswahlGetroffen == false)
					  {
						  AuswahlGameObjekt auswahl =  new AuswahlGameObjekt( GameObjekt.getTypeOfGameObjekt(GameObjektAuswalListeTitledPane.getSelectionModel().getSelectedItem() ) ); 
						  instance.getGameObjekteOnRegion().add(auswahl);
						  if (auswahl.getEinstellungGameObjket().getGameObjekt() instanceof Mauer){
							  instance.platzierungMauer(auswahl);
						  }
						  else{
							  instance.setzeGameObjektAufDieRegion(instance.getGameObjekteOnRegion().get(instance.getGameObjekteOnRegion().size()-1));
							
						  }
						  setAuswahlGetroffen(true);
						  }
					  instance.setPositionDesGameObjekt( instance.getGameObjekteOnRegion().get(instance.getGameObjekteOnRegion().size()-1).getGameObjekt(), mouseEvent);
						System.out.println("bin hier");
				  }
			});	
			GameObjektAuswalListeTitledPane.setOnMouseReleased(new EventHandler<MouseEvent>(){
				RegionMakerV instance = RegionMakerV.getInstance();
				  @Override public void handle(MouseEvent mouseEvent) {
					  if (instance.getGameObjekteOnRegion().size() > 0)
					  { 
						GameObjekt gameObjet = instance.getGameObjekteOnRegion().get(instance.getGameObjekteOnRegion().size()-1).getGameObjekt();
						Node node = instance.getGameObjekteOnRegion().get(instance.getGameObjekteOnRegion().size()-1).getNode();
						instance.randNodeSetztTest(gameObjet);
						GameObjekt objektBlockerung =  instance.ueberpruefeDiePositionAufSetzbarkeit(gameObjet);
						if (objektBlockerung != null)
						{
							//wenn die Node nicht setzbar ist dann ...
							if (instance.wennNodeSetzbarDannSetzeAufDieMoeglichePosition(gameObjet, objektBlockerung) ==  false)
							{
								instance.removeNodeFromRegion(node);	
							}	
						}
					  }  
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
}
