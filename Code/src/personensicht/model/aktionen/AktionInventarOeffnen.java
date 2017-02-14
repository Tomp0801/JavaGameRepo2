package personensicht.model.aktionen;

import java.util.ArrayList;

import personensicht.model.gameObjekte.Inventar;

public class AktionInventarOeffnen extends Aktion
{	
	private ArrayList<Inventar> inventar = new ArrayList<Inventar>(); 
	
	public AktionInventarOeffnen(Inventar inventar)
	{
		super(Aktionstyp.OeffneInventar);
		this.setName("öffnen");
		this.inventar.add(inventar);
	}
	
	public AktionInventarOeffnen(ArrayList<Inventar> inventar)
	{
		super(Aktionstyp.OeffneInventar);
		this.setName("öffnen");
		this.inventar = inventar;
	}
	

	public synchronized ArrayList<Inventar> getInventar() 
	{
		return inventar;
	}
}
