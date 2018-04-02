package myVelib.ridePolicies;
/**
 * Classe erreur lors qu'aucune station d'arrivée n'est disponible pour un parcourt
 * @author xavier
 *
 */
public class NoEndStationAvailableException extends Exception {
	private static final long serialVersionUID = 0;
	/**
	 * Renvoi un message à l'utilisateur pour l'informer du problème
	 */
	public NoEndStationAvailableException() {
		System.out.println("No station fitting your criteria is availabale for the end, please try again later or change your ride settings");
	}

}
