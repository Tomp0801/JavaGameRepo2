package map;

/**
 * Eine Gelände art mit entsprechenden Eigenschaften 
 * z.B. Temperatur, Vegetation, vorkommen von Rohstoffen
 * 
 * @author Thomas
 *
 */
public class GelaendeArt {
	/**
	 * Bezeichnung dieser GeländeArt
	 */
	private String name;
	
	/**
	 * Material, aus dem der Boden grundsätzlich besteht 
	 */
	private String grundBoden;
	
	/**
	 * DurchschnittsTemperatur, die hier herrscht
	 */
	private float temperatur;
	
	/**
	 * Faktor, wie viele Bdenschätze hier vorkommen
	 */
	private float bodenReichtum;
	
	/**
	 * Konstruktor
	 * 
	 * @param name sets Name des Geländes
	 * @param bodenReichtum sets Faktor für Bodenschätze Vorkommen
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
