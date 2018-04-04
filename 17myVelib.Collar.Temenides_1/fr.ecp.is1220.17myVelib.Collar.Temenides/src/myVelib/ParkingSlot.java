package myVelib;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import myVelib.Bicycle.Bicycle;
import myVelib.Card.Card;
import myVelib.ridePolicies.NoEndStationAvailableException;
/**
 * Une classe permettant de créer les objets de même nom
 * @author xavier
 *
 */
public class ParkingSlot {
	private static Long compteur=(long) 0;
	private Long slotID;
	private String state;
	private Bicycle bicycle;
	private ArrayList<TimeState> history;
	private Station station;
	/**
	 * Ce contructeur peux être amener à renvoyer une erreur dans le cas où le type d'état n'a pas été écrit correctement ou n'existe pas
	 * @param bicycle
	 * @param State
	 * @throws BadParkingSlotCreationException
	 * @throws NoEndStationAvailableException 
	 */
	public ParkingSlot(Bicycle bicycle, String State, Station station) throws BadParkingSlotCreationException, NoEndStationAvailableException {
		super();
		if (State=="Occupied" ||State=="Broken"){
			compteur=compteur+1;
			slotID=compteur;
			this.bicycle = bicycle; // Possible de créer un parking slot avec l'état free et un vélo à l'intérieur
			this.state= State;
			this.station=station;
			this.history = new ArrayList<TimeState>();
			history.add(new TimeState(true,Calendar.getInstance().getTime()));
			station.addParkingSlot(this);
		}
		else if ( State=="Free" && bicycle == null) {
			compteur=compteur+1;
			slotID=compteur;
			this.state= State;
			this.station=station;
			this.history = new ArrayList<TimeState>();
			history.add(new TimeState(false,Calendar.getInstance().getTime()));	
			station.addParkingSlot(this);
		}
		else{
			throw new BadParkingSlotCreationException(State);}
	}
	/**
	 * Permet de connaitre l'état du ParkingSlot
	 * @return Une chaine de caractère donnant l'état du ParkingSlot
	 */
	public String getState() {
		return state;
	}
	/**
	 * Le changement d'état est aussi contrôlé afin que seuls les états possibles soit mis
	 * Cette fonction créer aussi un nouveau TimeState à chaque changement
	 * @param state le nouvelle état à mettre
	 * @throws BadParkingSlotCreationException Erreur en cas d'un état non possible
	 * @throws NoEndStationAvailableException 
	 */
	public void setState(String state) throws BadParkingSlotCreationException, NoEndStationAvailableException {
		if (state=="Occupied" || state=="Free" ||state=="Broken"){
			this.state = state;
			station.calcul();
			history.get(history.size()-1).setEnd(Calendar.getInstance().getTime());
			if (state=="Free"){
				history.add(new TimeState(false,Calendar.getInstance().getTime()));
			}
			else{
				history.add(new TimeState(true,Calendar.getInstance().getTime()));
			}
		}
		else{
			throw new BadParkingSlotCreationException(state);}
	}
	/**
	 * Fonction permettant d'enlever le vélo du ParkingSlot
	 * @return le vélo stocké dans ce ParkingSlot
	 * @throws BadParkingSlotCreationException
	 * @throws NoEndStationAvailableException
	 */
	public Bicycle retrieveBike() throws BadParkingSlotCreationException, NoEndStationAvailableException {
		if (state=="Occupied") {
			this.setState("Free");
			Bicycle bike = this.getBicycle();
			this.setBicycle(null);
			System.out.println(bike.getTypeBike()+" bike "+bike.getBikeID()+" has been retrieved from parking slot "+this.slotID);
			return bike;		
		}
		else {
			return null;
		}
	}
	/**
	 * Permet de stocker un velo dans ce ParkingSlot
	 * @param bike le vélo à stocker
	 * @return Un booleen pour confirmer que le vélo à bien été stocké
	 * @throws BadParkingSlotCreationException
	 * @throws NoEndStationAvailableException
	 */
	public boolean storeBike(Bicycle bike) throws BadParkingSlotCreationException, NoEndStationAvailableException {
		if (state!="Free") {
			return false;
		}
		else {
			this.setState("Occupied");
			this.bicycle=bike;
			System.out.println(bike.getTypeBike()+" bike "+bike.getBikeID()+" has been parked in parking slot "+this.slotID);
			return true;
		}
	}
	/**
	 * 	Permet de connaitre le temps d'occupation de ce parking slot entre deux dates
	 * @param start Date de départ
	 * @param end Date de fin
	 * @return un temps d'occupation en minute
	 */
	public long getTimeOccupied(Date start,Date end){
		long timeOccupied=0;
		int longueur=history.size();
		for(int i=0;i<longueur-1;i++){
			// prends en compte les cas ou le timeState est inclus dans l'intervalle d'étude
			if (history.get(i).getStart().compareTo(start)>=0 && history.get(i).getEnd().compareTo(end)<=0 && history.get(i).isOccupied()){
				timeOccupied=timeOccupied+getDuration(history.get(i).getStart(), history.get(i).getEnd(), TimeUnit.MINUTES);
			}
			// prends en compte les cas ou le timestate possède une partie dans l'intervalle mais sa fin est hors de la fenetre d'étude
			else if(history.get(i).getStart().compareTo(start)>=0 && history.get(i).getEnd().compareTo(end)>=0 && history.get(i).isOccupied() && history.get(i).getStart().compareTo(end)<=0){
				timeOccupied=timeOccupied+getDuration(history.get(i).getStart(),end,TimeUnit.MINUTES);
			}
			// prends en compte les cas ou le timestate possède une partie dans l'intervalle mais son début est hors de la fenetre d'étude
			else if(history.get(i).getEnd().compareTo(end)<=0 && history.get(i).getEnd().compareTo(start)<=0 && history.get(i).isOccupied() && history.get(i).getStart().compareTo(start)<=0){
				timeOccupied=timeOccupied+getDuration(start,history.get(i).getEnd(),TimeUnit.MINUTES);
			}
			// prends en compte les cas ou l'intervalle d'étude est inclu dans le timestate
			else if(history.get(i).getEnd().compareTo(end)>0 && history.get(i).isOccupied() && history.get(i).getStart().compareTo(start)<0){
				timeOccupied=timeOccupied+getDuration(start,end,TimeUnit.MINUTES);
			}
		}
		// permet de considerer le dernier timestate qui n'a pas encore d'attribut end et avec un début dans l'intervalle
		if (history.get(longueur-1).getStart().compareTo(start)>=0 && history.get(longueur-1).isOccupied() && history.get(longueur-1).getStart().compareTo(end)<=0){
			// prends en compte que le fait que la date de fin de la période d'étude pourrai être plus tard que la date actuelle
			if(Calendar.getInstance().getTime().compareTo(end)<0){
				timeOccupied=timeOccupied+getDuration(history.get(longueur-1).getStart(),Calendar.getInstance().getTime(),TimeUnit.MINUTES);
			}
			else {
				timeOccupied=timeOccupied+getDuration(history.get(longueur-1).getStart(),end,TimeUnit.MINUTES);
			}
		}
		// permet de considerer le dernier timestate qui n'a pas encore d'attribut end et avec un début avant l'intervalle
		if (history.get(longueur-1).getStart().compareTo(start)<0 && history.get(longueur-1).isOccupied()){
			if(Calendar.getInstance().getTime().compareTo(end)<0){
				timeOccupied=timeOccupied+getDuration(start,Calendar.getInstance().getTime(),TimeUnit.MINUTES);
			}
			else {
				timeOccupied=timeOccupied+getDuration(start,end,TimeUnit.MINUTES);
			}
		}
		return(timeOccupied);
	}
	/**
	 * Fonction intermedaire permettant de calculer une durée entre deux dates
	 * @param date1 Date de départ
	 * @param date2 Date de fin
	 * @param timeUnit Unité pour le temps final
	 * @return durée entre les deux dates dans l'unité voulu
	 */
	public static long getDuration(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime()-date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
	/**
	 * Permet de connaitre l'identifiant du ParkingSlot
	 * @return un nombre representant l'identifiant du ParkingSlot
	 */
	public Long getSlotID() {
		return slotID;
	}
	/**
	 * Permet d'acceder au vélo stocker dans le ParkingSlot
	 * @return Velo stocker dans le parkingSlot
	 */
	public Bicycle getBicycle() {
		return bicycle;
	}
	/**
	 * Permet d'avoir la liste des TimeState de cette station
	 * @return une ArrayList des TimeState
	 */
	public ArrayList<TimeState> getHistory() {
		return history;
	}
	/**
	 * Permet de changer le vélo stocker dans le parkingSlot
	 * @param bicycle le nouveau vélo a stocker
	 */
	public void setBicycle(Bicycle bicycle) {
		this.bicycle = bicycle;
	}
	/**
	 * Permet de connaitre la station où se trouve se ParkingSlot
	 * @return Une station
	 */
	public Station getStation() {
		return station;
	}
/**
 * Permet de modifier la station d'acceuil du ParkingSlot
 * @param station Nouvelle station
 */
	public void setStation(Station station) {
		this.station = station;
	}
	@Override
	public String toString() {
		return "ParkingSlot " + slotID + ", state:" + state + ", " + bicycle + "";
	}
	@Override
	public boolean equals(Object obj){
		ParkingSlot pS;
		if (obj instanceof ParkingSlot){
			pS=(ParkingSlot) obj;
			return (pS.getSlotID()==this.slotID);
		}
		return(false);
	}

}