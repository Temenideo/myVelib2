package myVelib;

public class BadParkingSlotCreationException extends Exception {

	/**
	 * Exeception lors d'une erreur dans la création d'un parking Slot, c'est lorsque l'état du parking slot n'est pas un état possible
	 * @author xavier
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Crée une ereur sans donnée d'entrée
	 */
	public BadParkingSlotCreationException() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Crée une erreur et renvoi un message expliquant le problème
	 * @param message c'est la paramètre que l'utilisateur à rentrer et qui ne corresponds pas un à un état
	 */
	public BadParkingSlotCreationException(String message) {
		super("Le type :"+message+"n'existe pas, veuillez inscrire parmis Free, Occupied, Broken");
		// TODO Auto-generated constructor stub
	}

}
