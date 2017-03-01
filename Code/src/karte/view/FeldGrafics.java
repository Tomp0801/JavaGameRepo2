package karte.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import karte.model.Feld;

public class FeldGrafics extends Group {

	
	private IntegerProperty Width;
	private IntegerProperty Height;
	
	private Feld model;
	
	private Rectangle boden;
	
	public FeldGrafics(Feld feld) {
		model = feld;
		this.setOnMouseClicked(feld);
		
		Width = new SimpleIntegerProperty();
		Height = new SimpleIntegerProperty();
		
		boden = new Rectangle();
		boden.setFill(Color.BLACK);
		
		this.getChildren().add(boden);
	}
	
	public void bindProperties() {
		Width.bind(model.getKarte().getGrafics().feldWidthProperty());
		Height.bind(model.getKarte().getGrafics().feldHeightProperty());
		
		boden.widthProperty().bind(Width);
		boden.heightProperty().bind(Height);
	}
	
	public void addChild(Group child) {
		this.getChildren().add(child);
	}
	
	public void addChild(Node child) {
		this.getChildren().add(child);
	}
	
	public Feld getModel() {
		return model;
	}
	
	public Rectangle getBoden() {
		return boden;
	}
	
	public IntegerProperty widthProperty() {
		return Width;
	}
	
	public IntegerProperty heightProperty() {
		return Height;
	}
}
