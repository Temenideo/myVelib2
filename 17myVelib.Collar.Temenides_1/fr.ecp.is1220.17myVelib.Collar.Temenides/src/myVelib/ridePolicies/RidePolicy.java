package myVelib.ridePolicies;

import myVelib.GPScoord;
import myVelib.Station;

public interface RidePolicy {
	
	public Station computeStart(GPScoord start,GPScoord end,String typeBike) throws NoStartStationAvailableException, NoEndStationAvailableException;
	public Station computeEnd(GPScoord start,GPScoord end,String typeBike) throws NoEndStationAvailableException;
}
