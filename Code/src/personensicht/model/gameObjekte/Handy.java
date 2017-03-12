package personensicht.model.gameObjekte;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import personensicht.model.gameObjekte.software.HandySoftware;
import personensicht.view.gameObjekte.Item;

public class Handy extends GameObjekt implements Item{

	/**
	 * 
	 */
	private static final long serialVersionUID = 83836568449124241L;

	/**
	 * size zum Speichern bzw. laden (width, height, depht, layoutX, LayoutY, LayoutZ)
	 */
	private int sizeHandySerializ[] = new int[6];
	
	/**
	 * size (width, height, depht, layoutX, LayoutY, LayoutZ)
	 */
	private SimpleIntegerProperty sizeHandy[] = new SimpleIntegerProperty[6];
	
	/**
	 * Software die sich auf dem Handy befindet
	 */
	private ArrayList<HandySoftware> software = new ArrayList<HandySoftware>();
	
	public Handy() {
		super(GameObjektType.Handy);
	}

	@Override
	public void serializ() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deserializ() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node getItemNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refleshAktionsListe() {
		// TODO Auto-generated method stub	
	}

	@Override
	public Node ladeNodeObjekt() {
		// TODO Auto-generated method stub
		return null;
	}
}
