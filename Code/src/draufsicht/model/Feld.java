package draufsicht.model;

import java.util.HashMap;

import draufsicht.view.FeldGrafics;
import spielmodel.ressource.model.Material;

/**
 * Ein Feld einer Karte
 * 
 * @author Thomas
 *
 */
public class Feld implements PlaceableOnThis, Versorger {

	/**
	 * Objekt, das auf diesem Feld steht
	 */
	private Placeable object;
	
	/**
	 * Karte, zu der dieses  Feld gehört
	 */
	private Map karte;
	
	/**
	 * x-Koordinate dieses Feldes auf der parent Karte
	 */
	private int x;
	
	/**
	 * y-Koordinate dieses Feldes auf der parent Karte
	 */
	private int y;
	
	/**
	 * Die Grafik des Feldes
	 */
	private FeldGrafics grafics;
	
	/**
	 * Das BodenMaterial des Feldes
	 */
	private Material boden;
	
	/**
	 * Auflistung der Rohstoffe und deren Menge, die das Feld enthält
	 */
	private HashMap<Material, Float> bodenschatzVorkommen;
		
	/**
	 * Erstellt ein Feld, das zu der angegebenen Karte gehört
	 * @param parent
	 * @param x x-Position des Felds
	 * @param y y-Position des Felds
	 * @throws IllegalArgumentException, wenn die übergebene Karte null ist, oder dessen Grafik-Objekt null ist
	 */
	public Feld(Map parent, int x, int y, Material grundMaterial) throws IllegalArgumentException {
		if (parent == null) throw new IllegalArgumentException("Das übergebene Map-Objekt darf nicht null sein");
		if (parent.getGrafics() == null) throw new IllegalArgumentException("Das übergebene Map-Objekt muss ein gültiges Grafik-Objekt besitzen");
		
		this.karte = parent;
		this.x = x;
		this.y = y;
		this.boden = grundMaterial;
		
		if (grundMaterial.isFest()) {
			generateBodenschaetze();
		}
		
		grafics = new FeldGrafics(this);
	}
	
	private void generateBodenschaetze() {
		bodenschatzVorkommen = new HashMap<Material, Float>();
		bodenschatzVorkommen.put(boden, 100f);		//Bodenmaterial ist immer verfügbar
		
		HashMap<Material, Float> moeglichkeiten = karte.getBodenschaetze();
		
		//je nach Wahrscheinlichkeit, andere Bodenschätze hinzufügen
		for (Material m : moeglichkeiten.keySet()) {
			if (moeglichkeiten.get(m) > karte.getPrng().random(0, 100)) {
				bodenschatzVorkommen.put(m, m.getBodenVorkommen());
			}
		}
	}
	
	/**
	 * entfernt das aktuell auf diesem Feld befindliche Objekt von diesem Feld
	 */
	public void removeObject() {
		this.object = null;
	}
	
	/**
	 * Platziert das angegebene Objekt auf diesem Feld, wenn das Feld nicht schon besetzt ist
	 * @param object Objekt, das auf diesem Feld platziert werden soll
	 *
	 * @throws IllegalStateException wenn bereits ein Objekt auf dem Feld platziert ist
	 */
	public void place(Placeable object) throws IllegalStateException {
		if (this.object != null) throw new IllegalStateException("Es steht bereits ein Objekt auf dem Feld.");
		this.object = object;
		this.grafics.getChildren().add(object.getGrafics());
	}
	
	/**
	 * Gibt die Karte wieder, zu der dieses Feld gehört
	 * @return die Karte, zu der dieses Feld gehört
	 */
	public Map getKarte() {
		return karte;
	}
	
	/**
	 * gibt die x-Koordinate dieses Feldes auf der parent Karte zurück
	 * @return die x-Koordinate dieses Feldes auf der parent Karte
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * gibt die y-Koordinate dieses Feldes auf der parent Karte zurück
	 * @return die y-Koordinate dieses Feldes auf der parent Karte
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Gibt den Node zurück, der in einer Scene gezeichnet werden kann
	 * @return Node (Rectangle Objekt)
	 */
	public FeldGrafics getGrafics() {
		return grafics;
	}
	
	/**
	 * Gibt zurück, aus welchem Material der Boden auf diesem Feld besteht
	 * @return Das Bodenmaterial des Feldes
	 */
	public Material getBodenMaterial() {
		return boden;
	}
	
	/**
	 * Gibt das Nachbarfeld zurück
	 * @param richtung Himmelsrichtung in der das Nachbarfeld liegt
	 * @return Das nachbarfeld
	 */
	public Feld getNachbar(Kompass richtung) throws ArrayIndexOutOfBoundsException {
		switch (richtung) {
		case NORTH:
			return karte.getFeld(x, y - 1);
		case EAST:
			return karte.getFeld(x + 1, y);
		case SOUTH:
			return karte.getFeld(x, y + 1);
		case WEST:
			return karte.getFeld(x - 1, y);
		case NORTHEAST:
			return karte.getFeld(x + 1, y - 1);
		case SOUTHEAST:
			return karte.getFeld(x + 1, y + 1);
		case NORTHWEST:
			return karte.getFeld(x - 1, y - 1);
		case SOUTHWEST:
			return karte.getFeld(x - 1, y + 1);
		default:
			return null;
		}
	}
	
	/**
	 * Gibt das Objekt zurück, das auf diesem Feld steht
	 * @return das Objekt, das auf diesem Feld steht
	 */
	public Placeable getObject() {
		return this.object;
	}
	
	public HashMap<Material, Float> getBodenschatzVorkommen() {
		return bodenschatzVorkommen;
	}

	@Override
	public float getMaterialMenge(Material material) {
		if (bodenschatzVorkommen.get(material) != null) {
			return bodenschatzVorkommen.get(material);
		} else {
			return 0;	
		}
	}

	@Override
	public void setMaterialMenge(Material material, float menge) {
		if (bodenschatzVorkommen.get(material) != null) {
			bodenschatzVorkommen.replace(material, menge);
		}
	}



}