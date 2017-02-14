package draufsicht.map.objekte;

import draufsicht.map.Feld;

public class Infrastruktur {
	/**
	 * art der Infrastruktur
	 */
	private InfrastrukturEnum typ;
	
	/**
	 * Feld, auf dem sich die struktur befindet
	 */
	private Feld feld;
	
	/**
	 * distanz zu der Quelle dieser Leitung
	 * -1, wenn kein anschluss zu einer Quelle
	 */
	private int distanz;
	
	/**
	 * Feld, von dem der input in die Leitung kommt
	 */
	private Feld input;

	public Infrastruktur(Feld feld, InfrastrukturEnum typ)
	{
		this.typ = typ;
		this.feld = feld;
		
	}
	
	/**
	 * @return the typ
	 */
	public InfrastrukturEnum getTyp() {
		return typ;
	}

	/**
	 * @return the feld
	 */
	public Feld getFeld() {
		return feld;
	}

//	/**
//	 * @param feld the feld to set
//	 */
//	public void setFeld(Feld feld) {
//		this.feld = feld;
//	}

	/**
	 * @return the distanz
	 */
	public int getDistanz() {
		return distanz;
	}

	/**
	 * @param distanz the distanz to set
	 */
	public void setDistanz(int distanz) {
		this.distanz = distanz;
	}

	/**
	 * @return the input
	 */
	public Feld getInput() {
		return input;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(Feld input) {
		this.input = input;
	}
}
