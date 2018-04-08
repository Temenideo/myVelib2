package scenarioTest.strategy;

import java.util.ArrayList;

import myVelib.User;
/**
 * Permet de créer un nouvel utilisateur
 *  Pour exécuter cette opération, il faut mettre sur le fichier texte :
 *  "user;name;firstName;"
 * @author xavier
 *
 */
public class UserCreation implements StrategyMethode {
	@Override
	public void execute(ArrayList<String> array){
		new User(array.get(1),array.get(2));
	}
}
