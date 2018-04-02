package myVelib.ridePolicies;
/**
 * Classe erreur lors qu'aucune station d'arriv�e n'est disponible pour un parcourt
 * @author xavier
 *
 */
public class NoEndStationAvailableException extends Exception {
	private static final long serialVersionUID = 0;
	/**
	 * Renvoi un message � l'utilisateur pour l'informer du probl�me
	 */
	public NoEndStationAvailableException() {
		System.out.println("No station fitting your criteria is availabale for the end, please try again later or change your ride settings");
	}

}
