package global;

/**
 * Stellt eine Zeit zur verfügung, die angehalten, beschleunigt oder verlangsamt werden kann
 * 
 * @author Thomas
 *
 */
public final class GameTime {
	
	/**
	 * aktuelle Game Time
	 */
	private long time;
	
	/**
	 * System Zeit, zu der als letztes die Game Time berechet wurde
	 */
	private long lastUpdate;
	
	/**
	 * Faktor, wie schnell die Game Time vergeht
	 */
	private float zeitFaktor;
	
	/**
	 * flag, ob game time grade pausiert ist oder läuft
	 */
	private boolean paused;
	
	private static GameTime instance;
	/**
	 * Konstruktor
	 */
	private GameTime()
	{
		instance = this;
		time = 0;
		lastUpdate = System.currentTimeMillis();
		paused = false;
		zeitFaktor = (float) 1.0;
	}
	
	
	public static GameTime getInstance()
	{
		if (instance == null)
		{
			new GameTime();
		}
		return instance;
	}
	/**
	 * Gibt die SpielZeit in MilliSekunden wieder
	 * @return long zeit in milliSekunden
	 */
	public long timeMillis() {
		if (!paused)
		{
			calcGameTime();
		}
		return time;
	}
	
	/**
	 * hält die spielzeit an
	 */
	public void pause()
	{
		if (!paused)
		{
			calcGameTime();
			paused = true;
		}
	}
	
	/**
	 * lässt die Zeit weiterlaufen, wenn sie angehalten wurde
	 */
	public void resume()
	{
		paused = false;
		lastUpdate = System.currentTimeMillis();
	}
	
	/**
	 * setzt die Spielzeit auf 0 zurück
	 */
	public void reset()
	{
		time = 0;
		lastUpdate = System.currentTimeMillis();
	}

	/**
	 * berechnet die vergangene Spielzeit seit dem gespeicherten Zeitpunkt in last Update
	 */
	private void calcGameTime()
	{
		long currentTime = System.currentTimeMillis();
		long zeitVergangen = currentTime - lastUpdate;
		long gameZeitVergangen = Math.round(zeitVergangen * zeitFaktor);
		time = time + gameZeitVergangen;
		lastUpdate = currentTime;
	}
	
	/**
	 * @return the speed
	 */
	public float getZeitFaktor() {
		return zeitFaktor;
	}

	/**
	 * @param faktor, wie schnell die zeit vergehen soll 
	 */
	public void setZeitFaktor(float faktor) {
		if (!paused)
		{
			calcGameTime();
		}
		this.zeitFaktor = faktor;
	}
}
