package himmelskoerper;



import java.util.Vector;

import global.Agregat;
import global.Constants;
import global.GameTime;

/**
 * Eine abstrakte Klasse f�r Objekte, die sich im Orbit um ein anderes Objekt befinden
 * In dieser Klasse werden f�r den Orbit wichtige Parameter definiert
 * au�erdem ist die Methode bewegen definiert
 * 
 * Um das Objekt nicht in einen Orbit zu packen, BezugsObjekt gleich null setzen und 
 * orbitRadius gleich 0
 *
 * TODO Rotation?
 *
 * @author Thomas
 * @version 2.0
 */
public abstract class InOrbit extends Himmelskoerper {
	/**
	 * entfernung des Objekts zu seinem BezugsObjekt
	 * in km
	 */
	private double orbitRadius;
	
	/**
	 * K�rper, um den das Objekt kreist
	 */
	private Orbitable bezugsKoerper; 
	
	/**
	 * Bewegungsvektor, Bezugspunkt ist das Objekt, 90� zu Positionsvektor
	 * Einheit: km (pro s)
	 */
	private Vector<Float> bewegungsVektor;
	
	/**
	 * Geschwindigkeit, mit der sich der Planet bewegt
	 * in km/s L�nge des bewegungsVektors
	 */
	private float bewegungsGeschwindigkeit;

	/**
	 * Standardkonstruktor f�r inOrbit Objekt
	 * 
	 * @param bezugsK�rper der K�rper, um den das Objekt kreist
	 * @param distanz die Distanz, mit der das Objekt den K�rper umkreist
	 * @param masse Masse des Objekts
	 */
	public InOrbit(Orbitable bezugsKoerper, double distanz, double masse, double radius, Agregat art) {
		super(masse, radius, art);
		this.setBezugsKoerper(bezugsKoerper);
		this.setOrbitRadius(distanz);
		
		if (bezugsKoerper != null) {

			calcBewegungsVektor();
			
			//an zuf�lliger Position um den Stern herum platzieren
			setRandomPosition();
			
			//dem Bezugsk�rper hinzuf�gen
			bezugsKoerper.add(this);
		} else {
			this.setBewegungsVektor((float)0, (float)0);
		}
	
		setLastRefresh(GameTime.getInstance().timeMillis());	//last Refresh initialisieren
	}
	
	/**
	 * Zufalls Konstruktor
	 * @param bezugsKoerper
	 * @param seed
	 */
	public InOrbit(Orbitable bezugsKoerper, int seed) {
		super(seed);
		
		this.bezugsKoerper = bezugsKoerper;
		bezugsKoerper.add(this);	//zu bezugsKoerper hinzufuegen

		generate();
		
		//bewegungsVektor setzen
		calcBewegungsVektor();
		//an zuf�llige Stell im Orbit platzieren
		setRandomPosition();
	}
	
	/**
	 * berechnet den Bewegungsvektor-l�nge anhand der Gravitation und entfernung zum bezugsObjekt
	 * Richtung wird zuf�llig generiert 
	 */
	private void calcBewegungsVektor() {
		/*
		 * Bewegungsgeschwindigkeit v:
		 * v = sqrt(masseBezugsStern * Gravitationskonstante / orbitRadius)
		 * ergibt sich aus der Gleichsetzung von Zentrifugal- und Gravitationskraft
		 * Einheit: km pro s
		 */
		float v = (float) Math.sqrt(bezugsKoerper.getMasse() * Constants.G / this.getOrbitRadius());
		double random = getPRNG().random(0, 2 * Math.PI);	//zufalls winkel, um den der Vektor gedreht wird
		//polar koordinaten (v | random) in kartesische umrechnen
		this.setBewegungsVektor((float)(v * Math.cos(random)), (float)(v * Math.sin(random)));
	}
	
	/**
	 * setzt eine zuf�llige Position auf der Umlaufbahn
	 */
	private void setRandomPosition() {
		//zuf�llige Position auf dem Orbit erstellen
		setPosition(this.getOrbitRadius(), getPRNG().random(0, 2 * Math.PI), getPRNG().random(0, Math.PI));
	}
	
	/**
	 * @return the orbitRadius
	 */
	public double getOrbitRadius() {
		return orbitRadius;
	}

	/**
	 * @param orbitRadius the orbitRadius to set
	 */
	protected void setOrbitRadius(double orbitRadius) {
		this.orbitRadius = orbitRadius;
	}

	/**
	 * @return the bezugsKoerper
	 */
	public Orbitable getBezugsKoerper() {
		return bezugsKoerper;
	}

	/**
	 * @param bezugsKoerper the bezugsKoerper to set
	 */
	protected void setBezugsKoerper(Orbitable bezugsKoerper) {
		this.bezugsKoerper = bezugsKoerper;
	}

	/**
	 * @return the bewegungsVektor
	 */
	public Vector<Float> getBewegungsVektor() {
		return bewegungsVektor;
	}

	/**
	 * @param bewegungsVektor the bewegungsVektor to set
	 */
	protected void setBewegungsVektor(float bewegungX, float bewegungY) {
		Vector<Float> bewegungsVektor = new Vector<Float>(2);
		
		bewegungsVektor.add(bewegungX);
		bewegungsVektor.add(bewegungY);
		
		this.bewegungsVektor = bewegungsVektor;
	}

	/**
	 * @return the bewegungsGeschwindigkeit
	 */
	public float getBewegungsGeschwindigkeit() {
		return bewegungsGeschwindigkeit;
	}
	
	@Override
	public void refresh()
	{
		bewegen();
	}
	
	@Override
	public void calcPositionAbsolute()
	{
		calcPositionKartesisch();
		Himmelskoerper bezugsObjekt = (Himmelskoerper) this.getBezugsKoerper();	
		this.setPositionAbsolute(this.getPositionKartesisch().add(bezugsObjekt.getPositionAbsolute()));
	}

	/**
	 * bewegt das Objekt zur richtigen aktuellen Position
	 */
	public void bewegen() {
		long passedTime;
		long prevRefresh = getLastRefresh();
		setLastRefresh(GameTime.getInstance().timeMillis());
		double angleX, angleY;
		double wegX, wegY;
		Vector<Double> position = this.getPositionPolar(); 
		
		passedTime = getLastRefresh() - prevRefresh;
		
		if (orbitRadius > 0 && passedTime > 0) { 		//nur wenn auch Zeit vergangen ist und ein orbitRadius existiert
			//Berechnung des zur�ckgelegten weges in x- und y-Richtung
			wegX = passedTime / 1000 * bewegungsVektor.get(0);
			wegY = passedTime / 1000 * bewegungsVektor.get(1);
			//Berechnung des Winkels in x-Richtung und y-Richtung
			//Prozentsatz von 2pi : weg / Gesamt orbit l�nge
			angleX = 2 * Math.PI * wegX / (orbitRadius * 2 * Math.PI);
			angleY = 2 * Math.PI * wegY / (orbitRadius * 2 * Math.PI);
			
			//Winkel auf aktuelle Position addieren
			this.setPosition(orbitRadius, position.get(1) + angleX, position.get(2) + angleY);
		}
	}
	
	/**
	 * Berechnet Temperatur f�r dieses Objekt, die durch Bestrahlung des Bezugsk�rpers entsteht
	 * eigene W�rme entwicklung oder andere Quellen werden nicht mit einberechnet
	 * 
	 * @param bezugsKoerper K�rper um dieser kreist
	 * @param distanz von diesem K�rper zum BezugsK�rper
	 * @return die errechnete Oberfl�chen Temperatur dieses K�rpers 
	 */
	protected double calcTemperatur(Orbitable bezugsKoerper, double distanz) {
		//http://www.semibyte.de/wp/physics/atomphysics-qm/beispiel-zum-stefan-boltzmann-strahlungsgesetz-temperatur-erde/
		
		double T = Math.pow(bezugsKoerper.getRadius(), 2) * Math.pow(bezugsKoerper.getOberflaechenTemperatur(), 4);
		T = T / Math.pow(distanz, 2);
		T = Math.pow(T, 1.0/4.0);
		
		return T;
	}
}
