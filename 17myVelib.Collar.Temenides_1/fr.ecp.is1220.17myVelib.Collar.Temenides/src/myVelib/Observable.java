package myVelib;

import myVelib.ridePolicies.NoEndStationAvailableException;
/**
 * Interface permettant � toutes les observers d'avoir les m�me fonctions
 * @author xavier
 *
 */
public interface Observable {
	/**
	 * Permet d'ajouter une location � la liste des Observers
	 * @param loc location � rajouter � la liste des Observers
	 */
	public void registerEndRide(Location loc);
	/**
	 * Permet de retirer une location de la liste des Observers
	 * @param loc location a retirer de la liste
	 */
	public void removeRide(Location loc);
	/**
	 * Permet de prevenir que la situation de la station a chang�
	 * @throws NoEndStationAvailableException
	 */
	public void notifyEndRide() throws NoEndStationAvailableException;
}
