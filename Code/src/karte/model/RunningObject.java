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
	 * Erstellt ein neues Objekt und f�ngt an die Zeit zu stoppen
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
	 * Gibt die Zeit zur�ck, die vergangen ist, seitdem das letzte mal nach der Zeit gefragt wurde
	 * @return die Zeit, seitdem das letzte mal nach der Zeit gefragt wurde
	 */
	protected long getPassedTime() {
		long lastTime = lastRun;
		updateLastRun();
		return lastRun - lastTime;
	}
	
	/**
	 * f�hrt aus, was das Objekt abh�ngig von der vergangenen Zeit zu erledigen hat
	 */
	public abstract void run();
}
