package himmelskoerper;

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
	 * Fügt ein Objekt in den Orbit um dieses Objekt ein
	 * @param objectInOrbit das Objekt, das in den Orbit soll
	 */
	public void add(InOrbit objectInOrbit);		//TODO wie kann ich ne referenz auf dieses Objekt machen?
	
	/**
	 * berechnet den Gesamt Radius, den ein Körper mit seinen InOrbits einnimmt
	 * @return radius den Radius von dem Gesamtsystem (z.B. Sonnensystem)
	 */
	public double getSystemRadius();
	
}
