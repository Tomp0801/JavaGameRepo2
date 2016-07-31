package himmelskoerper;

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
	public GasPlanet(Stern bezugsKoerper, double distanz, double masse) {
		super(bezugsKoerper, distanz, masse, Constants.GAS);
		
	}

}
