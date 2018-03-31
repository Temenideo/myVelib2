package myVelib;

import java.util.Date;
/**
 * Cette classe permet de créer un historique des états du ParkingSlot, elle se compose de trois attributs un état sous la forme d'un booleen
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
	public void setEnd(Date end) {
		this.end = end;
	}
	public TimeState(boolean occupied, Date start) {
		super();
		this.occupied = occupied;
		this.start = start;
	}
	public boolean isOccupied() {
		return occupied;
	}
	public Date getStart() {
		return start;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	@Override
	public String toString() {
		return "TimeState [occupied=" + occupied + ", start=" + start + ", end=" + end + "]";
	}
	
	
	
	
}
