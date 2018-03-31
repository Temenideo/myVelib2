package myVelib.ridePolicies;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import myVelib.BadParkingSlotCreationException;
import myVelib.BadStateStationCreationException;
import myVelib.BadTypeStationCreationException;
import myVelib.GPScoord;
import myVelib.ParkingSlot;
import myVelib.Station;
import myVelib.Bicycle.Electrical;

public class PreferPlusTest {

	@Test
	public void testPreferPlus() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoStartStationAvailableException, NoEndStationAvailableException {
		Station stat1 =new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(0,10), null);
		Station stat2= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(0,111), null);
		Station stat3= new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(0,100), null);
		new ParkingSlot(new Electrical(),"Occupied",stat1);
		new ParkingSlot(null,"Free",stat2);
		new ParkingSlot(null,"Free",stat3);	
		PreferPlus pP=new PreferPlus();
		Station start=pP.computeStart(new GPScoord(0, 0),new GPScoord(0,100), "Electrical");
		Station end=pP.computeEnd(new GPScoord(0,100),new GPScoord(0,0), "Electrical");
		assertEquals(stat1, start);
		assertEquals(stat3, end);
		Station stat4= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(0,110), null);
		new ParkingSlot(null,"Free",stat4);	
		end=pP.computeEnd(new GPScoord(0, 0),new GPScoord(0,100), "Electrical");
		assertEquals(stat4, end);
	}
}
