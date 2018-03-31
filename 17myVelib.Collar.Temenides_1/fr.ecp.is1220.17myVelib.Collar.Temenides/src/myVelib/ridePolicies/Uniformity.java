package myVelib.ridePolicies;

import myVelib.GPScoord;
import myVelib.Reseau;
import myVelib.Station;

public class Uniformity implements RidePolicy{
	public Station computeStart(GPScoord start, GPScoord end, String typeBike)
			throws NoStartStationAvailableException {
		Reseau reseau = Reseau.getInstance();
		double dist=-1;
		Station startStation = null;
		int numberOfAvailibleBike=0;
		// ici on trouve la station la plus proche du lieu de départ
			for (Station stat : reseau.getStationList()) {
				if(stat.getState().equalsIgnoreCase("On service")) {
					if(stat.availableBike(typeBike)) {
						if (dist<0 || dist>start.getDistance(stat.getPosition())) {
							dist=start.getDistance(stat.getPosition());
							startStation=stat;
							numberOfAvailibleBike=stat.NumberAvailableBike(typeBike);
						}
					}
				}
			}
			// ici on compare si d'autres station aux alentours ont plus de vélo disponible
			for (Station stat : reseau.getStationList()) {
				if(stat.getState().equalsIgnoreCase("On service") && !stat.equals(startStation)) {
					if(stat.availableBike(typeBike)) {
						if (numberOfAvailibleBike<stat.NumberAvailableBike(typeBike) && 1.05*dist>start.getDistance(stat.getPosition())) {
							return(stat);
						}
					}
				}
			}
		// se cas se présente si la station la plus proche est aussi celle avec le plus de vélo
		if(startStation!=null) { 
			return(startStation);
		}
		else
			throw new NoStartStationAvailableException();
	}
	public Station computeEnd(GPScoord start,GPScoord end,String typeBike) throws NoEndStationAvailableException {
		Reseau reseau = Reseau.getInstance();
		double dist=-1;
		Station endStation = null;
		// ici on trouve la station la plus proche du lieu d'arriver
		for (Station stat : reseau.getStationList()) {
			if(stat.getState().equalsIgnoreCase("On service")) {
				if(stat.availableParkingSlot()) {
					if (dist<0 || dist>end.getDistance(stat.getPosition())) {
						dist=end.getDistance(stat.getPosition());
						endStation=stat;
					}
				}
			}
		}
		// ici on compare si d'autres station aux alentours ont plus de place disponible
		for (Station stat : reseau.getStationList()) {
			if(stat.getState().equalsIgnoreCase("On service") && !stat.equals(endStation)) {
					if (endStation.getFreeSlots()<stat.getFreeSlots() && 1.05*dist>end.getDistance(stat.getPosition())) {
						return(stat);
				}
			}
		}
		// se cas se présente si la station la plus proche est aussi celle avec le plus de place
		if(endStation!=null) { 
			return(endStation);
		}
		else
			throw new NoEndStationAvailableException();
	}
}
