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
	/**
	 * Test permettant de voir si le vélo a bien étais retire et le parking slot pret à accueillir un nouveau vélo
	 * @throws BadParkingSlotCreationException
	 * @throws BadStateStationCreationException
	 * @throws BadTypeStationCreationException
	 * @throws NoEndStationAvailableException
	 */
	@Test
	public void testGetBike() throws BadParkingSlotCreationException, BadStateStationCreationException, BadTypeStationCreationException, NoEndStationAvailableException {
		@SuppressWarnings("unused")
		Bicycle b1 = BicycleFactory.bike("MEchanical");
		@SuppressWarnings("unused")
		Bicycle b2 = BicycleFactory.bike("Electrical");
		Bicycle b4=BicycleFactory.bike("Mechanical");
		ParkingSlot pS = new ParkingSlot(b4, "Occupied",new Station("Standard", "on service", new GPScoord(0,0), null));
		Bicycle b3 = pS.retrieveBike();
		assertTrue(b3.equals(b4));
		assertEquals(null, pS.getBicycle());
		assertEquals("Free", pS.getState());
	}
	/**
	 * Test permettant de voir la réaction lorsqu'on veux retirer un vélo d'un parkingSlot vide
	 * @throws BadParkingSlotCreationException
	 * @throws BadStateStationCreationException
	 * @throws BadTypeStationCreationException
	 * @throws NoEndStationAvailableException
	 */
	@Test
	public void testGetBike2() throws BadParkingSlotCreationException, BadStateStationCreationException, BadTypeStationCreationException, NoEndStationAvailableException {
		@SuppressWarnings("unused")
		Bicycle b1 = BicycleFactory.bike("Mechanical");
		@SuppressWarnings("unused")
		Bicycle b2 = BicycleFactory.bike("Electrical");
		ParkingSlot pS = new ParkingSlot(null, "Free",new Station("Standard", "on service", new GPScoord(0,0), null));
		Bicycle b3 = pS.retrieveBike();
		assertEquals(null, b3);
	}
/**
 * Test permettant de voir s'il est bien possible de stocker le vélo
 * @throws BadParkingSlotCreationException
 * @throws BadStateStationCreationException
 * @throws BadTypeStationCreationException
 * @throws NoEndStationAvailableException
 */
	@Test
	public void testStoreBike() throws BadParkingSlotCreationException, BadStateStationCreationException, BadTypeStationCreationException, NoEndStationAvailableException {
		Reseau res=Reseau.getInstance();
		res.resetReseau();
		Bicycle b5 = BicycleFactory.bike("Electrical");
		ParkingSlot pS = new ParkingSlot(null, "Free",new Station("Standard", "on service", new GPScoord(0,0), null));
		pS.storeBike(b5);
		assertTrue(pS.getBicycle().equals(b5));
		assertEquals("Occupied", pS.getState());
		
	}
	/**
	 * Test permettant de voir la réaction quand veux stocker un vélo dans un parkingSlot déjà occupé ou cassé
	 * @throws BadParkingSlotCreationException
	 * @throws BadStateStationCreationException
	 * @throws BadTypeStationCreationException
	 * @throws NoEndStationAvailableException
	 */
		@Test
		public void testStoreBike2() throws BadParkingSlotCreationException, BadStateStationCreationException, BadTypeStationCreationException, NoEndStationAvailableException {
			Reseau res=Reseau.getInstance();
			res.resetReseau();
			Bicycle b1 = BicycleFactory.bike("Mechanical");
			Bicycle b5 = BicycleFactory.bike("Electrical");
			ParkingSlot pS = new ParkingSlot(b1, "Occupied",new Station("Standard", "on service", new GPScoord(0,0), null));
			assertFalse(pS.storeBike(b5));
			pS.setState("Broken");
			assertFalse(pS.storeBike(b5));

			
		}
/**
 * Permet de tester la fonction qui donne le temps d'occupation
 * @throws ParseException
 * @throws BadParkingSlotCreationException
 * @throws BadStateStationCreationException
 * @throws BadTypeStationCreationException
 * @throws NoEndStationAvailableException
 */
	@Test
	public void testGetTimeOccupied() throws ParseException, BadParkingSlotCreationException, BadStateStationCreationException, BadTypeStationCreationException, NoEndStationAvailableException {
		ParkingSlot pS = new ParkingSlot(null, "Occupied",new Station("Standard", "on service", new GPScoord(0,0), null));
		String string = "2018.03.25 AD at 12:08:56 PDT";
		String string2 = "2018.03.25 AD at 12:38:56 PDT";
		String string3 = "2018.03.25 AD at 15:38:56 PDT";
		String string4 = "2018.03.25 AD at 16:39:56 PDT";
		DateFormat format = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.ENGLISH);
		Date datestart = format.parse(string);
		Date dateend = format.parse(string2); 
		Date dateend2 = format.parse(string3);
		Date dateend3 = format.parse(string4);
		pS.getHistory().remove(0);
		pS.getHistory().add(new TimeState(true,datestart));
		pS.getHistory().get(0).setEnd(dateend);
		pS.getHistory().add(new TimeState(false,dateend));
		pS.getHistory().get(1).setEnd(dateend2);
		pS.getHistory().add(new TimeState(true,dateend2));
		pS.getHistory().get(2).setEnd(dateend3);
		assertEquals(pS.getTimeOccupied(datestart, dateend3),91);
		
		
}

}
