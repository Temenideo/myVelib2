package myVelib;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import myVelib.Bicycle.Electrical;
import myVelib.Bicycle.Mechanical;
import myVelib.ridePolicies.NoEndStationAvailableException;
/**
 * Il n'y a pas de test pour le tri des stations car les fonctions sont testées individuellement
 * @author xavier
 *
 */
public class ReseauTest {
/**
 * Test pour voir si toutes les stations Plus sont renvoyées
 * @throws BadParkingSlotCreationException 
 * @throws NoEndStationAvailableException 
 * @throws BadTypeStationCreationException 
 * @throws BadStateStationCreationException 
 */
	@Test
	public void testGetPlusStationList() throws NoEndStationAvailableException, BadParkingSlotCreationException, BadStateStationCreationException, BadTypeStationCreationException {
		Reseau res = Reseau.getInstance();
		res.resetReseau();
		ArrayList<Station> arrayStat=new ArrayList<Station>();
		for(int i=1; i<=3;i++) {
			Station stat1=new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(i,i), null);
			arrayStat.add(stat1);
		}
		new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(0,0), null);
		assertEquals(arrayStat, res.getPlusStationList());
	}
	/**
	 * Test pour voir si toutes les stations Standard sont renvoyées
	 * @throws BadParkingSlotCreationException 
	 * @throws NoEndStationAvailableException 
	 * @throws BadTypeStationCreationException 
	 * @throws BadStateStationCreationException 
	 */
	@Test
	public void testGetStandardStationList() throws BadStateStationCreationException, BadTypeStationCreationException {
		Reseau res = Reseau.getInstance();
		res.resetReseau();
		ArrayList<Station> arrayStat=new ArrayList<Station>();
		for(int i=1; i<=3;i++) {
			Station stat1=new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(i,i), null);
			arrayStat.add(stat1);
		}
		new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(0,0), null);
		assertEquals(arrayStat, res.getStandardStationList());
	}

}
