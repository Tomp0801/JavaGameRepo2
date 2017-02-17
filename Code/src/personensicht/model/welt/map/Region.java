package personensicht.model.welt.map;

import java.util.ArrayList;

import personensicht.model.gameObjekte.GameObjekt;

/**
 * Bescheibt eine Region. 
 * Befindet sich der Spieler auf diese Region, dann wird diese vom RegionCrt verwaltet und von der Klasse
 * RegionV dargestellt. 
 * 
 * 
 * @author Dennis
 *
 */
public class Region 
{
	/**
	 * Name des Ortes
	 */
	private String nameDerRegion;
	
	/**
	 * Minimale groﬂe der Region in Pixel. 
	 */
	public static final int SIZEMIN = 400;
	
	/**
	 * Maximale groﬂe der Region in Pixel. 
	 */
	public static final int SIZEMAX = 10000;

	/**
	 * Inhalt der Region. 
	 * Das Array beinhaltet alle Elemente die sich auf dieser Region befinden.
	 */
	private ArrayList<GameObjekt> objektListe = new ArrayList<GameObjekt>();
	
	/**
	 * Eine Liste mit Integer[] Arrays von der Groeﬂe 2.
	 * Der Inhalt dieser Integer steht fuer die Position des jeweiligen Objekts in der ObjektListe.
	 */
	private ArrayList<Integer[]> positionDerObjekteInDerListe = new ArrayList<Integer[]>();
	
	/**
	 * verweist auf die Orte, die von diesem Ort aus zu erreichen ist
	 */
	private ArrayList<Region> nachbarsOrte = new ArrayList<Region>();  // veraltet
	
	/**
	 * Beschreibung des Ortes
	 */
	private String beschreibung = "Dies ist ein schˆner Ort, den wir befinden uns in Baven"; //veraltet
	
	/**
	 * eine Liste von GameObjekten die sich auf diesem Spielfeld befinden
	 * 
	 */
	private ArrayList<GameObjekt> objekte = new ArrayList<GameObjekt>(); //veraltet
	
	/**
	 *  ein zwei Diminsionales Array mit der groeﬂe der Region. 
	 */
	private int size[] = {SIZEMIN, SIZEMIN};

	
	public Region(String nameDesOrtes, ArrayList<GameObjekt> objektListe, ArrayList<Integer[]> positionDerObjekteInDerListe) 		
	{
		this.nameDerRegion = nameDesOrtes; 
		this.objektListe = objektListe;
		this.positionDerObjekteInDerListe = positionDerObjekteInDerListe; 
	}
	
	
	public Region(String nameDesOrtes) 			// veraltet
	{
		this.nameDerRegion = nameDesOrtes; 
	}
	
//	public Region(String nameDesOrtes, ArrayList<Region> nachbarOrte)	veraltet
//	{
//		this(nameDesOrtes);
//		this.nameVomOrt = nameDesOrtes; 
//	}

	public Region(String nameDesOrtes, ArrayList<GameObjekt> felder)
	{	
		this.nameDerRegion = nameDesOrtes;
	
	}
	
	
	public void addObjekt(GameObjekt objekt) // veraltet
	{
		this.objekte.add(objekt);
	}
	
	public Region(String nameDesOrtes, Region nachbarOrte) // veraltet
	{
		this.nameDerRegion = nameDesOrtes; 
		this.nachbarsOrte.add(nachbarOrte);
	}

		
	
	public synchronized ArrayList<Region> getNachbarsOrte() {
		return nachbarsOrte;
	}

	public synchronized void setNachbarsOrte(ArrayList<Region> nachbarsOrte) {
		this.nachbarsOrte = nachbarsOrte;
	}

	public synchronized String getName() {
		return nameDerRegion;
	}

	public synchronized void setName(String name) {
		this.nameDerRegion = name;
	}

	public synchronized ArrayList<GameObjekt> getObjekte() {
		return objekte;
	}

	public synchronized void setObjekte(ArrayList<GameObjekt> objekte) {
		this.objekte = objekte;
	}

	public synchronized String getBeschreibung() {
		return beschreibung;
	}

	public synchronized void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public void addNachbarn(Region ort) 
	{
		this.nachbarsOrte.add(ort);	
	}


	public static synchronized int getSizemin() {
		return SIZEMIN;
	}


	public static synchronized int getSizemax() {
		return SIZEMAX;
	}


	public synchronized ArrayList<GameObjekt> getObjektListe() {
		return objektListe;
	}


	public synchronized int[] getSize() {
		return size;
	}


	public synchronized ArrayList<Integer[]> getPositionDerObjekteInDerListe() {
		return positionDerObjekteInDerListe;
	}


	public synchronized void setPositionDerObjekteInDerListe(ArrayList<Integer[]> positionDerObjekteInDerListe) {
		this.positionDerObjekteInDerListe = positionDerObjekteInDerListe;
	}
}
