package myVelib.ridePolicies;

import myVelib.GPScoord;
import myVelib.Reseau;
import myVelib.Station;

public class ShortestPath implements RidePolicy{

	@Override
	public Station computeStart(GPScoord start, GPScoord end, String typeBike)
			throws NoStartStationAvailableException {
		double dist=-1;
		Station startStation = null;
		Reseau reseau = Reseau.getInstance();
		// on parcours la liste des stations en regardant qu'elle respecte les critères
		for (Station stat1 : reseau.getStationList()) {
			for (Station stat2: reseau.getStationList()){
				if(stat1.getState().equalsIgnoreCase("On service") && stat2.getState().equalsIgnoreCase("On service") && stat1.availableBike(typeBike) && stat2.availableParkingSlot()) {
					// on regarde si elle est plus proche
					if (dist<0 || dist>(start.getDistance(stat1.getPosition())+stat1.getPosition().getDistance(stat2.getPosition())+stat2.getPosition().getDistance(end))) {
						dist=start.getDistance(stat1.getPosition())+stat1.getPosition().getDistance(stat2.getPosition())+stat2.getPosition().getDistance(end);
						startStation=stat1;
					}
				}
			}
		}
		// si la variable startSation n'est pas null c'est qu'au moins une station valide les critères
		if(startStation!=null) { 
			return(startStation);
		}
		// si ce n'est pas le cas on renvoie une erreur
		else
			throw new NoStartStationAvailableException();
	}

	@Override
	public Station computeEnd(GPScoord start, GPScoord end, String typeBike)
			throws NoEndStationAvailableException {
		double dist=-1;
		Station endStation = null;
		Reseau reseau = Reseau.getInstance();
		// on parcours la liste des stations en regardant qu'elle respecte les critères
		for (Station stat1 : reseau.getStationList()) {
			for (Station stat2: reseau.getStationList()){
				if(stat1.getState().equalsIgnoreCase("On service") && stat2.getState().equalsIgnoreCase("On service") && stat1.availableBike(typeBike) && stat2.availableParkingSlot()) {
					// on regarde si elle est plus proche
					if (dist<0 || dist>(start.getDistance(stat1.getPosition())+stat1.getPosition().getDistance(stat2.getPosition())+stat2.getPosition().getDistance(end))) {
						dist=start.getDistance(stat1.getPosition())+stat1.getPosition().getDistance(stat2.getPosition())+stat2.getPosition().getDistance(end);
						endStation=stat2;
					}
				}
			}
		}
		// si la variable startSation n'est pas null c'est qu'au moins une station valide les critères
		if(endStation!=null) { 
			return(endStation);
		}
		// si ce n'est pas le cas on renvoie une erreur
		else
			throw new NoEndStationAvailableException();
	}
}
