package myVelib.Bicycle;

public class Electrical extends Bicycle {
	private static double speed = 20;

	public Electrical() {
		super();
		this.typeBike="Electrical";
	}

	public static double getSpeed() {
		return speed;
	}

}
