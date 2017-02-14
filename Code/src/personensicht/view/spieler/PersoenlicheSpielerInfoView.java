package personensicht.view.spieler;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import personensicht.model.spieler.Spieler;
import personensicht.view.gameObjekte.lebewesen.Mensch3D;

public class PersoenlicheSpielerInfoView extends HBox
{
	public PersoenlicheSpielerInfoView(Spieler spieler) 
	{
		Mensch3D mensch = new Mensch3D(spieler);
		this.getChildren().add(mensch.getRoot());
		this.setAlignment(Pos.CENTER);
	}
}
