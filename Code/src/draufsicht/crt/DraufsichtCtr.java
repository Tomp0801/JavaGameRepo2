package draufsicht.crt;

/**
 * Ein Controller der die Klassen in diesem Paket verwaltet 
 * @author Dennis
 *
 */
public class DraufsichtCtr {
	
	private static DraufsichtCtr instance;
	private DraufsichtCtr(){
		instance = this; 
	}
	
	public static DraufsichtCtr getInstance(){
		if (instance == null){
			new DraufsichtCtr();
		}
		return instance; 
	}
}
