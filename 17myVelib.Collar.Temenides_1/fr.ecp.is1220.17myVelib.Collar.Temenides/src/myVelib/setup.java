package myVelib;

import myVelib.Bicycle.Electrical;
import myVelib.Bicycle.Mechanical;
import myVelib.ridePolicies.NoEndStationAvailableException;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Classe remplissant le cahier des charges de la partie 2.5 sur l'initialisation d'un reseau
 * @author xavier
 *
 */
public class setup {
	/**
	 * Créer un reseau avec des stations, des parkings slots et des vélos selon les demandes de l'utilisateur
	 * @param stationNumber nombre de station à créer
	 * @param slotsperStation nombre de parking slot à créer par station
	 * @throws BadStateStationCreationException
	 * @throws BadTypeStationCreationException
	 * @throws BadParkingSlotCreationException
	 * @throws NoEndStationAvailableException
	 */
	public static void startMyVelib(int stationNumber, int slotsperStation, double x, double y, double occupied, double ebike) throws BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException{	
		Station statTempo;
		if (stationNumber>0 && slotsperStation>0){
			for (int i=0;i<stationNumber;i++){
				double xi= ThreadLocalRandom.current().nextDouble(-x/2,x/2+1);
				double yi= ThreadLocalRandom.current().nextDouble(-y/2,y/2+1);
				statTempo=new Station("Standard", "on service", new GPScoord((float)xi,(float)yi), "Station"+i);
				for (int z=1;z<(1-occupied)*slotsperStation;z++){
					new ParkingSlot(null, "Free", statTempo);
				}
				for(int z=0;z<occupied*slotsperStation;z++){
					if (z<ebike*occupied*slotsperStation){
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
