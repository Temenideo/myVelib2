package myVelib.SortingStations;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

import myVelib.BadParkingSlotCreationException;
import myVelib.BadStateStationCreationException;
import myVelib.BadTypeStationCreationException;
import myVelib.GPScoord;
import myVelib.Location;
import myVelib.ParkingSlot;
import myVelib.Reseau;
import myVelib.Station;
import myVelib.TimeState;
import myVelib.User;
import myVelib.Bicycle.Mechanical;
import myVelib.ridePolicies.NoEndStationAvailableException;

public class LeastOccupiedTest {
/**
 *  Ce test est automiquement validé car la fonction ne renvoi rien,mais nous pouvons voir que les deux stations sont triées dans l'ordre
 * @throws ParseException
 * @throws BadStateStationCreationException
 * @throws BadTypeStationCreationException
 * @throws BadParkingSlotCreationException
 * @throws NoEndStationAvailableException
 */
	@Test
	public void testSortStation() throws ParseException, BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException {
		Reseau res=Reseau.getInstance();
		res.resetReseau();
		Station departure = new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(0,0), null);
		ParkingSlot ps1=new ParkingSlot(new Mechanical(),"Occupied",departure);
		departure.setName("name1");
		String string = "2018.03.25 AD at 12:08:56 PDT";
		String string2 = "2018.03.25 AD at 12:38:56 PDT";
		DateFormat format = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.ENGLISH);
		Date datestart = format.parse(string);
		Date dateend = format.parse(string2); 
		ps1.getHistory().remove(0);
		ps1.getHistory().add(new TimeState(true,datestart));
		ps1.getHistory().get(0).setEnd(dateend);
		Station departure2 = new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(0,0), null);
		departure2.setName("name2");
		ParkingSlot ps2=new ParkingSlot(null,"Free",departure2);
		ps2.getHistory().remove(0);
		ps2.getHistory().add(new TimeState(false,datestart));
		ps2.getHistory().get(0).setEnd(dateend);
		SortingMethods a=new LeastOccupied();
		a.sortStation(res.getStationList());

	}
}

