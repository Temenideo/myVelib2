package myVelib.Bicycle;

public class BicycleFactory {
	public static Bicycle bike(String typeBike) {
		if (typeBike==null) {
			return null;
		}
		if(typeBike.equalsIgnoreCase("MECHANICAL")) {
			return new Mechanical();
		} else if (typeBike.equalsIgnoreCase("ELECTRICAL")) {
			return new Electrical();
		} else return null;
		
	}
}
