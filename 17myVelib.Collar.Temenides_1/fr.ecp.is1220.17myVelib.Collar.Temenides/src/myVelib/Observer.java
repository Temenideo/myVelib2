package myVelib;

import myVelib.ridePolicies.NoEndStationAvailableException;
/**
 * Classe permettant d'implement les fonctionnalités d'Observer
 * @author xavier
 *
 */
public interface Observer {
	/**
	 * Actualise la station d'arriver lorsque la station intialement prévu n'est plus disponible
	 * @param arrival c'est la station qui doit signaler qu'elle n'a plus de place
	 * @throws NoEndStationAvailableException
	 */
	public void updateArrival(Station arrival) throws NoEndStationAvailableException;
}
