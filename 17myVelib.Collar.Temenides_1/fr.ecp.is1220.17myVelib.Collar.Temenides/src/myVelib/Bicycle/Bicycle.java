package myVelib.Bicycle;

import myVelib.Station;

/**
 * Permet de representer les v�los avec leur differents type
 * @author xavier
 *
 */
public abstract class Bicycle {
	private static Long compteur=(long) 1;
	private long bikeID;
	protected String typeBike; // protected est necessaire ici pour que les sous-classes y est acc�s
	public Bicycle() {
		super();
		bikeID=compteur;
		compteur++;
	}
	/**
	 * Permet d'acceder � l'identifiant du v�lo
	 * @return une nombre correspondant � l'identifiant du v�lo
	 */
	public long getBikeID() {
		return bikeID;
	}
	/**
	 * Permet d'acc�der au type de v�lo
	 * @return le type du v�lo sous forme de chaine de caract�re
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
