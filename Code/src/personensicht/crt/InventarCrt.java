package personensicht.crt;

import java.util.ArrayList;

import javafx.scene.control.MenuButton;
import personensicht.model.gameObjekte.Inventar;
import personensicht.model.inventar.items.Item;
import personensicht.model.inventar.items.Items;
import personensicht.view.inventar.InventarView;
import personensicht.view.inventar.ItemImInventarView;

public class InventarCrt 
{
	/**
	 * Die instance vom ViewInventar, das grade geoeffnet ist. 
	 * Dies wird benoetigt um die View anzupassen, wenn eine Aktion durchgefuert wurde
	 */
	private InventarView instanceOfViewInventar;
	
	/**
	 *  Eine Liste von den Inventaren die in der View angezeigt werden
	 */
	private ArrayList<Inventar> inventarListe = new ArrayList<Inventar>();
	
	/**
	 * verweist auf sich selber
	 */
	private static InventarCrt instance;
	
	public InventarCrt(InventarView instanceOfViewInventar, ArrayList<Inventar> inventarListe)
	{
		instance = this;
		this.inventarListe = inventarListe;
		this.instanceOfViewInventar = instanceOfViewInventar;
	}

	public static synchronized InventarCrt getInstance() {
		return instance;
	}
	
	/**
	 * verschiebt eine Item von einem zum andern Inventar
	 */
	public void verschiebeItem(Inventar inventarNeu, Item item)
	{
		try 
		{
			Item newItem = Items.newItemOfItemTyp(item);
			int index = inventarNeu.addItem(newItem);
			newItem.refleshAktionsListe();
			removeItemFromInventar(item);
			instanceOfViewInventar.getInventarListe().get(inventarListe.indexOf(inventarNeu)).getChildren().remove(index);
			instanceOfViewInventar.getInventarListe().get(inventarListe.indexOf(inventarNeu)).getChildren().add(index, new ItemImInventarView(newItem));	
		}
		catch (Exception e) 
		{}
	}
	
	
	/**
	 * loescht ein Item im Model und der View
	 * @param item
	 */
	public void removeItemFromInventar(Item item)
	{
		//sucht nach dem Item in den Invetaren um dies anschließend zu loeschen
		for (int i = 0; inventarListe.size() > i;i++)
		{
			for (int r = 0; inventarListe.get(i).getItemList().length >r; r++)
			{
				if (inventarListe.get(i).getItemList()[r] != null)
				{
					if (inventarListe.get(i).getItemList()[r].equals(item))
					{	
						//loescht das Item aus dem Inventar
						inventarListe.get(i).getItemList()[r] = null; 
						//loescht das Item aus der GUI
						instanceOfViewInventar.getInventarListe().get(i).getChildren().remove(r);
						instanceOfViewInventar.getInventarListe().get(i).getChildren().add(r, new MenuButton("-"));
					}
				}
			}
		}
	}
}
