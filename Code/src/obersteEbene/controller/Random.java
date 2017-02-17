package obersteEbene.controller;

/**
 * Ein PRNG, der pseudo-zufällige Nummern wiedergibt, abhängig von einem Seed (int)
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
	
	public Random(int seed) {
		this.seed = seed;
		currentSeed = seed;
		//die ersten paar randoms weglassen, da eventuell vorhersehbar
		random();
		random();
		random();
	}
	
	/**
	 * erstellt die nächste zufällige Zahl
	 * @return Zufallszahl double zwischen 0 und 1
	 */
	public double random() {
		currentSeed = (currentSeed * 16807) % 2147483647;
	    return Math.abs((double)currentSeed / 0x7FFFFFFF + 0.000000000233);
	}
	
	/**
	 * Erstellt die nächste zufallszahl und gibt sie als int in einem bestimmten Zahlenbereich wieder
	 * @param from Anfang des Intervalls
	 * @param to Ende des Intervalls
	 * @return zufällige Zahl aus dem festgelegten Intervall
	 */
	public int random(int from, int to) {
		return (from + (int) Math.round((random() * (to - from)))); 
	}
	
	/**
	 * Erstellt die nächste zufallszahl und gibt sie als double in einem bestimmten Zahlenbereich wieder
	 * @param from Anfang des Intervalls
	 * @param to Ende des Intervalls
	 * @return zufällige Zahl aus dem festgelegten Intervall
	 */
	public double random(double from, double to) {
		return (from + random() * (to - from)); 
	}
	
	public int randomInt() {
		return random(0, 2147483647);
	}
	
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
