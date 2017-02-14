package personensicht.model.aktionen;

import personensicht.model.inventar.items.Item;

public class AktionEssen extends Aktion
{
	private int naehrwert;
	
	private Item item; 
	
	public AktionEssen (int naehrwert, Item item)
	{
		super(Aktionstyp.Essen);
		this.naehrwert = naehrwert;
		this.item = item;
		this.setName("Essen");
	}
	
	public synchronized Item getItem() 
	{
		return item;
	}

	public synchronized int getNaehrwert() {
		return naehrwert;
	}
}
