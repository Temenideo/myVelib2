package myVelib.Bicycle;

public abstract class Bicycle {
	private static Long compteur=(long) 1;
	private long bikeID;
	protected String typeBike; // protected est necessaire ici pour que les sous-classes y est accès
	public Bicycle() {
		super();
		bikeID=compteur;
		compteur++;
	}
	
	public long getBikeID() {
		return bikeID;
	}
	public String getTypeBike() {
		return typeBike;
	}
	@Override
	public String toString() {
		return "Bicycle [bikeID=" + bikeID + ", typeBike=" + typeBike + "]";
	}
	
	
}
