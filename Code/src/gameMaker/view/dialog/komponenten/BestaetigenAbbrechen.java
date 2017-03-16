package gameMaker.view.dialog.komponenten;

import gameMaker.view.dialog.Dialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class BestaetigenAbbrechen extends HBox{
	
	private Dialog dialog; 
	
	public BestaetigenAbbrechen(Dialog dialog){
		this.dialog = dialog; 
		this.setAlignment(Pos.CENTER);
		this.setSpacing(6);
		
		Button fertig = new Button("Bestätigen");
		fertig.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				dialog.uebernehmeAenderung();		
			}
		});
		Button abbrechen = new Button("Abbrechen");		
		abbrechen.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				dialog.close();
			}
		});
		this.getChildren().addAll(fertig,abbrechen);	
	}
	
}
