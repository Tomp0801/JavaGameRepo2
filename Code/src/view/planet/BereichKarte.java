package view.planet;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import map.Bereich;

/**
 * Zeigt Felder eines Bereiches von einer Karte eines Planeten
 * @author Demix
 *
 */
public class BereichKarte extends StackPane
{
	/**
	 * Hier werden die felder dargestellt
	 */
	private StackPane[][] felder; 
	
	/**
	 * dieser Bereich eines Planeten wird hier dargestellt
	 */
	private Bereich bereich;

	/**
	 * erstellt ein Objekt von der Klasse BereichKarte
	 * @param bereich
	 */
	public BereichKarte(Bereich bereich)
	{
		for (int i = 0 ; bereich.BREITE > i ; i++)
		{
			for (int j = 0 ; bereich.HOEHE > j ; j++)
			{
				felder[i][j].getChildren().clear();
				felder[i][j].getChildren().add(bereich.getFeld(i, j).getAussehen());
			}
		}
	}
}
