package world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import ressource.Material;
import ressource.Prozess;

public class Materialien {
	
	public static ArrayList<Material> material = new ArrayList<Material>();
	
	static {
	
	//**************************************************************************************************************************//
													//STANDARD MATERIALIEN
		
		//************************************* METALLE ***********************************************//
		
		HashMap<Prozess, Material> verarbeitungMetalle = new HashMap<Prozess, Material>(); 
		//TODO mögliche Prozesse ausfüllen
		
		// TODO Spezifikationen

		material.add(new Material("Gold", javafx.scene.paint.Color.GOLD, new float[]{(float) 19302, (float) 0, (float) 0, (float) 1500}, new int[]{1064, 2700}, verarbeitungMetalle));
		
		material.add(new Material("Silber", javafx.scene.paint.Color.SILVER, new float[]{(float) 10490, (float) 0, (float) 0, (float) 1600}, new int[]{962, 2162}, verarbeitungMetalle));
		
		material.add(new Material("Eisen", javafx.scene.paint.Color.LIGHTGRAY, new float[]{(float) 7900, (float) 0, (float) 0, (float) 3000}, new int[]{1538, 2862}, verarbeitungMetalle));
		
		material.add(new Material("Kupfer", javafx.scene.paint.Color.SANDYBROWN, new float[]{(float) 8960, (float) 0, (float) 0, (float) 2000}, new int[]{1085, 2562}, verarbeitungMetalle));
	
		//*********************************** Bodenmaterialien **************************************//
		
		material.add(new Material("Granit", javafx.scene.paint.Color.DARKGRAY, new float[]{(float) 2800, (float) 0, (float) 0, (float) 1000}, new int[]{1450, Integer.MAX_VALUE}, verarbeitungMetalle));
		
		//******************************************* Flüssigkeiten ********************************************//
	
		material.add(new Material("Wasser", javafx.scene.paint.Color.SKYBLUE, new float[]{(float) 1000, (float) 0, (float) 0, (float) 150}, new int[]{0, 100}, verarbeitungMetalle));
		
		
		
		//Materialien nach der stabilität sortieren
		//TODO gut so??
		Collections.sort(material, new Comparator<Material>() 
		{
			 @Override
			 public int compare(Material first, Material second)
			 {
				if (first.getStabilitaet() < second.getStabilitaet())
				{
					return -1;
				}
				else if (first.getStabilitaet() > second.getStabilitaet())
				{
					return 1;
				}
				else
				{
					return 0;
				}
			 }
		} );
	}
}
