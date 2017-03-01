package personensicht.view.gameObjekte;

import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import personensicht.model.gameObjekte.Tisch;
import personensicht.view.Shape3DZusatzMethoden;

public class TischV extends GameObjektV
{

	public final static int MAXSIZE_X = 1000; 
	public final static int MAXSIZE_Y = 1000; 
	public final static int MAXSIZE_Z = 400; 
	public final static int MINSIZE_X = 20; 
	public final static int MINSIZE_Y = 20; 
	public final static int MINSIZE_Z = 20; 
	
	/**
	 * die Tischplatte
	 */
	private Box tischplatte = new Box();
	
	/**
	 * vier Tischbeine
	 */
	private Box[] tischBeine = new Box[4];
	
	public TischV(Tisch tisch)
	{
		this.setNode(this.tischplatte);
		this.tischplatte.setHeight(tisch.getY());
		this.tischplatte.setWidth(tisch.getX());
		this.tischplatte.setDepth(20);
	}

	@Override
	public void setY(double hohe) {
		this.tischplatte.setHeight(hohe);
	}

	@Override
	public void setX(double hohe) {
		this.tischplatte.setWidth(hohe);
		
	}

	@Override
	public void setZ(double hohe) {
		this.tischplatte.setDepth(hohe);
		
	}

	@Override
	public void setColor(Color color) 
	{
		Shape3DZusatzMethoden.hintergundFarbeSetzen(this.tischplatte, color);
		
	}

	public synchronized Box getTischplatte() {
		return tischplatte;
	}

	public synchronized Box[] getTischBeine() {
		return tischBeine;
	}

	public synchronized void setTischplatte(Box tischplatte) {
		this.tischplatte = tischplatte;
	}

	public synchronized void setTischBeine(Box[] tischBeine) {
		this.tischBeine = tischBeine;
	}

}
