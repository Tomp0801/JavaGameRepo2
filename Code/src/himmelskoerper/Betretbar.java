package himmelskoerper;

import map.Karte;

/**
 * Interface f�r Objekte, die von Spielfiguren betretbar sind
 * 
 * TODO weitere wichtige Funktionen �berlegen
 * 
 * @author Thomas
 * @version 0.0
 */
public interface Betretbar {
	/**
	 * @return map des Objekts, die begehbar und bebaubar ist
	 * TODO return type
	 */
	public Karte getKarte();
}
