package personensicht.model.spieler;

import javafx.scene.paint.Color;
import personensicht.model.spieler.klamotten.Klamotte;

public class Aussehen 
{
	private double groeﬂe;
	private Color hautfarbe;
//	private Farbe haarfarbe;
	private Klamotte[] klamotte; 
	
	/**
	 * 
	 * @param groﬂe
	 * @param hautfarbe
	 * @param klamotten Beispiel:  		
	 *  klamotte[0] = klamottenwahl.getHut();
		klamotte[1] = klamottenwahl.getTshirt();
		klamotte[2] = klamottenwahl.getJacke();
		klamotte[3] = klamottenwahl.getHose();
		klamotte[4] = klamottenwahl.getSchuhe();
	 */
	public Aussehen(double groﬂe, Color hautfarbe, Klamotte[] klamotten)
	{
		this.groeﬂe = groﬂe;
		this.hautfarbe = hautfarbe; 
		this.klamotte = klamotten; 
	}

	public synchronized double getGroeﬂe() {
		return groeﬂe;
	}

	public synchronized void setGroeﬂe(short groeﬂe) {
		this.groeﬂe = groeﬂe;
	}

	public synchronized Color getHautfarbe() {
		return hautfarbe;
	}

	public synchronized void setHautfarbe(Color hautfarbe) {
		this.hautfarbe = hautfarbe;
	}

	public synchronized Klamotte[] getKlamotte() {
		return klamotte;
	}

	public synchronized void setKlamotte(Klamotte[] klamotte) {
		this.klamotte = klamotte;
	}
}
