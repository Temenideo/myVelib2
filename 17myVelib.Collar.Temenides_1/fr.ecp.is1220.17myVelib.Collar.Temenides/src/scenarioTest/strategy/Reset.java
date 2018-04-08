package scenarioTest.strategy;

import java.util.ArrayList;

import myVelib.Reseau;
/**
 * Permet de reinitialiser le reseau si d'autres opération ont été faites, afin de ne pas pertuber le test
 *  Pour exécuter cette opération, il faut mettre sur le fichier texte :
 *  "rest;"
 * @author xavier
 *
 */
public class Reset implements StrategyMethode {

	@Override
	public void execute(ArrayList<String> array) {
		Reseau.getInstance().resetReseau();

	}

}
