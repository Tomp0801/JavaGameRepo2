package global;

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
	
	public double random() {
		currentSeed = (currentSeed * 16807) % 2147483647;
	    return Math.abs((double)currentSeed / 0x7FFFFFFF + 0.000000000233);
	}
	
	public int getSeed() {
		return seed;
	}
}
