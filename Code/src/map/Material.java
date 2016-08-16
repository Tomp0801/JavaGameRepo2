package map;

import javafx.scene.paint.Color;

/**
 * Eine Materialart mit Eigenschaften und Verhalten
 * Hat einen festen Aggregatzustand
 * z.B. zum bauen
 * 
 * TODO system StoffVeränderung (Schmelzen -> neuer Stoff, etc.)
 * 
 * TODO vielleicht streichen:
 * eLeitfähigkeit, schmelzpubkt, siedepunkt
 * 
 * @author Thomas
 *
 */
public class Material extends Ressource {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9166436242594580896L;

	/**
	 * Dichte des Materials in kg pro m³
	 */
	private float dichte;
	
	/**
	 * Elektrische Leitfähigkeit
	 * TODO einheit
	 */
	private float eLeitFähigkeit;
	
	/**
	 * Schmelzpubkt des Materials in °C
	 */
	private float schmelzpunkt;
	
	/**
	 * siedepunkt des Materials in °C
	 */
	private float siedepunkt;
	
	/**
	 * bezieht sich nur auf das Material in Festem zustand.
	 * TODO einheit
	 */
	private float haerte;
	
	/**
	 * Das Material, das durch Veredelung dieses Materials entsteht
	 */
	private Material veredelErgebnis;
	
	public Material(String name, Color color) 
	{
		super(name, color);
	}

	/**
	 * @return the dichte
	 */
	public float getDichte() {
		return dichte;
	}

	/**
	 * @param dichte the dichte to set
	 */
	protected void setDichte(float dichte) {
		this.dichte = dichte;
	}

	/**
	 * @return the eLeitFähigkeit
	 */
	public float getELeitFähigkeit() {
		return eLeitFähigkeit;
	}

	/**
	 * @param eLeitFähigkeit the eLeitFähigkeit to set
	 */
	protected void setELeitFähigkeit(float eLeitFähigkeit) {
		this.eLeitFähigkeit = eLeitFähigkeit;
	}

	/**
	 * @return the schmelzpunkt
	 */
	public float getSchmelzpunkt() {
		return schmelzpunkt;
	}

	/**
	 * @param schmelzpunkt the schmelzpunkt to set
	 */
	protected void setSchmelzpunkt(float schmelzpunkt) {
		this.schmelzpunkt = schmelzpunkt;
	}

	/**
	 * @return the siedepunkt
	 */
	public float getSiedepunkt() {
		return siedepunkt;
	}

	/**
	 * @param siedepunkt the siedepunkt to set
	 */
	protected void setSiedepunkt(float siedepunkt) {
		this.siedepunkt = siedepunkt;
	}

	/**
	 * @return the haerte
	 */
	public float getHaerte() {
		return haerte;
	}
	
	/**
	 * @param the haerte to set
	 */
	protected void setHaerte(float haerte) {
		this.haerte = haerte;
	}
}
