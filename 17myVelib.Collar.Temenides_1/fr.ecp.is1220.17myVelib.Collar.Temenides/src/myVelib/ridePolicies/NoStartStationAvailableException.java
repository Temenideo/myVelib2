package myVelib.ridePolicies;

public class NoStartStationAvailableException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NoStartStationAvailableException(){
		System.out.println("No station fitting your criteria is availabale for departure, please try again later or change your ride settings.");
	}
}
