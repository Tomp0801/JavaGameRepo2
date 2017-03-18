package spielmodel.ressource.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import spielctr.controller.Serializable;
import spielmodel.ressource.view.MaterialGrafics;

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
	public static Material WASSER;// = new Material("Wasser", javafx.scene.paint.Color.BLUE);
	public static Material ERDE;// = new Material("Erde", javafx.scene.paint.Color.PERU);
	public static Material STEIN;// = new Material("Stein", javafx.scene.paint.Color.DARKGRAY, 100f);
	public static Material GOLD;// = new Material("Gold", javafx.scene.paint.Color.GOLD, 15f);
	public static ArrayList<Material> materialien = new ArrayList<Material>();
	static {
		initStaticMaterials("src/spielmodel/ressource/model/Materialien.xml");
		
	}
	
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
		this.setColor(color);
		this.bodenVorkommen = 0f;
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
		this.setColor(color);
		this.bodenVorkommen = bodenVorkommen;
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
		getGrafics().getColorProperty().set(color);
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
		if (grafics == null) {
			grafics = new MaterialGrafics(this);
		}
		return grafics;
	}

	public static void initStaticMaterials(String fileName) {
		BufferedReader bReader = null;
		try {
			bReader = new BufferedReader(new FileReader(fileName));
			String fileText = "";
			String line = bReader.readLine();
			
			while (line != null) {
				fileText += line;
				line = bReader.readLine();
			}
			
			materialien = Serializable.deserializeArrayList(fileText, "materialien", new Material());
			
			
			for (Material m : materialien) {
				switch (m.getName()) {
				case "Wasser":
					WASSER = m;
					break;
				case "Stein":
					STEIN = m;
					break;
				case "Gold":
					GOLD = m;
					break;
				case "Erde":
					ERDE = m;
					break;
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bReader != null) {
				try {
					bReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
//	static {
//		WASSER.setAggregatzustand(Aggregat.FLUESSIG);
//		ERDE.setAggregatzustand(Aggregat.FEST);
//		STEIN.setAggregatzustand(Aggregat.FEST);
//		GOLD.setAggregatzustand(Aggregat.FEST);
//	}

	@Override
	public String serializeData() {
		String data = "";
		data += Serializable.serializeValue("name", name);
		int rgbInt = Math.round((float)color.getOpacity() * 255f) << 6;
		rgbInt = Math.round((float)color.getRed() * 255f) << 4;
		rgbInt |= Math.round((float)color.getGreen() * 255f) << 2;
		rgbInt |= Math.round((float)color.getBlue() * 255f);
		data += Serializable.serializeValue("color", "" + rgbInt);	//TODO
		data += Serializable.serializeValue("gewicht", "" + gewicht);
		data += Serializable.serializeValue("bodenVorkommen", "" + bodenVorkommen);
		data += Serializable.serializeValue("aggregatzustand", aggregatzustand.toString());
		return data;
	}

	@Override
	public void deserializeDataFrom(String objectString) {
		this.name = Serializable.findValue(objectString, "name");
		try {
			long rgbInt = Serializable.findLongValue(objectString, "color");
			int opacity = (int) ((rgbInt >> 24) & 0x000000FF);
			int red = (int) ((rgbInt >> 16) & 0x000000FF);
			int green = (int) ((rgbInt >> 8) & 0x000000FF);
			int blue = (int) (rgbInt & 0x000000FF);
			this.setColor(Color.rgb(red, green, blue, opacity / 255.0));
		} catch (NumberFormatException e) {
			throw new NumberFormatException("In dem Abschnitt " + objectString + " ist der Wert für color fehlerhaft werden.");
		}
		try {
			this.gewicht = Serializable.findFloatValue(objectString, "gewicht");
		} catch (NumberFormatException e) {
			throw new NumberFormatException("In dem Abschnitt " + objectString + " ist der Wert für gewicht fehlerhaft werden.");			
		}
		try {
			this.bodenVorkommen = Float.parseFloat(Serializable.findValue(objectString, "bodenVorkommen"));
		} catch (NumberFormatException e) {
			throw new NumberFormatException("In dem Abschnitt " + objectString + " ist der Wert für bodenVorkommen fehlerhaft werden.");			
		}
		this.aggregatzustand = Aggregat.valueOf(Serializable.findValue(objectString, "aggregatzustand"));
	}
	
	@SuppressWarnings("unchecked")
	@Override 
	public Material deepCopy() {
		Material material = new Material();
		
		material.name = this.name;
		material.setColor(this.color);
		material.gewicht = this.gewicht;
		material.bodenVorkommen = this.bodenVorkommen;
		material.aggregatzustand = this.aggregatzustand;
		
		return material;
	}
}
