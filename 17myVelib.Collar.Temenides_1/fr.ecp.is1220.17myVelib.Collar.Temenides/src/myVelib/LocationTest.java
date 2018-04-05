package myVelib;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import myVelib.Bicycle.Electrical;
import myVelib.Bicycle.Mechanical;
import myVelib.ridePolicies.NoEndStationAvailableException;

public class LocationTest {
	/**
	 * Fonction verifiant que l'utilisateur a bien reussi à retirer un vélo
	 * @throws BadStateStationCreationException
	 * @throws BadTypeStationCreationException
	 * @throws BadParkingSlotCreationException
	 * @throws NoEndStationAvailableException
	 */
	@Test
	public void testTakeBike() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException {
		User user=new User("Jean","Paul");
		Reseau res = Reseau.getInstance();
		res.resetReseau();
		Station stat1=new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(1,1), null);
		new ParkingSlot(new Electrical(),"Occupied",stat1);
		Location loc1=new Location(user,stat1);
		user.setLocation(null);
		loc1.takeBike(stat1,"Electrical");
		assertNotEquals(null, loc1.getBike());
	}
	/**
	 * Fonction verifiant que l'utilisateur n'a pas pu retirer de velo car il n'y avait pas le bon type
	 * @throws BadStateStationCreationException
	 * @throws BadTypeStationCreationException
	 * @throws BadParkingSlotCreationException
	 * @throws NoEndStationAvailableException
	 */
	@Test
	public void testTakeBike2() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException {
		User user=new User("Jean","Paul");
		Reseau res = Reseau.getInstance();
		res.resetReseau();
		Station stat1=new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(1,1), null);
		new ParkingSlot(new Electrical(),"Occupied",stat1);
		Location loc1=new Location(user,stat1);
		user.setLocation(null);
		loc1.takeBike(stat1,"Mechanical");
		assertEquals(null, loc1.getBike());
	}
	/**
	 * Fonction verifiant que l'utilisateur n'a pas pu retirer de velo car il avait déjà une location en cours
	 * @throws BadStateStationCreationException
	 * @throws BadTypeStationCreationException
	 * @throws BadParkingSlotCreationException
	 * @throws NoEndStationAvailableException
	 */
	@Test
	public void testTakeBike3() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException {
		User user=new User("Jean","Paul");
		Reseau res = Reseau.getInstance();
		res.resetReseau();
		Station stat1=new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(1,1), null);
		new ParkingSlot(new Electrical(),"Occupied",stat1);
		new ParkingSlot(new Electrical(),"Occupied",stat1);
		Location loc1=new Location(user,stat1);
		user.setLocation(loc1);
		loc1.takeBike(stat1,"Electrical");
		assertEquals(null, loc1.getBike());
	}
	/**
	 * Test afin de voir si l'utilisateur peux bien rendre un vélo s'il y a une place disponible
	 * @throws BadParkingSlotCreationException
	 * @throws InterruptedException
	 * @throws BadStateStationCreationException
	 * @throws BadTypeStationCreationException
	 * @throws NoEndStationAvailableException
	 */
	@Test
	public void testReturnBike() throws BadParkingSlotCreationException, InterruptedException, BadStateStationCreationException, BadTypeStationCreationException, NoEndStationAvailableException {
		User user=new User("Jean","Paul");
		Reseau res = Reseau.getInstance();
		res.resetReseau();
		Station stat1=new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(1,1), null);
		new ParkingSlot(new Electrical(),"Occupied",stat1);
		Location loc1=new Location(user,stat1);
		user.setLocation(null);
		loc1.takeBike(stat1,"Electrical");
		loc1.returnBike(stat1);
		assertEquals(null, loc1.getBike());
	}

	/**
	 * Permet de tester le cas où l'utilisateur veux rendre son vélo alors qu'il n'y a plus de place sur la station
	 * @throws BadParkingSlotCreationException
	 * @throws InterruptedException
	 * @throws BadStateStationCreationException
	 * @throws BadTypeStationCreationException
	 * @throws NoEndStationAvailableException
	 */
	@Test
	public void testReturnBike2() throws BadParkingSlotCreationException, InterruptedException, BadStateStationCreationException, BadTypeStationCreationException, NoEndStationAvailableException {
		User user=new User("Jean","Paul");
		Reseau res = Reseau.getInstance();
		res.resetReseau();
		Station stat1=new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(1,1), null);
		new ParkingSlot(new Electrical(),"Occupied",stat1);
		Station stat2=new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(1,1), null);
		new ParkingSlot(new Electrical(),"Occupied",stat2);
		Location loc1=new Location(user,stat1);
		user.setLocation(null);
		loc1.takeBike(stat1,"Electrical");
		loc1.returnBike(stat2);
		assertNotNull(loc1.getBike());
	}

}