package personensicht;

/**
 * Ein Controller der die Klassen in der Personensicht Kontrolliert
 * @author Dennis
 *
 */
public class PersonensichtCtr {
	
	private static PersonensichtCtr instance; 
	
	private PersonensichtCtr(){
		instance = this;
	}
	
	public static PersonensichtCtr getInsance(){
		if (instance == null){
			new PersonensichtCtr();	
		}
		return instance;
	}
}
