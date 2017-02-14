package personensicht.model.aktionen;

import personensicht.model.inventar.items.Item;

public class AktionTrinken extends Aktion
{
	private int durstLoescher;
	private Item item; 
	
	public AktionTrinken(int durstLoescher, Item item) 
	{
		super(Aktionstyp.Trinken);
		this.durstLoescher = durstLoescher; 
		this.setName("trinken");
		this.item= item;
	}
	public synchronized int getDurstLoescher() {
		return durstLoescher;
	}
	
	public synchronized Item getItem() 
	{
		return item;
	}
}
