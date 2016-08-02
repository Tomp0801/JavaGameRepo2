package map;

import java.util.ArrayList;

/**
 * Ein Bereich von Feldern, auf denen z.B. eine Stadt errichtet werden kann
 * 
 * @author Thomas
 *
 */
public class Bereich {
	/**
	 * breite des Bereichs
	 * TODO richtige Hoehe und Breite festlegen
	 */
	public final int BREITE = 10;
	
	/**
	 * Hoehe des Bereichs 
	 */
	public final int HOEHE = 10;
	
	/**
	 * Die Karte zu der dieser Bereich gehört
	 */
	private Karte parentKarte;
	
	/**
	 * Felder, die bebaut werden können
	 */
	private Feld[][] felder;
	
	/**
	 * Typ des Bereichs (wüste, wiese, wald...)
	 */
	private GelaendeArt typ;
	
	Bereich(Karte parentKarte, GelaendeArt typ, ArrayList<Bodenschatz> bodenschaetze) {
		felder = new Feld[BREITE][HOEHE];	
		this.typ = typ;
		
		for (int y = 0; y < HOEHE; y++) {
			for (int x = 0; x < BREITE; x++) {
				felder[x][y] = new Feld(this, bodenschaetze);
			}
		}
	}

	/**
	 * @return the parentKarte
	 */
	public Karte getParentKarte() {
		return parentKarte;
	}
	
	/**
	 * Gibt das Feld an einer bestimmten Stelle des Bereichs wieder
	 * 
	 * @param x x-Koordinate
	 * @param y y-Koordinate
	 * @return das Feld an der Stelle der angegebenen Koordinaten
	 */
	public Feld getFeld(int x, int y) {
		if (x >= 0 && x < BREITE && y >= 0 && y < HOEHE) {	//nur wenn die Koordinaten gültig sind
			return felder[x][y];
		} else {
			return null;
		}
	}

	/**
	 * @return the typ
	 */
	public GelaendeArt getTyp() {
		return typ;
	}
}
