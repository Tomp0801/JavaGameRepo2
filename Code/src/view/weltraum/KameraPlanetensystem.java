package view.weltraum;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Vector;
import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


public class KameraPlanetensystem extends Kamera2
{
	private double speed = 100; 
	
	/**
	 * wenn sich die Kamera in Rotation befindet, ist isRotation = true
	 */
	private Boolean isRotation = false;
	
	/**
	 * rotations um die Achse 
	 */
	private Rotate rotationY = new Rotate(0, Rotate.Y_AXIS);
	
	/**
	 * rotation um die Z Achse
	 */
	private Rotate rotationX = new Rotate(0, Rotate.X_AXIS);
	
	/**
	 * erstellt eine Kamera
	 * @param scene auf der KeyEvents behandelt werden um die Kamera zubewegen
	 * @param position die Position der Kamera
	 */
	public KameraPlanetensystem(Scene scene)
	{
		//position
		this.getKamera().setTranslateZ(-15000);
		this.getKamera().setTranslateX(-Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2);
		this.getKamera().setTranslateY(-Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
		//legt die Sichtweite fest
		this.getKamera().setFarClip(10000000);
		//maximale naehe
		this.getKamera().setNearClip(0.1);
		this.getKamera().setFieldOfView(35);
		this.getKamera().getTransforms().addAll(rotationY , rotationX);
		initEventScene(scene);
	}
	
	
	/**
	 * behandelt Eents zur bewegung der Kamera
	 * @param scene
	 */
	private void initEventScene(Scene scene)
	{
		Point positionMaus = new Point(0,0);
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>()
	        {
				@Override
				public void handle(KeyEvent event) 
				{
					doBewegung(event.getCode());	
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
						setRotaion(true);
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
	                   
	                if (mouseDeltaX >= 8 || mouseDeltaX <= -8)
	                {
	                	mouseDeltaX = 0;
	                }
	                if (mouseDeltaY >= 5 || mouseDeltaY <= -5  )
	                {
	                	mouseDeltaY = 0;
	                }
	                
					rotation(mouseDeltaX , mouseDeltaY );
				}
			});
	        
			scene.setOnMouseReleased(new EventHandler<MouseEvent>()
	        {
				@Override
				public void handle(MouseEvent event) 
				{
					if (event.getButton() == MouseButton.PRIMARY)
					{
						setRotaion(false);
					}
				}   	
	        });        
	}
	
	/**
	 * gibt die Position der Kamera zuruek
	 * @return
	 */
	public Point3D getPosition()
	{
		return this.getPosition();
	}
	
	
	/**
	 * veraendert die Position der Kamera. 
	 * 
	 * info: soll sich eine richtungskomponente nicht aendern, dann entspricht der Wert 0. 
	 * Beispiel veraenderePosition(10 , 0 , -5)
	 * 
	 * @param pointX in X Richtung
	 * @param pointY in Y Richtung
	 * @param pointZ in Z Richtung
	 */
	public void veraenderePosition(double pointX , double pointY , double pointZ)
	{
		this.setPosition(this.getKamera().getTranslateX() + pointX , this.getKamera().getTranslateY() + pointY, this.getKamera().getTranslateZ() + pointZ);
	}

	
	/**
	 * bewegt die Kamera in eine Bestimte richtung
	 * @param key W: nach vorne / S: zurueck / D: nach Rechts / A: nach Links / E: nach oben / Q: nach unten
	 */
	public void doBewegung(KeyCode key)
	{
		double a = speed*Math.cos(Math.toRadians(90-rotationY.getAngle()));
		double b = speed*Math.sin(Math.toRadians(-rotationX.getAngle()));
		double c = speed*Math.sin(Math.toRadians(90-rotationY.getAngle()));
		
		switch (key)
		{
		case W: 
			veraenderePosition(a, b, c);
			break;
		case S: 
			veraenderePosition(-a, -b, -c);
			break;
		case D: 
			veraenderePosition(c, 0, -a);
			break;
		case A: 
			veraenderePosition(-c, 0, a);
			break;
		case E: 
			veraenderePosition(0 , speed, 0);
			break;
		case Q: 
			veraenderePosition(0, -speed, 0);
			break;
			
		case NUMPAD1:
			if (speed < 90)
			speed = speed+ 10;
			break;
		case NUMPAD2:
			if (speed < 900)
			speed = speed+ 100;
			break;
		case NUMPAD3:
			if (speed < 9000)
			speed = speed+ 1000;
			break;
		case NUMPAD4:
			if (speed >= 10)
			speed = speed- 10;
			break;
		case NUMPAD5:
			if (speed >= 100)
			speed = speed- 100;
			break;
		case NUMPAD6:
			if (speed >= 1000)
			speed = speed- 1000;
			break;
		default:
			break;
		}
	}
	

	/**
	 * rotiert die Kamera
	 * @param mouseDeltaY
	 * @param mouseDeltaX
	 */
	public void rotation(double mouseDeltaY , double mouseDeltaX)
	{	
		if(this.isRotation)
		{	
			this.rotationX.setAngle(rotationX.getAngle() - mouseDeltaX/1);
			this.rotationY.setAngle(rotationY.getAngle() + mouseDeltaY/1);
		}
	}
	
	/**
	 * setzt isRotaion bei rotation auf true
	 * @param isRotation
	 */
	public void setRotaion(Boolean isRotation)
	{
		this.isRotation = isRotation;
	}
	
	
	/**
	 * bewegt die Kamera zu einer neuen Position mit einer Tr
	 * @param newPosition die Position zu der sich die Kamera bewegen soll
	 */
	public void geheZumPunkt(Vector<Double> newPosition)
	{
		//TODO Taransition hinzufuegen
	}
	
	
	/**
	 * richtet die Kammera nach eine Punkt hin aus
	 * @param vector
	 */
	public void ausrichtenNach(Point3D point)
	{	
		double x  = Math.sin(Math.toRadians(rotationX.getAngle()))*Math.cos(Math.toRadians(rotationY.getAngle()));
		double y = Math.sin(Math.toRadians(rotationX.getAngle()))*Math.sin(Math.toRadians(rotationY.getAngle()));
		double z = Math.cos(Math.toRadians(rotationX.getAngle()));	
		
		Point3D ausrichtungKamera = new Point3D(x ,y , z);
		Point3D richtungsVektor = point.subtract(ausrichtungKamera);
		
		RotateTransition rotation = new RotateTransition(Duration.seconds(3), this.getKamera());
		rotation.setAxis(ausrichtungKamera.crossProduct(richtungsVektor));
		rotation.setByAngle(ausrichtungKamera.angle(richtungsVektor));
		rotation.play();
	}
}
