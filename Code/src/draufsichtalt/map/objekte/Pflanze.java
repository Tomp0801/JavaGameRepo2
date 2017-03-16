package draufsicht.map.objekte;

import java.util.ArrayList;
import java.util.HashMap;

import draufsicht.ressource.Material;
import draufsicht.speicherverwaltung.IOHandler;

/**
 * eine Pflanze (Busch, Baum, Getreide), die auf einem Feld stehen kann und Material herstellen kann
 * 
 * @author Thomas
 *
 */
public class Pflanze extends Erzeuger implements Platzierbar {
	/**
	 * Konstruktor
	 */
	public Pflanze()
	{
		
	}

	@Override
	public HashMap<Material, Double> run() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Material> getOutputs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Material> getInputs() {
		ArrayList<Material> list = new ArrayList<>();
		list.add((Material)IOHandler.getInstance().readArrayList("verschiedene").get(0));
		return list;
	}
}
