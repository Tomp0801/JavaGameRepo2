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
	 */
	public Karte getKarte();
	
	/**
	 * 
	 * @return die Oberfl�chen Temperatur
	 */
	public float getOberflaechenTemperatur();
	
	/**
	 * 
	 * @return die Breite, die die zugeh�rige Karte haben soll
	 */
	public int getKartenBreite();
}
