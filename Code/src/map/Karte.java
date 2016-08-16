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
	 * Liste der Arten, aus denen der Boden bestehen kann
	 */
	private ArrayList<BodenMaterial> bodenarten;
	
	/**
	 * zahl zwischen 0 und 1, die darstellt, wie sehr verschiedene Gegenden auf dem Planeten
	 * variieren oder genau gleich sind
	 */
	private double varietaet;
	
	/**
	 * gibt an, ob die Karte schon initialisiert wurde oder noch nicht
	 */
	private boolean bereicheInit;
	
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
			this.bereicheInit = false;
			
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
			this.bereicheInit = false;
			
			prng = new Random(seed);
			
			generate();
		}
	}
	
	/**
	 * generiert automatisch eine Karte
	 * TODO optionen zum generieren überdenken
	 */
	private void generate() {
		
		//varietät festlegen
		this.varietaet = prng.random(0.0, 1.0);

		//mögliche Bodenschätze generieren
		bodenschaetze = new ArrayList<>();
		for (int i = 0; i < Deserializer.getBodenschaetze().size(); i++) {
			//entscheiden ob der Bodenschatz vorkommen soll oder nicht
			//TODO 50/50 chance beibehalten?
			if (prng.randomBoolean()) {
				bodenschaetze.add(Deserializer.getBodenschaetze().get(i));
			}
		}
		//TODO random generated zufalls bodenschätze hinzufügen

		//mögliche Boden Arten generieren
		bodenarten = new ArrayList<>();
		while (bodenarten.size() == 0) {	//es muss mindestens eine bodenart existieren
			for (int i = 0; i < Deserializer.getBodentypen().size(); i++) {
				//entscheiden ob der Bodenschatz vorkommen soll oder nicht
				//TODO 50/50 chance beibehalten?
				if (prng.randomBoolean()) {
					bodenarten.add(Deserializer.getBodentypen().get(i));
				}
			}
		}
		
		//TODO random generated zufalls bodentypen hinzufügen
	}
	
	/**
	 * initialisiert die Bereiche
	 */
	private void initBereiche() {
		bereiche = new Bereich[breite][hoehe];
		for (int x = 0; x < breite; x++) {
			for (int y = 0; y < hoehe; y++) {
				bereiche[x][y] = new Bereich(this);
			}
		}
		this.bereicheInit = true;
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
		if (!bereicheInit) {	//wenn bereiche noch nicht initialisiert, dies jetzt tun
			initBereiche();
		}
		if (x >= 0 && x < breite && y >= 0 && y < hoehe) {	//nur wenn die Koordinaten gültig sind
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

	/**
	 * @return the bodenarten
	 */
	public ArrayList<BodenMaterial> getBodenarten() {
		return bodenarten;
	}

	/**
	 * w-keit = vorkommensw-keit * (random * varietät + 1.0)
	 * random : zahl um 0
	 * obere/ untere grenze der zahl : +-varietät
	 * +1 : zahl um 1
	 * @return eine varierte zufallsZahl, dessen Zahlenbereich zw. 0 und 2 liegt und 
	 * durch die varietaet eingeschränkt ist
	 */
	public double getVarierteRandom() {
		return (prng.random(-1.0, 1.0) * varietaet + 1.0);
	}
}
