package map;

/**
 * Eine Gel�nde art mit entsprechenden Eigenschaften 
 * z.B. Temperatur, Vegetation, vorkommen von Rohstoffen
 * 
 * @author Thomas
 *
 */
public class GelaendeArt {
	/**
	 * Bezeichnung dieser Gel�ndeArt
	 */
	private String name;
	
	/**
	 * Material, aus dem der Boden grunds�tzlich besteht 
	 */
	private String grundBoden;
	
	/**
	 * DurchschnittsTemperatur, die hier herrscht
	 */
	private float temperatur;
	
	/**
	 * Faktor, wie viele Bdensch�tze hier vorkommen
	 */
	private float bodenReichtum;
	
	/**
	 * Konstruktor
	 * 
	 * @param name sets Name des Gel�ndes
	 * @param bodenReichtum sets Faktor f�r Bodensch�tze Vorkommen
	 */
	GelaendeArt(String name, float bodenReichtum) {
		this.name = name;
		this.bodenReichtum = bodenReichtum;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the grundBoden
	 */
	public String getGrundBoden() {
		return grundBoden;
	}

	/**
	 * @return the bodenReichtum
	 */
	public float getBodenReichtum() {
		return bodenReichtum;
	}
}
