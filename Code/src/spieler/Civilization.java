package spieler;

import java.util.ArrayList;

import map.Sektion;

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
	 */
	private ArrayList<Sektion> sektionsListeDerCivilisation = new ArrayList<Sektion>();
	
	/**
	 * erstellt eine Civilisation
	 */
	Civilization()
	{
		//TODO einen Civilisationsgenerrator muss erstellt werden.
	}
	
}
