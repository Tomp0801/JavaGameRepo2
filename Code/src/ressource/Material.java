package ressource;

import java.awt.Color;
import java.io.Serializable;
import java.util.HashMap;

import global.Agregat;
import global.StufenEnum;

/**
 * Grundklasse für alle Stoffe Materialen, etc.
 * 
 * z.B. Erze, Stein, Essen, Holz...
 * 
 * TODO Forschungssystem erstellen
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
	 * Wahrscheinlichkeit, mit der dieses Material auf einem Objekt vorkommt
	 * Zahl von 0 bis 1
	 */
	private float vorkommensWkeit;
	
	/**
	 * Häufigkeit, wie viel mit der das Material vorkommt
	 * Zahl von 0 bis 1
	 */
	private float vorkommensHaeufigkeit;
	
	/**
	 * die Dichte des Materials
	 * Einheit: kg / m³
	 * relevant für den Transport
	 */
	private float dichte;
	
	/**
	 * Nahrungswert pro einheit von diesem Material
	 */
	private float naehrwert;
	
	/**
	 * Energie pro Einheit von diesem Material
	 */
	private float energie;
	
	/**
	 * Stabilität des Materials (im festen zustand)
	 * relevant für Material als Baustoff
	 * Richtwert: Granit hat eine stabilität von 1000
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
	 * @param spezifikationen array von floats: 1. dichte, 2. nährwert, 3. energie, 4. stabilität
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
			this.dichte = spezifikationen[0];
			this.naehrwert = spezifikationen[1];
			this.energie = spezifikationen[2];
			this.stabilitaet = spezifikationen[3];
		}
		else
		{
			this.dichte = 0;
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
	 * @return die Wahrscheinlichkeit, mit der dieses Material auf einem Objekt vorkommt
	 */
	public float getVorkommensWkeit() {
		return vorkommensWkeit;
	}

	/**
	 * @param vorkommensWkeit setzt die Wahrscheinlichkeit, mit der dieses Material auf einem Objekt vorkommt
	 */
	protected void setVorkommensWkeit(float vorkommensWkeit) {
		this.vorkommensWkeit = vorkommensWkeit;
	}

	/**
	 * @param Einstellung, wie für die Häufigkeit des Materials
	 * @return die vorkommensHaeufigkeit, mit der das Material vorkommt
	 */
	public float getVorkommensHaeufigkeit(StufenEnum einstellung) {
		float temp = 0;
		switch (einstellung)
		{
		case LOW:	//halbe häufigkeit
			temp = vorkommensHaeufigkeit / 2f;
			break;
		case HIGH:	//höhere Vorkommenshäufigkeit
			temp = vorkommensHaeufigkeit / 2f + 0.5f;
			break;
		default:	 //beinhaltet auch die MID einstellung, normale Häufigkeit
			temp = vorkommensHaeufigkeit;
			break;
		}
		return temp;
	}
	
	/**
	 * @return die vorkommensHaeufigkeit, mit der das Material vorkommt
	 */
	public float getVorkommensHaeufigkeit() {
		return vorkommensHaeufigkeit;
	}

	/**
	 * @param vorkommensHaeufigkeit setzt die vorkommensHaeufigkeit, mit der das Material vorkommt
	 */
	protected void setVorkommensHaeufigkeit(float vorkommensHaeufigkeit) {
		this.vorkommensHaeufigkeit = vorkommensHaeufigkeit;
	}

	/**
	 * relevant für den Transport von Ressourcen
	 * @return die Dichte des Materials in kg / m³
	 */
	public float getDichte() {
		return dichte;
	}

	/**
	 * Dichte: Einheit: kg / m³
	 * @param dichte setzt die dichte 
	 */
	protected void setDichte(float dichte) {
		this.dichte = dichte;
	}

	/**
	 * Hinweis: ob das Material für eine Bevölkerung essbar ist oder nicht, muss in der 
	 * Bevölkerung gespeichert werden
	 * @return den naehrwert pro Einheit von diesem Material
	 */
	public float getNaehrwert() {
		return naehrwert;
	}
	
	/**
	 * 
	 * @param naehrwert setzt den Naehrwert des Materials
	 */
	protected void setNaehrwert(float naehrwert) {
		this.naehrwert = naehrwert;
	}

	/**
	 * @return die energie pro Einheit des Materials
	 */
	public float getEnergie() {
		return energie;
	}
	
	/**
	 * 
	 * @param energie setzt die Energie pro einheit des Materials
	 */
	protected void setEnergie(float energie) {
		this.energie = energie;
	}

	/**
	 * relevant für Material als Baumaterial
	 * als Richtwert: Granit hat stabilität von 1000
	 * @return die stabilitaet (in festem Zustand)
	 */
	public float getStabilitaet() {
		return stabilitaet;
	}
	
	/**
	 * 
	 * @param stabilität setzt die Stabilität des Materials
	 */
	protected void setStabilitaet(float stabilitaet) {
		this.stabilitaet = stabilitaet;
	}

	/**
	 * Einheit: °C
	 * @return den schmelzpunkt
	 */
	public int getSchmelzpunkt() {
		return schmelzpunkt;
	}
	
	/**
	 * 
	 * @param schmelzpunkt setzt den Schmelzpunkt des Materials in °C
	 */
	protected void setSchmelzpunkt(int schmelzpunkt) {
		this.schmelzpunkt = schmelzpunkt;
	}

	/**
	 * Einheit : °C
	 * @return den siedepunkt
	 */
	public int getSiedepunkt() {
		return siedepunkt;
	}
	
	/**
	 * 
	 * @param siedepunkt setzt den Siedepunkt des Materials in °C
	 */
	protected void setSiedepunkt(int siedepunkt) {
		this.siedepunkt = siedepunkt;
	}

	/**
	 * Der aktuelle Forschungsfortschritt an diesem Material
	 * je weiter die Forschung, desto mehr eigenschaften des Materials werden freigeschaltet
	 * @return die forschung
	 */
	public int getForschung() {
		return forschung;
	}
	
	/**
	 * Fügt der Forschung den gegebenen Wert hinzu 
	 * @param fortschritt : zusätzliche Forschung, die hinzugefügt werden
	 */
	public void addForschung(int fortschritt) {
		this.forschung += fortschritt;
	}
}
