package view.weltraum;

import java.util.ArrayList;
import java.util.Vector;

import com.sun.javafx.geom.Vec3d;
import com.sun.org.apache.xalan.internal.utils.XMLSecurityPropertyManager.Property;
import global.Constants;
import himmelskoerper.Himmelskoerper;
import himmelskoerper.InOrbit;
import himmelskoerper.Planet;
import himmelskoerper.SchwarzesLoch;
import himmelskoerper.Stern;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import map.Sektion;

/**
 * In dieser Sicht, werden die Planeten und die Sonnen einer Sektion ohne ihre Monde angezeigt.
 *  Flotten die vom einem Spieler erstellt wurden werden ebenfalls gezeigt. 
 * 
 * @author Dennis
 */
public class WeltraumSicht extends StackPane
{
	/**
	 * eine Kamera zum steuern der Sicht
	 */
	private Kamera kamera;
	
	/**
	 * Auf dieser AnchoPane befindet sich die Spielwelt. 
	 * Alle Elemente (Planete,Raumschiffe,Sonnen und weiteres befinden sich auf der spielWelt
	 */
	private AnchorPane spielWelt = new AnchorPane(); 
	
	private Sphere erdkugel = new Sphere(); //Zum testen

	/**
	 * dieser SubScene wird die Kamera zugeordnet. 
	 */
	private SubScene subScene;
	
	/**
	 * der Controller der zu dieser ViewKlasse gehoert
	 */
	private WeltraumSichtController controller = new WeltraumSichtController(); 
	
//	private Sonnensystem[] sonnensystem; TODO
	
	
	/**
	 * Erstellt eine Objekt von Weltrumsicht
	 * 
	 * @param zentrumGalaxis uebergibt eine Galaxi die dargestellt werden soll
	 * @param scene uebergibt die Scene auf der sich die Weltraumsich befinden wird, um dies der Kamera mitzuseilen. Dies wird 
	 * zur steuerung der Kamera verwendet
	 * @param positionKamera uebergibt die Position der Kamera in einem Vector<Double> index 0 => position in X Richtung index 1 => position in Y Richtung, index 2 => position in Z Richtung
	 */
	public WeltraumSicht(SchwarzesLoch zentrumGalaxis, Scene scene, Vector<Double> positionKamera )
	{   	
		kamera = new Kamera(scene, positionKamera);
		
		//-------------erstellt-die-SubScene-fuer-die-Kammera--------------------------------------------------//
		subScene = new SubScene(this , 500 , 500, true, null);
		subScene.setFill(Color.BLACK); 
		subScene.setCamera(kamera);
				
		//-------------erstellt-die-Spielumgebung-auf-dieser-befinden-sich-alle-Elemente-die-um-ein-großes-Schwarzesloch-kreisen-----//
        spielWelt.setStyle("-fx-background-color: BLACK; -fx-box-border: transparent;");   
        this.getChildren().add(spielWelt);
           
        //Hier werden die Sterne der Galaxie erstellt
        
//      Stern stern = (Stern) zentrumGalaxis.getChild(0);
        
       	for(int i = 0; zentrumGalaxis.getChildren().size() > i ; i++)
       	{
       		InOrbit objectInGalaxis = zentrumGalaxis.getChild(i);
       		
       		// ist die Sonne wirklich eine Sonne
       		if (objectInGalaxis.getClass() == Stern.class)
       		{
       			Stern stern = (Stern) objectInGalaxis;
       			
	       		Sphere sonne = new Sphere();
	       		//Radius der Sonne
	       		sonne.setRadius(objectInGalaxis.getRadius()/Constants.VERKLEINERUNGSFAKTOR);
	       		//Hier wird die Position der Sonne bestimmt.
	       		sonne.setTranslateX(stern.getPosition().get(0)/Constants.VERKLEINERUNGSFAKTOR);
	       		sonne.setTranslateY(stern.getPosition().get(1)/Constants.VERKLEINERUNGSFAKTOR);
	       		sonne.setTranslateZ(stern.getPosition().get(2)/Constants.VERKLEINERUNGSFAKTOR);
	       		//TODO Bild 
	       		//TODO Leuchteffekt 
	       		//TODO anklickbar. 
	       		//fuegt die Sonne dem Spielfeld hinzu
	       		spielWelt.getChildren().addAll(sonne);
	       		
	       		//befindet sich dieses Element im Sichtfeld der kamera dann
	       		if(isImSichtfeld(stern));
	       		{       
		       	 	zeichenPlanetenVomStern(stern);
	       		}
       	}	
        	//TODO Objekte wie raumschiffe und aehnliches muessen hier erstellt werden

		//---------------------ein Demo Planet zum Testen----------TODO-----------------------
//        Image image = new Image("view/hauptmenu/Erde.jpg"); 
//        System.out.println("Bild der Erde = null?    "+(image == null));
//        PhongMaterial material1 = new PhongMaterial();
//        material1.setDiffuseMap(image);
//        erdkugel.setMaterial(material1);
//        erdkugel.setRadius(100);
//        erdkugel.setLayoutX(50);
//        erdkugel.setLayoutY(60);
//        erdkugel.setTranslateZ(200);
//        erdkugel.toFront();
//        spielWelt.getChildren().addAll( erdkugel);     
       	}
       }
	
	
	/**
	 * 
	 * @return gibt die Kamerea der Spielwelt zurueck 
	 */
	public Kamera getKamera()
	{
		return kamera;
	}
	
	
	/**
	 * gibt die SubScene der Sicht.
	 */
	public SubScene getSubScene()
	{
		return subScene;
	}
	
	
	
	/**
	 * ueberprueft ob ein Object sich im Sichtfeld der Kamera befindet, also in dem Bereich in dem die Objekte geladen werden die sich im Orbit aufhalten,
	 * @param orbitObject
	 * @return true wenn sich das Object im Sichtfeld der Kamera befindet
	 */
	protected Boolean isImSichtfeld(InOrbit orbitObject)
	{
		//berechned die enternung vom Object zur Kamera 
		Point3D point1 = new Point3D(kamera.getPosition().get(0), kamera.getPosition().get(1) , kamera.getPosition().get(2));
		Point3D point2 = new Point3D(orbitObject.getPosition().get(0) ,orbitObject.getPosition().get(1) ,orbitObject.getPosition().get(2));
		
		double entfernung = point1.distance(point2);

		if (entfernung <= orbitObject.getOrbitRadius()*2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	protected void zeichenPlanetenVomStern(Stern stern)
	{
		for (int j = 0; stern.getChildren().size() > j ; j++)
		{
   	 		//fuege die Planeten des Sternes hinzu
   	 		Sphere planet = new Sphere();
   	 		
   	 		planet.setRadius(stern.getChild(j).getRadius()/1000);
   	 		planet.setTranslateX(stern.getChild(j).getPosition().get(0)/1000);
   	 		planet.setTranslateY(stern.getChild(j).getPosition().get(1)/1000);
   	 		planet.setTranslateZ(stern.getChild(j).getPosition().get(2)/1000);
   	 		
   	 		
   	 		spielWelt.getChildren().addAll(planet);
   	 		//TODO unterscheidung von den Obejkten die um die Sonne kreisen mit einer switch case abfrage
   	 		//Eventell in einem Himelskoerper einen Enum mit einem Typ speichern, dieser zeigt dann an was es fuer ein Typ ist
   	 		// mit einer Getter methode kann dies dann abgefragt werden
   	   		//TODO Bild 
       		//TODO Leuchteffekt 
       		//TOO anklickbar.
//     	 		Planet planet = sektion.getStern().get(i).getChildren().get(j);
   	 	}
	}
}
