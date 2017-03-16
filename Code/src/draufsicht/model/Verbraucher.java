package draufsicht.model;

import spielmodel.ressource.model.Material;

public interface Verbraucher {
	
	public float addMaterial(Material material, float menge);
	
	//TODO
	//public float offerMaterial(Material material, float menge);
}
