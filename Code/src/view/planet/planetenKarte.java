package view.planet;

import himmelskoerper.Planet;
import javafx.scene.layout.StackPane;

/**
 * Hier wird die Karte eines Planeten Visualisiert 
 * @author Demix
 *
 */
public class planetenKarte 
{
	/**
	 * der Planet des Oberflaeche dargestellt wird
	 */
	private Planet planet;
	
	private StackPane[][] fede; 
	
	/**
	 * erstellt eine Karte eines Planeten
	 */
	public planetenKarte(Planet planet)
	{
		
		this.planet = planet; 
	}
}
