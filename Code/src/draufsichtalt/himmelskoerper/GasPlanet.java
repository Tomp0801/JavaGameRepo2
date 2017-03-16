package draufsicht.himmelskoerper;

import draufsicht.global.Agregat;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

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
		 * Masse :
		 * Uranus: 868 * 10^23
		 * Jupiter : 19000 * 10^23
		 */
		
		double maxMasse = 4 * 19000 * Math.pow(10, 23);		//4 fache Masse Jupiters
		double minMasse = 0.75 * 868 * Math.pow(10, 23);	//3/4 Masse von Uranus
		
		//Masse Zufällig
		double masse = getPRNG().random(minMasse, maxMasse);
		//Radius abhängig von der Masse
		double radius = 3 * masse / (6 * Math.pow(10, 12) * Math.PI);
		radius = Math.pow(radius, 1.0/3.0);	//3. Wurzel ziehen
		
		//Distanz zum bezugsKoerper zufällig
		//TODO nicht zufällig?
		//distanz zwischen 10 und 5000 fachem des bezugsKörper-radius
		double distanz = getPRNG().random(getBezugsKoerper().getRadius() * 10, getBezugsKoerper().getRadius() * 5000);		
		
		double temperatur = calcTemperatur(this.getBezugsKoerper(), distanz);
		
		
		setMasse(masse);
		setRadius(radius);
		setOrbitRadius(distanz);
		setOberflaechenTemperatur((float)temperatur);
		setArt(Agregat.GAS);		
		
	}

	@Override
	public void generateChildren() {
		int numSterne = getPRNG().random((int)0, (int)20);
		
		//Monde generieren
		for (int i = 0; i <= numSterne; i++) {
			//Monde werden mit Zufalls Konstruktor erstellt
			new Mond(this, getPRNG().randomInt());
		}
		
	}

	@Override
	public PhongMaterial getAussehn() {
		 PhongMaterial material = new PhongMaterial();
		    material.setDiffuseColor(Color.BLUE);
		    material.setSpecularColor(Color.ORANGE);
			return material;
	}

}
