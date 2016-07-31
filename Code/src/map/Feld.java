package map;

import java.util.HashMap;

/**
 * Ein Feld eines gewissen typs, das bebaut werden kann und/oder Objekte enthalten kann
 * 
 * @author Thomas
 *
 */
public class Feld {
	/**
	 * Art des Feldes: Stein, W�ste, Wiese, Wald, etc.
	 */
	private String art;
	
	/**
	 * Auflistung der Rohstoffe und deren Menge, die das Feld enth�lt
	 */
	private HashMap<String, Float> rohstoffe;

	/**
	 * Stellt die Menge des angegebenen Rohstoffs f�r das Feld ein
	 * Wenn ein vorheriger Wert f�r diesen Rohstoff existiert, wird er �berschrieben
	 * 
	 * @param rohstoff Art des Rohstoffs
	 * @param menge Menge des Rostoffs
	 */
	public void setRohstoff(String rohstoff, Float menge) {
		rohstoffe.put(rohstoff, menge);
	}
	
	/**
	 * @return the art
	 */
	public String getArt() {
		return art;
	}

	/**
	 * @param art the art to set
	 */
	public void setArt(String art) {
		this.art = art;
	}

	/**
	 * @return the rohstoffe
	 */
	public HashMap<String, Float> getRohstoffe() {
		return rohstoffe;
	}

	/**
	 * @param rohstoffe the rohstoffe to set
	 */
	public void setRohstoffe(HashMap<String, Float> rohstoffe) {
		this.rohstoffe = rohstoffe;
	}
}
