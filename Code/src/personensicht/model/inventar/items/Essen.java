package personensicht.model.inventar.items;

import javafx.scene.Node;
import personensicht.crt.spielablauf.Spielcontroller;
import personensicht.model.aktionen.AktionEssen;
import personensicht.model.aktionen.AktionNehmen;
import personensicht.model.gameObjekte.Inventar;

public class Essen extends Item
{
	private int naehrwert;
	
	public Essen()
	{
		this.setName("Essen");
		this.naehrwert = 10;
		initAktionsListe();
	}
	
	public Essen(String name, int naehrwert)
	{
		this.setName(name);
		this.naehrwert = naehrwert;
		initAktionsListe();	
	}
	
	private void initAktionsListe()
	{
		getAktionen().add(new AktionEssen(naehrwert, this));
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

	
