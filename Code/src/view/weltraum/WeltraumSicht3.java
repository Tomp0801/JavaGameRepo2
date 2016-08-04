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
		kamera = new Kamera(scene);
//		kamera.setPosition(sektion.getSize()/2, sektion.getSize()/2, sektion.getSize()/2);
		kamera.setPosition(50, 50, -200);	
		
		//-------------erstellt-die-SubScene-fuer-die-Kammera--------------------------------------------------//
		subScene = new SubScene(this , 500 , 500, true, null);
		subScene.setFill(Color.BLACK);
		subScene.setCamera(kamera);
		//-------------erstellt-die-Spielumgebung-auf-dieser-befinden-sich-alle-Elemente-der-Sektion-----------//
        spielWelt.setStyle("-fx-background-color:BLACK");   
        spielWelt.setBackground(null);
        this.getChildren().add(spielWelt);

        
        //hier werden die Elemente der Sektion eine Position zugeordnet TODO
//        for (int i = 0 ..i. )
//        spielWelt.getChildren().addAll( erdkugel);

        
        
        

		//---------------------ein Demo Planet zum Testen---------------------------------
        Image image = new Image("view/hauptmenu/Erde.jpg"); 
        PhongMaterial material1 = new PhongMaterial();
        material1.setDiffuseMap(image);
        erdkugel.setMaterial(material1);
        erdkugel.setRadius(40);
        erdkugel.setLayoutX(50);
        erdkugel.setLayoutY(60);
        erdkugel.setTranslateZ(200);

        spielWelt.getChildren().addAll( erdkugel);
        //--------------------------------------------------------------------------------------     

        //Diese Methode wurde in die Kamera ausgelagert
//        initEventScene(scene);
	}
	
	
	//Diese Methode wurde in die Kamera ausgelagert
//	private void initEventScene(Scene scene)
//	{
//		Point positionMaus = new Point(0,0);
//		
//		scene.setOnKeyPressed(new EventHandler<KeyEvent>()
//	        {
//				@Override
//				public void handle(KeyEvent event) 
//				{
//					kamera.doBewegung(event.getCode());	
//				}
//	        });
//	        
//			scene.setOnMousePressed(new EventHandler<MouseEvent>()
//	        {
//
//				@Override
//				public void handle(MouseEvent event) 
//				{
//					if (event.getButton() == MouseButton.PRIMARY)
//					{
//						positionMaus.setLocation(MouseInfo.getPointerInfo().getLocation());
//						kamera.setRotaion(true);
//					}
//				}   	
//	        });
//	        	
//			scene.setOnMouseDragged(new EventHandler<MouseEvent>() 
//	        {
//	        	double mouseOldX = 0; 
//	        	double mouseOldY = 0;
//	        	
//	        	double mousePosX = 0;
//	        	double mousePosY = 0;
//	        	
//	        	double mouseDeltaX = 0;
//	        	double mouseDeltaY = 0;
//	        	
//				@Override
//				public void handle(MouseEvent event) 
//				{
//		        	mouseOldX = mousePosX;
//	                mouseOldY = mousePosY;
//	                mousePosX = MouseInfo.getPointerInfo().getLocation().getX();
//	                mousePosY = MouseInfo.getPointerInfo().getLocation().getY();
//	                
//	                mouseDeltaX = mousePosX - mouseOldX;
//	                mouseDeltaY = mousePosY - mouseOldY;
//	                   
//	                if (mouseDeltaX >= 5 || mouseDeltaX <= -5)
//	                {
//	                	mouseDeltaX = 0;
//	                }
//	                if (mouseDeltaY >= 5 || mouseDeltaY <= -5  )
//	                {
//	                	mouseDeltaY = 0;
//	                }
//	                
//					kamera.rotation(mouseDeltaX , mouseDeltaY );
//				}
//			});
//	        
//			scene.setOnMouseReleased(new EventHandler<MouseEvent>()
//	        {
//				@Override
//				public void handle(MouseEvent event) 
//				{
//					if (event.getButton() == MouseButton.PRIMARY)
//					{
//						kamera.setRotaion(false);
//					}
//				}   	
//	        });        
//	}
	
	
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
