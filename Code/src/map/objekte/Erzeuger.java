package map.objekte;

import global.GameTime;
import map.Feld;

/**
 * Abstrakte Klasse für alle Objekte, die Materialien produzieren sollen
 * 
 * @author Thomas
 *
 */
public abstract class Erzeuger implements Platzierbar {
	/**
	 * Name des Erzeugers, der zb angezeigt wird
	 */
	private String name;
	
	/**
	 * das Feld, auf dem dieses Objekt platziert ist
	 */
	private Feld feld;
	
	/**
	 * der letzte Zeitpunkt (spielzeit), zu dem das objekt aktualisiert wurde mit run()
	 */
	private long lastRefresh;
	
	@Override
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
	protected void setLastRefresh() {
		this.lastRefresh = GameTime.getInstance().timeMillis();
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}
}
