package myVelib.Bicycle;
/**
 * Represente les v�los �lectrique
 * @author xavier
 *
 */
public class Electrical extends Bicycle {
	private static double speed = 20;

	public Electrical() {
		super();
		this.typeBike="Electrical";
	}
/**
 * Permet d'avoir la vitesse avec ce v�lo
 * @return nombre representant la vitesse en Km/heure
 */
	public static double getSpeed() {
		return speed;
	}

}
