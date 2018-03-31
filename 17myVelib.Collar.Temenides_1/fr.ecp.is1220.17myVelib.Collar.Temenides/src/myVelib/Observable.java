package myVelib;

import myVelib.ridePolicies.NoEndStationAvailableException;

public interface Observable {
	public void registerEndRide(Location loc);
	
	public void removeRide(Location loc);
	
	public void notifyEndRide() throws NoEndStationAvailableException;
}
