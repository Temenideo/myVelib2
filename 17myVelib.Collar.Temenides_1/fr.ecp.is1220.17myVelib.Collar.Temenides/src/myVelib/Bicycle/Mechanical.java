package myVelib.Bicycle;

public class Mechanical extends Bicycle {
	private static double speed = 15;
	
	public Mechanical() {
		super();
		this.typeBike="Mechanical";
	}

	public double getSpeed() {
		return speed;
	}
	
	
	
}
