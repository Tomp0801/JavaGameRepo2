package personensicht.view.gameObjekte.lebewesen;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import personensicht.model.gameObjekte.lebewesen.mensch.AntwortGespraech;
import personensicht.model.gameObjekte.lebewesen.mensch.TextGespraech;

public class GespraechView
{
	private VBox[] unterhaltung;
	private StackPane root = new StackPane();
	
	public GespraechView(ArrayList<TextGespraech> reaktionListe, ArrayList<AntwortGespraech> antwortListe)
	{
		root.setAlignment(Pos.CENTER);
		unterhaltung = new VBox[reaktionListe.size()];
		
			for (int i = 0; reaktionListe.size() > i; i++)
			{
				unterhaltung[i] = new VBox();
				Label labelReaktion = new Label(reaktionListe.get(i).getText());
				labelReaktion.setWrapText(true);
				unterhaltung[i].getChildren().add(labelReaktion);
				HBox hBox = new HBox();
				unterhaltung[i].getChildren().add(hBox);
				hBox.setSpacing(4);
				hBox.setAlignment(Pos.CENTER);
				unterhaltung[i].setAlignment(Pos.CENTER); 
				unterhaltung[i].setSpacing(40); 
				
				
				if (reaktionListe.get(i).getIdVerweis() != null)
				{
				for (int j = 0; reaktionListe.get(i).getIdVerweis().length > j;j++)
				{
					if (antwortListe.get( reaktionListe.get(i).getIdVerweis()[j] ).getVerweist() != null)
					{
					Button buttenAntwort = new Button( antwortListe.get( reaktionListe.get(i).getIdVerweis()[j] ).getAntwort() );
					int k = antwortListe.get( reaktionListe.get(i).getIdVerweis()[j] ).getVerweist()[0];
					buttenAntwort.setOnAction(new EventHandler<ActionEvent>() 
					{
						@Override
						public void handle(ActionEvent event) 
						{
							root.getChildren().clear();
							root.getChildren().add(unterhaltung[k]);
						}
					});
					hBox.getChildren().add(buttenAntwort);
					}
				}
			}
		}	
		root.getChildren().add(unterhaltung[0]);
	}

	public synchronized Pane getRoot() {
		return root;
	}
}
