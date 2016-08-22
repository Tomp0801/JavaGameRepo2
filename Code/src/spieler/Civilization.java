package spieler;

import java.util.ArrayList;

/**
 * Eine Civilization ist ein Reich das von einem oder mehrere Spieler gesteuert werden kann.
 * Hier befinden sich alle wichtigen Daten ueber einen Civilisation.
 * 
 * @author Demix
 *
 */
public class Civilization 
{
	/**
	 * eine Liste der Spieler die dieser Civilization steuern
	 */
	private ArrayList<Spieler> spielerListe = new ArrayList<Spieler>();
	
	/**
	 * eine Liste der Sektionen die den Spieler beansrpucht hat. 
	 * TODO was ist eine Sektion?
	 */
//	private ArrayList<Sektion> sektionsListeDerCivilisation = new ArrayList<Sektion>();
	
	/**
	 * Liste aller Civilisationen die endeckt wurden
	 */
	private ArrayList<Civilization> endeckteSpieler = new ArrayList<Civilization>();
	
	/**
	 * erstellt eine Civilisation
	 */
	Civilization()
	{
		//TODO einen Civilisationsgenerrator muss erstellt werden.
	}
	
}
