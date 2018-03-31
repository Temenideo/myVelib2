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

public class UniformityTest {

	@Test
	public void testComputeStart() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoStartStationAvailableException, NoEndStationAvailableException {
		Station stat1 =new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(0,100), null);
		Station stat2= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(0,105), null);
		new ParkingSlot(new Electrical(),"Occupied",stat1);
		new ParkingSlot(new Electrical(),"Occupied",stat2);
		new ParkingSlot(new Electrical(),"Occupied",stat2);
		Uniformity uN=new Uniformity();
		Station start=uN.computeStart(new GPScoord(0, 0),new GPScoord(0,100), "Electrical");
		assertEquals(stat1, start);
		Station stat3= new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(0,104), null);
		new ParkingSlot(new Electrical(),"Occupied",stat3);
		new ParkingSlot(new Electrical(),"Occupied",stat3);
		start=uN.computeStart(new GPScoord(0, 0),new GPScoord(0,100), "Electrical");
		assertEquals(stat3, start);
	}
	
	@Test
	public void testComputeEnd() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoStartStationAvailableException, NoEndStationAvailableException {
		Station stat1 =new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(0,-100), null);
		Station stat2= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(0,-106), null);
		new ParkingSlot(null,"Free",stat1);
		new ParkingSlot(null,"Free",stat2);
		new ParkingSlot(null,"Free",stat2);
		Uniformity uN=new Uniformity();
		Station end=uN.computeEnd(new GPScoord(0, 0),new GPScoord(0,0), "Electrical");
		assertEquals(stat1, end);
		Station stat3= new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(0,-101), null);
		new ParkingSlot(null,"Free",stat3);
		new ParkingSlot(null,"Free",stat3);
		new ParkingSlot(null,"Free",stat3);
		end=uN.computeEnd(new GPScoord(0, 0),new GPScoord(0,0), "Electrical");
		assertEquals(stat3, end);
	}

}
