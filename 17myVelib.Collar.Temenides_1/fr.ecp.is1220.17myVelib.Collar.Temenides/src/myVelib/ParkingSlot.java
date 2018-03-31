package myVelib;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import myVelib.Bicycle.Bicycle;
import myVelib.Card.Card;
import myVelib.ridePolicies.NoEndStationAvailableException;
/**
 * Une classe permettant de cr�er les objets de m�me nom
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
	 * Ce contructeur peux �tre amener � renvoyer une erreur dans le cas o� le type d'�tat n'a pas �t� �crit correctement ou n'existe pas
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
			this.bicycle = bicycle; // Possible de cr�er un parking slot avec l'�tat free et un v�lo � l'int�rieur
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

	public String getState() {
		return state;
	}
	/**
	 * Le changement d'�tat est aussi contr�l� afin que seuls les �tats possibles soit mis
	 * @param state
	 * @throws BadParkingSlotCreationException 
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
	 * 	
	 * @param start
	 * @param end
	 * @return
	 */
	public long getTimeOccupied(Date start,Date end){
		long timeOccupied=0;
		int longueur=history.size();
		for(int i=0;i<longueur-1;i++){
			// prends en compte les cas ou le timeState est inclus dans l'intervalle d'�tude
			if (history.get(i).getStart().compareTo(start)>=0 && history.get(i).getEnd().compareTo(end)<=0 && history.get(i).isOccupied()){
				timeOccupied=timeOccupied+getDuration(history.get(i).getStart(), history.get(i).getEnd(), TimeUnit.MINUTES);
			}
			// prends en compte les cas ou le timestate poss�de une partie dans l'intervalle mais sa fin est hors de la fenetre d'�tude
			else if(history.get(i).getStart().compareTo(start)>=0 && history.get(i).getEnd().compareTo(end)>=0 && history.get(i).isOccupied() && history.get(i).getStart().compareTo(end)<=0){
				timeOccupied=timeOccupied+getDuration(history.get(i).getStart(),end,TimeUnit.MINUTES);
			}
			// prends en compte les cas ou le timestate poss�de une partie dans l'intervalle mais son d�but est hors de la fenetre d'�tude
			else if(history.get(i).getEnd().compareTo(end)<=0 && history.get(i).getEnd().compareTo(start)<=0 && history.get(i).isOccupied() && history.get(i).getStart().compareTo(start)<=0){
				timeOccupied=timeOccupied+getDuration(start,history.get(i).getEnd(),TimeUnit.MINUTES);
			}
			// prends en compte les cas ou l'intervalle d'�tude est inclu dans le timestate
			else if(history.get(i).getEnd().compareTo(end)>0 && history.get(i).isOccupied() && history.get(i).getStart().compareTo(start)<0){
				timeOccupied=timeOccupied+getDuration(start,end,TimeUnit.MINUTES);
			}
		}
		// permet de considerer le dernier timestate qui n'a pas encore d'attribut end et avec un d�but dans l'intervalle
		if (history.get(longueur-1).getStart().compareTo(start)>=0 && history.get(longueur-1).isOccupied() && history.get(longueur-1).getStart().compareTo(end)<=0){
			// prends en compte que le fait que la date de fin de la p�riode d'�tude pourrai �tre plus tard que la date actuelle
			if(Calendar.getInstance().getTime().compareTo(end)<0){
				timeOccupied=timeOccupied+getDuration(history.get(longueur-1).getStart(),Calendar.getInstance().getTime(),TimeUnit.MINUTES);
			}
			else {
				timeOccupied=timeOccupied+getDuration(history.get(longueur-1).getStart(),end,TimeUnit.MINUTES);
			}
		}
		// permet de considerer le dernier timestate qui n'a pas encore d'attribut end et avec un d�but avant l'intervalle
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
	public static long getDuration(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime()-date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
}

	public Long getSlotID() {
		return slotID;
	}
	public Bicycle getBicycle() {
		return bicycle;
	}

	public ArrayList<TimeState> getHistory() {
		return history;
	}
	public void setBicycle(Bicycle bicycle) {
		this.bicycle = bicycle;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	@Override
	public String toString() {
		return "ParkingSlot " + slotID + ", state:" + state + ", " + bicycle + "";
	}


}