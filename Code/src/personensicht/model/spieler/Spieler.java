package personensicht.model.spieler;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import personensicht.model.gameObjekte.Inventar;

public class Spieler 
{
	
	//---------------Spielereigenschaften--------------------------------------------------------------------//
	/**
	 * Name des Spielers
	 */
	private String name = "defaut";
	private Geschlecht geschlecht; 	
	private int alter; 
	private Persoenlichkeiten persoenlichkeit; 
	private Aussehen aussehen; 
	
	/**
	 * Hungeranzeige. Ist der Hunger auf den Wert 0, hat der Spieler sein maximalen Hungersstatus erreicht
	 */
	private SimpleIntegerProperty hunger = new SimpleIntegerProperty();
	private SimpleIntegerProperty durst= new SimpleIntegerProperty();
	private SimpleIntegerProperty lepenspunkte = new SimpleIntegerProperty();
	private SimpleIntegerProperty ausdauerpunkte= new SimpleIntegerProperty();
	private SimpleIntegerProperty muedigkeit= new SimpleIntegerProperty();
	private SimpleIntegerProperty geld= new SimpleIntegerProperty();	
	private Inventar inventar;
	
	public Spieler(String name)
	{
		this.name = name; 
		hunger.set(100);
		durst.set(100);
		muedigkeit.set(100);
		ausdauerpunkte.set(100);
		lepenspunkte.set(100);
		geld.set(50);
		inventar = new Inventar(40, name );
	}
	
	public Spieler(String name, Aussehen aussehen, Geschlecht geschlecht, int alter)
	{
		this(name);
		this.aussehen = aussehen; 
		this.geschlecht =  geschlecht; 
		this.alter = alter; 
	}
	
	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

	public synchronized SimpleIntegerProperty getHunger() {
		return hunger;
	}
	
	public synchronized SimpleIntegerProperty getDurst() {
		return durst;
	}

	public synchronized void setHunger(int hunger) {
		this.hunger.set(hunger);
	}
	
	public synchronized void changeHunger(int hunger) 
	{
		 Platform.runLater(new Runnable()
		{
			 SimpleIntegerProperty hunger2 = Spieler.this.hunger;

			@Override 
			public void run() 
			{
				if (hunger2.get()+hunger <= 100)
				 hunger2.set(hunger2.get()+hunger);
				else
					hunger2.set(100);
		    }
		 });
	}
	
	public synchronized void changeMuedigkeit(int wert) 
	{
		 Platform.runLater(new Runnable()
		{
			@Override 
			public void run() 
			{
				if (muedigkeit.get()+wert <= 100)
					muedigkeit.set(muedigkeit.get()+wert);
					else
						muedigkeit.set(100);
		    }
		 });
	}
	
	public synchronized void changeAusdauer(int wert) 
	{
		 Platform.runLater(new Runnable()
		{
			@Override 
			public void run() 
			{
				if (ausdauerpunkte.get()+wert <= 100)
					ausdauerpunkte.set(muedigkeit.get()+wert);
					else
					ausdauerpunkte.set(100);
		    }
		 });
	}
	
	public synchronized Inventar getInventar() {
		return inventar;
	}

	public synchronized void setInventar(Inventar inventar) {
		this.inventar = inventar;
	}
	
	public synchronized void changeDurst(int durst)
	{
		Platform.runLater(new Runnable() 
		{
			 SimpleIntegerProperty durst2 = Spieler.this.durst;

			@Override 
			public void run()
			{
				if (durst2.get()+ durst <= 100)
				 durst2.set(durst2.get()+durst);
				else
					durst2.set(100);
					
		    }
		 }) ; 
	}
	
	public synchronized SimpleIntegerProperty getLepenspunkte() {
		return lepenspunkte;
	}

	public synchronized void setLepenspunkte(SimpleIntegerProperty lepenspunkte) {
		this.lepenspunkte = lepenspunkte;
	}

	public synchronized SimpleIntegerProperty getAusdauerpunkte() {
		return ausdauerpunkte;
	}

	public synchronized void setAusdauerpunkte(SimpleIntegerProperty ausdauerpunkte) {
		this.ausdauerpunkte = ausdauerpunkte;
	}

	public synchronized SimpleIntegerProperty getMuedigkeit() {
		return muedigkeit;
	}

	public synchronized void setMuedigkeit(SimpleIntegerProperty muedigkeit) {
		this.muedigkeit = muedigkeit;
	}

	public synchronized SimpleIntegerProperty getGeld() {
		return geld;
	}

	public synchronized void setGeld(SimpleIntegerProperty geld) {
		this.geld = geld;
	}

	public synchronized Geschlecht getGeschlecht() {
		return geschlecht;
	}

	public synchronized void setGeschlecht(Geschlecht geschlecht) {
		this.geschlecht = geschlecht;
	}

	public synchronized int getAlter() {
		return alter;
	}

	public synchronized void setAlter(int alter) {
		this.alter = alter;
	}

	public synchronized Persoenlichkeiten getPersoenlichkeit() {
		return persoenlichkeit;
	}

	public synchronized void setPersoenlichkeit(Persoenlichkeiten persoenlichkeit) {
		this.persoenlichkeit = persoenlichkeit;
	}

	public synchronized Aussehen getAussehen() {
		return aussehen;
	}

	public synchronized void setAussehen(Aussehen aussehen) {
		this.aussehen = aussehen;
	}
}
