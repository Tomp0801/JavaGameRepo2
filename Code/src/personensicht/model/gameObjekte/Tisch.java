package personensicht.model.gameObjekte;

import javafx.scene.Node;
import personensicht.view.gameObjekte.TischV;

public class Tisch extends GameObjekt
{
	/**
	 * Einstellung der groeße der Tischbeine (x,y,z)
	 */
	private int[] tischBeineSize = new int[3];
	
	/**
	 * einstellung der Tischplatte (x,y,z)
	 */
	private int[] tischplatte = new int[3];
	
	
	public Tisch() {
		super(GameObjektType.Tisch);
		
	}

	@Override
	public void refleshAktionsListe() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node ladeNodeObjekt() {
		this.setNodeObjekt(new TischV(this)); 
		return this.getNodeObjekt().getNode();
	}


	public synchronized int[] getTischBeineSize() {
		return tischBeineSize;
	}

	public synchronized int[] getTischplatte() {
		return tischplatte;
	}

	public synchronized void setTischBeineSize(int[] tischBeineSize) {
		this.tischBeineSize = tischBeineSize;
	}

	public synchronized void setTischplatte(int[] tischplatte) {
		this.tischplatte = tischplatte;
	}
	
	public synchronized void setTischBeineSize(int x, int y, int z) {
		//Modeleinstllung
		this.tischBeineSize[0] = x;
		this.tischBeineSize[1] = y;
		this.tischBeineSize[2] = z;
		//ViewEinstllung
		if (super.getNodeObjekt() != null){
			TischV tischV = (TischV) super.getNodeObjekt();
			for (int i = 0; tischV.getTischBeine().length > i ; i++){
				tischV.getTischBeine()[i].setWidth(x);
				tischV.getTischBeine()[i].setHeight(y);
				tischV.getTischBeine()[i].setDepth(z);
			}
		}
	}

	public synchronized void setTischplatte(int x, int y, int z) {
		//Modeleinstllung
		
		this.tischplatte[0] = x;
		this.tischplatte[1] = y;
		this.tischplatte[2] = z;
		//ViewEinstllung
		if (super.getNodeObjekt() != null){
			TischV tischV = (TischV) super.getNodeObjekt();
			tischV.getTischplatte().setWidth(x);
			tischV.getTischplatte().setHeight(y);
			tischV.getTischplatte().setDepth(z);
		}
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
