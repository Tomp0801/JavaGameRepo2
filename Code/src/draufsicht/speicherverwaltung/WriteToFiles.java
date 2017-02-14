package draufsicht.speicherverwaltung;

public class WriteToFiles {
	public static void main(String[] args)
	{
		RessourcenObjekte ressourcen =  new RessourcenObjekte();
		IOHandler.getInstance().saveArrayList("bodenschaetze", ressourcen.getBodenschaetze());
		IOHandler.getInstance().saveArrayList("bodentypen", ressourcen.getBodentypen());
		IOHandler.getInstance().saveArrayList("verschiedene", ressourcen.getVerschiedene());
		
		System.out.println("Dateien erfolgreich beschrieben und gespeichert in " + IOHandler.getInstance().getDefaultPath());
	}
}
