package gameMaker.view;

import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

public class RegionEinstellungen extends TitledPane
{	
	
	private ListView<String> eigenschaften = new ListView<String>();
	
	protected RegionEinstellungen(){
		
		this.setText("Region");
		this.setContent(eigenschaften);
		eigenschaften.getItems().addAll(
				"Nichts","Mauer"
				);
	}
	
	public synchronized ListView<String> getEigenschaften() {
		return eigenschaften;
	}
}	
