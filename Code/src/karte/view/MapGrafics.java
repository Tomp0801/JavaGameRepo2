package karte.view;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import karte.model.Map;

public class MapGrafics extends Group {
	private Map model;
	
	private IntegerProperty MapWidth;
	
	private IntegerProperty MapHeight;
	
	private IntegerProperty FeldWidth;
	private IntegerProperty FeldHeight;
	
	public MapGrafics(Map map, int width, int height) {
		this.model = map;
		map.setGrafics(this);
		
		MapWidth = new SimpleIntegerProperty();
		MapHeight = new SimpleIntegerProperty();

		FeldWidth = new SimpleIntegerProperty();
		FeldHeight = new SimpleIntegerProperty();
		
		init();
		
		MapWidth.set(width);
		MapHeight.set(height);
	}
	
	private void init() {
		//bei Veränderung der Kartengröße
		MapWidth.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				FeldWidth.set(newValue.intValue() / model.getWidth());
			}
		});
		MapHeight.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				FeldHeight.set(newValue.intValue() / model.getHeight());
			}
		});
		
		//fields hinzufügen
		FeldGrafics feld;
		for (int x = 0; x < model.getWidth(); x++) {
			for (int y = 0; y < model.getHeight(); y++) {
				feld = model.getFeld(x, y).getGroup();
				feld.layoutXProperty().bind(FeldWidth.multiply(x));
				feld.layoutYProperty().bind(FeldHeight.multiply(y));
				this.getChildren().add(feld);
				feld.bindProperties();
			}
		}
	}

	/**
	 * @return the model
	 */
	public Map getModel() {
		return model;
	}

	/**
	 * @return the mapWidth
	 */
	public int getMapWidth() {
		return MapWidth.get();
	}
	
	public void setMapWidth(int width) {
		MapWidth.set(width);
	}

	/**
	 * @return the mapHeight
	 */
	public int getMapHeight() {
		return MapHeight.get();
	}
	
	public void setMapHeight(int height) {
		MapHeight.set(height);
	}

	/**
	 * @return the FeldWidth
	 */
	public IntegerProperty feldWidthProperty() {
		return FeldWidth;
	}

	/**
	 * @return the FeldHeight
	 */
	public IntegerProperty feldHeightProperty() {
		return FeldHeight;
	}
}

