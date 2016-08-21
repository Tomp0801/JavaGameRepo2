package view.weltraum;

import java.io.IOException;
import java.util.Vector;

import com.oracle.jrockit.jfr.DataType;

import controller.GameManager;
import global.Constants;
import global.GameTime;
import himmelskoerper.FestPlanet;
import himmelskoerper.GasPlanet;
import himmelskoerper.Himmelskoerper;
import himmelskoerper.InOrbit;
import himmelskoerper.Mond;
import himmelskoerper.Orbitable;
import himmelskoerper.Planet;
import himmelskoerper.SchwarzesLoch;
import himmelskoerper.Stern;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import view.weltraum.fxml.SpielUmgebungController;

/**
 * In dieser Sicht, werden die Planeten und die Sonnen einer Sektion ohne ihre Monde angezeigt.
 *  Flotten die vom einem Spieler erstellt wurden werden ebenfalls gezeigt. 
 * 
 * @author Dennis
 */
public class WeltraumSicht //extends StackPane
{
	/**
	 * eine Kamera, die der Spieler steuern kann um sich auf dem Spielfeld zubewegen 
	 */
	private Kamera kamera;
	
	/**
	 * Auf dieser AnchoPane befindet sich die Spielwelt. 
	 * Alle Elemente (Planete,Raumschiffe,Sonnen und weiteres befinden sich auf der spielWelt
	 */
	//private AnchorPane spielWelt = new AnchorPane(); 
	
	/**
	 * dieser SubScene wird eine Kamera zugeordnet. 
	 */
	private SubScene subScene;
	
	/**
	 * der Controller der zu dieser ViewKlasse gehoert. 
	 */
	private WeltraumSichtController controller = new WeltraumSichtController(); 
	
	private Group subSceneRoot = new Group();
	private Scene scene ; 
	
	/**
	 * Erstellt eine Objekt von Weltrumsicht
	 * 
	 * @param zentrumGalaxis uebergibt eine Galaxi die dargestellt werden soll
	 * @param scene uebergibt die Scene auf der sich die Weltraumsich befinden wird, um dies der Kamera mitzuseilen. Dies wird 
	 * zur steuerung der Kamera verwendet
	 * @param positionKamera uebergibt die Position der Kamera in einem Vector<Double> index 0 => position in X Richtung index 1 => position in Y Richtung, index 2 => position in Z Richtung
	 */
	public WeltraumSicht(SchwarzesLoch zentrumGalaxis, Point3D positionKamera )
	{   	
		initScene();
		kamera = new Kamera(scene, positionKamera);	
		subScene.setCamera(kamera);	         
        //---------Hier----werden-----die----Sterne----der----Galaxie----geladen---------------------------    
        zeichenSystem(zentrumGalaxis); 
     }

	
	private void initScene()
	{
		//-------------erstellt-die-SubScene-fuer-die-Kammera--------------------------------------------------//
		subScene = new SubScene(subSceneRoot , 500 , 500, true, SceneAntialiasing.BALANCED);
		subScene.setFill(Color.BLACK); 
		subScene.setDepthTest(DepthTest.ENABLE);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/weltraum/fxml/SpielUmgebung.fxml"));
		try{loader.load();}catch (IOException e){e.printStackTrace();}	
		SpielUmgebungController controller = loader.getController();
		scene = new Scene(loader.getRoot() , 800 , 800 , true, SceneAntialiasing.BALANCED);
		controller.wechsleZentrum(subScene);
		
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
	 * Gibt die Weltraumsicht szene wieder
	 * @return scene
	 */
	public Scene getScene()
	{
		return scene;
	}
	
	/**
	 * ueberprueft ob ein Object sich im Sichtfeld der Kamera befindet, also in dem Bereich in dem die Objekte geladen werden die sich im Orbit aufhalten,
	 * @param orbitObject
	 * @return true wenn sich das Object im Sichtfeld der Kamera befindet
	 */
//	protected Boolean isImSichtfeld(InOrbit orbitObject)
//	{
//		//berechned die enternung vom Object zur Kamera 
//		Point3D point1 = new Point3D(kamera.getPosition().getX(), kamera.getPosition().getY() , kamera.getPosition().getZ());
////		Point3D point2 = orbitObject.getAbsolutePosition().getAbsolutePosition().getY() ,orbitObject.getAbsolutePosition().getZ());
//		
////		double entfernung = point1.distance(point2);
//
//		if (entfernung <= orbitObject.getOrbitRadius()*2)
//		{
//			return true;
//		}
//		else
//		{
//			return false;
//		}
//	}
	
	
	//TODO
	int Z = 0;
	/**
	 * zeichnet alle Elemente die sich in einem Orbitable Objekt befinden
	 * @param zentrum
	 */
	protected void zeichenSystem(Orbitable zentrum)
	{
		double vergroﬂerungRadius = 1;
		for (int j = 0; zentrum.getChildren().size() > j ; j++)
		{
			//Passt die groeﬂer der Planeten an
			if (zentrum.getChild(j).getClass() == Stern.class)
			{
				System.out.println("Sonnne"+Constants.VERGROﬂERUNGSFAKTORRADIUSSONNE);
				vergroﬂerungRadius = Constants.VERGROﬂERUNGSFAKTORRADIUSSONNE;
			}
			else if (zentrum.getChild(j).getClass() == Mond.class)
			{
				System.out.println("Mond"+Constants.VERGROﬂERUNGSFAKTORRADIUSMOND);
				vergroﬂerungRadius = Constants.VERGROﬂERUNGSFAKTORRADIUSMOND;
			}
			else if (zentrum.getChild(j).getClass() == FestPlanet.class || zentrum.getChild(j).getClass() == GasPlanet.class)
			{
				System.out.println("Planet"+Constants.VERGROﬂERUNGSFAKTORRADIUSPLANET);
				vergroﬂerungRadius = Constants.VERGROﬂERUNGSFAKTORRADIUSPLANET;
			}
						
   	 		//erstelle einen Himmerlskoerper
   	 		Sphere himmelskoerper = new Sphere();
   	 		himmelskoerper.setRadius(zentrum.getChild(j).getRadius()*Constants.VERKLEINERUNGSFAKTOR*vergroﬂerungRadius);
   	        subSceneRoot.getChildren().add(himmelskoerper);	
   	        GameManager.getInstance().addInOrbitObjectToPositionsRechner(zentrum.getChild(j));  	        
   	        himmelskoerper.translateXProperty().bind(zentrum.getChild(j).getPositionAbsoluteProperty()[0]);
   	        himmelskoerper.translateYProperty().bind(zentrum.getChild(j).getPositionAbsoluteProperty()[1]);
   	        himmelskoerper.translateZProperty().bind(zentrum.getChild(j).getPositionAbsoluteProperty()[2]);	

   	        Z++;
   	        System.out.println(Z+          "Postion des Kˆrpers:   X: "+himmelskoerper.getTranslateX()+"   Y: "+himmelskoerper.getTranslateY()+"   Z: "+himmelskoerper.getTranslateZ()+"    Radius: "+himmelskoerper.getRadius());
   	        
   	        //setzt das aussehen der Kugel
	        himmelskoerper.setMaterial(zentrum.getChild(j).getAussehn());
	        
	        //macht den Himelskoerper anklickbar
	        himmelskoerper.setOnMouseClicked(new EventHandler<MouseEvent>()
	        {
				@Override
				public void handle(MouseEvent event) 
				{					
					if (event.getClickCount() == 2)
					{
						//TODO Lade Karte falls es eine gibt
					}
					else
					{
						// TODO lade informationen vom koerper
					}
				}
	        });     
	        
	        //TODO Demo zum zeichnen aller Planeten und Monde
	        if (zentrum.getChild(j).getClass() != Mond.class)
		    {	
		        Orbitable demoK = (Orbitable) zentrum.getChild(j);
		        if (demoK.getChildren().size() > 0)
		        {
		        	zeichenSystem(demoK);
		        }
	        }	    
	        
	        //TODO eine kleine Begrenzung
	        if (Z > 200)
		    {
	        	break;
		    }
   	 	}
	}
	
	
	public void zeichneInOrbitSystem()
	{
		//TODO
	}
}
