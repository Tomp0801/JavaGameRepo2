package personensicht.model.gameObjekte.lebewesen;



public class TextGespraech 
{
	private String text;
	
	private int id; 

	private int[] idVerweis;
	
	
	public TextGespraech(int id, String text, int[] idVerweis)
	{
		this.id = id; 
		this.text = text;
		this.idVerweis = idVerweis; 
	}

	
	public synchronized String getText() {
		return text;
	}

	public synchronized int[] getIdVerweis() {
		return idVerweis;
	}
}
