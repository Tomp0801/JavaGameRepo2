package himmelskoerper;

import global.Constants;

/**
 * Ein Planet aus Gas, nicht betretbar
 * 
 * @author Thomas
 *
 */
public class GasPlanet extends Planet {

	/**
	 * Konstruktor
	 * 
	 * @param bezugsKoerper
	 * @param distanz
	 * @param masse
	 */
	public GasPlanet(Stern bezugsKoerper, double distanz, double masse, double radius) {
		super(bezugsKoerper, distanz, masse, radius, Constants.GAS);
		
	}
	
	public GasPlanet(Stern bezugsKoerper, int seed) {
		super(bezugsKoerper, seed);
	}

	@Override
	protected void generate() {
		// TODO Auto-generated method stub
		
	}

}
