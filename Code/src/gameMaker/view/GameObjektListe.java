package gameMaker.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * wird verwendet von der RegionMakerView um GameObjekte auszuwaehlen. 
 *  
 * @author Dennis
 *
 */
public class GameObjektListe extends TitledPane
{
	/**
	 * In dieser Liste befinden sich die GameObejkte, die dargestellt werden
	 */
//	private ArrayList<GameObjekt> gameObjekteListe = new ArrayList<GameObjekt>(); 
	
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
		this.setOnMousePressed(new EventHandler<Event>() 
		{
			@Override
			public void handle(Event event) 
			{
				
			}
		});
	}
	

	public void addGameObjekt(String name)
	{
		this.gameObjekteListe.getItems().add(name);
	}
}
