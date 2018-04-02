package myVelib.Card;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

import myVelib.BadParkingSlotCreationException;
import myVelib.BadStateStationCreationException;
import myVelib.BadTypeStationCreationException;
import myVelib.GPScoord;
import myVelib.Location;
import myVelib.ParkingSlot;
import myVelib.Reseau;
import myVelib.Station;
import myVelib.User;
import myVelib.Bicycle.Electrical;
import myVelib.Bicycle.Mechanical;


public class NoCardTest {
/**
 * Test la cas d'une location mecanique arrivant sur une station plus
 * @throws BadStateStationCreationException
 * @throws BadTypeStationCreationException
 * @throws BadParkingSlotCreationException
 * @throws InterruptedException
 * @throws ParseException
 */
	@Test
	public void testGetChargeMechanicalPlus() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, InterruptedException, ParseException {
		User user=new User("Jean","Paul");
		Reseau res = Reseau.getInstance();
		res.resetReseau();
		res.addStation(new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(1,1), null));
		Location loc1=new Location(user,res.getStationList().get(0));
		loc1.setBike(new Mechanical());
		String string = "2018.07.04 AD at 12:08:56 PDT";
		String string2 = "2018.07.04 AD at 13:38:56 PDT";
		DateFormat format = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.ENGLISH);
		Date datestart = format.parse(string);
		Date dateend = format.parse(string2);
		loc1.setTimeStart(datestart);
		loc1.setTimeEnd(dateend);
		loc1.setArrivalForTest(new Station(new ArrayList<ParkingSlot>(), "Plus", "on service", new GPScoord(1,1), null));
		Card card = new NoCard();
		float num=card.getCharge(loc1, user);
		assertEquals(2.5, num, 0.01);
		assertEquals(5, card.getTimeCredit(), 0.01);
	}
/**
 * Test le cas d'une location electrique
 * @throws BadStateStationCreationException
 * @throws BadTypeStationCreationException
 * @throws BadParkingSlotCreationException
 * @throws InterruptedException
 * @throws ParseException
 */
	@Test
	public void testGetChargeElectrical() throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, InterruptedException, ParseException {
		User user=new User("Jean","Paul");
		Reseau res = Reseau.getInstance();
		res.resetReseau();
		res.addStation(new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(1,1), null));
		Location loc1=new Location(user,res.getStationList().get(0));
		loc1.setBike(new Electrical());
		String string = "2018.07.04 AD at 12:08:56 PDT";
		String string2 = "2018.07.04 AD at 13:38:56 PDT";
		DateFormat format = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z", Locale.ENGLISH);
		Date datestart = format.parse(string);
		Date dateend = format.parse(string2);
		loc1.setTimeStart(datestart);
		loc1.setTimeEnd(dateend);
		loc1.setArrivalForTest(new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(1,1), null));
		Card card = new NoCard();
		float num=card.getCharge(loc1, user);
		assertEquals(5, num, 0.01);
	}
}
