package map;

/**
 * Ressource, die von Feldern abgebaut werden kann
 * 
 * @author Thomas
 *
 */
public class Rohstoff {
	/**
	 * Wert der Ressource
	 * TODO sinnvolle einheit festlegen, pro kilogramm?
	 * TODO behalten? oder durch markt bestimmen
	 */
	private float wert;
	
	/**
	 * Werkzeug oder Methode, mit dem die Ressource abgebaut werden kann
	 */
	private String abbauWerkzeug;

	/**
	 * Konstruktor
	 * @param abbauMethode legt abbauMethode oder Werkzeug fest
	 */
	public Rohstoff(String abbauWerkzeug) {
		this.abbauWerkzeug = abbauWerkzeug;
	}
	
	/**
	 * @return the wert
	 */
	public float getWert() {
		return wert;
	}

	/**
	 * @param wert the wert to set
	 */
	public void setWert(float wert) {
		this.wert = wert;
	}

	/**
	 * @return the abbauWerkzeug
	 */
	public String getAbbauWerkzeug() {
		return abbauWerkzeug;
	}
	
	
}
