package myVelib.ridePolicies;

import myVelib.GPScoord;
import myVelib.Station;

public interface RidePolicy {
	public static String policyType="Ride";
	/**
	 * Fonction permettant d'obtenir la station de départ au vue d'un critère donné
	 * @param start coordonnées GPS du point de départ sous le format GPScoord
	 * @param end coordonnées GPS du point d'arrivée sous le format GPScoord
	 * @param typeBike	type de vélo voulu sous le format d'une chaine de caractère
	 * @return la station de départ pour le parcourt
	 * @throws NoStartStationAvailableException Erreur lorsqu'aucune station de départ n'est possible
	 * @throws NoEndStationAvailableException Erreur lorsqu'aucune station d'arrivée n'est possible
	 */
	public Station computeStart(GPScoord start,GPScoord end,String typeBike) throws NoStartStationAvailableException, NoEndStationAvailableException;
	/**
	 *  Fonction permettant d'obtenir la station d'arrivée au vue d'un critère donné
	 * @param start coordonnées GPS du point de départ sous le format GPScoord
	 * @param end coordonnées GPS du point d'arrivée sous le format GPScoord
	 * @param typeBike type de vélo voulu sous le format d'une chaine de caractère
	 * @return la station d'arrivée pour le parcourt
	 * @throws NoEndStationAvailableException Erreur lorsqu'aucune station d'arrivée n'est possible
	 */
	public Station computeEnd(GPScoord start,GPScoord end,String typeBike) throws NoEndStationAvailableException;
	public String getRidePolicy();
	
}
