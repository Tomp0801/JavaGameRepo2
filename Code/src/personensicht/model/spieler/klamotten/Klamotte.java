package personensicht.model.spieler.klamotten;

import javafx.scene.paint.Color;

public class Klamotte
{
	private String name; 
	private short wohlhabend;
	private short bedrohlich;
	private short ordentlich;
	private short verschmutzt;
	private short beschaedigt; 
	private Color color = Color.WHITE; 
		
	public Klamotte(String name) 
	{
		this.name = name; 
	}
	
	public synchronized String getName() {
		return name;
	}
	public synchronized void setName(String name) {
		this.name = name;
	}
	public synchronized short getWohlhabend() {
		return wohlhabend;
	}
	public synchronized void setWohlhabend(short wohlhabend) {
		this.wohlhabend = wohlhabend;
	}
	public synchronized short getBedrohlich() {
		return bedrohlich;
	}
	public synchronized void setBedrohlich(short bedrohlich) {
		this.bedrohlich = bedrohlich;
	}
	public synchronized short getOrdentlich() {
		return ordentlich;
	}
	public synchronized void setOrdentlich(short ordentlich) {
		this.ordentlich = ordentlich;
	}
	public synchronized short getVerschmutzt() {
		return verschmutzt;
	}
	public synchronized void setVerschmutzt(short verschmutzt) {
		this.verschmutzt = verschmutzt;
	}
	public synchronized short getBeschaedigt() {
		return beschaedigt;
	}
	public synchronized void setBeschaedigt(short beschaedigt) {
		this.beschaedigt = beschaedigt;
	}

	public synchronized Color getColor() {
		return color;
	}

	public synchronized void setColor(Color color) {
		this.color = color;
	}
}
