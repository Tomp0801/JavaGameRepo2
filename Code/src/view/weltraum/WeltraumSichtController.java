package view.weltraum;

import java.util.ArrayList;

import himmelskoerper.InOrbit;
import javafx.geometry.Point3D;
import sun.net.www.content.text.plain;

/**
 * Diese Klasse ist eine Controllerklase zur WeltraumSicht. Sie kuemert sich um die Logig zum Beispiel
 * darum welche Elemente dargestellt werden.
 * 
 * @author Dennis
 */
public class WeltraumSichtController 
{
	/**
	 * die Elemente die sich in dieser Liste befinden, werden in der View angezeigt
	 */
	private ArrayList<InOrbit> animationElement = new ArrayList<InOrbit>();  
	
	/**
	 * erstellt eine Instance vom Controller der WeltraumSicht
	 */
	protected WeltraumSichtController()
	{
		
	}
	
	
	/**
	 * fuegt ein InOrbit Object zu die Liste animationElement hinzu. Alle Elemente in dieser Liste werden in der View gezeichnet
	 * @param inOrbitObect
	 */
	protected void addObjectToTheAnimatonList(InOrbit inOrbitObect)
	{
		animationElement.add(inOrbitObect);
	}
	
	
	/**
	 * loescht ein Object von der Animationsliste
	 * @param inOrbitObect
	 */
	protected void delateObjectFromTheAnimationList(InOrbit inOrbitObect)
	{
		
	}
}
