package myVelib;
/**
 * Classe permettant de representer la position d'un objet. Il est composer de deux coordonnées pour representer la longitude et la latittude
 * @author xavier
 *
 */
public class GPScoord {
	private float latittude;
	private float longitude;
	/**
	 * Permet d'initialiser une position
	 * @param lat correspondant à la latittude
	 * @param lon correspondant à la longitude
	 */
	public GPScoord(float lat,float lon) {
		this.latittude=lat;
		this.longitude=lon;
	}
	/**
	 * Fonction qui donne la distance entre cette element et l'element mit en argument de la fonction
	 * @param place c'est les coordonées GPS de l'autre objet
	 * @return donne la distance entre les deux objets
	 */
	public double getDistance(GPScoord place) {
		return Math.sqrt((this.latittude-place.latittude)*(this.latittude-place.latittude)+(this.longitude-place.longitude)*(this.longitude-place.longitude));
	}
	/**
	 * Permet d'acceder à la lattitude de l'objet
	 * @return donne un flottant
	 */
	public float getLatittude() {
		return latittude;
	}
	/**
	 * Permet de modifier la lattitude d'un objet
	 * @param latittude c'est la nouvelle lattitude de l'objet
	 */
	public void setLatittude(float latittude) {
		this.latittude = latittude;
	}
	/**
	 * Permet d'acceder à la longitude de l'objet
	 * @return donne un flottant
	 */
	public float getLongitude() {
		return longitude;
	}
	/**
	 * Permet de modifier la longitude d'un objet
	 * @param latittude c'est la nouvelle longitude de l'objet
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return latittude + "," + longitude;
	}
	
	
}
