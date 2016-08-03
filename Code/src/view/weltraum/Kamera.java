package view.weltraum;

import javafx.geometry.Point3D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.input.KeyCode;
import javafx.scene.transform.Rotate;

/**
 * das ist die Kamera der WeltraumSicht. 
 * @author Dennis
 *
 */
public class Kamera extends PerspectiveCamera
{
	/**
	 * Position der Kamera
	 */
	private Point3D position;
	
	/**
	 * wenn sich die Kamera in Rotation befindet, ist isRotation = true
	 */
	private Boolean isRotation = false;
	
	/**
	 * rotations um die Achse 
	 */
	private Rotate rotationX = new Rotate(0, Rotate.Y_AXIS);
	
	/**
	 * rotation um die Z Achse
	 */
	private Rotate rotationY = new Rotate(0, Rotate.X_AXIS);

	/**
	 * erstellt eine Kamera
	 */
	public Kamera()
	{
		super(true);
	
		//setzt die Position auf 0
		position = new Point3D(0, 0, 0);
		
		//legt die Sichtweite fest
		this.setFarClip(10000);
		//maximale naehe
		this.setNearClip(10000);
		this.getTransforms().addAll(rotationX , rotationY);
	}
	
	
	/**
	 * setzt die Position der Kamera
	 * @param positionX
	 * @param positionY
	 * @param positionZ
	 */
	public void setPosition(double positionX, double positionY , double positionZ) 
	{
	    this.setTranslateX(positionX);
	    this.setTranslateY(positionY);
		this.setTranslateZ(positionZ);
		
		this.position = this.position.add(positionX, positionY, positionZ);
	}
	
	
	/**
	 * setzt die Position der Kamera
	 * @param positon
	 */
	public void setPosition(Point3D position) 
	{
		this.setPosition(position.getX(), position.getY(), position.getZ());
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
		this.setPosition(this.getTranslateX() + pointX , this.getTranslateY() + pointY, this.getTranslateZ() + pointZ);
	}

	
	/**
	 * bewegt die Kamera in eine Bestimte richtung
	 * @param key W: nach vorne / S: zurueck / D: nach Rechts / A: nach Links / E: nach oben / Q: nach unten
	 */
	public void doBewegung(KeyCode key)
	{
		double a = 5*Math.cos(Math.toRadians(90-rotationX.getAngle()));
		double b = 5*Math.sin(Math.toRadians(-rotationY.getAngle()));
		double c = 5*Math.sin(Math.toRadians(90-rotationX.getAngle()));
		
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
			veraenderePosition(0 , c, 0);
			break;
		case Q: 
			veraenderePosition(0, -c, 0);
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
			this.rotationY.setAngle(rotationY.getAngle() - mouseDeltaX/5);
			this.rotationX.setAngle(rotationX.getAngle() + mouseDeltaY/5);
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
}
