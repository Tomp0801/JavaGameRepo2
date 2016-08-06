package view.weltraum;

import java.awt.MouseInfo;
import java.awt.Point;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
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
public class WeltraumSicht3 extends StackPane
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
	
//	private Sonnensystem[] sonnensystem; TODO
	
	/**
	 * erstelle ein Objekt dieser Sicht
	 * 
	 * @param sektion
	 * @param scene
	 */
	public WeltraumSicht3(Sektion sektion, Scene scene)
	{   
		System.out.println("Sind in der Klasse WeltraumSicht3");
		System.out.println("Ist die übergebende Scene = null?   "  +(scene==null));
		kamera = new Kamera(scene);
//		kamera.setPosition(sektion.getSize()/2, sektion.getSize()/2, sektion.getSize()/2);
		kamera.setPosition(50, 50, -200);	
		System.out.println("Die position der Kamera: (X/Y/Z)"+kamera.getTranslateX()+" / "+ kamera.getTranslateY()+ "/ " + kamera.getTranslateZ());
		
		//-------------erstellt-die-SubScene-fuer-die-Kammera--------------------------------------------------//
		subScene = new SubScene(this , 500 , 500, true, null);
		System.out.println("Größe der SubScene (X/Y):   "+subScene.getWidth()+"/" + subScene.getHeight());
		subScene.setFill(Color.BLACK); 
		System.out.println("ist die SubScen = null??  "+(subScene == null));
		subScene.setCamera(kamera);
		System.out.println("Ist die Kamera der SubScene = null?   "+(subScene.getCamera()==null));
		
		//-------------erstellt-die-Spielumgebung-auf-dieser-befinden-sich-alle-Elemente-der-Sektion-----------//
        spielWelt.setStyle("-fx-background-color: BLACK; -fx-box-border: transparent;");   
        System.out.println("Styl der SpielWelt:       "+spielWelt.getStyle());
        System.out.println("Stylder subScene:    "+ subScene.getStyle());
//       spielWelt.setBackground(null);
        this.getChildren().add(spielWelt);
        System.out.println("Anzahl der Elemente der StackPane von WeltraumSicht3?:    "+this.getChildren().size());
        
        //hier werden die Elemente der Sektion eine Position zugeordnet TODO
//        for (int i = 0 ..i. )
//        spielWelt.getChildren().addAll( erdkugel);

        
        
        

		//---------------------ein Demo Planet zum Testen---------------------------------
        Image image = new Image("view/hauptmenu/Erde.jpg"); 
        System.out.println("Bild der Erde = null?    "+(image == null));
        PhongMaterial material1 = new PhongMaterial();
        material1.setDiffuseMap(image);
        erdkugel.setMaterial(material1);
        erdkugel.setRadius(40);
        erdkugel.setLayoutX(50);
        erdkugel.setLayoutY(60);
        erdkugel.setTranslateZ(200);
        erdkugel.toFront();
        System.out.println("Position des Planeten (X/Y/Z/Radius):      "+erdkugel.getTranslateX()+"/"+erdkugel.getTranslateY()+"/"+erdkugel.getTranslateZ()+"/"+erdkugel.getRadius());
        spielWelt.getChildren().addAll( erdkugel);     
        System.out.println("Die Menge der Elemente die sich auf den Spielfeld befinden:  "+spielWelt.getChildren().size());
	}
	
	
	/**
	 * 
	 * @return gibt die Kamerea dieser Sicht
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
}
