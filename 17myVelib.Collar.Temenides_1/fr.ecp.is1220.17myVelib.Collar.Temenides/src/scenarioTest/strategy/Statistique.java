package scenarioTest.strategy;

import java.text.ParseException;
import java.util.ArrayList;

import myVelib.BadParkingSlotCreationException;
import myVelib.BadStateStationCreationException;
import myVelib.BadTypeStationCreationException;
import myVelib.Reseau;
import myVelib.SortingStations.LeastOccupied;
import myVelib.SortingStations.MostUsed;
import myVelib.ridePolicies.NoEndStationAvailableException;
import myVelib.ridePolicies.NoStartStationAvailableException;
import scenarioTest.strategy.StrategyMethode;
/**
 * Permet d'aficher le tri des stations selon les deux m�thodes
 *  Pour ex�cuter cette op�ration, il faut mettre sur le fichier texte :
 *  "statistique;"
 * @author xavier
 *
 */
public class Statistique implements StrategyMethode {

	@Override
	public void execute(ArrayList<String> array)
			throws NumberFormatException, BadStateStationCreationException, BadTypeStationCreationException,
			BadParkingSlotCreationException, NoEndStationAvailableException, NoStartStationAvailableException, ParseException {
		Reseau res=Reseau.getInstance();
		Reseau.getInstance().SortStation(new LeastOccupied( ));
		Reseau.getInstance().SortStation(new MostUsed());

	}

}
