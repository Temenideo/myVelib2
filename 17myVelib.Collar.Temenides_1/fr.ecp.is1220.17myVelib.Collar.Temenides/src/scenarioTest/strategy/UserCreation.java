package scenarioTest.strategy;

import java.util.ArrayList;

import myVelib.User;
/**
 * Permet de cr�er un nouvel utilisateur
 *  Pour ex�cuter cette op�ration, il faut mettre sur le fichier texte :
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
