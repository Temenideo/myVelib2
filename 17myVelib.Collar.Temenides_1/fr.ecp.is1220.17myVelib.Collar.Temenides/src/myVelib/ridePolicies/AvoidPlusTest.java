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

public class AvoidPlusTest {
	/**
	 * Test le cas o� malgr� qu'une station plus soir plus proche c'est bien la station Standard qui soit choisi
	 * @throws BadStateStationCreationException
	 * @throws BadTypeStationCreationException
	 * @throws BadParkingSlotCreationException
	 * @throws NoStartStationAvailableException
	 * @throws NoEndStationAvailableException
	 */
	@Test
	public void testAvoidPlus() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoStartStationAvailableException, NoEndStationAvailableException {
		Reseau res = Reseau.getInstance();
		res.resetReseau();
		Station stat =new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(100,99), null);
		Station stat2= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(100,105), null);
		Station stat3= new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(100,120), null);
		new ParkingSlot(new Electrical(),"Occupied",stat);
		new ParkingSlot(null,"Free",stat2);
		new ParkingSlot(null,"Free",stat3);
		AvoidPlus aP=new AvoidPlus();
		Station stat1=aP.computeStart(new GPScoord(100, 100),new GPScoord(100,100), "Electrical");
		Station stat4=aP.computeEnd(new GPScoord(100, 100),new GPScoord(100,110), "Electrical");
		assertEquals(stat, stat1);
		assertEquals(stat3, stat4);
	}
	/**
	 * Test le cas o� il n'y a pas de station remplissant le crit�re
	 * @throws BadStateStationCreationException
	 * @throws BadTypeStationCreationException
	 * @throws BadParkingSlotCreationException
	 * @throws NoStartStationAvailableException
	 * @throws NoEndStationAvailableException
	 */
	@Test (expected=NoEndStationAvailableException.class)
	public void testAvoidPlus2() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoStartStationAvailableException, NoEndStationAvailableException {
		Reseau res = Reseau.getInstance();
		res.resetReseau();
		Station stat =new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(100,99), null);
		Station stat3= new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(100,120), null);
		new ParkingSlot(new Electrical(),"Occupied",stat);
		new ParkingSlot(null,"Free",stat3);
		AvoidPlus aP=new AvoidPlus();
		Station stat1=aP.computeStart(new GPScoord(100, 100),new GPScoord(100,100), "Electrical");
		Station stat4=aP.computeEnd(new GPScoord(100, 100),new GPScoord(100,110), "Electrical");
	}
}
