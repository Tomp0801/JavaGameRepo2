package draufsicht.ressource;

/**
 * Interface f�r alle Materialien, die zumm Bau von Geb�uden, Raumschiffen o.�. 
 * verwendet werden k�nnen
 * 
 * @author Thomas
 *
 */
public interface BauMaterial {
	/**
	 * gibt die H�rte/Widerstandsf�higkeit des Materials an
	 * TODO Einheit
	 * @return die h�rte des Materials
	 */
	public float getHaerte();
	
	/**
	 * gibt Schmelzpunkt des Materials an. Also die maximale Hitze, die es vertr�gt 
	 * @return der Schmelzpunkt des Materials
	 */
	public float getSchmelzpunkt();
}
