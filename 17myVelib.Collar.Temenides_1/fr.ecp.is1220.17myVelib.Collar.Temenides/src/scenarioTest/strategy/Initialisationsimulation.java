package scenarioTest.strategy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import myVelib.BadParkingSlotCreationException;
import myVelib.BadStateStationCreationException;
import myVelib.BadTypeStationCreationException;
import myVelib.ParkingSlot;
import myVelib.Reseau;
import myVelib.Station;
import myVelib.TimeState;
import myVelib.ridePolicies.NoEndStationAvailableException;
import myVelib.ridePolicies.NoStartStationAvailableException;
/**
 * Permet en vue d'une simulation de location de modifer les TimeState des ParkingSlot afin d'avoir des temps d'occupation non null
 * Pour exécuter cette opération, il faut mettre sur le fichier texte :
 * "initialisationsimualtion;"
 * @author xavier
 *
 */
public class Initialisationsimulation implements StrategyMethode {

	@Override
	public void execute(ArrayList<String> array)
			throws NumberFormatException, BadStateStationCreationException, BadTypeStationCreationException,
			BadParkingSlotCreationException, NoEndStationAvailableException, NoStartStationAvailableException {
		Reseau res=Reseau.getInstance();
		TimeState Ts=null;
		Date datetempo=Calendar.getInstance().getTime();
		datetempo.setMinutes(datetempo.getMinutes()-180);
		for (Station stat:res.getStationList()){
			for(ParkingSlot ps:stat.getParkingSlotList()){
				Ts=ps.getHistory().get(0);
				ps.getHistory().add(new TimeState(Ts.isOccupied(),datetempo));
				ps.getHistory().remove(Ts);
			}
		}

	}

}
