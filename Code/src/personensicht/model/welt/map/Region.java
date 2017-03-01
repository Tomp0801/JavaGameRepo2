package personensicht.model.welt.map;

import java.io.Serializable;
import java.util.ArrayList;

import personensicht.model.gameObjekte.GameObjekt;
import personensicht.view.welt.map.RegionV;

/**
 * Bescheibt eine Region. 
 * Befindet sich der Spieler auf diese Region, dann wird diese vom RegionCrt verwaltet und von der Klasse
 * RegionV dargestellt. 
 * 
 * 
 * @author Dennis
 *
 */
public class Region implements Serializable
{

	private String nameDerRegion;
	
//	/**
//	 * Inhalt der Region. 
//	 * Das Array beinhaltet alle Elemente die sich auf dieser Region befinden.
//	 */
//	private ArrayList<GameObjekt> objektListe = new ArrayList<GameObjekt>(); 
	
	
//	/**
//	 * verweist auf die Orte, die von diesem Ort aus zu erreichen ist
//	 */
//	private ArrayList<Region> nachbarsOrte = new ArrayList<Region>();  // veraltet
//	
//	/**
//	 * Beschreibung des Ortes
//	 */
//	private String beschreibung = "Dies ist ein schöner Ort, den wir befinden uns in Baven"; //veraltet
//	
	/**
	 * eine Liste von GameObjekten die sich auf dieser Region befinden
	 */
	private ArrayList<GameObjekt> children = new ArrayList<GameObjekt>(); 
	
	/**
	 * Breite der Region
	 */
	private double width = RegionV.SIZEMIN;
	/**
	 * Hohe der Region
	 */
	private double height = RegionV.SIZEMIN;
	
	public Region(){
	}
	
	public Region(String nameDesOrtes, ArrayList<GameObjekt> children) 		{
		this.nameDerRegion = nameDesOrtes; 
		this.children = children;
	}
	
	
	public Region(String nameDesOrtes) 			
	{
		this.nameDerRegion = nameDesOrtes; 
	}
	
	
//	public void addObjekt(GameObjekt objekt) // veraltet
//	{
//		this.objekte.add(objekt);
//	}
//	
//	public Region(String nameDesOrtes, Region nachbarOrte) // veraltet
//	{
//		this.nameDerRegion = nameDesOrtes; 
//		this.nachbarsOrte.add(nachbarOrte);
//	}

		
	
//	public synchronized ArrayList<Region> getNachbarsOrte() {
//		return nachbarsOrte;
//	}
//
//	public synchronized void setNachbarsOrte(ArrayList<Region> nachbarsOrte) {
//		this.nachbarsOrte = nachbarsOrte;
//	}

	public synchronized String getName() {
		return nameDerRegion;
	}

	public synchronized void setName(String name) {
		this.nameDerRegion = name;
	}

//	public synchronized ArrayList<GameObjekt> getObjekte() {
//		return objekte;
//	}
//
//	public synchronized void setObjekte(ArrayList<GameObjekt> objekte) {
//		this.objekte = objekte;
//	}
//
//	public synchronized String getBeschreibung() {
//		return beschreibung;
//	}
//
//	public synchronized void setBeschreibung(String beschreibung) {
//		this.beschreibung = beschreibung;
//	}
//
//	public void addNachbarn(Region ort) 
//	{
//		this.nachbarsOrte.add(ort);	
//	}

//	public synchronized ArrayList<GameObjekt> getObjektListe() {
//		return objektListe;
//	}


	public synchronized ArrayList<GameObjekt> getChildren() {
		return children;
	}


	public synchronized double getWidth() {
		return width;
	}


	public synchronized double getHeight() {
		return height;
	}


	public synchronized void setWidth(double width) {
		this.width = width;
	}


	public synchronized void setHeight(double height) {
		this.height = height;
	}
}
