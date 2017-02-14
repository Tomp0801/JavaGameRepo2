package gameMaker.view.einstellungGameObjekte;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import personensicht.model.gameObjekte.GameObjekt;

public abstract class EinstellungGameObjekt extends VBox
{
	private String name;  
	private TitledPane eigenschaften;
	private VBox rootEigenschaften;
	private GameObjekt objekt;
	
	EinstellungGameObjekt(GameObjekt objekt)
	{
		this.objekt = objekt;  
		eigenschaften = new TitledPane();
		rootEigenschaften = new VBox();
		eigenschaften.setContent(rootEigenschaften);
		HBox namesEingabeBox = new HBox();
		namesEingabeBox.setSpacing(6);
		namesEingabeBox.setAlignment(Pos.CENTER);
		Label bedeutungLabel = new Label("Erholung");
		TextField textField = new TextField("10");
		namesEingabeBox.getChildren().addAll(bedeutungLabel, textField);
		this.getChildren().add(eigenschaften);
	}

	
	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

	public synchronized TitledPane getEigenschaften() {
		return eigenschaften;
	}

	public synchronized VBox getRootEigenschaften() {
		return rootEigenschaften;
	}


	public synchronized GameObjekt getObjekt() {
		return objekt;
	}
}
