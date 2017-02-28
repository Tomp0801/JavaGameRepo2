package obersteEbene.controller;

/**
 * Ein PRNG, der pseudo-zuf�llige Nummern wiedergibt, abh�ngig von einem Seed (int)
 * Zahlen sind doubles zwischen 0 und 1
 * 
 * Nach dem Park-Miller-Carta-PRNG, von dem Typen hier implementiert:
 * http://stackoverflow.com/questions/750145/park-miller-carta-prng-random-generator-allways-returns-2-33e-10
 * 
 * @author Thomas
 *
 */
public class Random {
	private int currentSeed;
	private int seed;
	
	/**
	 * Erstellt einen pseudo-random number generator mit dem gegebenen Seed
	 * ein PRNG mit diesem Seed gibt immer die gleichen nummern
	 * @param seed Int wert, der als Seed des prng verwendet wird
	 */
	public Random(int seed) {
		this.seed = seed;
		currentSeed = seed;
		//die ersten paar randoms weglassen, da eventuell vorhersehbar
		random();
		random();
		random();
	}
	
	/**
	 * Erstellt einen pseudo-random number generator mit dem gegebenen Seed
	 * ein PRNG mit diesem Seed gibt immer die gleichen nummern
	 * @param seed String Wert, wird in int-Wert umgewandelt und als Seed verwendet
	 */
	public Random(String seed) {
		this.seed = seed.hashCode();
		currentSeed = this.seed;
		//die ersten paar randoms weglassen, da eventuell vorhersehbar
		random();
		random();
		random();
	}
	
	/**
	 * erstellt die n�chste zuf�llige Zahl
	 * @return Zufallszahl double zwischen 0 und 1
	 */
	public double random() {
		currentSeed = (currentSeed * 16807) % 2147483647;
	    return Math.abs((double)currentSeed / 0x7FFFFFFF + 0.000000000233);
	}
	
	/**
	 * Erstellt die n�chste zufallszahl und gibt sie als int in einem bestimmten Zahlenbereich wieder
	 * @param from Anfang des Intervalls
	 * @param to Ende des Intervalls
	 * @return zuf�llige Zahl aus dem festgelegten Intervall
	 */
	public int random(int from, int to) {
		return (from + (int) Math.round((random() * (to - from)))); 
	}
	
	/**
	 * Erstellt die n�chste zufallszahl und gibt sie als double in einem bestimmten Zahlenbereich wieder
	 * @param from Anfang des Intervalls
	 * @param to Ende des Intervalls
	 * @return zuf�llige Zahl aus dem festgelegten Intervall
	 */
	public double random(double from, double to) {
		return (from + random() * (to - from)); 
	}
	
	/**
	 * Erstellt die n�chste zufallszahl und gibt sie als float in einem bestimmten Zahlenbereich wieder
	 * @param from Anfang des Intervalls
	 * @param to Ende des Intervalls
	 * @return zuf�llige Zahl aus dem festgelegten Intervall
	 */
	public float random(float from, float to) {
		return (from + (float)random() * (to - from)); 
	}
	
	/**
	 * Gibt einen zuf�lligen Integer Wert zur�ck
	 * @return int-Wert zwischen -2^31 und 2^31-1
	 */
	public int randomInt() {
		return random(Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	/**
	 * Gibt einen zuf�lligen positiven Integer Wert zur�ck
	 * @return int Wert zwischen 0 und 2^31-1
	 */
	public int randomPositiveInt() {
		return random(0, Integer.MAX_VALUE);
	}
	
	/**
	 * Gibt einen zuf�lligen bool-Wert zur�ck
	 * @return true oder false
	 */
	public boolean randomBoolean() {
		int random = random(0, 1);
		if (random==1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Gibt den benutzten seed der PRNG wieder
	 * @return den Seed, der zum initialisieren benutzt wurde
	 */
	public int getSeed() {
		return seed;
	}
}
