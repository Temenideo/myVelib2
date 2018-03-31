package myVelib;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import myVelib.Card.Card;
import myVelib.ridePolicies.NoEndStationAvailableException;
import myVelib.ridePolicies.NoStartStationAvailableException;
import myVelib.ridePolicies.RidePolicy;
import myVelib.Bicycle.Bicycle;

public class Location implements Observer{
	private Date timeStart;
	private Date timeEnd;
	private Station departure;
	private Station arrival;
	private Bicycle bike;
	private GPScoord start;
	private GPScoord end;
	private boolean hasStarted;
	private boolean hasEnded;
	private User user;
	private RidePolicy ridePolicy;

	public Location(User user, GPScoord start, GPScoord end, RidePolicy ridePolicy, String typeBike) throws NoEndStationAvailableException, NoStartStationAvailableException {
		this.user=user;
		this.start=start;
		this.end=end;
		this.hasStarted=false;
		this.hasEnded=false;
		this.user.setLocation(this);
		this.ridePolicy=ridePolicy;
		this.departure=ridePolicy.computeStart(start, end, typeBike);
		this.arrival=ridePolicy.computeEnd(start, end, typeBike);
		Reseau.getInstance().addLocation(this);
	}

	public Location(User user, Station departure) {
		this.user=user;
		this.departure=departure;
		this.hasStarted=true;
		this.timeStart=Calendar.getInstance().getTime();
		this.user.setLocation(this);
		this.hasEnded=false;
		Reseau.getInstance().addLocation(this);
	}
	
	public Location(User user, Station departure, String typeBike, RidePolicy policy) throws BadParkingSlotCreationException, NoEndStationAvailableException {
		this.user=user;
		this.departure=departure;
		this.hasStarted=true;
		this.timeStart=Calendar.getInstance().getTime();
		this.user.setLocation(this);
		this.hasEnded=false;
		this.ridePolicy=policy;
		this.takeBike(departure, typeBike);
		Reseau.getInstance().addLocation(this);
	}

	/**
	 * This method tries to retrieve a bike from the departure station. 
	 * It goes through all of the station's parking slot until it has found one that is holding a bike.
	 * If a bike is found, the location starts, hence the start time of the location is defined and a bike is linked to this location.
	 * If no bike is found, then the method gives an error message and finds another departure station fitting with the user's settings.
	 * @throws NoEndStationAvailableException 
	 */
//Pour l'instant aucune vérification n'est faite pour vérifier que l'user n'a pas déjà une location en cours
	public void takeBike(Station departure,String type) throws BadParkingSlotCreationException, NoEndStationAvailableException{
		while(bike==null) {
			for(ParkingSlot pS : departure.getParkingSlotList()) {
				if (pS.getBicycle().getTypeBike()==type){

					bike=pS.retrieveBike();
					break;}
			}
			break;
		}
		if(bike!=null) {
			this.departure=departure;
			this.timeStart=Calendar.getInstance().getTime();
			this.hasStarted=true;
			this.user.setRideNumber(user.getRideNumber()+1);
		}
		else {
			System.out.println("No bike is available in this station");
		}
	}

	@Override
	public String toString() {
		return "Location [timeStart=" + timeStart + ", timeEnd=" + timeEnd + ", departure=" + departure + ", arrival="
				+ arrival + ", bike=" + bike + ", start=" + start + ", end=" + end + ", hasStarted=" + hasStarted
				+ ", user=" + user + ", ridePolicy=" + ridePolicy + "]";
	}

	/**
	 * This method tries to store its bike in the arrival station. 
	 * It goes through all of the station's parking slot until it has found one that isn't holding a bike or free.
	 * If such a parking slot is found, the location ends, hence the end time of the location is defined, the bike is unlinked to the location
	 * and the cost of the location is computed.
	 * If no free parking slot is found, this method outputs an error message and finds another arrival station fitting with the user's settings.
	 * @throws NoEndStationAvailableException 
	 */

	public void returnBike(Station arrival) throws BadParkingSlotCreationException, NoEndStationAvailableException {
		boolean stored = false;
		while(stored==false) {
			for(ParkingSlot pS : arrival.getParkingSlotList()){
				stored = pS.storeBike(this.bike);		
				break;
			}
			break;
		}
		if(stored==true) {
			this.arrival=arrival;
			this.timeEnd=Calendar.getInstance().getTime();
			int duration = (int)Card.getDuration(timeStart, timeEnd, TimeUnit.MINUTES);
			int charge = this.user.getCard().getCharge(this, user);
			this.user.setTotalTime(user.getTotalTime()+(int)duration);
			this.user.setTotalCharges(user.getTotalCharges()+charge);
			this.bike=null;
			this.hasEnded=false;
			System.out.println("Bike location charged "+charge+"€");
			this.user.setLocation(null);

		}
		else {
			System.out.println("No parking slot is available in this station");
			this.ridePolicy.computeEnd(user.getPosition(), end, bike.getTypeBike());
		}

	}

	public boolean isHasEnded() {
		return hasEnded;
	}

	public void setHasEnded(boolean hasEnded) {
		this.hasEnded = hasEnded;
	}

	
	@Override
	public void updateArrival(Station arrival) throws NoEndStationAvailableException {
		System.out.println("The destination station isn't available anymore.");
		System.out.println("Please proceed to this new station");	
		this.ridePolicy.computeEnd(user.getPosition(), end, bike.getTypeBike());
	}


	public Date getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Station getArrival() {
		return arrival;
	}

	public void setArrival(Station arrival) {
		this.arrival = arrival;
	}

	public Bicycle getBike() {
		return bike;
	}

	public void setBike(Bicycle bike) {
		this.bike = bike;
	}

	public Station getDeparture() {
		return departure;
	}

	public void setDeparture(Station departure) {
		this.departure = departure;
	}

	public GPScoord getEnd() {
		return end;
	}

	public void setEnd(GPScoord end) {
		this.end = end;
	}

	public boolean isHasStarted() {
		return hasStarted;
	}

	public void setHasStarted(boolean hasStarted) {
		this.hasStarted = hasStarted;
	}

	public void setStart(GPScoord start) {
		this.start = start;
	}

	public GPScoord getStart() {
		return start;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RidePolicy getRidePolicy() {
		return ridePolicy;
	}

	public void setRidePolicy(RidePolicy ridePolicy) {
		this.ridePolicy = ridePolicy;
	}

}
