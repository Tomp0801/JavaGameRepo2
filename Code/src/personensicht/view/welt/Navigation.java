package personensicht.view.welt;

import javafx.scene.layout.VBox;
import personensicht.model.welt.map.Region;
//Diese Klasse ist veraltet und wird demnaechst geloescht
public class Navigation extends VBox
{
	public Navigation(Region ort)
	{
		reflesh(ort);
	}
	
	private void reflesh(Region ort)
	{
		this.getChildren().clear();
//		for (int i = 0 ; i < ort.getNachbarsOrte().size() ; i++)
//		{
//			Button reise = new Button();
//			Region neuerOrt = ort.getNachbarsOrte().get(i);
//			reise.setOnAction(new EventHandler<ActionEvent>() 
//			{
//				Region neuerOrt2 = neuerOrt; 
//				@Override
//				public void handle(ActionEvent event) 
//				{
//					Spielzustand.setOrt(neuerOrt2);
//					Spielcontroller.getInstance().getSpieler().changeAusdauer(-1);
//					reflesh(neuerOrt2);
//				}
//			});
//			reise.setText(ort.getNachbarsOrte().get(i).getName());
//			this.getChildren().add(reise);
//		}
	}
}
