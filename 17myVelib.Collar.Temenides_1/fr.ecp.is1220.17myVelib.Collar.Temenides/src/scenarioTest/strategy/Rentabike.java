package scenarioTest.strategy;

import java.util.ArrayList;

import myVelib.BadParkingSlotCreationException;
import myVelib.Location;
import myVelib.Reseau;
import myVelib.Station;
import myVelib.User;
import myVelib.ridePolicies.NoEndStationAvailableException;
/**
 * Afin de r�cuperer un v�lo � une station
 * Pour ex�cuter cette op�ration, il faut mettre sur le fichier texte :
 * "rentabike;IDuser;IDStation;type de v�lo;"
 * @author xavier
 *
 */
public class Rentabike implements StrategyMethode {
	@Override
	public void execute(ArrayList<String> array) throws BadParkingSlotCreationException, NoEndStationAvailableException{
		Reseau res=Reseau.getInstance();
		Station stat1 = null;
		for(Station stat:res.getStationList()){
			if(stat.getStationID()==Long.parseLong(array.get(2))){
				stat1=stat;
				break;
			}
		}
		for(User user:res.getUserList()){
			if(user.getUserID()==Long.parseLong(array.get(1))){
				Location loc=new Location(user,stat1);
				loc.takeBike(stat1,array.get(3));
				break;
			}
		}
	}
}
