package view.weltraum;

import javafx.geometry.Point3D;
import javafx.scene.PerspectiveCamera;

public abstract class Kamera2 
{
	/**
	 * die Kamera
	 */
	private PerspectiveCamera kamera = new PerspectiveCamera();
	
	
	protected Kamera2()
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
