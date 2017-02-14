package personensicht.view.inventar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import personensicht.crt.spielablauf.AktionsCrt;
import personensicht.model.inventar.items.Item;

/**
 * 
 * @author Dennis
 *
 */
public class ItemImInventarView extends MenuButton
{
	private Item item; 
	
	public ItemImInventarView(Item item)
	{	
		this.item =item;
		this.setText(item.getName());
		for (int r = 0; item.getAktionen().size() > r ; r++)
		{	
			MenuItem aktions = new MenuItem(item.getAktionen().get(r).getName());

			int b = r;
			aktions.setOnAction(new EventHandler<ActionEvent>() {
					
				@Override
				public void handle(ActionEvent event) 
				{
					AktionsCrt.doAktion(item.getAktionen().get(b));
				}
			});
			this.getItems().add(aktions);
		}
	}

	public synchronized Item getItem() {
		return item;
	}
}
