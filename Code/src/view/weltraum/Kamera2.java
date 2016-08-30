package view.weltraum;

import javafx.geometry.Point3D;

public abstract class Kamera2 
{
	private  Point3D position = new Point3D(0 ,0 ,0);
	
	protected Kamera2()
	{
		
	}
	
	/**
	 * setzt die Position der Kamera auf einen neuen Punkt
	 * @param posi ist der neue Punkt 
	 */
	public void setPosition(Point3D posi)
	{
		this.position = posi; 
	}
}
