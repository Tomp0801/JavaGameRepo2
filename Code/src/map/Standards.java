package map;

import java.util.ArrayList;

/**
 * stellt standard Objekte zur verf�gung
 * TODO sollte vielleicht aus einer Datei gelesen werden; �ber Daten verwaltung
 * 
 * @author Thomas
 *
 */
public class Standards {
	/**
	 * Liste der Standard Bodenschaetze
	 */
	private ArrayList<BodenMaterial> bodenschaetze;
	
	/**
	 * Liste der Standard Gel�ndearten
	 */
	private ArrayList<GelaendeArt> gelaendeArten; 
	
	/**
	 * Konstruktor
	 */
	public Standards() {
		//Bodenschatze hinzuf�gen
		setBodenschaetze();
		setGelaendeArten();
	}
	
	/**
	 * stellt die Bodenschaetze ein
	 */
	private void setBodenschaetze() {
		bodenschaetze = new ArrayList<BodenMaterial>();
		bodenschaetze.add(new BodenMaterial("Gold", "Spitzhacke", (float)0.05));
		bodenschaetze.add(new BodenMaterial("Silber", "Spitzhacke", (float)0.08));
		bodenschaetze.add(new BodenMaterial("Kupfer", "Spitzhacke", (float)0.1));
		bodenschaetze.add(new BodenMaterial("Kohle", "Spitzhacke", (float)0.4));
		bodenschaetze.add(new BodenMaterial("Diamant", "Spitzhacke", (float)0.01));
		bodenschaetze.add(new BodenMaterial("Eisen", "Spitzhacke", (float)0.2));
	}
	
	private void setGelaendeArten() {
		gelaendeArten = new ArrayList<GelaendeArt>();
		gelaendeArten.add(new GelaendeArt("W�ste", (float)1.2));
		gelaendeArten.add(new GelaendeArt("Fels", (float)2));
		gelaendeArten.add(new GelaendeArt("Wiese", (float)0.7));
	}
	
	/**
	 * getter f�r bodenschaetze
	 * @return bodenschaetze
	 */
	public ArrayList<BodenMaterial> getBodenschaetze() {
		return bodenschaetze;
	}
	
	public ArrayList<GelaendeArt> getGelaendeArten() {
		return gelaendeArten;
	}
}
