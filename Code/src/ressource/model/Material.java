package ressource.model;

import javafx.scene.paint.Color;
import ressource.view.MaterialGrafics;

import ressource.model.Serializable;

/**
 * Grundklasse für alle Stoffe Materialen, etc.
 * 
 * z.B. Erze, Stein, Essen, Holz...
 * 
 * 
 * @author Thomas
 *
 */
public class Material implements Serializable {
	/**
	 * Bezeichnung für das Material
	 */
	private String name;
	
	/**
	 * Farbe des Materials
	 * Kann nicht javafx Color nehmen, da diese nicht serializeable ist
	 */
	private Color color;
	
	/**
	 * das Gewicht der Material pro Einheit
	 * relevant für den Transport
	 */
	private float gewicht;
	
	private MaterialGrafics grafics;
	
	private float bodenVorkommen;
	
	private Aggregat aggregatzustand;
	
	public Material() {
		
	}
	
	/**
	 * Kosntruktor mit javafx Color, da diese die opacity mit enthält
	 * 
	 * @param name des Materials
	 * @param color (javafx.scene.paint)
	 */
	public Material(String name, Color color) 
	{
		this.name = name;
		this.color = color;
		this.bodenVorkommen = 0f;
		
		this.grafics = new MaterialGrafics(this);
	}
	
	/**
	 * Kosntruktor mit javafx Color, da diese die opacity mit enthält
	 * 
	 * @param name des Materials
	 * @param color (javafx.scene.paint)
	 * @param bodenVorkommen Menge, mit der dieses Material im Boden Vorkommen kann
	 */
	public Material(String name, Color color, float bodenVorkommen) 
	{
		this.name = name;
		this.color = color;
		this.bodenVorkommen = bodenVorkommen;
		
		this.grafics = new MaterialGrafics(this);
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	protected void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return die Farbe als jafafx Color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	protected void setColor(Color color) {
		this.color = color;
	}

	/**
	 * relevant für den Transport von Ressourcen
	 * @return das Gewicht der Material pro Einheit
	 */
	public float getGewicht() {
		return gewicht;
	}

	/**
	 * @param gewicht the gewicht to set
	 */
	protected void setGewicht(float gewicht) {
		this.gewicht = gewicht;
	}
	
	public Aggregat getAggregatzustand() {
		return aggregatzustand;
	}
	
	public boolean isFluessig() {
		if (aggregatzustand == Aggregat.FLUESSIG) return true; 
		return false;
	}
	
	public boolean isFest() {
		if (aggregatzustand == Aggregat.FEST) return true; 
		return false;
	}
	
	public boolean isGas() {
		if (aggregatzustand == Aggregat.GAS) return true; 
		return false;
	}

	protected void setAggregatzustand(Aggregat aggregatzustand) {
		this.aggregatzustand = aggregatzustand;
	}

	public float getBodenVorkommen() {
		return bodenVorkommen;
	}
	
	/**
	 * Gibt die Grafikkomponente dieses Materials zurück
	 * diese enthält unterschiedliche Grafiken für dieses Material
	 * @return MaterialGrafics von diesem Material
	 */
	public MaterialGrafics getGrafics() {
		return grafics;
	}


	public static Material WASSER = new Material("Wasser", javafx.scene.paint.Color.BLUE);
	public static Material ERDE = new Material("Erde", javafx.scene.paint.Color.PERU);
	public static Material STEIN = new Material("Stein", javafx.scene.paint.Color.DARKGRAY, 100f);
	public static Material GOLD = new Material("Gold", javafx.scene.paint.Color.GOLD, 15f);
	
	static {
		WASSER.setAggregatzustand(Aggregat.FLUESSIG);
		ERDE.setAggregatzustand(Aggregat.FEST);
		STEIN.setAggregatzustand(Aggregat.FEST);
		GOLD.setAggregatzustand(Aggregat.FEST);
	}

	@Override
	public String serializeData() {
		String data = "";
		data += Serializable.serializePrimitive("name", name);
		int rgbInt = Math.round((float)color.getOpacity() * 255f) << 6;
		rgbInt = Math.round((float)color.getRed() * 255f) << 4;
		rgbInt |= Math.round((float)color.getGreen() * 255f) << 2;
		rgbInt |= Math.round((float)color.getBlue() * 255f);
		data += Serializable.serializePrimitive("color", "" + rgbInt);	//TODO
		data += Serializable.serializePrimitive("gewicht", "" + gewicht);
		data += Serializable.serializePrimitive("bodenVorkommen", "" + bodenVorkommen);
		data += Serializable.serializePrimitive("aggregatzustand", aggregatzustand.toString());
		return data;
	}

	@Override
	public void deserializeDataFrom(String objectString) {
		this.name = Serializable.findValue(objectString, "name");
		try {
			int rgbInt = Integer.parseInt(Serializable.findValue(objectString, "color"));
			int opacity = (rgbInt >> 24) & 0x000000FF;
			int red = (rgbInt >> 16) & 0x000000FF;
			int green = (rgbInt >> 8) & 0x000000FF;
			int blue = rgbInt & 0x000000FF;
			this.color = Color.rgb(red, green, blue, opacity / 255.0);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("In dem Abschnitt " + objectString + " ist der Wert für color fehlerhaft werden.");
		}
		try {
			this.gewicht = Float.parseFloat(Serializable.findValue(objectString, "gewicht"));
		} catch (NumberFormatException e) {
			throw new NumberFormatException("In dem Abschnitt " + objectString + " ist der Wert für gewicht fehlerhaft werden.");			
		}
		try {
			this.bodenVorkommen = Float.parseFloat(Serializable.findValue(objectString, "bodenVorkommen"));
		} catch (NumberFormatException e) {
			throw new NumberFormatException("In dem Abschnitt " + objectString + " ist der Wert für bodenVorkommen fehlerhaft werden.");			
		}
		//aggregat TODO
	}
}
