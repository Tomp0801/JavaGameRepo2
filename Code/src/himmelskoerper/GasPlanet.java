package himmelskoerper;

import global.Agregat;

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
		super(bezugsKoerper, distanz, masse, radius, Agregat.GAS);
		
	}
	
	public GasPlanet(Stern bezugsKoerper, int seed) {
		super(bezugsKoerper, seed);
	}

	@Override
	protected void generate() {
		/*
		 * Mittlere Materiedichte: p = 3 m/ (4* pi* r^3)
		 * p <= 2 g/cm^3 für GasPlaneten
		 * 
		 * Jupiter Radius: 69.911 km, Masse: 1,898 × 10^27 kg (317,8 M)
		 */
		// TODO Auto-generated method stub
		
	}

}
