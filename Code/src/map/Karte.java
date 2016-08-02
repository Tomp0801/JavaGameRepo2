package map;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Eine 2D-Karte, zum Beispiel eines Planeten
 * Begehbar und Bebaubar
 * 
 * @author Thomas
 *
 */
public class Karte {
	/**
	 * 2D-Array der bereiche, die die Karte aufbauen
	 */
	private Bereich[][] bereiche;
	
	/**
	 * Breite der Karte
	 */
	private int breite;
	
	/**
	 * hoehe der Karte
	 */
	private int hoehe;
	
	/**
	 * Liste der Bodenschaetze, die auf dieser Karte vorkommen können
	 */
	private ArrayList<Bodenschatz> bodenschaetze;
	
	/**
	 * Konstruktor
	 * 
	 * @param breite
	 * @param hoehe
	 * @param bodenschaetze die Art der Bodenschätze, die auf der Karte vorkommen
	 */
	Karte(int breite, int hoehe, ArrayList<Bodenschatz> bodenschaetze) {
		if (breite > 0 && hoehe > 0) {
			this.breite = breite;
			this.hoehe = hoehe;
			this.bodenschaetze = bodenschaetze;
			
			bereiche = new Bereich[breite][hoehe];
		} else {
			//TODO throw exception
		}
	}
	
	/**
	 * generiert automatisch eine Karte
	 * TODO optionen zum generieren überdenken
	 * @param typ
	 */
	private void generate() {
		//TODO generieren erstellen

	}
	
	/**
	 * Gibt den Bereich an einer bestimmten Stelle der Karte wieder
	 * 
	 * @param x x-Koordinate
	 * @param y y-Koordinate
	 * @return der Bereich an der Stelle der angegebenen Koordinaten
	 */
	public Bereich getBereich(int x, int y) {
		if (x >= 0 && x < breite && y >= 0 && y < hoehe) {	//nur wenn die Koordinaten gültig sind
			//Wenn bereich noch nicht aufgerufen wurde, ihn jetzt erstellen
			if (bereiche[x][y] == null) {
				bereiche[x][y] = new Bereich(this, Main.standards.getGelaendeArten().get(1) ,bodenschaetze);	//TODO typen
			}
			
			return bereiche[x][y];			
		} else {
			return null;
		}
	}

	/**
	 * @return the breite
	 */
	public int getBreite() {
		return breite;
	}

	/**
	 * @param breite the breite to set
	 */
	public void setBreite(int breite) {
		this.breite = breite;
	}

	/**
	 * @return the hoehe
	 */
	public int getHoehe() {
		return hoehe;
	}

	/**
	 * @param hoehe the hoehe to set
	 */
	public void setHoehe(int hoehe) {
		this.hoehe = hoehe;
	}

	/**
	 * @return the bodenschaetze
	 */
	public ArrayList<Bodenschatz> getBodenschaetze() {
		return bodenschaetze;
	}
}
