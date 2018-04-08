package scenarioTest.strategy;

import java.util.ArrayList;

import myVelib.BadParkingSlotCreationException;
import myVelib.BadStateStationCreationException;
import myVelib.BadTypeStationCreationException;
import myVelib.GPScoord;
import myVelib.Location;
import myVelib.Reseau;
import myVelib.User;
import myVelib.ridePolicies.AvoidPlus;
import myVelib.ridePolicies.FastestPath;
import myVelib.ridePolicies.NoEndStationAvailableException;
import myVelib.ridePolicies.NoStartStationAvailableException;
import myVelib.ridePolicies.PreferPlus;
import myVelib.ridePolicies.RidePolicy;
import myVelib.ridePolicies.ShortestPath;
import myVelib.ridePolicies.Uniformity;
/**
 * Permet de créer une location avec la volontée de faire une parcours, le programme recupère le velo de la station de départ.
 * Pour exécuter cette opération, il faut mettre sur le fichier texte :
 * "planningride;IDuser;X coordonnées de départ;Y coordonnées de départ;X coordonnées d'arrivée;Y coordonnées d'arrivée;ridePolicy name;type de velo;"
 * @author xavier
 *
 */
public class Planningride implements StrategyMethode {

	@Override
	public void execute(ArrayList<String> array) throws NumberFormatException, BadStateStationCreationException,
			BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException, NoStartStationAvailableException {
		Reseau res=Reseau.getInstance();
		RidePolicy ridePolicy=null;
		Location loc=null;
		switch(array.get(6)){
		case "ShortestPath": ridePolicy=new ShortestPath();
		case "AvoidPlus": ridePolicy=new AvoidPlus();
		case "FastestPath" : ridePolicy=new FastestPath();
		case "PreferPlus" : ridePolicy=new PreferPlus();
		case "Uniformity" : ridePolicy=new Uniformity();
		}
		for(User user:res.getUserList()){
			if(user.getUserID()==Long.parseLong(array.get(1))){
				loc=new Location(user,new GPScoord(Float.parseFloat(array.get(2)),Float.parseFloat(array.get(3))),
						new GPScoord(Float.parseFloat(array.get(4)),Float.parseFloat(array.get(5))),ridePolicy,array.get(7));
				break;
			}
		}
		loc.takeBike(loc.getDeparture(),array.get(7));

	}

}
