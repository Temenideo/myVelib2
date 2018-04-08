package scenarioTest.strategy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import myVelib.BadParkingSlotCreationException;
import myVelib.BadStateStationCreationException;
import myVelib.BadTypeStationCreationException;
import myVelib.Location;
import myVelib.ParkingSlot;
import myVelib.Reseau;
import myVelib.Station;
import myVelib.TimeState;
import myVelib.User;
import myVelib.ridePolicies.NoEndStationAvailableException;
import myVelib.ridePolicies.NoStartStationAvailableException;
import scenarioTest.strategy.StrategyMethode;
/**
 * Permet de simuler une location sans à avoir à attendre un écoulement du temps
 *  Pour exécuter cette opération, il faut mettre sur le fichier texte :
 *  "simulationlocation;IDuser;IDstationDedépart;IDstationD'arrivée;durée de la location(en minute);type de vélo;"
 * La location aura commencé avant de tel sorte que le location finisse au temps machine actuel
 * @author xavier
 *
 */
public class SimulationLocation implements StrategyMethode {

	@Override
	public void execute(ArrayList<String> array)
			throws NumberFormatException, BadStateStationCreationException, BadTypeStationCreationException,
			BadParkingSlotCreationException, NoEndStationAvailableException, NoStartStationAvailableException {
		Reseau res=Reseau.getInstance();
		Station statdepart = null;
		Station statarrivée = null;
		ParkingSlot ps=null;
		User user1=null;
		for(Station stat:res.getStationList()){
			if(stat.getStationID()==Long.parseLong(array.get(2))){
				statdepart=stat;
				break;
			}
		}
		for(Station stat:res.getStationList()){
			if(stat.getStationID()==Long.parseLong(array.get(3))){
				statarrivée=stat;
				break;
			}
		}
		for(User user:res.getUserList()){
			if(user.getUserID()==Long.parseLong(array.get(1))){
				user1=user;
				break;
			}
		}
		Location loc=new Location(user1,statdepart);
		loc.takeBike(statdepart, array.get(5));
		Date datetempo=Calendar.getInstance().getTime();
		datetempo.setMinutes(datetempo.getMinutes()-Integer.parseInt(array.get(4)));
		loc.setTimeStart(datetempo);
		if(array.get(5).equalsIgnoreCase("Mechanical")){
			ps=statdepart.getParkingSlotList().get(5);
			ps.getHistory().remove(ps.getHistory().size()-1);
			ps.getHistory().add(new TimeState(false,datetempo));
		}
		else {
			ps=statdepart.getParkingSlotList().get(0);
			ps.getHistory().remove(ps.getHistory().size()-1);
			ps.getHistory().add(new TimeState(false,datetempo));
		}
		loc.returnBike(statarrivée);

	}

}
