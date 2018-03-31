package myVelib;

import java.util.Date;
/**
 * Cette classe permet de cr�er un historique des �tats du ParkingSlot, elle se compose de trois attributs un �tat sous la forme d'un booleen
 * et deux sous la forme d'une date.
 * @author xavier
 *
 */
public class TimeState {
	private boolean occupied;
	private Date start;
	private Date end;
	public Date getEnd() {
		return end;
	}
/**
 * Permet d'initialiser un timeState
 * @param occupied Etat du parkingSlot sous la forme d'un booleen, True si le parkingSlot est occup�, False sinon
 * @param start Date de d�part de l'�tat sous le format Date
 */
	public TimeState(boolean occupied, Date start) {
		super();
		this.occupied = occupied;
		this.start = start;
	}
	/**
	 * Permet de fixer la date de fin du TimeState
	 * @param end Date de fin du TimeState sous le format Date
	 */
	public void setEnd(Date end) {
		this.end = end;
	}
	/**
	 * Donne l'�tat du parkingSlot dans ce TimeState
	 * @return un bool�en, True si le parkingSlot est occup�, False sinon
	 */
	public boolean isOccupied() {
		return occupied;
	}
	/**
	 * Donne la date de D�part de ce TimeState
	 * @return La date de d�part du TimeState sous le format Date
	 */
	public Date getStart() {
		return start;
	}
	/**
	 * Permet de changer l'�tat du parkingSlot pendant ce TimeState
	 * @param occupied un bool�en, True si le parkingSlot est occup�, False sinon
	 */
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	/**
	 * Permet de changer la date de d�part du TimeState,fonction utilis� seulement dans les test
	 * @param start Date de d�part de l'�tat sous le format Date
	 */
	public void setStart(Date start) {
		this.start = start;
	}
	@Override
	public String toString() {
		return "TimeState [occupied=" + occupied + ", start=" + start + ", end=" + end + "]";
	}
	
	
	
	
}
