package personensicht.model.inventar.items;

import personensicht.model.gameObjekte.Inventar;

/**
 * bitet methoden zum erleichter der Verwaltung von Items
 * @author Demix
 *
 */
public class Items 
{
	public static Item newItemOfItemTyp(Item item)
	{
		if (item instanceof Essen)
		{
			Essen essen = new Essen();
			essen.refleshAktionsListe();
			return essen;
		}
		else if (item instanceof Trinken)
		{
			Trinken trinken = new Trinken();
			trinken.refleshAktionsListe();
			return trinken; 
		}
		else
		{
			return null; 
		}
	}
}
