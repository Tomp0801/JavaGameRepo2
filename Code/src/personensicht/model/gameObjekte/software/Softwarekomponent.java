package personensicht.model.gameObjekte.software;

import ioHandler.SerializableNew;
import javafx.scene.Node;

/**
 * Eine Softwarekomponent kann auf ein Computer verwendet werden. 
 * Zum Beispiel um Notzien zu speichern, um E-Mail zu lesen ect. 
 * 
 * @author Dennis
 *
 */
public abstract class Softwarekomponent implements SerializableNew{

	//TODO alles auch die View noch
	
	/**
	 * lead die Node der Software. Die Node ist in 2D
	 * @return
	 */
	public abstract Node getNode();
}
