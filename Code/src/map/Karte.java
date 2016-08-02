package map;

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
	 * Konstruktor
	 * 
	 * @param breite
	 * @param hoehe
	 */
	public Karte(int breite, int hoehe) {		//TODO reichen ints?
		if (breite > 0 && hoehe > 0) {
			this.breite = breite;
			this.hoehe = hoehe;
			
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
	private void generate(float[] typ) {
		//TODO generieren erstellen
	}

	/**
	 * @return the felder
	 */
	public Bereich[][] getBereiche() {
		return bereiche;
	}

	/**
	 * @param felder the felder to set
	 */
	public void setBereiche(Bereich[][] bereiche) {
		this.bereiche = bereiche;
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
}
