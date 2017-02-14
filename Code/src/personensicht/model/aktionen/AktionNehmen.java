package personensicht.model.aktionen;

import personensicht.model.inventar.items.Item;

public class AktionNehmen extends Aktion
{
	private Item item; 
	
	public AktionNehmen(Item item) 
	{
		super(Aktionstyp.nehmen);
		this.setName("nehmen");
		this.item= item; 
	}

	public synchronized Item getItem() {
		return item;
	}

}
