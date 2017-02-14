package personensicht.model.gameObjekte;

import javafx.scene.Node;
import personensicht.model.aktionen.AktionSchlafen;
import personensicht.view.gameObjekte.BettV;

public class Bett extends GameObjekt
{
	private int erholungswert = 10;
	
	public Bett()
	{
		super(GameObjektType.Bett);
		this.setName("Bett");
		initAktionsListe();
	}
	
	public Bett(int erholungswert)
	{
		this();
		this.erholungswert = erholungswert; 
	}
	
	private void initAktionsListe()
	{
		this.getAktionen().add(new AktionSchlafen(erholungswert));
	}
	
	
	@Override
	public void refleshAktionsListe() 
	{
		
	}

	@Override
	public Node ladeNodeObjekt()
	{
		return new BettV().getNode();
	}

	@Override
	public Node ladeNodeObjekt(int localX, int localY) {
		// TODO Auto-generated method stub
		return null;
	}
}
