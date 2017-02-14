package personensicht.view.inventar;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import personensicht.crt.InventarCrt;
import personensicht.model.gameObjekte.Inventar;

/**
 * Zeigt ein oder mehrere Inventare eines Objektes oder Spielers an und ermoeglicht das benutzen und austauschen von 
 * Objekten. 
 * 
 * @author Dennis
 *
 */
public class InventarView extends VBox
{	
	private ArrayList<FlowPane> inventarListe = new ArrayList<FlowPane>();
	
	/**
	 * Nur ein Inventar wird angezeigt
	 * @param inventar
	 */
	public InventarView(Inventar inventar)
	{
		this.getChildren().clear();
		init(inventar);
		ArrayList<Inventar> inventarListe = new ArrayList<Inventar>();
		inventarListe.add(inventar);
		new InventarCrt(this, inventarListe);
	}
	
	/**
	 * beliebig viele Inventarer werden gleichhzeitig angezeigt. 
	 * @param inventare
	 */
	public InventarView(ArrayList<Inventar> inventare)
	{
		for (int i = 0; inventare.size() > i ; i++)
		{
			init(inventare.get(i));
		}
		new InventarCrt(this, inventare);
	}
	
	private void init(Inventar inventar)
	{
		FlowPane flowPane = new FlowPane();
		inventarListe.add(flowPane);
		for (int i = 0; inventar.getItemList().length >i; i++)
		{
			MenuButton item;
			if (inventar.getItemList()[i] != null)
			{
				item = new ItemImInventarView(inventar.getItemList()[i]);
			}
			else
			{
				item = new MenuButton();
				item.setText(" - ");
			}
			
			flowPane.getChildren().add(item);
		}		
			this.getChildren().add(new Label(inventar.getBesitzer())); 
			this.getChildren().add(flowPane);
			this.setAlignment(Pos.TOP_CENTER);
			this.setSpacing(40);
	}

	public synchronized ArrayList<FlowPane> getInventarListe() {
		return inventarListe;
	}
	
	public synchronized void removeItemListe(int index) 
	{
		FlowPane iflow = inventarListe.get(index);
		iflow.getChildren().clear();
	}
}