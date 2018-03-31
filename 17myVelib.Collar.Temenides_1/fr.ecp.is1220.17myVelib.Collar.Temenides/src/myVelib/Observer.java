package myVelib;

import myVelib.ridePolicies.NoEndStationAvailableException;

public interface Observer {
	public void updateArrival(Station arrival) throws NoEndStationAvailableException;
}
