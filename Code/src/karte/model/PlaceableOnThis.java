package karte.model;

public interface PlaceableOnThis {
	public void place(Placeable object);
	
	public void removeObject();
	
	public Placeable getObject();
	
	public Map getKarte();
	
	public int getX();
	
	public int getY();
	
	public PlaceableOnThis getNachbar(Kompass richtung) throws ArrayIndexOutOfBoundsException;
}
