package ressource;

import java.awt.Color;
import java.io.Serializable;
import java.util.HashMap;

import global.Agregat;

/**
 * Grundklasse für alle Stoffe Materialen, etc.
 * 
 * z.B. Erze, Stein, Essen, Holz...
 * 
 * 
 * @author Thomas
 *
 */
public abstract class Material implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4161272941765820029L;

	/**
	 * Bezeichnung für das Material
	 */
	private String name;
	
	/**
	 * Farbe der Material
	 * Kann nicht javafx Color nehmen, da diese nicht serializeable ist
	 * TODO suboptimal, wegen umwandlung von 0-1 zu 0-255
	 */
	private Color color;
	
	/**
	 * die durchsichtigkeit der Farbe des Materials. 0 = durchsichtig, 1 = undurchsichtig
	 */
	private double opacity;
	
	/**
	 * das Gewicht der Material pro Einheit
	 * relevant für den Transport
	 */
	private float gewicht;
	
	/**
	 * Nahrungswert pro einheit von diesem Material
	 */
	private float naehrwert;
	
	/**
	 * Energie pro Einheit von diesem Material
	 */
	private float energie;
	
	/**
	 * Stabilität des Materials
	 * relevant für Material als Baustoff
	 */
	private float stabilitaet;
	
	/**
	 * Einheit: °C
	 */
	private int schmelzpunkt;
	
	/**
	 * Einheit: °C
	 */
	private int siedepunkt;
	
	/**
	 * Temperatur, bei der das Material verbrennt
	 * TODO nötig? im Prozess mit in behalten?
	 * Einheit: °C 
	 */
	//private int verbrennpunkt;
	
	/**
	 * Forschungsfortschritt an diesem Material.
	 * Je mehr Forschung, desto mehr eigenschaften werden freigeschaltet
	 * TODO für verschiedene Spieler??
	 */
	private int forschung;
	
	/**
	 * HashMap mit Prozessen und ihren Ergebnissen, die an diesem Material durchgeführt 
	 * werden können
	 */
	private HashMap<Prozess, Material> verarbeitung;
	
	/**
	 * Kosntruktor mit javafx Color, da diese die opacity mit enthält
	 * 
	 * @param name des Materials
	 * @param color (javafx.scene.paint)
	 */
	public Material(String name, javafx.scene.paint.Color color) 
	{
		this.name = name;
		this.color = new Color((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue());
		this.opacity = color.getOpacity();
	}
	
	/**
	 * vollständiger Konstruktor
	 * 
	 * @param name Name des Materials
	 * @param color Farbe des Materials
	 * @param spezifikationen array von floats: 1. gewicht, 2. nährwert, 3. energie, 4. stabilität
	 * @param agregatpunkt array von ints: 1. schmelzpunkt, 2. siedepunkt
	 * @param verarbeitung Liste mit möglichen Prozessen und den Materialien, zu denen sie führen, wenn angewandt
	 */
	public Material(String name, javafx.scene.paint.Color color, float spezifikationen[], int agregatPunkte[], HashMap<Prozess, Material> verarbeitung)
	{
		forschung = 0;
		this.name = name;
		this.color = new Color(Math.round(color.getRed() * 255), Math.round(color.getGreen() * 255), Math.round(color.getBlue() * 255));
		this.opacity = color.getOpacity();
		if (spezifikationen.length == 4)
		{
			this.gewicht = spezifikationen[0];
			this.naehrwert = spezifikationen[1];
			this.energie = spezifikationen[2];
			this.stabilitaet = spezifikationen[3];
		}
		else
		{
			this.gewicht = 0;
			this.naehrwert = 0;
			this.energie = 0;
			this.stabilitaet = 0;
		}
		
		if (agregatPunkte.length == 2)
		{
			this.schmelzpunkt = agregatPunkte[0];
			this.siedepunkt = agregatPunkte[1];
		}
		else
		{
			this.schmelzpunkt = 0;
			this.siedepunkt = 100;
		}
		this.verarbeitung = verarbeitung;
	}
	
	/**
	 * gibt wieder, welches Material entsteht, wenn diese mit einem Prozess bearbeitet wird 
	 * @param prozess der Prozess mit dem dieses Material bearbeitet wird
	 * @return Material das dabei entsteht
	 */
	public Material verarbeite(Prozess prozess)
	{
		return verarbeitung.get(prozess);
	}
	
	/**
	 * Gibt agregatzustand des Materials bei gegebener Temperatur wider
	 * @param temperatur gegebene Temperatur
	 * @return Agregat zusatnd des Materials bei dieser Temperatur
	 */
	public Agregat getAgregatzustand(int temperatur)
	{
		if (temperatur < schmelzpunkt)
		{
			return Agregat.FEST;
		}
		else if (temperatur < siedepunkt)
		{
			return Agregat.FLUESSIG;
		}
		else 
		{
			return Agregat.GAS;
		}
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
	 * @return die Farbe als javafx Color
	 */
	public javafx.scene.paint.Color getColor() {
		return new javafx.scene.paint.Color((double)color.getRed()/255.0, (double)color.getGreen()/255.0, (double)color.getBlue()/255.0, opacity);
	}

	/**
	 * @param color the color to set
	 */
	protected void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return die Transparenz der Farbe
	 */
	public double getOpacity() {
		return opacity;
	}

	/**
	 * @param opacity setzt die Transparenz (Zahl von 0 bis 1)
	 */
	public void setOpacity(double opacity) {
		this.opacity = opacity;
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
}
