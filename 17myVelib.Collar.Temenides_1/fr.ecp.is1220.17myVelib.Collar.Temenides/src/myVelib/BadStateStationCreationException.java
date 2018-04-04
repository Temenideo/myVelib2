package myVelib;

public class BadStateStationCreationException extends Exception {

	/**Une classe erreur dans le cas où l'utilisateur n'a pas tapé un bon type lors de la création d'une station
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Crée une ereur sans donnée d'entrée
	 */
	public BadStateStationCreationException() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Crée une erreur et renvoi un message expliquant le problème
	 * @param message c'est la paramètre que l'utilisateur à rentrer et qui ne corresponds pas un à un état
	 */
	public BadStateStationCreationException(String message) {
		super("Le type :"+message+"n'existe pas, veuillez inscrire soit on service soit offline");
		// TODO Auto-generated constructor stub
	}

}
