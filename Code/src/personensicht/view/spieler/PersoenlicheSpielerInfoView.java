package personensicht.view.spieler;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import personensicht.model.spieler.Spieler;
import personensicht.view.gameObjekte.lebewesen.MenschV;

public class PersoenlicheSpielerInfoView extends HBox
{
	public PersoenlicheSpielerInfoView(Spieler spieler) 
	{
		MenschV mensch = new MenschV(spieler);
		this.getChildren().add(mensch.getRoot());
		this.setAlignment(Pos.CENTER);
	}
}
