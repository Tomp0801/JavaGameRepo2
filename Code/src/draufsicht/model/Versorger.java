package draufsicht.model;

import spielmodel.ressource.model.Material;

public interface Versorger {
	
	float getMaterialMenge(Material material);
	
	void setMaterialMenge(Material material, float menge);
	
	public default float requestMaterial(Verbraucher anfrager, Material material, float menge) {
		if (getMaterialMenge(material) != 0) {
			//�berpr�fen ob menge vorhanden, wen nicht, menge neu zuweisen
			if (getMaterialMenge(material) < menge) {
				menge = getMaterialMenge(material);
			}
			
			//menge an anfrager �bergeben, dieser gibt zur�ck, wie viel er aufnehmen kann
			menge = anfrager.addMaterial(material, menge);
			
			//menge an Material updaten
			setMaterialMenge(material, getMaterialMenge(material) - menge);
			return menge;
		}
		return 0;
	}
}
