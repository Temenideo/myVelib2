package scenarioTest.strategy;

import java.util.ArrayList;

import myVelib.BadParkingSlotCreationException;
import myVelib.BadStateStationCreationException;
import myVelib.BadTypeStationCreationException;
import myVelib.GPScoord;
import myVelib.ParkingSlot;
import myVelib.Station;
import myVelib.Bicycle.Electrical;
import myVelib.Bicycle.Mechanical;
import myVelib.ridePolicies.NoEndStationAvailableException;
/**
 * Permet de cr�er une nouvelle station sur le reseau
 *  Pour ex�cuter cette op�ration, il faut mettre sur le fichier texte :
 *  "station;Xcoordonn�es,Ycoordonn�es;type de la station;nombre de parkingSlot;nombre de v�lo �l�ctrique;nombre de v�lo m�canique;nom de la station;"
 * @author xavier
 *
 */
public class StationCreation implements StrategyMethode {

	@Override
	public void execute(ArrayList<String> array) throws NumberFormatException, BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException {
		Station stat=new Station(array.get(3),"on service",new GPScoord(Float.parseFloat(array.get(1)), Float.parseFloat(array.get(2))),array.get(7));
		for(int i=0;i<Integer.parseInt(array.get(5));i++){
			new ParkingSlot(new Electrical(),"Occupied",stat);
		}
		for(int i=0;i<Integer.parseInt(array.get(6));i++){
			new ParkingSlot(new Mechanical(),"Occupied",stat);
		}
		for(int i=0;i<Integer.parseInt(array.get(4))-Integer.parseInt(array.get(6))-Integer.parseInt(array.get(5));i++){
			new ParkingSlot(null,"Free",stat);
		}

	}

}
