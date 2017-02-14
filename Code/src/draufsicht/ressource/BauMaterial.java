package draufsicht.ressource;

/**
 * Interface für alle Materialien, die zumm Bau von Gebäuden, Raumschiffen o.Ä. 
 * verwendet werden können
 * 
 * @author Thomas
 *
 */
public interface BauMaterial {
	/**
	 * gibt die Härte/Widerstandsfähigkeit des Materials an
	 * TODO Einheit
	 * @return die härte des Materials
	 */
	public float getHaerte();
	
	/**
	 * gibt Schmelzpunkt des Materials an. Also die maximale Hitze, die es verträgt 
	 * @return der Schmelzpunkt des Materials
	 */
	public float getSchmelzpunkt();
}
