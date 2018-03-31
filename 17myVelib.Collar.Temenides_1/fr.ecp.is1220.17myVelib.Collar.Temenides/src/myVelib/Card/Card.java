package myVelib.Card;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import myVelib.Location;
import myVelib.User;

public abstract class Card {
	private int timeCredit;
	
	public int getCharge(Location loc, User user) {
		return 1;
	}
	
	/**
	 * This method outputs as a long the difference in minutes between two dates. It is used to compute the rental time of a location
	 * 
	 */
	// aucune certitude que le temps date2 est supérieur au temps date1 (à voir si je casse pas juste les couilles)
	public static long getDuration(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime()-date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
	
	public int getTimeCredit() {
		return timeCredit;
	}
	public void setTimeCredit(int timeCredit) {
		this.timeCredit = timeCredit;
	}
}
