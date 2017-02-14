package draufsicht.netzwerk;

import draufsicht.map.Spielwelt;

/**
 * Der Spieler der ein Spiel erstellt hat besitzt einen HostServer.
 * Auf diesen Server befinden sich viele wichtige geminsame Spieldaten die, die Clients der Spieler abfragen  
 * 
 * @author Dennis
 *
 */
public class HostServer 
{
	private Spielwelt spielwelt; 
	
	/**
	 * erstellt einen Server
	 */
	public HostServer()
	{
		this.spielwelt = Spielwelt.getInstance();
	}
	
	/**
	 * erstellt einen Server
	 * @param spielwelt eine vorhhandene Welt wird geladen
	 */
	public HostServer(Spielwelt spielwelt)
	{
		this.spielwelt = Spielwelt.getInstance();
	}
}
