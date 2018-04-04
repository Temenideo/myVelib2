package myVelib;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

import myVelib.Bicycle.Electrical;
import myVelib.Bicycle.Mechanical;
import myVelib.ridePolicies.NoEndStationAvailableException;

public class StationTest {
/**
 * Permet de vérifier que test calcul mets à jour correctement les données de stations
 * @throws BadStateStationCreationException
 * @throws BadTypeStationCreationException
 * @throws BadParkingSlotCreationException
 * @throws NoEndStationAvailableException
 */
	@Test
	public void testCalcul() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException {
		Reseau res = Reseau.getInstance();
		res.resetReseau();
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
/**
 * Permet de test si la fonction Available avec les differents type de vélo
 * @throws BadStateStationCreationException
 * @throws BadTypeStationCreationException
 * @throws BadParkingSlotCreationException
 * @throws NoEndStationAvailableException
 */
	@Test
	public void testAvailableBike() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException {
		Reseau res = Reseau.getInstance();
		res.resetReseau();
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
		assertFalse(stat.availableBike("hjgfdebh"));
	}
/**
 * Permet de tester si la fonction renvoie le bon nombre de parkingSlot
 * @throws BadStateStationCreationException
 * @throws BadTypeStationCreationException
 * @throws BadParkingSlotCreationException
 * @throws NoEndStationAvailableException
 */
	@Test
	public void testAvailableParkingSlot() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException {
		Reseau res = Reseau.getInstance();
		res.resetReseau();
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
/**
 * Permet de tester la fonction dans le cas des locations qui partent de la station et dans le cas ou il n'y en a aucune
 * @throws BadStateStationCreationException
 * @throws BadTypeStationCreationException
 */
	@Test
	public void testNumberOfRentsOperation() throws BadStateStationCreationException, BadTypeStationCreationException {
		Reseau res = Reseau.getInstance();
		res.resetReseau();
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
/**
 * Permet de tester la fonction dans le cas des locations qui arrive sur la station
 * @throws BadStateStationCreationException
 * @throws BadTypeStationCreationException
 */
	@Test
	public void testNumberOfReturnOperation() throws BadStateStationCreationException, BadTypeStationCreationException {
		Reseau res = Reseau.getInstance();
		res.resetReseau();
		User user=new User("Paul","Jacque");
		Location loc;
		Station stat=new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(1,1), null);
		Station stat1=new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(2,2), null);
		for (int i=0;i<10;i++){
			new Location(user,stat);
			loc=new Location(user,stat1);
			loc.setArrivalForTest(stat);
			loc.setHasEnded(true);
			
		}
		for (int i=0;i<10;i++){
			new Location(user,stat);
			loc=new Location(user,stat1);	
		}
		assertEquals(10, stat.numberOfReturnOperation());
	}

/**
 * Test le taux d'occupation avec plusieurs cas inclus dans le test
 * @throws ParseException
 * @throws BadParkingSlotCreationException
 * @throws NoEndStationAvailableException
 * @throws BadStateStationCreationException
 * @throws BadTypeStationCreationException
 */
	@Test
	public void testGetRateOfOccupation() throws ParseException, BadParkingSlotCreationException, NoEndStationAvailableException, BadStateStationCreationException, BadTypeStationCreationException {
		Reseau res = Reseau.getInstance();
		Station stat=new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(1,1), null);
		ParkingSlot pS = new ParkingSlot(null, "Occupied",stat);
		ParkingSlot pS1 = new ParkingSlot(null, "Free",stat);
		String string = "2018.03.25 AD at 12:08:56 PDT";
		String string2 = "2018.03.25 AD at 12:38:56 PDT";
		DateFormat format = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.ENGLISH);
		Date datestart = format.parse(string);
		Date dateend = format.parse(string2); 
		pS.getHistory().remove(0);
		pS.getHistory().add(new TimeState(true,datestart));
		pS.getHistory().get(0).setEnd(dateend);
		pS1.getHistory().remove(0);
		pS1.getHistory().add(new TimeState(false,datestart));
		pS1.getHistory().get(0).setEnd(dateend);
		assertEquals(0.5, stat.getRateOfOccupation(datestart, dateend),0.001);
		}

}
