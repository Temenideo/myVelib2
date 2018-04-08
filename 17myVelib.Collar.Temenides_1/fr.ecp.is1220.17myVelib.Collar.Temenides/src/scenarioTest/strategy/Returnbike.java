package scenarioTest.strategy;

import java.util.ArrayList;

import myVelib.BadParkingSlotCreationException;
import myVelib.BadStateStationCreationException;
import myVelib.BadTypeStationCreationException;
import myVelib.Reseau;
import myVelib.Station;
import myVelib.User;
import myVelib.ridePolicies.NoEndStationAvailableException;
import myVelib.ridePolicies.NoStartStationAvailableException;
/**
 * Permet de rendre un vélo après une location
 *  Pour exécuter cette opération, il faut mettre sur le fichier texte :
 *  "returnbike;IDstation;IDuser;"
 * @author xavier
 *
 */
public class Returnbike implements StrategyMethode {

	@Override
	public void execute(ArrayList<String> array)
			throws NumberFormatException, BadStateStationCreationException, BadTypeStationCreationException,
			BadParkingSlotCreationException, NoEndStationAvailableException, NoStartStationAvailableException {
		Reseau res=Reseau.getInstance();
		Station stat1 = null;
		for(Station stat:res.getStationList()){
			if(stat.getStationID()==Long.parseLong(array.get(1))){
				stat1=stat;
				break;
			}
		}
		for(User user:res.getUserList()){
			if(user.getUserID()==Long.parseLong(array.get(2))){
				user.getLoc().returnBike(stat1);
				break;
			}
		}

	}

}
