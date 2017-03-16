package draufsicht.himmelskoerper;

import java.util.LinkedList;

/**
 * Interface für SpaceObjects, um die andere Objekte im Orbit sein können
 * 
 * @author Thomas
 * @version 0.0
 */
public interface Orbitable {
	/**
	 * @return masse die masse dieses Objektes
	 */
	public double getMasse();
	
	/**
	 * 
	 * @return den radius des Körpers
	 */
	public double getRadius();
	
	/**
	 * 
	 * @return die Oberflächen Temperatur des Körpers
	 */
	public float getOberflaechenTemperatur();
	
	/**
	 * Fügt ein Objekt in den Orbit um dieses Objekt ein
	 * @param objectInOrbit das Objekt, das in den Orbit soll
	 */
	public void add(InOrbit objectInOrbit);
	
	/**
	 * berechnet den Gesamt Radius, den ein Körper mit seinen InOrbits einnimmt
	 * @return radius den Radius von dem Gesamtsystem (z.B. Sonnensystem)
	 */
	public double getSystemRadius();
	
	/**
	 * generiert zufällig die Objekte, die um dieses kreisen
	 */
	public void generateChildren();
	
	/**
	 * @return liste der Objekte im orbit
	 */
	public LinkedList<InOrbit> getChildren();
	
	/**
	 * 
	 * @param index des Körpers den man haben möchte
	 * @return den Körper in Orbit mit dem angegebnen Index
	 */
	public InOrbit getChild(int index);
}
