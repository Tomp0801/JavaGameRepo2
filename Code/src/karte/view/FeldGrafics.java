package karte.view;

import javafx.beans.property.IntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import karte.model.Feld;
import karte.model.RunningObject;
import ressource.model.Material;

/**
 * Klasse für Grafische Darstellung eines Feldes
 * die Eigenschaft bodenNode legt einen Hintergrund fest, darauf wird das Objekt dargestellt, das auf
 * diesem Feld steht
 * Das Feld hat ein BorderLayout
 * 
 * @author Thomas
 *
 */
public class FeldGrafics extends StackPane implements EventHandler<MouseEvent> {

	/**
	 * Daten-Objekt, das zu diesem View-Objekt gehört
	 */
	private Feld model;
	
	/**
	 * Boden Fläche. Stellt eine Farbe dar, wird an unterste Stelle platziert
	 */
	private Rectangle bodenNode;
	
	private Group bodenschaetzeNode;
		
	/**
	 * Erstellt die Grafische Darstellung eines Feldes
	 * @param feld zugehöriges Feld für diese grafische Darstellung
	 */
	public FeldGrafics(Feld feld) {
		model = feld;
		
		bodenNode = new Rectangle();
		bodenNode.setFill(model.getBodenMaterial().getColor());
		bodenschaetzeNode = new Group();
		
		this.setOnMouseClicked(this);
		this.getChildren().add(bodenNode);
		
		this.setBodenschaetzeNode();
		this.getChildren().add(bodenschaetzeNode);
		
		this.bindProperties();
		
		feld.getKarte().getGrafics().getChildren().add(this);
	}
	
	/**
	 * Bindet die nötigen Properties dieses Objekts an die entsprechenden Properties
	 */
	public void bindProperties() {
		//binde die breite und Höhe an die von der Karte vorgegebenen Werte
		IntegerProperty width = model.getKarte().getGrafics().feldWidthProperty();
		IntegerProperty height = model.getKarte().getGrafics().feldHeightProperty(); 

		bodenNode.widthProperty().bind(width);
		bodenNode.heightProperty().bind(height);
	//	bodenschaetzeNode.widthProperty().bind(width);
	//	bodenschaetzeNode.heightProperty().bind(height);
		
		this.layoutXProperty().bind(width.multiply(model.getX()));
		this.layoutYProperty().bind(height.multiply(model.getY()));
	}
	
	/**
	 * Fügt dem Feld-Group eine zusätzliche Komponente hinzu (in der Mitte)
	 * @param child Grafische Komponente zum Hinzufügen
	 */
	public void addChild(Node child) {
		this.getChildren().add(child);
	}
	
	public void setBodenschaetzeNode() {
		ImagePattern pattern;
		Rectangle dots;
		if (model.getBodenschatzVorkommen() != null) {
			for (Material m : model.getBodenschatzVorkommen().keySet()) {
				pattern = new ImagePattern(m.getGrafics().getDotImage());
				dots = new Rectangle();
				dots.widthProperty().bind(this.widthProperty());
				dots.heightProperty().bind(this.heightProperty());
				dots.setFill(pattern);
				bodenschaetzeNode.getChildren().add(dots);
			}
		}
		
	}
	
	@Override
	public void handle(MouseEvent event) {
		//TODO set this Feld als Scene
		
		if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			System.out.println("Feld " + model.getX() + "|" + model.getY() + " angeklickt.");
			System.out.println("Bodenart: " + model.getBodenMaterial().getName());
			if (model.getObject() != null) {
				System.out.println("Hier steht: " + model.getObject().getName());
				
				if (model.getObject() instanceof RunningObject) {
					RunningObject obj = (RunningObject) model.getObject();
					obj.run();
				}
			}
		}
	}
	
	/**
	 * gibt das zu diesem Grafischen Objekt zugehörige Model-Objekt zurück
	 * @return Model-Objekt dieses Grafik-Objekts
	 */
	public Feld getModel() {
		return model;
	}
	
	/**
	 * Gibt die Farbe des Bodens dieses Feldes zurück
	 * @return Farbe des Bodens
	 */
	public Color getBodenColor() {
		return (Color) bodenNode.getFill();
	}
	
}
