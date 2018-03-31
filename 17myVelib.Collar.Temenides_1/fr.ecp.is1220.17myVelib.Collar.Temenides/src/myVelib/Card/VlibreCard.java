package myVelib.Card;

import java.util.concurrent.TimeUnit;

import myVelib.Location;
import myVelib.User;
/**
 * Classe permettant de representer les cartes Vlibre
 * @author xavier
 *
 */
public class VlibreCard extends Card{
	public double costMH1=0;
	public double costMH2=1;
	public double costEH1=1;
	public double costEH2=2;
	
	@Override
	public float getCharge(Location loc, User user) {
		if(loc.getArrival().getTypeStation().equals("Plus")) {
			this.setTimeCredit(getTimeCredit()+5);
			user.setEarnedCredits(user.getEarnedCredits()+5);
		}
		long duration = Card.getDuration(loc.getTimeStart(), loc.getTimeEnd(), TimeUnit.MINUTES);
		float hours = (float) duration/60;
		long min = duration%60;
		float timeMoreThanOneHour=duration-60;
		float cost = 0;
		if(loc.getBike().getTypeBike().equalsIgnoreCase("Mechanical")) {
			if(hours<1) {
				cost+=costMH1;
			}
			else if(timeMoreThanOneHour<this.getTimeCredit()){
				this.setTimeCredit(getTimeCredit()-(int)timeMoreThanOneHour);
				cost+=costMH1;
			}
			else{
				hours=(hours-1)- (float) this.getTimeCredit()/60;
				this.setTimeCredit(0);
				cost+=costMH1+costMH2*hours;
			}
			
		}
		if(loc.getBike().getTypeBike().equalsIgnoreCase("Electrical")) {
			if(hours<1) {
				cost+=costEH1;
			}
			else if(timeMoreThanOneHour<this.getTimeCredit()){
				this.setTimeCredit(getTimeCredit()-(int)timeMoreThanOneHour);
				cost+=costEH1;
			}
			else{
				hours=(hours-1)- (float) this.getTimeCredit()/60;
				this.setTimeCredit(0);
				cost+=costEH1+costEH2*hours;
			}
		}
		return cost;
	}
}
