  package myVelib.ridePolicies;

import myVelib.GPScoord;
import myVelib.Reseau;
import myVelib.Station;
import myVelib.User;

public class FastestPath implements RidePolicy{

	@Override
	public Station computeStart(GPScoord start, GPScoord end, String typeBike)
			throws NoStartStationAvailableException, NoEndStationAvailableException {
		Station arrival = computeEnd(start,end,typeBike);
		Reseau reseau = Reseau.getInstance();
		double travel = -1;
		Station departure = null;
			for (Station stat : reseau.getStationList()) {
				if(stat.getState().equalsIgnoreCase("On service") && stat.availableBike(typeBike)) {
						if (travel<0 || travel>User.getUserSpeed()*(start.getDistance(stat.getPosition()))+stat.getPosition().getDistance(arrival.getPosition())) {
							travel=User.getUserSpeed()*(start.getDistance(stat.getPosition()))+stat.getPosition().getDistance(arrival.getPosition());
							departure=stat;
						}
					}
				}
		// si la variable startSation n'est pas null c'est qu'au moins une station valide les critères
		if(departure!=null) { 
			return(departure);
		}
		// si ce n'est pas le cas on renvoie une erreur
		else
			throw new NoStartStationAvailableException();
	}

	@Override
	public Station computeEnd(GPScoord start,GPScoord end,String typeBike)
			throws NoEndStationAvailableException {
		Reseau reseau = Reseau.getInstance();
		double dist = -1;
		Station arrival = null;
		for (Station stat : reseau.getStationList()) {
			if(stat.getState().equalsIgnoreCase("On service")&&stat.availableParkingSlot()) {
				if (dist<0 || dist>end.getDistance(stat.getPosition())) {
					dist=end.getDistance(stat.getPosition());
					arrival=stat;
				}
				
			}
		}
		if(arrival!=null) { 
				return(arrival);
		}
		else
			throw new NoEndStationAvailableException();
	}

}
