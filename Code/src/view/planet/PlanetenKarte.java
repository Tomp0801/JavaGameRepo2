package view.planet;

import himmelskoerper.Betretbar;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import map.Karte;

/**
 * Hier wird die Karte eines Planeten Visualisiert 
 * @author Demix
 *
 */
public class PlanetenKarte extends StackPane
{
	/**
	 * dies ist die Karte, die dargestellt wird. 
	 */
	private Karte karte; 
	
	/**
	 * in dieser Box beinhaltet HBoxen mit eine Ansammlung von Bereichen. 
	 */
	private VBox firstBox = new VBox(); 
	
	/**
	 * in dieser Box befinden sich die Bereiche eines Planeten
	 */
	private HBox[] secondBox;;
	
	/**
	 * Hier werden die Bereiche dargestellt
	 */
	private StackPane[][] bereich; 
	
	/**
	 * erstellt eine Karte eines Plaeten
	 */
	public PlanetenKarte(Betretbar planet)
	{
		this.karte = planet.getKarte(); 
		this.getChildren().add(firstBox);
		this.secondBox = new HBox[karte.getHoehe()];	
		
		
		//Die Bereiche angelegt
		this.bereich = new StackPane[karte.getBreite()][karte.getHoehe()];
		for (int i = 0 ; karte.getHoehe() > i ; i++)
		{			
			for (int j = 0 ; karte.getBreite() > j ; j++)
			{	
				bereich[i][j] = new StackPane();
				bereich[i][j].prefHeight(2000);
				bereich[i][j].prefWidth(2000);
			}
		}
		
		//Felder eines Bereiches werden geladen
		for (int i = 0 ; karte.getHoehe() > i ; i++)
		{
			this.secondBox[i] = new HBox();
			this.firstBox.getChildren().add(secondBox[i]);
			
			for (int j = 0 ; karte.getBreite() > j ; j++)
			{
				secondBox[i].getChildren().add(this.bereich[i][j]);
				this.bereich[i][j].getChildren().add(karte.getBereich(i, j).getAussehen());
				
				int indexX = i;
				int indexY = j;
				
				bereich[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() 
				{
					int[] bereich = {indexX , indexY}; 
					
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
//							ladeFelder(indexX , indexY); TODO 
						}
					}
				});
			}
		}
	}
}
