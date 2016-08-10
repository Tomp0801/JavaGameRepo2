package map;

import java.util.ArrayList;

import global.Random;
import himmelskoerper.Betretbar;
import speicherverwaltung.Deserializer;

/**
 * Eine 2D-Karte, zum Beispiel eines Planeten
 * Begehbar und Bebaubar
 * 
 * @author Thomas
 *
 */
public class Karte {
	/**
	 * Objekt, das diese Karte beinhaltet
	 */
	private Betretbar parentObject;
	
	/**
	 * 2D-Array der bereiche, die die Karte aufbauen
	 */
	private Bereich[][] bereiche;
	
	/**
	 * Breite der Karte
	 */
	private int breite;
	
	/**
	 * hoehe der Karte
	 */
	private int hoehe;
	
	/**
	 * Individueller Pseudo-Random Number Generator zum generieren der Karte
	 */
	private Random prng;
	
	/**
	 * Liste der Bodenschaetze, die auf dieser Karte vorkommen können
	 */
	private ArrayList<BodenMaterial> bodenschaetze;
	
	/**
	 * Manueller Konstruktor
	 * 
	 * @param parentObject das Objekt, auf dem diese Karte sich befindet
	 * @param breite
	 * @param hoehe
	 * @param bodenschaetze die Art der Bodenschätze, die auf der Karte vorkommen
	 */
	Karte(Betretbar parentObject, int breite, int hoehe, ArrayList<BodenMaterial> bodenschaetze) {
		if (breite > 0 && hoehe > 0) {
			this.parentObject = parentObject;
			this.breite = breite;
			this.hoehe = hoehe;
			this.bodenschaetze = bodenschaetze;
			
			bereiche = new Bereich[breite][hoehe];
		}
	}
	
	/**
	 * Zufalls Konstruktor, generiert zufällig (nach dem Seed) eine Karte
	 * 
 	 * @param parentObject das Objekt, auf dem diese Karte sich befindet
	 * @param breite der Karte
	 * @param hoehe der Karte
	 * @param seed zum Generieren der Karte
	 */
	public Karte(Betretbar parentObject, int breite, int hoehe, int seed) {
		if (breite > 0 && hoehe > 0) {
			this.parentObject = parentObject;
			this.breite = breite;
			this.hoehe = hoehe;
			
			bereiche = new Bereich[breite][hoehe];
			prng = new Random(seed);
			
			generate();
		}
	}
	
	/**
	 * generiert automatisch eine Karte
	 * TODO optionen zum generieren überdenken
	 */
	private void generate() {

		//mögliche Bodenschätze generieren
		bodenschaetze = new ArrayList<>();
		for (int i = 0; i < Deserializer.getBodenschaetze().size(); i++) {
			//entscheiden ob der Bodenschatz vorkommen soll oder nicht
			//TODO 50/50 chance beibehalten?
			if (prng.randomBoolean()) {
				bodenschaetze.add(Deserializer.getBodenschaetze().get(i));
			}
		}
		
		for (int x = 0; x < breite; x++) {
			for (int y = 0; y < hoehe; y++) {
				//bereiche generieren
				bereiche[x][y] = new Bereich(this);
			}
		}
	}

	/**
	 * @return the parentObject
	 */
	public Betretbar getParentObject() {
		return parentObject;
	}
	
	/**
	 * Gibt den Bereich an einer bestimmten Stelle der Karte wieder
	 * 
	 * @param x x-Koordinate
	 * @param y y-Koordinate
	 * @return der Bereich an der Stelle der angegebenen Koordinaten
	 */
	public Bereich getBereich(int x, int y) {
		if (x >= 0 && x < breite && y >= 0 && y < hoehe) {	//nur wenn die Koordinaten gültig sind
			//Wenn bereich noch nicht aufgerufen wurde, jetzt die Felder generieren
			if (!bereiche[x][y].isInit()) {
				bereiche[x][y].initFelder();
			}
			return bereiche[x][y];
		} else {
			return null;
		}
	}

	/**
	 * @return the breite
	 */
	public int getBreite() {
		return breite;
	}

	/**
	 * @return the hoehe
	 */
	public int getHoehe() {
		return hoehe;
	}

	/**
	 * @return the prng
	 */
	protected Random getPrng() {
		return prng;
	}

	/**
	 * @return the bodenschaetze
	 */
	public ArrayList<BodenMaterial> getBodenschaetze() {
		return bodenschaetze;
	}
}
