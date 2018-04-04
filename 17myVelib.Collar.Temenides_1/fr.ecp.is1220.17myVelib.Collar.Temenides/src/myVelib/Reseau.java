package myVelib;

import java.text.ParseException;
import java.util.ArrayList;

import myVelib.SortingStations.SortingMethods;

/**
 * Classe Reseau permettant de rassembler la liste des stations, la liste des utilisateurs, la liste des locations
 * afin de permettre de faire les statistiques et d'acceder � diverses informations
 * @author xavier
 *
 */
public class Reseau {
	/**
	 * La classe reseau est constitu�e de 4 attributs,le nom de la station, une ArrayList de Station nomm�e stationList, une ArrayList de User nomm�e userList
	 * et une ArrayList de Location nomm�e locationList
	 */
	protected String name;
	protected ArrayList<Station> stationList;
	protected ArrayList<User> userList;
	protected ArrayList<Location> locationList;
	private static Reseau instance = null;
	
	/**
	 * Le constructeur est priv�e pour assurer l'unicit� du reseau cr�e	
	 */
	private Reseau() {
		super();
		this.stationList = new ArrayList<Station>();
		this.userList = new ArrayList<User>();
		this.locationList = new ArrayList<Location>();
	}
	/**
	 * Fonction qui permet de cr�er un reseau s'il n'est pas cr�e et sinon d'acc�der au reseau d�j� cr�e
	 * @return le reseau
	 */
	public static Reseau getInstance() {
		if (instance==null) {
			instance = new Reseau();
		}
		return instance;
	}
	/**
	 * Fonction qui permet d'acc�der au nom de la station
	 * @return le nom de la station
	 */
	public String getName() {
		return name;
	}
/**
 * Fonction qui permet de modifier le nom du reseau
 * @param name le nouveau nom du reseau
 */
	public void setName(String name) {
		this.name = name;
	}

/**
 * Fonction qui sert pour les test � remettre � zero le reseau et ne pas avoir d'historique pouvant interferer dans les r�sultats
 */
	public void resetReseau(){
		this.stationList = new ArrayList<Station>();
		this.userList = new ArrayList<User>();
		this.locationList = new ArrayList<Location>();
	}
	/**
	 * Fonction qui permet d'acceder � la liste des stations associ� � ce reseau
	 * @return une ArrayList de Station
	 */
	public ArrayList<Station> getStationList() {
		return stationList;
	}
	/**
	 * Fonction qui permet d'acceder � la liste de User associ� � ce reseau
	 * @return une ArrayList de User
	 */
	public ArrayList<User> getUserList() {
		return userList;
	}
	/**
	 * Fonction qui permet d'acceder � la liste de toutes les locations en cours ou pass� du ce reseau
	 * @return Une ArrayListe de loaction
	 */
	public ArrayList<Location> getLocationList() {
		return locationList;
	}
	/**
	 * Permet d'ajouter une nouvelle station � la liste du reseau
	 * @param station La nouvelle station � ajouter
	 */
	public void addStation(Station station) {
		stationList.add(station);
	}
	/**
	 * Permet d'ajouter un nouvel utilisateur � la liste du reseau
	 * @param user Utilisateur � ajouter
	 */
	public void addUser(User user) {
		userList.add(user);
	}
	/**
	 * Permet d'ajouter une nouvelle location � la liste du reseau
	 * @param loc La nouvelle location � ajouter
	 */
	public void addLocation(Location loc) {
		locationList.add(loc);
	}
	/**
	 * Permet d'enlever une station de la liste du reseau en cas de fermeture definitive d'une station
	 * @param station La station a enlever definitivement
	 */
	public void removeStation(Station station) {
		stationList.remove(station);
	}
	/**
	 * Permet d'enlever un utilisateur de la liste du reseau
	 * @param user L'utilisateur � enlever
	 */
	public void removeUser(User user) {
		userList.remove(user);
	}
	/**
	 * Permet d'enlever une location de la liste du reseau
	 * @param loc La location � enlever
	 */
	public void removeLocation(Location loc) {
		locationList.remove(loc);
	}

	@Override
	public String toString() {
		return "Reseau [name="+name+"stationList=" + stationList + ", userList=" + userList + ", locationList=" + locationList + "]";
	}
	
	/**
	 * This method finds the stations that are currently of the "Plus" type and returns the list of them
	 * @return	Une arrayList des StationsPlus
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
	 * @return Une arrayList des Stations non Plus
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
	 * Fonction qui permet de trier les stations du reseau selon un crit�re
	 * @param Sm crit�re de tri sous le format SortingMethods
	 * @throws ParseException
	 */
	public void SortStation(SortingMethods Sm) throws ParseException{
		Sm.sortStation(getStationList());
	}
}