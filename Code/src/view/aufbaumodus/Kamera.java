package view.aufbaumodus;

import javafx.geometry.Point3D;
import javafx.scene.PerspectiveCamera;

/**
 * Eine Kamera zur Steuerung der Sicht
 * 
 * @author Dennis
 *
 */
public abstract class Kamera 
{
	/**
	 * die Kamera
	 */
	private PerspectiveCamera kamera = new PerspectiveCamera();
	
	
	protected Kamera()
	{}
	
	/**
	 * setzt die Position der Kamera auf einen neuen Punkt
	 * @param posi ist der neue Punkt 
	 */
	public void setPosition(Point3D posi)
	{
		this.kamera.setTranslateX(posi.getX());
		this.kamera.setTranslateY(posi.getY());
		this.kamera.setTranslateZ(posi.getZ());
	}
	
	
	/**
	 * setzt die Position der Kamera
	 * @param positionX
	 * @param positionY
	 * @param positionZ
	 */
	public void setPosition(double positionX, double positionY , double positionZ) 
	{
		this.setPosition(new Point3D(positionX, positionY , positionZ));
	}
	
	

	/**
	 * gibt die Kamera wieder, die ran und weg sumt
	 * @return
	 */
	protected PerspectiveCamera getKamera()
	{
		return this.kamera;
	}
}
