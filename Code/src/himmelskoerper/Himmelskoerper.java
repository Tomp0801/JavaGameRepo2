package himmelskoerper;

import java.util.Vector;

/**
 * Ein kugelförmiges Objekt mit Masse und einer Position im Raum;
 *  natürlichen Ursprungs oder auch nicht
 *  
 *  TODO vielleicht umbenennen
 *  TODO zusammenhang zwischen art, masse, radius und material
 * 
 * @author Thomas
 * @version 1.0
 */
public class Himmelskoerper 
{
	/**
	 * Masse des Objekts in Kg 
	 * TODO Masse aus dem Radius des Objekts ermitteln
	 */
	private double masse;
	
	/**
	 * Radius des Kugelförmigen Objekts
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
 	 * @param art sets art des Objekts (fest oder gasförmig)
	 */
	public Himmelskoerper(double masse, float radius, String art) {
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
	 * Methode für Testzwecke
	 */
	public void printStatus() {
		Vector<Double> pos = getPosition();
		System.out.print("Tick: " + getLastRefresh()/1000 + ": ");
		System.out.println(pos.get(0) + " " + pos.get(1) + " " + pos.get(2));
	}
	
}
