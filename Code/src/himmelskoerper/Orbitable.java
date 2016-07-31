package himmelskoerper;

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
	 * F�gt ein Objekt in den Orbit um dieses Objekt ein
	 * @param objectInOrbit das Objekt, das in den Orbit soll
	 */
	public void add(InOrbit objectInOrbit);		//TODO wie kann ich ne referenz auf dieses Objekt machen?
	
	/**
	 * berechnet den Gesamt Radius, den ein K�rper mit seinen InOrbits einnimmt
	 * @return radius den Radius von dem Gesamtsystem (z.B. Sonnensystem)
	 */
	public double getSystemRadius();
	
}
