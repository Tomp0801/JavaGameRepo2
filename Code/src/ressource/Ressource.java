package ressource;

import java.awt.Color;
import java.io.Serializable;

/**
 * Grundklasse f�r alle Stoffe Materialen, etc.
 * 
 * z.B. Erze, Stein, Essen, Holz...
 * 
 * 
 * @author Thomas
 *
 */
public class Ressource implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4161272941765820029L;

	/**
	 * Bezeichnung f�r die Ressource
	 */
	private String name;
	
	/**
	 * Farbe der Ressource
	 * Kann nicht javafx Color nehmen, da diese nicht serializeable ist
	 */
	private Color color;
	
	/**
	 * die durchsichtigkeit der Farbe des Materials. 0 = durchsichtig, 1 = undurchsichtig
	 */
	private double opacity;
	
	/**
	 * das Gewicht der Ressource pro Einheit
	 * relevant f�r den Transport
	 */
	private float gewicht;
	
	/**
	 * Kosntruktor mit javafx Color, da diese die opacity mit enth�lt
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
	protected void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return die Farbe als jafafx Color
	 */
	public javafx.scene.paint.Color getColor() {
		return new javafx.scene.paint.Color((double)color.getRed(), (double)color.getGreen(), (double)color.getBlue(), opacity);
	}

	/**
	 * @param color the color to set
	 */
	protected void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the opacity der Farbe
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
	 * relevant f�r den Transport von Ressourcen
	 * @return das Gewicht der Ressource pro Einheit
	 */
	public float getGewicht() {
		return gewicht;
	}

	/**
	 * @param gewicht the gewicht to set
	 */
	protected void setGewicht(float gewicht) {
		this.gewicht = gewicht;
	}
}
