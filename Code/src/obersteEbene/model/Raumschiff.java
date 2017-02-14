package obersteEbene.model;

import java.util.ArrayList;

import personensicht.model.welt.map.Region;

public class Raumschiff 
{
	/**
	 * Name des Raumschiffes
	 */
	private String nameVomSchiff;
	
	ArrayList<Region> umgebung = new ArrayList<Region>();
	
	public Raumschiff(String name)
	{
		this.nameVomSchiff = name;
	}
	
	public void setUmgebung(ArrayList<Region> umgebung)
	{
		this.umgebung = umgebung;
	}
}
