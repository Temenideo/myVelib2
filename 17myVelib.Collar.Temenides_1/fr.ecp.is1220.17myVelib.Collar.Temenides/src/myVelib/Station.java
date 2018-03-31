package myVelib;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import myVelib.ridePolicies.NoEndStationAvailableException;

/**
 * La classe station cr�era les objets de m�me nom
 * @author xavier
 *
 */
public class Station implements Observable {
	/**
	 * C'est la liste des ParkingSlot rattach� � cette station
	 */
	private ArrayList<ParkingSlot> parkingSlotList;
	private String typeStation;
	private String state;
	private GPScoord position;
	private static Long compteur=(long) 0;
	private Long stationID;
	private String name;
	private int freeSlots;
	private int freeBikes;
	/**
	 * C'est la liste des Locations en cours ou prevu qui ont pour arriv� cette station
	 */
	private ArrayList<Location> incomingRideList;
	/**
	 * Ce contructeur peux �tre amen� � renvoyer une erreur dans le cas o� le type de station ou le type d'�tat n'a pas �t� �crit correctement ou n'existe pas
	 * @param parkingSlotList La liste de ParkingSlot � rajouter � la station
	 * @param typeStation Cela doit �tre une chaine de caract�re du type Standard ou Plus
	 * @param state doit �tre une chaine de caract�re du type on service ou offline
	 * @param position Les coordonn�es GPS de la stations sous le format GPScoord
	 * @param name le nom de la station
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
	/**
	 *  Ce contructeur peux �tre amen� � renvoyer une erreur dans le cas o� le type de station ou le type d'�tat n'a pas �t� �crit correctement ou n'existe pas
	 * @param typeStation Cela doit �tre une chaine de caract�re du type Standard ou Plus
	 * @param state doit �tre une chaine de caract�re du type on service ou offline
	 * @param position Les coordonn�es GPS de la stations sous le format GPScoord
	 * @param name le nom de la station
	 * @throws BadStateStationCreationException
	 * @throws BadTypeStationCreationException
	 */
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
	/**
	 * Fonction qui permet de connaitre le type de la station
	 * @return Une chaine de caract�re correpondant au type de la station
	 */
	public String getTypeStation() {
		return typeStation;
	}
	/**
	 * Le changement d'�tat est aussi contr�l� afin que aucun �tat autre que Plus ou Standard ne soit mis
	 * @param typeStation le nouveau type de station � mettre
	 * @throws BadTypeStationCreationException
	 */
	public void setTypeStation(String typeStation) throws BadTypeStationCreationException {
		if (typeStation=="Standard" || typeStation=="Plus"){
			this.typeStation = typeStation;}
		else{
			throw new BadTypeStationCreationException(typeStation);
		}
	}
	/**
	 * Fonction qui permet d'acceder � la liste des ParkingSlot de cette station
	 * @return Une arrayList de ParkingSlot
	 */
	public ArrayList<ParkingSlot> getParkingSlotList() {
		return parkingSlotList;
	}
	/**
	 * Fonction qui permet d'acceder � l'�tat de la station
	 * @return une chaine de carat�re correspondant � l'�tat de la station
	 */
	public String getState() {
		return state;
	}
	/**
	 * Le changement d'�tat est aussi contr�l� afin que aucun �tat autre que on service ou offline ne soit mis
	 * @param state Le nouvelle �tat de la station � mettre
	 * @throws BadStateStationCreationException
	 */
	public void setState(String state) throws BadStateStationCreationException {
		if ( state=="on service" || state=="offline"){
			this.state = state;}
		else{
			throw new BadStateStationCreationException(state);
		}
	}
	/**
	 * Permet d'acceder aux coordonn�es GPS de la station
	 * @return Les coordonn�es GPS de la station sous le format GPScoord
	 */
	public GPScoord getPosition() {
		return position;
	}
	/**
	 * Permet d'acceder � l'identifiant de la station
	 * @return Un nombre sous le format Long representant l'identifiant de la station
	 */
	public Long getStationID() {
		return stationID;
	}
	/**
	 * Permet d'acceder au nom de la station
	 * @return Une chaine de carat�re correspondant au nom de la station
	 */
	public String getName() {
		return name;
	}
	/**
	 * Fonction qui permet d'acceder au nombre de ParkingSlot disponible rendre un v�lo
	 * @return un entier correspondant au nombre de parking slot disponible
	 */
	public int getFreeSlots() {
		return freeSlots;
	}
	/**
	 * Permet de connaitre le nombre de v�lo disponible dans la station
	 * @return un entier correspondant au nombre de v�lo disponible
	 */
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
	 * @param typeBike Type de velo dont on veux connaitre la disponibilit�
	 * @return un booleen, True s'il y a des v�los disponible, False sinon
	 */
	public boolean availableBike(String typeBike) {
		return NumberAvailableBike(typeBike)>0;
	}

	/**
	 * Calculates the number of bikes of the desired type that the station currently holds
	 * @param typeBike Type de velo dont on veux connaitre la disponibilit�
	 * @return un entier correspondant au nombre de v�lo disponible
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
	 * @return un boolen, True s'il y a des parkingSlot disponible, False sinon
	 */

	public boolean availableParkingSlot() {
		return (this.freeSlots>0);
	}
	/**
	 * Permet d'ajouter un ParkingSlot � la station
	 * @param pS	noveau PArkingSlot � rajouter
	 * @throws NoEndStationAvailableException
	 */
	public void addParkingSlot(ParkingSlot pS) throws NoEndStationAvailableException {
		parkingSlotList.add(pS);
		pS.setStation(this);
		this.calcul();
	}
/**
 * Permet d'enlever un ParkingSlot � la station
 * @param pS ParkingSlot � enlever
 * @throws NoEndStationAvailableException
 */
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
	 * Fonction qui donne le nombre d'op�ration de location dans cette station
	 * @return Un entier correpondant au nombre de location arrivant ou partant de cette station
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
	 * Fonction qui donne le nombre d'op�ration de retrour de v�lo dans cette station
	 * @return Un entier correspondant au nombre de location arrivant � cette station
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
	/**
	 * Permet d'avoir un taux d'occupation moyen des parkingSlot de cette station sur une dur�e determin�e
	 * @param start Date de d�part de l'�tude sous le format Date
	 * @param end Date de in de l'�tude sous le format Date
	 * @return Un relatif correspondant au taux d'occupation moyen
	 */
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
