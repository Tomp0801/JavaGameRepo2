package map;

import java.awt.Color;
import java.io.Serializable;

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
	 * die durchsichtigkeit der Farbe des Materials. 0 = durchsichtig, 1 = undurchsichtig
	 */
	private double opacity;
	
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
	
//	/**
//	 * Kosntruktor
//	 * 
//	 * @param name
//	 * @param color
//	 */
//	public Ressource(String name, Color color) 
//	{
//		this.name = name;
//		this.color = color;
//	}
	
	/**
	 * Kosntruktor mit javafx Color, sollte eher benutzt werden, da diese die opacity mit enthält
	 * 
	 * @param name des Materials
	 * @param color (javafx.scene.paint)
	 */
	public Ressource(String name, javafx.scene.paint.Color color) 
	{
		this.name = name;
		this.color = new Color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue());
		this.opacity = color.getOpacity();
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
	public javafx.scene.paint.Color getColor() {
		return new javafx.scene.paint.Color((double)color.getRed(), (double)color.getGreen(), (double)color.getBlue(), opacity);
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the opacity
	 */
	public double getOpacity() {
		return opacity;
	}

	/**
	 * @param opacity the opacity to set
	 */
	public void setOpacity(double opacity) {
		this.opacity = opacity;
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
