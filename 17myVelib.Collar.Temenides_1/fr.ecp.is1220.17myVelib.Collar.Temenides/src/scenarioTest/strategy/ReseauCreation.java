package scenarioTest.strategy;

import java.util.ArrayList;

import myVelib.Reseau;
/**
 * Permet de créer un reseau pour les futurs opérations
 * Pour exécuter cette opération, il faut mettre sur le fichier texte :
 * "reseau;"
 * @author xavier
 *
 */
public class ReseauCreation implements StrategyMethode {
	public void execute(ArrayList<String> array){
		Reseau.getInstance();
	}
}
