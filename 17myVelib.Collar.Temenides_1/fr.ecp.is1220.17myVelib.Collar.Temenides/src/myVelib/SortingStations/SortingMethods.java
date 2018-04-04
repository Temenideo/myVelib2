package myVelib.SortingStations;

import java.text.ParseException;
import java.util.ArrayList;

import myVelib.Station;

public interface SortingMethods {
	/**
	 * Fonction permettant d'afficher la liste des stations selon un crit�re donn�e
	 * @param stationList C'est la liste des stations � trier
	 * @throws ParseException
	 */
	public void sortStation(ArrayList<Station> stationList) throws ParseException;
}
