package personensicht.crt.spielablauf;

import personensicht.model.spieler.Spieler;

/**
 * verwaltet die Zeitabhaengigen mechanismen
 * @author Dennis
 *
 */
public class ZeitCrt 
{
	private Thread zeitablauf;
	private final int SPEED = 5000; 
	public ZeitCrt(Spieler spieler)
	{
		zeitablauf = new Thread(new Runnable()
		{
			@Override
			public void run() 
			{
				while (zeitablauf.isInterrupted() == false)
				{
					try 
					{
						Thread.sleep(SPEED);
						spieler.changeHunger(-1);
						spieler.changeDurst(-1);
						spieler.changeMuedigkeit(-1);
						if (spieler.getDurst().get() <= 0)
						{
							Spielcontroller.getInstance().spielVerloren();
						}
						if (spieler.getHunger().get() <= 0)
						{
							Spielcontroller.getInstance().spielVerloren();
						}
					} 
					catch (InterruptedException e) 
					{
						
						e.printStackTrace();
					}
				}	
			}
		});
	}
	
	public void startZeit()
	{
		zeitablauf.start();
	}
	
	public void beenden()
	{
		zeitablauf.interrupt();
	}
}
