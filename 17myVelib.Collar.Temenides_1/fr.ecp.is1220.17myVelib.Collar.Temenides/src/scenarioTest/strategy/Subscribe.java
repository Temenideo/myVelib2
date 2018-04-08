package scenarioTest.strategy;

import java.text.ParseException;
import java.util.ArrayList;

import myVelib.BadParkingSlotCreationException;
import myVelib.BadStateStationCreationException;
import myVelib.BadTypeStationCreationException;
import myVelib.Location;
import myVelib.Reseau;
import myVelib.Station;
import myVelib.User;
import myVelib.ridePolicies.NoEndStationAvailableException;
import myVelib.ridePolicies.NoStartStationAvailableException;
/**
 * Permet d'affciher les statistiques d'un utilisateur
 * Pour exécuter cette opération, il faut mettre sur le fichier texte :
 * "subscribe;Iduser;typecard;"
 * @author xavier
 *
 */
public class Subscribe implements StrategyMethode {

	@Override
	public void execute(ArrayList<String> array) throws NumberFormatException, BadStateStationCreationException,
	BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException,
	NoStartStationAvailableException, ParseException {
		Reseau res=Reseau.getInstance();
		User use=null;
		for(User user:res.getUserList()){
			if(user.getUserID()==Long.parseLong(array.get(1))){
				use=user;
				break;
			}
		}
		if(array.get(2).equalsIgnoreCase("VmaxCard")){
			use.subscribeVmax();
		}
		else if (array.get(2).equalsIgnoreCase("VLibreCard")){
			use.subscribeVlibre();
		}
	}

}
