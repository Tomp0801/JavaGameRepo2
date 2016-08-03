package global;

/**
 * Klasse mit physikalischen Konstanten, die f�r verschiedene Berechnungen ben�tigt werden 
 * 
 * @author Thomas
 * @version 1.0
 */
public final class Constants {
	/**
	 * Gravitationskonstante G = 6.67408 * 10^(-11) m^3 / (kg * s^2)
	 * Einheit: km^3 / (kg * s^2)
	 */
	public static final double G = 6.67408 * Math.pow(10, -20);
	
	/**
	 * Sonnenmasse M = 1,989 * 10^30 kg
	 */
	public static final double M = 1.989 * Math.pow(10, 30);
	
	/**
	 * attribut f�r gasf�rmige Himmelsk�rper
	 */
	public static final String GAS = "gas";
	/**
	 * attribut f�r feste himmelsk�rper
	 */
	public static final String FEST = "fest";
}
