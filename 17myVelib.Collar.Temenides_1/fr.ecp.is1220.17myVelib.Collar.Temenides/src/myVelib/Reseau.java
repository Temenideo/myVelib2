package myVelib;

import java.text.ParseException;
import java.util.ArrayList;

import myVelib.SortingStations.SortingMethods;

/**
 * Classe Reseau permettant de rassembler la liste des stations, la liste des utilisateurs, la liste des locations
 * afin de permettre de faire les statistiques et d'acceder à diverses informations
 * @author xavier
 *
 */
public class Reseau {
	/**
	 * La classe reseau est constituée de 3 attributs, une ArrayList de Station nommée stationList, une ArrayList de User nommée userList
	 * et une ArrayList de Location nommée locationList
	 */
	protected ArrayList<Station> stationList;
	protected ArrayList<User> userList;
	protected ArrayList<Location> locationList;
	private static Reseau instance = null;
	
		
	private Reseau() {
		super();
		this.stationList = new ArrayList<Station>();
		this.userList = new ArrayList<User>();
		this.locationList = new ArrayList<Location>();
	}
	
	public static Reseau getInstance() {
		if (instance==null) {
			instance = new Reseau();
		}
		return instance;
	}
	public void resetReseau(){
		this.stationList = new ArrayList<Station>();
		this.userList = new ArrayList<User>();
		this.locationList = new ArrayList<Location>();
	}
	public ArrayList<Station> getStationList() {
		return stationList;
	}
	public ArrayList<User> getUserList() {
		return userList;
	}
	public ArrayList<Location> getLocationList() {
		return locationList;
	}
	
	public void addStation(Station station) {
		stationList.add(station);
	}
	
	public void addUser(User user) {
		userList.add(user);
	}
	
	public void addLocation(Location loc) {
		locationList.add(loc);
	}
	
	public void removeStation(Station station) {
		stationList.remove(station);
	}
	
	public void removeUser(User user) {
		userList.remove(user);
	}
	
	public void removeLocation(Location loc) {
		locationList.remove(loc);
	}

	@Override
	public String toString() {
		return "Reseau [stationList=" + stationList + ", userList=" + userList + ", locationList=" + locationList + "]";
	}
	
	/**
	 * This method finds the stations that are currently of the "Plus" type and returns the list of them
	 * @return
	 */
	
	public ArrayList<Station> getPlusStationList(){
		ArrayList<Station> plusList = new ArrayList<Station>();
		for (Station s : stationList) {
			if(s.getTypeStation().equalsIgnoreCase("Plus")) {
				plusList.add(s);
			}
		}
		return plusList;
	}
	
	/**
	 * This method finds the stations that are currently of the "Standard" type and returns the list of them
	 * @return
	 */
	
	public ArrayList<Station> getStandardStationList(){
		ArrayList<Station> standardList = new ArrayList<Station>();
		for (Station s : stationList) {
			if(s.getTypeStation().equalsIgnoreCase("Standard")) {
				standardList.add(s);
			}
		}
		return standardList;
	}
	/**
	 * Description a faire
	 * @param Sm
	 * @throws ParseException
	 */
	public void SortStation(SortingMethods Sm) throws ParseException{
		Sm.sortStation(getStationList());
	}
}
