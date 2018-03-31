package myVelib;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

import myVelib.Bicycle.Electrical;
import myVelib.Bicycle.Mechanical;
import myVelib.ridePolicies.NoEndStationAvailableException;

public class StationTest {

	@Test
	public void testCalcul() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException {
		Reseau res = Reseau.getInstance();
		Station stat=new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(1,1), null);
		for (int i=0;i<10;i++){
			new ParkingSlot(null, "Free", stat);
		}
		for (int i=0;i<10;i++){
			new ParkingSlot(new Electrical(), "Occupied", stat);
		}
		for (int i=0;i<10;i++){
			new ParkingSlot(new Mechanical(), "Occupied", stat);
		}
		for (int i=0;i<10;i++){
			new ParkingSlot(new Mechanical(), "Broken", stat);
		}
		stat.calcul();
		assertEquals(20,stat.getFreeBikes());
		assertEquals(10, stat.getFreeSlots());
	}

	@Test
	public void testAvailableBike() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException {
		Reseau res = Reseau.getInstance();
		Station stat=new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(1,1), null);
		for (int i=0;i<10;i++){
			new ParkingSlot(null, "Free", stat);
		}
		for (int i=0;i<10;i++){
			new ParkingSlot(new Electrical(), "Occupied", stat);
		}
		for (int i=0;i<10;i++){
			new ParkingSlot(new Mechanical(), "Occupied", stat);
		}
		for (int i=0;i<10;i++){
			new ParkingSlot(new Mechanical(), "Broken", stat);
		}
		assertTrue(stat.availableBike("Electrical"));
		assertTrue(stat.availableBike("Mechanical"));
		assertTrue(stat.availableBike(null));
		assertFalse(stat.availableBike("hjgfdebh"));
	}

	@Test
	public void testAvailableParkingSlot() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException {
		Reseau res = Reseau.getInstance();
		Station stat=new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(1,1), null);
		for (int i=0;i<10;i++){
			new ParkingSlot(null, "Free", stat);
		}
		for (int i=0;i<10;i++){
			new ParkingSlot(new Electrical(), "Occupied", stat);
		}
		for (int i=0;i<10;i++){
			new ParkingSlot(new Mechanical(), "Occupied", stat);
		}
		for (int i=0;i<10;i++){
			new ParkingSlot(new Mechanical(), "Broken", stat);
		}
		stat.calcul();
		assertEquals(10, stat.getFreeSlots());
	}

	@Test
	public void testNumberOfRentsOperation() throws BadStateStationCreationException, BadTypeStationCreationException {
		Reseau res = Reseau.getInstance();
		User user=new User("Paul","Jacque");
		Location loc;
		Station stat=new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(1,1), null);
		Station stat1=new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(2,2), null);
		for (int i=0;i<10;i++){
			new Location(user,stat);
			loc=new Location(user,stat1);
			loc.setHasStarted(false);
			
		}
		assertEquals(10, stat.numberOfRentsOperation());
		assertEquals(0, stat1.numberOfRentsOperation());
	}

	@Test
	public void testNumberOfReturnOperation() throws BadStateStationCreationException, BadTypeStationCreationException {
		Reseau res = Reseau.getInstance();
		User user=new User("Paul","Jacque");
		Location loc;
		Station stat=new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(1,1), null);
		Station stat1=new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(2,2), null);
		for (int i=0;i<10;i++){
			new Location(user,stat);
			loc=new Location(user,stat1);
			loc.setArrival(stat);
			loc.setHasEnded(true);
			
		}
		for (int i=0;i<10;i++){
			new Location(user,stat);
			loc=new Location(user,stat1);	
		}
		assertEquals(10, stat.numberOfReturnOperation());
	}


	@Test
	public void testGetRateOfOccupation() {
		fail("Not yet implemented");
	}

}
