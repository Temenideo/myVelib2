package scenarioTest.strategy;

import java.text.ParseException;
import java.util.ArrayList;

import myVelib.BadParkingSlotCreationException;
import myVelib.BadStateStationCreationException;
import myVelib.BadTypeStationCreationException;
import myVelib.ridePolicies.NoEndStationAvailableException;
import myVelib.ridePolicies.NoStartStationAvailableException;
/**
 * Interface regroupant toutes les commandes possibles lors des scénarios test
 * @author xavier
 *
 */
public interface StrategyMethode {
	/**
	 * Execute les intructions en lien avec le nom de la classe
	 * @param array la liste des paramètres pour executer la fonction sous la forme d'une ArrayList de Chaine de caractère
	 * @throws NumberFormatException
	 * @throws BadStateStationCreationException
	 * @throws BadTypeStationCreationException
	 * @throws BadParkingSlotCreationException
	 * @throws NoEndStationAvailableException
	 * @throws NoStartStationAvailableException
	 * @throws ParseException
	 */
	public void execute(ArrayList<String> array) throws NumberFormatException, BadStateStationCreationException, BadTypeStationCreationException, BadParkingSlotCreationException, NoEndStationAvailableException, NoStartStationAvailableException, ParseException;
}
