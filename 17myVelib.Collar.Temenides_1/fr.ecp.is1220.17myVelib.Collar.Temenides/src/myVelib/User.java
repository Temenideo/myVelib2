package myVelib;
import myVelib.Card.Card;
import myVelib.Card.NoCard;
import myVelib.Card.VlibreCard;
import myVelib.Card.VmaxCard;
/**
 * Une classe permettant de representer les utilisateurs avec les fonctionnalités associées
 * @author xavier
 *
 */
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
	/**
	 * Permet la création d'un nouvelle utilisateur qui sera ajouté automatiquement au reseau
	 * @param name Chaine de caractère representant le nom de l'utilisateur
	 * @param firstName Chaine de caractère representant le prenom de l'utilisateur
	 */
	public User(String name, String firstName) {
		this.userID=IDCount;
		IDCount++;
		this.firstName=firstName;
		this.name=name;
		this.loc=null;
		this.card=new NoCard();
		Reseau.getInstance().addUser(this);
	}
	/**
	 * Permet à l'utilisateur d'avoir ses statistiques
	 * C'est à dire son nombre de location, son temps de location, la montant de ses dépenses et les montants des crédits gagnés
	 */
	public void getStatistics() {
		System.out.println("User "+userID+": "+firstName+" "+name);
		System.out.println("Number of locations: "+rideNumber);
		System.out.println("Total location time: "+totalTime+" minutes");
		System.out.println("Total charges payed: "+totalCharges+"€");
		System.out.println("Earned Credits: "+earnedCredits+" minutes");	
	}
	/**
	 * Permet à l'utilisateur de souscrire à une VlibCard
	 */
	public void subscribeVlibre() {
		this.card=new VlibreCard();
	}
	/**
	 * Permet à l'utilisateur de souscrire à une VmaxCard
	 */
	public void subscribeVmax() {
		this.card=new VmaxCard();
	}
	
	/*
	 * Getters and setters methods for all the User class attributes
	 */
	
	/**
	 * Fonction qui permet d'avoir le nom de l'utilisateur
	 * @return Une chaine de caractère correspondant au nom de l'utilisateur
	 */
	public String getName() {
		return name;
	}
	/**
	 * Fonction qui permet de modifier le nom de l'utilisateur
	 * @param name Chaine de caractère correpondant au nouveau nom
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Fonction qui permet d'avoir le prenom de l'utilisateur
	 * @return Une chaine de caractère correspondant au prenom de l'utilisateur
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Fonction qui permet de modifier le prenom de l'utilisateur
	 * @param name Chaine de caractère correpondant au nouveau prenom
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * Fonction qui permet d'avoir l'identifiant de l'utilisateur
	 * @return Un nombre sous le format long correspondant à l'identifant de l'utilisateur
	 */
	public long getUserID() {
		return userID;
	}
	/**
	 * Fonction qui permetb d'avoir le nombre de location de l'utilisateur
	 * @return Un entier correspondant au nombre de location de l'utilisateur
	 */
	public int getRideNumber() {
		return rideNumber;
	}
	/**
	 * Fonction qui permet de modifier le nombre de location de l'utilisateur
	 * @param rideNumber Nouvelle entier correspondant au nombre de location de l'utilisateur
	 */
	public void setRideNumber(int rideNumber) {
		this.rideNumber = rideNumber;
	}
	/**
	 * Fonction qui permet d'avoir le temps total passé sur en location de cet utilisateur
	 * @return Entier correspondant au temps total passé sur en location de cet utilisateur
	 */
	public int getTotalTime() {
		return totalTime;
	}
	/**
	 * Fonction qui permet de modifier le temps total passé sur en location de cet utilisateur
	 * @param totalTime Nouvelle entier correspondant au temps total passé sur en location de cet utilisateur
	 */
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	/**
	 * Fonction qui permet d'avoir le montant total depensé de cet utilisateur
	 * @return Nombre correspondant au montant total depensé de cet utilisateur
	 */
	public double getTotalCharges() {
		return totalCharges;
	}
	/**
	 * Fonction qui permet de modifier le montant total depensé de cet utilisateur
	 * @param totalCharges Nouveau nombre correspondant au montant total depensé de cet utilisateur
	 */
	public void setTotalCharges(double totalCharges) {
		this.totalCharges = totalCharges;
	}
	/**
	 * Fonction qui permet d'avoir le temps total gagné de cet utilisateur
	 * @return Nombre correspondant au temps total gagné de cet utilisateur
	 */
	public int getEarnedCredits() {
		return earnedCredits;
	}
	/**
	 * Fonction qui permet de modifier le temps total gagné de cet utilisateur
	 * @param earnedCredits Nouveau nombre correspondant au temps total gagné de cet utilisateur
	 */
	public void setEarnedCredits(int earnedCredits) {
		this.earnedCredits = earnedCredits;
	}
	/**
	 * Fonction qui permet d'acceder à la location de l'utilisateur
	 * @return La location de l'utilisateur
	 */
	public Location getLoc() {
		return loc;
	}
/**
 * Fonction qui permet de changer la location de l'utilisateur
 * @param loc Nouvelle location de l'utilisateur
 */
	public void setLocation(Location loc) {
		this.loc = loc;
	}
/**
 * Permet de connaitre la carte d'abonnement de l'utilisateur
 * @return Carte d'abonnement de l'utilisateur
 */
	public Card getCard() {
		return card;
	}
/**
 * Fonction permettant de modifier la carte d'abonnement de l'utilisateur
 * @param card Nouvelle carte d'abonnment de l'utilisateur
 */
	public void setCard(Card card) {
		this.card = card;
	}
/**
 * 
 * @return
 */
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
