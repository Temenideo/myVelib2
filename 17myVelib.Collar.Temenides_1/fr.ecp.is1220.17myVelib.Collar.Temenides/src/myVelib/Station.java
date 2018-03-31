package myVelib;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import myVelib.ridePolicies.NoEndStationAvailableException;

/**
 * La classe station créera les objets de même nom
 * @author xavier
 *
 */
public class Station implements Observable {
	private ArrayList<ParkingSlot> parkingSlotList;
	private String typeStation;
	private String state;
	private GPScoord position;
	private static Long compteur=(long) 0;
	private Long stationID;
	private String name;
	private int freeSlots;
	private int freeBikes;
	private ArrayList<Location> incomingRideList;
	/**
	 * Ce contructeur peux être amené à renvoyer une erreur dans le cas où le type de station ou le type d'état n'a pas été écrit correctement ou n'existe pas
	 * @param parkingSlotList
	 * @param typeStation doit être une chaine de caractère du type Standard ou Plus
	 * @param state doit être une chaine de caractère du type on service ou offline
	 * @param position
	 * @param name
	 * @throws BadStateStationCreationException,BadTypeStationCreationException
	 */
	public Station(ArrayList<ParkingSlot> parkingSlotList, String typeStation, String state, GPScoord position,
			String name) throws BadStateStationCreationException,BadTypeStationCreationException {
		super();
		if (typeStation=="Standard" || typeStation=="Plus"){
			if ( state=="on service" || state=="offline"){
				compteur=compteur+1;
				stationID=compteur;
				this.parkingSlotList = parkingSlotList;
				this.typeStation = typeStation;
				this.state = state;
				this.position = position;
				this.name = name;
				this.incomingRideList=new ArrayList<Location>();
				Reseau.getInstance().addStation(this);
			}
			else {
				throw new BadStateStationCreationException(state);
			}
		}
		else {
			throw new BadTypeStationCreationException(typeStation);
		}


	}

	public Station(String typeStation, String state, GPScoord position,
			String name) throws BadStateStationCreationException,BadTypeStationCreationException {
		super();
		if (typeStation=="Standard" || typeStation=="Plus"){
			if ( state=="on service" || state=="offline"){
				compteur=compteur+1;
				stationID=compteur;
				this.parkingSlotList = new ArrayList<ParkingSlot>();
				this.typeStation = typeStation;
				this.state = state;
				this.position = position;
				this.name = name;
				this.incomingRideList=new ArrayList<Location>();
				Reseau.getInstance().addStation(this);
			}
			else {
				throw new BadStateStationCreationException(state);
			}
		}
		else {
			throw new BadTypeStationCreationException(typeStation);
		}


	}

	public String getTypeStation() {
		return typeStation;
	}
	/**
	 * Le changement d'état est aussi contrôlé afin que aucun état autre que Plus ou Standard ne soit mis
	 * @param typeStation
	 * @throws BadTypeStationCreationException
	 */
	public void setTypeStation(String typeStation) throws BadTypeStationCreationException {
		if (typeStation=="Standard" || typeStation=="Plus"){
			this.typeStation = typeStation;}
		else{
			throw new BadTypeStationCreationException(typeStation);
		}
	}
	public ArrayList<ParkingSlot> getParkingSlotList() {
		return parkingSlotList;
	}
	public String getState() {
		return state;
	}
	/**
	 * Le changement d'état est aussi contrôlé afin que aucun état autre que on service ou offline ne soit mis
	 * @param state
	 * @throws BadStateStationCreationException
	 */
	public void setState(String state) throws BadStateStationCreationException {
		if ( state=="on service" || state=="offline"){
			this.state = state;}
		else{
			throw new BadStateStationCreationException(state);
		}
	}
	public GPScoord getPosition() {
		return position;
	}
	public Long getStationID() {
		return stationID;
	}
	public String getName() {
		return name;
	}

	public int getFreeSlots() {
		return freeSlots;
	}

	public int getFreeBikes() {
		return freeBikes;
	}

	/**
	 * Method to recompute the number of available parking slots and bikes
	 * @throws NoEndStationAvailableException 
	 */
	public void calcul() throws NoEndStationAvailableException {
		int free = 0;
		int bikes = 0;
		for (ParkingSlot pS : parkingSlotList) {
			if(pS.getState().equalsIgnoreCase("Free")) {
				free++;
			}
			if(pS.getState().equalsIgnoreCase("Occupied")) {
				bikes++;
			}
		}
		freeBikes=bikes;
		freeSlots=free;
		if(freeSlots==0) {
			this.notifyEndRide();
		}
	}

	/**
	 * Method to check if the station currently holds a bike of the desired type in one of its parking slots.
	 */	
	public boolean availableBike(String typeBike) {
		if(freeBikes==0) {
			return false;
		}
		if(typeBike==null) {
			return (availableBike("Electrical")||availableBike("Mechanical"));
		}
		for (ParkingSlot pS : parkingSlotList) {
			if (pS.getState().equals("Occupied")) {
				if (pS.getBicycle().getTypeBike().equalsIgnoreCase(typeBike)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Calculates the number of bikes of the desired type that the station currently holds
	 * @return
	 */
	public int NumberAvailableBike(String typeBike) {
		int numb=0;
		for (ParkingSlot pS : parkingSlotList) {
			if (pS.getState().equals("Occupied")) {
				if (pS.getBicycle().getTypeBike().equalsIgnoreCase(typeBike)) {
					numb+=1;
				}
			}
		}
		return numb;
	}

	/**
	 * Method to check if the station currently has a free parking slot.
	 * @return
	 */

	public boolean availableParkingSlot() {
		return (this.freeSlots>0);
	}

	public void addParkingSlot(ParkingSlot pS) throws NoEndStationAvailableException {
		parkingSlotList.add(pS);
		pS.setStation(this);
		this.calcul();
	}

	public void removeParkingSlot(ParkingSlot pS) throws NoEndStationAvailableException {
		parkingSlotList.remove(pS);
		this.calcul();
	}

	@Override
	public void registerEndRide(Location loc) {
		this.incomingRideList.add(loc);
	}

	@Override
	public void removeRide(Location loc) {
		this.incomingRideList.remove(loc);
	}

	@Override
	public void notifyEndRide() throws NoEndStationAvailableException {
		if(this.freeSlots==0) {
			for(Location loc:incomingRideList) {
				loc.updateArrival(this);
			}
		}

	}
	@Override
	public boolean equals(Object obj){
		Station stat;
		if (obj instanceof Station){
			stat=(Station) obj;
			return (stat.getStationID()==this.stationID);
		}
		return(false);
	}
	@Override
	public String toString() {
		return "Station"+ stationID+" "+name+" ("+position+") Parking Slots:" + parkingSlotList;
	}
	/**
	 * Fonction qui donne le nombre d'opération de location dans cette station
	 * @return 
	 */
	public int numberOfRentsOperation(){
		ArrayList<Location> locationList=Reseau.getInstance().getLocationList();
		int compteur=0;
		for (Location loc :locationList){
			try{
				if(loc.getDeparture().equals(this) && loc.isHasStarted()){
					compteur++;
				}
			}
			finally{
			}

		}
		return(compteur);
	}
	/**
	 * Fonction qui donne le nombre d'opération de retrour de vélo dans cette station
	 * @return
	 */
	public int numberOfReturnOperation(){
		ArrayList<Location> locationList=Reseau.getInstance().getLocationList();
		int compteur=0;
		for (Location loc :locationList){
			if(loc.isHasEnded()){
				try {
					if(loc.getEnd().equals(this)){
						compteur++;
					}
				}
				finally{
				}
			}	
		}
		return(compteur);
	}
	public float getRateOfOccupation(Date start,Date end){
		long diffInMillies = end.getTime()-start.getTime();
		float duration=TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
		float rate=0;
		if (duration>0){
			for(ParkingSlot s:parkingSlotList){
				rate=rate+s.getTimeOccupied(start, end);
			}
			rate=rate/(parkingSlotList.size()*duration);
		}

		return(rate);
	}
}
