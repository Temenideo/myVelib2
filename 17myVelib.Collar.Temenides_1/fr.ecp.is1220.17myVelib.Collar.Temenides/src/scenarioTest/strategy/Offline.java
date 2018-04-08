package scenarioTest.strategy;

import java.util.ArrayList;

import myVelib.BadParkingSlotCreationException;
import myVelib.BadStateStationCreationException;
import myVelib.BadTypeStationCreationException;
import myVelib.Reseau;
import myVelib.Station;
import myVelib.ridePolicies.NoEndStationAvailableException;
import myVelib.ridePolicies.NoStartStationAvailableException;
/**
 * Permet de mettre une station Offline grâce à son ID
 * Pour exécuter cette opération, il faut mettre sur le fichier texte :
 * "offline;IDstation;"
 * @author xavier
 *
 */
public class Offline implements StrategyMethode {

	@Override
	public void execute(ArrayList<String> array)
			throws NumberFormatException, BadStateStationCreationException, BadTypeStationCreationException,
			BadParkingSlotCreationException, NoEndStationAvailableException, NoStartStationAvailableException {
		Reseau res=Reseau.getInstance();
		Station stat1 = null;
		for(Station stat:res.getStationList()){
			if(stat.getStationID()==Long.parseLong(array.get(1))){
				stat.setState("Offline");
				break;
			}
		}

	}

}
