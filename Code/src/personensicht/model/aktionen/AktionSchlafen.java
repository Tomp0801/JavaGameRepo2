package personensicht.model.aktionen;

public class AktionSchlafen extends Aktion
{
	private int erholung;
	
	public AktionSchlafen(int erholung)
	{
		super(Aktionstyp.schlafen);
		this.setName("schlafen");
		this.erholung = erholung;
	}

	public synchronized int getErholung() {
		return erholung;
	}
}
