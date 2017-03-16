package personensicht.model.gameObjekte;

import java.io.Serializable;

import javafx.scene.Node;
import personensicht.view.gameObjekte.MauerV;

public class Mauer extends GameObjekt implements Serializable
{

	public Mauer() 
	{
		super(GameObjektType.Mauer,50,50,200);
	}

	@Override
	public void refleshAktionsListe() {	
	}

	@Override
	public Node ladeNodeObjekt() {
		System.out.println("die Node wird geladen");
		this.setNodeObjekt(new MauerV(this)); 
		return this.getNodeObjekt().getNode();
	}

	@Override
	public void serializ() {
		this.setLayoutXSerializ(this.getLayoutX().get());
		this.setLayoutYSerializ(this.getLayoutY().get());
		this.setWidthSerializ(this.getWidth().get());
		this.setHeightSerializ(this.getHeight().get());
		this.setDepthSerializ(this.getDepth().get());
	}

	@Override
	public void deserializ() {
		this.setLayoutX(this.getLayoutXSerializ());
		this.setLayoutY(this.getLayoutYSerializ());
		this.setWidth(this.getWidthSerializ());
		this.setHeight(this.getHeightSerializ());
		this.setDepth(this.getDepthSerializ());
	}
}
