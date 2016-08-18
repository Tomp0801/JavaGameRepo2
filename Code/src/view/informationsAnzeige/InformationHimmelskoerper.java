package view.informationsAnzeige;

import java.util.ArrayList;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 * geht der Spieler mit der Maus ueber einen Himmelskoerper, werden Informationen mit hilfe dieser Klasse angezeigt. 
 * @author Demix
 *
 */
public class InformationHimmelskoerper extends VBox
{
	public InformationHimmelskoerper(ArrayList<TextArea> inhalt)
	{
		
		//TODO 
		
		for (int i = 0 ; inhalt.size() > i ; i++)
		{
			this.getChildren().add(inhalt.get(i));
		}
	}
}
 