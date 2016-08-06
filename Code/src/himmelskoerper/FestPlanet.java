package himmelskoerper;

import global.Agregat;
import map.Karte;

/**
 * Ein Planet aus Festen Materialien
 * Betretbar, theoretisch bewohn- und bebaubar
 * 
 * @author Thomas
 *
 */
public class FestPlanet extends Planet implements Betretbar {
	/**
	 * Karte, die betreten werden kann
	 */
	private Karte karte;
	
	/**
	 * Konstruktor
	 * 
	 * @param bezugsKoerper
	 * @param distanz
	 * @param masse
	 */
	public FestPlanet(Stern bezugsKoerper, double distanz, double masse, double radius) {
		super(bezugsKoerper, distanz, masse, radius, Agregat.FEST);
		
		int breite;
		//Größe der Karte aus dem Radius ermitteln
		//breite = Umfang;
		breite = (int) Math.round(2 * Math.PI * this.getRadius());
		
		//TODO Karte erstellen mit (Typ) und Bodenschaetzen
		//karte = new Karte(breite, hoehe);
	}
	
	public FestPlanet(Stern bezugsKoerper, int seed) {
		super(bezugsKoerper, seed);
	}

	@Override
	public Karte getKarte() {
		return karte;
	}

	@Override
	protected void generate() {
		/*
		 * Mittlere Materiedichte: p = 3 m/ (4* pi* r^3)
		 * p >= 3 g/cm^3 für FestPlaneten
		 * =>  r^3 = 3*masse / (16 * 10^12 * pi)
		 * 
		 * Masse :
		 * Merkur: 3,30 * 10^23
		 * Erde : 5,97 * 10^24
		 */
		
		double maxMasse = 5.97 * Math.pow(10, 24) * 4;	//vierfache Masse der Erde	
		double minMasse = 3.3 * Math.pow(10, 23) * 0.75;	//3/4 Masse von Merkur
		
		//Masse Zufällig
		double masse = getPRNG().random(minMasse, maxMasse);
		//Radius abhängig von der Masse
		double radius = 3 * masse / (16 * Math.pow(10, 12) * Math.PI);
		radius = Math.pow(radius, 1.0/3.0);	//3. Wurzel ziehen
		
		//Distanz zum bezugsKoerper zufällig
		//TODO nicht zufällig?
		//distanz zwischen 10 und 5000 fachem des bezugsKörper-radius
		double distanz = getPRNG().random(getBezugsKoerper().getRadius() * 10 , getBezugsKoerper().getRadius() * 5000);
		
		double temperatur = calcTemperatur(this.getBezugsKoerper(), distanz);
		
		
		setMasse(masse);
		setRadius(radius);
		setOrbitRadius(distanz);
		setOberflaechenTemperatur((float)temperatur);
		setArt(Agregat.FEST);		
	}

	@Override
	public void generateChildren() {
		int numSterne = getPRNG().random((int)0, (int)5);
		
		//Monde generieren
		for (int i = 0; i <= numSterne; i++) {
			//Monde werden mit Zufalls Konstruktor erstellt
			add(new Mond(this, getPRNG().randomInt()));
		}
	}
}
