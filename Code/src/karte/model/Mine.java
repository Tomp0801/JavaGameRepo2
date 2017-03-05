package karte.model;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import karte.view.MineGrafics;
import ressource.model.Material;
/**
 * Ein Gebäude, das Rohstoffe aus Feldern einer Karte abbauen kann
 * Vorraussetzung für das Laufen der Mine sind Arbeitskräfte
 * 
 * @author Thomas
 *
 */
public class Mine extends RunningObject implements Placeable, Verbraucher {
	
	private Feld feld;
	
	private MineGrafics grafics;
	
	private int benoetigteArbeiter;
	
	private int arbeiter;
	
	private Material minbaresMaterial;

	private float maxStauraum;
	
	private float stauraum;
	
	private float materialProSekunde;
	
	/**
	 * Erstellt eine Mine
	 */
	public Mine(Material minbaresMaterial) {
		this.minbaresMaterial = minbaresMaterial;
		maxStauraum = 10f;
		stauraum = 0f;
		benoetigteArbeiter = 10;
		arbeiter = 0;
		materialProSekunde = 1f;
		
		loadGrafics();
	}
	
	private boolean canRun() {
		if (stauraum < maxStauraum) {
			return true;
		} else {
			getPassedTime();		//Zeit updaten		TODO
			return false;
		}
	}
	
	/**
	 * Erstellt ein neues Grafikobjekt das zu dieser Mine passt
	 */
	public void loadGrafics() {
		grafics = new MineGrafics(this);
	}
	
	@Override
	public void run() {
		
		//TODO nur vorläufig gefüllt
		long time = getPassedTime();
				
		//check Umgebung nach Arbeitskräften
		if (canRun()) {
			float gemintesMaterial = time / 1000f * materialProSekunde;
			gemintesMaterial = feld.requestMaterial(this, minbaresMaterial, gemintesMaterial);
			System.out.println("Die Mine hat in " + time + " ms " + gemintesMaterial + " " + this.minbaresMaterial.getName() + " gemint.");
			System.out.println("Im Stauraum der Mine befinden sich " + stauraum + " " + minbaresMaterial.getName());
			System.out.println(minbaresMaterial.getName() + " im Feld: " + this.feld.getMaterialMenge(minbaresMaterial));
		} else {
			System.out.println("Mine ist nicht aktiv");
		}
		
	}


	@Override
	public Group getGrafics() {
		return grafics;
	}

	@Override
	public String getName() {
		return minbaresMaterial.getName() + "mine";
	}

	@Override
	public Feld getFeld() {
		return feld;
	}

	@Override
	public void place(Feld feld) {
		feld.place(this);
		this.feld = feld;
	}

	@Override
	public void place(Map parent, Point2D position) throws ArrayIndexOutOfBoundsException {
		place(parent.getFeld(position));
	}

	@Override
	public void unplace() {
		feld.removeObject();
		feld = null;
	}

	@Override
	public float addMaterial(Material material, float menge) {
		if (material == this.minbaresMaterial) {
			if (stauraum + menge > maxStauraum) {
				menge = maxStauraum - stauraum;
			}
			stauraum += menge;
		}
		return menge;
	}
	
	public Material getMinbaresMaterial() {
		return minbaresMaterial;
	}
	
	public int getBenoetigteArbeiter() {
		return benoetigteArbeiter;
	}

	public int getArbeiter() {
		return arbeiter;
	}

	public float getMaxStauraum() {
		return maxStauraum;
	}

	public float getStauraum() {
		return stauraum;
	}

	public float getMaterialProSekunde() {
		return materialProSekunde;
	}
}
