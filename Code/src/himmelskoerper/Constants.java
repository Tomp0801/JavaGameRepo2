package himmelskoerper;

/**
 * Klasse mit physikalischen Konstanten, die für verschiedene Berechnungen benötigt werden 
 * 
 * @author Thomas
 * @version 1.0
 */
public final class Constants {
	/**
	 * Gravitationskonstante G = 6.67408 * 10^(-11) m^3 / (kg * s^2)
	 * Einheit: km^3 / (kg * s^2)
	 */
	static final double G = 6.67408 * Math.pow(10, -20);
	
	/**
	 * attribut für gasförmige Himmelskörper
	 */
	static final String GAS = "gas";
	/**
	 * attribut für feste himmelskörper
	 */
	static final String FEST = "fest";
}
