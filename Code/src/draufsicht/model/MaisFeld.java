package draufsicht.model;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import spielmodel.ressource.model.Material;

public class MaisFeld extends RunningObject implements Placeable, Versorger {

	private float maisProSekunde;
	
	private Feld feld;
	
	private Node grafics;
	
	public MaisFeld() {
		maisProSekunde = 0.01f;
	}
	
	@Override
	public void run() {
		if (isPlaced()) {
			float mais = getPassedTime() * maisProSekunde;
			
			for (Kompass dir : Kompass.values()) {
				try {
					if (feld.getNachbar(dir).getObject() instanceof Verbraucher) {
						Verbraucher lager = (Verbraucher) feld.getNachbar(dir).getObject();
						
					}
				} catch (NullPointerException e) {
					
				}
			}
		}
	}

	@Override
	public PlaceableOnThis getGrund() {
		return feld;
	}

	@Override
	public void place(Feld feld) {
		this.feld = feld;
		feld.place(this);
	}

	@Override
	public void unplace() {
		feld.removeObject();
		this.feld = null;
	}

	@Override
	public Node getGrafics() {
		return grafics;
	}

	@Override
	public String getName() {
		return "Maisfeld";
	}

	@Override
	public float getMaterialMenge(Material material) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaterialMenge(Material material, float menge) {
		// TODO Auto-generated method stub
		
	}

}
