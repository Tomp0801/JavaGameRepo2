package draufsicht.view;


import draufsicht.model.Map;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;

/**
 * Klasse für die Grafische Darstellung einer Karte
 * erbt von Group
 * es werden die Einzelnen Felder nebeneinander dargestellt
 * 
 * @author Thomas
 *
 */
public class MapGrafics extends Group {
	public static final int STANDARDFELDWIDTH = 20;
	public static final int STANDARDFELDHEIGHT = 20;
	
	private Map model;
	
	private IntegerProperty MapWidth;
	
	private IntegerProperty MapHeight;
	
	private IntegerProperty FeldWidth;
	private IntegerProperty FeldHeight;
	
	/**
	 * Erstellt die grafische Darstellung der abngegebenen Karte
	 * @param map Karte zu der dieses View-Objekt erstellt werden soll
	 */
	public MapGrafics(Map map) {
		this.model = map;
		
		MapWidth = new SimpleIntegerProperty();
		MapHeight = new SimpleIntegerProperty();

		FeldWidth = new SimpleIntegerProperty();
		FeldHeight = new SimpleIntegerProperty();
		
		init();
		
		MapWidth.set(map.getWidth() * STANDARDFELDWIDTH);
		MapHeight.set(map.getHeight() * STANDARDFELDHEIGHT);
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
	}

	/**
	 * @return das zu diesem Grafik-Objekt zugehörige Model-Objekt
	 */
	public Map getModel() {
		return model;
	}

	/**
	 * @return die Breite der Karte in Pixeln
	 */
	public int getMapWidth() {
		return MapWidth.get();
	}
	
	/**
	 * Setzt die Breite der Karte in Pixeln
	 * @param width Breite der Karte in Pixeln
	 */
	public void setMapWidth(int width) {
		setMapWidth(width, false);
	}
	
	/**
	 * Setzt die Breite der Karte in Pixeln und passt die Höhe automatisch so an,
	 * dass die Felder quadratisch sind
	 * @param width Breite der Karte in Pixeln
	 * @param autoAdjust wenn true, Automatische Anpassung der Kartenhöhe
	 */
	public void setMapWidth(int width, boolean autoAdjust) {
		MapWidth.set(width);
		if (autoAdjust) {
			MapHeight.set(FeldWidth.get() * model.getHeight());
		}
	}

	/**
	 * @return die Höhe der Karte in Pixeln
	 */
	public int getMapHeight() {
		return MapHeight.get();
	}
	
	/**
	 * Setzt die Höhe der Karte in Pixeln
	 * @param height Höhe der Karte in Pixeln
	 */
	public void setMapHeight(int height) {
		setMapHeight(height, false);
	}
	
	/**
	 * Setzt die Höhe der Karte in Pixeln und passt die Breite automatisch so an,
	 * dass die Felder quadratisch sind
	 * @param height Höhe der Karte in Pixeln
	 * @param autoAdjust wenn true, Automatische Anpassung der Kartenbreite
	 */
	public void setMapHeight(int height, boolean autoAdjust) {
		MapHeight.set(height);
		if (autoAdjust) {
			MapWidth.set(FeldHeight.get() * model.getWidth());
		}
	}
	
	/**
	 * Setzt Breite und Höhe der Karte in Pixeln
	 * @param width Breite der Karte
	 * @param height Höhe der Karte
	 */
	public void setDimensions(int width, int height) {
		MapWidth.set(width);
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

