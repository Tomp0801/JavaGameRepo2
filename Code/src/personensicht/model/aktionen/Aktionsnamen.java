package personensicht.model.aktionen;

public class Aktionsnamen 
{
	public static String getNamefuerAktion(Aktionstyp typ)
	{
		String name = null;
		switch (typ)
		{
		case Essen:
			name = "essen";
			break; 
		case Trinken:
			name = "trinken";
			break;
		case OeffneInventar:
			name = "öffnen";
			break;
		default:
			break; 
		}
		return name;
	}
}
