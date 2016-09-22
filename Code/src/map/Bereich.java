package map;

import java.util.HashMap;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Ein Bereich von Feldern, auf denen z.B. eine Stadt errichtet werden kann
 * 
 * @author Thomas
 *
 */
public class Bereich {
	/**
	 * breite des Bereichs
	 * TODO richtige Hoehe und Breite festlegen
	 */
	public final int BREITE = 10;
	
	/**
	 * Hoehe des Bereichs 
	 */
	public final int HOEHE = 10;
	
	/**
	 * Die Karte zu der dieser Bereich gehört
	 */
	private Karte parentKarte;
	
	/**
	 * Felder, die bebaut werden können
	 */
	private Feld[][] felder;
	
	/**
	 * speicher für eine abgewandelte vorkommenswahrscheinlichkeit der versch. bodenschaetze
	 */
	private HashMap<BodenMaterial, Float> bodenschaetze;
	
	/**
	 * speichert abgeandelte vorkommenswahrscheinlichkeit für die versch. bodenarten
	 */
	private HashMap<BodenMaterial, Float> bodenarten;
	
	Bereich(Karte parentKarte) {
		this.parentKarte = parentKarte;
		felder = new Feld[BREITE][HOEHE];
		
		generateVariationen();	//bodenschaetze und bodenarten variieren
		
		initFelder();	//Felder generieren
	}
	
	/**
	 * variiert die vorkommen von bodenarten und bodenschaetzen für diesen Bereich
	 */
	private void generateVariationen() 
	{
		
		float wahrscheinlichkeit;
		bodenschaetze = new HashMap<BodenMaterial, Float>();
		bodenarten = new HashMap<BodenMaterial, Float>();
		
		//bodenschaetze
		for (int i = 0; i < parentKarte.getBodenschaetze().size(); i++) 
		{	
			wahrscheinlichkeit = parentKarte.getBodenschaetze().get(i).getVorkommensWkeit() * (float)parentKarte.getVarierteRandom();
			this.bodenschaetze.put(parentKarte.getBodenschaetze().get(i), wahrscheinlichkeit);
		}
		//bodenarten
		for (int i = 0; i < parentKarte.getBodenarten().size(); i++) 
		{	
			wahrscheinlichkeit = parentKarte.getBodenarten().get(i).getVorkommensWkeit() * (float)(parentKarte.getVarierteRandom());
			this.bodenarten.put(parentKarte.getBodenarten().get(i), wahrscheinlichkeit);
		}
	}
	
	/**
	 * initialisiert die felder
	 */
	private void initFelder() 
	{
		//Felder initialisieren/erstellen
		for (int y = 0; y < HOEHE; y++) 
		{
			for (int x = 0; x < BREITE; x++) 
			{
				felder[x][y] = new Feld(this);
			}
		}
	}
	
	/**
	 * Gibt das Feld an einer bestimmten Stelle des Bereichs wieder
	 * 
	 * @param x x-Koordinate
	 * @param y y-Koordinate
	 * @return das Feld an der Stelle der angegebenen Koordinaten
	 */
	public Feld getFeld(int x, int y) {
		if (x >= 0 && x < BREITE && y >= 0 && y < HOEHE) {	//nur wenn die Koordinaten gültig sind
			return felder[x][y];
		} else {
			return null;
		}
	}

	/**
	 * @return the parentKarte
	 */
	public Karte getParentKarte() {
		return parentKarte;
	}

	
	/**
	 * 
	 * @return gibt ein Canvas zurueck mit einem zum Bereich passenden Hintergrund zurueck
	 */
	public Canvas getAussehen()
	{
		Canvas oberflaeche = new Canvas();
		GraphicsContext grafik = oberflaeche.getGraphicsContext2D();
		
		oberflaeche.prefHeight(this.HOEHE * 20);
		oberflaeche.prefWidth(this.BREITE * 20);
		
		for (int x = 0; x < BREITE; x++) 
		{
			for (int y = 0; y < HOEHE; y++)
			{
				grafik.setFill(felder[x][y].getBodentyp().getColor());
				grafik.fillRect(0, 0, 20, 20);
			}
		}
		
		//TODO Bodenschätze, Gebäude??
				
		return oberflaeche; 
	}
	
	/**
	 * @return the bodenschaetze
	 */
	public HashMap<BodenMaterial, Float> getBodenschaetze() {
		return bodenschaetze;
	}

	/**
	 * @return the bodenarten
	 */
	public HashMap<BodenMaterial, Float> getBodenarten() {
		return bodenarten;
	}
}
