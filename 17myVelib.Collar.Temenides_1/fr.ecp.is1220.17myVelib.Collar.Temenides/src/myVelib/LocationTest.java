package myVelib;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import myVelib.Bicycle.Electrical;
import myVelib.Bicycle.Mechanical;
import myVelib.ridePolicies.NoEndStationAvailableException;

public class LocationTest {

	@Test
	public void testTakeBike() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException {
		User user=new User("Jean","Paul");
		Reseau res = Reseau.getInstance();
		res.resetReseau();
		for(int i=1; i<=3;i++) {
			res.addStation(new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(i,i), null));
			for (int j=1;j<=10;j++) {
				res.getStationList().get(i-1).addParkingSlot(new ParkingSlot(new Electrical(),"Occupied",res.getStationList().get(i-1)));
			}
			res.getStationList().get(i-1).addParkingSlot(new ParkingSlot(new Mechanical(), "Occupied",res.getStationList().get(i-1)));
		}
		Location loc1=new Location(user,res.getStationList().get(0));
		user.setLocation(null);
		loc1.takeBike(res.getStationList().get(0),"Mechanical");
		assertNotEquals(null, loc1.getBike());
	}

	@Test
	public void testReturnBike() throws BadParkingSlotCreationException, InterruptedException, BadStateStationCreationException, BadTypeStationCreationException, NoEndStationAvailableException {
		User user=new User("Jean","Paul");
		Reseau res = Reseau.getInstance();
		for(int i=1; i<=3;i++) {
			res.addStation(new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(i,i), null));
			for (int j=1;j<=10;j++) {
				res.getStationList().get(i-1).addParkingSlot(new ParkingSlot(new Electrical(),"Occupied",res.getStationList().get(i-1)));
			}
			res.getStationList().get(i-1).addParkingSlot(new ParkingSlot(new Mechanical(), "Occupied",res.getStationList().get(i-1)));
		}
		Location loc1=new Location(user,res.getStationList().get(0));
		loc1.takeBike(res.getStationList().get(0),"Electrical");
		loc1.returnBike(res.getStationList().get(0));
		assertEquals(null, loc1.getBike());
	}

}
