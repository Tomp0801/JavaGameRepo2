package personensicht.model.gameObjekte.lebewesen;

public class AntwortGespraech 
{
	private int[] verweist;
	private String antwort; 
	private int id; 
	
	public AntwortGespraech(int id, String antwort, int[] verweistID)
	{
		this.id = id;
		this.antwort = antwort; 
		this.verweist = verweistID; 
	}

	public synchronized int[] getVerweist() {
		return verweist;
	}

	public synchronized String getAntwort() {
		return antwort;
	}

	public synchronized int getId() {
		return id;
	}
}
