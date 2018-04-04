package myVelib.Card;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import myVelib.Location;
import myVelib.User;
/**
 * Classe abstraire afin de modeliser les differents type de carte et afin qu'elles aient des noms de fonction identiques
 * @author xavier
 *
 */
public abstract class Card {
	private int timeCredit;
	/**
	 * Fonction renvoyant le cout de la location
	 * @param loc La location a factur�
	 * @param user	L'utilisateur ayant effectu� cette location
	 * @return Un entier correpondant au cout de la location
	 */
	public float getCharge(Location loc, User user) {
		return 1;
	}
	
	/**
	 * This method outputs as a long the difference in minutes between two dates. It is used to compute the rental time of a location
	 * @param date1 Date de d�part de l'�v�nement
	 * @param date2	Date de fin de l'�v�nement
	 * @param timeUnit l'unit� dans laquelle on veux que la dur�e soit donn�e
	 * @return la dur�e entre les deux dates
	 */
	public static long getDuration(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime()-date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
	/**
	 * Fonction permettant d'acceder au Timecredit enregistr�e sur la carte
	 * @return le TimeCredit present sur la carte en minute
	 */
	public int getTimeCredit() {
		return timeCredit;
	}
	/**
	 * Fonction permettant de modifier le TimeCredit sur la carte
	 * @param timeCredit Nouveau TimeCredit � mettre en minute
	 */
	public void setTimeCredit(int timeCredit) {
		this.timeCredit = timeCredit;
	}
}
