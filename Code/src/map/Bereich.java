package map;

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
	 * kennzeichnet, ob dieser Bereich bereits initialisiert wurde, oder noch nicht
	 */
	private boolean init;
	
	/**
	 * Die Karte zu der dieser Bereich gehört
	 */
	private Karte parentKarte;
	
	/**
	 * Felder, die bebaut werden können
	 */
	private Feld[][] felder;
	
	Bereich(Karte parentKarte) {
		this.parentKarte = parentKarte;
		felder = new Feld[BREITE][HOEHE];
		
		init = false;
	}
	
	public void initFelder() {
		//Felder initialisieren/erstellen
		for (int y = 0; y < HOEHE; y++) {
			for (int x = 0; x < BREITE; x++) {
				felder[x][y] = new Feld(this, parentKarte.getBodenschaetze(), parentKarte.getBodenarten());
			}
		}
		
		init = true;
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
	 * @return the init
	 */
	public boolean isInit() {
		return init;
	}

	/**
	 * @return the parentKarte
	 */
	public Karte getParentKarte() {
		return parentKarte;
	}
}
