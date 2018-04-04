package myVelib;

public class BadStateStationCreationException extends Exception {

	/**Une classe erreur dans le cas o� l'utilisateur n'a pas tap� un bon type lors de la cr�ation d'une station
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Cr�e une ereur sans donn�e d'entr�e
	 */
	public BadStateStationCreationException() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Cr�e une erreur et renvoi un message expliquant le probl�me
	 * @param message c'est la param�tre que l'utilisateur � rentrer et qui ne corresponds pas un � un �tat
	 */
	public BadStateStationCreationException(String message) {
		super("Le type :"+message+"n'existe pas, veuillez inscrire soit on service soit offline");
		// TODO Auto-generated constructor stub
	}

}
