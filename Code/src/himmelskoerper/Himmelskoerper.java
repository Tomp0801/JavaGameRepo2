package himmelskoerper;

import java.util.Vector;

import global.Agregat;
import global.Constants;
import global.GameTime;
import global.Random;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point3D;
import javafx.scene.paint.PhongMaterial;

/**
 * Ein kugelförmiges Objekt mit Masse und einer Position im Raum;
 *  natürlichen Ursprungs oder auch nicht
 *  
 *  TODO zusammenhang zwischen art, masse, radius und material
 * 
 * @author Thomas
 * @version 1.0
 */
public abstract class Himmelskoerper 
{
	/**
	 * Pseudo-Random Number Generator für dieses Objekt
	 */
	private Random prng;
	
	/**
	 * Masse des Objekts in Kg 
	 */
	private double masse;
	
	/**
	 * Radius des Kugelförmigen Objekts
	 */
	private double radius;
	
	/**
	 * OberflächenTemperatur des Objekts in Celsius
	 */
	private float oberflaechenTemperatur;
	
	/**
	 * Position der aktuellen Sektion (Sonnensystem) in Polarkoordinaten
	 * (radius, winkelVonX0 = [0, 2pi) (=azimutWinkel), winkelVonZ0 = [0, pi]) (=PolarWinkel)
	 */
	private Vector<Double> positionPolar;
	
	/**
	 * aktuelle Position, relativ zum Bezugskoerper
	 */
	private SimpleDoubleProperty[] position;
	
	/**
	 * aktuelle position relativ zum zentrum des Systems
	 */
	private SimpleDoubleProperty[] positionAbsolute;
	
	/**
	 * Art des Objekts : Gas oder Fest 
	 */
	private Agregat art;
	
	/**
	 * Zeitpunkt der Letzten Positions- und Zustandsberechnung
	 */
	private long lastRefresh;

	/**
	 * manueller Konstruktor
	 * 
 	 * @param masse sets Masse des Objekts
 	 * @param radius sets radius der Kugel
 	 * @param art sets art des Objekts (fest oder gasförmig)
	 */
	public Himmelskoerper(double masse, double radius, Agregat art) {
		this.masse = masse;
		this.radius = radius;
		this.art = art;
		//prng mit zufälliger Zahl initialisieren
		prng = new Random((int) Math.round(Math.random() * 2147483647));
		
		position = new SimpleDoubleProperty[3];
		positionAbsolute = new SimpleDoubleProperty[3];
		for (int i = 0; i < 3; i++)
		{
			position[i] = new SimpleDoubleProperty(0);
			positionAbsolute[i] = new SimpleDoubleProperty(0);
		}			
		setPosition(0, 0, 0); 	//Position initialisieren mit 0
		this.lastRefresh = GameTime.getInstance().timeMillis();		//lastRefresh initialisieren
	}
	
	/**
	 * Konstruktor zur zufälligen generierung
	 * @param seed der Seed nach dem generiert wird
	 */
	public Himmelskoerper(int seed) {
		prng = new Random(seed);	//PRNG erstellen
		
		position = new SimpleDoubleProperty[3];
		positionAbsolute = new SimpleDoubleProperty[3];
		for (int i = 0; i < 3; i++)
		{
			position[i] = new SimpleDoubleProperty(0);
			positionAbsolute[i] = new SimpleDoubleProperty(0);
		}	
		
//		setPosition(0, 0, 0); 	//Position initialisieren mit 0
		this.lastRefresh = GameTime.getInstance().timeMillis();		//lastRefresh initialisieren
		
	}
	
	/**
	 * @return die masse der Kugel
	 */
	public double getMasse() {
		return masse;
	}
	
	/**
	 * @param masse setzt die Masse der Kugel
	 */
	protected void setMasse(double masse) {
		this.masse = masse;
	}

	/**
	 * @return den Radius der Kugel, die der Körper des Objekts ist
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @param radius setzt den Radius der Kugel (Körper des Objekts)
	 */
	protected void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * @return die Temperatur die an der Objekt oberfläche herrscht
	 */
	public float getOberflaechenTemperatur() {
		return oberflaechenTemperatur;
	}

	/**
	 * @param setzt die oberflaechenTemperatur
	 */
	protected void setOberflaechenTemperatur(float oberflaechenTemperatur) {
		this.oberflaechenTemperatur = oberflaechenTemperatur;
	}

	/**
	 * @return die als Vektor in Polar Koordinaten (radius, azimutWinkel, PolarWinkel)
	 */
	public Vector<Double> getPositionPolar() {
		return positionPolar;
	}
	
	/**
	 * @return the position Property (kartesisch)
	 */
	public DoubleProperty[] getPositionProperty() {
		return position;
	}

	/**
	 * @return the positionAbsolute Property (kartesisch)
	 */
	public DoubleProperty[] getPositionAbsoluteProperty() {
		return positionAbsolute;
	}
	
	/**
	 * @return die kartesische Position relativ zum bezugskörper
	 */
	public Point3D getPositionKartesisch()
	{
		return new Point3D(position[0].get(), position[1].get(), position[2].get());
	}
	
	/**
	 * @return die absolute Position(zum System mittelpunkt) in kartesischen Koordinaten
	 */
	public Point3D getPositionAbsolute()
	{
		return new Point3D(positionAbsolute[0].get(), positionAbsolute[1].get(), positionAbsolute[2].get());
	}
	
	/**
	 * setzt die absolute position
	 * @param position
	 */
	protected void setPositionAbsolute(Point3D position)
	{
		positionAbsolute[0].set(position.getX());
		positionAbsolute[1].set(position.getY());
		positionAbsolute[2].set(position.getZ());
	}

	/**
	 * rechnet die polar position in kartesischen Koordinaten (x, y, z) um
	 */
	protected void calcPositionKartesisch()
	{
		double x, y, z;
		
		x = positionPolar.get(0) * Math.sin(positionPolar.get(2)) * Math.cos(positionPolar.get(1));
		y = positionPolar.get(0) * Math.sin(positionPolar.get(2)) * Math.sin(positionPolar.get(1));
		z = positionPolar.get(0) * Math.cos(positionPolar.get(2));
		
		position[0].set(x*Constants.VERKLEINERUNGSFAKTOR);
		position[1].set(y*Constants.VERKLEINERUNGSFAKTOR);
		position[2].set(z*Constants.VERKLEINERUNGSFAKTOR);
	}
	
	/**
	 * die absolute Position (relativ zu dem Mittelpunkt des kompletten systems)
	 * @return Vektor mit kartesischen Koordinaten
	 */
	protected abstract void calcPositionAbsolute();

	/**
	 * Position setzen in Polar Koordinaten
	 * @param r Länge des Positionsvektors
	 * @param angleFromX Winkel, Azimutwinkel, der von der x achse aus bis zu 2*pi geht
	 * @param angleFromZ Winkel, Polarwinkel, der von der z-Achse aus bis zu pi geht
	 */
	public void setPosition(double r, double angleFromX, double angleFromZ) {
		Vector<Double> positionsVektor = new Vector<Double>(3);
		
		//beide Winkel in den Bereich von 0 bis 2*pi bringen, falls sie größer oder kleiner sind
		while (angleFromX >= 2 * Math.PI) {
			angleFromX = angleFromX - 2 * Math.PI;
		}
		while (angleFromX < 0) {
			angleFromX = angleFromX + 2 * Math.PI;
		}
		while (angleFromZ > Math.PI) {
			angleFromZ = angleFromZ - 2 * Math.PI;
		}
		while (angleFromZ < 0) {
			angleFromZ = angleFromZ + 2 * Math.PI;
		}
		
		positionsVektor.add(r);
		positionsVektor.add(angleFromX);
		positionsVektor.add(angleFromZ);
		
		this.positionPolar = positionsVektor;
		calcPositionKartesisch();
		calcPositionAbsolute();
	}
	
	/**
	 * Eine Methode zum zufälligen generieren der Eigenschaften des Himmelskörpers
	 * @param seed der Seed, nach dem generiert werden soll
	 */
	protected abstract void generate();
	
	/**
	 * 
	 * @return individueller Psuedo-Random-Number-Generator dieses Objekts
	 */
	protected Random getPRNG() {
		return prng;
	}
	
	/**
	 * @return Agregatzustand des Körpers (Fest, [Flüssig], Gas)
	 */
	public Agregat getArt() {
		return art;
	}
	
	/**
	 * 
	 * @param art setzt de Agregatzustand 
	 */
	protected void setArt(Agregat art) {
		this.art = art;
	}

	/**
	 * Methode zum aktualisieren des zustands des Himmelskörpers
	 */
	public abstract void refresh();
	
	/**
	 * @return das letzte mal, wo der Körper refreshed wurde
	 */
	protected long getLastRefresh() {
		return lastRefresh;
	}

	/**
	 * @param lastRefresh the lastRefresh to set
	 */
	protected void setLastRefresh(long lastRefresh) {
		this.lastRefresh = lastRefresh;
	}
	
	
	/**
	 * beschreibt das aussehen des Himmelskoerpers 
	 * @return gibt ein PhongMaterial zurueck mit dem Aussehen
	 */
	public abstract PhongMaterial getAussehn();
	

	/**********************************************************************************/
	/**
	 * Methode für Testzwecke
	 */
	public void printStatus() {
		Vector<Double> pos = getPositionPolar();
		System.out.println(" _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
		System.out.println("| Seed: " + getPRNG().getSeed());
		System.out.println("| Art: " + this.art);
		System.out.println("| Position: " + pos.get(0) + " " + pos.get(1) + " " + pos.get(2));
		System.out.println("| Absolute Position (kart): " + getPositionAbsolute().getX() + " " + getPositionAbsolute().getY() + " " + getPositionAbsolute().getZ());
		System.out.println("| Radius: " + this.radius);
		System.out.println("| Masse: "+ this.masse);
		System.out.println("| Temperatur: "+ this.oberflaechenTemperatur);
		System.out.println("| Tick: " + getLastRefresh()/1000);
		System.out.println(" _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
	}
}
