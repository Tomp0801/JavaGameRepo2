package spieler;

/**
 * Eine Rasse. 
 * 
 * @author Dennis
 */
public class Rasse 
{
	/**
	 * name der Rasse
	 */
	private String name;

	//--------------------------------------Eigenschaften-der-Rasse---------------------------------------------------//
	
	/**
	 * Die Geburtsrate einer Rasse ist von diesem Wert abhaengig. Um so hoeher der Wert, des so mehr Kinder werden in einem
	 * Zeitinterval geboren.
	 */
	private int geburtsrate;
	
	/**
	 * 	Die maximale Lebenserwartung die eine Rasse hat. 
	 */
	private int lebensduer;
	
	/**
	 * Die lerngeschwindigkeit der Rasse beeinflusst die Ausbildungszeit von Einheiten. 
	 * Um so hoher die lernfaehigkeit des so Anassungsfeaahiger und des so schneller koennen Einheiten der Rasse ausgebildet werden.
	 */
	private int lernfaehgígkeit;

	//--------------------------------------------Verhaltensweisen----------------------------------------------------------//
	//Diese Eigenschaften sollen das Dilomatische Verhalten beeinflussen, haben aber keinen weiteren Effekt.
	
	private int neugier;
	
	private int aggressivitaet; 

	private int misstrauen;
	
	private int gier; 
	
	/**
	 * generriert eine Rasse
	 */
	public Rasse()
	{
		
	}
	
	
	public synchronized String getName() 
	{
		return name;
	}


	public synchronized void setName(String name) 
	{
		this.name = name;
	}


	public synchronized int getGeburtsrate()
	{
		return geburtsrate;
	}


	public synchronized void setGeburtsrate(int geburtsrate) 
	{
		this.geburtsrate = geburtsrate;
	}


	public synchronized int getLebensduer() 
	{
		return lebensduer;
	}


	public synchronized void setLebensduer(int lebensduer) 
	{
		this.lebensduer = lebensduer;
	}


	public synchronized int getLernfaehgígkeit() 
	{
		return lernfaehgígkeit;
	}


	public synchronized void setLernfaehgígkeit(int lernfaehgígkeit) 
	{
		this.lernfaehgígkeit = lernfaehgígkeit;
	}


	public synchronized int getNeugier()
	{
		return neugier;
	}


	public synchronized void setNeugier(int neugier) 
	{
		this.neugier = neugier;
	}


	public synchronized int getAggressivitaet()
	{
		return aggressivitaet;
	}


	public synchronized void setAggressivitaet(int aggressivitaet) 
	{
		this.aggressivitaet = aggressivitaet;
	}


	public synchronized int getMisstrauen() 
	{
		return misstrauen;
	}


	public synchronized void setMisstrauen(int misstrauen) 
	{
		this.misstrauen = misstrauen;
	}


	public synchronized int getGier() 
	{
		return gier;
	}


	public synchronized void setGier(int gier) 
	{
		this.gier = gier;
	}

	
	
	/**
	 * 
	 * @author Demix
	 *
	 */
	public class Haut
	{
		
	}
}
