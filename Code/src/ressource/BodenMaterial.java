package ressource;

import javafx.scene.paint.Color;

/**
 * Material, die von Feldern abgebaut werden kann
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
	 * die Wahrscheinlichkeit, dass dieser Rohstoff vorkommt in Prozent ( < 1)
	 * TODO abhängig von einer einstellung LOW, MID, HIGH (0, 1, 2)
	 */
	private float vorkommensWkeit;

	/**
	 * Konstruktor
	 * @param abbauMethode legt abbauMethode oder Werkzeug fest
	 */
	public BodenMaterial(String name, Color color, float vorkommensWkeit) {
		super(name, color);
		
		setVorkommensWkeit(vorkommensWkeit);
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
	protected void setVorkommensWkeit(float vorkommensWkeit) {
		if (vorkommensWkeit < 1 && vorkommensWkeit > 0) {
			this.vorkommensWkeit = vorkommensWkeit;
		}
	}
}
