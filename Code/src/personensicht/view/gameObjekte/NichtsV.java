package personensicht.view.gameObjekte;

import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import personensicht.view.Shape3DZusatzMethoden;

/**
 * Besteht eine Feld aus nichts, (Kein betretbarerraum) Dann wird wird diese Box verwendet
 * @author Dennis
 *
 */
public class NichtsV extends Box
{
	
	public NichtsV()
	{
		Shape3DZusatzMethoden.hintergundFarbeSetzen(this, Color.BLACK);

	}
	
	public NichtsV(int hohe, int breite, int lange)
	{
		this();
		this.setWidth(breite);
		this.setHeight(hohe);
		this.setDepth(lange);
	}
}
