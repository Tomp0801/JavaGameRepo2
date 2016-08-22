package himmelskoerper;

import map.Karte;

/**
 * Interface für Objekte, die von Spielfiguren betretbar sind
 * 
 * TODO weitere wichtige Funktionen überlegen
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
	 * @return die Oberflächen Temperatur
	 */
	public float getOberflaechenTemperatur();
	
	/**
	 * 
	 * @return die Breite, die die zugehörige Karte haben soll
	 */
	public int getKartenBreite();
}
