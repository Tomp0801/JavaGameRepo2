package draufsicht.himmelskoerper;

import java.util.LinkedList;

/**
 * Interface f�r SpaceObjects, um die andere Objekte im Orbit sein k�nnen
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
	 * @return den radius des K�rpers
	 */
	public double getRadius();
	
	/**
	 * 
	 * @return die Oberfl�chen Temperatur des K�rpers
	 */
	public float getOberflaechenTemperatur();
	
	/**
	 * F�gt ein Objekt in den Orbit um dieses Objekt ein
	 * @param objectInOrbit das Objekt, das in den Orbit soll
	 */
	public void add(InOrbit objectInOrbit);
	
	/**
	 * berechnet den Gesamt Radius, den ein K�rper mit seinen InOrbits einnimmt
	 * @return radius den Radius von dem Gesamtsystem (z.B. Sonnensystem)
	 */
	public double getSystemRadius();
	
	/**
	 * generiert zuf�llig die Objekte, die um dieses kreisen
	 */
	public void generateChildren();
	
	/**
	 * @return liste der Objekte im orbit
	 */
	public LinkedList<InOrbit> getChildren();
	
	/**
	 * 
	 * @param index des K�rpers den man haben m�chte
	 * @return den K�rper in Orbit mit dem angegebnen Index
	 */
	public InOrbit getChild(int index);
}
