package myVelib.ridePolicies;
/**
 * Classe erreur lors qu'aucune station de départ n'est disponible pour un parcourt
 * @author xavier
 *
 */
public class NoStartStationAvailableException extends Exception {
	private static final long serialVersionUID = 1L;
	/**
	 * Renvoi un message à l'utilisateur pour l'informer du problème
	 */
	public NoStartStationAvailableException(){
		System.out.println("No station fitting your criteria is availabale for departure, please try again later or change your ride settings.");
	}
}
