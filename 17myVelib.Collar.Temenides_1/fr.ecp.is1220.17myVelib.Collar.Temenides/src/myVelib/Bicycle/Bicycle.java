package myVelib.Bicycle;

import myVelib.Station;

/**
 * Permet de representer les vélos avec leur differents type
 * @author xavier
 *
 */
public abstract class Bicycle {
	private static Long compteur=(long) 1;
	private long bikeID;
	protected String typeBike; // protected est necessaire ici pour que les sous-classes y est accès
	public Bicycle() {
		super();
		bikeID=compteur;
		compteur++;
	}
	/**
	 * Permet d'acceder à l'identifiant du vélo
	 * @return une nombre correspondant à l'identifiant du vélo
	 */
	public long getBikeID() {
		return bikeID;
	}
	/**
	 * Permet d'accéder au type de vélo
	 * @return le type du vélo sous forme de chaine de caractère
	 */
	public String getTypeBike() {
		return typeBike;
	}
	@Override
	public boolean equals(Object obj){
		Bicycle bi;
		if (obj instanceof Bicycle){
			bi=(Bicycle) obj;
			return (bi.getBikeID()==this.bikeID);
		}
		return(false);
	}
	@Override
	public String toString() {
		return "Bicycle [bikeID=" + bikeID + ", typeBike=" + typeBike + "]";
	}
	
	
}
