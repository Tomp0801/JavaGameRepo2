package personensicht.model.inventar.items;

import javafx.scene.Node;
import personensicht.crt.spielablauf.Spielcontroller;
import personensicht.model.aktionen.AktionNehmen;
import personensicht.model.aktionen.AktionTrinken;
import personensicht.model.aktionen.Aktionstyp;

public class Trinken extends Item
{
	private int menge;
	
	public Trinken()
	{
		this.setName("Trinken");
		this.menge = 10;
		initAktionsListe();
	}
	
	public Trinken(String name, int menge)
	{
		this.setName(name);
		this.menge = menge;
		initAktionsListe();	
	}
	
	private void initAktionsListe()
	{
		getAktionen().add(new AktionTrinken(menge, this));
		if (Spielcontroller.getInstance().getSpieler().getInventar().existItem(this) == false)
		{
			getAktionen().add(new AktionNehmen(this));
		}
	}

	@Override
	public void refleshAktionsListe() 
	{
		this.getAktionen().clear();
		initAktionsListe();	
	}

	@Override
	public Node ladeNodeObjekt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node ladeNodeObjekt(int localX, int localY) {
		// TODO Auto-generated method stub
		return null;
	}
}
