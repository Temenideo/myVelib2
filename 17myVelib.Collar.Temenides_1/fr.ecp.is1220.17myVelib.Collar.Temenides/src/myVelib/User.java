package myVelib;
import myVelib.Card.Card;
import myVelib.Card.NoCard;
import myVelib.Card.VlibreCard;
import myVelib.Card.VmaxCard;

public class User {
	private String name;
	private String firstName;
	private long userID;
	private int rideNumber;
	private int totalTime;
	private double totalCharges;
	private int earnedCredits;
	static long IDCount=444000000;
	private Location loc;
	private Card card;
	private GPScoord position;
	private static double userSpeed = 5;
	
	public User(String name, String firstName) {
		this.userID=IDCount;
		IDCount++;
		this.firstName=firstName;
		this.name=name;
		this.loc=null;
		this.card=new NoCard();
		Reseau.getInstance().addUser(this);
	}
	
	public void getStatistics() {
		System.out.println("User "+userID+": "+firstName+" "+name);
		System.out.println("Number of locations: "+rideNumber);
		System.out.println("Total location time: "+totalTime+" minutes");
		System.out.println("Total charges payed: "+totalCharges+"€");
		System.out.println("Earned Credits: "+earnedCredits+" minutes");	
	}
	
	public void subscribeVlibre() {
		this.card=new VlibreCard();
	}
	
	public void subscribeVmax() {
		this.card=new VmaxCard();
	}
	
	/*
	 * Getters and setters methods for all the User class attributes
	 */
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public int getRideNumber() {
		return rideNumber;
	}
	public void setRideNumber(int rideNumber) {
		this.rideNumber = rideNumber;
	}
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	public double getTotalCharges() {
		return totalCharges;
	}
	public void setTotalCharges(double totalCharges) {
		this.totalCharges = totalCharges;
	}
	public int getEarnedCredits() {
		return earnedCredits;
	}
	public void setEarnedCredits(int earnedCredits) {
		this.earnedCredits = earnedCredits;
	}
	
	public Location getLoc() {
		return loc;
	}

	public void setLocation(Location loc) {
		this.loc = loc;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public GPScoord getPosition() {
		return position;
	}

	public void setPosition(GPScoord position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "User "+ userID;
	}
	@Override
	public boolean equals(Object obj){
		User user;
		if (obj instanceof User){
			user=(User) obj;
			return (user.getUserID()==this.userID);
		}
		return(false);
	}

	public static double getUserSpeed() {
		return userSpeed;
	}	
}
