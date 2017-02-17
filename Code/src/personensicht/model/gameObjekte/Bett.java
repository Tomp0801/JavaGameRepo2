package personensicht.model.gameObjekte;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import personensicht.model.aktionen.AktionSchlafen;
import personensicht.view.gameObjekte.BettV;

public class Bett extends GameObjekt
{	
	private int erholungswert = 10;
	private Color color;
	
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

	/**
	 * lead die Node, die ein Bett darstellt
	 */
	@Override
	public Node ladeNodeObjekt()
	{
		this.setNodeObjekt(new BettV(this)); 
		return this.getNodeObjekt().getNode();
	}

	@Override
	public Node ladeNodeObjekt(int localX, int localY) 
	{
		return null;
	}

	public void setColor(Color color) 
	{
		this.color = color; 
		if (this.getNodeObjekt() != null)
		{
			BettV bettV = (BettV) this.getNodeObjekt();
			bettV.setColor(color);
		}
	}
}
