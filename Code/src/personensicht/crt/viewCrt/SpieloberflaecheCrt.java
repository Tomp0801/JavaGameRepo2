package personensicht.crt.viewCrt;

import personensicht.model.welt.map.ModelSpieloberflaeche;
import personensicht.model.welt.map.Region;
import personensicht.view.SpielScene;
import personensicht.view.welt.map.RegionV;

/**
 * maneged Aktionen auf der Spieloberfaeche
 * @author Dennis
 *
 */
public class SpieloberflaecheCrt 
{
	/**
	 * Instance der ViewSpieloberflaeche
	 */
	private static SpielScene instanceOfViewSpieloberflaeche;
	
	private static SpieloberflaecheCrt instance;
	
	private ModelSpieloberflaeche instanceOfModelSpieloberflaeche;
	
	private SpieloberflaecheCrt()
	{
		instance = this; 
	}
	
	public void startView(Region startOrt)
	{
		instanceOfModelSpieloberflaeche = new ModelSpieloberflaeche(startOrt);
		instanceOfViewSpieloberflaeche = new SpielScene();
		StageCrt.getInstance().setScene(instanceOfViewSpieloberflaeche.getScene());
		instanceOfViewSpieloberflaeche.setMittelfeld(new RegionV(instanceOfModelSpieloberflaeche.getPosition()));
		
	}
	
	public static SpieloberflaecheCrt getInstance()
	{
		if (instance == null)
			new SpieloberflaecheCrt();
		return instance; 
	}
	
	public static synchronized SpielScene getInstanceOfViewSpieloberflaeche() 
	{
		return instanceOfViewSpieloberflaeche;
	}
}
