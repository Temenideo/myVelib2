package myVelib;

public class BadParkingSlotCreationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BadParkingSlotCreationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BadParkingSlotCreationException(String message) {
		super("Le type :"+message+"n'existe pas, veuillez inscrire parmis Free, Occupied, Broken");
		// TODO Auto-generated constructor stub
	}

}
