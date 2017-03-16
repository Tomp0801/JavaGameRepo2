package ressource.model;

/**
 * Interface für alle Materialien, die zum Bau von Gebäuden, Raumschiffen o.Ä. 
 * verwendet werden können
 * 
 * TODO arbeiten mit resistenzen? Hitzeresistenz, Strahlungsresistenz...
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
