package personensicht.view.welt;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import personensicht.crt.spielablauf.AktionsCrt;
import personensicht.model.gameObjekte.lebewesen.Mensch;
import personensicht.model.welt.map.Region;

public class Umgebunsbeschreibung extends VBox
{
	public Umgebunsbeschreibung(Region ort)
	{
		HBox hBox = new HBox();
		VBox links = new VBox();
		VBox rechts = new VBox();
		rechts.getChildren().add(new Label("Lebewesen"));
		rechts.setVisible(false);
		links.getChildren().add(new Label("Gegenstände"));
		hBox.getChildren().addAll(links, rechts);
		this.getChildren().add(hBox);
		
		for (int i= 0; ort.getObjekte().size() >i ; i++)
		{
			MenuButton gameObjekt = new MenuButton(ort.getObjekte().get(i).getName());
			for (int r = 0; ort.getObjekte().get(i).getAktionen().size()> r ; r++)
			{
				MenuItem aktion = new MenuItem(ort.getObjekte().get(i).getAktionen().get(r).getName());
				
				int a = i;
				int b = r;
				aktion.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent event) 
					{
						AktionsCrt.doAktion(ort.getObjekte().get(a).getAktionen().get(b));
					}
				});
				gameObjekt.getItems().add(aktion);
			}
			if (ort.getObjekte().get(i) instanceof Mensch == false)
			{
				links.getChildren().add(gameObjekt);
			}
			else
			{
				rechts.setVisible(true);	
				rechts.getChildren().add(gameObjekt);
			}
		}
		
		this.setAlignment(Pos.CENTER);
		hBox.setAlignment(Pos.CENTER);
		links.setAlignment(Pos.CENTER);
		rechts.setAlignment(Pos.CENTER);
		this.setSpacing(4);
		links.setSpacing(2);
		rechts.setSpacing(2);
		hBox.setSpacing(60);
	}
}
