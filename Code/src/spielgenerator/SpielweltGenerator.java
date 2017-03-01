package spielgenerator;

import java.util.ArrayList;

import personensicht.model.gameObjekte.Bett;
import personensicht.model.gameObjekte.GameObjekt;
import personensicht.model.gameObjekte.Schrank;
import personensicht.model.gameObjekte.lebewesen.Mensch;
import personensicht.model.inventar.items.Essen;
import personensicht.model.inventar.items.Item;
import personensicht.model.inventar.items.Trinken;
import personensicht.model.welt.map.Region;

/**
 * generriert eine Spielwelt
 * @author Dennis
 *
 */
public class SpielweltGenerator 
{
	public SpielweltGenerator()
	{}
	
	/**
	 *  Erstellt eine Demowelt
	 * @return Die Region, auf dem sich der Spieler befindet
	 */
	public static Region generriereDemoWelt()
	{	
		ArrayList<Region> stockwerk = new ArrayList<Region>();
		ArrayList<Region> flure = new ArrayList<Region>();
		
		Region fahrstuhl = new Region("Fahrstuhl");
				
		//Stockwerk
		for (int r = 0; 10 > r ; r++)
		{
			stockwerk.add(new Region("Stockwerk "+r, fahrstuhl));
			fahrstuhl.addNachbarn(stockwerk.get(r));
			//Flure
			for (int i = 0; 5 > i ; i++)
			{
				flure.add(new Region("Flur "+(i), stockwerk.get(r)));
				stockwerk.get(r).addNachbarn(flure.get(flure.size()-1));
				
				for (int j = 0; 10 > j ; j++)
				{
					Region ort = new Region("Quatier "+j , flure.get(flure.size()-1));
					
					ort.addObjekt(new Bett(5));
					Item[] items = {new Essen(), new Essen(), new Trinken()};
					ort.addObjekt(new Schrank(items));
					
					flure.get(flure.size()-1).addNachbarn(ort);
				}
			}
		}
		
		Region zentrale = new Region("Zuhause", fahrstuhl); 
		Mensch mensch = new Mensch("Dennis");
		zentrale.addObjekt(mensch);
		
		return zentrale;
	}
	
	/**
	 * Aufbau des Raumschiffes
	 */
	private void initQuatier()
	{
		//Objekte die ich im Quatier befinden
		ArrayList<GameObjekt> objekte = new ArrayList<GameObjekt>();
		//Die Position der Objekte
		ArrayList<Integer[]> position = new ArrayList<Integer[]>();
		//fuegt einen Menschen hinzu
		objekte.add(new Mensch("Name"));
		
		Region startRegion = new Region("Eigenes Quatier", objekte, position);
	}
}
