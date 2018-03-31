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

public class FastestPathTest {

	@Test
	public void testComputeSimpleCase() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoStartStationAvailableException, NoEndStationAvailableException {
		Station stat =new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(100,100), null);
		Station stat2= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(100,105), null);
		new ParkingSlot(new Electrical(),"Occupied",stat);
		new ParkingSlot(null,"Free",stat2);
		FastestPath fP=new FastestPath();
		Station stat1=fP.computeStart(new GPScoord(100, 100),new GPScoord(100,106), "Electrical");
		Station stat3=fP.computeEnd(new GPScoord(100, 100),new GPScoord(100,106), "Electrical");
		assertEquals(stat, stat1);
		assertEquals(stat2, stat3);
	}

	@Test
	public void testComputeThreeStation() throws BadStateStationCreationException, BadTypeStationCreationException, NoStartStationAvailableException, NoEndStationAvailableException, BadParkingSlotCreationException {
		Station stat =new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(1,1), null);
		Station stat2= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(0,(float) 5.5), null);
		Station stat4= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(0,(float) 3), null);
		
		new ParkingSlot(new Electrical(),"Occupied",stat);
		new ParkingSlot(new Electrical(),"Occupied",stat4);
		new ParkingSlot(null,"Free",stat2);
		FastestPath fP=new FastestPath();
		Station stat1=fP.computeStart(new GPScoord(0, 0),new GPScoord(5,0), "Electrical");
		Station stat3=fP.computeEnd(new GPScoord(0, 0),new GPScoord(5,0), "Electrical");
		assertEquals(stat, stat1);
		assertEquals(stat2, stat3);
		
	}
	@Test 
	public void testComputeThreeStationBis() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoStartStationAvailableException, NoEndStationAvailableException {
		Reseau res = Reseau.getInstance();
		res.resetReseau();
		Station stat =new Station(new ArrayList<ParkingSlot>(), "Plus", "offline", new GPScoord(200,201), null);
		Station stat2= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(200,(float) 205.5), null);
		Station stat4= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(200,(float) 203), null);
		
		new ParkingSlot(new Electrical(),"Occupied",stat);
		new ParkingSlot(new Electrical(),"Occupied",stat4);
		new ParkingSlot(null,"Free",stat2);
		FastestPath fP=new FastestPath();
		Station stat1=fP.computeStart(new GPScoord(200, 200),new GPScoord(205,200), "Electrical");
		Station stat3=fP.computeEnd(new GPScoord(200, 200),new GPScoord(205,200), "Electrical");
		assertEquals(stat4, stat1);
		assertEquals(stat2, stat3);
		
	}
	@Test
	public void testComputeThreeStationBiss() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoStartStationAvailableException, NoEndStationAvailableException {
		Reseau res = Reseau.getInstance();
		res.resetReseau();
		Station stat =new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(300,301), null);
		Station stat2= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(300,(float) 305.5), null);
		Station stat4= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(300,(float) 303), null);	
		new ParkingSlot(new Electrical(),"Broken",stat);
		new ParkingSlot(new Electrical(),"Occupied",stat4);
		new ParkingSlot(null,"Free",stat2);
		FastestPath fP=new FastestPath();
		Station stat1=fP.computeStart(new GPScoord(300, 300),new GPScoord(305,300), "Electrical");
		Station stat3=fP.computeEnd(new GPScoord(300, 300),new GPScoord(305,300), "Electrical");
		assertEquals(stat4, stat1);
		assertEquals(stat2, stat3);
		
	}
	@Test(expected=NoEndStationAvailableException.class)
	public void testComputeError() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoStartStationAvailableException, NoEndStationAvailableException {
		Reseau res = Reseau.getInstance();
		res.resetReseau();
		Station stat =new Station(new ArrayList<ParkingSlot>(), "Plus", "offline", new GPScoord(400,401), null);
		Station stat2= new Station(new ArrayList<ParkingSlot>(), "Plus", "offline", new GPScoord(400,(float) 405.5), null);
		new ParkingSlot(new Electrical(),"Broken",stat);
		new ParkingSlot(null,"Free",stat2);
		FastestPath fP=new FastestPath();
		Station stat1=fP.computeStart(new GPScoord(400, 400),new GPScoord(405,400), "Electrical");
		Station stat3=fP.computeEnd(new GPScoord(400, 400),new GPScoord(405,400), "Electrical");
		System.out.println(stat1);
		
	}
}
