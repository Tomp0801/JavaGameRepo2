package himmelskoerper;

import java.util.Vector;

/**
 * Eine abstrakte Klasse für Objekte, die sich im Orbit um ein anderes Objekt befinden
 * In dieser Klasse werden für den Orbit wichtige Parameter definiert
 * außerdem ist die Methode bewegen definiert
 * 
 * Um das Objekt nicht in einen Orbit zu packen, BezugsObjekt gleich null setzen und 
 * orbitRadius gleich 0
 *
 * TODO Rotation?
 *
 * @author Thomas
 * @version 2.0
 * @param <BezugsKoerperKlasse>	Körper, um den das Objekt sich kreist
 */
public abstract class InOrbit extends Himmelskoerper {
	/**
	 * entfernung des Objekts zu seinem BezugsObjekt
	 * in km
	 */
	private double orbitRadius;
	
	/**
	 * Körper, um den das Objekt kreist
	 */
	private Orbitable bezugsKoerper; 
	
	/**
	 * Bewegungsvektor, Bezugspunkt ist das Objekt, 90° zu Positionsvektor
	 * Einheit: km (pro s)
	 */
	private Vector<Float> bewegungsVektor;
	
	/**
	 * Geschwindigkeit, mit der sich der Planet bewegt
	 * in km/s Länge des bewegungsVektors
	 */
	private float bewegungsGeschwindigkeit;

	/**
	 * Standardkonstruktor für inOrbit Objekt
	 * 
	 * @param bezugsKörper der Körper, um den das Objekt kreist
	 * @param distanz die Distanz, mit der das Objekt den Körper umkreist
	 * @param masse Masse des Objekts
	 */
	public InOrbit(Orbitable bezugsKoerper, double distanz, double masse, String art) {
		//TODO Radius
		super(masse, 0, art);
		this.setBezugsKoerper(bezugsKoerper);
		this.setOrbitRadius(distanz);
		
		if (bezugsKoerper != null) {
			/*
			 * Bewegungsgeschwindigkeit v:
			 * v = sqrt(masseBezugsStern * Gravitationskonstante / orbitRadius)
			 * ergibt sich aus der Gleichsetzung von Zentrifugal- und Gravitationskraft
			 * Einheit: km pro s
			 */
			float v = (float) Math.sqrt(((Himmelskoerper) bezugsKoerper).getMasse() * Constants.G / getOrbitRadius());
			//TODO für 3D abändern
			int random = (int)Math.round(Math.random()) * 2 - 1;	//zufallszahl: +1 oder -1
			this.setBewegungsVektor(random * v, (float)0); //für 2D nur x-Teil des Vektors einstellen 
			
			//TODO an zufälliger Position um den Stern herum platzieren
			this.setPosition(orbitRadius, 0, 0);
			
			//dem Bezugskörper hinzufügen
			bezugsKoerper.add(this);
		} else {
			this.setBewegungsVektor((float)0, (float)0);
		}
	
		setLastRefresh(System.currentTimeMillis());	//last Refresh initialisieren
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

	/**
	 * bewegt das Objekt zur richtigen aktuellen Position
	 */
	public void bewegen() {
		long passedTime;
		long prevRefresh = getLastRefresh();
		setLastRefresh(System.currentTimeMillis());
		double angleX, angleY;
		double wegX, wegY;
		Vector<Double> position = this.getPosition(); 
		
		passedTime = getLastRefresh() - prevRefresh;
		
		if (orbitRadius > 0 && passedTime > 0) { 		//nur wenn auch Zeit vergangen ist und ein orbitRadius existiert
			//Berechnung des zurückgelegten weges in x- und y-Richtung
			wegX = passedTime / 1000 * bewegungsVektor.get(0);
			wegY = passedTime / 1000 * bewegungsVektor.get(1);
			//Berechnung des Winkels in x-Richtung und y-Richtung
			//Prozentsatz von 2pi : weg / Gesamt orbit länge
			angleX = 2 * Math.PI * wegX / (orbitRadius * 2 * Math.PI);
			angleY = 2 * Math.PI * wegY / (orbitRadius * 2 * Math.PI);
			
			//Winkel auf aktuelle Position addieren
			this.setPosition(orbitRadius, position.get(1) + angleX, position.get(2) + angleY);
		}
	}
}
