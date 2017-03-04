package gameMaker.view.dialog;

import gameMaker.view.RegionMakerV;
import gameMaker.view.dialog.komponenten.BestaetigenAbbrechen;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import personensicht.view.welt.map.RegionV;

public class MapSizeDialog extends Dialog {
	
	private TextField sizeX; 
	private TextField sizeY;
	private int sizeXNeu;
	private int sizeYNeu;
	private Label info; 
	
	public MapSizeDialog(double valueX, double valueY){
		this.sizeXNeu = (int)valueX;
		this.sizeYNeu = (int) valueY;
			
		this.setTitle("Region Größe");
		
		VBox content = new VBox();
		content.setPrefSize(260, 140);
		content.setAlignment(Pos.CENTER);
		content.setSpacing(10);
		
		info = new Label();
		
		HBox sizeXHBox = new HBox();
		sizeXHBox.setAlignment(Pos.CENTER);
		sizeXHBox.setSpacing(6);
		Label sizeXLabel = new Label("Größe X");
		sizeXLabel.setAlignment(Pos.CENTER);
		sizeXLabel.setPrefSize(100, 30);
		this.sizeX = new TextField(""+sizeXNeu);
		this.sizeX.setPrefSize(100, 30);
		sizeXHBox.getChildren().addAll(sizeXLabel,sizeX);
		
		HBox sizeYHBox = new HBox();
		sizeYHBox.setAlignment(Pos.CENTER);
		sizeYHBox.setSpacing(6);
		Label sizeYLabel = new Label("Größe Y");
		sizeYLabel.setPrefSize(100, 30);
		sizeYLabel.setAlignment(Pos.CENTER);
		this.sizeY = new TextField(""+sizeYNeu);
		this.sizeY.setPrefSize(100, 30);
		sizeYHBox.getChildren().addAll(sizeYLabel,sizeY);
		BestaetigenAbbrechen end = new BestaetigenAbbrechen(this);
		end.setSpacing(6);
		end.setPrefSize(200, 30);
		
		content.getChildren().addAll(info, sizeXHBox,sizeYHBox,end);
		this.setScene(new Scene(content));
	}
	
	/**
	 * ueberprueft die Eingabe aus dem Textfeld und gibt eine -1 zurueck, wenn ein Fehler aufgetreten ist
	 * @param string
	 * @return
	 */
	private int ueberpruefeEingabe(String string){
		int i = -1;
		try{
			i = Integer.valueOf(string);
		}
		catch (NumberFormatException e) {
			return -1;
		}
		return i;
	}

	@Override
	public void uebernehmeAenderung() {
		int valueX  = ueberpruefeEingabe(sizeX.getText());
		int valueY  = ueberpruefeEingabe(sizeY.getText());
	
		if (valueX == -1 ){
			sizeX.setText(""+sizeXNeu);
		}
		else{
			sizeXNeu = valueX;
		}
		if (valueY == -1 ){
			sizeY.setText(""+sizeYNeu);
		}
		else{
			sizeYNeu = valueY;
		}
		if (valueY > RegionV.SIZEMAX){
			info.setText("Maximale größe von "+RegionV.SIZEMAX+" überschritten");
			valueY = -2;
		}
		if (valueX > RegionV.SIZEMAX){
			info.setText("Maximale größe von "+RegionV.SIZEMAX+" überschritten");
			valueX = -2; 
		}
		
		if (valueY != -1 && valueX != -1){
			RegionMakerV.getInstance().getRegionPane().setPrefSize(valueX, valueY);
			RegionMakerV.getInstance().getSubScene().setHeight(valueY);
			RegionMakerV.getInstance().getSubScene().setWidth(valueX);
			this.close();
		}
		else{
			info.setText("Eingabe it ungültig");
		}
	}
}
