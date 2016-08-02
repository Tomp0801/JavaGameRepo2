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
	 * Art des Feldes: Stein, Wüste, Wiese, Wald, etc.
	 */
	private String art;
	
	/**
	 * Auflistung der Rohstoffe und deren Menge, die das Feld enthält
	 */
	private HashMap<Rohstoff, Float> rohstoffe;
	
	/**
	 * Platz, an dem ein Gebäude o.ä. errichtet werden kann
	 * Kann auch durch natürliche Objekte eingenommen werden
	 */
	private Platzierbar bauplatz;

	/**
	 * Stellt die Menge des angegebenen Rohstoffs für das Feld ein
	 * Wenn ein vorheriger Wert für diesen Rohstoff existiert, wird er überschrieben
	 * 
	 * @param rohstoff Art des Rohstoffs
	 * @param menge Menge des Rostoffs
	 */
	public void setRohstoff(Rohstoff rohstoff, Float menge) {
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
	public HashMap<Rohstoff, Float> getRohstoffe() {
		return rohstoffe;
	}

	/**
	 * @param rohstoffe the rohstoffe to set
	 */
	public void setRohstoffe(HashMap<Rohstoff, Float> rohstoffe) {
		this.rohstoffe = rohstoffe;
	}
}
