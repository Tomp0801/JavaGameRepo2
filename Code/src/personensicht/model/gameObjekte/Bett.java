package personensicht.model.gameObjekte;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import personensicht.model.aktionen.AktionSchlafen;
import personensicht.view.gameObjekte.BettV;

public class Bett extends GameObjekt
{	
	private int erholungswert = 10;
	private Color color; //testen, kann eventuell nicht mit gespeichert werden
	
	public Bett()
	{
		super(GameObjektType.Bett, 50 ,50 ,50);
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

	public void setColor(Color color) 
	{
		this.color = color; 
		if (this.ladeNodeObjekt() != null)
		{
			BettV bettV = (BettV) this.getNodeObjekt();
			bettV.setColor(color);
		}
	}

	public synchronized Color getColor() {
		return color;
	}

	@Override
	public void serializ() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deserializ() {
		// TODO Auto-generated method stub
		
	}
}
