package personensicht.view.spieler;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.converter.NumberStringConverter;
import personensicht.crt.spielablauf.Spielcontroller;

/**
 * 
 * @author Dennis
 *
 */
public class SpielerInformationenView extends VBox
{
	public SpielerInformationenView()
	{
		Label nameSpieler = new Label();
		Label hungerSpieler = new Label();
		Label hunger = new Label("Hunger");
		Label durstSpieler = new Label();
		Label durst = new Label("Durst");
		Label muedigkeitSpieler = new Label();
		Label muedigkeit = new Label("Wach");
		Label ausdauerSpieler = new Label();
		Label ausdauer = new Label("Ausdauer");
		Label geldSpieler = new Label();
		Label geld = new Label("Geld ");

		nameSpieler.setTextFill(Color.WHITE);
		nameSpieler.setText(Spielcontroller.getInstance().getSpieler().getName());		
		hunger.setTextFill(Color.WHITE);
		hungerSpieler.setTextFill(Color.WHITE);
		durst.setTextFill(Color.WHITE);
		durstSpieler.setTextFill(Color.WHITE);
		muedigkeitSpieler.setTextFill(Color.WHITE);
		muedigkeit.setTextFill(Color.WHITE);
		ausdauer.setTextFill(Color.WHITE);
		ausdauerSpieler.setTextFill(Color.WHITE);
		geldSpieler.setTextFill(Color.WHITE);
		geld.setTextFill(Color.WHITE);
		
		Bindings.bindBidirectional(hungerSpieler.textProperty(), Spielcontroller.getInstance().getSpieler().getHunger() , new NumberStringConverter());
		Bindings.bindBidirectional(durstSpieler.textProperty(), Spielcontroller.getInstance().getSpieler().getDurst() , new NumberStringConverter());
		Bindings.bindBidirectional(	muedigkeitSpieler.textProperty(), Spielcontroller.getInstance().getSpieler().getMuedigkeit() , new NumberStringConverter());
		Bindings.bindBidirectional(ausdauerSpieler.textProperty(), Spielcontroller.getInstance().getSpieler().getAusdauerpunkte() , new NumberStringConverter());
		Bindings.bindBidirectional(geldSpieler.textProperty(), Spielcontroller.getInstance().getSpieler().getGeld(), new NumberStringConverter());	
		
		
		HBox spielerHungerAnzeige = new HBox(hunger , hungerSpieler);
		HBox spielerDurstAnzeige = new HBox(durst, durstSpieler);
		HBox spielerHungerDurst = new HBox(spielerHungerAnzeige, spielerDurstAnzeige);
		HBox spielerMuedigkeitAnzeige = new HBox(muedigkeit , muedigkeitSpieler);
		HBox spielerAusdauerAnzeige = new HBox(ausdauer, ausdauerSpieler);
		HBox spielermMuedigkeitAusdauer = new HBox(spielerMuedigkeitAnzeige,  spielerAusdauerAnzeige);
		HBox geldBox = new HBox(geld , geldSpieler);
		
		
		spielerHungerDurst.setSpacing(10);
		spielerHungerDurst.setAlignment(Pos.TOP_CENTER);
		spielerDurstAnzeige.setStyle("-fx-fourground-color: WHITH");
		
		spielerDurstAnzeige.setSpacing(6);
		spielerHungerAnzeige.setSpacing(6);
		spielerMuedigkeitAnzeige.setSpacing(2);	
		spielerAusdauerAnzeige.setSpacing(2);
		
		VBox spielerEigenschaften = new VBox(nameSpieler, spielerHungerDurst, spielermMuedigkeitAusdauer, geldBox);	
		spielerEigenschaften.setSpacing(4);
		spielermMuedigkeitAusdauer .setSpacing(10);
		this.setSpacing(40);
		
		spielermMuedigkeitAusdauer.setAlignment(Pos.TOP_CENTER);
		geldBox.setAlignment(Pos.TOP_CENTER);
		spielerEigenschaften.setAlignment(Pos.TOP_CENTER);
		
		this.getChildren().addAll(spielerEigenschaften);
		this.setMinWidth(200);
		this.setAlignment(Pos.TOP_CENTER);
	}
}
