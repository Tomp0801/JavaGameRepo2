package map.objekte;

import java.util.ArrayList;
import java.util.HashMap;

import global.GameTime;
import map.BodenMaterial;
import map.Feld;
import map.Material;

/**
 * Ein Platzierbares objekt, das von einem feld bodenSchätze abbauen kann
 * 
 * TODO getter und setter
 * 
 * @author Thomas
 *
 */
public class Mine extends Erzeuger implements Platzierbar {
	/**
	 * der Bodenschatz, den diese Mine abbauen kann
	 */
	private BodenMaterial art;
	
	/**
	 * das Feld, auf dem diese Mine platziert ist
	 */
	private Feld feld;
	
	/**
	 * der letzte Zeitpunkt (spielzeit), zu dem das objekt aktualisiert wurde mit run()
	 */
	private long lastRefresh;
	
	/**
	 * gibt an, wie schnell/effektiv Rohstoffe abgebaut werden.
	 * Prozent (zahl zwischen 0 und 1)
	 */
	private float effektivitaet;
	
	/**
	 * Konstruktor
	 * @param feld das Feld, auf dem die Mine steht
	 * @param material der ROhstoff, den die Mine abbaut
	 * @param effiktivitaet die effektivitaet, mit der der Rohstoff abgebaut wird (Zahl zwischen 0 und 1)
	 */
	public Mine(BodenMaterial material, float effektivitaet)
	{
		this.art = material;
		if (effektivitaet >= 0 && effektivitaet <= 1)
		{
			this.effektivitaet = effektivitaet;
		}
		else
		{
			this.effektivitaet = 0;
		}
		
		lastRefresh = GameTime.getInstance().timeMillis();
	}
	
	/**
	 * ermittelt ob die Mine noch Bodenschaetze von diesem Feld holen kann, oder ob keine mehr vorhanden sind
	 * @return boolean, ob noch bodenschaetze zu holen sind, oder ob die Mine still liegt
	 */
	public boolean isWorking()
	{
		if (feld.getBodenschatzVorkommen().containsKey(this.art))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public HashMap<Material, Double> run() {
		long passedTime;
		long prevRefresh = lastRefresh;
		lastRefresh = GameTime.getInstance().timeMillis();
		passedTime = lastRefresh - prevRefresh;
		
		//berechnen, wie viel abgebaut werden könnte
		//TODO material pro sekunde, wie viel wird abgebaut?
		//in Material speichern? abhängig von übriger Menge?
		float materialProStunde = 1;
		float menge = effektivitaet * (float)passedTime * materialProStunde;
		
		menge = feld.mineRohstoff(this.art, menge);
		
		HashMap<Material, Double> abbau = new HashMap<Material, Double>();
		abbau.put(this.art, new Double(menge));
		return abbau;
	}

	@Override
	public ArrayList<Material> getOutputs() {
		ArrayList<Material> produkte = new ArrayList<Material>();
		produkte.add(this.art);
		return produkte;
	}

	@Override
	public ArrayList<Material> getInputs() {
		// TODO wirklich keine Materialien?
		return null;
	}

}
