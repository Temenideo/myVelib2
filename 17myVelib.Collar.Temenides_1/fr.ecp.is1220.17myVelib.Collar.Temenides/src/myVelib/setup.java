package myVelib;

import myVelib.Bicycle.Electrical;
import myVelib.Bicycle.Mechanical;
import myVelib.ridePolicies.NoEndStationAvailableException;
/**
 * Classe remplissant le cahier des charges de la partie 2.5 sur l'initialisation d'un reseau
 * @author xavier
 *
 */
public class setup {
	/**
	 * Créer un reseau avec des stations, des parkings slots et des vélos selon les demandes de l'utilisateur
	 * @param n nombre de station à créer
	 * @param m nombre de parking slot à créer par station
	 * @throws BadStateStationCreationException
	 * @throws BadTypeStationCreationException
	 * @throws BadParkingSlotCreationException
	 * @throws NoEndStationAvailableException
	 */
	public static void startMyVelib(int n, int m) throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException{
		Reseau res=Reseau.getInstance();
		Station statTempo;
		if (n>0 && m>0){
			for (int i=0;i<n;i++){
				statTempo=new Station("Standard", "on service", new GPScoord(i, i), "Station"+i);
				for (int z=0;z<0.3*m;z++){
					new ParkingSlot(null, "Free", statTempo);
				}
				for(int z=0;z<m-((int) (0.3*m)+1);z++){
					if (z<0.3*(m-((int) (0.3*m)+1))){
						new ParkingSlot(new Electrical(),"Occupied",statTempo);
					}
					else {
						new ParkingSlot(new Mechanical(),"Occupied",statTempo);
					}
				}
			}
		}
		else{
			System.out.println("Veulliez mettre des données valides");
		}
	}
}
