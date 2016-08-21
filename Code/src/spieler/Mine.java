package spieler;

import java.util.ArrayList;
import java.util.HashMap;

import map.BodenMaterial;
import map.Feld;
import map.Material;
import map.Platzierbar;

/**
 * Ein Platzierbares objekt, das von einem feld bodenSchätze abbauen kann
 * @author Thomas
 *
 */
public class Mine implements Platzierbar {
	/**
	 * der Bodenschatz, den diese Mine abbauen kann
	 */
	private BodenMaterial art;
	
	/**
	 * das Feld, auf dem diese Mine platziert ist
	 */
	private Feld feld;
	
	@Override
	public HashMap<Material, Double> run() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Material> getProducts() {
		// TODO Auto-generated method stub
		return null;
	}

}
