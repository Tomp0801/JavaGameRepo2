package ressource;

import java.awt.Color;
import java.io.Serializable;
import java.util.HashMap;

import global.Agregat;

/**
 * Grundklasse f�r alle Stoffe Materialen, etc.
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
	 * Bezeichnung f�r das Material
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
	 * das Gewicht des Materials
	 * Einheit: kg / m�
	 * relevant f�r den Transport
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
	 * Stabilit�t des Materials (im festen zustand)
	 * relevant f�r Material als Baustoff
	 * Richtwert: Granit hat eine stabilit�t von 1000
	 */
	private float stabilitaet;
	
	/**
	 * Einheit: �C
	 */
	private int schmelzpunkt;
	
	/**
	 * Einheit: �C
	 */
	private int siedepunkt;
	
	/**
	 * Temperatur, bei der das Material verbrennt
	 * TODO n�tig? im Prozess mit in behalten?
	 * Einheit: �C 
	 */
	//private int verbrennpunkt;
	
	/**
	 * Forschungsfortschritt an diesem Material.
	 * Je mehr Forschung, desto mehr eigenschaften werden freigeschaltet
	 * TODO f�r verschiedene Spieler??
	 */
	private int forschung;
	
	/**
	 * HashMap mit Prozessen und ihren Ergebnissen, die an diesem Material durchgef�hrt 
	 * werden k�nnen
	 */
	private HashMap<Prozess, Material> verarbeitung;
	
	/**
	 * Kosntruktor mit javafx Color, da diese die opacity mit enth�lt
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
	 * vollst�ndiger Konstruktor
	 * 
	 * @param name Name des Materials
	 * @param color Farbe des Materials
	 * @param spezifikationen array von floats: 1. gewicht, 2. n�hrwert, 3. energie, 4. stabilit�t
	 * @param agregatpunkt array von ints: 1. schmelzpunkt, 2. siedepunkt
	 * @param verarbeitung Liste mit m�glichen Prozessen und den Materialien, zu denen sie f�hren, wenn angewandt
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
	 * relevant f�r den Transport von Ressourcen
	 * Einheit : kg / m�
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

	/**
	 * Hinweis: ob das Material f�r eine Bev�lkerung essbar ist oder nicht, muss in der 
	 * Bev�lkerung gespeichert werden
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
	 * relevant f�r Material als Baumaterial
	 * als Richtwert: Granit hat stabilit�t von 1000
	 * @return die stabilitaet (in festem Zustand)
	 */
	public float getStabilitaet() {
		return stabilitaet;
	}
	
	/**
	 * 
	 * @param stabilit�t setzt die Stabilit�t des Materials
	 */
	protected void setStabilitaet(float stabilitaet) {
		this.stabilitaet = stabilitaet;
	}

	/**
	 * Einheit: �C
	 * @return den schmelzpunkt
	 */
	public int getSchmelzpunkt() {
		return schmelzpunkt;
	}
	
	/**
	 * 
	 * @param schmelzpunkt setzt den Schmelzpunkt des Materials in �C
	 */
	protected void setSchmelzpunkt(int schmelzpunkt) {
		this.schmelzpunkt = schmelzpunkt;
	}

	/**
	 * Einheit : �C
	 * @return den siedepunkt
	 */
	public int getSiedepunkt() {
		return siedepunkt;
	}
	
	/**
	 * 
	 * @param siedepunkt setzt den Siedepunkt des Materials in �C
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
	 * F�gt der Forschung den gegebenen Wert hinzu 
	 * @param fortschritt : zus�tzliche Forschung, die hinzugef�gt werden
	 */
	public void addForschung(int fortschritt) {
		this.forschung += fortschritt;
	}
	
	//**************************************************************************************************************************//
												//STANDARD MATERIALIEN
	
	//************************************* METALLE ***********************************************//
	
	private static HashMap<Prozess, Material> verarbeitungMetalle = new HashMap<Prozess, Material>(); 
	static {
		//TODO m�gliche Prozesse ausf�llen
	}
	
	/**
	 * Das Standard Material Gold
	 * TODO Spezifikationen
	 */
	public final static Material GOLD = new Material("Gold", javafx.scene.paint.Color.GOLD, new float[]{(float) 19302, (float) 0, (float) 0, (float) 1500}, new int[]{1064, 2700}, verarbeitungMetalle);
	
	/**
	 * Das Standard Material Silber
	 */
	public final static Material SILBER = new Material("Silber", javafx.scene.paint.Color.SILVER, new float[]{(float) 10490, (float) 0, (float) 0, (float) 1600}, new int[]{962, 2162}, verarbeitungMetalle);
	
	/**
	 * Das Standard Material Eisen
	 */
	public final static Material EISEN = new Material("Eisen", javafx.scene.paint.Color.LIGHTGRAY, new float[]{(float) 7900, (float) 0, (float) 0, (float) 3000}, new int[]{1538, 2862}, verarbeitungMetalle);
	
	/**
	 * Das Standard Material Kupfer
	 */
	public final static Material KUPFER = new Material("Kupfer", javafx.scene.paint.Color.SANDYBROWN, new float[]{(float) 8960, (float) 0, (float) 0, (float) 2000}, new int[]{1085, 2562}, verarbeitungMetalle);

	//*********************************** Bodenmaterialien **************************************//
	
	/**
	 * Das Standard Material Granit
	 */
	public final static Material GRANIT = new Material("Granit", javafx.scene.paint.Color.DARKGRAY, new float[]{(float) 2800, (float) 0, (float) 0, (float) 1000}, new int[]{1450, Integer.MAX_VALUE}, verarbeitungMetalle);
	
	//******************************************* Fl�ssigkeiten ********************************************//
	
	/**
	 * Das Standard Material Wasser
	 */
	public final static Material WASSER = new Material("Wasser", javafx.scene.paint.Color.SKYBLUE, new float[]{(float) 1000, (float) 0, (float) 0, (float) 150}, new int[]{0, 100}, verarbeitungMetalle);


}
