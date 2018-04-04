package myVelib.Bicycle;
/**
 * Represente les vélos électrique
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
 * Permet d'avoir la vitesse avec ce vélo
 * @return nombre representant la vitesse en Km/heure
 */
	public static double getSpeed() {
		return speed;
	}

}
