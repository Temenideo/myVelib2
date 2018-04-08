package myVelib.ridePolicies;

import myVelib.GPScoord;
import myVelib.Station;

public interface RidePolicy {
	public static String policyType="Ride";
	/**
	 * Fonction permettant d'obtenir la station de d�part au vue d'un crit�re donn�
	 * @param start coordonn�es GPS du point de d�part sous le format GPScoord
	 * @param end coordonn�es GPS du point d'arriv�e sous le format GPScoord
	 * @param typeBike	type de v�lo voulu sous le format d'une chaine de caract�re
	 * @return la station de d�part pour le parcourt
	 * @throws NoStartStationAvailableException Erreur lorsqu'aucune station de d�part n'est possible
	 * @throws NoEndStationAvailableException Erreur lorsqu'aucune station d'arriv�e n'est possible
	 */
	public Station computeStart(GPScoord start,GPScoord end,String typeBike) throws NoStartStationAvailableException, NoEndStationAvailableException;
	/**
	 *  Fonction permettant d'obtenir la station d'arriv�e au vue d'un crit�re donn�
	 * @param start coordonn�es GPS du point de d�part sous le format GPScoord
	 * @param end coordonn�es GPS du point d'arriv�e sous le format GPScoord
	 * @param typeBike type de v�lo voulu sous le format d'une chaine de caract�re
	 * @return la station d'arriv�e pour le parcourt
	 * @throws NoEndStationAvailableException Erreur lorsqu'aucune station d'arriv�e n'est possible
	 */
	public Station computeEnd(GPScoord start,GPScoord end,String typeBike) throws NoEndStationAvailableException;
	public String getRidePolicy();
	
}
