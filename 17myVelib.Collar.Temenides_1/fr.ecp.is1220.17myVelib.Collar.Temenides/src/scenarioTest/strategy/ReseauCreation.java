package scenarioTest.strategy;

import java.util.ArrayList;

import myVelib.Reseau;
/**
 * Permet de cr�er un reseau pour les futurs op�rations
 * Pour ex�cuter cette op�ration, il faut mettre sur le fichier texte :
 * "reseau;"
 * @author xavier
 *
 */
public class ReseauCreation implements StrategyMethode {
	public void execute(ArrayList<String> array){
		Reseau.getInstance();
	}
}
