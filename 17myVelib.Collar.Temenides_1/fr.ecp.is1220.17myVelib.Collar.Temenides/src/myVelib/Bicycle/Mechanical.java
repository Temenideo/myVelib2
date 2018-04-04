package myVelib.Bicycle;
/**
 * Represente les velos mécanique
 * @author xavier
 *
 */
public class Mechanical extends Bicycle {
	private static double speed = 15;
	
	public Mechanical() {
		super();
		this.typeBike="Mechanical";
	}
	/**
	 * Permet d'avoir la vitesse avec ce vélo
	 * @return nombre representant la vitesse en Km/heure
	 */
	public double getSpeed() {
		return speed;
	}
	
	
	
}
