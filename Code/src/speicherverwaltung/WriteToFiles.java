package speicherverwaltung;

public class WriteToFiles {
	public static void main(String[] args)
	{
		RessourcenObjekte ressourcen =  new RessourcenObjekte();
		IOHandler.getInstance().saveArrayList("bodenschaetze", ressourcen.getBodenschaetze());
		IOHandler.getInstance().saveArrayList("bodentypen", ressourcen.getBodentypen());
	}
}
