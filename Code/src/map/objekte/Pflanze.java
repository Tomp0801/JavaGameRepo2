package map.objekte;

import java.util.ArrayList;
import java.util.HashMap;

import ressource.Ressource;
import speicherverwaltung.IOHandler;

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
	public HashMap<Ressource, Double> run() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Ressource> getOutputs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Ressource> getInputs() {
		ArrayList<Ressource> list = new ArrayList<>();
		list.add((Ressource)IOHandler.getInstance().readArrayList("verschiedene").get(0));
		return list;
	}
}
