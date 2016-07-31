package himmelskoerper;

import java.util.Vector;

/**
 * Ein kugelf�rmiges Objekt mit Masse und einer Position im Raum;
 *  nat�rlichen Ursprungs oder auch nicht
 *  
 *  TODO vielleicht umbenennen
 *  TODO zusammenhang zwischen art, masse, radius und material
 * 
 * @author Thomas
 * @version 1.0
 */
public class SpaceObject 
{
	/**
	 * Masse des Objekts in Kg 
	 * TODO Masse aus dem Radius des Objekts ermitteln
	 */
	private double masse;
	
	/**
	 * Radius des Kugelf�rmigen Objekts
	 */
	private float radius;
	
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
	 */
	private long lastRefresh;

	/**
	 * Konstruktor
 	 * @param masse sets Masse des Objekts
 	 * @param radius sets radius der Kugel
 	 * @param art sets art des Objekts (fest oder gasf�rmig)
	 */
	public SpaceObject(double masse, float radius, String art) {
		this.masse = masse;
		this.radius = radius;
		this.art = art;
		setPosition(0, 0, 0); 	//Position initialisieren mit 0
	}
	
	/**
	 * @return die masse
	 */
	public double getMasse() {
		return masse;
	}

	/**
	 * @return the radius
	 */
	public float getRadius() {
		return radius;
	}

	/**
	 * @param radius the radius to set
	 */
	public void setRadius(float radius) {
		this.radius = radius;
	}

	/**
	 * @return the position
	 */
	public Vector<Double> getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(double r, double angleXY, double angleYZ) {
		Vector<Double> positionsVektor = new Vector<Double>(3);
		
		positionsVektor.add(r);
		positionsVektor.add(angleXY);
		positionsVektor.add(angleYZ);
		
		this.position = positionsVektor;
	}
	
	/**
	 * @return the art
	 */
	public String getArt() {
		return art;
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
	 * Methode f�r Testzwecke
	 */
	public void printStatus() {
		Vector<Double> pos = getPosition();
		System.out.print("Tick: " + getLastRefresh()/1000 + ": ");
		System.out.println(pos.get(0) + " " + pos.get(1) + " " + pos.get(2));
	}
	
}
