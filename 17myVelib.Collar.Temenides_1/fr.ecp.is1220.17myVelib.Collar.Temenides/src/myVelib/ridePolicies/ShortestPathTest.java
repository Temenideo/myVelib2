package myVelib.ridePolicies;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import myVelib.BadParkingSlotCreationException;
import myVelib.BadStateStationCreationException;
import myVelib.BadTypeStationCreationException;
import myVelib.GPScoord;
import myVelib.ParkingSlot;
import myVelib.Reseau;
import myVelib.Station;
import myVelib.Bicycle.Electrical;

public class ShortestPathTest {

	@Test
	public void testComputeSimpleCase() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoStartStationAvailableException, NoEndStationAvailableException {
		Station stat =new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(0,5), null);
		Station stat2= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(0,105), null);
		new ParkingSlot(new Electrical(),"Occupied",stat);
		new ParkingSlot(null,"Free",stat2);
		ShortestPath sP=new ShortestPath();
		Station departure=sP.computeStart(new GPScoord(0, 0),new GPScoord(0,100), "Electrical");
		Station arrival=sP.computeEnd(new GPScoord(0, 0),new GPScoord(0,100), "Electrical");
		assertEquals(stat, departure);
		assertEquals(stat2, arrival);
	}

	@Test
	public void testComputeThreeStation() throws BadStateStationCreationException, BadTypeStationCreationException, NoStartStationAvailableException, NoEndStationAvailableException, BadParkingSlotCreationException {
		Reseau res = Reseau.getInstance();
		res.resetReseau();
		Station stat1 =new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(0,1), null);
		Station stat2= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(0,-1), null);
		Station stat3= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(0,9), null);
		Station stat4= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(0,11), null);
		new ParkingSlot(new Electrical(),"Occupied",stat1);
		new ParkingSlot(new Electrical(),"Occupied",stat2);
		new ParkingSlot(null,"Free",stat3);
		new ParkingSlot(null,"Free",stat4);
		ShortestPath sP=new ShortestPath();
		Station departure=sP.computeStart(new GPScoord(0, 0),new GPScoord(0,10), "Electrical");
		Station arrival=sP.computeEnd(new GPScoord(0, 0),new GPScoord(0,10), "Electrical");
		assertEquals(stat1, departure);
		assertEquals(stat3, arrival);
		
	}
	@Test 
	public void testComputeThreeStationBis() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoStartStationAvailableException, NoEndStationAvailableException {
		Station stat1 =new Station(new ArrayList<ParkingSlot>(), "Plus", "offline", new GPScoord(200,200), null);
		Station stat2= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(200,205), null);
		Station stat3= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(200,203), null);
		
		new ParkingSlot(new Electrical(),"Occupied",stat1);
		new ParkingSlot(new Electrical(),"Occupied",stat3);
		new ParkingSlot(null,"Free",stat2);
		ShortestPath sP=new ShortestPath();
		Station departure=sP.computeStart(new GPScoord(200, 200),new GPScoord(205,200), "Electrical");
		Station arrival=sP.computeEnd(new GPScoord(200, 200),new GPScoord(205,200), "Electrical");
		assertEquals(stat3, departure);
		assertEquals(stat2, arrival);
		
	}
	@Test
	public void testComputeThreeStationBiss() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoStartStationAvailableException, NoEndStationAvailableException {
		Station stat1 =new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(301,300), null);
		Station stat2= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(300,309), null);
		Station stat3= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(300,302), null);	
		new ParkingSlot(new Electrical(),"Occupied",stat1);
		new ParkingSlot(new Electrical(),"Occupied",stat3);
		new ParkingSlot(null,"Free",stat2);
		ShortestPath sP=new ShortestPath();
		FastestPath fP = new FastestPath();
		Station departure=sP.computeStart(new GPScoord(300, 300),new GPScoord(300,310), "Electrical");
		Station fdeparture=fP.computeStart(new GPScoord(300, 300),new GPScoord(300,310), "Electrical");
		Station arrival=sP.computeEnd(new GPScoord(300, 300),new GPScoord(300,310), "Electrical");
		assertEquals(stat3, departure);
		assertEquals(stat2, arrival);
		assertNotEquals(fdeparture, departure);
		
	}
	@Test(expected=NoStartStationAvailableException.class)
	public void testComputeError() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoStartStationAvailableException, NoEndStationAvailableException {
		Reseau res = Reseau.getInstance();
		res.resetReseau();
		Station stat =new Station(new ArrayList<ParkingSlot>(), "Plus", "offline", new GPScoord(400,401), null);
		Station stat2= new Station(new ArrayList<ParkingSlot>(), "Plus", "offline", new GPScoord(400,(float) 405.5), null);
		new ParkingSlot(new Electrical(),"Broken",stat);
		new ParkingSlot(null,"Free",stat2);
		ShortestPath sP=new ShortestPath();
		Station departure=sP.computeStart(new GPScoord(400, 400),new GPScoord(405,400), "Electrical");
		@SuppressWarnings("unused")
		Station arrival=sP.computeEnd(new GPScoord(400, 400),new GPScoord(405,400), "Electrical");
		System.out.println(departure);
		
	}

}
