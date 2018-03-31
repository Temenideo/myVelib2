package myVelib;

public class BadParkingSlotCreationException extends Exception {

	/**
	 * Exeception lors d'une erreur dans la cr�ation d'un parking Slot, c'est lorsque l'�tat du parking slot n'est pas un �tat possible
	 * @author xavier
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Cr�e une ereur sans donn�e d'entr�e
	 */
	public BadParkingSlotCreationException() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Cr�e une erreur et renvoi un message expliquant le probl�me
	 * @param message c'est la param�tre que l'utilisateur � rentrer et qui ne corresponds pas un � un �tat
	 */
	public BadParkingSlotCreationException(String message) {
		super("Le type :"+message+"n'existe pas, veuillez inscrire parmis Free, Occupied, Broken");
		// TODO Auto-generated constructor stub
	}

}
