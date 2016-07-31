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
	 * 2D-Array der felder, die die Karte aufbauen
	 */
	private Feld[][] felder;
	
	/**
	 * Konstruktor
	 * 
	 * @param width
	 * @param height
	 */
	public Karte(int width, int height) {
		felder = new Feld[width][height];
	}

	/**
	 * @return the felder
	 */
	public Feld[][] getFelder() {
		return felder;
	}

	/**
	 * @param felder the felder to set
	 */
	public void setFelder(Feld[][] felder) {
		this.felder = felder;
	}
}
