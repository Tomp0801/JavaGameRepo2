package view.weltraum;

import java.awt.MouseInfo;
import java.awt.Point;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
	private Kamera kamera = new Kamera();
	
	private AnchorPane anchorPane = new AnchorPane(); 
	
	private Canvas hintergrund = new Canvas();
	
	private Sphere erdkugel = new Sphere();
	
	private Scene scene;
	
	int i = 0; 
	
	private SubScene subScene;
	
	
	public WeltraumSicht3(Sektion sektion, Scene scene)
	{   
		this.scene = scene;
		subScene = new SubScene(this , 400 , 400);
		subScene.setCamera(kamera);
		
        //-------------Hintergrund wird erstellt--------------------------------------
        hintergrund.setWidth(sektion.getSize()+50);
        hintergrund.setHeight(sektion.getSize()+50);		
        //Hintergrund wird angemalt
        GraphicsContext graphic = hintergrund.getGraphicsContext2D();
		graphic.setFill(Color.BLACK);
		graphic.fillRect(0, 0, hintergrund.getWidth(), hintergrund.getHeight());
        //----------------------------------------------------------------------------
        
		kamera.setPosition(50, 45, -200);
             
		//---------------------ein Demo Planet zum Testen---------------------------------
        Image image = new Image("view/startMenu/Erde.jpg"); 
        PhongMaterial material1 = new PhongMaterial();
        material1.setDiffuseMap(image);
        erdkugel.setMaterial(material1);
        erdkugel.setRadius(40);
        erdkugel.setLayoutX(50);
        erdkugel.setLayoutY(60);
     
        anchorPane.getChildren().addAll(erdkugel);
      
        this.getChildren().add(anchorPane);

//        scene = new Scene(this);
//        scene.setCamera(kamera);
          
        initEventScene();

	}
	
	
	private void initEventScene()
	{
		Point positionMaus = new Point(0,0);
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>()
	        {
				@Override
				public void handle(KeyEvent event) 
				{
					kamera.doBewegung(event.getCode());	
				}
	        });
	        
			scene.setOnMousePressed(new EventHandler<MouseEvent>()
	        {

				@Override
				public void handle(MouseEvent event) 
				{
					if (event.getButton() == MouseButton.PRIMARY)
					{
						positionMaus.setLocation(MouseInfo.getPointerInfo().getLocation());
						kamera.setRotaion(true);
					}
				}   	
	        });
	        	
			scene.setOnMouseDragged(new EventHandler<MouseEvent>() 
	        {
	        	double mouseOldX = 0; 
	        	double mouseOldY = 0;
	        	
	        	double mousePosX = 0;
	        	double mousePosY = 0;
	        	
	        	double mouseDeltaX = 0;
	        	double mouseDeltaY = 0;
	        	
				@Override
				public void handle(MouseEvent event) 
				{
		        	mouseOldX = mousePosX;
	                mouseOldY = mousePosY;
	                mousePosX = MouseInfo.getPointerInfo().getLocation().getX();
	                mousePosY = MouseInfo.getPointerInfo().getLocation().getY();
	                
	                mouseDeltaX = mousePosX - mouseOldX;
	                mouseDeltaY = mousePosY - mouseOldY;
	                   
	                if (mouseDeltaX >= 5 || mouseDeltaX <= -5)
	                {
	                	mouseDeltaX = 0;
	                }
	                if (mouseDeltaY >= 5 || mouseDeltaY <= -5  )
	                {
	                	mouseDeltaY = 0;
	                }
	                
					kamera.rotation(mouseDeltaX , mouseDeltaY );
				}
			});
	        
			scene.setOnMouseReleased(new EventHandler<MouseEvent>()
	        {
				@Override
				public void handle(MouseEvent event) 
				{
					if (event.getButton() == MouseButton.PRIMARY)
					{
						kamera.setRotaion(false);
					}
				}   	
	        });        
	}
	
	
	public Kamera getKamera()
	{
		return kamera;
	}
	
	
	public SubScene getSceneSicht()
	{
		return subScene;
	}
}
