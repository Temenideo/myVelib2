package myVelib;

import java.util.ArrayList;

import myVelib.Bicycle.Electrical;
import myVelib.Bicycle.Mechanical;
import myVelib.ridePolicies.NoEndStationAvailableException;

public class myVelibTest {
	public static void main(String[] args) throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException{
		Reseau res = Reseau.getInstance();
		for(int i=1; i<=3;i++) {
			res.addStation(new Station(new ArrayList<ParkingSlot>(), "Standard", "on service", new GPScoord(i,i), null));
			for (int j=1;j<=10;j++) {
				res.getStationList().get(i-1).addParkingSlot(new ParkingSlot(new Electrical(), "Occupied",res.getStationList().get(i-1)));
			}
			res.getStationList().get(i-1).addParkingSlot(new ParkingSlot(new Mechanical(), "Occupied",res.getStationList().get(i-1)));
		}
		System.out.println(res);
	}
}
