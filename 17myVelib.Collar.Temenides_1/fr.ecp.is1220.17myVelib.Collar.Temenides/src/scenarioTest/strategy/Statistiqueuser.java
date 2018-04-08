package scenarioTest.strategy;

import java.text.ParseException;
import java.util.ArrayList;

import myVelib.BadParkingSlotCreationException;
import myVelib.BadStateStationCreationException;
import myVelib.BadTypeStationCreationException;
import myVelib.Reseau;
import myVelib.User;
import myVelib.ridePolicies.NoEndStationAvailableException;
import myVelib.ridePolicies.NoStartStationAvailableException;
/**
 * Permet d'affciher les statistiques d'un utilisateur
 *  Pour exécuter cette opération, il faut mettre sur le fichier texte :
 *  "statistiqueuser;Iduser;"
 * @author xavier
 *
 */
public class Statistiqueuser implements StrategyMethode {

	@Override
	public void execute(ArrayList<String> array) throws NumberFormatException, BadStateStationCreationException,
			BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException,
			NoStartStationAvailableException, ParseException {
		Reseau res=Reseau.getInstance();
		for(User user:res.getUserList()){
			if(user.getUserID()==Long.parseLong(array.get(1))){
				user.getStatistics();
				break;
			}
		}

	}

}
