package personensicht.model.gameObjekte;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import personensicht.model.gameObjekte.software.ComputerSoftware;

/**
 * TODO Diese untenstehenden Funktionen koennen hinzugefugt werde. Nicht alle sind sinvoll. 
 * Ich fuege das hinzu auf das ich grade lust habe. 
 * 
 * 1. Liste mit Softwarekomponenten
 * 2. Betriebssysetem
 * @author Dennis
 *
 */
public class Computer extends GameObjekt
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2758050643717478478L;

	/**
	 * size zum Speichern bzw. laden (width, height, depht, layoutX, LayoutY, LayoutZ)
	 */
	private int sizeSerializ[] = new int[6];
	
	/**
	 * size (width, height, depht, layoutX, LayoutY, LayoutZ)
	 */
	private SimpleIntegerProperty size[] = new SimpleIntegerProperty[6];
	
	/**
	 * Eine Liste von Softwarekomponenten, die dieser PC verwendet. Mehr dazu in ComputerSoftwareKomponent
	 */
	private ArrayList<ComputerSoftware> software = new ArrayList<ComputerSoftware>();
	
	public Computer() 
	{
		super(GameObjektType.Computer);
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

	@Override
	public void serializ() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deserializ() {
		// TODO Auto-generated method stub	
	}
}
