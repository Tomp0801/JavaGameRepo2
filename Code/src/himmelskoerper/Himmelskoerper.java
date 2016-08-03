package himmelskoerper;

import java.util.Vector;


import global.GameTime;
import global.Random;

/**
 * Ein kugelförmiges Objekt mit Masse und einer Position im Raum;
 *  natürlichen Ursprungs oder auch nicht
 *  
 *  TODO zusammenhang zwischen art, masse, radius und material
 * 
 * @author Thomas
 * @version 1.0
 */
public abstract class Himmelskoerper 
{
	/**
	 * der Seed des Himmelskörpers, nach dem er generiert wurde
	 */
	private int seed;
	
	/**
	 * Pseudo-Random Number Generator für dieses Objekt
	 */
	private Random prng;
	
	/**
	 * Masse des Objekts in Kg 
	 */
	private double masse;
	
	/**
	 * Radius des Kugelförmigen Objekts
	 */
	private double radius;
	
	/**
	 * OberflächenTemperatur des Objekts in Celsius
	 */
	private float oberflaechenTemperatur;
	
	/**
	 * Position der aktuellen Sektion (Sonnensystem) in Polarkoordinaten
	 */
	private Vector<Double> position;
	
	/**
	 * Art des Objekts : Gas oder Fest 
	 */
	private String art;
	
	/**
	 * Zeitpunkt der Letzten Positions- und Zustandsberechnung
	 * TODO soll Zeit nicht vom System sondern von einem Zeitsimulator holen
	 */
	private long lastRefresh;

	/**
	 * manueller Konstruktor
 	 * @param masse sets Masse des Objekts
 	 * @param radius sets radius der Kugel
 	 * @param art sets art des Objekts (fest oder gasförmig)
	 */
	public Himmelskoerper(double masse, double radius, String art) {
		this.masse = masse;
		this.radius = radius;
		this.art = art;
		this.seed = 0;	//kein seed wurde verwendet -> seed = 0 setzen
		
		setPosition(0, 0, 0); 	//Position initialisieren mit 0
		this.lastRefresh = GameTime.timeMillis();		//lastRefresh initialisieren
	}
	
	/**
	 * Konstruktor zur zufälligen generierung
	 * @param seed der Seed nach dem generiert wird
	 */
	public Himmelskoerper(int seed) {
		this.seed = seed;
		prng = new Random(seed);	//PRNG erstellen
		
		setPosition(0, 0, 0); 	//Position initialisieren mit 0
		this.lastRefresh = GameTime.timeMillis();		//lastRefresh initialisieren
	}
	
	/**
	 * @return the seed
	 */
	public int getSeed() {
		return seed;
	}
	
	/**
	 * @return die masse
	 */
	public double getMasse() {
		return masse;
	}
	
	/**
	 * @param masse the masse to set
	 */
	protected void setMasse(double masse) {
		this.masse = masse;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @param radius the radius to set
	 */
	protected void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * @return the oberflaechenTemperatur
	 */
	public float getOberflaechenTemperatur() {
		return oberflaechenTemperatur;
	}

	/**
	 * @param oberflaechenTemperatur the oberflaechenTemperatur to set
	 */
	protected void setOberflaechenTemperatur(float oberflaechenTemperatur) {
		this.oberflaechenTemperatur = oberflaechenTemperatur;
	}

	/**
	 * @return the position
	 */
	public Vector<Double> getPosition() {
		return position;
	}

	/**
	 * Position setzen
	 * @param r Länge des Positionsvektors
	 * @param angleXY Winkel, der in der XY-Ebene liegt
	 * @param angleYZ Winkel, der in der YZ-Ebene liegt
	 */
	public void setPosition(double r, double angleXY, double angleYZ) {
		Vector<Double> positionsVektor = new Vector<Double>(3);
		
		//beide Winkel in den Bereich von 0 bis 2*pi bringen, falls sie größer oder kleiner sind
		while (angleXY >= 2 * Math.PI) {
			angleXY = angleXY - 2 * Math.PI;
		}
		while (angleXY < 0) {
			angleXY = angleXY + 2 * Math.PI;
		}
		while (angleYZ >= 2 * Math.PI) {
			angleYZ = angleYZ - 2 * Math.PI;
		}
		while (angleYZ < 0) {
			angleYZ = angleYZ + 2 * Math.PI;
		}
		
		positionsVektor.add(r);
		positionsVektor.add(angleXY);
		positionsVektor.add(angleYZ);
		
		this.position = positionsVektor;
	}
	
	
	
	/**
	 * Eine Methode zum zufälligen generieren der Eigenschaften des Himmelskörpers
	 * @param seed der Seed, nach dem generiert werden soll
	 */
	protected abstract void generate();
	
	/**
	 * 
	 * @return eine ZufallsZahl vom Objekteigenen PRNG
	 */
	protected double getRandom() {
		return prng.random();
	}
	
	/**
	 * @return the art
	 */
	public String getArt() {
		return art;
	}
	
	/**
	 * 
	 * @param art sets the art
	 */
	protected void setArt(String art) {
		this.art = art;
	}

	/**
	 * @return the lastRefresh
	 */
	protected long getLastRefresh() {
		return lastRefresh;
	}

	/**
	 * @param lastRefresh the lastRefresh to set
	 */
	protected void setLastRefresh(long lastRefresh) {
		this.lastRefresh = lastRefresh;
	}

	/**********************************************************************************/
	/**
	 * Methode für Testzwecke
	 */
	public void printStatus() {
		Vector<Double> pos = getPosition();
		System.out.println(" _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
		System.out.println("| Tick: " + getLastRefresh()/1000 + ": ");
		System.out.println("| Position: " + pos.get(0) + " " + pos.get(1) + " " + pos.get(2));
		System.out.println("| Radius: " + this.radius);
		System.out.println("| Masse: "+ this.masse);
		System.out.println(" _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
	}
	
}
