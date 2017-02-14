package personensicht.model.gameObjekte;

import personensicht.model.inventar.items.Item;

public class Inventar
{
	private String besitzer = "default";
	
	private Item[] item;
	
	public Inventar(int maxMengeItem, String name)
	{
		this.besitzer = name; 
		item = new Item[maxMengeItem];
		for ( int i = 0; maxMengeItem > i ; i++)
		{
			item[i] = null;
		}
		
	}
	
	public synchronized Item[] getItemList() {
		return item;
	}

	public synchronized void setItemList(Item[] item) 
	{
		this.item = item;
	}
	
	/**
	 * 
	 * @param item
	 * @return Index, in dem das Item hineingelegt wurde
	 * @throws Exception wenn kein Platz mehr vorhanden ist
	 */
	public synchronized int addItem(Item item) throws Exception
	{
		boolean itemGesetzt = false; 
		for (int i = 0; this.item.length > i; i++)
		{
			if (this.item[i] == null)
			{
				this.item[i] = item; 
				itemGesetzt = true;
				return i;
			}
		}
		if (itemGesetzt == false)
		{
			throw new Exception("Inventar ist voll"); 
		}
		return -1;
	}
	
	public synchronized void addAllItem(Item[] newItem) throws Exception
	{
		for (int i = 0; newItem.length > i; i++)
		{
			this.addItem(newItem[i]);
		}
	}

	public Boolean existItem(Item item)
	{
		for (int i = 0; this.item.length > i; i++)
		{
			if (this.item[i] != null)
			{
				if (this.item[i] == item)
				{
					return true; 
				}
			}
		}
		return false; 
	}

	public synchronized String getBesitzer() {
		return besitzer;
	}
	public synchronized void setBesitzer(String besitzer) {
		this.besitzer = besitzer;
	}

}
