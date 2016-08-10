package map;

import java.io.Serializable;

/**
 * Grundklasse f�r alle Stoffe Materialen, etc.
 * 
 * z.B. Erze, Stein, Essen, Holz...
 * 
 * 
 * @author Thomas
 *
 */
public abstract class Ressource implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4161272941765820029L;

	/**
	 * Bezeichnung f�r die Ressource
	 */
	private String name;
	
	/**
	 * Energie, die das Material beinhaltet und die, z.B. durch Verbrennung umgesetzt werden kann
	 * TODO einheit
	 */
	private float innereEnergie;
	
	/**
	 * Das Material, das durch Verbrennung dieses Materials entsteht
	 * TODO n�tig hier?
	 */
	private Material verbrennErgebnis;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the innereEnergie
	 */
	public float getInnereEnergie() {
		return innereEnergie;
	}

	/**
	 * @param innereEnergie the innereEnergie to set
	 */
	protected void setInnereEnergie(float innereEnergie) {
		this.innereEnergie = innereEnergie;
	}

}
