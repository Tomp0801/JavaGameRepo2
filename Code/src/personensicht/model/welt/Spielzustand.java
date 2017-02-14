package personensicht.model.welt;

import personensicht.model.welt.map.Region;

/**
 * beinhaltete alle wichtigen Informationen die das Spiel betreffen zum Beispiel die position des Spielers
 * @author Dennis
 *
 */
public class Spielzustand 
{
	/**
	 * der Ort, auf dem sich der Spieler grade befindet
	 */
	private static Region ort; 
	
	public Spielzustand()
	{}

	public static synchronized Region getOrt() {
		return ort;
	}

	public static synchronized void setOrt(Region neuerort) {
		ort = neuerort;
	}
}
