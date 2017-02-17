package personensicht.view.welt.map;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import personensicht.model.welt.map.Region;

public class RegionV extends AnchorPane
{
	/**
	 * verweist auf die Obejkte, die sich in diser Region befinden. 
	 * Diese Liste wird verwendte um zum Beispiel Kollision zu erkennen. 
	 */
	private ArrayList<Node> objektListe = new ArrayList<Node>();
	
	/**
	 * zeigt auf die Region, die dargestellt wird
	 */
	private Region region; 
	
	public RegionV(Region region)
	{
		this.region = region;
		
		for (int i = 0; region.getObjektListe().size() > i; i++)
		{
			//wenn der Platz frei ist, dann setze die Node
			if (isPlaceFree(region.getObjektListe().get(i).getNodeObjekt().getNode()))
			{
				this.getChildren().add(region.getObjektListe().get(i).ladeNodeObjekt(region.getPositionDerObjekteInDerListe().get(i)[0] ,region.getPositionDerObjekteInDerListe().get(i)[1] ));
			}
			else
			{
				System.err.println("Der Platz für "+region.getObjektListe().get(i)+" "+i+" ist belegt");
			}
		}
	}
	
	/**
	 * fragt ab ob der Platz, von einer andern Node bereit belegt ist
	 *  @return false, wenn der Platz blegt ist
	 */
	public Boolean isPlaceFree(Node node)
	{
		Boolean antwort = true; 
		
		for (int i = 0 ; objektListe.size() > i ;i++)
		{
			if (objektListe.get(0).intersects(node.getBoundsInLocal()))
			{
				antwort = false; 
				break; 
			}
		}
		return antwort; 
	}
}