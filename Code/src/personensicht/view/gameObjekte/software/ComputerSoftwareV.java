package personensicht.view.gameObjekte.software;

import javafx.scene.Node;
import personensicht.model.gameObjekte.software.ComputerSoftware;

public abstract class ComputerSoftwareV {
	
	public ComputerSoftwareV(ComputerSoftware software) {
	}
	
	public abstract Node getNode();
}
