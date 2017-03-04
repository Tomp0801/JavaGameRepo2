package karte.model;

import ressource.model.Material;

public interface Versorger {
	public float requestMaterial(Material material, float menge);
}
