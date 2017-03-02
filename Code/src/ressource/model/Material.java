package ressource.model;

import javafx.scene.paint.Color;
import java.io.Serializable;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

/**
 * Grundklasse für alle Stoffe Materialen, etc.
 * 
 * z.B. Erze, Stein, Essen, Holz...
 * 
 * 
 * @author Thomas
 *
 */
public class Material implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4161272941765820029L;

	/**
	 * Bezeichnung für das Material
	 */
	private String name;
	
	/**
	 * Farbe des Materials
	 * Kann nicht javafx Color nehmen, da diese nicht serializeable ist
	 */
	private Color color;
	
	/**
	 * das Gewicht der Material pro Einheit
	 * relevant für den Transport
	 */
	private float gewicht;
	
	private Background background; 
	
	/**
	 * Kosntruktor mit javafx Color, da diese die opacity mit enthält
	 * 
	 * @param name des Materials
	 * @param color (javafx.scene.paint)
	 */
	public Material(String name, Color color) 
	{
		this.name = name;
		this.color = color;
		
		background = new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
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
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	protected void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * Gibt einen Hintergrund zurück mit der Farbe dieses Materials
	 * @return
	 */
	public Background asBackground() {
		return background;
	}

	/**
	 * relevant für den Transport von Ressourcen
	 * @return das Gewicht der Material pro Einheit
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
	
	
	
	public static Material WASSER = new Material("Wasser", javafx.scene.paint.Color.BLUE);
	public static Material ERDE = new Material("Erde", javafx.scene.paint.Color.PERU);
	public static Material STEIN = new Material("Stein", javafx.scene.paint.Color.DARKGRAY);
}
