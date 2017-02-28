package karte.view;

import javafx.scene.Group;
import karte.model.Building;

/**
 * Graphisches Objekt, das als Fixpunkt das Zentrum hat 
 * (soll in der Mitte des Feldes gezeichnet werden)
 * 
 * @author Thomas
 *
 */
public abstract class BuildingGrafics extends Group {
	private Building parent;
	
	public BuildingGrafics(Building parent) {
		this.parent = parent;
	}
	
	public Building getBuilding() {
		return parent;
	}
}
