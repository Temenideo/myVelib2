package myVelib;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.junit.Test;

import myVelib.Bicycle.Bicycle;
import myVelib.Bicycle.BicycleFactory;
import myVelib.ridePolicies.NoEndStationAvailableException;

public class ParkingSlotTest {
	
	@Test
	public void testGetBike() throws BadParkingSlotCreationException, BadStateStationCreationException, BadTypeStationCreationException, NoEndStationAvailableException {
		@SuppressWarnings("unused")
		Bicycle b1 = BicycleFactory.bike("MEchanical");
		@SuppressWarnings("unused")
		Bicycle b2 = BicycleFactory.bike("Electrical");
		ParkingSlot pS = new ParkingSlot(BicycleFactory.bike("Mechanical"), "Occupied",new Station("Standard", "on service", new GPScoord(0,0), null));
		Bicycle b3 = pS.retrieveBike();
		assertEquals(b3.getBikeID(),4);
	}

	@Test
	public void testStoreBike() throws BadParkingSlotCreationException, BadStateStationCreationException, BadTypeStationCreationException, NoEndStationAvailableException {
		Bicycle b5 = BicycleFactory.bike("Electrical");
		ParkingSlot pS = new ParkingSlot(null, "Free",new Station("Standard", "on service", new GPScoord(0,0), null));
		pS.storeBike(b5);
		assertEquals(pS.getBicycle().getBikeID(),1);
		
	}

	@Test
	public void testGetTimeOccupied() throws ParseException, BadParkingSlotCreationException, BadStateStationCreationException, BadTypeStationCreationException, NoEndStationAvailableException {
		ParkingSlot pS = new ParkingSlot(null, "Occupied",new Station("Standard", "on service", new GPScoord(0,0), null));
		String string = "2018.03.26 AD at 12:08:56 PDT";
		String string2 = "2018.03.26 AD at 12:38:56 PDT";
		String string3 = "2018.03.26 AD at 15:38:56 PDT";
		String string4 = "2018.03.26 AD at 16:38:56 PDT";
		String string5 = "2018.03.26 AD at 17:38:56 PDT";
		String string6 = "2018.03.26 AD at 20:38:56 PDT";
		DateFormat format = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.ENGLISH);
		Date date1 = format.parse(string);
		Date date2 = format.parse(string2);
		Date date3 = format.parse(string3);
		Date date4 = format.parse(string4);
		Date date5 = format.parse(string5);
		Date date6 = format.parse(string6);
		pS.getHistory().add(new TimeState(true,date1));
		pS.getHistory().get(0).setEnd(date2);
		pS.getHistory().add(new TimeState(true,date2));
		pS.getHistory().get(1).setEnd(date3);
		pS.getHistory().add(new TimeState(true,date3));
		pS.getHistory().get(2).setEnd(date4);
		pS.getHistory().add(new TimeState(true,date4));
		pS.getHistory().get(2).setEnd(date5);
		pS.getHistory().add(new TimeState(true,date5));
		pS.getHistory().get(2).setEnd(date6);
		System.out.println(pS.getHistory());
		assertEquals(pS.getTimeOccupied(date1, date6),30);
		
		
	}

}
