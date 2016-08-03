package map;

import java.util.ArrayList;

/**
 * stellt standard Objekte zur verfügung
 * TODO sollte vielleicht aus einer Datei gelesen werden; über Daten verwaltung
 * 
 * @author Thomas
 *
 */
public class Standards {
	/**
	 * Liste der Standard Bodenschaetze
	 */
	private ArrayList<Bodenschatz> bodenschaetze;
	
	private ArrayList<GelaendeArt> gelaendeArten; 
	
	/**
	 * Konstruktor
	 */
	public Standards() {
		//Bodenschatze hinzufügen
		setBodenschaetze();
		setGelaendeArten();
	}
	
	/**
	 * stellt die Bodenschaetze ein
	 */
	private void setBodenschaetze() {
		bodenschaetze = new ArrayList<Bodenschatz>();
		bodenschaetze.add(new Bodenschatz("Gold", "Spitzhacke", (float)0.05));
		bodenschaetze.add(new Bodenschatz("Silber", "Spitzhacke", (float)0.08));
		bodenschaetze.add(new Bodenschatz("Kupfer", "Spitzhacke", (float)0.1));
		bodenschaetze.add(new Bodenschatz("Kohle", "Spitzhacke", (float)0.4));
		bodenschaetze.add(new Bodenschatz("Diamant", "Spitzhacke", (float)0.01));
		bodenschaetze.add(new Bodenschatz("Eisen", "Spitzhacke", (float)0.2));
	}
	
	private void setGelaendeArten() {
		gelaendeArten = new ArrayList<GelaendeArt>();
		gelaendeArten.add(new GelaendeArt("Wüste", (float)1.2));
		gelaendeArten.add(new GelaendeArt("Fels", (float)2));
		gelaendeArten.add(new GelaendeArt("Wiese", (float)0.7));
	}
	
	/**
	 * getter für bodenschaetze
	 * @return bodenschaetze
	 */
	public ArrayList<Bodenschatz> getBodenschaetze() {
		return bodenschaetze;
	}
	
	public ArrayList<GelaendeArt> getGelaendeArten() {
		return gelaendeArten;
	}
}
