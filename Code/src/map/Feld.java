package map;

import java.util.HashMap;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import map.objekte.Platzierbar;

/**
 * Ein Feld, das Objekte enthalten kann, eine Bodenart und abbaubare Materialien in der Erde hat
 * 
 * @author Thomas
 *
 */
public class Feld {
	/**
	 * Bereich, zu dem dieses Feld geh�rt
	 */
	private Bereich parentBereich;
	
	/**
	 * Auflistung der Rohstoffe und deren Menge, die das Feld enth�lt
	 */
	private HashMap<BodenMaterial, Float> bodenschatzVorkommen;
	
	/**
	 * Material aus dem der Boden hier ist
	 */
	private BodenMaterial bodentyp;
	
	/**
	 * Platz, an dem ein Geb�ude o.�. errichtet werden kann
	 * Kann auch durch nat�rliche Objekte eingenommen werden
	 */
	private Platzierbar bauplatz;

	/**
	 * Zufalls Konstruktor
	 * 
	 * @param bereich Bereich, zu dem das Feld geh�rt
	 * @param bodenschaetze M�gliche bodenschatze
	 * @param bodentypen M�gliche Bodenarten
	 */
	Feld(Bereich bereich) {
		this.parentBereich = bereich;
		
		bodenschatzVorkommen = new HashMap<BodenMaterial, Float>();
		
		//rohstoffe generieren
		//Alle Bodenschaetze, die dem Feld in der ArrayList �bergeben wurden
		double menge;
		for (BodenMaterial temp : parentBereich.getBodenschaetze().keySet()) {
			//Vorkommenswahrscheinlichkeit und variet�t mit einbringen
			menge = parentBereich.getParentKarte().getVarierteRandom() * temp.getVorkommensWkeit();
			if (menge > 0.005) {	//wenn unter bestimmten schwellenwert, rauslassen.
				setRohstoff(temp, (float)menge); //1 entspricht 100%
			}
		}
		
		//bodenart generieren; sich f�r einen Boden entscheiden
		//f�r jede Art eine zahl aus zufall und vorkommensWkeit generieren
		//gr��te Zahl wird ausgew�hlt
		BodenMaterial chosen = null;
		double largest = 0;
		double random;
		for (BodenMaterial temp : parentBereich.getBodenarten().keySet()) {
			random = temp.getVorkommensWkeit() * parentBereich.getParentKarte().getVarierteRandom(); 
			if (random > largest) {
				random = largest;
				chosen = temp;
			}
		}
		bodentyp = chosen;
	}
	
	/**
	 * Stellt die Menge des angegebenen Rohstoffs f�r das Feld ein
	 * Wenn ein vorheriger Wert f�r diesen Rohstoff existiert, wird er �berschrieben
	 * 
	 * @param rohstoff Art des Rohstoffs
	 * @param menge Menge des Rostoffs
	 */
	public void setRohstoff(BodenMaterial rohstoff, Float menge) {
		bodenschatzVorkommen.put(rohstoff, menge);
	}

	public float mineRohstoff(BodenMaterial rohstoff, float requestMenge)
	{
		float mineMenge = 0;
		if (bodenschatzVorkommen.containsKey(rohstoff))
		{
			float aktuelleMenge = bodenschatzVorkommen.get(rohstoff);
			if (aktuelleMenge <= requestMenge)	//Vorkommen wird aufgebraucht, da nicht mehr so viel vorhanden 
			{
				mineMenge = aktuelleMenge;
				bodenschatzVorkommen.remove(rohstoff);	//vorkommen entfernen
			}
			else	//genug menge vorhanden, kann abgegeben werden
			{
				mineMenge = requestMenge;
				bodenschatzVorkommen.put(rohstoff, aktuelleMenge - requestMenge);	//geminte menge von bestand abziehen
			}
		}
		
		return mineMenge;
	}

	/**
	 * @return the rohstoffe
	 */
	public HashMap<BodenMaterial, Float> getRohstoffe() {
		return bodenschatzVorkommen;
	}
	
	/**
	 * @return the bereich
	 */
	public Bereich getParentBereich() {
		return parentBereich;
	}

	/**
	 * 
	 * @return the bodenschatzVorkommen
	 */
	public HashMap<BodenMaterial, Float> getBodenschatzVorkommen() {
		return bodenschatzVorkommen;
	}

	/**
	 * @return the bodentyp
	 */
	public BodenMaterial getBodentyp() {
		return bodentyp;
	}

	/**
	 * @return the bauplatz
	 */
	public Platzierbar getBauplatz() {
		return bauplatz;
	}

	/**
	 * @param bauplatz the bauplatz to set
	 * TODO bedingungen f�r den Bau eines Objekts
	 */
	public void setBauplatz(Platzierbar bauplatz) {
		this.bauplatz = bauplatz;
	}
	
	/**
	 * gibt an, ob ein Objekt auf dem Feld ist oder nicht
	 * @return true, wenn Feld frei ist, false, wenn besetzt durch Objekt
	 */
	public boolean isFree()
	{
		return (bauplatz == null);
	}
	
	/**
	 * 
	 * @return gibt ein Canvas mit einem zum Feld passendem Aussehen zurueck
	 */
	public Canvas getAussehen()
	{
		Canvas oberflaeche = new Canvas();
		GraphicsContext grafik = oberflaeche.getGraphicsContext2D();
		
		//TODO
		//geb�ude, pflanzen, rohstoffe markieren
		
		grafik.setFill(this.bodentyp.getColor());
		grafik.fillRect(0, 0, 400, 400);
				
		return new Canvas(); 
	}
}
