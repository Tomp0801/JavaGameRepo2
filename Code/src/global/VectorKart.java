package global;

/**
 * Vektor mit beliebig vielen doubles
 * standard mathematische Operation wie plus und minus, was man so braucht
 * 
 * @author Thomas
 *
 */
public class VectorKart {
	
	/**
	 * beinhaltet die elemente des Vektors
	 */
	private double[] values;
	
	/**
	 * Anzahl der Elemente in dem Vektor
	 */
	private int dimension;
	
	/**
	 * konstruktor
	 * @param dimension Anzahl der Elemente, die im Vektor gespeichert werden
	 */
	public VectorKart(int dimension)
	{
		this.dimension = dimension;
		values = new double[dimension];
	}
	
	/**
	 * berechnet die Länge des Vektors
	 * @return die Länge des Vektors
	 */
	public double getLength()
	{
		double l = 0;
		for (int i = 0; i < this.dimension; i++)
		{
			l = l + Math.pow(this.get(i), 2);
		}
		l = Math.sqrt(l);
		return l;
	}
	
	/**
	 * Streckt oder Staucht den Vektor um einen Faktor
	 * @param faktor Faktor um den der Vektor gestreckt/gestaucht wird
	 * @return das ergebnis der operation
	 */
	public VectorKart resize(double faktor)
	{
		VectorKart ergebnis = new VectorKart(this.dimension);
		for (int i = 0; i < this.dimension; i++)
		{
			ergebnis.set(i, this.get(i) * faktor);
		}
		return ergebnis;
	}
	
	/**
	 * addiert gegebenen Vektor auf diesen Vektor drauf
	 * 
	 * @param vectorToAdd der Vektor der hinzuaddiert werden soll
	 * @return ergebnis der operation
	 */
	public VectorKart plus(VectorKart vectorToAdd)
	{
		if (vectorToAdd.getDimension() == this.dimension)	// nur bei gleichen Dimensionen
		{
			VectorKart ergebnis = new VectorKart(this.dimension);
			for (int i = 0; i < this.dimension; i++)
			{
				ergebnis.set(i, this.get(i) + vectorToAdd.get(i));
			}
			return ergebnis;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * zieht gegebenen Vektor von diesem Vektor ab
	 * 
	 * @param vectorToAdd der Vektor der abgezogen werden soll
	 * @return ergebnis der operation
	 */
	public VectorKart minus(VectorKart vectorToAdd)
	{
		if (vectorToAdd.getDimension() == this.dimension)	// nur bei gleichen Dimensionen
		{
			VectorKart ergebnis = new VectorKart(this.dimension);
			for (int i = 0; i < this.dimension; i++)
			{
				ergebnis.set(i, this.get(i) - vectorToAdd.get(i));
			}
			return ergebnis;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * stellt einen Wert an einer bestimmten stelle des Vektors ein
	 * @param index stelle, an der der Wert eingfügt werden soll
	 * @param value Wert, der eingefügt wird
	 * @return boolean erfolg 
	 */
	public boolean set(int index, double value)
	{
		if (index >= 0 && index < dimension)
		{
			values[index] = value;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * gibt Wert an bestimmter stelle zurück
	 * @param index des Wertes
	 * @return der Wert an der Stelle index
	 */
	public double get(int index)
	{
		if (index >= 0 && index < dimension)
		{
			return values[index];
		}
		else
		{
			return 0;
		}
	}

	/**
	 * @return die dimension des Vektors (Anzahl der Element)
	 */
	public int getDimension() {
		return dimension;
	}
}
