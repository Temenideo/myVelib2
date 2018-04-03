package myVelib.SortingStations;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Test;

import myVelib.BadParkingSlotCreationException;
import myVelib.BadStateStationCreationException;
import myVelib.BadTypeStationCreationException;
import myVelib.GPScoord;
import myVelib.Location;
import myVelib.ParkingSlot;
import myVelib.Reseau;
import myVelib.Station;
import myVelib.User;
import myVelib.Bicycle.Mechanical;
import myVelib.ridePolicies.NoEndStationAvailableException;
import myVelib.ridePolicies.NoStartStationAvailableException;

public class MostUsedTest {
/**
 * Ce test est automiquement validé car la fonction ne renvoi rien,mais nous pouvons voir que les deux stations sont triées dans l'ordre
 * @throws BadStateStationCreationException
 * @throws BadTypeStationCreationException
 * @throws BadParkingSlotCreationException
 * @throws NoEndStationAvailableException
 * @throws NoStartStationAvailableException
 * @throws ParseException
 */
	@Test
	public void testSortStation() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException, NoStartStationAvailableException, ParseException {
		Reseau res=Reseau.getInstance();
		res.resetReseau();
		Station departure = new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(0,0), null);
		new ParkingSlot(new Mechanical(),"Occupied",departure);
		User user1 = new User("Jean","Paul");
		departure.setName("name1");
		for (int i=0;i<10;i++){
			Location loc2 = new Location(user1,departure);
			loc2.setArrivalForTest(departure);
			loc2.setDeparture(departure);
			loc2.setHasEnded(true);
		}
		Station departure2 = new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(0,0), null);
		departure2.setName("name2");
		for (int i=0;i<5;i++){
			Location loc2 = new Location(user1,departure2);
			loc2.setArrivalForTest(departure2);
			loc2.setDeparture(departure2);
			loc2.setHasEnded(true);
		}
		SortingMethods a=new MostUsed();
		a.sortStation(res.getStationList());
		
	}

}
