package map;

/**
 * Ressource, die von Feldern abgebaut werden kann
 * 
 * @author Thomas
 *
 */
public class BodenMaterial extends Material {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7537111714172956705L;

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
	public BodenMaterial(String name, String abbauWerkzeug, float vorkommensWkeit) {
		setName(name);
		this.abbauWerkzeug = abbauWerkzeug;
		
		if (vorkommensWkeit < 1 && vorkommensWkeit > 0) {
			this.vorkommensWkeit = vorkommensWkeit;
		}
		//TODO else throw exception
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
