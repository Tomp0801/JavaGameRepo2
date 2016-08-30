package map.objekte;

import java.util.ArrayList;
import java.util.HashMap;

import ressource.BodenMaterial;
import ressource.Ressource;

/**
 * Ein Platzierbares objekt, das von einem feld bodenSchätze abbauen kann
 * 
 * @author Thomas
 *
 */
public class Mine extends Erzeuger {
	/**
	 * der Bodenschatz, den diese Mine abbauen kann
	 */
	private BodenMaterial art;
	
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
		
		this.setName(material.getName() + "mine");
		
		this.setLastRefresh();
	}
	
	/**
	 * ermittelt ob die Mine noch Bodenschaetze von diesem Feld holen kann, oder ob keine mehr vorhanden sind
	 * @return boolean, ob noch bodenschaetze zu holen sind, oder ob die Mine still liegt
	 */
	public boolean isWorking()
	{
		//TODO überdenken, wie Ressourcen als Key benutzt werden können
		return false;
	}
	
	@Override
	public HashMap<Ressource, Double> run() {
		long passedTime;
		long prevRefresh = getLastRefresh();
		setLastRefresh();
		passedTime = getLastRefresh() - prevRefresh;
		
		//berechnen, wie viel abgebaut werden könnte
		//TODO material pro sekunde, wie viel wird abgebaut?
		//in Material speichern? abhängig von übriger Menge?
		float materialProStunde = 1;
		float menge = effektivitaet * (float)passedTime * materialProStunde;
		
		menge = getFeld().mineRohstoff(this.art, menge);
		
		HashMap<Ressource, Double> abbau = new HashMap<Ressource, Double>();
		abbau.put(this.art, new Double(menge));
		return abbau;
	}

	@Override
	public ArrayList<Ressource> getOutputs() {
		ArrayList<Ressource> produkte = new ArrayList<Ressource>();
		produkte.add(this.art);
		return produkte;
	}

	@Override
	public ArrayList<Ressource> getInputs() {
		// TODO beschleunigung durch Energie
		return null;
	}

	/**
	 * @return der Bodenschatz, den diese Mine aus dem Boden holen kann
	 */
	public BodenMaterial getArt() {
		return art;
	}

	/**
	 * die Effektivitaet mit der die Mine arbeitet
	 * @return float zwischen 0 und 1  
	 */
	public float getEffektivitaet() {
		return effektivitaet;
	}

}
