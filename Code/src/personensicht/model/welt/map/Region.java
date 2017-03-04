package personensicht.model.welt.map;

import java.util.ArrayList;

import ioHandler.SerializableNew;
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
public class Region implements SerializableNew
{

	private String nameDerRegion;
	
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
	
	public Region(String nameDesOrtes, ArrayList<GameObjekt> children) {
		this.nameDerRegion = nameDesOrtes; 
		this.children = children;
	}
	
	
	public Region(String nameDesOrtes) 			
	{
		this.nameDerRegion = nameDesOrtes; 
	}
	
	public synchronized String getName() {
		return nameDerRegion;
	}

	public synchronized void setName(String name) {
		this.nameDerRegion = name;
	}

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

	@Override
	public void serializ() {
		for ( int i = 0; this.children.size() > i ; i++){
			this.children.get(i).serializ();
		}
	}

	@Override
	public void deserializ() {
		for ( int i = 0; this.children.size() > i ; i++){
			this.children.get(i).deserializ();
		}
	}
}
