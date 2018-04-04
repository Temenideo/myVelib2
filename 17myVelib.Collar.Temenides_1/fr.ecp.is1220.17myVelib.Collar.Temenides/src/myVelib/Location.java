package myVelib;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import myVelib.Card.Card;
import myVelib.ridePolicies.NoEndStationAvailableException;
import myVelib.ridePolicies.NoStartStationAvailableException;
import myVelib.ridePolicies.RidePolicy;
import myVelib.Bicycle.Bicycle;
/**
 * Cette classe represente la location d'un v�lo par un utilisateur
 * Chaque location ou intention de location par une planification entrainera la cr�ation d'un objet location
 * @author xavier
 *
 */
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
/**
 * Permet la cr�ation d'une location lorsque l'utilisateur veux programmer son parcourt
 * @param user	utilisateur qui veux faire une location
 * @param start	coordonn�es GPS de d�part
 * @param end	coordonn�es GPS d'arriv�e
 * @param ridePolicy	type de parcours voulu
 * @param typeBike	type de v�lo voulu
 * @throws NoEndStationAvailableException erreur dans le cas o� il n'y  pas de station d'arriv�e possible
 * @throws NoStartStationAvailableException erreur dans le cas o� il n'y pas de station de d�part voulu
 */
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
/**
 * Permet la cr�ation d'une location lorsque l'utilisateur se trouve � une station donn�e
 * @param user utilisateur qui veux faire une location
 * @param departure station de d�part
 */
	public Location(User user, Station departure) {
		this.user=user;
		this.departure=departure;
		this.hasStarted=true;
		this.timeStart=Calendar.getInstance().getTime();
		this.user.setLocation(this);
		this.hasEnded=false;
		Reseau.getInstance().addLocation(this);
	}
	/**
	 * Permet la cr�ation d'une location lorsque l'utilisateur veux programmer son parcourt
	 * @param user utilisateur qui veux faire une location
	 * @param departure station de d�part
	 * @param typeBike type de v�lo voulu
	 * @param policy type de parcours voulu
	 * @throws BadParkingSlotCreationException
	 * @throws NoEndStationAvailableException 
	 */
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
	 * @param departure station de d�part
	 * @param type type de v�lo voulu
	 * @throws BadParkingSlotCreationException
	 * @throws NoEndStationAvailableException 
	 */
	public void takeBike(Station departure,String type) throws BadParkingSlotCreationException, NoEndStationAvailableException{
		if(this.user.getLoc()==null) {
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
				this.user.setLocation(this);
			}
			else {
				System.out.println("No bike is available in this station");
			}
		}
		else {
			System.out.println("User is currently renting another bike. Please return it before getting a new one");
		}
		
	}

	@Override
	public String toString() {
		return "Location [timeStart=" + timeStart + ", timeEnd=" + timeEnd + ", departure=" + departure + ", arrival="
				+ arrival + ", bike=" + bike + ", start=" + start + ", end=" + end + ", hasStarted=" + hasStarted
				+ ", user=" + user + ", ridePolicy=" + ridePolicy + "]";
	}

	/**
	 *  This method tries to store its bike in the arrival station. 
	 * It goes through all of the station's parking slot until it has found one that isn't holding a bike or free.
	 * If such a parking slot is found, the location ends, hence the end time of the location is defined, the bike is unlinked to the location
	 * and the cost of the location is computed.
	 * If no free parking slot is found, this method outputs an error message and finds another arrival station fitting with the user's settings.
	 * @param arrival station o� l'on souhaite deposer le velo
	 * @throws BadParkingSlotCreationException
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
			int charge = (int) this.user.getCard().getCharge(this, user);
			this.user.setTotalTime(user.getTotalTime()+(int)duration);
			this.user.setTotalCharges(user.getTotalCharges()+charge);
			this.bike=null;
			this.hasEnded=true;
			System.out.println("Bike location charged "+charge+"�");
			this.user.setLocation(null);
			
			

		}
		else {
			System.out.println("No parking slot is available in this station");
		}

	}
/**
 * Permet de savoir si la location a fini ou non
 * @return retroune True si la location a fini
 */
	public boolean isHasEnded() {
		return hasEnded;
	}
/**
 * Permet de changer le param�tre hasEnded, lorsque la location a fini
 * @param hasEnded �tat de la location
 */
	public void setHasEnded(boolean hasEnded) {
		this.hasEnded = hasEnded;
	}


	@Override
	public void updateArrival(Station arrival) throws NoEndStationAvailableException {
		System.out.println("The destination station isn't available anymore.");
		System.out.println("Please proceed to this new station");
		this.arrival.removeRide(this);
		this.arrival=this.ridePolicy.computeEnd(start, end, bike.getTypeBike());
		this.arrival.registerEndRide(this);
	}

/**
 * Fonction qui donne l'heure de d�part de la location
 * @return Heure de d�part sous le format Date
 */
	public Date getTimeStart() {
		return timeStart;
	}
/**
 * Fonction qui permet de donner l'heure de d�part
 * @param timeStart heure de d�part
 */
	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}
/**
 * Fonction qui donne l'heure d'arriv�e de la location
 * @return Heure d'arriv�e sous le format Date
 */
	public Date getTimeEnd() {
		return timeEnd;
	}
	/**
	 * Fonction qui permet de donner l'heure d'arriver
	 * @param timeStart heure d'arriver
	 */
	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}
/**
 * Fonction qui donne la station d'arriv�e de la location
 * @return fonction d'arriv�e
 */
	public Station getArrival() {
		return arrival;
	}
/**
 * Fonction qui permet de donner la station d'arriv�e
 * @param arrival nouvelle station d'arriv�e
 */
	public void setArrival(Station arrival) {
		this.arrival.removeRide(this);
		this.arrival = arrival;
		this.arrival.registerEndRide(this);
	}
	/**
	 * Fonction servant uniquement pour les test, limitant le nombre de ligne de code dans certains test
	 * @param arrival nouvelle station d'arriv�e
	 */
	public void setArrivalForTest(Station arrival) {
		this.arrival = arrival;
	}
/**
 * Fonction qui permet de voir le velo de la location
 * @return le velo correspondant � cette location
 */
	public Bicycle getBike() {
		return bike;
	}
/**
 * Fonction qui permet de lier un velo � la location
 * @param bike le v�lo qui est associ� � cette location
 */
	public void setBike(Bicycle bike) {
		this.bike = bike;
	}
/**
 * Fonction qui donne la station de d�part de la location
 * @return Station de d�part de la location
 */
	public Station getDeparture() {
		return departure;
	}
/**
 * Fonction qui permet de changer la station de d�part de la location
 * @param departure nouvelle station de d�part
 */
	public void setDeparture(Station departure) {
		this.departure = departure;
	}
/**
 * Fonction qui permet d'acc�der aux coordonn�es GPS du lieux de destination (qui n'est pas forcement une station)
 * @return les coordonn�es GPS sous le format GPScoord
 */
	public GPScoord getEnd() {
		return end;
	}
/**
 * Fonction qui permet de changer les coordonn�es GPS du lieux d'arriv�e
 * @param end Nouvelle coordon�es GPS sous le format GPScoord
 */
	public void setEnd(GPScoord end) {
		this.end = end;
	}
/**
 * Fonction qui permet de savoir si la location a commenc� c'est a dire si un v�lo a �tait lou�
 * @return retourne un bool�en, True si la location a commenc� False sinon
 */
	public boolean isHasStarted() {
		return hasStarted;
	}
/**
 * Permet d'actualiser le commencement ou non de la location
 * @param hasStarted nouvelle �tat � mettre sous la forme d'un bool�en
 */
	public void setHasStarted(boolean hasStarted) {
		this.hasStarted = hasStarted;
	}
/**
 * Permet d'actualiser les coordonn�es GPS du point de d�part ( qui n'est pas forc�ment une station)
 * @param start nouvelle coordon�es GPS dans le format GPScoord
 */
	public void setStart(GPScoord start) {
		this.start = start;
	}
/**
 * Permet d'avoir les coordonn�es GPS du point de d�part
 * @return coordonn�es GPS du point de d�part sous le format GPScoord
 */
	public GPScoord getStart() {
		return start;
	}
	/**
	 * Permet d'acceder � l'user associ� � la location
	 * @return user asscoi� � la location
	 */
	public User getUser() {
		return user;
	}
/**
 * Fonction qui permet de changer l'utilisateur d'une location
 * @param user user � mettre
 */
	public void setUser(User user) {
		this.user = user;
	}
/**
 * Fonction qui permet d'acceder � la ridePolicy de cette location
 * @return la ridePolicy
 */
	public RidePolicy getRidePolicy() {
		return ridePolicy;
	}
/**
 * Fonction qui permet de changer la RidePolicy
 * @param ridePolicy nouvelle RidePolicy sous le format RidePolicy
 */
	public void setRidePolicy(RidePolicy ridePolicy) {
		this.ridePolicy = ridePolicy;
	}

}