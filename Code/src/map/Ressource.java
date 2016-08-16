package map;

import java.io.Serializable;

import javafx.scene.paint.Color;

/**
 * Grundklasse für alle Stoffe Materialen, etc.
 * 
 * z.B. Erze, Stein, Essen, Holz...
 * 
 * 
 * @author Thomas
 *
 */
public abstract class Ressource implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4161272941765820029L;

	/**
	 * Bezeichnung für die Ressource
	 */
	private String name;
	
	/**
	 * Farbe der Ressource
	 */
	private Color color;
	
	/**
	 * Energie, die das Material beinhaltet und die, z.B. durch Verbrennung umgesetzt werden kann
	 * TODO einheit
	 */
	private float innereEnergie;
	
	/**
	 * Das Material, das durch Verbrennung dieses Materials entsteht
	 * TODO nötig hier?
	 */
	private Material verbrennErgebnis;
	
	/**
	 * Kosntruktor
	 * 
	 * @param name
	 * @param color
	 */
	public Ressource(String name, Color color) 
	{
		this.name = name;
		this.color = color;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the innereEnergie
	 */
	public float getInnereEnergie() {
		return innereEnergie;
	}

	/**
	 * @param innereEnergie the innereEnergie to set
	 */
	protected void setInnereEnergie(float innereEnergie) {
		this.innereEnergie = innereEnergie;
	}

}
