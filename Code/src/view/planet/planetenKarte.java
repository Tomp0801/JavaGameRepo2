package view.planet;

import himmelskoerper.Betretbar;
import himmelskoerper.FestPlanet;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import map.Karte;

/**
 * Hier wird die Karte eines Planeten Visualisiert 
 * @author Demix
 *
 */
public class planetenKarte 
{

	/**
	 * Hier werden die felder dargestellt
	 */
	private StackPane[][] felder; 
	
	/**
	 * Hier werden die Bereiche dargestellt
	 */
	private StackPane[][] bereich; 
	
	/**
	 * dies ist die Karte, die dargestellt wird. 
	 */
	private Karte karte; 
	
	/**
	 * erstellt eine Karte eines Plaeten
	 */
	public planetenKarte(Betretbar planet)
	{
		this.karte = planet.getKarte(); 
		//Die Bereiche angelegt
		bereich = new StackPane[karte.getBreite()][karte.getHoehe()];
			
		//Felder eines Bereiches werden geladen
		for (int i = 0 ; karte.getBreite() > i ; i++)
		{
			for (int j = 0 ; karte.getHoehe() > j ; j++)
			{
				bereich[i][j].getChildren().add(karte.getBereich(i, j).getAussehen());
				
				bereich[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() 
				{
					@Override
					public void handle(MouseEvent event) 
					{	
						if (event.getClickCount() == 1)
						{
							// TODO lade Informationen ueber den Bereich
						}
						//Bei doppelklick
						if (event.getClickCount() == 2)
						{
							//TODO lade Felder
						}
					}
				});
			}
		}
	}
}
