package draufsicht.global;

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
	public static final double G = 6.67408 * Math.pow(10, -20);
	
	/**
	 * Sonnenmasse M = 1,989 * 10^30 kg
	 */
	public static final double M = 1.989 * Math.pow(10, 30);
	
	/**
	 * Radius unserer Sonne in km
	 */
	public static final double SONNENRADIUS = 695700;
	
	/**
	 * Astronomische Einheit: 149597870.7 km
	 * Entfernung der Erde zur Sonne
	 */
	public static final double AE = 149597870.7;
	
	/**
	 * Das Spielfeld wird um diesen Faktor von Realistischen großen verkleinert
	 */
	public static final double VERKLEINERUNGSFAKTOR = 1.0/100000.0;
	
	public static final double VERGROßERUNGSFAKTORRADIUSSONNE = 10000;
	
	public static final double VERGROßERUNGSFAKTORRADIUSPLANET = 1000;
	
	public static final double VERGROßERUNGSFAKTORRADIUSMOND = 10000;
	
//	public static final double SPEEDKAMERA = 50000;
	
	public static final float ZEITFAKTOR = 100;
	
}
