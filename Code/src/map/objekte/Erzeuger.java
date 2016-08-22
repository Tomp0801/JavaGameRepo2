package map.objekte;

import map.Feld;

/**
 * Abstrakte Klasse für alle Objekte, die Materialien produzieren sollen
 * 
 * @author Thomas
 *
 */
public abstract class Erzeuger implements Platzierbar {
	/**
	 * das Feld, auf dem dieses Objekt platziert ist
	 */
	private Feld feld;
	
	/**
	 * der letzte Zeitpunkt (spielzeit), zu dem das objekt aktualisiert wurde mit run()
	 */
	private long lastRefresh;
	
	
	/**
	 * @return das Feld, auf dem dieses Objekt steht
	 */
	public Feld getFeld() {
		return feld;
	}

	/**
	 * Platziert das Objekt auf einem Feld, wenn dieses frei ist
	 * @param feld Feld, auf dem das Objekt platziert werden soll
	 * @return erfolg der Platzierung. false wenn Feld besetzt ist
	 */
	public boolean platziere(Feld feld)
	{
		if (feld.isFree())
		{
			this.feld = feld;
			feld.setBauplatz(this);
			return true;
		}
		else
		{
			return false;
		}
	}


	/**
	 * @return the lastRefresh
	 */
	protected long getLastRefresh() {
		return lastRefresh;
	}
	
	/**
	 * @param lastRefresh the lastRefresh to set
	 */
	protected void setLastRefresh(long lastRefresh) {
		this.lastRefresh = lastRefresh;
	}
}
