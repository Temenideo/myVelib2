package myVelib.SortingStations;

import java.text.ParseException;
import java.util.ArrayList;

import myVelib.Station;

public interface SortingMethods {
	/**
	 * Fonction permettant d'afficher la liste des stations selon un critère donnée
	 * @param stationList C'est la liste des stations à trier
	 * @throws ParseException
	 */
	public void sortStation(ArrayList<Station> stationList) throws ParseException;
}
