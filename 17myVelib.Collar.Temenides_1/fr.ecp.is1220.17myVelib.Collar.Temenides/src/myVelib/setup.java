package myVelib;

import myVelib.Bicycle.Electrical;
import myVelib.Bicycle.Mechanical;
import myVelib.ridePolicies.NoEndStationAvailableException;

public class setup {
	public static void startMyVelib(int n, int m) throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException{
		Reseau res=Reseau.getInstance();
		Station statTempo;
		if (n>0 && m>0){
			for (int i=0;i<n;i++){
				statTempo=new Station("Standard", "on service", new GPScoord(i, i), "Station"+i);
				for (int z=0;z<0.3*m;z++){
					new ParkingSlot(null, "Free", statTempo);
				}
				for(int z=0;z<m-((int) (0.3*m)+1);z++){
					if (z<0.3*(m-((int) (0.3*m)+1))){
						new ParkingSlot(new Electrical(),"Occupied",statTempo);
					}
					else {
						new ParkingSlot(new Mechanical(),"Occupied",statTempo);
					}
				}
			}
		}
		else{
			System.out.println("Veulliez mettre des données valides");
		}
	}
	public static void main(String[] args) throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException {
		startMyVelib(10,10);
		System.out.println(Reseau.getInstance());
	}
}
