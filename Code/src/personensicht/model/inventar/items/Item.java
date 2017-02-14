package personensicht.model.inventar.items;

import personensicht.model.gameObjekte.GameObjekt;
import personensicht.model.gameObjekte.GameObjektType;

public abstract class Item extends GameObjekt
{
	Item()
	{
		super(GameObjektType.Item);
	}
}
