package karte.view;

import javafx.beans.property.IntegerProperty;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import karte.model.Feld;

/**
 * Klasse für Grafische Darstellung eines Feldes
 * die Eigenschaft boden legt einen Hintergrund fest, darauf wird das Objekt dargestellt, das auf
 * diesem Feld steht
 * Das Feld hat ein BorderLayout
 * 
 * @author Thomas
 *
 */
public class FeldGrafics extends StackPane {

	/**
	 * Daten-Objekt, das zu diesem View-Objekt gehört
	 */
	private Feld model;
	
	/**
	 * Boden Fläche. Stellt eine Farbe dar, wird an unterste Stelle platziert
	 */
	private Rectangle boden;
		
	/**
	 * Erstellt die Grafische Darstellung eines Feldes
	 * @param feld zugehöriges Feld für diese grafische Darstellung
	 */
	public FeldGrafics(Feld feld) {
		model = feld;
		
		boden = new Rectangle();
		
		this.setOnMouseClicked(feld);					
		this.getChildren().add(boden);
		
		this.bindProperties();
		//model.karte.grafics ist zu diesem Zeitpunkt null
		
		feld.getKarte().getGrafics().getChildren().add(this);
	}
	
	/**
	 * Bindet die nötigen Properties dieses Objekts an die entsprechenden Properties
	 */
	public void bindProperties() {
		//binde die breite und Höhe an die von der Karte vorgegebenen Werte
		IntegerProperty width = model.getKarte().getGrafics().feldWidthProperty();
		IntegerProperty height = model.getKarte().getGrafics().feldHeightProperty(); 

		boden.widthProperty().bind(width);
		boden.heightProperty().bind(height);
		
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
	public Color getBoden() {
		return (Color) boden.getFill();
	}
	
	/**
	 * Setzt die Farbe des Bodens dieses Feldes
	 * @param boden Farbe des Bodens
	 */
	public void setBoden(Color boden) {
		this.boden.setFill(boden);;
	}
}
