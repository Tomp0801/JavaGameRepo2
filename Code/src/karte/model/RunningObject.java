package karte.model;


import obersteEbene.controller.GameTime;

/**
 * Ein Objekt, das konstant eine Arbeit verrichtet
 * 
 * @author Thomas
 *
 */
public abstract class RunningObject {
	private long lastRun;
	
	/**
	 * Erstellt ein neues Objekt und fängt an die Zeit zu stoppen
	 */
	public RunningObject() {
		updateLastRun();
	}
	
	/**
	 * setzt die Zeit auf die aktuelle Spielzeit
	 */
	private void updateLastRun() {
		lastRun = GameTime.getInstance().timeMillis();
	}
	
	/**
	 * Gibt die Zeit zurück, die vergangen ist, seitdem das letzte mal nach der Zeit gefragt wurde
	 * @return die Zeit, seitdem das letzte mal nach der Zeit gefragt wurde
	 */
	protected long getPassedTime() {
		long lastTime = lastRun;
		updateLastRun();
		return lastRun - lastTime;
	}
	
	/**
	 * führt aus, was das Objekt abhängig von der vergangenen Zeit zu erledigen hat
	 */
	public abstract void run();
}
