package karte.model;

public class FeldConnection {
	private Feld feld1;
	
	private Feld feld2;
	
	
	
	public FeldConnection(Feld feld1, Feld feld2) {
		this.feld1 = feld1;
		this.feld2 = feld2;
	}
	
	public Feld getConnectedFeld(Feld feld) {
		if (feld == feld1) {
			return feld2;
		} else if (feld == feld2) {
			return feld1;
		} else {
			return null;
		}
	}
}
