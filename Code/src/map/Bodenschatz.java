package map;

/**
 * Ressource, die von Feldern abgebaut werden kann
 * 
 * @author Thomas
 *
 */
public class Bodenschatz {
	/**
	 * Bezeichnung des Bodenschatzes
	 */
	private String name;
	
	/**
	 * die Wahrscheinlichkeit, dass dieser Rohstoff vorkommt in Prozent ( > 1)
	 * abhängig von einer einstellung LOW, MID, HIGH (0, 1, 2)
	 */
	private float vorkommensWkeit;
	
	/**
	 * Werkzeug oder Methode, mit dem die Ressource abgebaut werden kann
	 */
	private String abbauWerkzeug;

	/**
	 * Konstruktor
	 * @param abbauMethode legt abbauMethode oder Werkzeug fest
	 */
	Bodenschatz(String name, String abbauWerkzeug, float vorkommensWkeit) {
		this.name = name;
		this.abbauWerkzeug = abbauWerkzeug;
		
		if (vorkommensWkeit < 1 && vorkommensWkeit > 0) {
			this.vorkommensWkeit = vorkommensWkeit;
		}
		//TODO else throw exception
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the vorkommensWkeit
	 */
	public float getVorkommensWkeit() {
		return vorkommensWkeit;
	}

	/**
	 * @param vorkommensWkeit the vorkommensWkeit to set
	 */
	public void setVorkommensWkeit(float vorkommensWkeit) {
		this.vorkommensWkeit = vorkommensWkeit;
	}

	/**
	 * @return the abbauWerkzeug
	 */
	public String getAbbauWerkzeug() {
		return abbauWerkzeug;
	}
	
	
}
