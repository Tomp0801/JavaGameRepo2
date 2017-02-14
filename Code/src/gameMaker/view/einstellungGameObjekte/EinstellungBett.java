package gameMaker.view.einstellungGameObjekte;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import personensicht.model.gameObjekte.Bett;

public class EinstellungBett extends EinstellungGameObjekt
{
	private int erholungswert = 10;
	
	/**
	 * die groeße des Bettes. [0]fuert x [1]fuer y
	 */
	private int groeßeDesBettes[] = new int[2];
	
	public EinstellungBett(Bett bett)
	{
		super(bett);
		this.setName("Bett");
		this.getRootEigenschaften().getChildren().addAll(
				generateLabelWithTextFieldZahlenEingabe("Erholung", "10", 1 , 100, erholungswert),
				generateLabelWithTextFieldZahlenEingabe("Größe in X", "10", 10 , 40, 10),
				generateLabelWithTextFieldZahlenEingabe("Größe in Y", "10", 10 , 40, 10)			
				);
	}
	
	private static HBox generateLabelWithTextFieldZahlenEingabe(String bedeutung, String inhalt, int von, int bis, int defultWert)
	{
		HBox hBox = new HBox();
		hBox.setSpacing(6);
		hBox.setAlignment(Pos.CENTER);
		Label bedeutungLabel = new Label(bedeutung);
		TextField textField = new TextField(inhalt);
		textField.focusedProperty().addListener(new ChangeListener<Boolean>()
		{

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean alteEinstellung, Boolean neueEinstllung) 
			{
				if (alteEinstellung == true && neueEinstllung == false)
				{
					int i = Integer.valueOf(textField.getText());
					if (i < von || i > bis)
					{
						System.err.println("Die Eingabe ist ungueltig");
						textField.setText(""+defultWert);
					}
				}	
			}
			
		});
		hBox.getChildren().addAll(bedeutungLabel, textField);
		return hBox;
	}
}
